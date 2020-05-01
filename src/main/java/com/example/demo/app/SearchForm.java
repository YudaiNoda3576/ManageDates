package com.example.demo.app;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class SearchForm {
	
	@NotNull(message = "入力値が空です")
	@Pattern(regexp = "^\\-?[0-9]*\\.?[0-9]+$", message = "数字を入力してください")
	private String input;
}
