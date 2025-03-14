package com.core.customerservice.customer.infrastructure;

import com.core.customerservice.customer.application.CustomerService;
import com.core.customerservice.customer.domain.dtos.*;
import com.core.customerservice.shared.domain.ResponseWrapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService service;

    @GetMapping
    public ResponseEntity<ResponseWrapper<List<CustomerDTO>>> list() {
        List<CustomerDTO> customers = service.list().stream()
                .map(CustomerDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(ResponseWrapper.success(customers));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseWrapper<CustomerDTO>> getById(@PathVariable Long id) {
        return service.get(id)
                .map(customer -> ResponseEntity.ok(ResponseWrapper.of(HttpStatus.OK, "Customer retrieved successfully", new CustomerDTO(customer))))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(ResponseWrapper.error(HttpStatus.NOT_FOUND, "Customer not found")));
    }

    @GetMapping("/{id}/exist")
    public ResponseEntity<ResponseWrapper<CustomerExistResponseDTO>> exist(@PathVariable Long id) {
        return service.get(id)
                .map(customer -> {
                    CustomerExistResponseDTO responseDTO = new CustomerExistResponseDTO(customer);
                    return ResponseEntity.ok(ResponseWrapper.of(HttpStatus.OK, "Customer found", responseDTO));
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(ResponseWrapper.error(HttpStatus.NOT_FOUND, "Customer not found")));
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper<CustomerDTO>> create(@RequestBody CustomerPostDTO dto) {
        CustomerDTO createdCustomer = new CustomerDTO(service.create(dto));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseWrapper.of(HttpStatus.CREATED, "Customer created successfully", createdCustomer));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseWrapper<CustomerDTO>> update(@PathVariable Long id, @RequestBody CustomerPutDTO dto) {
        CustomerDTO updatedCustomer = new CustomerDTO(service.update(id, dto));
        return ResponseEntity.ok(ResponseWrapper.of(HttpStatus.OK, "Customer updated successfully", updatedCustomer));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ResponseWrapper<CustomerDTO>> edit(@PathVariable Long id, @RequestBody CustomerPatchDTO dto) {
        CustomerDTO editedCustomer = new CustomerDTO(service.edit(id, dto));
        return ResponseEntity.ok(ResponseWrapper.of(HttpStatus.OK, "Customer edited successfully", editedCustomer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseWrapper<Void>> deletePerson(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(ResponseWrapper.of(HttpStatus.OK, "Customer deleted successfully", null));
    }
}