package com.olisaude.challenge.olisaudeapi.controller;

import com.olisaude.challenge.olisaudeapi.dto.CustomerRequest;
import com.olisaude.challenge.olisaudeapi.dto.CustomerResponse;
import com.olisaude.challenge.olisaudeapi.dto.CustomerRiskier;
import com.olisaude.challenge.olisaudeapi.model.Customer;
import com.olisaude.challenge.olisaudeapi.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/customer", produces = {"application/json"})
@Tag(name = "customer")
public class CustomerController {

    @Autowired
    private CustomerService cs;

    @Operation(summary = "Cria um novo cliente", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cadastro de cliente realizado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao cadastrar cliente")
    })
    @Transactional
    @PostMapping
    public ResponseEntity<CustomerResponse> create (@RequestBody CustomerRequest request) throws Exception {
        Customer customer = cs.createCustomer(request);
        CustomerResponse response = new CustomerResponse(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Lists all customers", method = "GET")
    @GetMapping
    public ResponseEntity<List<CustomerResponse>> listAll() {
        List<CustomerResponse> customerResponse = cs.listAll()
                .stream()
                .map(customer -> new CustomerResponse(customer))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(customerResponse);
    }

    @Operation(summary = "Lists a customer by ID", method = "GET")
    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> listById(@PathVariable Long id ){
        Customer customer = cs.listById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new CustomerResponse(customer));
    }

    @Operation(summary = "Lists a customer by name", method = "GET")
    @GetMapping("/findByName")
    public ResponseEntity<List<CustomerResponse>> listByName(@RequestParam String name){
        List<CustomerResponse> customerResponse = cs.findByName(name.trim().toUpperCase())
                .stream()
                .map(customer -> new CustomerResponse(customer))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(customerResponse);
    }

    @Operation(summary = "Lists 10 more riskier customers", method = "GET")
    @GetMapping("/riskier")
    public ResponseEntity<List<CustomerRiskier>> listRiskier() {
        List<CustomerRiskier> response = cs.listRiskier()
                .stream()
                .map(customer -> new CustomerRiskier(customer))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Delete a customer by ID", method = "DELETE")
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteCustomer(@PathVariable Long id){
        cs.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Update customer by ID", method = "PUT")
    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> updateCustomer(@PathVariable Long id, @RequestBody CustomerRequest request) {
        Customer customer = cs.updateCustomer(id, request);
        return ResponseEntity.ok(new CustomerResponse(customer));
    }

}

