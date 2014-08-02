package com.melawai.ppuc.web.validator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.melawai.ppuc.model.GroupBiaya;
import com.melawai.ppuc.services.GroupBiayaManager;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: $date.long 
 * @Description	: Validator for table GroupBiaya
 * @Revision	:
 */
@Component
public class GroupBiayaValidator  implements Validator {
	
	private static Logger logger = Logger.getLogger(GroupBiayaValidator.class);

	@Autowired
	private GroupBiayaManager groupBiayaManager; 
	
	@Override
	public boolean supports(Class cls) {
		return GroupBiaya.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors e) {
		GroupBiaya groupBiaya=(GroupBiaya) obj;
		if(!e.hasErrors()){
			if(groupBiayaManager.selectCountTable("group_biaya", "nm_group = '"+groupBiaya.nm_group+"' and kd_group <> '"+groupBiaya.kd_group+"'")>0){
				e.rejectValue("nm_group", "duplicate", new String[]{"Group Name"}, null);
			}
		}
	}

	public void setGroupBiayaManager(GroupBiayaManager groupBiayaManager) {
		this.groupBiayaManager = groupBiayaManager;
	}

}

