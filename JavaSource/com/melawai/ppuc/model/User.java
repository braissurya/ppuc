package com.melawai.ppuc.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.melawai.ppuc.utils.Utils;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Thu Jun 19 23:42:39 ICT 2014
 * @Description: Model for table user
 * @Revision	:
 */


public class User extends BaseObject implements Serializable , UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 761598147495279228L;

	//****************** COLOMN FROM TABLE START HERE ******************/
	@NotEmpty
	@Size(max=50)
	public String user_id;

	@NotEmpty
	@Size(max=100)
	public String password;

	@NotEmpty
	@Size(max=100)
	public String user_name;

	@NotEmpty
	@Size(max=25)
	public String no_hp;
	
	@NotEmpty
	@Email
	@Size(max=100)
	public String email;

	@NotEmpty
	@Size(max=5)
	public String kd_fungsi;

	@NotEmpty
	@Size(max=5)
	public String group_kd;

	public Integer f_aktif;

	@Size(max=50)
	public String user_create;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style="MM")
	public Date tgl_create;

	@Size(max=8)
	public String jam_create;

	@Size(max=50)
	public String user_update;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style="MM")
	public Date tgl_update;

	@Size(max=8)
	public String jam_update;

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
	public String oldPassword;
	public String newPassword;
	public String confirmPassword;
	public String passwordDefault;
	
	public Date loginTime;
	public GroupUser groupUser;
	public MFungsi mFungsi;
	public String menu;
	public Integer filter_faktif;
	public String isActive;
	public String revActive;

	//****************** OTHERS END HERE ******************/


	//****************** CONSTRUCTOR START HERE ******************/
	public User(){
		//TODO: standard constructor free to change
	}

	//****************** CONSTRUCTOR END HERE ******************/


	//****************** GETTER SETTER START HERE ******************/
	public String getUser_id(){ return user_id; }
	public void setUser_id(String user_id){ this.user_id = user_id; }

	public String getPassword(){ return password; }
	public void setPassword(String password){ this.password = password; }

	public String getUser_name(){ return user_name; }
	public void setUser_name(String user_name){ this.user_name = user_name; }

	public String getNo_hp(){ return Utils.mobileNoStdFormat(no_hp); }
	public void setNo_hp(String no_hp){ this.no_hp = Utils.mobileNoStdFormat(no_hp); }

	public String getEmail(){ return email; }
	public void setEmail(String email){ this.email = email; }

	public String getKd_fungsi(){ return kd_fungsi; }
	public void setKd_fungsi(String kd_fungsi){ this.kd_fungsi = kd_fungsi; }

	public String getGroup_kd(){ return group_kd; }
	public void setGroup_kd(String group_kd){ this.group_kd = group_kd; }

	public Integer getF_aktif(){ 
		if(f_aktif==null)f_aktif=0;
		return f_aktif; 
	}
	public void setF_aktif(Integer f_aktif){ 
		if(f_aktif==null)f_aktif=0;
		this.f_aktif = f_aktif; 
		if(f_aktif==0){
			revActive="Active";
			isActive="Non-Active";
		}else{
			revActive="Non-Active";
			isActive="Active";
		}
	}

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

	public String getUser_update(){ return user_update; }
	public void setUser_update(String user_update){ this.user_update = user_update; }

	public Date getTgl_update(){ return tgl_update; }
	public void setTgl_update(Date tgl_update){ 
		this.tgl_update = tgl_update;
		if (tgl_update != null) {
			this.jam_update = Utils.convertDateToString(tgl_update, jam_format);
		}
	}

	public String getJam_update(){ return jam_update; }
	public void setJam_update(String jam_update){ this.jam_update = jam_update; }

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


	public String getItemId() {return ""+user_id;	}
	public void setItemId(String itemId) {this.itemId = itemId;}

	public String getOldPassword() {return oldPassword;	}
	public void setOldPassword(String oldPassword) {this.oldPassword = oldPassword;}

	public String getNewPassword() {return newPassword;}
	public void setNewPassword(String newPassword) {this.newPassword = newPassword;}

	public String getConfirmPassword() {return confirmPassword;}
	public void setConfirmPassword(String confirmPassword) {this.confirmPassword = confirmPassword;	}

	public MFungsi getmFungsi() {return mFungsi;}
	public void setmFungsi(MFungsi mFungsi) {this.mFungsi = mFungsi;}

	public GroupUser getGroupUser() {return groupUser;}
	public void setGroupUser(GroupUser groupUser) {	this.groupUser = groupUser;}

	public Date getLoginTime() {return loginTime;}
	public void setLoginTime(Date loginTime) {	this.loginTime = loginTime;}
	
	public String getMenu() {return menu;}
	public void setMenu(String menu) {this.menu = menu;	}
	
	
	
	//****************** GETTER SETTER END HERE ******************/

	

	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = new LinkedHashSet<GrantedAuthority>();
		authorities.add(new Role(groupUser.role.role_name));
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return f_aktif.intValue()==1?true:false;
	}

	@Override
	public String getUsername() {
		return user_id;
	}

	public String getPasswordDefault() {
		return passwordDefault;
	}

	public void setPasswordDefault(String passwordDefault) {
		this.passwordDefault = passwordDefault;
	}

	public Integer getFilter_faktif() {
		return filter_faktif;
	}

	public void setFilter_faktif(Integer filter_faktif) {
		this.filter_faktif = filter_faktif;
	}

	public String getIsActive() {		
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getRevActive() {
		return revActive;
	}

	public void setRevActive(String revActive) {
		this.revActive = revActive;
	}
	
	
	
}
