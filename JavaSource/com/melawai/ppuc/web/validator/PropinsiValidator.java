package com.melawai.ppuc.web.validator;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.melawai.ppuc.model.Propinsi;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: $date.long 
 * @Description	: Validator for table Propinsi
 * @Revision	:
 */
@Component
public class PropinsiValidator implements Validator {
	
	private static Logger logger = Logger.getLogger(PropinsiValidator.class);

	@Override
	public boolean supports(Class cls) {
		return Propinsi.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors e) {
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "propinsi",  "NotEmpty", new String[]{"Propinsi"},null);
	}

}

