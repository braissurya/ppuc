package com.melawai.ppuc.web.validator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.melawai.ppuc.model.User;
import com.melawai.ppuc.services.UserManager;
import com.melawai.ppuc.utils.Utils;

/**
 * GENERATE BY BraisSpringMVCHelp
 * 
 * @since : $date.long
 * @Description : Validator for table User
 * @Revision :
 */
@Component
public class UserValidator implements Validator {
	
	private static Logger logger = Logger.getLogger(UserValidator.class);

	@Autowired
	private UserManager userManager;

	@Override
	public boolean supports(Class cls) {
		return User.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors e) {
		User user = (User) obj;
		
//		ValidationUtils.rejectIfEmptyOrWhitespace(e, "user_id", "NotEmpty", new String[] { "User ID" }, null);
//		ValidationUtils.rejectIfEmptyOrWhitespace(e, "group_kd", "NotEmpty", new String[] { "Group Kode" }, null);
//		ValidationUtils.rejectIfEmptyOrWhitespace(e, "kd_fungsi", "NotEmpty", new String[] { "Kode Fungsi" }, null);
//		ValidationUtils.rejectIfEmptyOrWhitespace(e, "no_hp", "NotEmpty", new String[] { "No HP" }, null);
//		ValidationUtils.rejectIfEmptyOrWhitespace(e, "email", "NotEmpty", new String[] { "Email" }, null);

		if (!e.hasErrors()) {
			
			//cek apakah  no hp duplicate
			if (!Utils.isEmpty(user.getNo_hp())) {
				if (userManager.selectCountTable("user", "no_hp = '" + user.getNo_hp() + "' and user_id <> '"+user.getUser_id()+"'") > 0)
					e.rejectValue("no_hp", "duplicate", new String[] { "No HP" }, null);
				else if(!Utils.validPhone(Utils.mobileNoContryCode(user.no_hp)))
					e.rejectValue("no_hp", "field_invalid_mobile",null,null);
				
			}
			
			//cek apakah email duplicate
			if (!Utils.isEmpty(user.getEmail())) {
				if (userManager.selectCountTable("user", "email = '" + user.getEmail() + "' and user_id <> '"+user.getUser_id()+"'") > 0)
					e.rejectValue("email", "duplicate", new String[] { "Email" }, null);
			}
		}
	}
}
