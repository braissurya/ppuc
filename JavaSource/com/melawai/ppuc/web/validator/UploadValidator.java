package com.melawai.ppuc.web.validator;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.melawai.ppuc.model.Upload;
import com.melawai.ppuc.utils.Utils;

/**
 * Validator Untuk Input Group Policy (gabungan life dan fire)
 * 
 * @author Yusuf
 * @since Feb 4, 2013 (11:29:07 AM)
 * 
 */
@Component
public class UploadValidator extends LocalValidatorFactoryBean implements Validator {
	
	private static Logger logger = Logger.getLogger(UploadValidator.class);

	public boolean supports(Class clazz) {
		return Upload.class.equals(clazz);
	}

	public void validate(Object obj, Errors e) {
		// Validasi dilakukan pada nested objects
		Upload upload = (Upload) obj;

		if (upload.isRequired() && upload.uploadFile.getSize() == 0) {
			e.rejectValue("uploadFile", "NotEmpty", new String[]{"File Upload"},null);
		} else if (upload.uploadFile.getSize() > upload.getMaxSize()) {
			e.rejectValue("uploadFile", "upload_max",new String[]{""+upload.maxSize/1000+" kb"},null);
		} else if (upload.uploadFile.getSize() != 0&&!Utils.isEmpty(upload.filetypeAllow)){
			if(!upload.getFiletypeAllow().contains("*."+upload.getFileExt())){
				e.rejectValue("uploadFile", "upload_format_permitted",new String[]{""+upload.filetypeAllow},null);
			}
		}

	}

}
