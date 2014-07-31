package com.melawai.ppuc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Sun Jul 06 16:28:27 ICT 2014
 * @Description: Model for table hak_biaya_hist
 * @Revision	:
 */

public class HakBiayaHist extends BaseObject implements Serializable  {
 
	private static final long serialVersionUID = 8791929325906992831L;

	//****************** COLOMN FROM TABLE START HERE ******************/
	
	public Long id;
	
	@NotEmpty
	@Size(max=3)
	public String divisi_kd;

	@NotEmpty
	@Size(max=3)
	public String subdiv_kd;

	@NotEmpty
	@Size(max=3)
	public String dept_kd;

	@NotEmpty
	@Size(max=5)
	public String lok_kd;

	@NotEmpty
	@Size(max=5)
	public String kd_group;

	@NotEmpty
	@Size(max=30)
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
	//****************** OTHERS END HERE ******************/


	//****************** CONSTRUCTOR START HERE ******************/
	public HakBiayaHist(){
		//TODO: standard constructor free to change
	}
	
	
	public HakBiayaHist(String divisi_kd, String subdiv_kd, String dept_kd, String lok_kd, String kd_group, String kd_biaya, Integer f_aktif, Date drtgl, Date sptgl, String user_nonaktif, Date tgl_nonaktif, String jam_nonaktif) {
		super();
		this.divisi_kd = divisi_kd;
		this.subdiv_kd = subdiv_kd;
		this.dept_kd = dept_kd;
		this.lok_kd = lok_kd;
		this.kd_group = kd_group;
		this.kd_biaya = kd_biaya;
		this.f_aktif = f_aktif;
		this.drtgl = drtgl;
		this.sptgl = sptgl;
		this.user_nonaktif = user_nonaktif;
		this.tgl_nonaktif = tgl_nonaktif;
		this.jam_nonaktif = jam_nonaktif;
	}


	//****************** CONSTRUCTOR END HERE ******************/


	//****************** GETTER SETTER START HERE ******************/
	
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	
	public String getDivisi_kd(){ return divisi_kd; }
	public void setDivisi_kd(String divisi_kd){ this.divisi_kd = divisi_kd; }

	public String getSubdiv_kd(){ return subdiv_kd; }
	public void setSubdiv_kd(String subdiv_kd){ this.subdiv_kd = subdiv_kd; }

	public String getDept_kd(){ return dept_kd; }
	public void setDept_kd(String dept_kd){ this.dept_kd = dept_kd; }

	public String getLok_kd(){ return lok_kd; }
	public void setLok_kd(String lok_kd){ this.lok_kd = lok_kd; }

	public String getKd_group(){ return kd_group; }
	public void setKd_group(String kd_group){ this.kd_group = kd_group; }

	public String getKd_biaya(){ return kd_biaya; }
	public void setKd_biaya(String kd_biaya){ this.kd_biaya = kd_biaya; }

	public Integer getF_aktif(){ return f_aktif; }
	public void setF_aktif(Integer f_aktif){ this.f_aktif = f_aktif; }

	public Date getDrtgl(){ return drtgl; }
	public void setDrtgl(Date drtgl){ this.drtgl = drtgl; }

	public Date getSptgl(){ return sptgl; }
	public void setSptgl(Date sptgl){ this.sptgl = sptgl; }

	public String getUser_nonaktif(){ return user_nonaktif; }
	public void setUser_nonaktif(String user_nonaktif){ this.user_nonaktif = user_nonaktif; }

	public Date getTgl_nonaktif(){ return tgl_nonaktif; }
	public void setTgl_nonaktif(Date tgl_nonaktif){ this.tgl_nonaktif = tgl_nonaktif; }

	public String getJam_nonaktif(){ return jam_nonaktif; }
	public void setJam_nonaktif(String jam_nonaktif){ this.jam_nonaktif = jam_nonaktif; }


	public String getItemId() { return ""+id;/*return ""+divisi_kd+"/"+subdiv_kd+"/"+dept_kd+"/"+lok_kd+"/"+kd_group+"/"+kd_biaya;*/	}
	public void setItemId(String itemId) {this.itemId = itemId;}

	

	//****************** GETTER SETTER END HERE ******************/

}
