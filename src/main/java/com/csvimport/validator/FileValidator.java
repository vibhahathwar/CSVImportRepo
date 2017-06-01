package com.csvimport.validator;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.csvimport.controller.FileuploadController;
import com.csvimport.model.FileUpload;



@Component
public class FileValidator implements Validator{
	private static final Logger logger = Logger.getLogger(FileValidator.class);
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return FileUpload.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		FileUpload fileupload = (FileUpload) target;
		CommonsMultipartFile[] multipartFiles = fileupload.getFiles();
		for(CommonsMultipartFile multipartFile:multipartFiles){
			if(multipartFile.getSize() == 0){
				logger.error("file size is zero");
				errors.rejectValue("files", "required.files");
				break;
			}
					
		String fileExtension = CommonUtils.checkExtension(multipartFile.getOriginalFilename().toLowerCase());
		if(!fileExtension.equals("csv")){
			logger.error("File extension is not proper");			
			errors.rejectValue("files", "file.extension.allowed");
			break;
		}
	}
		
	}

	


}
