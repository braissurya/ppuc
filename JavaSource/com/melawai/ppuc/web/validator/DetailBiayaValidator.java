package com.melawai.ppuc.web.validator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.melawai.ppuc.model.DetailBiaya;
import com.melawai.ppuc.services.DetailBiayaManager;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: $date.long 
 * @Description	: Validator for table DetailBiaya
 * @Revision	:
 */
@Component
public class DetailBiayaValidator  implements Validator {
	
	private static Logger logger = Logger.getLogger(DetailBiayaValidator.class);
	
	@Autowired
	private DetailBiayaManager detailbiayaManager;
	
	public void setDetailbiayaManager(DetailBiayaManager detailbiayaManager) {
		this.detailbiayaManager = detailbiayaManager;
	}

	@Override
	public boolean supports(Class cls) {
		return DetailBiaya.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors e) {
		DetailBiaya detailBiaya=(DetailBiaya) obj;
		
		if(!e.hasErrors()){
			if(detailbiayaManager.selectCountTable("group_biaya", "kd_group = '"+detailBiaya.kd_group+"'")<1){
				e.rejectValue("kd_group", "entity_not_exist", new String[]{"KD GROUP"}, null);
			}
		}
	}

}

