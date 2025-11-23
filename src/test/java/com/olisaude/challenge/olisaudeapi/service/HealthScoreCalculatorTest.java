package com.olisaude.challenge.olisaudeapi.service;

import com.olisaude.challenge.olisaudeapi.dto.CustomerRequest;
import com.olisaude.challenge.olisaudeapi.dto.HealthProblemRequest;
import com.olisaude.challenge.olisaudeapi.model.Customer;
import com.olisaude.challenge.olisaudeapi.model.CustomerGender;
import com.olisaude.challenge.olisaudeapi.model.HealthProblem;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;
import java.util.List;

import static com.olisaude.challenge.olisaudeapi.model.CustomerGender.FEMALE;
import static org.junit.jupiter.api.Assertions.*;


class HealthScoreCalculatorTest {

    @Test
    void testCreateCustomer() throws Exception {

        // Given
        Customer customer = new Customer(new CustomerRequest(
                "Noname User",
                LocalDate.parse("1997-01-12"),
                FEMALE,
                List.of(new HealthProblem(new HealthProblemRequest(
                        null,
                        "Depression",
                        null
                )))
        ));

        assertEquals("Noname User", customer.getName());
        assertEquals(LocalDate.parse("1997-01-12"), customer.getBirthDate());
        assertEquals(FEMALE, customer.getGender());
        assertEquals(1, customer.getHealthProblems().size());

    }

    @Test
    void testCreateCustomerNoHealthProblems() throws Exception {

        // Given
        Customer customer = new Customer(new CustomerRequest(
                "Noname User",
                LocalDate.parse("1997-01-12"),
                FEMALE,
                List.of(new HealthProblem(new HealthProblemRequest(
                        null,
                        null,
                        null
                )))
        ));

        assertEquals("Noname User", customer.getName());
        assertEquals(LocalDate.parse("1997-01-12"), customer.getBirthDate());
        assertEquals(FEMALE, customer.getGender());
        assertEquals(1, customer.getHealthProblems().size());

    }
}