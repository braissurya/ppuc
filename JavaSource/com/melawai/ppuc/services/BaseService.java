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
		Integer max=mainMapper.selectMaxValue(coloumnName, table, where);
		max=max==null?0:max;
		return max;
	}
	
	/**
	 * Fungsi untuk mereplace semua occurence dalam sebuah message template menjadi field2 dari database
	 * 
	 * @author Yusuf
	 * @since Mar 8, 2011
	 */
	/*public String getMessageTemplateFilled(int templateID, int replyID, String jenis, Map<String, Object> params) throws DataAccessException, IllegalArgumentException, SecurityException, IllegalAccessException, NoSuchFieldException{
		
		//tarik dulu template dari tabel sesuai ID nya
		SMSReplyTemplate template = selectSMSReplyTemp(replyID);
		if(template == null) throw new RuntimeException ("Tidak ada message dengan replyID = " + replyID);

		//kemudian tentukan jenis template yg diambil apa
		String txt = null;
		if(jenis.equals("sms")) txt = template.template_sms;
		else if(jenis.equals("web")) txt = template.template_web;
		else if(jenis.equals("email")) txt = template.template_email;
		else if(jenis.equals("subject")) txt = template.template_subject;
		
		//buat pattern regex untuk mari dalam string, semua text yang isinya dalam bracket $P{}
		Pattern p = Pattern.compile("\\$P\\{\\w+\\.\\w+\\}");
		
		//matching antara regex pattern dengan templatenya
		Matcher m = p.matcher(txt);
		
		//lakukan looping terhadap semua $P{} yang ditemukan
		List<String> columns = new ArrayList<String>();
		while(m.find()){
			//occurence
			String tmp = m.group();
			//nama lengkap kolom
			String column = tmp.substring(tmp.indexOf("$P{")+3, tmp.indexOf("}"));
			//bila kolom belum ada di list, maka tambahkan ke list tersebut
			if(!columns.contains(column)) columns.add(column);
		}
		
		//tarik data ke tabel sesuai templateIDnya, dan params nya harus fixed sesuai template tersebut
		if(!columns.isEmpty()){
			params.put("replyId", replyID);
			params.put("columns", StringUtils.join(columns, ","));
			Map<String, Object> data = (HashMap<String, Object>) querySingle("selectMessageTemplate" + templateID, params);
			
			//dari hasil query, kemudian replace nilai2nya
			if(data != null){
				for(String column : columns){
					String shortName = column.substring(column.indexOf(".")+1);
					Object value = data.get(shortName);
					if(value == null) value = "";
					if(shortName.equalsIgnoreCase("password")) value = Utils.decrypt(value.toString());
					if(shortName.contains("date")){
						value = Utils.convertDateToString((Date)value, "dd-MMM-yyyy");
					}
					if(value == null) value = "";
					txt = txt.replace("$P{" + column + "}", value.toString());
				}
			}else{
				throw new RuntimeException("Data tidak ditemukan (selectMessageTemplate" + templateID + ", " + StringUtils.join(params.values(), ",") + ")");
			}
		}
		
		return txt.replace("$P{sysdate()}", Utils.convertDateToString(selectSysdate(0), "dd-MMM-yyyy"));
	}*/
	

}
