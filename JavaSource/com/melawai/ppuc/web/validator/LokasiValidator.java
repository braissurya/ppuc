package com.melawai.ppuc.web.validator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

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
public class LokasiValidator  implements Validator {
	
	private static Logger logger = Logger.getLogger(LokasiValidator.class);
	
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
		
//		ValidationUtils.rejectIfEmptyOrWhitespace(e, "divisi_kd",  "NotEmpty", new String[]{"Divisi KD"},null);
//		ValidationUtils.rejectIfEmptyOrWhitespace(e, "subdiv_kd",  "NotEmpty", new String[]{"Subdivisi KD"},null);
//		ValidationUtils.rejectIfEmptyOrWhitespace(e, "dept_kd",  "NotEmpty", new String[]{"Departmen KD"},null);
//		ValidationUtils.rejectIfEmptyOrWhitespace(e, "lok_kd",  "NotEmpty", new String[]{"Lokasi KD"},null);
//		ValidationUtils.rejectIfEmptyOrWhitespace(e, "lok_nm",  "NotEmpty", new String[]{"Lokasi Nama"},null);
		
		if(!e.hasErrors()){
			lokasi.subdiv_kd=lokasi.subdiv_kd.substring(lokasi.subdiv_kd.lastIndexOf(".") + 1);
			lokasi.dept_kd=lokasi.dept_kd.substring(lokasi.dept_kd.lastIndexOf(".") + 1);
			
			if(!divisiManager.exists(lokasi.getDivisi_kd())){
				e.rejectValue("divisi_kd", "entity_not_exist", new String[]{"DIVISI KD"}, null);
			}
			
			if(!subdivisiManager.exists(lokasi.subdiv_kd, lokasi.divisi_kd)){
				e.rejectValue("subdiv_kd", "entity_not_exist", new String[]{"SUBDIVISI KD"}, null);
			}
			
			if(!departmenManager.exists(lokasi.dept_kd, lokasi.subdiv_kd, lokasi.divisi_kd)){
				e.rejectValue("dept_kd", "entity_not_exist", new String[]{"DEPARTMEN KD"}, null);
			}
			
			if(divisiManager.selectCountTable("propinsi", "propinsi ='"+lokasi.getPropinsi()+"'")<1){
				e.rejectValue("propinsi", "entity_not_exist", new String[]{"Propinsi"}, null);
			}
			
			if(divisiManager.selectCountTable("kota", "kota='"+lokasi.getKota()+"' and propinsi ='"+lokasi.getPropinsi()+"'")<1){
				e.rejectValue("kota", "entity_not_exist", new String[]{"Kota"}, null);
			}
			
			if(lokasiManager.selectCountTable("lokasi", "lok_nm = '"+lokasi.lok_nm+"' and lok_kd <>'"+lokasi.lok_kd+"'")>0){
				e.rejectValue("lok_nm",  "duplicate", new String[]{"Lokasi Name"}, null);
			}
			
			
			if(lokasi.getF_tutup()==1)
				ValidationUtils.rejectIfEmptyOrWhitespace(e, "tgl_tutup",  "NotEmpty", new String[]{"Tanggal Tutup"},null);
		}
	}

}

