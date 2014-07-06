package com.melawai.ppuc.web.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.melawai.ppuc.model.Subdivisi;
import com.melawai.ppuc.services.DivisiManager;
import com.melawai.ppuc.services.SubdivisiManager;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: $date.long 
 * @Description	: Validator for table Subdivisi
 * @Revision	:
 */
@Component
public class SubdivisiValidator implements Validator {
	
	@Autowired
	private SubdivisiManager subdivisiManager;
	
	@Autowired
	private DivisiManager divisiManager;
	
	
	
	public void setSubdivisiManager(SubdivisiManager subdivisiManager) {
		this.subdivisiManager = subdivisiManager;
	}

	public void setDivisiManager(DivisiManager divisiManager) {
		this.divisiManager = divisiManager;
	}

	@Override
	public boolean supports(Class cls) {
		return Subdivisi.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors e) {
		Subdivisi subdivisi=(Subdivisi) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "divisi_kd",  "NotEmpty", new String[]{"Divisi KD"},null);
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "subdiv_kd",  "NotEmpty", new String[]{"Subdivisi KD"},null);
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "subdiv_nm",  "NotEmpty", new String[]{"Subdivisi NM"},null);
		
		if(!e.hasErrors()){
			if(!divisiManager.exists(subdivisi.getDivisi_kd())){
				e.rejectValue("divisi_kd", "entity_not_found_single", new String[]{"DIVISI KD ["+subdivisi.divisi_kd+"]"}, null);
			}
		}
	}

}

