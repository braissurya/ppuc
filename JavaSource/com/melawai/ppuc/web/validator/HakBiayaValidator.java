package com.melawai.ppuc.web.validator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.melawai.ppuc.model.DetailBiaya;
import com.melawai.ppuc.model.HakBiaya;
import com.melawai.ppuc.model.Lokasi;
import com.melawai.ppuc.services.DepartmenManager;
import com.melawai.ppuc.services.DetailBiayaManager;
import com.melawai.ppuc.services.DivisiManager;
import com.melawai.ppuc.services.HakBiayaManager;
import com.melawai.ppuc.services.LokasiManager;
import com.melawai.ppuc.services.SubdivisiManager;
import com.melawai.ppuc.services.UserDivisiManager;
import com.melawai.ppuc.utils.Utils;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: $date.long 
 * @Description	: Validator for table HakBiaya
 * @Revision	:
 */
@Component
public class HakBiayaValidator implements Validator {
	
	private static Logger logger = Logger.getLogger(HakBiayaValidator.class);
	
	@Autowired
	private HakBiayaManager hakbiayaManager;
	
	@Autowired
	private DivisiManager divisiManager;
	
	@Autowired
	private SubdivisiManager subdivisiManager;

	@Autowired
	private DepartmenManager departmenManager;

	@Autowired
	private LokasiManager lokasiManager;
	
	@Autowired
	private DetailBiayaManager detailBiayaManager;
	
	
	public void setDivisiManager(DivisiManager divisiManager) {
		this.divisiManager = divisiManager;
	}
	
	
	public void setSubdivisiManager(SubdivisiManager subdivisiManager) {
		this.subdivisiManager = subdivisiManager;
	}


	public void setDepartmenManager(DepartmenManager departmenManager) {
		this.departmenManager = departmenManager;
	}

	public void setLokasiManager(LokasiManager lokasiManager) {
		this.lokasiManager = lokasiManager;
	}
	

	public void setHakbiayaManager(HakBiayaManager hakbiayaManager) {
		this.hakbiayaManager = hakbiayaManager;
	}
	
	

	@Override
	public boolean supports(Class cls) {
		return HakBiaya.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors e) {
		HakBiaya hakbiaya=(HakBiaya) obj;
		
		if(hakbiaya.typeInput!=null){
			ValidationUtils.rejectIfEmptyOrWhitespace(e, "lk",  "NotEmpty", null,null);
			ValidationUtils.rejectIfEmptyOrWhitespace(e, "kb",  "NotEmpty", null,null);
			
			if(!e.hasErrors()){
				String [] lokasi=hakbiaya.lk.split(",");
				String [] biaya=hakbiaya.kb.split(",");
				
				for(String lok_kd:lokasi){
					Lokasi lok=lokasiManager.get(lok_kd, null, null, null);
					for(String kd_biaya:biaya){
						DetailBiaya detailBiaya=detailBiayaManager.get(kd_biaya);
						
						hakbiaya.hakBiayaList.add(new HakBiaya(lok.divisi_kd, lok.subdiv_kd, lok.dept_kd, lok_kd, detailBiaya.kd_group, detailBiaya.kd_biaya, hakbiaya.f_aktif, hakbiaya.drtgl, hakbiaya.sptgl, hakbiaya.user_nonaktif, hakbiaya.tgl_nonaktif, hakbiaya.jam_nonaktif));
					}
				}
				
				for(HakBiaya hk:hakbiaya.hakBiayaList){
				// tambahan validasi khusus
					if (hakbiayaManager.exists(hk.divisi_kd, hk.subdiv_kd, hk.dept_kd, hk.lok_kd, hk.kd_group, hk.kd_biaya)) {
						e.rejectValue("kb", "duplicate", new String[] { "LOKASI KD : " + hk.lok_kd + " | DIVISI KD : " + hk.divisi_kd + " | SUBDIVISI KD : " + hk.subdiv_kd + " | DEPARTMEN KD : " + hk.dept_kd + " | KD HAKBIAYA : " + hk.kd_biaya + ", " }, null);
					}
				}
			}
		}else{
			ValidationUtils.rejectIfEmptyOrWhitespace(e, "divisi_kd",  "NotEmpty", null,null);
			ValidationUtils.rejectIfEmptyOrWhitespace(e, "subdiv_kd",  "NotEmpty", null,null);
			ValidationUtils.rejectIfEmptyOrWhitespace(e, "dept_kd",  "NotEmpty", null,null);
			ValidationUtils.rejectIfEmptyOrWhitespace(e, "lok_kd",  "NotEmpty", null,null);
			ValidationUtils.rejectIfEmptyOrWhitespace(e, "kd_group",  "NotEmpty", null,null);
			ValidationUtils.rejectIfEmptyOrWhitespace(e, "kd_biaya",  "NotEmpty", null,null);

			if(!e.hasErrors()){
				hakbiaya.subdiv_kd = hakbiaya.subdiv_kd.substring(hakbiaya.subdiv_kd.lastIndexOf(".") + 1);
				hakbiaya.dept_kd = hakbiaya.dept_kd.substring(hakbiaya.dept_kd.lastIndexOf(".") + 1);
				hakbiaya.lok_kd = hakbiaya.lok_kd.substring(hakbiaya.lok_kd.lastIndexOf(".") + 1);
				
				if (!divisiManager.exists(hakbiaya.getDivisi_kd())) {
					e.rejectValue("divisi_kd", "entity_not_exist", new String[] { "DIVISI KD" }, null);
				}
	
				if (!subdivisiManager.exists(hakbiaya.subdiv_kd, hakbiaya.divisi_kd)) {
					e.rejectValue("subdiv_kd", "entity_not_exist", new String[] {"SUBDIVISI KD" }, null);
				}
	
				if (!departmenManager.exists(hakbiaya.dept_kd, hakbiaya.subdiv_kd, hakbiaya.divisi_kd)) {
					e.rejectValue("dept_kd", "entity_not_exist", new String[] { "DEPARTMEN KD" }, null);
				}
				
				if (!lokasiManager.exists(hakbiaya.lok_kd,hakbiaya.dept_kd, hakbiaya.subdiv_kd, hakbiaya.divisi_kd)) {
					e.rejectValue("lok_kd", "entity_not_exist", new String[] { "LOKASI KD" }, null);
				}
				
				if(hakbiayaManager.selectCountTable("group_biaya", "kd_group = '"+hakbiaya.kd_group+"'")<1){
					e.rejectValue("kd_group", "entity_not_exist", new String[] { "KD GROUP" }, null);
				}
				
				if(hakbiayaManager.selectCountTable("detail_biaya", "kd_biaya = '"+hakbiaya.kd_biaya+"'")<1){
					e.rejectValue("kd_biaya", "entity_not_exist", new String[] { "KD BIAYA" }, null);
				}
			}
		}
	}


	public void setDetailBiayaManager(DetailBiayaManager detailBiayaManager) {
		this.detailBiayaManager = detailBiayaManager;
	}

}

