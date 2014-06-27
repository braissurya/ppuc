package com.melawai.ppuc.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.melawai.ppuc.model.Upload;

/**
 * Validator Untuk Input Group Policy (gabungan life dan fire)
 * 
 * @author Yusuf
 * @since Feb 4, 2013 (11:29:07 AM)
 * 
 */
@Component
public class UploadValidator implements Validator {

	public boolean supports(Class clazz) {
		return Upload.class.equals(clazz);
	}

	public void validate(Object obj, Errors e) {
		// Validasi dilakukan pada nested objects
		Upload upload = (Upload) obj;

		if (upload.isRequired() && upload.uploadFile.getSize() == 0) {
			e.rejectValue("uploadFile", null, "Tidak boleh kosong");
		} else if (upload.uploadFile.getSize() > upload.maxSize) {
			e.rejectValue("uploadFile", "", "Maksimal upload "+upload.maxSize/1000+" kb");
		}

	}

}