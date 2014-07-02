package com.melawai.ppuc.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;

/**
 * GENERATE BY BraisSpringMVCHelp
 * 
 * @since : Wed Jun 18 23:10:18 ICT 2014
 * @Description: Model for table role
 * @Revision :
 */

public class Role extends BaseObject implements Serializable, GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1623893049898281141L;
	public static final String ROLE_NAME_ADMIN_SUPER = "ROLE_SUPER";
	public static final String ROLE_NAME_ADMIN = "ROLE_ADMIN";
	public static final String ROLE_NAME_USER = "ROLE_USER";

	public Role(String role_name) {
		this.role_name = role_name;
	}

	// ****************** COLOMN FROM TABLE START HERE ******************/
	@NotNull
	public Long id_role;

	@Size(max = 45)
	public String role_name;

	// ****************** COLOMN FROM TABLE END HERE ******************/

	// ****************** OTHERS START HERE ******************/

	public String itemId;

	// ****************** OTHERS END HERE ******************/

	// ****************** CONSTRUCTOR START HERE ******************/
	public Role() {
		// TODO: standard constructor free to change
	}

	// ****************** CONSTRUCTOR END HERE ******************/

	// ****************** GETTER SETTER START HERE ******************/
	public Long getId_role() {
		return id_role;
	}

	public void setId_role(Long id_role) {
		this.id_role = id_role;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public String getItemId() {
		return "" + id_role;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return role_name;
	}

	// ****************** GETTER SETTER END HERE ******************/

}
