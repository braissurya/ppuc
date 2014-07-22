package com.melawai.ppuc.web.validator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.melawai.ppuc.model.UserDivisi;
import com.melawai.ppuc.services.DepartmenManager;
import com.melawai.ppuc.services.DivisiManager;
import com.melawai.ppuc.services.LokasiManager;
import com.melawai.ppuc.services.SubdivisiManager;
import com.melawai.ppuc.services.UserDivisiManager;
import com.melawai.ppuc.utils.Utils;

/**
 * GENERATE BY BraisSpringMVCHelp
 * 
 * @since : $date.long
 * @Description : Validator for table UserDivisi
 * @Revision :
 */
@Component
public class UserDivisiValidator implements Validator {
	
	private static Logger logger = Logger.getLogger(UserDivisiValidator.class);

	@Autowired
	private SubdivisiManager subdivisiManager;

	@Autowired
	private DivisiManager divisiManager;

	@Autowired
	private DepartmenManager departmenManager;

	@Autowired
	private LokasiManager lokasiManager;
	
	@Autowired
	private UserDivisiManager userDivisiManager;

	public void setSubdivisiManager(SubdivisiManager subdivisiManager) {
		this.subdivisiManager = subdivisiManager;
	}

	public void setDivisiManager(DivisiManager divisiManager) {
		this.divisiManager = divisiManager;
	}

	public void setDepartmenManager(DepartmenManager departmenManager) {
		this.departmenManager = departmenManager;
	}

	public void setLokasiManager(LokasiManager lokasiManager) {
		this.lokasiManager = lokasiManager;
	}
	

	public void setUserDivisiManager(UserDivisiManager userDivisiManager) {
		this.userDivisiManager = userDivisiManager;
	}

	@Override
	public boolean supports(Class cls) {
		return UserDivisi.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors e) {
		UserDivisi userDivisi = (UserDivisi) obj;
		
//		ValidationUtils.rejectIfEmptyOrWhitespace(e, "user_id", "NotEmpty", new String[] { "User Id	" }, null);
//		ValidationUtils.rejectIfEmptyOrWhitespace(e, "divisi_kd", "NotEmpty", new String[] { "Divisi KD" }, null);
//		ValidationUtils.rejectIfEmptyOrWhitespace(e, "subdiv_kd", "NotEmpty", new String[] { "Subdivisi KD" }, null);
//		ValidationUtils.rejectIfEmptyOrWhitespace(e, "dept_kd", "NotEmpty", new String[] { "Departmen KD" }, null);

		if (!e.hasErrors()) {
			userDivisi.subdiv_kd = userDivisi.subdiv_kd.substring(userDivisi.subdiv_kd.lastIndexOf(".") + 1);
			userDivisi.dept_kd = userDivisi.dept_kd.substring(userDivisi.dept_kd.lastIndexOf(".") + 1);
			userDivisi.lok_kd = userDivisi.lok_kd.substring(userDivisi.lok_kd.lastIndexOf(".") + 1);
			
			if (!divisiManager.exists(userDivisi.getDivisi_kd())) {
				e.rejectValue("divisi_kd", "entity_not_exist", new String[] { "DIVISI KD" }, null);
			}

			if (!subdivisiManager.exists(userDivisi.subdiv_kd, userDivisi.divisi_kd)) {
				e.rejectValue("subdiv_kd", "entity_not_exist", new String[] {"SUBDIVISI KD" }, null);
			}

			if (!departmenManager.exists(userDivisi.dept_kd, userDivisi.subdiv_kd, userDivisi.divisi_kd)) {
				e.rejectValue("dept_kd", "entity_not_exist", new String[] { "DEPARTMEN KD" }, null);
			}
			
			if(!Utils.isEmpty(userDivisi.lok_kd))
			if (!lokasiManager.exists(userDivisi.lok_kd,userDivisi.dept_kd, userDivisi.subdiv_kd, userDivisi.divisi_kd)) {
				e.rejectValue("lok_kd", "entity_not_exist", new String[] { "LOKASI KD" }, null);
			}
			
			
			
		}
	}

}
