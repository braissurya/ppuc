package com.melawai.ppuc.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.melawai.ppuc.model.Menu;
import com.melawai.ppuc.model.User;
import com.melawai.ppuc.persistence.UserMapper;
import com.melawai.ppuc.utils.Utils;

/**
 * GENERATE BY BraisSpringMVCHelp
 * 
 * @since : Thu Jun 19 23:42:39 ICT 2014
 * @Description: Services for table user
 * @Revision :
 */

@Service("userManager")
public class UserManager {

	private static Logger logger = Logger.getLogger(UserManager.class);

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private MenuManager menuManager;

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
		return get(user_id) != null;
	}

	/** Delete data berdasarkan user_id **/
	@Transactional
	public void remove(String user_id) {
		userMapper.remove(user_id);
	}

	/** Ambil jumlah seluruh data **/
	public int selectPagingCount(String search) {
		User user = new User();
		user.setSearch(search);
		return userMapper.selectPagingCount(user);
	}

	/** Ambil data paging **/
	public List<User> selectPagingList(String search, String sort, String sortOrder, int page, int rowcount) {
		User user = new User();
		user.setSearch(search);
		if (sort != null)
			user.setSort(sort + " " + sortOrder);
		user.setPage(page);
		user.setRowcount(rowcount);
		return userMapper.selectPagingList(user);
	}

	/** Save Model **/
	@Transactional
	public User save(User user) {
		if (user.getTgl_create() == null) {
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
		if (user.getTgl_create() == null) {
			user.setPassword(passwordEncoder.encodePassword(user.getPassword(), saltSource.getSalt(user)));
		} else if (user.getNewPassword() != null) {
			user.setPassword(passwordEncoder.encodePassword(user.getNewPassword(), saltSource.getSalt(user)));
		} else {
			user.setPassword(null);
		}
		return save(user);
	}
	
	public  String generateMenu(String path, User currentUser) {
		StringBuffer result = new StringBuffer();
		Long tingkat = 0l;

		List<Long> parentIdList = new ArrayList<Long>();
		result.append("<div id=\"ddtopmenubar\" class=\"slantedmenu\" >");
		result.append("<ul class=\"box\" >\n");
		result.append("<li><a href=\"" + path + "\" ><span>Home</span></a></li>\n");
		
		List<Menu> daftarMenu = menuManager.selectMenuAccess(currentUser.getGroup_kd(), null, null);
		// menu tingkat 1
		for (Menu baris : daftarMenu) {
			if (baris.level.intValue() == 1) {
				if (baris.urut.intValue() == 1) {

				}

				if (Utils.isEmpty(baris.link)) {
					result.append("<li><a href=\"#\" rel=\"ddsubmenu" + baris.urut + "\"><span>" + baris.nama + "</span></a></li>\n");
					parentIdList.add(baris.urut);
				} else {
					if (baris.link.startsWith("http://")) { // klo linknya dari
															// luar
						result.append("<li><a href=\"" + baris.link + " \"><span>" + baris.nama + "</span></a></li>\n");
					} else {
						result.append("<li><a href=\"" + path + "/" + baris.link + " \"><span>" + baris.nama + "</span></a></li>\n");
					}
				}
			}
		}
		result.append("<li><a href=\"" + path + "/resources/j_spring_security_logout\" ><span>Logout</span></a></li>\n");
		result.append("</ul>\n");
		result.append("</div>\n");
		result.append("<script type=\"text/javascript\">ddlevelsmenu.setup(\"ddtopmenubar\", \"topbar\") </script>\n");

		for (Long parentId : parentIdList) {

			List<Menu> daftarMenuNoRootPath = menuManager.selectMenuAccess(currentUser.group_kd, null, "0.1." + parentId);
			if (!daftarMenuNoRootPath.isEmpty()) {
				result.append("<ul id=\"ddsubmenu" + parentId + "\" class=\"ddsubmenustyle\">\n");
				tingkat = 2l;
				int count = 0;
				for (Menu baris : daftarMenuNoRootPath) {
					if (baris.level.intValue() != 1) {
						if (baris.level.intValue() == tingkat) {
							// result.append("<li id=\""+id+"\" style=\"margin-left: 15px;\" class=\"jstree-no-icons\">"
							// +isi+ "\n");
							if (baris.link == null) {
								result.append("<li><a href=\"#\" >" + baris.nama + "</a>\n");
							} else if (baris.link != null) {
								if (baris.link.startsWith("http://")) {
									result.append("<li><a href=\"" + baris.link + " \">" + baris.nama + "</a>\n");
								} else {
									result.append("<li><a href=\"" + path + "/" + baris.link + " \">" + baris.nama + "</a>\n");
								}
							}

						} else if (baris.level > tingkat) {
							if (baris.level - tingkat > 1) {
								for (int i = 1; i <= (baris.level - tingkat); i++) {
									// result.append("<ul>\n<li class=\"jstree-no-icons\"><a href=\"#\">....</a>\n");
									if (baris.link == null) {
										result.append("<ul>\n<li><a href=\"#\" >....</a>\n");
									} else if (baris.link != null) {
										if (baris.link.startsWith("http://")) {
											result.append("<ul>\n<li><a href=\"#\">....</a>\n");
										} else {
											result.append("<ul>\n<li><a href=\"#\">....</a>\n");
										}
									}
								}
							}
							// result.append("<ul>\n<li id=\""+id+"\" style=\"margin-left: 15px;\" class=\"jstree-no-icons\">"
							// +isi+"\n");
							if (baris.link == null) {
								result.append("<ul>\n<li><a href=\"#\" >" + baris.nama + "</a>\n");
							} else if (baris.link != null) {
								if (baris.link.startsWith("http://")) {
									result.append("<ul>\n<li><a href=\"" + baris.link + " \">" + baris.nama + "</a>\n");
								} else {
									result.append("<ul>\n<li><a href=\"" + path + "/" + baris.link + " \">" + baris.nama + "</a>\n");
								}
							}

						} else if (baris.level < tingkat) {
							for (int i = 1; i <= (tingkat - baris.level); i++) {
								result.append("</ul></li>\n");
							}

							// result.append("<li id=\""+id+"\" style=\"margin-left: 15px;\" class=\"jstree-no-icons\">"+isi+"\n");
							if (baris.link == null) {
								result.append("\n<li><a href=\"#\" >" + baris.nama + "</a>\n");
							} else if (baris.link != null) {
								if (baris.link.startsWith("http://")) {
									result.append("\n<li><a href=\"" + baris.link + " \">" + baris.nama + "</a>\n");
								} else {
									result.append("\n<li><a href=\"" + path + "/" + baris.link + " \">" + baris.nama + "</a>\n");
								}
							}

						}
						tingkat = baris.level;

						count++;
					}
				}
				for (int i = 1; i <= (tingkat - 2); i++) {
					result.append("</li></ul>\n");
				}
				result.append("</ul>\n");
			}

		}
		
		logger.debug(result.toString());
		return result.toString();
	}

}
