package com.bootcamp.controller.test;


import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.bootcamp.controller.CustomerController;
import com.bootcamp.repository.CustomerRepository;
import com.bootcamp.service.CustomerService;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = CustomerController.class)
@Import(CustomerService.class)
public class CustomerControllerTest {

	@MockBean
	CustomerRepository repo;

	
	@Autowired
	WebTestClient webTestClient;
	
	
	
	
	
}
