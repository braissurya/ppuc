package com.melawai.ppuc.web.validator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.melawai.ppuc.model.HakApprove;
import com.melawai.ppuc.services.DepartmenManager;
import com.melawai.ppuc.services.DivisiManager;
import com.melawai.ppuc.services.HakApproveManager;
import com.melawai.ppuc.services.SubdivisiManager;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: $date.long 
 * @Description	: Validator for table HakApprove
 * @Revision	:
 */
@Component
public class HakApproveValidator extends LocalValidatorFactoryBean implements Validator {
	
	private static Logger logger = Logger.getLogger(HakApproveValidator.class);
	
	@Autowired
	private DivisiManager divisiManager;
	
	@Autowired
	private SubdivisiManager subdivisiManager;

	@Autowired
	private DepartmenManager departmenManager;
	
	@Autowired
	private HakApproveManager hakApproveManager;
	
	@Override
	public boolean supports(Class cls) {
		return HakApprove.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors e) {
		HakApprove hakapprove=(HakApprove) obj;
		if(!e.hasErrors()){
			hakapprove.subdiv_kd = hakapprove.subdiv_kd.substring(hakapprove.subdiv_kd.lastIndexOf(".") + 1);
			hakapprove.dept_kd = hakapprove.dept_kd.substring(hakapprove.dept_kd.lastIndexOf(".") + 1);
			
			if (!divisiManager.exists(hakapprove.getDivisi_kd())) {
				e.rejectValue("divisi_kd", "entity_not_exist", new String[] { "DIVISI KD" }, null);
			}

			if (!subdivisiManager.exists(hakapprove.subdiv_kd, hakapprove.divisi_kd)) {
				e.rejectValue("subdiv_kd", "entity_not_exist", new String[] {"SUBDIVISI KD" }, null);
			}

			if (!departmenManager.exists(hakapprove.dept_kd, hakapprove.subdiv_kd, hakapprove.divisi_kd)) {
				e.rejectValue("dept_kd", "entity_not_exist", new String[] { "DEPARTMEN KD" }, null);
			}
			
			if(hakApproveManager.selectCountTable("group_biaya", "kd_group = '"+hakapprove.kd_group+"'")<1){
				e.rejectValue("kd_group", "entity_not_exist", new String[] { "KD GROUP" }, null);
			}
			
			if(hakApproveManager.selectCountTable("detail_biaya", "kd_biaya = '"+hakapprove.kd_biaya+"'")<1){
				e.rejectValue("kd_biaya", "entity_not_exist", new String[] { "KD BIAYA" }, null);
			}
		}
	}

	public void setDivisiManager(DivisiManager divisiManager) {
		this.divisiManager = divisiManager;
	}

	public void setSubdivisiManager(SubdivisiManager subdivisiManager) {
		this.subdivisiManager = subdivisiManager;
	}

	public void setDepartmenManager(DepartmenManager departmenManager) {
		this.departmenManager = departmenManager;
	}

	public void setHakApproveManager(HakApproveManager hakApproveManager) {
		this.hakApproveManager = hakApproveManager;
	}

}

