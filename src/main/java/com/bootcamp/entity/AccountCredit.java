package com.bootcamp.entity;

import java.io.Serializable;

import lombok.Data;
@Data
public class AccountCredit implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer id;	
	private String nameAccount;
	private Long  cardNumber;
	private String dniCustomer;
	private double balance;
	private String idBank;

}
