package com.melawai.ppuc.services;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.melawai.ppuc.model.Audittrail;
import com.melawai.ppuc.model.AudittrailDetail;
import com.melawai.ppuc.model.Departmen;
import com.melawai.ppuc.model.Lokasi;
import com.melawai.ppuc.persistence.LokasiMapper;
import com.melawai.ppuc.utils.CommonUtil;

/**
 * GENERATE BY BraisSpringMVCHelp
 * 
 * @since : Thu Jun 19 23:42:35 ICT 2014
 * @Description: Services for table lokasi
 * @Revision :
 */

@Service("lokasiManager")
public class LokasiManager extends BaseService {

	private static Logger logger = Logger.getLogger(LokasiManager.class);

	@Autowired
	private LokasiMapper lokasiMapper;

	/** Ambil DATA berdasarkan lok_kd **/
	public Lokasi get(String lok_kd, String dept_kd, String subdiv_kd, String divisi_kd) {
		return lokasiMapper.get(lok_kd, dept_kd, subdiv_kd, divisi_kd);
	}

	/** Apakah data dengan lok_kd ini ada? **/
	public boolean exists(String lok_kd, String dept_kd, String subdiv_kd, String divisi_kd) {
		return get(lok_kd, dept_kd, subdiv_kd, divisi_kd) != null;
	}

	/** Delete data berdasarkan lok_kd **/
	@Transactional
	public void remove(String lok_kd, String dept_kd, String subdiv_kd, String divisi_kd) {
		Lokasi tmp = get(lok_kd, dept_kd, subdiv_kd, divisi_kd);
		Set<AudittrailDetail> changes = CommonUtil.changes(tmp, null);
		lokasiMapper.remove(lok_kd, dept_kd, subdiv_kd, divisi_kd);
		audittrail(Audittrail.Activity.TRANS, Audittrail.TransType.DELETE, tmp.getClass().getSimpleName(), tmp.getItemId(), CommonUtil.getIpAddr(httpServletRequest), "DELETE LOKASI",
				CommonUtil.getCurrentUser(), changes);
	}

	/** Ambil jumlah seluruh data **/
	public int selectPagingCount(String search) {
		Lokasi lokasi = new Lokasi();
		lokasi.setSearch(search);
		return lokasiMapper.selectPagingCount(lokasi);
	}

	/** Ambil data paging **/
	public List<Lokasi> selectPagingList(String search, String sort, String sortOrder, int page, int rowcount) {
		Lokasi lokasi = new Lokasi();
		lokasi.setSearch(search);
		if (sort != null)
			lokasi.setSort(sort + " " + sortOrder);
		lokasi.setPage(page);
		lokasi.setRowcount(rowcount);
		return lokasiMapper.selectPagingList(lokasi);
	}

	/** Save Model **/
	@Transactional
	public Lokasi save(Lokasi lokasi) {
		lokasi.subdiv_kd=lokasi.subdiv_kd.substring(lokasi.subdiv_kd.lastIndexOf(".") + 1);
		lokasi.dept_kd=lokasi.dept_kd.substring(lokasi.dept_kd.lastIndexOf(".") + 1);
		
		if (!exists(lokasi.lok_kd, lokasi.dept_kd, lokasi.subdiv_kd, lokasi.divisi_kd)) {
			lokasi.setTgl_create(selectSysdate());
			lokasi.setUser_create(CommonUtil.getCurrentUserId());
			
			Set<AudittrailDetail> changes = CommonUtil.changes(lokasi, get(lokasi.lok_kd, lokasi.dept_kd, lokasi.subdiv_kd, lokasi.divisi_kd));
			
			lokasiMapper.insert(lokasi);
			audittrail(Audittrail.Activity.TRANS, Audittrail.TransType.ADD, lokasi.getClass().getSimpleName(), lokasi.getItemId(), CommonUtil.getIpAddr(httpServletRequest), "ADD Lokasi",
					CommonUtil.getCurrentUser(), changes);
		} else {
			lokasi.setTgl_update(selectSysdate());
			lokasi.setUser_update(CommonUtil.getCurrentUserId());

			Set<AudittrailDetail> changes = CommonUtil.changes(lokasi, get(lokasi.lok_kd, lokasi.dept_kd, lokasi.subdiv_kd, lokasi.divisi_kd));

			lokasiMapper.update(lokasi);

			audittrail(Audittrail.Activity.TRANS, Audittrail.TransType.UPDATE, lokasi.getClass().getSimpleName(), lokasi.getItemId(), CommonUtil.getIpAddr(httpServletRequest), "UPDATE LOKASI",
					CommonUtil.getCurrentUser(), changes);
		}
		return lokasi;
	}

	/** Others Method **/

	@Transactional
	public void save(List<Lokasi> lslokasi) {
		for (Lokasi lokasi : lslokasi) {
			Lokasi tmpLokasi = get(lokasi.lok_kd, lokasi.dept_kd, lokasi.subdiv_kd, lokasi.divisi_kd);

			if (tmpLokasi != null) {
				lokasi.tgl_create = tmpLokasi.tgl_create;
			} else {
				lokasi.tgl_create = null;
			}

			save(lokasi);
		}

	}

}
