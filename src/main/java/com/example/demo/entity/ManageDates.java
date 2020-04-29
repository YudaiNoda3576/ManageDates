package com.example.demo.entity;


import lombok.Data;

@Data
public class ManageDates {
	
	public ManageDates() {}
	
	
	private String id;
	private String name;
	private int year ;
	private int month ;
	private int date ;
	
}
