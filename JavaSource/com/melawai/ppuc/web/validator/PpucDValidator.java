package com.melawai.ppuc.web.validator;

import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.melawai.ppuc.model.PpucD;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: $date.long 
 * @Description	: Validator for table PpucD
 * @Revision	:
 */
public class PpucDValidator extends LocalValidatorFactoryBean implements Validator {
	
	private static Logger logger = Logger.getLogger(PpucDValidator.class);

	@Override
	public boolean supports(Class cls) {
		return PpucD.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors e) {
		// TODO Auto-generated method stub
	}

}

