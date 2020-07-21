package com.bootcamp.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import com.bootcamp.entity.BankAccount;
import com.bootcamp.entity.Customer;
import com.bootcamp.service.CustomerService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {
	
	private WebClient client = WebClient.create("htpp://localhost:8087/bankAccount");
	
	@Autowired
	private CustomerService customerService; 
	
	
	@GetMapping("/getBankAccount")
	public Flux<BankAccount>getCustomer(){
		return  client.get().uri("/allBA").accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToFlux(BankAccount.class)
				.log("RETRIVE Bank ACCOUNT:: ");

	}
	
	
	@GetMapping("/{dniCustomer}")
	public Mono<BankAccount>getBankAccount(@PathVariable String dniCustomer){
		return  client.get().uri("/getDNI/{dniCustomer}",dniCustomer).accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(BankAccount.class)
				.log("Customer with Bank Account:: ");
	}
	
	
	
	
	

	@PostMapping("/addCustomer")
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Customer> saveCustomer(@RequestBody Customer customer) {
		return customerService.save(customer);	
	}
	
	
	@PutMapping("/updateCustomer")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Mono<Void> updateCustomer(@RequestBody Customer cus){	
		return customerService.update(cus);
	}
	
	@GetMapping("/getCustomer/{id}")
	public Mono<Customer> getCustomerById(@PathVariable("id") String id){
		return customerService.findById(id);		
	}
	
	@GetMapping("/allCustomer")
	public Flux<Customer> getCustomerAll(){
		return customerService.getAll();
	}

	@DeleteMapping(value = "/delete/{id}")
	public Mono<Customer> delete (@PathVariable("id") String id){
		return customerService.delete(id);
	}
	
	



}
