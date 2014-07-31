package com.melawai.ppuc.model;

import java.io.Serializable;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Thu Jun 19 23:42:36 ICT 2014
 * @Description: Model for table message_template
 * @Revision	:
 */

public class MessageTemplate extends BaseObject implements Serializable  {

	private static final long serialVersionUID = -5360632574567761560L;

	//****************** COLOMN FROM TABLE START HERE ******************/
	@NotEmpty
	public Long id_template;

	@Size(max=50)
	public String nm_template;

	@Size(max=400)
	public String parameter;

	@Size(max=200)
	public String keterangan;

	@Size(max=45)
	public String f_aktif;

	

	//****************** COLOMN FROM TABLE END HERE ******************/


	//****************** OTHERS START HERE ******************/

	public String itemId;
	//****************** OTHERS END HERE ******************/


	//****************** CONSTRUCTOR START HERE ******************/
	public MessageTemplate(){
		//TODO: standard constructor free to change
	}

	//****************** CONSTRUCTOR END HERE ******************/


	//****************** GETTER SETTER START HERE ******************/
	public Long getId_template(){ return id_template; }
	public void setId_template(Long id_template){ this.id_template = id_template; }

	public String getNm_template(){ return nm_template; }
	public void setNm_template(String nm_template){ this.nm_template = nm_template; }

	public String getParameter(){ return parameter; }
	public void setParameter(String parameter){ this.parameter = parameter; }

	public String getKeterangan(){ return keterangan; }
	public void setKeterangan(String keterangan){ this.keterangan = keterangan; }

	public String getF_aktif(){ return f_aktif; }
	public void setF_aktif(String f_aktif){ this.f_aktif = f_aktif; }

	

	public String getItemId() {return ""+id_template;	}
	public void setItemId(String itemId) {this.itemId = itemId;}

	//****************** GETTER SETTER END HERE ******************/

}
