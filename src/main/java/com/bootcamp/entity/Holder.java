package com.bootcamp.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Holder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("dniHolder")
	private String dniHolder;
	@JsonProperty("nameHolder")
	private String nameHolder;
	
}
