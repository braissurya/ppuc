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
import com.melawai.ppuc.model.Subdivisi;
import com.melawai.ppuc.persistence.SubdivisiMapper;
import com.melawai.ppuc.utils.CommonUtil;

/**
 * GENERATE BY BraisSpringMVCHelp
 * 
 * @since : Thu Jun 19 23:42:38 ICT 2014
 * @Description: Services for table subdivisi
 * @Revision :
 */

@Service("subdivisiManager")
public class SubdivisiManager extends BaseService {

	private static Logger logger = Logger.getLogger(SubdivisiManager.class);

	@Autowired
	private SubdivisiMapper subdivisiMapper;
	
	

	/** Ambil DATA berdasarkan subdiv_kd **/
	public Subdivisi get(String subdiv_kd, String divisi_kd) {
		return subdivisiMapper.get(subdiv_kd, divisi_kd);
	}

	/** Apakah data dengan subdiv_kd ini ada? **/
	public boolean exists(String subdiv_kd, String divisi_kd) {
		return get(subdiv_kd, divisi_kd) != null;
	}

	/** Delete data berdasarkan subdiv_kd **/
	@Transactional
	public void remove(String subdiv_kd, String divisi_kd) {
		Subdivisi tmp = get(subdiv_kd, divisi_kd);
		Set<AudittrailDetail> changes = CommonUtil.changes(tmp, null);
		subdivisiMapper.remove(subdiv_kd, divisi_kd);
		audittrail(Audittrail.Activity.TRANS, Audittrail.TransType.DELETE, tmp.getClass().getSimpleName(), tmp.getItemId(), CommonUtil.getIpAddr(httpServletRequest), "DELETE SUB DIVISI",
				CommonUtil.getCurrentUser(), changes);
	}

	/** Ambil jumlah seluruh data **/
	public int selectPagingCount(String search) {
		Subdivisi subdivisi = new Subdivisi();
		subdivisi.setSearch(search);
		return subdivisiMapper.selectPagingCount(subdivisi);
	}

	/** Ambil data paging **/
	public List<Subdivisi> selectPagingList(String search, String sort, String sortOrder, int page, int rowcount) {
		Subdivisi subdivisi = new Subdivisi();
		subdivisi.setSearch(search);
		if (sort != null)
			subdivisi.setSort(sort + " " + sortOrder);
		subdivisi.setPage(page);
		subdivisi.setRowcount(rowcount);
		return subdivisiMapper.selectPagingList(subdivisi);
	}

	/** Save Model **/
	@Transactional
	public Subdivisi save(Subdivisi subdivisi) {
		if (subdivisi.getTgl_create() == null) {
			subdivisi.setTgl_create(new Date());
			subdivisi.setUser_create(CommonUtil.getCurrentUserId());

			Set<AudittrailDetail> changes = CommonUtil.changes(subdivisi, get(subdivisi.getSubdiv_kd(), subdivisi.getDivisi_kd()));

			subdivisiMapper.insert(subdivisi);

			audittrail(Audittrail.Activity.TRANS, Audittrail.TransType.ADD, subdivisi.getClass().getSimpleName(), subdivisi.getItemId(), CommonUtil.getIpAddr(httpServletRequest), "ADD SUB DIVISI",
					CommonUtil.getCurrentUser(), changes);
		} else {
			subdivisi.setTgl_update(new Date());
			subdivisi.setUser_update(CommonUtil.getCurrentUserId());

			Set<AudittrailDetail> changes = CommonUtil.changes(subdivisi, get(subdivisi.getSubdiv_kd(), subdivisi.getDivisi_kd()));

			subdivisiMapper.update(subdivisi);

			audittrail(Audittrail.Activity.TRANS, Audittrail.TransType.UPDATE, subdivisi.getClass().getSimpleName(), subdivisi.getItemId(), CommonUtil.getIpAddr(httpServletRequest),
					"UPDATE SUB DIVISI", CommonUtil.getCurrentUser(), changes);
		}
		return subdivisi;
	}

	/** Others Method **/

	@Transactional
	public void save(List<Subdivisi> lssubdivisi) {
		for (Subdivisi subdivisi : lssubdivisi) {
			Subdivisi tmpsubdiv = get(subdivisi.subdiv_kd, subdivisi.divisi_kd);

			if (tmpsubdiv != null) {
				subdivisi.tgl_create = tmpsubdiv.tgl_create;
			} else {
				subdivisi.tgl_create = null;
			}

			save(subdivisi);
		}

	}
}
