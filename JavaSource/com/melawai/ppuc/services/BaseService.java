package com.melawai.ppuc.services;

import java.util.Date;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.melawai.ppuc.model.Audittrail;
import com.melawai.ppuc.model.AudittrailDetail;
import com.melawai.ppuc.model.User;

@Service("baseService")
public class BaseService {

	@Autowired
	protected AudittrailManager audittrailManager;
	
	@Autowired
	protected HttpServletRequest httpServletRequest;

	@Autowired
	protected AudittrailDetailManager audittrailDetailManager;

	@Transactional
	public Audittrail audittrail(String type_audit, String subtype_audit, String modelName, String model_id, String ip, String info, User createdBy, Set<AudittrailDetail> changes) {

		Audittrail auditLog = new Audittrail();

		auditLog.setType_audit(type_audit);

		auditLog.setSubtype_audit(subtype_audit);

		auditLog.setModel(modelName);

		auditLog.setModel_id(model_id);

		auditLog.setIp(ip);

		auditLog.setInfo(info);

		auditLog.setUser_create(createdBy.user_id);
		auditLog.setTgl_create(new Date());

		audittrailManager.save(auditLog);

		if (changes!=null) {
			for (AudittrailDetail det : changes) {
				det.setId_audittrail(auditLog.getId_audittrail());
				det.setUser_create(createdBy.user_id);
				det.setTgl_create(new Date());
				audittrailDetailManager.save(det);
			}
		}

		return auditLog;
	}

}
