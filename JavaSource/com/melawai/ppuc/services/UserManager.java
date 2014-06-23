package com.melawai.ppuc.services;

import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.melawai.ppuc.model.User;
import com.melawai.ppuc.persistence.UserMapper;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Thu Jun 19 23:42:39 ICT 2014
 * @Description: Services for table user
 * @Revision	:
 */

@Service("userManager")
public class UserManager {

	private static Logger logger = Logger.getLogger(UserManager.class);

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired(required = false)
	private SaltSource saltSource;

	/** Ambil DATA berdasarkan user_id **/
	public User get(String user_id) {
		return userMapper.get(user_id);
	}

	/** Apakah data dengan user_id ini ada? **/
	public boolean exists(String user_id) {	
		return get(user_id)!=null;
	}

	/** Delete data berdasarkan user_id **/
	@Transactional
	public void remove(String user_id) {
		userMapper.remove(user_id);
	}

	/** Ambil jumlah seluruh data **/
	public int selectPagingCount(String search) {
		User user=new User();
		user.setSearch(search);
		return userMapper.selectPagingCount(user);
	}

	/** Ambil data paging **/
	public List<User> selectPagingList(String search, String sort,String sortOrder, int page, int rowcount) {
		User user=new User();
		user.setSearch(search);
		 if(sort!=null)user.setSort(sort+" "+sortOrder);
		user.setPage(page);
		user.setRowcount(rowcount);
		return userMapper.selectPagingList(user);
	}

	/** Save Model **/
	@Transactional
	public User save(User user) {
		if (user.getTgl_create()==null) {
			userMapper.insert(user);
		} else {
			userMapper.update(user);
		} 
		return user;
	}
	/** Others Method **/

	public User getUserByUsername(String user_id) {
		return userMapper.loadUserByUsername(user_id);
	}

	public User saveUserLogin(User user) {
		if (user.getTgl_create()==null) {
			user.setPassword(passwordEncoder.encodePassword(user.getPassword(), saltSource.getSalt(user)));
		}else if (user.getNewPassword() != null) {
			user.setPassword(passwordEncoder.encodePassword(user.getNewPassword(), saltSource.getSalt(user)));
		}else{
			user.setPassword(null);
		}
		return save(user);
		}

	}
