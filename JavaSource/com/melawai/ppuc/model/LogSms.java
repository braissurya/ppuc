package com.melawai.ppuc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Thu Jun 19 23:42:38 ICT 2014
 * @Description: Model for table sys_log_sms
 * @Revision	:
 */

public class LogSms extends BaseObject implements Serializable  {

	private static final long serialVersionUID = -8585791105547932978L;

	//****************** COLOMN FROM TABLE START HERE ******************/
	@NotNull
	public Long id_log_sms;

	@Size(max=3)
	public String divisi_kd;

	@Size(max=3)
	public String subdiv_kd;

	@Size(max=3)
	public String dept_kd;

	@Size(max=5)
	public String lok_kd;

	@Size(max=9)
	public String no_ppuc;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(style="M-")
	public Date tgl_ppuc;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style="MM")
	public Date send_date;

	@Size(max=8)
	public String send_time;

	@Size(max=1)
	public String status;

	@Size(max=65535)
	public String isi_sms;

	@Size(max=25)
	public String hp_maker;

	@Size(max=25)
	public String hp_approval;

	//****************** COLOMN FROM TABLE END HERE ******************/


	//****************** OTHERS START HERE ******************/

	public String itemId;
	//****************** OTHERS END HERE ******************/


	//****************** CONSTRUCTOR START HERE ******************/
	public LogSms(){
		//TODO: standard constructor free to change
	}

	//****************** CONSTRUCTOR END HERE ******************/


	//****************** GETTER SETTER START HERE ******************/
	public Long getId_log_sms(){ return id_log_sms; }
	public void setId_log_sms(Long id_log_sms){ this.id_log_sms = id_log_sms; }

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

	public Date getSend_date(){ return send_date; }
	public void setSend_date(Date send_date){ this.send_date = send_date; }

	public String getSend_time(){ return send_time; }
	public void setSend_time(String send_time){ this.send_time = send_time; }

	public String getStatus(){ return status; }
	public void setStatus(String status){ this.status = status; }

	public String getIsi_sms(){ return isi_sms; }
	public void setIsi_sms(String isi_sms){ this.isi_sms = isi_sms; }

	public String getHp_maker(){ return hp_maker; }
	public void setHp_maker(String hp_maker){ this.hp_maker = hp_maker; }

	public String getHp_approval(){ return hp_approval; }
	public void setHp_approval(String hp_approval){ this.hp_approval = hp_approval; }

	public String getItemId() {return ""+id_log_sms;	}
	public void setItemId(String itemId) {this.itemId = itemId;}

	//****************** GETTER SETTER END HERE ******************/

}
