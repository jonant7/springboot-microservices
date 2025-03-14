package com.core.accountservice.movement.application;

import com.core.accountservice.movement.domain.*;
import com.core.accountservice.movement.domain.dtos.MovementPatchDTO;
import com.core.accountservice.movement.domain.dtos.MovementPostDTO;
import com.core.accountservice.movement.domain.dtos.MovementPutDTO;
import com.core.accountservice.movement.domain.exceptions.MovementNotFoundException;
import com.core.accountservice.movement.infrastructure.MovementRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MovementService {
    private final MovementRepository repository;

    public MovementService(MovementRepository movementRepository) {
        this.repository = movementRepository;
    }

    public List<Movement> list() {
        return repository.findAll();
    }

    public Optional<Movement> get(Long id) {
        return repository.findById(id);
    }

    @Transactional(rollbackFor = Throwable.class)
    public Movement create(MovementPostDTO dto) {
//        repository.findByAccountTypeAndNumber(dto.getType(), dto.getNumber())
//                .ifPresent(p -> {
//                    throw new MovementAlreadyExistsException(dto.getType(), dto.getNumber());
//                });
        Movement movement = Movement.builder()
                .date(dto.getDate())
                .type(dto.getType())
                .value(dto.getValue())
                .balance(dto.getBalance())
                .build();
        return repository.save(movement);
    }

    @Transactional(rollbackFor = Throwable.class)
    public Movement update(Long id, MovementPutDTO dto) {
        return repository.findById(id)
                .map(account -> {
                    account.update(dto);
                    return repository.save(account);
                })
                .orElseThrow(() -> new MovementNotFoundException(id));
    }

    @Transactional(rollbackFor = Throwable.class)
    public Movement edit(Long id, MovementPatchDTO dto) {
        return repository.findById(id)
                .map(account -> {
                    account.edit(dto);
                    return repository.save(account);
                })
                .orElseThrow(() -> new MovementNotFoundException(id));
    }

    @Transactional(rollbackFor = Throwable.class)
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new MovementNotFoundException(id);
        }
        repository.deleteById(id);
    }

}
