package com.melawai.ppuc.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Sun Jul 06 16:28:28 ICT 2014
 * @Description: Model for table kota
 * @Revision	:
 */

public class Kota extends BaseObject implements Serializable  {

	private static final long serialVersionUID = 5485925578667539846L;

	//****************** COLOMN FROM TABLE START HERE ******************/
	@NotNull
	@Size(max=100)
	public String propinsi;

	@NotNull
	@Size(max=100)
	public String kota;

	//****************** COLOMN FROM TABLE END HERE ******************/


	//****************** OTHERS START HERE ******************/

	public String itemId;
	//****************** OTHERS END HERE ******************/


	//****************** CONSTRUCTOR START HERE ******************/
	public Kota(){
		//TODO: standard constructor free to change
	}

	//****************** CONSTRUCTOR END HERE ******************/


	//****************** GETTER SETTER START HERE ******************/
	public String getPropinsi(){ return propinsi; }
	public void setPropinsi(String propinsi){ this.propinsi = propinsi; }

	public String getKota(){ return kota; }
	public void setKota(String kota){ this.kota = kota; }

	public String getItemId() {return ""+propinsi+"/"+kota;	}
	public void setItemId(String itemId) {this.itemId = itemId;}

	//****************** GETTER SETTER END HERE ******************/

}
