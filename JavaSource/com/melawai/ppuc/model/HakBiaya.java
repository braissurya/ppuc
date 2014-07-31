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
 * @since		: Thu Jun 19 23:42:34 ICT 2014
 * @Description: Model for table hak_biaya
 * @Revision	:
 */

public class HakBiaya extends BaseObject implements Serializable  {

	private static final long serialVersionUID = -8631836770378561629L;

	//****************** COLOMN FROM TABLE START HERE ******************/
	public Long id;
	
	@NotEmpty
	public String divisi_kd;

	@NotEmpty
	public String subdiv_kd;

	@NotEmpty
	public String dept_kd;

	@NotEmpty
	public String lok_kd;

	@NotEmpty
	public String kd_group;

	@NotEmpty
	public String kd_biaya;

	public Integer f_aktif;

	@NotNull
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
	public Integer typeInput;
	public String gl;
	public String pr;
	public String kt;
	public String lk;
	public String gb;
	public String kb;
	public Integer aktif;
	
	public Lokasi lokasi;
	
	public Integer filter_faktif;
	public String isActive;
	public String revActive;
	//****************** OTHERS END HERE ******************/

	
	//****************** CONSTRUCTOR START HERE ******************/
	public HakBiaya(){
		//TODO: standard constructor free to change
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

	public Integer getF_aktif() {
		if (f_aktif == null) f_aktif = 0;
		return f_aktif;
	}

	public void setF_aktif(Integer f_aktif) {
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


	public String getItemId() {return ""+id;/*return ""+divisi_kd+"/"+subdiv_kd+"/"+dept_kd+"/"+lok_kd+"/"+kd_group+"/"+kd_biaya;*/	}
	public void setItemId(String itemId) {this.itemId = itemId;}

	public Integer getTypeInput() {
		return typeInput;
	}

	public void setTypeInput(Integer typeInput) {
		this.typeInput = typeInput;
	}

	public String getGl() {
		return gl;
	}

	public void setGl(String gl) {
		this.gl = gl;
	}

	public String getPr() {
		return pr;
	}

	public void setPr(String pr) {
		this.pr = pr;
	}

	public String getKt() {
		return kt;
	}

	public void setKt(String kt) {
		this.kt = kt;
	}

	public String getLk() {
		return lk;
	}

	public void setLk(String lk) {
		this.lk = lk;
	}

	public String getGb() {
		return gb;
	}

	public void setGb(String gb) {
		this.gb = gb;
	}

	public String getKb() {
		return kb;
	}

	public void setKb(String kb) {
		this.kb = kb;
	}

	public Integer getAktif() {
		return aktif;
	}

	public void setAktif(Integer aktif) {
		this.aktif = aktif;
	}

	public Lokasi getLokasi() {
		return lokasi;
	}

	public void setLokasi(Lokasi lokasi) {
		this.lokasi = lokasi;
	}

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
	/*<table:table data="${hakbiayaList}" id="l_com_melawai_ppuc_model_HakBiaya"  typeIdFieldName="itemId"  path="/master/hakbiaya">
			<table:column id="c_com_melawai_ppuc_model_hakbiaya_propinsi" property="lokasi.propinsi" label="Propinsi" />
			<table:column id="c_com_melawai_ppuc_model_hakbiaya_kota" property="lokasi.kota" label="Kota" />
			<table:column id="c_com_melawai_ppuc_model_hakbiaya_lok_kd" property="lokasi.lok_nm" label="Lok Kd" />
			<table:column id="c_com_melawai_ppuc_model_hakbiaya_kd_group" property="kd_group" label="Kd Group" />
			<table:column id="c_com_melawai_ppuc_model_hakbiaya_kd_biaya" property="kd_biaya" label="Kd Biaya" />
			<table:column id="c_com_melawai_ppuc_model_hakbiaya_user_create" property="user_create" label="User Create" />
			<table:column id="c_com_melawai_ppuc_model_hakbiaya_tgl_create" property="tgl_create" label="Tgl Create" date="true" dateTimePattern="${hakbiaya_tgl_create_date_format}" />
			<table:column id="c_com_melawai_ppuc_model_hakbiaya_user_nonaktif" property="user_nonaktif" label="User Nonaktif" />
			<table:column id="c_com_melawai_ppuc_model_hakbiaya_tgl_nonaktif" property="tgl_nonaktif" label="Tgl Nonaktif"  date="true" dateTimePattern="${hakbiaya_tgl_nonaktif_date_format}" />
			<table:column id="c_com_melawai_ppuc_model_hakbiaya_aktifnonaktif" label=" " custom="true" property="revActive">
				<spring:url value="/master/hakbiaya/aktifnonaktif/#itemID" var="aktif_form_url" />
				<spring:url value="/resources/images/icons/#prop.png" var="aktif_image_url" />
				<c:set value="Click To #propd Hak Biaya" var="label_aktif" />
				<a href="javascript:showDialog('#prop','#itemID')" alt="${label_aktif}" title="${label_aktif}"> <img alt="${label_aktif}" class="image" src="${aktif_image_url}" title="${label_aktif}" />
				</a>
			</table:column>
		</table:table>*/
}
