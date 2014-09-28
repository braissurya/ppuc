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
import com.melawai.ppuc.model.Lokasi;
import com.melawai.ppuc.model.Propinsi;
import com.melawai.ppuc.persistence.PropinsiMapper;
import com.melawai.ppuc.utils.CommonUtil;

/**
 * GENERATE BY BraisSpringMVCHelp
 * 
 * @since : Sun Jul 06 16:28:31 ICT 2014
 * @Description: Services for table propinsi
 * @Revision :
 */

@Service("propinsiManager")
public class PropinsiManager extends BaseService {

	private static Logger logger = Logger.getLogger(PropinsiManager.class);

	@Autowired
	private PropinsiMapper propinsiMapper;

	/** Ambil DATA berdasarkan propinsi **/
	public Propinsi get(String propinsi) {
		return propinsiMapper.get(propinsi);
	}

	/** Apakah data dengan propinsi ini ada? **/
	public boolean exists(String propinsi) {
		return get(propinsi) != null;
	}

	/** Delete data berdasarkan propinsi **/
	@Transactional
	public void remove(String propinsi) {
		
		Propinsi tmp = get(propinsi);
		Set<AudittrailDetail> changes = CommonUtil.changes(tmp, null);
		propinsiMapper.remove(propinsi);
		audittrail(Audittrail.Activity.TRANS, Audittrail.TransType.DELETE, tmp.getClass().getSimpleName(), tmp.getItemId(), CommonUtil.getIpAddr(httpServletRequest), "DELETE PROPINSI",
				CommonUtil.getCurrentUser(), changes);
	}

	/** Ambil jumlah seluruh data **/
	public int selectPagingCount(String search) {
		Propinsi propinsi = new Propinsi();
		propinsi.setSearch(search);
		return propinsiMapper.selectPagingCount(propinsi);
	}

	/** Ambil data paging **/
	public List<Propinsi> selectPagingList(String search, String sort, String sortOrder, int page, int rowcount) {
		Propinsi propinsi = new Propinsi();
		propinsi.setSearch(search);
		if (sort != null)
			propinsi.setSort(sort + " " + sortOrder);
		propinsi.setPage(page);
		propinsi.setRowcount(rowcount);
		return propinsiMapper.selectPagingList(propinsi);
	}

	/** Save Model **/
	@Transactional
	public Propinsi save(Propinsi propinsi) {
		if (!exists(propinsi.propinsi)) {
			propinsi.setTgl_create(selectSysdate());
			propinsi.setUser_create(CommonUtil.getCurrentUserId());

			Set<AudittrailDetail> changes = CommonUtil.changes(propinsi, get(propinsi.propinsi));

			propinsiMapper.insert(propinsi);
			audittrail(Audittrail.Activity.TRANS, Audittrail.TransType.ADD, propinsi.getClass().getSimpleName(), propinsi.getItemId(), CommonUtil.getIpAddr(httpServletRequest), "ADD propinsi", CommonUtil.getCurrentUser(), changes);
		} else {

			propinsi.setTgl_update(selectSysdate());
			propinsi.setUser_update(CommonUtil.getCurrentUserId());

			Set<AudittrailDetail> changes = CommonUtil.changes(propinsi, get(propinsi.propinsi));

			propinsiMapper.update(propinsi);

			audittrail(Audittrail.Activity.TRANS, Audittrail.TransType.UPDATE, propinsi.getClass().getSimpleName(), propinsi.getItemId(), CommonUtil.getIpAddr(httpServletRequest), "UPDATE PROPINSI", CommonUtil.getCurrentUser(), changes);
		}
		return propinsi;
	}
	
	/** Others Method **/

	@Transactional
	public void save(List<Propinsi> lsPropinsi) {
		for (Propinsi propinsi : lsPropinsi) {
			Propinsi tmpPropinsi = get(propinsi.propinsi);

			if (tmpPropinsi != null) {
				propinsi.tgl_create = tmpPropinsi.tgl_create;
			} else {
				propinsi.tgl_create = null;
			}

			save(propinsi);
		}

	}
}
