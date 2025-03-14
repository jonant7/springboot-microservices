package com.core.accountservice.account.application;

import com.core.accountservice.account.domain.Account;
import com.core.accountservice.account.domain.dtos.AccountPatchDTO;
import com.core.accountservice.account.domain.dtos.AccountPostDTO;
import com.core.accountservice.account.domain.dtos.AccountPutDTO;
import com.core.accountservice.account.domain.exceptions.AccountAlreadyExistsException;
import com.core.accountservice.account.domain.exceptions.AccountNotFoundException;
import com.core.accountservice.account.infrastructure.AccountRepository;
import com.core.accountservice.customer.domain.CustomerExistResponseDTO;
import com.core.accountservice.customer.domain.CustomerNotFoundException;
import com.core.accountservice.shared.domain.ResponseWrapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AccountService {

    private final WebClient.Builder webClientBuilder;
    private final AccountRepository repository;

    @Value("${customer.service.url}")
    private String customerServiceUrl;

    public AccountService(WebClient.Builder webClientBuilder, AccountRepository repository) {
        this.webClientBuilder = webClientBuilder;
        this.repository = repository;
    }

    public List<Account> list() {
        return repository.findAll();
    }

    public List<Account> list(Long customerId) {
        ResponseWrapper<CustomerExistResponseDTO> responseWrapper = existCustomer(customerId);

        if (responseIsNotNull(responseWrapper) && responseWrapper.getData().getId().equals(customerId)) {
            return repository.findAllByCustomerId(customerId);
        }
        return List.of();
    }

    public Optional<Account> get(Long id) {
        return repository.findById(id);
    }

    public Optional<Account> getByAccount(String accountNumber) {
        return  repository.findByNumber(accountNumber);
    }

    @Transactional(rollbackFor = Throwable.class)
    public Account create(AccountPostDTO dto) {
        ResponseWrapper<CustomerExistResponseDTO> responseWrapper = existCustomer(dto.getCustomerId());
        if (!responseIsNotNull(responseWrapper) || !responseWrapper.getData().getId().equals(dto.getCustomerId())) {
            throw new CustomerNotFoundException(dto.getCustomerId());
        }
        repository.findByAccountTypeAndNumber(dto.getType(), dto.getNumber())
                .ifPresent(p -> {
                    throw new AccountAlreadyExistsException(dto.getType(), dto.getNumber());
                });
        Account account = Account.builder()
                .number(dto.getNumber())
                .accountType(dto.getType())
                .openingBalance(dto.getOpeningBalance())
                .balance(dto.getOpeningBalance())
                .status(dto.getStatus())
                .customerId(dto.getCustomerId())
                .build();
        return repository.save(account);
    }

    @Transactional(rollbackFor = Throwable.class)
    public Account update(Long id, AccountPutDTO dto) {
        return repository.findById(id)
                .map(account -> {
                    account.update(dto);
                    return repository.save(account);
                })
                .orElseThrow(() -> new AccountNotFoundException(id));
    }

    @Transactional(rollbackFor = Throwable.class)
    public Account edit(Long id, AccountPatchDTO dto) {
        return repository.findById(id)
                .map(account -> {
                    account.edit(dto);
                    return repository.save(account);
                })
                .orElseThrow(() -> new AccountNotFoundException(id));
    }

    @Transactional(rollbackFor = Throwable.class)
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new AccountNotFoundException(id);
        }
        repository.deleteById(id);
    }

    private ResponseWrapper<CustomerExistResponseDTO> existCustomer(Long customerId) {
        String url = String.format("%s/customers/%s/exist", customerServiceUrl, customerId);

        return webClientBuilder.build()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ResponseWrapper<CustomerExistResponseDTO>>() {
                })
                .block();
    }

    private boolean responseIsNotNull(ResponseWrapper<CustomerExistResponseDTO> responseWrapper) {
        return (Objects.nonNull(responseWrapper) && Objects.nonNull(responseWrapper.getData()));
    }
}
