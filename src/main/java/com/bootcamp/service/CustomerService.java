package com.bootcamp.service;

import java.util.Objects;
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
	
	public Flux<Customer> getAll() {
		return reposiory.findAll().switchIfEmpty(Flux.empty());
	}
	
	public Mono<Customer> findById(final String id) {
		return reposiory.findById(id);
	}
	
	
	public Mono<Void> update(Customer customer) {
		return findById(customer.getDniCustomer())
				.flatMap(reposiory::save)
				.thenEmpty(Mono.empty());
	}
	public Mono<Customer> save(final Customer customer) {
		return reposiory.save(customer);
	}
	public Mono<Customer> delete(final String id) {
		final Mono<Customer> dbCustomer = findById(id);
		if (Objects.isNull(dbCustomer)) {
			return Mono.empty();
		}
		return findById(id).switchIfEmpty(Mono.empty()).filter(Objects::nonNull).flatMap(customerToBeDeleted -> reposiory
				.delete(customerToBeDeleted).then(Mono.just(customerToBeDeleted)));
	}

	
}
