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
	
	public Flux<Customer> getAllCustomer() {
		return reposiory.findAll().switchIfEmpty(Flux.empty());
	}
	
	
	public Mono<Customer> findByDNICustomer (String dniCustomer) {
		Flux<Customer> flux = reposiory.findAll().filter(p -> p.getDniCustomer().equals(dniCustomer));
		return Mono.from(flux);
	}
	
	
	public Mono<Void> updateCustomer (Customer customer) {
		return findByDNICustomer(customer.getDniCustomer())
				.flatMap(reposiory::save)
				.thenEmpty(Mono.empty());
	}
	
	public Mono<Customer> save(final Customer customer) {
		return reposiory.save(customer);
	}
	
	public Mono<Object>  delete( final String id ){
		Mono<Customer> mono = findByDNICustomer(id);
		if(Objects.isNull(mono))  return Mono.empty();
		return findByDNICustomer(id).switchIfEmpty(Mono.empty()).filter(Objects::nonNull).flatMap(custobedelete -> reposiory.delete(custobedelete).then(Mono.just(custobedelete)));
	}
	
		
}
