package com.melawai.ppuc.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.melawai.ppuc.model.AksesMenu;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: $date.long 
 * @Description	: Validator for table AksesMenu
 * @Revision	:
 */
public class AksesMenuValidator implements Validator {

	@Override
	public boolean supports(Class cls) {
		return AksesMenu.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors e) {
		// TODO Auto-generated method stub
	}

}

