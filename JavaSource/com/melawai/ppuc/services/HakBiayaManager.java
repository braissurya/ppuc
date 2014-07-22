package com.melawai.ppuc.services;

import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.melawai.ppuc.model.Audittrail;
import com.melawai.ppuc.model.AudittrailDetail;
import com.melawai.ppuc.model.Divisi;
import com.melawai.ppuc.model.HakBiaya;
import com.melawai.ppuc.persistence.HakBiayaMapper;
import com.melawai.ppuc.utils.CommonUtil;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Thu Jun 19 23:42:34 ICT 2014
 * @Description: Services for table hak_biaya
 * @Revision	:
 */

@Service("hakbiayaManager")
public class HakBiayaManager extends BaseService {

	private static Logger logger = Logger.getLogger(HakBiayaManager.class);

	@Autowired
	private HakBiayaMapper hakbiayaMapper;

	/** Ambil DATA berdasarkan divisi_kd, subdiv_kd, kd_group, kd_biaya **/
	public HakBiaya get(String divisi_kd, String subdiv_kd,String dept_kd,String lok_kd, String kd_group, String kd_biaya) {
		return hakbiayaMapper.get(divisi_kd, subdiv_kd, dept_kd, lok_kd, kd_group, kd_biaya);
	}

	/** Apakah data dengan divisi_kd, subdiv_kd, kd_group, kd_biaya ini ada? **/
	public boolean exists(String divisi_kd, String subdiv_kd,String dept_kd,String lok_kd, String kd_group, String kd_biaya) {	
		return get(divisi_kd, subdiv_kd, dept_kd, lok_kd, kd_group, kd_biaya)!=null;
	}

	/** Delete data berdasarkan id **/
	@Transactional
	public void remove(String divisi_kd, String subdiv_kd,String dept_kd,String lok_kd, String kd_group, String kd_biaya) {
		
		HakBiaya tmp=get(divisi_kd, subdiv_kd, dept_kd, lok_kd, kd_group, kd_biaya);
		Set<AudittrailDetail> changes=CommonUtil.changes(tmp,null);
		hakbiayaMapper.remove(divisi_kd, subdiv_kd, dept_kd, lok_kd, kd_group, kd_biaya);
		audittrail(Audittrail.Activity.TRANS, Audittrail.TransType.DELETE, tmp.getClass().getSimpleName(), tmp.getItemId(), CommonUtil.getIpAddr(httpServletRequest), "DELETE HAK AKSES", CommonUtil.getCurrentUser(), changes);
	
	}

	/** Ambil jumlah seluruh data **/
	public int selectPagingCount(String search) {
		HakBiaya hakbiaya=new HakBiaya();
		hakbiaya.setSearch(search);
		return hakbiayaMapper.selectPagingCount(hakbiaya);
	}

	/** Ambil data paging **/
	public List<HakBiaya> selectPagingList(String search, String sort,String sortOrder, int page, int rowcount) {
		HakBiaya hakbiaya=new HakBiaya();
		hakbiaya.setSearch(search);
		 if(sort!=null)hakbiaya.setSort(sort+" "+sortOrder);
		hakbiaya.setPage(page);
		hakbiaya.setRowcount(rowcount);
		return hakbiayaMapper.selectPagingList(hakbiaya);
	}

	/** Save Model **/
	@Transactional
	public HakBiaya save(HakBiaya hakbiaya) {
		if (!exists(hakbiaya.divisi_kd, hakbiaya.subdiv_kd, hakbiaya.dept_kd, hakbiaya.lok_kd, hakbiaya.kd_group, hakbiaya.kd_biaya)) {
			
			hakbiaya.setTgl_create(selectSysdate());
			hakbiaya.setUser_create(CommonUtil.getCurrentUserId());
			
			Set<AudittrailDetail> changes=CommonUtil.changes(hakbiaya,get(hakbiaya.divisi_kd, hakbiaya.subdiv_kd, hakbiaya.dept_kd, hakbiaya.lok_kd, hakbiaya.kd_group, hakbiaya.kd_biaya));
			
			hakbiayaMapper.insert(hakbiaya);
			
			audittrail(Audittrail.Activity.TRANS, Audittrail.TransType.ADD, hakbiaya.getClass().getSimpleName(), hakbiaya.getItemId(), CommonUtil.getIpAddr(httpServletRequest), "ADD HAK BIAYA", CommonUtil.getCurrentUser(), changes);
		} else {
			
			Set<AudittrailDetail> changes=CommonUtil.changes(hakbiaya,get(hakbiaya.divisi_kd, hakbiaya.subdiv_kd, hakbiaya.dept_kd, hakbiaya.lok_kd, hakbiaya.kd_group, hakbiaya.kd_biaya));			
			
			hakbiayaMapper.update(hakbiaya);
			
			audittrail(Audittrail.Activity.TRANS, Audittrail.TransType.UPDATE, hakbiaya.getClass().getSimpleName(), hakbiaya.getItemId(), CommonUtil.getIpAddr(httpServletRequest), "UPDATE HAK BIAYA", CommonUtil.getCurrentUser(),changes );
		
		} 
		return hakbiaya;
	}
	/** Others Method **/

	}
