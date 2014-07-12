package com.melawai.ppuc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.melawai.ppuc.utils.Utils;

/**
 * GENERATE BY BraisSpringMVCHelp
 * 
 * @since : Thu Jun 19 23:42:35 ICT 2014
 * @Description: Model for table lokasi
 * @Revision :
 */

public class Lokasi extends BaseObject implements Serializable {

	private static final long serialVersionUID = 7503294106098421175L;

	// ****************** COLOMN FROM TABLE START HERE ******************/
	@NotEmpty
	@Size(max = 5)
	public String lok_kd;

	@NotEmpty
	public String divisi_kd;

	@NotEmpty
	public String subdiv_kd;

	@NotEmpty
	public String dept_kd;

	@NotEmpty
	@Size(max = 50)
	public String lok_nm;

	@NotEmpty
	public String propinsi;

	@NotEmpty
	public String kota;
	
	@NotEmpty
	@Email
	@Size(max = 100)
	public String email;
	
	public Integer f_tutup;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "MM")
	public Date tgl_tutup;

	public Long ctr_ppuc;

	public Long max_ctr_ppuc;

	public Long ctr_batch;

	public Long max_ctr_batch;

	public Long ctr_realisasi;

	public Long max_ctr_realisasi;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(style = "M-")
	public Date curr_ctr_date;

	@Size(max = 50)
	public String user_update;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "MM")
	public Date tgl_update;

	@Size(max = 8)
	public String jam_update;

	@Size(max = 50)
	public String user_create;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "MM")
	public Date tgl_create;

	// ****************** COLOMN FROM TABLE END HERE ******************/

	// ****************** OTHERS START HERE ******************/

	public String itemId;
	public Upload upload = new Upload();

	// ****************** OTHERS END HERE ******************/

	// ****************** CONSTRUCTOR START HERE ******************/
	public Lokasi() {
		// TODO: standard constructor free to change
	}

	// ****************** CONSTRUCTOR END HERE ******************/

	public Lokasi(String lok_kd, String divisi_kd, String subdiv_kd, String dept_kd, String lok_nm, String propinsi, String kota, String email, Date tgl_tutup) {
		super();
		this.lok_kd = lok_kd;
		this.divisi_kd = divisi_kd;
		this.subdiv_kd = subdiv_kd;
		this.dept_kd = dept_kd;
		this.lok_nm = lok_nm;
		this.tgl_tutup = tgl_tutup;
		if(tgl_tutup!=null)f_tutup=1;
		this.email = email;
		this.kota = kota;
		this.propinsi = propinsi;
	}

	// ****************** GETTER SETTER START HERE ******************/
	public String getLok_kd() {
		return lok_kd;
	}

	public void setLok_kd(String lok_kd) {
		this.lok_kd = lok_kd;
	}

	public String getDivisi_kd() {
		return divisi_kd;
	}

	public void setDivisi_kd(String divisi_kd) {
		this.divisi_kd = divisi_kd;
	}

	public String getSubdiv_kd() {
		return subdiv_kd;
	}

	public void setSubdiv_kd(String subdiv_kd) {
		this.subdiv_kd = subdiv_kd;
	}

	public String getDept_kd() {
		return dept_kd;
	}

	public void setDept_kd(String dept_kd) {
		this.dept_kd = dept_kd;
	}

	public String getLok_nm() {
		return lok_nm;
	}

	public void setLok_nm(String lok_nm) {
		this.lok_nm = lok_nm;
	}

	public String getPropinsi() {
		return propinsi;
	}

	public void setPropinsi(String propinsi) {
		this.propinsi = propinsi;
	}

	public String getKota() {
		return kota;
	}

	public void setKota(String kota) {
		this.kota = kota;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getF_tutup() {
		if(f_tutup==null)f_tutup=0;
		return f_tutup;
	}

	public void setF_tutup(Integer f_tutup) {
		if(f_tutup==null)f_tutup=0;
		this.f_tutup = f_tutup;
	}

	public Date getTgl_tutup() {
		return tgl_tutup;
	}

	public void setTgl_tutup(Date tgl_tutup) {
		this.tgl_tutup = tgl_tutup;
	}

	public Long getCtr_ppuc() {
		return ctr_ppuc;
	}

	public void setCtr_ppuc(Long ctr_ppuc) {
		this.ctr_ppuc = ctr_ppuc;
	}

	public Long getMax_ctr_ppuc() {
		return max_ctr_ppuc;
	}

	public void setMax_ctr_ppuc(Long max_ctr_ppuc) {
		this.max_ctr_ppuc = max_ctr_ppuc;
	}

	public Long getCtr_batch() {
		return ctr_batch;
	}

	public void setCtr_batch(Long ctr_batch) {
		this.ctr_batch = ctr_batch;
	}

	public Long getMax_ctr_batch() {
		return max_ctr_batch;
	}

	public void setMax_ctr_batch(Long max_ctr_batch) {
		this.max_ctr_batch = max_ctr_batch;
	}

	public Long getCtr_realisasi() {
		return ctr_realisasi;
	}

	public void setCtr_realisasi(Long ctr_realisasi) {
		this.ctr_realisasi = ctr_realisasi;
	}

	public Long getMax_ctr_realisasi() {
		return max_ctr_realisasi;
	}

	public void setMax_ctr_realisasi(Long max_ctr_realisasi) {
		this.max_ctr_realisasi = max_ctr_realisasi;
	}

	public Date getCurr_ctr_date() {
		return curr_ctr_date;
	}

	public void setCurr_ctr_date(Date curr_ctr_date) {
		this.curr_ctr_date = curr_ctr_date;
	}

	public String getUser_update() {
		return user_update;
	}

	public void setUser_update(String user_update) {
		this.user_update = user_update;
	}

	public Date getTgl_update() {
		return tgl_update;
	}

	public void setTgl_update(Date tgl_update) {
		this.tgl_update = tgl_update;
		if (tgl_update != null) {
			this.jam_update = Utils.convertDateToString(tgl_update, jam_format);
		}
	}

	public String getJam_update() {
		return jam_update;
	}

	public void setJam_update(String jam_update) {
		this.jam_update = jam_update;
	}

	public String getUser_create() {
		return user_create;
	}

	public void setUser_create(String user_create) {
		this.user_create = user_create;
	}

	public Date getTgl_create() {
		return tgl_create;
	}

	public void setTgl_create(Date tgl_create) {
		this.tgl_create = tgl_create;
	}

	public String getItemId() {
		return "" + lok_kd + "/" + dept_kd + "/" + subdiv_kd + "/" + divisi_kd;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public Upload getUpload() {
		return upload;
	}

	public void setUpload(Upload upload) {
		this.upload = upload;
	}

	// ****************** GETTER SETTER END HERE ******************/

}
