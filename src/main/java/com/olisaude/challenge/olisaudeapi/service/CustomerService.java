package com.olisaude.challenge.olisaudeapi.service;

import com.olisaude.challenge.olisaudeapi.dto.CustomerRequest;
import com.olisaude.challenge.olisaudeapi.model.Customer;
import com.olisaude.challenge.olisaudeapi.model.HealthProblem;
import com.olisaude.challenge.olisaudeapi.repository.CustomerRepository;
import com.olisaude.challenge.olisaudeapi.repository.HealthProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository repository;
    @Autowired
    private HealthProblemRepository healthProblemRepository;
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
        List<HealthProblem> healthProblems =
                healthProblemRepository.findByNameInAndActiveTrue(
                        request.healthProblem()
                                .stream()
                                .map(HealthProblem::getName)
                                .collect(Collectors.toList())
                );

        customer.update(request, healthProblems);

        return customer;
    }

}
