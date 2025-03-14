package com.core.customerservice.person.infrastructure;

import com.core.customerservice.person.application.PersonService;
import com.core.customerservice.person.domain.dtos.PersonDTO;
import com.core.customerservice.person.domain.dtos.PersonPostDTO;
import com.core.customerservice.person.domain.dtos.PersonPutDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/persons")
public class PersonController {
    private final PersonService service;

    @GetMapping
    public ResponseEntity<List<PersonDTO>> list() {
        final var persons = service.list().stream()
                .map(PersonDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(persons);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDTO> getById(@PathVariable Long id) {
        final var person = service.get(id).orElse(null);
        return ResponseEntity.ok(Objects.nonNull(person) ? new PersonDTO(person) : null);
    }

    @PostMapping
    public ResponseEntity<PersonDTO> create(@RequestBody PersonPostDTO dto) {
        final var person = service.create(dto);
        return ResponseEntity.ok(new PersonDTO(person));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonDTO> update(@PathVariable Long id, @RequestBody PersonPutDTO dto) {
        final var person = service.update(id, dto);
        return ResponseEntity.ok(new PersonDTO(person));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
