package com.melawai.ppuc.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.melawai.ppuc.model.Kota;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: $date.long 
 * @Description	: Validator for table Kota
 * @Revision	:
 */
public class KotaValidator implements Validator {

	@Override
	public boolean supports(Class cls) {
		return Kota.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors e) {
		// TODO Auto-generated method stub
	}

}

