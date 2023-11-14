package com.olisaude.challenge.olisaudeapi.controller;

import com.olisaude.challenge.olisaudeapi.dto.CustomerRequest;
import com.olisaude.challenge.olisaudeapi.dto.CustomerResponse;
import com.olisaude.challenge.olisaudeapi.model.Customer;
import com.olisaude.challenge.olisaudeapi.service.CustomerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService cs;

    @Transactional
    @PostMapping
    public ResponseEntity<CustomerResponse> create (@RequestBody CustomerRequest request) throws Exception {
        Customer customer = cs.createCustomer(request);
        CustomerResponse response = new CustomerResponse(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> listAll() {
        List<CustomerResponse> customerRespons = cs.listAll()
                .stream()
                .map(customer -> new CustomerResponse(customer))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(customerRespons);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteCustomer(@PathVariable Long id){
        cs.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody CustomerRequest request) {
        Customer customer = cs.updateCustomer(id, request);
        return ResponseEntity.ok(customer);
    }

}

