package com.melawai.ppuc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Sun Jul 06 16:28:25 ICT 2014
 * @Description: Model for table group_lokasi_h
 * @Revision	:
 */

public class GroupLokasiH extends BaseObject implements Serializable  {

	private static final long serialVersionUID = -8450998741872418461L;

	//****************** COLOMN FROM TABLE START HERE ******************/
	@NotEmpty
	public String divisi_kd;

	@NotEmpty
	public String subdiv_kd;

	@NotEmpty
	@Size(max=5)
	public String group_lok;

	@Size(max=50)
	public String group_desc;

	

	//****************** COLOMN FROM TABLE END HERE ******************/


	//****************** OTHERS START HERE ******************/

	public String itemId;
	public List<GroupLokasiD> groupLokasiDList=new ArrayList<GroupLokasiD>();
	
	@NotEmpty
	public String [] lok_kd;
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

	

	public String getItemId() {return ""+divisi_kd+"/"+subdiv_kd+"/"+group_lok;	}
	public void setItemId(String itemId) {this.itemId = itemId;}

	public List<GroupLokasiD> getGroupLokasiDList() {
		return groupLokasiDList;
	}

	public void setGroupLokasiDList(List<GroupLokasiD> groupLokasiDList) {
		this.groupLokasiDList = groupLokasiDList;
	}

	public String[] getLok_kd() {
		return lok_kd;
	}

	public void setLok_kd(String[] lok_kd) {
		this.lok_kd = lok_kd;
	}
	
	

	//****************** GETTER SETTER END HERE ******************/

}
