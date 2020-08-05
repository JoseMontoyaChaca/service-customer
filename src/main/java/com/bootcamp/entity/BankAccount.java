package com.bootcamp.entity;

import lombok.Data;

@Data
public class BankAccount {

	private Integer id;	
	private String nameAccount;
	private Long  cardNumber;
	private Holder holder;
	private  Signatory signatory;
	private String dniCustomer;
	private double balance;
	private String idBank;
	
}
