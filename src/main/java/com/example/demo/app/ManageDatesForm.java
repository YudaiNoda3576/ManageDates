package com.example.demo.app;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;


public class ManageDatesForm {
	
	public ManageDatesForm() {		
	}
//	htmlのnameと値が一致していることを確認
	@Size(min = 1, max = 20, message = "１文字以上、２０文字以内で入力してください。")
	@Setter
	@Getter
	private String name;
	@NotNull
	@Setter
	@Getter
	private int year;
	@NotNull
	@Setter
	@Getter
	private int month;
	@NotNull
	@Setter
	@Getter
	private int date;
}
