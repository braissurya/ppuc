package com.melawai.ppuc.web.validator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.melawai.ppuc.model.Divisi;
import com.melawai.ppuc.services.DivisiManager;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: $date.long 
 * @Description	: Validator for table Divisi
 * @Revision	:
 */
@Component
public class DivisiValidator implements Validator {
	
	private static Logger logger = Logger.getLogger(DivisiValidator.class);
	
	@Autowired
	private DivisiManager divisiManager;
	
	@Override
	public boolean supports(Class cls) {
		return Divisi.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors e) {
		
		Divisi divisi=(Divisi) obj;
//		ValidationUtils.rejectIfEmptyOrWhitespace(e, "divisi_kd",  "NotEmpty", new String[]{"Divisi KD"},null);
//		ValidationUtils.rejectIfEmptyOrWhitespace(e, "divisi_nm",  "NotEmpty", new String[]{"Divisi NM"},null);
		
		if(!e.hasErrors()){
			if(divisiManager.selectCountTable("divisi", "divisi_nm = '"+divisi.divisi_nm+"' and divisi_kd <> '"+divisi.divisi_kd+"'")>0){
				e.rejectValue("divisi_nm", "duplicate", new String[]{"Divisi Name"}, null);
			}
		}
	}

	public void setDivisiManager(DivisiManager divisiManager) {
		this.divisiManager = divisiManager;
	}

}

