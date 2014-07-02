package com.melawai.ppuc.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.melawai.ppuc.model.GroupBiaya;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: $date.long 
 * @Description	: Validator for table GroupBiaya
 * @Revision	:
 */
public class GroupBiayaValidator implements Validator {

	@Override
	public boolean supports(Class cls) {
		return GroupBiaya.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors e) {
		// TODO Auto-generated method stub
	}

}

