package com.bootcamp.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import com.bootcamp.entity.Customer;
import reactor.core.publisher.Mono;


@Repository
public interface CustomerRepository extends ReactiveMongoRepository<Customer, String> {
	

	@Query(value =  "{dniCustomer:?0}" )
	Mono<Customer> findByDNICustomer (String dniCustomer);

	
}
