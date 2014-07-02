package com.melawai.ppuc.web.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.melawai.ppuc.model.Lokasi;
import com.melawai.ppuc.services.DepartmenManager;
import com.melawai.ppuc.services.DivisiManager;
import com.melawai.ppuc.services.LokasiManager;
import com.melawai.ppuc.services.SubdivisiManager;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: $date.long 
 * @Description	: Validator for table Lokasi
 * @Revision	:
 */
@Component
public class LokasiValidator implements Validator {
	
	@Autowired
	private SubdivisiManager subdivisiManager;
	
	@Autowired
	private DivisiManager divisiManager;
	
	@Autowired
	private DepartmenManager departmenManager;
	
	@Autowired
	private LokasiManager lokasiManager;
	
	
	
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

	@Override
	public boolean supports(Class cls) {
		return Lokasi.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors e) {
		Lokasi lokasi = (Lokasi) obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "divisi_kd",  "NotEmpty", new String[]{"Divisi KD"},null);
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "subdiv_kd",  "NotEmpty", new String[]{"Subdivisi KD"},null);
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "dept_kd",  "NotEmpty", new String[]{"Departmen KD"},null);
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "lok_kd",  "NotEmpty", new String[]{"Lokasi KD"},null);
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "lok_nm",  "NotEmpty", new String[]{"Lokasi Nama"},null);
		
		if(!e.hasErrors()){
			if(!divisiManager.exists(lokasi.getDivisi_kd())){
				e.rejectValue("upload.uploadFile", "entity_not_found_single", new String[]{"DIVISI KD ["+lokasi.divisi_kd+"]"}, null);
			}
			
			if(!subdivisiManager.exists(lokasi.subdiv_kd, lokasi.divisi_kd)){
				e.rejectValue("upload.uploadFile", "entity_not_found_single", new String[]{"DIVISI KD ["+lokasi.divisi_kd+"]"+" | SUBDIVISI KD ["+lokasi.subdiv_kd+"]"}, null);
			}
			
			if(!departmenManager.exists(lokasi.dept_kd, lokasi.subdiv_kd, lokasi.divisi_kd)){
				e.rejectValue("upload.uploadFile", "entity_not_found_single", new String[]{"DIVISI KD ["+lokasi.divisi_kd+"]"+" | SUBDIVISI KD ["+lokasi.subdiv_kd+"]"+" | DEPARTMEN KD ["+lokasi.dept_kd+"]"}, null);
			}
		}
	}

}

