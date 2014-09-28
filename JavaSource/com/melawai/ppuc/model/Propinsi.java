package com.melawai.ppuc.model;

import java.io.Serializable;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Sun Jul 06 16:28:31 ICT 2014
 * @Description: Model for table propinsi
 * @Revision	:
 */

public class Propinsi extends BaseObject implements Serializable  {

	private static final long serialVersionUID = -7526172424060671013L;

	//****************** COLOMN FROM TABLE START HERE ******************/
	@NotEmpty
	@Size(max=100)
	public String propinsi;


	//****************** COLOMN FROM TABLE END HERE ******************/


	//****************** OTHERS START HERE ******************/

	public String itemId;
	public Upload upload = new Upload();

	//****************** OTHERS END HERE ******************/


	//****************** CONSTRUCTOR START HERE ******************/
	public Propinsi(){
		//TODO: standard constructor free to change
	}
	
	

	public Propinsi(String propinsi) {
		super();
		this.propinsi = propinsi;
	}



	//****************** CONSTRUCTOR END HERE ******************/


	//****************** GETTER SETTER START HERE ******************/
	public String getPropinsi(){ return propinsi; }
	public void setPropinsi(String propinsi){ this.propinsi = propinsi; }

	public String getItemId() {return ""+propinsi;	}
	public void setItemId(String itemId) {this.itemId = itemId;}

	public Upload getUpload() {
		return upload;
	}

	public void setUpload(Upload upload) {
		this.upload = upload;
	}

	//****************** GETTER SETTER END HERE ******************/

}
