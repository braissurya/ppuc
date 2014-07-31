package com.melawai.ppuc.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Thu Jun 19 23:42:38 ICT 2014
 * @Description: Model for table sys_akses_menu
 * @Revision	:
 */

public class AksesMenu extends BaseObject implements Serializable  {

	private static final long serialVersionUID = 2858890973001209186L;

	//****************** COLOMN FROM TABLE START HERE ******************/
	@NotEmpty
	@Size(max=5)
	public String group_kd;

	@NotNull
	public Long menu_id;

	

	//****************** COLOMN FROM TABLE END HERE ******************/


	//****************** OTHERS START HERE ******************/

	public String itemId;
	//****************** OTHERS END HERE ******************/


	//****************** CONSTRUCTOR START HERE ******************/
	public AksesMenu(){
	}
	
	

	public AksesMenu(String group_kd, Long menu_id) {
		super();
		this.group_kd = group_kd;
		this.menu_id = menu_id;
	}



	//****************** CONSTRUCTOR END HERE ******************/


	//****************** GETTER SETTER START HERE ******************/
	public String getGroup_kd(){ return group_kd; }
	public void setGroup_kd(String group_kd){ this.group_kd = group_kd; }

	public Long getMenu_id(){ return menu_id; }
	public void setMenu_id(Long menu_id){ this.menu_id = menu_id; }

	public String getItemId() {return ""+group_kd+"/"+menu_id;	}
	public void setItemId(String itemId) {this.itemId = itemId;}

	//****************** GETTER SETTER END HERE ******************/

}
