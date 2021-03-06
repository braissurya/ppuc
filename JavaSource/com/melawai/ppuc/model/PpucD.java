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
 * @since		: Thu Jun 19 23:42:37 ICT 2014
 * @Description: Model for table ppuc_d
 * @Revision	:
 */

public class PpucD extends BaseObject implements Serializable  {

	private static final long serialVersionUID = 609284784977244779L;

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

	@NotEmpty
	public String kd_group;

	@NotEmpty
	public String kd_biaya;

	public String no_batch;
	
	public Integer posisi;

	public Long qty;

	public Double harga;

	public Double total;

	public Long qty_old;

	public Double harga_old;

	public Double total_old;

	@Size(max=65535)
	public String keterangan;

	public Integer f_approval;
	
	@Size(max=65535)
	public String ket_approve;

	@Size(max=25)
	public String no_realisasi;
	
	@Size(max=50)
	public String user_realisasi;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style="MM")
	public Date tgl_realisasi;

	@Size(max=8)
	public String jam_realisasi;

	public Long qty_real_cbg;

	public Double harga_real_cbg;

	public Double total_real_cbg;

	public Long qty_real_oc;

	public Double harga_real_oc;

	public Double total_real_oc;

	public Double nilai_charge;
	
	public Integer f_batal;

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

	//****************** COLOMN FROM TABLE END HERE ******************/


	//****************** OTHERS START HERE ******************/

	public String itemId;
	public String nm_group;
	public String nm_biaya;
	
	public GroupBiaya groupBiaya;
	public DetailBiaya detailBiaya;
	
	public String posisi_desc;
	//****************** OTHERS END HERE ******************/


	//****************** CONSTRUCTOR START HERE ******************/
	public PpucD(){
	}
	



	public PpucD(String divisi_kd, String subdiv_kd, String dept_kd,
			String lok_kd, String no_ppuc, Date tgl_ppuc, String kd_group,
			String kd_biaya, Long qty, Double harga, Double total,
			String keterangan, String nm_group, String nm_biaya) {
		super();
		this.divisi_kd = divisi_kd;
		this.subdiv_kd = subdiv_kd;
		this.dept_kd = dept_kd;
		this.lok_kd = lok_kd;
		this.no_ppuc = no_ppuc;
		this.tgl_ppuc = tgl_ppuc;
		this.kd_group = kd_group;
		this.kd_biaya = kd_biaya;
		this.qty = qty;
		this.harga = harga;
		if(qty!=null&&harga!=null)this.total = qty*harga;
		this.keterangan = keterangan;
		this.nm_biaya=nm_biaya;
		this.nm_group=nm_group;
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

	public String getKd_group(){ return kd_group; }
	public void setKd_group(String kd_group){ this.kd_group = kd_group; }

	public String getKd_biaya(){ return kd_biaya; }
	public void setKd_biaya(String kd_biaya){ this.kd_biaya = kd_biaya; }

	public String getNo_batch(){ return no_batch; }
	public void setNo_batch(String no_batch){ this.no_batch = no_batch; }

	public Long getQty(){ return qty; }
	public void setQty(Long qty){ this.qty = qty; }

	public Double getHarga(){ return harga; }
	public void setHarga(Double harga){ this.harga = harga; }

	public Double getTotal(){ return total; }
	public void setTotal(Double total){ this.total = total; }

	public Long getQty_old(){ return qty_old; }
	public void setQty_old(Long qty_old){ this.qty_old = qty_old; }

	public Double getHarga_old(){ return harga_old; }
	public void setHarga_old(Double harga_old){ this.harga_old = harga_old; }

	public Double getTotal_old(){ return total_old; }
	public void setTotal_old(Double total_old){ this.total_old = total_old; }

	public String getKeterangan(){ return keterangan; }
	public void setKeterangan(String keterangan){ this.keterangan = keterangan; }

	public String getKet_approve(){ return ket_approve; }
	public void setKet_approve(String ket_approve){ this.ket_approve = ket_approve; }

	public String getNo_realisasi(){ return no_realisasi; }
	public void setNo_realisasi(String no_realisasi){ this.no_realisasi = no_realisasi; }

	public Long getQty_real_cbg(){ return qty_real_cbg; }
	public void setQty_real_cbg(Long qty_real_cbg){ this.qty_real_cbg = qty_real_cbg; }

	public Double getHarga_real_cbg(){ return harga_real_cbg; }
	public void setHarga_real_cbg(Double harga_real_cbg){ this.harga_real_cbg = harga_real_cbg; }

	public Double getTotal_real_cbg(){ return total_real_cbg; }
	public void setTotal_real_cbg(Double total_real_cbg){ this.total_real_cbg = total_real_cbg; }

	public Long getQty_real_oc(){ return qty_real_oc; }
	public void setQty_real_oc(Long qty_real_oc){ this.qty_real_oc = qty_real_oc; }

	public Double getHarga_real_oc(){ return harga_real_oc; }
	public void setHarga_real_oc(Double harga_real_oc){ this.harga_real_oc = harga_real_oc; }

	public Double getTotal_real_oc(){ return total_real_oc; }
	public void setTotal_real_oc(Double total_real_oc){ this.total_real_oc = total_real_oc; }

	public Double getNilai_charge(){ return nilai_charge; }
	public void setNilai_charge(Double nilai_charge){ this.nilai_charge = nilai_charge; }

	public String getItemId() {return ""+divisi_kd+"/"+subdiv_kd+"/"+dept_kd+"/"+lok_kd+"/"+no_ppuc+"/"+tgl_ppuc+"/"+kd_biaya;	}
	public void setItemId(String itemId) {this.itemId = itemId;}




	public GroupBiaya getGroupBiaya() {
		return groupBiaya;
	}




	public void setGroupBiaya(GroupBiaya groupBiaya) {
		this.groupBiaya = groupBiaya;
	}




	public DetailBiaya getDetailBiaya() {
		return detailBiaya;
	}




	public void setDetailBiaya(DetailBiaya detailBiaya) {
		this.detailBiaya = detailBiaya;
	}




	public String getNm_group() {
		return nm_group;
	}




	public void setNm_group(String nm_group) {
		this.nm_group = nm_group;
	}




	public String getNm_biaya() {
		return nm_biaya;
	}




	public void setNm_biaya(String nm_biaya) {
		this.nm_biaya = nm_biaya;
	}




	public Integer getPosisi() {
		return posisi;
	}




	public void setPosisi(Integer posisi) {
		this.posisi = posisi;
	}




	public Integer getF_approval() {
		return f_approval;
	}




	public void setF_approval(Integer f_approval) {
		this.f_approval = f_approval;
	}




	public String getUser_realisasi() {
		return user_realisasi;
	}




	public void setUser_realisasi(String user_realisasi) {
		this.user_realisasi = user_realisasi;
	}




	public Date getTgl_realisasi() {
		return tgl_realisasi;
	}




	public void setTgl_realisasi(Date tgl_realisasi) {
		this.tgl_realisasi = tgl_realisasi;
	}




	public String getJam_realisasi() {
		return jam_realisasi;
	}




	public void setJam_realisasi(String jam_realisasi) {
		this.jam_realisasi = jam_realisasi;
	}




	public Integer getF_batal() {
		return f_batal;
	}




	public void setF_batal(Integer f_batal) {
		this.f_batal = f_batal;
	}




	public String getUser_batal() {
		return user_batal;
	}




	public void setUser_batal(String user_batal) {
		this.user_batal = user_batal;
	}




	public Date getTgl_batal() {
		return tgl_batal;
	}




	public void setTgl_batal(Date tgl_batal) {
		this.tgl_batal = tgl_batal;
		if (tgl_batal != null) {
			this.jam_batal = Utils.convertDateToString(tgl_batal, jam_format);
		}
	}




	public String getJam_batal() {
		return jam_batal;
	}




	public void setJam_batal(String jam_batal) {
		this.jam_batal = jam_batal;
	}




	public String getAlasan_batal() {
		return alasan_batal;
	}




	public void setAlasan_batal(String alasan_batal) {
		this.alasan_batal = alasan_batal;
	}




	public String getHp_asal_batal() {
		return hp_asal_batal;
	}




	public void setHp_asal_batal(String hp_asal_batal) {
		this.hp_asal_batal = hp_asal_batal;
	}




	public String getHp_tujuan_batal() {
		return hp_tujuan_batal;
	}




	public void setHp_tujuan_batal(String hp_tujuan_batal) {
		this.hp_tujuan_batal = hp_tujuan_batal;
	}




	public String getEmail_asal_batal() {
		return email_asal_batal;
	}




	public void setEmail_asal_batal(String email_asal_batal) {
		this.email_asal_batal = email_asal_batal;
	}




	public String getEmail_tujuan_batal() {
		return email_tujuan_batal;
	}




	public void setEmail_tujuan_batal(String email_tujuan_batal) {
		this.email_tujuan_batal = email_tujuan_batal;
	}




	public String getPosisi_desc() {
		return posisi_desc;
	}




	public void setPosisi_desc(String posisi_desc) {
		this.posisi_desc = posisi_desc;
	}

	//****************** GETTER SETTER END HERE ******************/

}
