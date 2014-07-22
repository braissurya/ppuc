package com.melawai.ppuc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
 * @since		: Sun Jul 06 16:28:25 ICT 2014
 * @Description: Model for table group_lokasi_h
 * @Revision	:
 */

public class GroupLokasiH extends BaseObject implements Serializable  {

	//****************** COLOMN FROM TABLE START HERE ******************/
	@NotNull
	@Size(max=3)
	public String divisi_kd;

	@NotNull
	@Size(max=3)
	public String subdiv_kd;

	@NotNull
	@Size(max=5)
	public String group_lok;

	@Size(max=50)
	public String group_desc;

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
	public List<GroupLokasiD> groupLokasiDList=new ArrayList<GroupLokasiD>();
	//****************** OTHERS END HERE ******************/


	//****************** CONSTRUCTOR START HERE ******************/
	public GroupLokasiH(){
		//TODO: standard constructor free to change
	}

	//****************** CONSTRUCTOR END HERE ******************/


	//****************** GETTER SETTER START HERE ******************/
	public String getDivisi_kd(){ return divisi_kd; }
	public void setDivisi_kd(String divisi_kd){ this.divisi_kd = divisi_kd; }

	public String getSubdiv_kd(){ return subdiv_kd; }
	public void setSubdiv_kd(String subdiv_kd){ this.subdiv_kd = subdiv_kd; }

	public String getGroup_lok(){ return group_lok; }
	public void setGroup_lok(String group_lok){ this.group_lok = group_lok; }

	public String getGroup_desc(){ return group_desc; }
	public void setGroup_desc(String group_desc){ this.group_desc = group_desc; }

	public String getUser_create(){ return user_create; }
	public void setUser_create(String user_create){ this.user_create = user_create; }

	public Date getTgl_create(){ return tgl_create; }
	public void setTgl_create(Date tgl_create){ this.tgl_create = tgl_create; }

	public String getJam_create(){ return jam_create; }
	public void setJam_create(String jam_create){ this.jam_create = jam_create; }


	public String getItemId() {return ""+divisi_kd+"/"+subdiv_kd+"/"+group_lok;	}
	public void setItemId(String itemId) {this.itemId = itemId;}

	public List<GroupLokasiD> getGroupLokasiDList() {
		return groupLokasiDList;
	}

	public void setGroupLokasiDList(List<GroupLokasiD> groupLokasiDList) {
		this.groupLokasiDList = groupLokasiDList;
	}
	
	

	//****************** GETTER SETTER END HERE ******************/

}
