package com.core.customerservice.person.application;

import com.core.customerservice.person.domain.Person;
import com.core.customerservice.person.domain.dtos.PersonPatchDTO;
import com.core.customerservice.person.domain.dtos.PersonPostDTO;
import com.core.customerservice.person.domain.dtos.PersonPutDTO;
import com.core.customerservice.person.domain.exceptions.PersonAlreadyExistsException;
import com.core.customerservice.person.domain.exceptions.PersonNotFoundException;
import com.core.customerservice.person.infrastructure.PersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private final PersonRepository repository;

    public PersonService(PersonRepository personRepository) {
        this.repository = personRepository;
    }

    public List<Person> list() {
        return repository.findAll();
    }

    public Optional<Person> get(Long id) {
        return repository.findById(id);
    }

    @Transactional(rollbackFor = Throwable.class)
    public Person create(PersonPostDTO dto) {
        repository.findByIdentification(dto.getIdentification())
                .ifPresent(p -> {
                    throw new PersonAlreadyExistsException(dto.getIdentification());
                });
        Person person = Person.builder()
                .name(dto.getName())
                .gender(dto.getGender())
                .dateOfBirth(dto.getDateOfBirth())
                .identification(dto.getIdentification())
                .address(dto.getAddress())
                .phone(dto.getPhone())
                .build();
        return repository.save(person);
    }

    @Transactional(rollbackFor = Throwable.class)
    public Person update(Long id, PersonPutDTO dto) {
        return repository.findById(id)
                .map(person -> {
                    person.update(dto);
                    return repository.save(person);
                })
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    @Transactional(rollbackFor = Throwable.class)
    public Person edit(Long id, PersonPatchDTO dto) {
        return repository.findById(id)
                .map(person -> {
                    person.edit(dto);
                    return repository.save(person);
                })
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new PersonNotFoundException(id);
        }
        repository.deleteById(id);
    }

}
