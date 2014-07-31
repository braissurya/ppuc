package com.melawai.ppuc.model;

import java.io.Serializable;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Thu Jun 19 23:42:38 ICT 2014
 * @Description: Model for table subdivisi
 * @Revision	:
 */

public class Subdivisi extends BaseObject implements Serializable  {
	
	private static final long serialVersionUID = -4358864468672803061L;

	//****************** COLOMN FROM TABLE START HERE ******************/
	@NotEmpty
	@Size(max=3)
	public String subdiv_kd;

	@NotEmpty
	public String divisi_kd;

	@NotEmpty
	@Size(max=50)
	public String subdiv_nm;

	//****************** COLOMN FROM TABLE END HERE ******************/


	//****************** OTHERS START HERE ******************/

	public String itemId;
	public Upload upload=new Upload();
	//****************** OTHERS END HERE ******************/


	//****************** CONSTRUCTOR START HERE ******************/
	public Subdivisi(){
		//TODO: standard constructor free to change
	}
	
	

	public Subdivisi( String divisi_kd, String subdiv_kd,String subdiv_nm) {
		super();
		this.subdiv_kd = subdiv_kd;
		this.divisi_kd = divisi_kd;
		this.subdiv_nm = subdiv_nm;
	}



	//****************** CONSTRUCTOR END HERE ******************/


	//****************** GETTER SETTER START HERE ******************/
	public String getSubdiv_kd(){ return subdiv_kd; }
	public void setSubdiv_kd(String subdiv_kd){ this.subdiv_kd = subdiv_kd; }

	public String getDivisi_kd(){ return divisi_kd; }
	public void setDivisi_kd(String divisi_kd){ this.divisi_kd = divisi_kd; }

	public String getSubdiv_nm(){ return subdiv_nm; }
	public void setSubdiv_nm(String subdiv_nm){ this.subdiv_nm = subdiv_nm; }

	public String getItemId() {return ""+subdiv_kd+"/"+divisi_kd;	}
	public void setItemId(String itemId) {this.itemId = itemId;}

	public Upload getUpload() {	return upload;}
	public void setUpload(Upload upload) {this.upload = upload;	}
	
	

	//****************** GETTER SETTER END HERE ******************/

}
