package com.melawai.ppuc.web.validator;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.melawai.ppuc.model.PpucH;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: $date.long 
 * @Description	: Validator for table PpucH
 * @Revision	:
 */
@Component
public class PpucHValidator extends LocalValidatorFactoryBean implements Validator {
	
	private static Logger logger = Logger.getLogger(PpucHValidator.class);

	@Override
	public boolean supports(Class cls) {
		return PpucH.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors e) {
		if(!e.hasErrors()){
			
		}
	}

}

