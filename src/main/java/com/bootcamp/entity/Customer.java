package com.bootcamp.entity;

import java.io.Serializable;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Customer implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String dniCustomer;
	@NotNull
	private String firstName;
	@NotNull
	private String lastName;
	@NotNull
	private Integer key;
	@NotNull
	private boolean status;

	

}