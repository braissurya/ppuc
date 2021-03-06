package com.melawai.ppuc.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Thu Jun 19 23:42:36 ICT 2014
 * @Description: Model for table message_template_detail
 * @Revision	:
 */

public class MessageTemplateDetail extends BaseObject implements Serializable  {

	private static final long serialVersionUID = 496262995993782321L;

	//****************** COLOMN FROM TABLE START HERE ******************/
	@NotNull
	public Long id_template_detail;

	@NotNull
	public Long id_template;

	@Size(max=200)
	public String keterangan;

	@Size(max=65535)
	public String template_sms;

	@Size(max=65535)
	public String template_web;

	@Size(max=65535)
	public String template_subject;

	@Size(max=65535)
	public String template_email;

	public Long f_aktif;

	

	//****************** COLOMN FROM TABLE END HERE ******************/


	//****************** OTHERS START HERE ******************/

	public String itemId;
	//****************** OTHERS END HERE ******************/


	//****************** CONSTRUCTOR START HERE ******************/
	public MessageTemplateDetail(){
		//TODO: standard constructor free to change
	}

	//****************** CONSTRUCTOR END HERE ******************/


	//****************** GETTER SETTER START HERE ******************/
	public Long getId_template_detail(){ return id_template_detail; }
	public void setId_template_detail(Long id_template_detail){ this.id_template_detail = id_template_detail; }

	public Long getId_template(){ return id_template; }
	public void setId_template(Long id_template){ this.id_template = id_template; }

	public String getKeterangan(){ return keterangan; }
	public void setKeterangan(String keterangan){ this.keterangan = keterangan; }

	public String getTemplate_sms(){ return template_sms; }
	public void setTemplate_sms(String template_sms){ this.template_sms = template_sms; }

	public String getTemplate_web(){ return template_web; }
	public void setTemplate_web(String template_web){ this.template_web = template_web; }

	public String getTemplate_subject(){ return template_subject; }
	public void setTemplate_subject(String template_subject){ this.template_subject = template_subject; }

	public String getTemplate_email(){ return template_email; }
	public void setTemplate_email(String template_email){ this.template_email = template_email; }

	public Long getF_aktif(){ return f_aktif; }
	public void setF_aktif(Long f_aktif){ this.f_aktif = f_aktif; }

	

	public String getItemId() {return ""+id_template_detail;	}
	public void setItemId(String itemId) {this.itemId = itemId;}

	//****************** GETTER SETTER END HERE ******************/

}
