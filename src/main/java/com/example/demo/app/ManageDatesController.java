package com.example.demo.app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ManageDates")
public class ManageDatesController {
	
	@GetMapping("/form")
	public String form(ManageDatesForm manageDatesForm, Model model) {
		model.addAttribute("title", "Edit Form");
		return "ManageDates/edit";
	}
//	戻るボタンを受け取る
	@PostMapping("/form")
	public String formGoBack(ManageDatesForm manageDatesForm, Model model) {
		model.addAttribute("title", "Edit Form");
		return "ManageDates/edit";
	}
	
	@PostMapping("/confirm")
	public String confirm(@Validated ManageDatesForm manageDatesForm,
			BindingResult result,
			Model model) {
		if(result.hasErrors()) {
			model.addAttribute("title", "ManageDates Form");
			return "ManageDates/edit";
	}
	model.addAttribute("title", "Confirm Page");
	return "ManageDates/confirm";
  }
}
