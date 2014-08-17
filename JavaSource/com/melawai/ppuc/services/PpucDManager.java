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
import com.melawai.ppuc.model.PpucD;
import com.melawai.ppuc.model.Subdivisi;
import com.melawai.ppuc.persistence.PpucDMapper;
import com.melawai.ppuc.utils.CommonUtil;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Thu Jun 19 23:42:37 ICT 2014
 * @Description: Services for table ppuc_d
 * @Revision	:
 */

@Service("ppucdManager")
public class PpucDManager extends BaseService {

	private static Logger logger = Logger.getLogger(PpucDManager.class);

	@Autowired
	private PpucDMapper ppucdMapper;

	/** Ambil DATA berdasarkan divisi_kd, subdiv_kd, dept_kd, lok_kd, no_ppuc, tgl_ppuc, kd_biaya **/
	public PpucD get(String divisi_kd, String subdiv_kd, String dept_kd, String lok_kd, String no_ppuc, Date tgl_ppuc, String kd_biaya) {
		return ppucdMapper.get(divisi_kd, subdiv_kd, dept_kd, lok_kd, no_ppuc, tgl_ppuc, kd_biaya);
	}

	/** Apakah data dengan divisi_kd, subdiv_kd, dept_kd, lok_kd, no_ppuc, tgl_ppuc, kd_biaya ini ada? **/
	public boolean exists(String divisi_kd, String subdiv_kd, String dept_kd, String lok_kd, String no_ppuc, Date tgl_ppuc, String kd_biaya) {	
		return get(divisi_kd, subdiv_kd, dept_kd, lok_kd, no_ppuc, tgl_ppuc, kd_biaya)!=null;
	}

	/** Delete data berdasarkan id **/
	@Transactional
	public void remove(String divisi_kd, String subdiv_kd, String dept_kd, String lok_kd, String no_ppuc, Date tgl_ppuc, String kd_biaya) {
		PpucD tmp = get(divisi_kd, subdiv_kd, dept_kd, lok_kd, no_ppuc, tgl_ppuc, kd_biaya);
		Set<AudittrailDetail> changes = CommonUtil.changes(tmp, null);
		ppucdMapper.remove(divisi_kd, subdiv_kd, dept_kd, lok_kd, no_ppuc, tgl_ppuc, kd_biaya);
		audittrail(Audittrail.Activity.TRANS, Audittrail.TransType.DELETE, tmp.getClass().getSimpleName(), tmp.getItemId(), CommonUtil.getIpAddr(httpServletRequest), "DELETE PPUCD",
				CommonUtil.getCurrentUser(), changes);
	}

	/** Ambil jumlah seluruh data **/
	public int selectPagingCount(String search) {
		PpucD ppucd=new PpucD();
		ppucd.setSearch(search);
		return ppucdMapper.selectPagingCount(ppucd);
	}

	/** Ambil data paging **/
	public List<PpucD> selectPagingList(String search, String sort,String sortOrder, int page, int rowcount) {
		PpucD ppucd=new PpucD();
		ppucd.setSearch(search);
		 if(sort!=null)ppucd.setSort(sort+" "+sortOrder);
		ppucd.setPage(page);
		ppucd.setRowcount(rowcount);
		return ppucdMapper.selectPagingList(ppucd);
	}

	/** Save Model **/
	@Transactional
	public PpucD save(PpucD ppucd) {
		if (ppucd.getTgl_create()==null) {
			ppucd.tgl_create=selectSysdate();
			ppucd.user_create=CommonUtil.getCurrentUserId();

			Set<AudittrailDetail> changes = CommonUtil.changes(ppucd, get(ppucd.divisi_kd, ppucd.subdiv_kd, ppucd.dept_kd, ppucd.lok_kd, ppucd.no_ppuc, ppucd.tgl_ppuc, ppucd.kd_biaya));

			ppucdMapper.insert(ppucd);

			audittrail(Audittrail.Activity.TRANS, Audittrail.TransType.ADD, ppucd.getClass().getSimpleName(), ppucd.getItemId(), CommonUtil.getIpAddr(httpServletRequest), "ADD PPUCD",
					CommonUtil.getCurrentUser(), changes);
		} else {
			ppucd.setTgl_update(selectSysdate());
			ppucd.setUser_update(CommonUtil.getCurrentUserId());

			Set<AudittrailDetail> changes = CommonUtil.changes(ppucd,  get(ppucd.divisi_kd, ppucd.subdiv_kd, ppucd.dept_kd, ppucd.lok_kd, ppucd.no_ppuc, ppucd.tgl_ppuc, ppucd.kd_biaya));

			ppucdMapper.update(ppucd);

			audittrail(Audittrail.Activity.TRANS, Audittrail.TransType.UPDATE, ppucd.getClass().getSimpleName(), ppucd.getItemId(), CommonUtil.getIpAddr(httpServletRequest),
					"UPDATE PPUCD", CommonUtil.getCurrentUser(), changes);
		} 
		return ppucd;
	}
	/** Others Method **/

	}
