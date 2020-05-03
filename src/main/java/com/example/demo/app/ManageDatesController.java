package com.example.demo.app;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.ManageDates;
import com.example.demo.service.ManageDatesService;

@Controller
@RequestMapping("/")
public class ManageDatesController {
	
//	private final ManageDatesService manageDatesService;
//	
//	@Autowired
//	public ManageDatesController(ManageDatesService manageDatesService) {
//		this.manageDatesService = manageDatesService;
//	}
//	上の処理を1行でまとめた↓
	@Autowired
	ManageDatesService manageDatesService;

	
	@GetMapping("/index")
	public String index(Model model, @ModelAttribute("input") String input, SearchForm searchForm) {
		model.addAttribute("searchForm", searchForm);
		return "index";
	}
	
	@PostMapping
	public String search(@RequestParam("input") String input,
			@Validated SearchForm searchForm,
			BindingResult result,
			Model model) {
//		計算結果
		if(!result.hasErrors()) {
			List<LocalDate>manageDatesCalResult = manageDatesService.search(input);
			model.addAttribute("manageDatesCal", manageDatesService.findAll());
			model.addAttribute("manageDatesCalResult", manageDatesCalResult);
			model.addAttribute("val", input);
		} else {
			model.addAttribute("failed", "入力値に誤りがあります");
			model.addAttribute("val", input);
		}
		return "index";
	}
	
//	新規登録
	@GetMapping("/create")
	public String create(ManageDatesForm manageDatesForm, Model model) {
		model.addAttribute("manageDatesForm", manageDatesForm);
		return"/create";
	}
	
	@PostMapping("/create")
	public String create(@ModelAttribute @Validated ManageDatesForm manageDatesForm, 
			BindingResult result, 
			Model model, 
			RedirectAttributes redirectAttributes) {
//		Formの値をEntityへ詰め直す
		ManageDates manageDates = new ManageDates();
		
		manageDates.setId(manageDatesForm.getId());
		manageDates.setName(manageDatesForm.getName());
		manageDates.setYear(manageDatesForm.getYear());
		manageDates.setMonth(manageDatesForm.getMonth());
		manageDates.setDate(manageDatesForm.getDate());
	
		if(!result.hasErrors()) {
			manageDatesService.insert(manageDates);
			redirectAttributes.addFlashAttribute("success", "新規登録が完了しました");
			return "redirect:/index";
		} else {
			model.addAttribute("manageDatesCal", manageDatesService.findAll());
			model.addAttribute("manageDatesForm", manageDatesForm);
			model.addAttribute("failed", "不正値に誤りがあります");
			return "create";
		}
		
	}
	
	@GetMapping("/edit/{id}")
	public String getId(@PathVariable String id, 
		    Model model,
			RedirectAttributes redirectAttributes) {
		ManageDates result = manageDatesService.findOne(id);
		model.addAttribute("manageDates", result);
		return "edit";
	}
		

	@PostMapping("/edit/{id}")
	public String update(@PathVariable String id, @ModelAttribute ManageDatesForm manageDatesForm,
			Model model, RedirectAttributes redirectAttributes) {
			
		ManageDates manageDates = new ManageDates();
		
	 	manageDates.setId(manageDatesForm.getId());
	 	manageDates.setName(manageDatesForm.getName());
	 	manageDates.setYear(manageDatesForm.getYear());
	 	manageDates.setMonth(manageDatesForm.getMonth());
	 	manageDates.setDate(manageDatesForm.getDate());
	 	
	 	manageDates.setId(id);
	 	
	 	manageDatesService.update(manageDates);
 
		redirectAttributes.addFlashAttribute("success", "更新が完了しました");
		
		return "redirect:/";
	}
	
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable String id, Model model) {
		manageDatesService.delete(id);
		model.addAttribute("success", "削除が成功しました");
		return "redirect:/index";
	}
	
}
