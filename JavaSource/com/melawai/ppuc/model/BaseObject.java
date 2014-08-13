package com.melawai.ppuc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.melawai.ppuc.utils.Utils;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Thu Jun 19 23:42:25 ICT 2014
 * @Description: Model Base Object
 * @Revision	:
 */

public class BaseObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6552694768967147551L;
	
	public final String jam_format="HH:mm";
	
	//search helper
	public Integer rowcount;
	public Integer page;
	public String sort;
	public String search;
	
	public Integer typeQuery;
	
	@Size(max = 50)
	public String user_update;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "MM")
	public Date tgl_update;

	@Size(max = 8)
	public String jam_update;

	@Size(max = 50)
	public String user_create;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "MM")
	public Date tgl_create;
	
	@Size(max = 8)
	public String jam_create;

	//****************** GETTER SETTER START HERE ******************/
	public Integer getRowcount() { return rowcount; }
	public void setRowcount(Integer rowcount) { this.rowcount = rowcount; }

	public Integer getPage() { return page; }
	public void setPage(Integer page) { this.page = page; }

	public String getSort() { return sort; }
	public void setSort(String sort) { this.sort = sort; }

	public String getSearch() { return search; }
	public void setSearch(String search) { this.search = search; }
	
	public String getUser_update() {
		return user_update;
	}
	public void setUser_update(String user_update) {
		this.user_update = user_update;
	}
	public Date getTgl_update() {
		return tgl_update;
	}
	public void setTgl_update(Date tgl_update) {
		this.tgl_update = tgl_update;
		if (tgl_update != null) {
			this.jam_update = Utils.convertDateToString(tgl_update, jam_format);
		}
	}
	public String getJam_update() {
		return jam_update;
	}
	public void setJam_update(String jam_update) {
		this.jam_update = jam_update;
	}
	public String getUser_create() {
		return user_create;
	}
	public void setUser_create(String user_create) {
		this.user_create = user_create;
	}
	public Date getTgl_create() {
		return tgl_create;
		
	}
	public void setTgl_create(Date tgl_create) {
		this.tgl_create = tgl_create;
		if (tgl_create != null) {
			this.jam_create = Utils.convertDateToString(tgl_create, jam_format);
		}
	}
	
	
	public String getJam_create() {
		return jam_create;
	}
	public void setJam_create(String jam_create) {
		this.jam_create = jam_create;
	}
	public String getJam_format() {
		return jam_format;
	}
	public Integer getTypeQuery() {
		return typeQuery;
	}
	public void setTypeQuery(Integer typeQuery) {
		this.typeQuery = typeQuery;
	}
	
	
	

	//****************** GETTER SETTER END HERE ******************/

}
