package com.melawai.ppuc.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.melawai.ppuc.utils.Utils;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Thu Jun 19 23:42:39 ICT 2014
 * @Description: Model for table user_divisi
 * @Revision	:
 */

public class UserDivisi extends BaseObject implements Serializable  {
	
	private static final long serialVersionUID = 5168851440655783784L;

	//****************** COLOMN FROM TABLE START HERE ******************/
	
	public Long id_user_divisi;

	@NotNull
	public String user_id;

	@NotNull
	public String divisi_kd;

	@NotNull
	public String subdiv_kd;

	@NotNull
	public String dept_kd;

	public String lok_kd;

	//****************** COLOMN FROM TABLE END HERE ******************/


	//****************** OTHERS START HERE ******************/

	public String itemId;
	//****************** OTHERS END HERE ******************/


	//****************** CONSTRUCTOR START HERE ******************/
	public UserDivisi(){
		//TODO: standard constructor free to change
	}

	//****************** CONSTRUCTOR END HERE ******************/


	//****************** GETTER SETTER START HERE ******************/
	public Long getId_user_divisi(){ return id_user_divisi; }
	public void setId_user_divisi(Long id_user_divisi){ this.id_user_divisi = id_user_divisi; }

	public String getUser_id(){ return user_id; }
	public void setUser_id(String user_id){ this.user_id = user_id; }

	public String getDivisi_kd(){ return divisi_kd; }
	public void setDivisi_kd(String divisi_kd){ this.divisi_kd = divisi_kd; }

	public String getSubdiv_kd(){ return subdiv_kd; }
	public void setSubdiv_kd(String subdiv_kd){ this.subdiv_kd = subdiv_kd; }

	public String getDept_kd(){ return dept_kd; }
	public void setDept_kd(String dept_kd){ this.dept_kd = dept_kd; }

	public String getLok_kd(){ return Utils.nvl(lok_kd); }
	public void setLok_kd(String lok_kd){ this.lok_kd = lok_kd; }

	public String getItemId() {return ""+id_user_divisi;	}
	public void setItemId(String itemId) {this.itemId = itemId;}

	//****************** GETTER SETTER END HERE ******************/

}
