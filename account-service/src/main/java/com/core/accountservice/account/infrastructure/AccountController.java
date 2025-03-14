package com.core.accountservice.account.infrastructure;

import com.core.accountservice.account.application.AccountService;
import com.core.accountservice.account.domain.dtos.AccountDTO;
import com.core.accountservice.account.domain.dtos.AccountPatchDTO;
import com.core.accountservice.account.domain.dtos.AccountPostDTO;
import com.core.accountservice.account.domain.dtos.AccountPutDTO;
import com.core.accountservice.shared.domain.ResponseWrapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService service;

    @GetMapping
    public ResponseEntity<ResponseWrapper<List<AccountDTO>>> list() {
        final var accounts = service.list().stream()
                .map(AccountDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(ResponseWrapper.of(HttpStatus.OK, "Accounts retrieved successfully", accounts));
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<ResponseWrapper<List<AccountDTO>>> list(@PathVariable Long id) {
        final var accounts = service.list(id).stream()
                .map(AccountDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(ResponseWrapper.of(HttpStatus.OK, "Accounts retrieved successfully", accounts));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseWrapper<AccountDTO>> get(@PathVariable Long id) {
        return service.get(id)
                .map(account -> ResponseEntity.ok(ResponseWrapper.of(HttpStatus.OK, "Account found", new AccountDTO(account))))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(ResponseWrapper.error(HttpStatus.NOT_FOUND, "Account not found")));
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper<AccountDTO>> create(@RequestBody AccountPostDTO dto) {
        final var account = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseWrapper.of(HttpStatus.CREATED, "Account created successfully", new AccountDTO(account)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseWrapper<AccountDTO>> update(@PathVariable Long id, @RequestBody AccountPutDTO dto) {
        final var account = service.update(id, dto);
        return ResponseEntity.ok(ResponseWrapper.of(HttpStatus.OK, "Account updated successfully", new AccountDTO(account)));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ResponseWrapper<AccountDTO>> edit(@PathVariable Long id, @RequestBody AccountPatchDTO dto) {
        final var account = service.edit(id, dto);
        return ResponseEntity.ok(ResponseWrapper.of(HttpStatus.OK, "Account edited successfully", new AccountDTO(account)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseWrapper<Void>> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(ResponseWrapper.of(HttpStatus.OK, "Account deleted successfully", null));
    }
}
