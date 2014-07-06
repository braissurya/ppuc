package com.melawai.ppuc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.melawai.ppuc.model.BaseObject;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Sun Jul 06 16:28:28 ICT 2014
 * @Description: Model for table kota
 * @Revision	:
 */

public class Kota extends BaseObject implements Serializable  {

	//****************** COLOMN FROM TABLE START HERE ******************/
	@NotNull
	@Size(max=100)
	public String propinsi;

	@NotNull
	@Size(max=100)
	public String kota;

	@Size(max=50)
	public String user_create;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style="MM")
	public Date tgl_create;

	@Size(max=8)
	public String jam_create;

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

	public String getUser_create(){ return user_create; }
	public void setUser_create(String user_create){ this.user_create = user_create; }

	public Date getTgl_create(){ return tgl_create; }
	public void setTgl_create(Date tgl_create){ this.tgl_create = tgl_create; }

	public String getJam_create(){ return jam_create; }
	public void setJam_create(String jam_create){ this.jam_create = jam_create; }


	public String getItemId() {return ""+propinsi+"/"+kota;	}
	public void setItemId(String itemId) {this.itemId = itemId;}

	//****************** GETTER SETTER END HERE ******************/

}
