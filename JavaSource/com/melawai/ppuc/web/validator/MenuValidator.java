package com.melawai.ppuc.web.validator;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.melawai.ppuc.model.Menu;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: $date.long 
 * @Description	: Validator for table Menu
 * @Revision	:
 */
@Component
public class MenuValidator  implements Validator {
	
	private static Logger logger = Logger.getLogger(MenuValidator.class);

	@Override
	public boolean supports(Class cls) {
		return Menu.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors e) {
		// TODO Auto-generated method stub
	}

}

