package com.melawai.ppuc.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.melawai.ppuc.model.Divisi;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: $date.long 
 * @Description	: Validator for table Divisi
 * @Revision	:
 */
@Component
public class DivisiValidator implements Validator {

	@Override
	public boolean supports(Class cls) {
		return Divisi.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors e) {
		// TODO Auto-generated method stub
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "divisi_kd",  "NotEmpty", new String[]{"Divisi KD"},null);
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "divisi_nm",  "NotEmpty", new String[]{"Divisi NM"},null);
	}

}

