package com.melawai.ppuc.services;

import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.melawai.ppuc.model.Audittrail;
import com.melawai.ppuc.model.AudittrailDetail;
import com.melawai.ppuc.model.DropDown;
import com.melawai.ppuc.model.User;
import com.melawai.ppuc.persistence.MainMapper;

@Service("baseService")
public class BaseService {
	
	private static Logger logger = Logger.getLogger(BaseService.class);
	
	@Autowired
	protected MainMapper mainMapper;

	@Autowired
	protected AudittrailManager audittrailManager;
	
	@Autowired
	protected HttpServletRequest httpServletRequest;

	@Autowired
	protected AudittrailDetailManager audittrailDetailManager;
	
	@Autowired
	protected Properties props;

	@Transactional
	public Audittrail audittrail(String type_audit, String subtype_audit, String modelName, String model_id, String ip, String info, User createdBy, Set<AudittrailDetail> changes) {

		Audittrail auditLog = new Audittrail();

		auditLog.setType_audit(type_audit);

		auditLog.setSubtype_audit(subtype_audit);

		auditLog.setModel(modelName);

		auditLog.setModel_id(model_id);

		auditLog.setIp(ip);

		auditLog.setInfo(info);

		if(createdBy!=null)
		auditLog.setUser_create(createdBy.user_id);
		
		auditLog.setTgl_create(selectSysdate());

		audittrailManager.save(auditLog);

		if (changes!=null) {
			for (AudittrailDetail det : changes) {
				det.setId_audittrail(auditLog.getId_audittrail());
				det.setUser_create(createdBy.user_id);
				det.setTgl_create(selectSysdate());
				audittrailDetailManager.save(det);
			}
		}

		return auditLog;
	}
	
	public Date selectSysdate() {
		logger.debug("selectSysdate");
		return mainMapper.selectSysdate();
	}

	public List<DropDown> selectDropDown(String key, String value, String table, String where, String orderby) {
		return mainMapper.selectDropDown(key, value, table, where, orderby);
	}



	
	public Integer selectCountTable(String table, String where) {
		return mainMapper.selectCountTable(table, where);
	}
	
	public Integer selectMaxValue(String coloumnName,String table, String where) {
		return mainMapper.selectMaxValue(coloumnName, table, where);
	}

}
