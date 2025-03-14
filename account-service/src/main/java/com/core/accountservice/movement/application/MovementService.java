package com.core.accountservice.movement.application;

import com.core.accountservice.account.domain.Account;
import com.core.accountservice.account.domain.exceptions.AccountNotFoundException;
import com.core.accountservice.account.infrastructure.AccountRepository;
import com.core.accountservice.movement.domain.Movement;
import com.core.accountservice.movement.domain.MovementType;
import com.core.accountservice.movement.domain.dtos.MovementPatchDTO;
import com.core.accountservice.movement.domain.dtos.MovementPostDTO;
import com.core.accountservice.movement.domain.dtos.MovementPutDTO;
import com.core.accountservice.movement.domain.exceptions.InsufficientFundsException;
import com.core.accountservice.movement.domain.exceptions.MovementNotFoundException;
import com.core.accountservice.movement.infrastructure.MovementRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MovementService {
    private final MovementRepository repository;
    private final AccountRepository accountRepository;

    public MovementService(MovementRepository movementRepository, AccountRepository accountRepository) {
        this.repository = movementRepository;
        this.accountRepository = accountRepository;
    }

    public List<Movement> list() {
        return repository.findAll();
    }

    public Optional<Movement> get(Long id) {
        return repository.findById(id);
    }

    @Transactional(rollbackFor = Throwable.class)
    public Movement create(MovementPostDTO dto) {
        Account account = accountRepository.findByNumber(dto.getAccountNumber())
                .orElseThrow(() -> new AccountNotFoundException(dto.getAccountNumber()));

        BigDecimal newBalance = adjustBalance(account.getBalance(), dto.getType(), dto.getValue());

        Movement movement = Movement.builder()
                .date(LocalDate.now())
                .type(dto.getType())
                .value(dto.getValue())
                .balance(newBalance)
                .account(account)
                .build();

        account.setBalance(newBalance);
        accountRepository.save(account);

        return repository.save(movement);
    }

    @Transactional(rollbackFor = Throwable.class)
    public Movement update(Long id, MovementPutDTO dto) {
        Movement movement = repository.findById(id)
                .orElseThrow(() -> new MovementNotFoundException(id));

        Account oldAccount = movement.getAccount();

        if (!dto.getAccountNumber().equals(oldAccount.getNumber())) {
            Account newAccount = accountRepository.findByNumber(dto.getAccountNumber())
                    .orElseThrow(() -> new AccountNotFoundException(dto.getAccountNumber()));

            BigDecimal revertedBalance = adjustBalance(oldAccount.getBalance(), movement.getType(), movement.getValue().negate());
            oldAccount.setBalance(revertedBalance);
            accountRepository.save(oldAccount);

            BigDecimal newBalance = adjustBalance(newAccount.getBalance(), dto.getType(), dto.getValue());
            newAccount.setBalance(newBalance);
            accountRepository.save(newAccount);

            movement.setAccount(newAccount);
            movement.setBalance(newBalance);
        } else {
            Account account = movement.getAccount();
            BigDecimal previousValue = movement.getValue();
            MovementType previousType = movement.getType();

            BigDecimal revertedBalance = adjustBalance(account.getBalance(), previousType, previousValue.negate());

            BigDecimal newBalance = adjustBalance(revertedBalance, dto.getType(), dto.getValue());

            movement.update(dto);
            movement.setBalance(newBalance);
            account.setBalance(newBalance);

            accountRepository.save(account);
        }

        return repository.save(movement);
    }

    @Transactional(rollbackFor = Throwable.class)
    public Movement edit(Long id, MovementPatchDTO dto) {
        Movement movement = repository.findById(id)
                .orElseThrow(() -> new MovementNotFoundException(id));

        Account account = movement.getAccount();
        BigDecimal previousValue = movement.getValue();
        MovementType previousType = movement.getType();

        BigDecimal revertedBalance = adjustBalance(account.getBalance(), previousType, previousValue.negate());
        movement.edit(dto);

        BigDecimal updatedValue = Optional.ofNullable(dto.getValue()).orElse(previousValue);
        MovementType updatedType = Optional.ofNullable(dto.getType()).orElse(previousType);

        BigDecimal newBalance = adjustBalance(revertedBalance, updatedType, updatedValue);

        movement.setBalance(newBalance);
        account.setBalance(newBalance);

        accountRepository.save(account);
        return repository.save(movement);
    }

    @Transactional(rollbackFor = Throwable.class)
    public void delete(Long id) {
        Movement movement = repository.findById(id)
                .orElseThrow(() -> new MovementNotFoundException(id));

        Account account = movement.getAccount();

        BigDecimal revertedBalance = adjustBalance(account.getBalance(), movement.getType(), movement.getValue().negate());
        account.setBalance(revertedBalance);

        accountRepository.save(account);
        repository.delete(movement);
    }

    private BigDecimal adjustBalance(BigDecimal currentBalance, MovementType type, BigDecimal value) {
        BigDecimal newBalance = (type == MovementType.DEPOSIT)
                ? currentBalance.add(value)
                : currentBalance.subtract(value);

        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new InsufficientFundsException(currentBalance);
        }
        return newBalance;
    }
}
