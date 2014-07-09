package com.melawai.ppuc.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.melawai.ppuc.utils.Utils;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Thu Jun 19 23:42:33 ICT 2014
 * @Description: Model for table group_user
 * @Revision	:
 */

public class GroupUser extends BaseObject implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8946818516215179670L;

	//****************** COLOMN FROM TABLE START HERE ******************/
	@NotNull
	@Size(max=5)
	public String group_kd;

	@Size(max=50)
	public String group_nm;

	public Long id_role;

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
	public Role role;
	public String role_name;

	public List<Menu> menus;
	//****************** OTHERS END HERE ******************/


	//****************** CONSTRUCTOR START HERE ******************/
	public GroupUser(){
		//TODO: standard constructor free to change
	}

	//****************** CONSTRUCTOR END HERE ******************/


	//****************** GETTER SETTER START HERE ******************/
	public String getGroup_kd(){ return group_kd; }
	public void setGroup_kd(String group_kd){ this.group_kd = group_kd; }

	public String getGroup_nm(){ return group_nm; }
	public void setGroup_nm(String group_nm){ this.group_nm = group_nm; }

	public Long getId_role(){ return id_role; }
	public void setId_role(Long id_role){ this.id_role = id_role; }

	public String getUser_create(){ return user_create; }
	public void setUser_create(String user_create){ this.user_create = user_create; }

	public Date getTgl_create(){ return tgl_create; }
	public void setTgl_create(Date tgl_create){ 
		this.tgl_create = tgl_create; 
		if (tgl_create != null) {
			this.jam_create = Utils.convertDateToString(tgl_create, jam_format);
		}
	}

	public String getJam_create(){ return jam_create; }
	public void setJam_create(String jam_create){ this.jam_create = jam_create; }


	public String getItemId() {return ""+group_kd;	}
	public void setItemId(String itemId) {this.itemId = itemId;}

	public Role getRole() {	return role;}
	public void setRole(Role role) {this.role = role;}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	public String getRole_name() {
		return role.getRole_name();
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	
	
	
	
	

	//****************** GETTER SETTER END HERE ******************/

}
