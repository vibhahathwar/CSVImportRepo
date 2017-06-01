package com.csvimport.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.csvimport.model.FileUpload;
import com.csvimport.service.DealService;
import com.csvimport.validator.FileValidator;

@Controller
public class FileuploadController {

	private static final Logger logger = Logger
			.getLogger(FileuploadController.class);

	@Autowired
	FileValidator fileValidator;

	@Autowired
	DealService dealService;

	@RequestMapping(value = "/uploadpage", method = RequestMethod.GET)
	public ModelAndView uploadPage() {
		ModelAndView model = new ModelAndView("upload_page");
		FileUpload formupload = new FileUpload();
		logger.info("redirect the page");
		model.addObject("formupload", formupload);
		return model;
	}

	@RequestMapping(value = "/doUpload", method = RequestMethod.POST)
	public String doUpload(@ModelAttribute("formupload") FileUpload fileupload,
			BindingResult result, RedirectAttributes redirect)
			throws IOException {

		logger.info("file validation .");
		fileValidator.validate(fileupload, result);
		if (result.hasErrors()) {
			return "upload_page";

		} else {

			redirect.addFlashAttribute("fileNames",
					uploadAndImportDb(fileupload));
			return "redirect:/Success";
		}

	}

	@RequestMapping(value = "/Success", method = RequestMethod.GET)
	public ModelAndView Success() {
		ModelAndView model = new ModelAndView("success");
		return model;
	}

	private List<String> uploadAndImportDb(FileUpload fileUpload)
			throws IOException {
		logger.info("upload data into database.");
		List<String> fileNames = new ArrayList<String>();
		List<String> path = new ArrayList<String>();

		CommonsMultipartFile[] commonsMultipartFiles = fileUpload.getFiles();
		String fielpath = null;
		for (CommonsMultipartFile multipartFile : commonsMultipartFiles) {
			fielpath = "F:\\test\\" + multipartFile.getOriginalFilename();
			File file = new File(fielpath);
			FileCopyUtils.copy(multipartFile.getBytes(), file);
			fileNames.add(multipartFile.getOriginalFilename());
			path.add(fielpath);
		}

		dealService.process(path);
		return fileNames;
	}
}
