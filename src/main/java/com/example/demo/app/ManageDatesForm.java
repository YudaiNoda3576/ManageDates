package com.example.demo.app;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class ManageDatesForm {
	
	public ManageDatesForm() {}

	@NotNull(message="必須項目です")
	@Size(min = 1, max =10, message = "1文字以上10文字以内で入力してください")
	private String id;
	
	@NotNull(message="必須項目です")
	@Size(min = 1, max =10, message = "1文字以上10文字以内で入力してください")
	private String name;
	
	@NotNull(message="必須項目です")
	@Digits(integer = 4, fraction = 4, message = "4桁以内で入力してください")
	private int year = 0;
	
	@NotNull(message="必須項目です")
	@Digits(integer = 2, fraction = 2, message = "2桁以内で入力してください")
	private int month = 0;
	
	@NotNull(message="必須項目です")
	@Digits(integer = 2, fraction = 2, message = "2桁以内で入力してください")
	private int date = 0;
}
