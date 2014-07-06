package com.melawai.ppuc.web.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

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

	@Autowired
	private UserManager userManager;

	@Override
	public boolean supports(Class cls) {
		return User.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors e) {
		User user = (User) obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "user_id", "NotEmpty", new String[] { "User ID" }, null);
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "group_kd", "NotEmpty", new String[] { "Group Kode" }, null);
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "kd_fungsi", "NotEmpty", new String[] { "Kode Fungsi" }, null);
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "no_hp", "NotEmpty", new String[] { "No HP" }, null);
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "email", "NotEmpty", new String[] { "Email" }, null);

		if (!e.hasErrors()) {
			if (!Utils.isEmpty(user.getUser_id())) {
				if (userManager.exists(user.getUser_id()))
					e.rejectValue("user_id", "duplicate", new String[] { "User ID" }, null);
			}
			
			if (!Utils.isEmpty(user.getNo_hp())) {
				if (userManager.selectCountTable("user", "no_hp = '" + user.getNo_hp() + "'") > 0)
					e.rejectValue("no_hp", "duplicate", new String[] { "No HP" }, null);
				else if(!Utils.validPhone(Utils.mobileNoContryCode(user.no_hp)))
					e.rejectValue("no_hp", "field_invalid_mobile",null,null);
				
			}
			
			if (!Utils.isEmpty(user.getEmail())) {
				if (userManager.selectCountTable("user", "email = '" + user.getEmail() + "'") > 0)
					e.rejectValue("email", "duplicate", new String[] { "Email" }, null);
			}
		}
	}
}
