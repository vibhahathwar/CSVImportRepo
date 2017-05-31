package com.csvimport.validator;



import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


import com.csvimport.model.FileUpload;

@Component
public class FileValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return FileUpload.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		System.out.println(target +"---target details");
		FileUpload fileupload = (FileUpload) target;
		CommonsMultipartFile[] multipartFiles = fileupload.getFiles();
		System.out.println("files deatils.... "+multipartFiles);
		for(CommonsMultipartFile multipartFile:multipartFiles){
			if(multipartFile.getSize() == 0){
				errors.rejectValue("files", "required.files");
				break;
			}
			
			
		String fileExtension = CommonUtils.checkExtension(multipartFile.getOriginalFilename().toLowerCase());
		if(!fileExtension.equals("csv")){
			errors.rejectValue("file", "file.extension.allowed");
			break;
		}
	}
		
	}

	


}
