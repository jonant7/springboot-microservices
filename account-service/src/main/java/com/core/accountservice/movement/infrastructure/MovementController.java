package com.core.accountservice.movement.infrastructure;

import com.core.accountservice.movement.application.MovementService;
import com.core.accountservice.movement.domain.dtos.MovementDTO;
import com.core.accountservice.movement.domain.dtos.MovementPatchDTO;
import com.core.accountservice.movement.domain.dtos.MovementPostDTO;
import com.core.accountservice.movement.domain.dtos.MovementPutDTO;
import com.core.accountservice.shared.domain.ResponseWrapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/movements")
public class MovementController {
    private final MovementService service;

    @GetMapping
    public ResponseEntity<ResponseWrapper<List<MovementDTO>>> list() {
        final var movements = service.list().stream()
                .map(MovementDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(ResponseWrapper.of(HttpStatus.OK, "Movements retrieved successfully", movements));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseWrapper<MovementDTO>> getById(@PathVariable Long id) {
        final var movement = service.get(id).orElse(null);
        if (movement == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseWrapper.error(HttpStatus.NOT_FOUND, "Movement not found"));
        }
        return ResponseEntity.ok(ResponseWrapper.of(HttpStatus.OK, "Movement found", new MovementDTO(movement)));
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper<MovementDTO>> create(@RequestBody MovementPostDTO dto) {
        final var movement = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseWrapper.of(HttpStatus.CREATED, "Movement created successfully", new MovementDTO(movement)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseWrapper<MovementDTO>> update(@PathVariable Long id, @RequestBody MovementPutDTO dto) {
        final var movement = service.update(id, dto);
        return ResponseEntity.ok(ResponseWrapper.of(HttpStatus.OK, "Movement updated successfully", new MovementDTO(movement)));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ResponseWrapper<MovementDTO>> edit(@PathVariable Long id, @RequestBody MovementPatchDTO dto) {
        final var movement = service.edit(id, dto);
        return ResponseEntity.ok(ResponseWrapper.of(HttpStatus.OK, "Movement edited successfully", new MovementDTO(movement)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseWrapper<Void>> deleteAccount(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(ResponseWrapper.of(HttpStatus.OK, "Movement deleted successfully", null));
    }
}
