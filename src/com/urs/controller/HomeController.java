package com.urs.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.EAN13Writer;

/**
 * This is the HomeController Class. This class handles all the request
 * mappings.
 * 
 * @author Premnath Christopher
 *
 */
@Controller
public class HomeController {

	private static final String FILE_PATH = "file.path";

	private static final String BARCODE_PER_PAGE = "numberOfBarcodePerPage";

	private static final String REPEAT = "mirror";

	@Autowired
	private Environment environment;

	/**
	 * Get all the bar code numbers from properties file and store it in a list
	 */
	@Value("#{'${barcode.list}'.split(',')}")
	private List<String> allBarcodes;

	/**
	 * This method is used to display all the barcode as per the configuration
	 * set in the properties file. This method also returns the home page.
	 * 
	 * @param model
	 * @param request
	 * @return index
	 * @throws WriterException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@GetMapping("/")
	public String getHomePage(Model model, HttpServletRequest request)
			throws WriterException, FileNotFoundException, IOException {

		String mirrorBarcode = environment.getProperty(REPEAT);
		String barcodePerPage = environment.getProperty(BARCODE_PER_PAGE);
		Map<String, String> barcodeImageSource = new HashMap<String, String>();
		String imageFilePath = "";

		for (String barcode : allBarcodes) {
			int requestedWidth = 200;
			int requestedHeight = 100;
			String imageFormat = "jpeg";
			// random digit is used as a suffix to the name of the generated
			// image.
			int randomDigit = (int) (Math.random() * 50 + 1);
			String barcodeName = "barcode";

			String directory = environment.getProperty(FILE_PATH);
			BitMatrix bitMatrix = new EAN13Writer().encode(barcode, BarcodeFormat.EAN_13, requestedWidth,
					requestedHeight);
			MatrixToImageWriter.writeToStream(bitMatrix, imageFormat,
					new FileOutputStream(new File(directory + barcodeName + randomDigit + ".jpg")));
			imageFilePath = directory + barcodeName + randomDigit + ".jpg";
			barcodeImageSource.put(barcode, imageFilePath);

		}
		System.out.println(barcodeImageSource);
		model.addAttribute("imageSource", barcodeImageSource);
		model.addAttribute("barcodes", allBarcodes);
		model.addAttribute("isImageMirrored", mirrorBarcode);
		model.addAttribute("barcodePerPage", barcodePerPage);
		return "index";
	}

}
