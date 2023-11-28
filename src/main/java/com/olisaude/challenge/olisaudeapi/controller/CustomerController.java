package com.olisaude.challenge.olisaudeapi.controller;

import com.olisaude.challenge.olisaudeapi.dto.CustomerRequest;
import com.olisaude.challenge.olisaudeapi.dto.CustomerResponse;
import com.olisaude.challenge.olisaudeapi.dto.CustomerRiskier;
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
        List<CustomerResponse> customerResponse = cs.listAll()
                .stream()
                .map(customer -> new CustomerResponse(customer))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(customerResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> listById(@PathVariable Long id){
        Customer customer = cs.listById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new CustomerResponse(customer));
    }

    @GetMapping("/riskier")
    public ResponseEntity<List<CustomerRiskier>> listRiskier() {
        List<CustomerRiskier> response = cs.listRiskier()
                .stream()
                .map(customer -> new CustomerRiskier(customer))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteCustomer(@PathVariable Long id){
        cs.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> updateCustomer(@PathVariable Long id, @RequestBody CustomerRequest request) {
        Customer customer = cs.updateCustomer(id, request);
        return ResponseEntity.ok(new CustomerResponse(customer));
    }

}

