package com.melawai.ppuc.web.validator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.melawai.ppuc.model.GroupLokasiH;
import com.melawai.ppuc.services.DepartmenManager;
import com.melawai.ppuc.services.DivisiManager;
import com.melawai.ppuc.services.GroupLokasiHManager;
import com.melawai.ppuc.services.LokasiManager;
import com.melawai.ppuc.services.SubdivisiManager;
import com.melawai.ppuc.utils.Utils;

/**
 * GENERATE BY BraisSpringMVCHelp
 * 
 * @since : $date.long
 * @Description : Validator for table GroupLokasiH
 * @Revision :
 */
@Component
public class GroupLokasiHValidator implements Validator {

	private static Logger logger = Logger.getLogger(GroupLokasiHValidator.class);

	@Autowired
	private SubdivisiManager subdivisiManager;

	@Autowired
	private DivisiManager divisiManager;

	@Autowired
	private DepartmenManager departmenManager;

	@Autowired
	private LokasiManager lokasiManager;
	
	@Autowired
	private GroupLokasiHManager groupLokasiHManager;

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
		return GroupLokasiH.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors e) {
		GroupLokasiH groupLokasih = (GroupLokasiH) obj;
		
		if(!Utils.isEmpty(groupLokasih.subdiv_kd))
			groupLokasih.subdiv_kd = groupLokasih.subdiv_kd.substring(groupLokasih.subdiv_kd.lastIndexOf(".") + 1);

		if (!e.hasErrors()) {
			

			if (!divisiManager.exists(groupLokasih.getDivisi_kd())) {
				e.rejectValue("divisi_kd", "entity_not_exist", new String[] { "DIVISI KD" }, null);
			}

			if (!subdivisiManager.exists(groupLokasih.subdiv_kd, groupLokasih.divisi_kd)) {
				e.rejectValue("subdiv_kd", "entity_not_exist", new String[] { "SUBDIVISI KD" }, null);
			}
			
			

			for (String lok_kd : groupLokasih.lok_kd) {
				lok_kd=lok_kd.substring(lok_kd.lastIndexOf(".") + 1);
				if (!lokasiManager.exists(lok_kd, null, groupLokasih.subdiv_kd, groupLokasih.divisi_kd)) {
					e.rejectValue("lok_kd", "entity_not_exist", new String[] { "LOKASI KD" }, null);
				}
			}

		}
	}

	public void setGroupLokasiHManager(GroupLokasiHManager groupLokasiHManager) {
		this.groupLokasiHManager = groupLokasiHManager;
	}

}
