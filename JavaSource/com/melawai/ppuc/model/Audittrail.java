package com.melawai.ppuc.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Thu Jun 19 23:42:38 ICT 2014
 * @Description: Model for table sys_audittrail
 * @Revision	:
 */

public class Audittrail extends BaseObject implements Serializable  {
	

	private static final long serialVersionUID = -7425784022134568436L;
	
	static public class Activity {
		public static final String APP = "APP";
		public static final String LOGIN = "LOGIN";
		public static final String TRANS = "TRANS";
		public static final String AUTO = "AUTO";
		public static final String EXIM = "EXIM";
	}
	
	static public class APPType {
		public static final String STARTED = "STARTED";
		public static final String STOPPED = "STOPPED";
	}

	static public class LoginType {
		public static final String SUCCESS = "SUCCESS";
		public static final String FAILED = "FAILED";
	}

	static public class TransType {
		public static final String ADD = "ADD";
		public static final String UPDATE = "UPDATE";
		public static final String DELETE = "DELETE";
		public static final String RESET = "RESET";
		public static final String NONAKTIF = "NONAKTIF";
		public static final String AKTIF = "AKTIF";
	}
	
	static public class EximType {
		public static final String SUCCESS = "SUCCESS";
		public static final String FAILED = "FAILED";
	}

	//****************** COLOMN FROM TABLE START HERE ******************/
	@NotNull
	public Long id_audittrail;

	@Size(max=10)
	public String type_audit;
	
	@Size(max=10)
	public String subtype_audit;

	@Size(max=100)
	public String model;

	@Size(max=100)
	public String model_id;

	@Size(max=100)
	public String ip;

	@Size(max=65535)
	public String info;


	//****************** COLOMN FROM TABLE END HERE ******************/


	//****************** OTHERS START HERE ******************/

	public String itemId;
	//****************** OTHERS END HERE ******************/


	//****************** CONSTRUCTOR START HERE ******************/
	public Audittrail(){
		//TODO: standard constructor free to change
	}

	//****************** CONSTRUCTOR END HERE ******************/


	//****************** GETTER SETTER START HERE ******************/
	public Long getId_audittrail(){ return id_audittrail; }
	public void setId_audittrail(Long id_audittrail){ this.id_audittrail = id_audittrail; }

	public String getType_audit(){ return type_audit; }
	public void setType_audit(String type_audit){ this.type_audit = type_audit; }
	
	public String getSubtype_audit() {return subtype_audit;	}
	public void setSubtype_audit(String subtype_audit) {this.subtype_audit = subtype_audit;}

	public String getModel(){ return model; }
	public void setModel(String model){ this.model = model; }

	public String getModel_id(){ return model_id; }
	public void setModel_id(String model_id){ this.model_id = model_id; }

	public String getIp(){ return ip; }
	public void setIp(String ip){ this.ip = ip; }

	public String getInfo(){ return info; }
	public void setInfo(String info){ this.info = info; }

	public String getItemId() {return ""+id_audittrail;	}
	public void setItemId(String itemId) {this.itemId = itemId;}

	//****************** GETTER SETTER END HERE ******************/

}
