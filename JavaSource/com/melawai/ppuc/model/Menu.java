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
 * @since		: Thu Jun 19 23:42:35 ICT 2014
 * @Description: Model for table menu
 * @Revision	:
 */

public class Menu extends BaseObject implements Serializable  {

	
	private static final long serialVersionUID = 2062694598974079208L;

	//****************** COLOMN FROM TABLE START HERE ******************/
	public Long menu_id;

	@NotNull
	public Long parent;

	@NotEmpty
	@Size(max=50)
	public String nama;

	@Size(max=200)
	public String link;

	public Long level;

	@NotNull
	public Long urut;

	@Size(max=50)
	public String path;

	public Integer f_aktif;

	@Size(max=50)
	public String user_nonaktif;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style="MM")
	public Date tgl_nonaktif;

	@Size(max=8)
	public String jam_nonaktif;

	//****************** COLOMN FROM TABLE END HERE ******************/


	//****************** OTHERS START HERE ******************/

	public String itemId;
	public Menu menu_parent;
	public String parent_nama;
	public String parent_link;
	
	public Boolean akses;
	
	//****************** OTHERS END HERE ******************/


	//****************** CONSTRUCTOR START HERE ******************/
	public Menu(){
		//TODO: standard constructor free to change
	}

	//****************** CONSTRUCTOR END HERE ******************/


	//****************** GETTER SETTER START HERE ******************/
	public Long getMenu_id(){ return menu_id; }
	public void setMenu_id(Long menu_id){ this.menu_id = menu_id; }

	public Long getParent(){ return parent; }
	public void setParent(Long parent){ this.parent = parent; }

	public String getNama(){ return nama; }
	public void setNama(String nama){ this.nama = nama; }

	public String getLink(){ return link; }
	public void setLink(String link){ this.link = link; }

	public Long getLevel(){ return level; }
	public void setLevel(Long level){ this.level = level; }

	public Long getUrut(){ return urut; }
	public void setUrut(Long urut){ this.urut = urut; }

	public String getPath(){ return path; }
	public void setPath(String path){ this.path = path; }

	public Integer getF_aktif(){
		if(f_aktif==null)f_aktif=0;
		return f_aktif; 
	}
	public void setF_aktif(Integer f_aktif){ 
		if(f_aktif==null)f_aktif=0;
		this.f_aktif = f_aktif; 
	}

	public String getUser_nonaktif(){ return user_nonaktif; }
	public void setUser_nonaktif(String user_nonaktif){ this.user_nonaktif = user_nonaktif; }

	public Date getTgl_nonaktif(){ return tgl_nonaktif; }
	public void setTgl_nonaktif(Date tgl_nonaktif){ 
		this.tgl_nonaktif = tgl_nonaktif; 
		if (tgl_nonaktif != null) {
			this.jam_nonaktif = Utils.convertDateToString(tgl_nonaktif, jam_format);
		}
	}

	public String getJam_nonaktif(){ return jam_nonaktif; }
	public void setJam_nonaktif(String jam_nonaktif){ this.jam_nonaktif = jam_nonaktif; }


	public String getItemId() {return ""+menu_id;	}
	public void setItemId(String itemId) {this.itemId = itemId;}

	public Menu getMenu_parent() {	return menu_parent;	}

	public void setMenu_parent(Menu menu_parent) {	this.menu_parent = menu_parent;	}

	public String getParent_nama() {
		return parent_nama;
	}

	public void setParent_nama(String parent_nama) {
		this.parent_nama = parent_nama;
	}

	public String getParent_link() {
		return parent_link;
	}

	public void setParent_link(String parent_link) {
		this.parent_link = parent_link;
	}

	public Boolean getAkses() {
		return akses;
	}

	public void setAkses(Boolean akses) {
		this.akses = akses;
	}

	
	
	

	//****************** GETTER SETTER END HERE ******************/

}
