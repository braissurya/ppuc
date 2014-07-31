package com.melawai.ppuc.model;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Thu Jun 19 23:42:33 ICT 2014
 * @Description: Model for table group_user
 * @Revision	:
 */

public class GroupUser extends BaseObject implements Serializable  {

	private static final long serialVersionUID = -8946818516215179670L;

	//****************** COLOMN FROM TABLE START HERE ******************/
	@NotEmpty
	@Size(max=5)
	public String group_kd;

	@NotEmpty
	@Size(max=50)
	public String group_nm;

	@NotNull
	public Long id_role;

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
