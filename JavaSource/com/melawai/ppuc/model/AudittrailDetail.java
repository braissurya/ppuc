package com.melawai.ppuc.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Thu Jun 19 23:42:38 ICT 2014
 * @Description: Model for table sys_audittrail_detail
 * @Revision	:
 */

public class AudittrailDetail extends BaseObject implements Serializable  {

	
	private static final long serialVersionUID = 1306133885745974070L;

	//****************** COLOMN FROM TABLE START HERE ******************/
	@NotNull
	public Long id_audittrail_detail;

	@NotNull
	public Long id_audittrail;

	@Size(max=50)
	public String field;

	@Size(max=65535)
	public String before;

	@Size(max=65535)
	public String after;

	//****************** COLOMN FROM TABLE END HERE ******************/


	//****************** OTHERS START HERE ******************/

	public String itemId;
	//****************** OTHERS END HERE ******************/


	//****************** CONSTRUCTOR START HERE ******************/
	
	
	public AudittrailDetail(){
		//TODO: standard constructor free to change
	}

	public AudittrailDetail(String field, String before, String after) {
		super();
		this.field = field;
		this.before = before;
		this.after = after;
	}

	//****************** CONSTRUCTOR END HERE ******************/


	//****************** GETTER SETTER START HERE ******************/
	public Long getId_audittrail_detail(){ return id_audittrail_detail; }
	public void setId_audittrail_detail(Long id_audittrail_detail){ this.id_audittrail_detail = id_audittrail_detail; }

	public Long getId_audittrail(){ return id_audittrail; }
	public void setId_audittrail(Long id_audittrail){ this.id_audittrail = id_audittrail; }

	public String getField(){ return field; }
	public void setField(String field){ this.field = field; }

	public String getBefore(){ return before; }
	public void setBefore(String before){ this.before = before; }

	public String getAfter(){ return after; }
	public void setAfter(String after){ this.after = after; }

	public String getItemId() {return ""+id_audittrail_detail;	}
	public void setItemId(String itemId) {this.itemId = itemId;}

	@Override
	public String toString() {
		return "AudittrailDetail [id_audittrail_detail=" + id_audittrail_detail + ", id_audittrail=" + id_audittrail + ", field=" + field + ", before=" + before + ", after=" + after
				+ ", user_create=" + user_create + ", tgl_create=" + tgl_create + ", itemId=" + itemId + "]";
	}

	//****************** GETTER SETTER END HERE ******************/
	
	

}
