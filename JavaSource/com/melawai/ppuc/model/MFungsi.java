package com.melawai.ppuc.model;

import java.io.Serializable;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Thu Jun 19 23:42:37 ICT 2014
 * @Description: Model for table m_fungsi
 * @Revision	:
 */

public class MFungsi extends BaseObject implements Serializable  {

	private static final long serialVersionUID = 3664358261409039786L;

	//****************** COLOMN FROM TABLE START HERE ******************/
	@NotEmpty
	@Size(max=5)
	public String kd_fungsi;

	@NotEmpty
	@Size(max=50)
	public String nm_fungsi;

	public Long mail_seq;


	//****************** COLOMN FROM TABLE END HERE ******************/


	//****************** OTHERS START HERE ******************/

	public String itemId;
	//****************** OTHERS END HERE ******************/


	//****************** CONSTRUCTOR START HERE ******************/
	public MFungsi(){
		//TODO: standard constructor free to change
	}

	//****************** CONSTRUCTOR END HERE ******************/


	//****************** GETTER SETTER START HERE ******************/
	public String getKd_fungsi(){ return kd_fungsi; }
	public void setKd_fungsi(String kd_fungsi){ this.kd_fungsi = kd_fungsi; }

	public String getNm_fungsi(){ return nm_fungsi; }
	public void setNm_fungsi(String nm_fungsi){ this.nm_fungsi = nm_fungsi; }

	public Long getMail_seq(){ return mail_seq; }
	public void setMail_seq(Long mail_seq){ this.mail_seq = mail_seq; }

	public String getItemId() {return ""+kd_fungsi;	}
	public void setItemId(String itemId) {this.itemId = itemId;}

	//****************** GETTER SETTER END HERE ******************/

}
