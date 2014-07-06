package com.melawai.ppuc.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.melawai.ppuc.model.UserDivisi;
import com.melawai.ppuc.persistence.UserDivisiMapper;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Thu Jun 19 23:42:39 ICT 2014
 * @Description: Services for table user_divisi
 * @Revision	:
 */

@Service("userdivisiManager")
public class UserDivisiManager {

	private static Logger logger = Logger.getLogger(UserDivisiManager.class);

	@Autowired
	private UserDivisiMapper userdivisiMapper;

	/** Ambil DATA berdasarkan id_user_divisi, user_id, divisi_kd, subdiv_kd, dept_kd **/
	public UserDivisi get(Long id_user_divisi, String user_id, String divisi_kd, String subdiv_kd, String dept_kd) {
		return userdivisiMapper.get(id_user_divisi, user_id, divisi_kd, subdiv_kd, dept_kd);
	}

	/** Apakah data dengan id_user_divisi, user_id, divisi_kd, subdiv_kd, dept_kd ini ada? **/
	public boolean exists(Long id_user_divisi, String user_id, String divisi_kd, String subdiv_kd, String dept_kd) {	
		return get(id_user_divisi, user_id, divisi_kd, subdiv_kd, dept_kd)!=null;
	}

	/** Delete data berdasarkan id_user_divisi **/
	@Transactional
	public void remove(Long id_user_divisi, String user_id, String divisi_kd, String subdiv_kd, String dept_kd) {
		userdivisiMapper.remove(id_user_divisi, user_id, divisi_kd, subdiv_kd, dept_kd);
	}

	/** Ambil jumlah seluruh data **/
	public int selectPagingCount(String search) {
		UserDivisi userdivisi=new UserDivisi();
		userdivisi.setSearch(search);
		return userdivisiMapper.selectPagingCount(userdivisi);
	}

	/** Ambil data paging **/
	public List<UserDivisi> selectPagingList(String search, String sort,String sortOrder, int page, int rowcount) {
		UserDivisi userdivisi=new UserDivisi();
		userdivisi.setSearch(search);
		 if(sort!=null)userdivisi.setSort(sort+" "+sortOrder);
		userdivisi.setPage(page);
		userdivisi.setRowcount(rowcount);
		return userdivisiMapper.selectPagingList(userdivisi);
	}

	/** Save Model **/
	@Transactional
	public UserDivisi save(UserDivisi userdivisi) {
		if (userdivisi.getId_user_divisi()==null) {
			userdivisiMapper.insert(userdivisi);
		} else {
			userdivisiMapper.update(userdivisi);
		} 
		return userdivisi;
	}
	/** Others Method **/

	}
