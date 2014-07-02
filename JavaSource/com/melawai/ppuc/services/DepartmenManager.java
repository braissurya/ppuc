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
import com.melawai.ppuc.model.Departmen;
import com.melawai.ppuc.persistence.DepartmenMapper;
import com.melawai.ppuc.utils.CommonUtil;

/**
 * GENERATE BY BraisSpringMVCHelp
 * 
 * @since : Thu Jun 19 23:42:31 ICT 2014
 * @Description: Services for table departmen
 * @Revision :
 */

@Service("departmenManager")
public class DepartmenManager extends BaseService {

	private static Logger logger = Logger.getLogger(DepartmenManager.class);

	@Autowired
	private DepartmenMapper departmenMapper;

	/** Ambil DATA berdasarkan dept_kd **/
	public Departmen get(String dept_kd, String subdiv_kd, String divisi_kd) {
		return departmenMapper.get(dept_kd, subdiv_kd, divisi_kd);
	}

	/** Apakah data dengan dept_kd ini ada? **/
	public boolean exists(String dept_kd, String subdiv_kd, String divisi_kd) {
		return get(dept_kd, subdiv_kd, divisi_kd) != null;
	}

	/** Delete data berdasarkan dept_kd **/
	@Transactional
	public void remove(String dept_kd, String subdiv_kd, String divisi_kd) {
		Departmen tmp = get(dept_kd, subdiv_kd, divisi_kd);
		Set<AudittrailDetail> changes = CommonUtil.changes(tmp, null);
		departmenMapper.remove(dept_kd, subdiv_kd, divisi_kd);
		audittrail(Audittrail.Activity.TRANS, Audittrail.TransType.DELETE, tmp.getClass().getSimpleName(), tmp.getItemId(), CommonUtil.getIpAddr(httpServletRequest), "DELETE DEPARTMENT",
				CommonUtil.getCurrentUser(), changes);
	}

	/** Ambil jumlah seluruh data **/
	public int selectPagingCount(String search) {
		Departmen departmen = new Departmen();
		departmen.setSearch(search);
		return departmenMapper.selectPagingCount(departmen);
	}

	/** Ambil data paging **/
	public List<Departmen> selectPagingList(String search, String sort, String sortOrder, int page, int rowcount) {
		Departmen departmen = new Departmen();
		departmen.setSearch(search);
		if (sort != null)
			departmen.setSort(sort + " " + sortOrder);
		departmen.setPage(page);
		departmen.setRowcount(rowcount);
		return departmenMapper.selectPagingList(departmen);
	}

	/** Save Model **/
	@Transactional
	public Departmen save(Departmen departmen) {
		if (departmen.getTgl_create() == null) {
			departmen.setTgl_create(new Date());
			departmen.setUser_create(CommonUtil.getCurrentUserId());

			Set<AudittrailDetail> changes = CommonUtil.changes(departmen, get(departmen.dept_kd,departmen.subdiv_kd, departmen.divisi_kd));

			departmenMapper.insert(departmen);

			audittrail(Audittrail.Activity.TRANS, Audittrail.TransType.ADD, departmen.getClass().getSimpleName(), departmen.getItemId(), CommonUtil.getIpAddr(httpServletRequest), "ADD DEPARTMENT",
					CommonUtil.getCurrentUser(), changes);
		} else {
			departmen.setTgl_update(new Date());
			departmen.setUser_update(CommonUtil.getCurrentUserId());

			Set<AudittrailDetail> changes = CommonUtil.changes(departmen,get(departmen.dept_kd, departmen.subdiv_kd, departmen.divisi_kd));

			departmenMapper.update(departmen);

			audittrail(Audittrail.Activity.TRANS, Audittrail.TransType.UPDATE, departmen.getClass().getSimpleName(), departmen.getItemId(), CommonUtil.getIpAddr(httpServletRequest),
					"UPDATE DEPARTMENT", CommonUtil.getCurrentUser(), changes);
		}
		return departmen;
	}

	/** Others Method **/

	@Transactional
	public void save(List<Departmen> lsdepartmen) {
		for (Departmen departmen : lsdepartmen) {
			Departmen tmpdepartmen = get(departmen.dept_kd, departmen.subdiv_kd, departmen.divisi_kd);

			if (tmpdepartmen != null) {
				departmen.tgl_create = tmpdepartmen.tgl_create;
			} else {
				departmen.tgl_create = null;
			}

			save(departmen);
		}

	}
}
