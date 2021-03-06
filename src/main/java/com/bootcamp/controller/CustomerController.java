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
import com.bootcamp.entity.AccountCredit;
import com.bootcamp.entity.Customer;
import com.bootcamp.service.CustomerService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiResponse;
//import io.swagger.annotations.ApiResponses;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//@Api(value = "bank", produces = "application/json")
@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	private WebClient bankAccount = WebClient.create("htpp://localhost:8072/bankAccount");
	private WebClient credit	  = WebClient.create("htpp://localhost:8074/credit");

	@Autowired
	private CustomerService customerService; 
	
	//Report of the products according to the client
	@GetMapping("/reportProducts/{dniCustomer}")
	public Flux<Object> getAllBankAccount(@PathVariable String dniCustomer){
		Flux<BankAccount> bankList =  bankAccount.get().uri("/getDNI/{dniCustomer}",dniCustomer)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToFlux(BankAccount.class)
				.log("Customer with Bank Account:: ");
		
		Flux<AccountCredit> creditList = 	credit.get().uri("/getDNI/{dniCustomer}",dniCustomer)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToFlux(AccountCredit.class)
				.log("Customer with Credit:: ");

		
		Flux<Object> concat = Flux.concat(bankList,creditList);		
		return concat;	
		
	}
	
	//CRUD
	@PostMapping("/addCustomer")
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Customer> saveCustomer		(@RequestBody Customer customer) {
		return customerService.save(customer);	
	}
	
	@PutMapping("/updateCustomer")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Mono<Void> updateCustomer		(@RequestBody Customer cus){	
		return customerService.updateCustomer(cus);
	}
	
	@DeleteMapping("/delete/{dniCustomer}")
	public Mono<Void>  delete				(@PathVariable String dniCustomer) {
		return customerService.delete(dniCustomer);
	}
	
	@GetMapping(path = "/{dniCustomer}")
	public Mono<Customer> findByDNICustomer (@PathVariable String dniCustomer){
		return customerService.findByDNICustomer(dniCustomer);

	}
	
	@GetMapping("/{id}")
	public Mono<Customer> findById (@PathVariable String id){
		return customerService.findById(id);

	}
	
	@GetMapping("/allCustomer")
	public Flux<Customer> getCustomerAll(){
		return customerService.getAllCustomer();
	}
	
}
