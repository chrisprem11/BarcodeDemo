package com.urs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	private static final String BARCODE_ONE = "barcodeOne";

	@Autowired
	private Environment environment;

	@GetMapping("/")
	public String getHomePage(Model model) {
		String barcode= environment.getRequiredProperty(BARCODE_ONE);
		model.addAttribute("barcode", barcode);
		return "index";
	}

}
