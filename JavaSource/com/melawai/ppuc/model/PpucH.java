package com.melawai.ppuc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.melawai.ppuc.utils.Utils;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Thu Jun 19 23:42:37 ICT 2014
 * @Description: Model for table ppuc_h
 * @Revision	:
 */

public class PpucH extends BaseObject implements Serializable  {

	private static final long serialVersionUID = 5383311198787488870L;

	//****************** COLOMN FROM TABLE START HERE ******************/
	@NotEmpty
	public String divisi_kd;

	@NotEmpty
	public String subdiv_kd;

	@NotEmpty
	public String dept_kd;

	@NotEmpty
	public String lok_kd;

	public String no_ppuc;

	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(style="M-")
	public Date tgl_ppuc;

	public String no_batch;

	@Size(max=50)
	public String user_confirm;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style="MM")
	public Date tgl_confirm;

	@Size(max=8)
	public String jam_confirm;

	@Size(max=25)
	public String hp_asal_create;

	@Size(max=25)
	public String hp_tujuan_create;

	@Size(max=100)
	public String email_asal_create;

	@Size(max=100)
	public String email_tujuan_create;

	@Size(max=5)
	public String divisi_kd_apprv;

	@Size(max=5)
	public String subdiv_kd_apprv;

	@Size(max=5)
	public String dept_kd_apprv;

	@Size(max=50)
	public String user_approve;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style="MM")
	public Date tgl_approve;

	@Size(max=8)
	public String jam_approve;

	public Long f_approval;

	@Size(max=25)
	public String hp_asal_approve;

	@Size(max=25)
	public String hp_tujuan_approve;

	@Size(max=100)
	public String email_asal_approve;

	@Size(max=100)
	public String email_tujuan_approve;

	@Size(max=25)
	public String no_realisasi;

	@Size(max=50)
	public String user_realisasi;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style="MM")
	public Date tgl_realisasi;

	@Size(max=8)
	public String jam_realisasi;

	@Size(max=50)
	public String user_conf_real;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style="MM")
	public Date tgl_conf_real;

	@Size(max=8)
	public String jam_conf_real;

	@Size(max=50)
	public String user_conf_oc;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style="MM")
	public Date tgl_conf_oc;

	@Size(max=8)
	public String jam_conf_oc;

	@Size(max=25)
	public String hp_asal_conf_oc;

	@Size(max=25)
	public String hp_tujuan_conf_oc;

	@Size(max=100)
	public String email_asal_conf_oc;

	@Size(max=100)
	public String email_tujuan_conf_oc;

	public Long f_batal;

	@Size(max=50)
	public String user_batal;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style="MM")
	public Date tgl_batal;

	@Size(max=8)
	public String jam_batal;

	@Size(max=65535)
	public String alasan_batal;

	@Size(max=25)
	public String hp_asal_batal;

	@Size(max=25)
	public String hp_tujuan_batal;

	@Size(max=100)
	public String email_asal_batal;

	@Size(max=100)
	public String email_tujuan_batal;
	
	public Integer posisi;

	//****************** COLOMN FROM TABLE END HERE ******************/


	//****************** OTHERS START HERE ******************/

	public String itemId;
	
	public List<PpucD> ppucds = new ArrayList<PpucD>();
	public List<PpucH> ppuchs= new ArrayList<PpucH>();
	
	public PpucD ppucd;
	public Divisi divisi;
	public Divisi divisi_app;
	public Subdivisi subdivisi;
	public Subdivisi subdivisi_app;
	public Departmen departmen;
	public Departmen departmen_app;
	public Lokasi lokasi;
	public User user_d;
	public User user_conf_d;
	public User user_app_d;
	public User user_real_d;
	public User user_conf_real_d;
	public User user_conf_oc_d;
	
	
	public String kd_group;
	public String kd_biaya;
	
	public String divisi_nm;
	public String subdiv_nm;
	public String dept_nm;
	public String lok_nm;
	
	public String posisi_desc;
	public String posisiGroup;
	public Integer posisi_min;
	
	@NotEmpty(message="At least one Detail Biaya is required")
	public Integer[] idx;
	
	//****************** OTHERS END HERE ******************/


	//****************** CONSTRUCTOR START HERE ******************/
	public PpucH(){
		//TODO: standard constructor free to change
	}
	
	

	public PpucH(String divisi_kd, String subdiv_kd, String dept_kd, String lok_kd, String no_ppuc, Date tgl_ppuc, String no_batch) {
		super();
		this.divisi_kd = divisi_kd;
		this.subdiv_kd = subdiv_kd;
		this.dept_kd = dept_kd;
		this.lok_kd = lok_kd;
		this.no_ppuc = no_ppuc;
		this.tgl_ppuc = tgl_ppuc;
		this.no_batch = no_batch;
	}



	//****************** CONSTRUCTOR END HERE ******************/


	//****************** GETTER SETTER START HERE ******************/
	public String getDivisi_kd(){ return divisi_kd; }
	public void setDivisi_kd(String divisi_kd){ this.divisi_kd = divisi_kd; }

	public String getSubdiv_kd(){ return subdiv_kd; }
	public void setSubdiv_kd(String subdiv_kd){ this.subdiv_kd = subdiv_kd; }

	public String getDept_kd(){ return dept_kd; }
	public void setDept_kd(String dept_kd){ this.dept_kd = dept_kd; }

	public String getLok_kd(){ return lok_kd; }
	public void setLok_kd(String lok_kd){ this.lok_kd = lok_kd; }

	public String getNo_ppuc(){ return no_ppuc; }
	public void setNo_ppuc(String no_ppuc){ this.no_ppuc = no_ppuc; }

	public Date getTgl_ppuc(){ return tgl_ppuc; }
	public void setTgl_ppuc(Date tgl_ppuc){ this.tgl_ppuc = tgl_ppuc; }

	public String getNo_batch(){ return no_batch; }
	public void setNo_batch(String no_batch){ this.no_batch = no_batch; }

	public String getUser_confirm(){ return user_confirm; }
	public void setUser_confirm(String user_confirm){ this.user_confirm = user_confirm; }

	public Date getTgl_confirm(){ return tgl_confirm; }
	public void setTgl_confirm(Date tgl_confirm){ 
		this.tgl_confirm = tgl_confirm; 
		if (tgl_confirm != null) {
			this.jam_confirm = Utils.convertDateToString(tgl_confirm, jam_format);
		}
	}

	public String getJam_confirm(){ return jam_confirm; }
	public void setJam_confirm(String jam_confirm){ this.jam_confirm = jam_confirm; }

	public String getHp_asal_create(){ return hp_asal_create; }
	public void setHp_asal_create(String hp_asal_create){ this.hp_asal_create = hp_asal_create; }

	public String getHp_tujuan_create(){ return hp_tujuan_create; }
	public void setHp_tujuan_create(String hp_tujuan_create){ this.hp_tujuan_create = hp_tujuan_create; }

	public String getEmail_asal_create(){ return email_asal_create; }
	public void setEmail_asal_create(String email_asal_create){ this.email_asal_create = email_asal_create; }

	public String getEmail_tujuan_create(){ return email_tujuan_create; }
	public void setEmail_tujuan_create(String email_tujuan_create){ this.email_tujuan_create = email_tujuan_create; }

	public String getDivisi_kd_apprv(){ return divisi_kd_apprv; }
	public void setDivisi_kd_apprv(String divisi_kd_apprv){ this.divisi_kd_apprv = divisi_kd_apprv; }

	public String getSubdiv_kd_apprv(){ return subdiv_kd_apprv; }
	public void setSubdiv_kd_apprv(String subdiv_kd_apprv){ this.subdiv_kd_apprv = subdiv_kd_apprv; }

	public String getDept_kd_apprv(){ return dept_kd_apprv; }
	public void setDept_kd_apprv(String dept_kd_apprv){ this.dept_kd_apprv = dept_kd_apprv; }

	public String getUser_approve(){ return user_approve; }
	public void setUser_approve(String user_approve){ this.user_approve = user_approve; }

	public Date getTgl_approve(){ return tgl_approve; }
	public void setTgl_approve(Date tgl_approve){ 
		this.tgl_approve = tgl_approve;
		if (tgl_approve != null) {
			this.jam_approve = Utils.convertDateToString(tgl_approve, jam_format);
		}
	}

	public String getJam_approve(){ return jam_approve; }
	public void setJam_approve(String jam_approve){ this.jam_approve = jam_approve; }

	public Long getF_approval(){ return f_approval; }
	public void setF_approval(Long f_approval){ this.f_approval = f_approval; }

	public String getHp_asal_approve(){ return hp_asal_approve; }
	public void setHp_asal_approve(String hp_asal_approve){ this.hp_asal_approve = hp_asal_approve; }

	public String getHp_tujuan_approve(){ return hp_tujuan_approve; }
	public void setHp_tujuan_approve(String hp_tujuan_approve){ this.hp_tujuan_approve = hp_tujuan_approve; }

	public String getEmail_asal_approve(){ return email_asal_approve; }
	public void setEmail_asal_approve(String email_asal_approve){ this.email_asal_approve = email_asal_approve; }

	public String getEmail_tujuan_approve(){ return email_tujuan_approve; }
	public void setEmail_tujuan_approve(String email_tujuan_approve){ this.email_tujuan_approve = email_tujuan_approve; }

	public String getNo_realisasi(){ return no_realisasi; }
	public void setNo_realisasi(String no_realisasi){ this.no_realisasi = no_realisasi; }

	public String getUser_realisasi(){ return user_realisasi; }
	public void setUser_realisasi(String user_realisasi){ this.user_realisasi = user_realisasi; }

	public Date getTgl_realisasi(){ return tgl_realisasi; }
	public void setTgl_realisasi(Date tgl_realisasi){ 
		this.tgl_realisasi = tgl_realisasi; 
		if (tgl_realisasi!= null) {
			this.jam_realisasi = Utils.convertDateToString(tgl_realisasi, jam_format);
		}
	}

	public String getJam_realisasi(){ return jam_realisasi; }
	public void setJam_realisasi(String jam_realisasi){ this.jam_realisasi = jam_realisasi; }

	public String getUser_conf_real(){ return user_conf_real; }
	public void setUser_conf_real(String user_conf_real){ this.user_conf_real = user_conf_real; }

	public Date getTgl_conf_real(){ return tgl_conf_real; }
	public void setTgl_conf_real(Date tgl_conf_real){ 
		this.tgl_conf_real = tgl_conf_real; 
		if (tgl_conf_real != null) {
			this.jam_conf_real = Utils.convertDateToString(tgl_conf_real, jam_format);
		}
	}

	public String getJam_conf_real(){ return jam_conf_real; }
	public void setJam_conf_real(String jam_conf_real){ this.jam_conf_real = jam_conf_real; }

	public String getUser_conf_oc(){ return user_conf_oc; }
	public void setUser_conf_oc(String user_conf_oc){ this.user_conf_oc = user_conf_oc; }

	public Date getTgl_conf_oc(){ return tgl_conf_oc; }
	public void setTgl_conf_oc(Date tgl_conf_oc){ 
		this.tgl_conf_oc = tgl_conf_oc; 
		if (tgl_conf_oc != null) {
			this.jam_conf_oc = Utils.convertDateToString(tgl_conf_oc, jam_format);
		}
	}

	public String getJam_conf_oc(){ return jam_conf_oc; }
	public void setJam_conf_oc(String jam_conf_oc){ this.jam_conf_oc = jam_conf_oc; }

	public String getHp_asal_conf_oc(){ return hp_asal_conf_oc; }
	public void setHp_asal_conf_oc(String hp_asal_conf_oc){ this.hp_asal_conf_oc = hp_asal_conf_oc; }

	public String getHp_tujuan_conf_oc(){ return hp_tujuan_conf_oc; }
	public void setHp_tujuan_conf_oc(String hp_tujuan_conf_oc){ this.hp_tujuan_conf_oc = hp_tujuan_conf_oc; }

	public String getEmail_asal_conf_oc(){ return email_asal_conf_oc; }
	public void setEmail_asal_conf_oc(String email_asal_conf_oc){ this.email_asal_conf_oc = email_asal_conf_oc; }

	public String getEmail_tujuan_conf_oc(){ return email_tujuan_conf_oc; }
	public void setEmail_tujuan_conf_oc(String email_tujuan_conf_oc){ this.email_tujuan_conf_oc = email_tujuan_conf_oc; }

	public Long getF_batal(){ return f_batal; }
	public void setF_batal(Long f_batal){ this.f_batal = f_batal; }

	public String getUser_batal(){ return user_batal; }
	public void setUser_batal(String user_batal){ this.user_batal = user_batal; }

	public Date getTgl_batal(){ return tgl_batal; }
	public void setTgl_batal(Date tgl_batal){ 
		this.tgl_batal = tgl_batal; 
		if (tgl_batal != null) {
			this.jam_batal = Utils.convertDateToString(tgl_batal, jam_format);
		}
	}

	public String getJam_batal(){ return jam_batal; }
	public void setJam_batal(String jam_batal){ this.jam_batal = jam_batal; }

	public String getAlasan_batal(){ return alasan_batal; }
	public void setAlasan_batal(String alasan_batal){ this.alasan_batal = alasan_batal; }

	public String getHp_asal_batal(){ return hp_asal_batal; }
	public void setHp_asal_batal(String hp_asal_batal){ this.hp_asal_batal = hp_asal_batal; }

	public String getHp_tujuan_batal(){ return hp_tujuan_batal; }
	public void setHp_tujuan_batal(String hp_tujuan_batal){ this.hp_tujuan_batal = hp_tujuan_batal; }

	public String getEmail_asal_batal(){ return email_asal_batal; }
	public void setEmail_asal_batal(String email_asal_batal){ this.email_asal_batal = email_asal_batal; }

	public String getEmail_tujuan_batal(){ return email_tujuan_batal; }
	public void setEmail_tujuan_batal(String email_tujuan_batal){ this.email_tujuan_batal = email_tujuan_batal; }


	public String getItemId() {return ""+divisi_kd+"/"+subdiv_kd+"/"+dept_kd+"/"+lok_kd+"/"+no_ppuc+"/"+tgl_ppuc;	}
	public void setItemId(String itemId) {this.itemId = itemId;}

	public PpucD getPpucd() {
		return ppucd;
	}

	public void setPpucd(PpucD ppucd) {
		this.ppucd = ppucd;
	}

	public Divisi getDivisi() {
		return divisi;
	}

	public void setDivisi(Divisi divisi) {
		this.divisi = divisi;
	}

	public Divisi getDivisi_app() {
		return divisi_app;
	}

	public void setDivisi_app(Divisi divisi_app) {
		this.divisi_app = divisi_app;
	}

	public Subdivisi getSubdivisi() {
		return subdivisi;
	}

	public void setSubdivisi(Subdivisi subdivisi) {
		this.subdivisi = subdivisi;
	}

	public Subdivisi getSubdivisi_app() {
		return subdivisi_app;
	}

	public void setSubdivisi_app(Subdivisi subdivisi_app) {
		this.subdivisi_app = subdivisi_app;
	}

	public Departmen getDepartmen() {
		return departmen;
	}

	public void setDepartmen(Departmen departmen) {
		this.departmen = departmen;
	}

	public Departmen getDepartmen_app() {
		return departmen_app;
	}

	public void setDepartmen_app(Departmen departmen_app) {
		this.departmen_app = departmen_app;
	}

	public Lokasi getLokasi() {
		return lokasi;
	}

	public void setLokasi(Lokasi lokasi) {
		this.lokasi = lokasi;
	}

	public User getUser_d() {
		return user_d;
	}

	public void setUser_d(User user_d) {
		this.user_d = user_d;
	}

	public User getUser_conf_d() {
		return user_conf_d;
	}

	public void setUser_conf_d(User user_conf_d) {
		this.user_conf_d = user_conf_d;
	}

	public User getUser_app_d() {
		return user_app_d;
	}

	public void setUser_app_d(User user_app_d) {
		this.user_app_d = user_app_d;
	}

	public User getUser_real_d() {
		return user_real_d;
	}

	public void setUser_real_d(User user_real_d) {
		this.user_real_d = user_real_d;
	}

	public User getUser_conf_real_d() {
		return user_conf_real_d;
	}

	public void setUser_conf_real_d(User user_conf_real_d) {
		this.user_conf_real_d = user_conf_real_d;
	}

	public User getUser_conf_oc_d() {
		return user_conf_oc_d;
	}

	public void setUser_conf_oc_d(User user_conf_oc_d) {
		this.user_conf_oc_d = user_conf_oc_d;
	}

	public List<PpucD> getPpucds() {
		return ppucds;
	}

	public void setPpucds(List<PpucD> ppucds) {
		this.ppucds = ppucds;
	}

	public String getKd_group() {
		return kd_group;
	}

	public void setKd_group(String kd_group) {
		this.kd_group = kd_group;
	}

	public String getKd_biaya() {
		return kd_biaya;
	}

	public void setKd_biaya(String kd_biaya) {
		this.kd_biaya = kd_biaya;
	}

	public Integer[] getIdx() {
		return idx;
	}

	public void setIdx(Integer[] idx) {
		this.idx = idx;
	}

	public Integer getPosisi() {
		return posisi;
	}

	public void setPosisi(Integer posisi) {
		this.posisi = posisi;
	}

	public String getPosisi_desc() {
		return posisi_desc;
	}

	public void setPosisi_desc(String posisi_desc) {
		this.posisi_desc = posisi_desc;
	}

	public String getDivisi_nm() {
		return divisi_nm;
	}

	public void setDivisi_nm(String divisi_nm) {
		this.divisi_nm = divisi_nm;
	}

	public String getSubdiv_nm() {
		return subdiv_nm;
	}

	public void setSubdiv_nm(String subdiv_nm) {
		this.subdiv_nm = subdiv_nm;
	}

	public String getDept_nm() {
		return dept_nm;
	}

	public void setDept_nm(String dept_nm) {
		this.dept_nm = dept_nm;
	}

	public String getLok_nm() {
		return lok_nm;
	}

	public void setLok_nm(String lok_nm) {
		this.lok_nm = lok_nm;
	}

	public Integer getPosisi_min() {
		return posisi_min;
	}

	public void setPosisi_min(Integer posisi_min) {
		this.posisi_min = posisi_min;
	}



	public String getPosisiGroup() {
		return posisiGroup;
	}



	public void setPosisiGroup(String posisiGroup) {
		this.posisiGroup = posisiGroup;
	}



	public List<PpucH> getPpuchs() {
		return ppuchs;
	}



	public void setPpuchs(List<PpucH> ppuchs) {
		this.ppuchs = ppuchs;
	}

	//****************** GETTER SETTER END HERE ******************/

}
