package com.melawai.ppuc.services;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.melawai.ppuc.model.Audittrail;
import com.melawai.ppuc.model.AudittrailDetail;
import com.melawai.ppuc.model.HakBiaya;
import com.melawai.ppuc.model.HakBiayaHist;
import com.melawai.ppuc.persistence.HakBiayaHistMapper;
import com.melawai.ppuc.utils.CommonUtil;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Sun Jul 06 16:28:27 ICT 2014
 * @Description: Services for table hak_biaya_hist
 * @Revision	:
 */

@Service("hakbiayahistManager")
public class HakBiayaHistManager extends BaseService {

	private static Logger logger = Logger.getLogger(HakBiayaHistManager.class);

	@Autowired
	private HakBiayaHistMapper hakbiayahistMapper;

	/** Ambil DATA berdasarkan divisi_kd, subdiv_kd, dept_kd, lok_kd, kd_group, kd_biaya **/
	public HakBiayaHist get(Long id/*String divisi_kd, String subdiv_kd, String dept_kd, String lok_kd, String kd_group, String kd_biaya*/) {
		return hakbiayahistMapper.get(id/*divisi_kd, subdiv_kd, dept_kd, lok_kd, kd_group, kd_biaya*/);
	}
	
	public Date getLastSpTgl(String divisi_kd, String subdiv_kd, String dept_kd, String lok_kd, String kd_group, String kd_biaya) {
		return hakbiayahistMapper.getLastSpTgl(divisi_kd, subdiv_kd, dept_kd, lok_kd, kd_group, kd_biaya);
	}

	/** Apakah data dengan divisi_kd, subdiv_kd, dept_kd, lok_kd, kd_group, kd_biaya ini ada? **/
	public boolean exists(Long id/*,String divisi_kd, String subdiv_kd, String dept_kd, String lok_kd, String kd_group, String kd_biaya*/) {	
		return get(id/*,divisi_kd, subdiv_kd, dept_kd, lok_kd, kd_group, kd_biaya*/)!=null;
	}

	/** Delete data berdasarkan id **/
	@Transactional
	public void remove(Long id/*,String divisi_kd, String subdiv_kd, String dept_kd, String lok_kd, String kd_group, String kd_biaya*/) {
		hakbiayahistMapper.remove(id/*,divisi_kd, subdiv_kd, dept_kd, lok_kd, kd_group, kd_biaya*/);
	}

	/** Ambil jumlah seluruh data **/
	public int selectPagingCount(String search) {
		HakBiayaHist hakbiayahist=new HakBiayaHist();
		hakbiayahist.setSearch(search);
		return hakbiayahistMapper.selectPagingCount(hakbiayahist);
	}

	/** Ambil data paging **/
	public List<HakBiayaHist> selectPagingList(String search, String sort,String sortOrder, int page, int rowcount) {
		HakBiayaHist hakbiayahist=new HakBiayaHist();
		hakbiayahist.setSearch(search);
		 if(sort!=null)hakbiayahist.setSort(sort+" "+sortOrder);
		hakbiayahist.setPage(page);
		hakbiayahist.setRowcount(rowcount);
		return hakbiayahistMapper.selectPagingList(hakbiayahist);
	}

	/** Save Model **/
	@Transactional
	public HakBiayaHist save(HakBiayaHist hakbiayahist) {
		if (hakbiayahist.getId()==null) {
			
			hakbiayahist.setTgl_create(selectSysdate());
			hakbiayahist.setUser_create(CommonUtil.getCurrentUserId());
			
			Set<AudittrailDetail> changes=CommonUtil.changes(hakbiayahist,null);
			
			hakbiayahistMapper.insert(hakbiayahist);
			
			audittrail(Audittrail.Activity.TRANS, Audittrail.TransType.ADD, hakbiayahist.getClass().getSimpleName(), hakbiayahist.getItemId(), CommonUtil.getIpAddr(httpServletRequest), "ADD HAK BIAYA HIST", CommonUtil.getCurrentUser(), changes);
		} else {
			
			Set<AudittrailDetail> changes=CommonUtil.changes(hakbiayahist,get(hakbiayahist.id));
			hakbiayahistMapper.update(hakbiayahist);
			
			
			audittrail(Audittrail.Activity.TRANS, Audittrail.TransType.UPDATE, hakbiayahist.getClass().getSimpleName(), hakbiayahist.getItemId(), CommonUtil.getIpAddr(httpServletRequest), "UPDATE HAK BIAYA HIST", CommonUtil.getCurrentUser(),changes );
		} 
		return hakbiayahist;
	}
	/** Others Method **/

	}
