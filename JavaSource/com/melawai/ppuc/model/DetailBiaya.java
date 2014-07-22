package com.melawai.ppuc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.melawai.ppuc.utils.Utils;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Thu Jun 19 23:42:31 ICT 2014
 * @Description: Model for table detail_biaya
 * @Revision	:
 */

public class DetailBiaya extends BaseObject implements Serializable  {

	//****************** COLOMN FROM TABLE START HERE ******************/
	@NotEmpty
	@Size(max=30)
	public String kd_biaya;

	@NotEmpty
	@Size(max=5)
	public String kd_group;

	@Size(max=45)
	public String biaya_description;

	public Integer f_putus;

	public Integer f_used;

	@Size(max=50)
	public String acc_no;

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
	public DetailBiaya(){
		//TODO: standard constructor free to change
	}

	//****************** CONSTRUCTOR END HERE ******************/


	//****************** GETTER SETTER START HERE ******************/
	public String getKd_biaya(){ return kd_biaya; }
	public void setKd_biaya(String kd_biaya){ this.kd_biaya = kd_biaya; }

	public String getKd_group(){ return kd_group; }
	public void setKd_group(String kd_group){ this.kd_group = kd_group; }

	public String getBiaya_description(){ return biaya_description; }
	public void setBiaya_description(String biaya_description){ this.biaya_description = biaya_description; }

	public Integer getF_putus() {
		if(f_putus==null)f_putus=0;
		return f_putus;
	}

	public void setF_putus(Integer f_putus) {
		if(f_putus==null)f_putus=0;
		this.f_putus = f_putus;
	}

	public Integer getF_used() {
		if(f_used==null)f_used=0;
		return f_used;
	}

	public void setF_used(Integer f_used) {
		if(f_used==null)f_used=0;
		this.f_used = f_used;
	}

	public String getAcc_no(){ return acc_no; }
	public void setAcc_no(String acc_no){ this.acc_no = acc_no; }

	public String getUser_create(){ return user_create; }
	public void setUser_create(String user_create){ this.user_create = user_create; }

	public Date getTgl_create(){ return tgl_create; }
	public void setTgl_create(Date tgl_create){
		this.tgl_create = tgl_create; 
		if (tgl_create != null) {
			this.jam_create = Utils.convertDateToString(tgl_create, jam_format);
		}
	}

	public String getJam_create(){ return jam_create; }
	public void setJam_create(String jam_create){ this.jam_create = jam_create; }


	public String getItemId() {return ""+kd_biaya;	}
	public void setItemId(String itemId) {this.itemId = itemId;}

	//****************** GETTER SETTER END HERE ******************/

}
