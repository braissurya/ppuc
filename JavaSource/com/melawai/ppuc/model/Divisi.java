package com.melawai.ppuc.model;

import java.io.Serializable;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Thu Jun 19 23:42:32 ICT 2014
 * @Description: Model for table divisi
 * @Revision	:
 */

public class Divisi extends BaseObject implements Serializable  {

	private static final long serialVersionUID = 4205222752833921897L;
	

	//****************** COLOMN FROM TABLE START HERE ******************/
	@NotEmpty
	@Size(max=3)
	public String divisi_kd;

	@NotEmpty
	@Size(max=50)
	public String divisi_nm;


	//****************** COLOMN FROM TABLE END HERE ******************/


	//****************** OTHERS START HERE ******************/

	public String itemId;
	public Upload upload=new Upload();
	//****************** OTHERS END HERE ******************/


	//****************** CONSTRUCTOR START HERE ******************/
	public Divisi(String divisi_kd, String divisi_nm) {
		super();
		this.divisi_kd = divisi_kd;
		this.divisi_nm = divisi_nm;
	}
	
	public Divisi(){
		
	}
	//****************** CONSTRUCTOR END HERE ******************/


	

	//****************** GETTER SETTER START HERE ******************/
	public String getDivisi_kd(){ return divisi_kd; }
	public void setDivisi_kd(String divisi_kd){ this.divisi_kd = divisi_kd; }

	public String getDivisi_nm(){ return divisi_nm; }
	public void setDivisi_nm(String divisi_nm){ this.divisi_nm = divisi_nm; }

	public String getItemId() {return ""+divisi_kd;	}
	public void setItemId(String itemId) {this.itemId = itemId;}

	public Upload getUpload() {	return upload;	}
	public void setUpload(Upload upload) {	this.upload = upload;}
	
	

	//****************** GETTER SETTER END HERE ******************/
	
	

}
