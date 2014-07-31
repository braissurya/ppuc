package com.melawai.ppuc.model;

import java.io.Serializable;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Thu Jun 19 23:42:31 ICT 2014
 * @Description: Model for table departmen
 * @Revision	:
 */

public class Departmen extends BaseObject implements Serializable  {

	private static final long serialVersionUID = 4539182142457484816L;

	//****************** COLOMN FROM TABLE START HERE ******************/
	@NotEmpty
	@Size(max=3)
	public String dept_kd;

	@NotEmpty
	public String divisi_kd;

	@NotEmpty
	public String subdiv_kd;

	@NotEmpty
	@Size(max=50)
	public String dept_nm;

	//****************** COLOMN FROM TABLE END HERE ******************/


	//****************** OTHERS START HERE ******************/

	public String itemId;
	public Upload upload=new Upload();
	//****************** OTHERS END HERE ******************/


	//****************** CONSTRUCTOR START HERE ******************/
	public Departmen(){
		//TODO: standard constructor free to change
	}
	
	

	public Departmen( String divisi_kd, String subdiv_kd,String dept_kd, String dept_nm) {
		super();
		this.dept_kd = dept_kd;
		this.divisi_kd = divisi_kd;
		this.subdiv_kd = subdiv_kd;
		this.dept_nm = dept_nm;
	}



	//****************** CONSTRUCTOR END HERE ******************/


	//****************** GETTER SETTER START HERE ******************/
	public String getDept_kd(){ return dept_kd; }
	public void setDept_kd(String dept_kd){ this.dept_kd = dept_kd; }

	public String getDivisi_kd(){ return divisi_kd; }
	public void setDivisi_kd(String divisi_kd){ this.divisi_kd = divisi_kd; }

	public String getSubdiv_kd(){ return subdiv_kd; }
	public void setSubdiv_kd(String subdiv_kd){ this.subdiv_kd = subdiv_kd; }

	public String getDept_nm(){ return dept_nm; }
	public void setDept_nm(String dept_nm){ this.dept_nm = dept_nm; }

	public String getItemId() {return ""+dept_kd+"/"+subdiv_kd+"/"+divisi_kd;	}
	public void setItemId(String itemId) {this.itemId = itemId;}

	public Upload getUpload() {	return upload;}
	public void setUpload(Upload upload) {this.upload = upload;	}
	
	

	//****************** GETTER SETTER END HERE ******************/

}
