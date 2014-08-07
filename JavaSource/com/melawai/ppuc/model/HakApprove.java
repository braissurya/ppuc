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
 * @since		: Thu Jun 19 23:42:33 ICT 2014
 * @Description: Model for table hak_approve
 * @Revision	:
 */

public class HakApprove extends BaseObject implements Serializable  {

	private static final long serialVersionUID = 3917846671656716785L;

	//****************** COLOMN FROM TABLE START HERE ******************/
	@NotEmpty
	@Size(max=50)
	public String user_id;

	@NotEmpty
	public String divisi_kd;

	@NotEmpty
	public String subdiv_kd;

	@NotEmpty
	public String dept_kd;

	@NotEmpty
	public String kd_group;

	@NotEmpty
	public String kd_biaya;

	public Integer f_aktif;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(style="M-")
	public Date drtgl;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(style="M-")
	public Date sptgl;

	@Size(max=50)
	public String user_nonaktif;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style="MM")
	public Date tgl_nonaktif;

	@Size(max=8)
	public String jam_nonaktif;

	//****************** COLOMN FROM TABLE END HERE ******************/


	//****************** OTHERS START HERE ******************/

	public String itemId;
	public Integer filter_faktif;
	public String isActive;
	public String revActive;
	//****************** OTHERS END HERE ******************/


	//****************** CONSTRUCTOR START HERE ******************/
	public HakApprove(){
		//TODO: standard constructor free to change
	}

	//****************** CONSTRUCTOR END HERE ******************/


	//****************** GETTER SETTER START HERE ******************/
	public String getUser_id(){ return user_id; }
	public void setUser_id(String user_id){ this.user_id = user_id; }

	public String getDivisi_kd(){ return divisi_kd; }
	public void setDivisi_kd(String divisi_kd){ this.divisi_kd = divisi_kd; }

	public String getSubdiv_kd(){ return subdiv_kd; }
	public void setSubdiv_kd(String subdiv_kd){ this.subdiv_kd = subdiv_kd; }

	public String getDept_kd(){ return dept_kd; }
	public void setDept_kd(String dept_kd){ this.dept_kd = dept_kd; }

	public String getKd_group(){ return kd_group; }
	public void setKd_group(String kd_group){ this.kd_group = kd_group; }

	public String getKd_biaya(){ return kd_biaya; }
	public void setKd_biaya(String kd_biaya){ this.kd_biaya = kd_biaya; }

	public Integer getF_aktif(){ 
		if (f_aktif == null) f_aktif = 0;
		return f_aktif;
	}
	public void setF_aktif(Integer f_aktif){ 
		if(f_aktif==null)f_aktif=0;
		this.f_aktif = f_aktif;
		if(f_aktif==0){
			revActive="Active";
			isActive="Non-Active";
		}else{
			revActive="Non-Active";
			isActive="Active";
		} 
	}

	public Date getDrtgl(){ return drtgl; }
	public void setDrtgl(Date drtgl){ this.drtgl = drtgl; }

	public Date getSptgl(){ return sptgl; }
	public void setSptgl(Date sptgl){ this.sptgl = sptgl; }

	public String getUser_nonaktif(){ return user_nonaktif; }
	public void setUser_nonaktif(String user_nonaktif){ this.user_nonaktif = user_nonaktif; }

	public Date getTgl_nonaktif(){ return tgl_nonaktif; }
	public void setTgl_nonaktif(Date tgl_nonaktif){
		this.tgl_nonaktif = tgl_nonaktif; 
		if (tgl_nonaktif != null) {
			this.jam_nonaktif = Utils.convertDateToString(tgl_nonaktif, jam_format);
		}
	}

	public String getJam_nonaktif(){ return jam_nonaktif; }
	public void setJam_nonaktif(String jam_nonaktif){ this.jam_nonaktif = jam_nonaktif; }


	public String getItemId() {return ""+user_id+"/"+divisi_kd+"/"+subdiv_kd+"/"+dept_kd+"/"+kd_group+"/"+kd_biaya;	}
	public void setItemId(String itemId) {this.itemId = itemId;}

	public Integer getFilter_faktif() {
		return filter_faktif;
	}

	public void setFilter_faktif(Integer filter_faktif) {
		this.filter_faktif = filter_faktif;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getRevActive() {
		return revActive;
	}

	public void setRevActive(String revActive) {
		this.revActive = revActive;
	}

	//****************** GETTER SETTER END HERE ******************/

}
