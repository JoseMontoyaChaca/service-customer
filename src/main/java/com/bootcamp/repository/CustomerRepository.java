package com.bootcamp.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.bootcamp.entity.Customer;

@Repository
public interface CustomerRepository extends ReactiveMongoRepository<Customer, String> {
	

}
