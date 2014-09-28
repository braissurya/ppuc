package com.melawai.ppuc.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Sun Jul 06 16:28:28 ICT 2014
 * @Description: Model for table kota
 * @Revision	:
 */

public class Kota extends BaseObject implements Serializable  {

	private static final long serialVersionUID = 5485925578667539846L;

	//****************** COLOMN FROM TABLE START HERE ******************/
	@NotEmpty
	@Size(max=100)
	public String propinsi;

	@NotEmpty
	@Size(max=100)
	public String kota;

	//****************** COLOMN FROM TABLE END HERE ******************/


	//****************** OTHERS START HERE ******************/

	public String itemId;
	public Upload upload = new Upload();

	//****************** OTHERS END HERE ******************/


	//****************** CONSTRUCTOR START HERE ******************/
	public Kota(){
		//TODO: standard constructor free to change
	}
	
	

	public Kota(String propinsi, String kota) {
		super();
		this.propinsi = propinsi;
		this.kota = kota;
	}



	//****************** CONSTRUCTOR END HERE ******************/


	//****************** GETTER SETTER START HERE ******************/
	public String getPropinsi(){ return propinsi; }
	public void setPropinsi(String propinsi){ this.propinsi = propinsi; }

	public String getKota(){ return kota; }
	public void setKota(String kota){ this.kota = kota; }

	public String getItemId() {return ""+propinsi+"/"+kota;	}
	public void setItemId(String itemId) {this.itemId = itemId;}

	public Upload getUpload() {
		return upload;
	}

	public void setUpload(Upload upload) {
		this.upload = upload;
	}

	//****************** GETTER SETTER END HERE ******************/

}
