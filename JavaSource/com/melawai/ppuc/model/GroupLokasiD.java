package com.melawai.ppuc.model;

import java.io.Serializable;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Sun Jul 06 16:28:25 ICT 2014
 * @Description: Model for table group_lokasi_d
 * @Revision	:
 */

public class GroupLokasiD extends BaseObject implements Serializable  {

	private static final long serialVersionUID = -1061238427154930837L;

	//****************** COLOMN FROM TABLE START HERE ******************/
	@NotEmpty
	@Size(max=3)
	public String divisi_kd;

	@NotEmpty
	@Size(max=3)
	public String subdiv_kd;

	@NotEmpty
	@Size(max=5)
	public String group_lok;

	@NotEmpty
	@Size(max=5)
	public String lok_kd;

	
	//****************** COLOMN FROM TABLE END HERE ******************/


	//****************** OTHERS START HERE ******************/

	public String itemId;
	
	public Lokasi lokasi=new Lokasi();
	
	public Integer noUrut;
	//****************** OTHERS END HERE ******************/


	//****************** CONSTRUCTOR START HERE ******************/
	public GroupLokasiD(){
	}
	
	

	public GroupLokasiD(Integer noUrut,String lok_kd,  String lok_nm, String propinsi,String kota, String email) {
		super();
		this.noUrut = noUrut;
		this.lok_kd = lok_kd;
		this.lokasi.lok_nm=lok_nm;
		this.lokasi.propinsi=propinsi;
		this.lokasi.kota=kota;
		this.lokasi.email=email;
		
	}

	


	public GroupLokasiD(String divisi_kd, String subdiv_kd, String group_lok, String lok_kd) {
		super();
		this.divisi_kd = divisi_kd;
		this.subdiv_kd = subdiv_kd;
		this.group_lok = group_lok;
		this.lok_kd = lok_kd;
	}



	//****************** CONSTRUCTOR END HERE ******************/


	//****************** GETTER SETTER START HERE ******************/
	public String getDivisi_kd(){ return divisi_kd; }
	public void setDivisi_kd(String divisi_kd){ this.divisi_kd = divisi_kd; }

	public String getSubdiv_kd(){ return subdiv_kd; }
	public void setSubdiv_kd(String subdiv_kd){ this.subdiv_kd = subdiv_kd; }

	public String getGroup_lok(){ return group_lok; }
	public void setGroup_lok(String group_lok){ this.group_lok = group_lok; }

	public String getLok_kd(){ return lok_kd; }
	public void setLok_kd(String lok_kd){ this.lok_kd = lok_kd; }


	public Lokasi getLokasi() {
		return lokasi;
	}



	public void setLokasi(Lokasi lokasi) {
		this.lokasi = lokasi;
	}



	public Integer getNoUrut() {
		return noUrut;
	}



	public void setNoUrut(Integer noUrut) {
		this.noUrut = noUrut;
	}



	public String getItemId() {return ""+divisi_kd+"/"+subdiv_kd+"/"+group_lok+"/"+lok_kd;	}
	public void setItemId(String itemId) {this.itemId = itemId;}

	//****************** GETTER SETTER END HERE ******************/

}
