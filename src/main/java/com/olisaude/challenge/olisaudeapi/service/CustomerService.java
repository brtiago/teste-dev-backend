package com.olisaude.challenge.olisaudeapi.service;

import com.olisaude.challenge.olisaudeapi.dto.CustomerRequest;
import com.olisaude.challenge.olisaudeapi.dto.CustomerResponse;
import com.olisaude.challenge.olisaudeapi.model.Customer;
import com.olisaude.challenge.olisaudeapi.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository repository;
    public Customer createCustomer(@RequestBody CustomerRequest request) {
        Customer customer = new Customer(request);
        System.out.println(customer);
        return repository.save(customer);
    }

    public List<Customer> listAll(){
        return repository.findAll();
    }

    public List<Customer> listRiskier() { return repository.findTop10ByOrderByHealthScoreDesc(); }

    public void delete(Long id) {
        var customer = repository.getReferenceById(id);
        customer.delete();
    }

    public Customer updateCustomer(Long id, CustomerRequest request){
        var customer = repository.getReferenceById(id);
        customer.update(request);

        return customer;
    }

}
