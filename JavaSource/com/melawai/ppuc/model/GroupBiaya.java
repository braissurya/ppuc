package com.melawai.ppuc.model;

import java.io.Serializable;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Thu Jun 19 23:42:32 ICT 2014
 * @Description: Model for table group_biaya
 * @Revision	:
 */

public class GroupBiaya extends BaseObject implements Serializable  {

	private static final long serialVersionUID = -8152483192097389053L;

	//****************** COLOMN FROM TABLE START HERE ******************/
	@NotEmpty
	@Size(max=5)
	public String kd_group;

	@NotEmpty
	@Size(max=100)
	public String nm_group;


	//****************** COLOMN FROM TABLE END HERE ******************/


	//****************** OTHERS START HERE ******************/

	public String itemId;
	//****************** OTHERS END HERE ******************/


	//****************** CONSTRUCTOR START HERE ******************/
	public GroupBiaya(){
		//TODO: standard constructor free to change
	}

	//****************** CONSTRUCTOR END HERE ******************/


	//****************** GETTER SETTER START HERE ******************/
	public String getKd_group(){ return kd_group; }
	public void setKd_group(String kd_group){ this.kd_group = kd_group; }

	public String getNm_group(){ return nm_group; }
	public void setNm_group(String nm_group){ this.nm_group = nm_group; }

	public String getItemId() {return ""+kd_group;	}
	public void setItemId(String itemId) {this.itemId = itemId;}

	//****************** GETTER SETTER END HERE ******************/

}
