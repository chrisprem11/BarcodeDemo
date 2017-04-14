package com.urs.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.EAN13Writer;

@Controller
public class HomeController {

	private static final String BARCODE_ONE = "barcodeOne";

	@Autowired
	private Environment environment;

	@GetMapping("/")
	public String getHomePage(Model model) throws WriterException, FileNotFoundException, IOException {
		String barcode = environment.getRequiredProperty(BARCODE_ONE);
		int requestedWidth = 500;
		int requestedHeight = 300;
		String imageFormat = "png";
		
		BitMatrix bitMatrix = new EAN13Writer().encode(barcode, BarcodeFormat.EAN_13, requestedWidth, requestedHeight);
		MatrixToImageWriter.writeToStream(bitMatrix, imageFormat,
				new FileOutputStream(new File("E:\\EAN13.png")));
		model.addAttribute("barcode", barcode);
		return "index";
	}

}
