package com.melawai.ppuc.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.melawai.ppuc.model.GroupLokasiH;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: $date.long 
 * @Description	: Validator for table GroupLokasiH
 * @Revision	:
 */
public class GroupLokasiHValidator implements Validator {

	@Override
	public boolean supports(Class cls) {
		return GroupLokasiH.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors e) {
		// TODO Auto-generated method stub
	}

}

