package com.melawai.ppuc.services;

import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.melawai.ppuc.model.Audittrail;
import com.melawai.ppuc.model.AudittrailDetail;
import com.melawai.ppuc.model.Lokasi;
import com.melawai.ppuc.model.UserDivisi;
import com.melawai.ppuc.persistence.UserDivisiMapper;
import com.melawai.ppuc.utils.CommonUtil;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Thu Jun 19 23:42:39 ICT 2014
 * @Description: Services for table user_divisi
 * @Revision	:
 */

@Service("userdivisiManager")
public class UserDivisiManager extends BaseService {

	private static Logger logger = Logger.getLogger(UserDivisiManager.class);

	@Autowired
	private UserDivisiMapper userdivisiMapper;

	/** Ambil DATA berdasarkan id_user_divisi, user_id, divisi_kd, subdiv_kd, dept_kd **/
	public List<UserDivisi> get(Long id_user_divisi,String user_id, String divisi_kd, String subdiv_kd, String dept_kd, String lok_kd) {
		return userdivisiMapper.get(id_user_divisi, user_id, divisi_kd, subdiv_kd, dept_kd, lok_kd);
	}

	/** Apakah data dengan id_user_divisi, user_id, divisi_kd, subdiv_kd, dept_kd ini ada? **/
	public boolean exists(Long id_user_divisi,String user_id, String divisi_kd, String subdiv_kd, String dept_kd, String lok_kd) {	
		return userdivisiMapper.exists(id_user_divisi,user_id, divisi_kd, subdiv_kd, dept_kd, lok_kd).size()>0;
	}
	
	public UserDivisi get(Long id_user_divisi) {
		List<UserDivisi> sd=userdivisiMapper.get(id_user_divisi, null, null, null, null, null);
		return sd.isEmpty()?null:sd.get(0);
	}
	
	public  List<UserDivisi> get(String user_id) {
		return userdivisiMapper.get(null, user_id, null, null, null, null);
	}
	
	public UserDivisi getDivisiNSubdivUser(String user_id){
		List<UserDivisi> tmp=get(user_id);
		return tmp.isEmpty()?new UserDivisi():tmp.get(0);
	}

	/** Delete data berdasarkan id_user_divisi **/
	@Transactional
	public void remove(Long id_user_divisi) {
		
		UserDivisi tmp = get(id_user_divisi);
		Set<AudittrailDetail> changes = CommonUtil.changes(tmp, null);
		userdivisiMapper.remove( id_user_divisi);
		audittrail(Audittrail.Activity.TRANS, Audittrail.TransType.DELETE, tmp.getClass().getSimpleName(), tmp.getItemId(), CommonUtil.getIpAddr(httpServletRequest), "DELETE USERDIVISI",
				CommonUtil.getCurrentUser(), changes);
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
		userdivisi.subdiv_kd=userdivisi.subdiv_kd.substring(userdivisi.subdiv_kd.lastIndexOf(".") + 1);
		userdivisi.dept_kd=userdivisi.dept_kd.substring(userdivisi.dept_kd.lastIndexOf(".") + 1);
		userdivisi.lok_kd=userdivisi.lok_kd.substring(userdivisi.lok_kd.lastIndexOf(".") + 1);
		
		
		if (userdivisi.id_user_divisi==null) {
		
			userdivisi.setTgl_create(selectSysdate());
			userdivisi.setUser_create(CommonUtil.getCurrentUserId());

			Set<AudittrailDetail> changes = CommonUtil.changes(userdivisi, get(userdivisi.id_user_divisi));

			userdivisiMapper.insert(userdivisi);
			audittrail(Audittrail.Activity.TRANS, Audittrail.TransType.ADD, userdivisi.getClass().getSimpleName(), userdivisi.getItemId(), CommonUtil.getIpAddr(httpServletRequest), "ADD USERDIVISI",
					CommonUtil.getCurrentUser(), changes);
		} else {
			

			Set<AudittrailDetail> changes = CommonUtil.changes(userdivisi, get(userdivisi.id_user_divisi));
			userdivisiMapper.update(userdivisi);
			
			audittrail(Audittrail.Activity.TRANS, Audittrail.TransType.UPDATE, userdivisi.getClass().getSimpleName(), userdivisi.getItemId(), CommonUtil.getIpAddr(httpServletRequest), "UPDATE USERDIVISI",
					CommonUtil.getCurrentUser(), changes);
		} 
		return userdivisi;
	}
	/** Others Method **/

	}
