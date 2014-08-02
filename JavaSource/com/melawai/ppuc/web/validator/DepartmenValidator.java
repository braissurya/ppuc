package com.melawai.ppuc.web.validator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.melawai.ppuc.model.Departmen;
import com.melawai.ppuc.services.DepartmenManager;
import com.melawai.ppuc.services.DivisiManager;
import com.melawai.ppuc.services.SubdivisiManager;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: $date.long 
 * @Description	: Validator for table Departmen
 * @Revision	:
 */
@Component
public class DepartmenValidator implements Validator {
	
	private static Logger logger = Logger.getLogger(DepartmenValidator.class);
	
	@Autowired
	private SubdivisiManager subdivisiManager;
	
	@Autowired
	private DivisiManager divisiManager;
	
	@Autowired
	private DepartmenManager departmenManager;
	
	
	
	public void setSubdivisiManager(SubdivisiManager subdivisiManager) {
		this.subdivisiManager = subdivisiManager;
	}

	public void setDivisiManager(DivisiManager divisiManager) {
		this.divisiManager = divisiManager;
	}

	public void setDepartmenManager(DepartmenManager departmenManager) {
		this.departmenManager = departmenManager;
	}

	@Override
	public boolean supports(Class cls) {
		return Departmen.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors e) {
		Departmen departmen= (Departmen) obj;
		
//		ValidationUtils.rejectIfEmptyOrWhitespace(e, "divisi_kd",  "NotEmpty", new String[]{"Divisi KD"},null);
//		ValidationUtils.rejectIfEmptyOrWhitespace(e, "subdiv_kd",  "NotEmpty", new String[]{"Subdivisi KD"},null);
//		ValidationUtils.rejectIfEmptyOrWhitespace(e, "dept_kd",  "NotEmpty", new String[]{"Departmen KD"},null);
//		ValidationUtils.rejectIfEmptyOrWhitespace(e, "dept_nm",  "NotEmpty", new String[]{"Departmen NM"},null);
		
		if(!e.hasErrors()){
			
			departmen.subdiv_kd=departmen.subdiv_kd.substring(departmen.subdiv_kd.lastIndexOf(".") + 1);
			
			if(!divisiManager.exists(departmen.getDivisi_kd())){
				e.rejectValue("divisi_kd", "entity_not_exist", new String[]{"DIVISI KD"}, null);
			}
			
			if(!subdivisiManager.exists(departmen.subdiv_kd, departmen.divisi_kd)){
				e.rejectValue("subdiv_kd", "entity_not_exist", new String[]{"SUBDIVISI KD"}, null);
			}
			
			if(divisiManager.selectCountTable("departmen", "dept_nm = '"+departmen.dept_nm+"' and dept_kd <> '"+departmen.dept_kd+"'")>0){
				e.rejectValue("dept_nm", "duplicate", new String[]{"Dept Name"}, null);
			}
			
		}
	}

}

