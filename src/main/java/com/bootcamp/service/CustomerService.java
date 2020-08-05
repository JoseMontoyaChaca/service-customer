package com.bootcamp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.bootcamp.entity.Customer;
import com.bootcamp.repository.CustomerRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerService {
		
	@Autowired
	private CustomerRepository reposiory;
	
	public <T>Mono<T>monoResponseStatusFoundExeption(){
		return Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,"Customer no Found"));
	}
		
	public Mono<Customer> save(final Customer customer) {
		return reposiory.save(customer);
	}
	
	public Mono<Void>  delete( String dniCustomer ){
		return findByDNICustomer(dniCustomer)
				.flatMap(reposiory::delete);
	}
	
	
	public Mono<Void> updateCustomer (Customer customer) {
		return findByDNICustomer(customer.getDniCustomer())
				.flatMap(reposiory::save)
				.thenEmpty(Mono.empty());
	}
	
	public Flux<Customer> getAllCustomer() {
		return reposiory.findAll().switchIfEmpty(Flux.empty());
	}
	
	
	public Mono<Customer> findByDNICustomer (String dniCustomer) {
		return reposiory.findByDNICustomer(dniCustomer);
	}
	
	public Mono<Customer> findById (String id) {
		return reposiory.findById(id);
	}
	
	

		
}
