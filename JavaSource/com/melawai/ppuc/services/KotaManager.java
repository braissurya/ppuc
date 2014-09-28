package com.melawai.ppuc.services;

import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.melawai.ppuc.model.Audittrail;
import com.melawai.ppuc.model.AudittrailDetail;
import com.melawai.ppuc.model.Kota;
import com.melawai.ppuc.persistence.KotaMapper;
import com.melawai.ppuc.utils.CommonUtil;

/**
 * GENERATE BY BraisSpringMVCHelp
 * 
 * @since : Sun Jul 06 16:28:28 ICT 2014
 * @Description: Services for table kota
 * @Revision :
 */

@Service("kotaManager")
public class KotaManager extends BaseService {

	private static Logger logger = Logger.getLogger(KotaManager.class);

	@Autowired
	private KotaMapper kotaMapper;

	/** Ambil DATA berdasarkan propinsi, kota **/
	public Kota get(String propinsi, String kota) {
		return kotaMapper.get(propinsi, kota);
	}

	/** Apakah data dengan propinsi, kota ini ada? **/
	public boolean exists(String propinsi, String kota) {
		return get(propinsi, kota) != null;
	}

	/** Delete data berdasarkan id **/
	@Transactional
	public void remove(String propinsi, String kota) {

		Kota tmp = get(propinsi, kota);
		Set<AudittrailDetail> changes = CommonUtil.changes(tmp, null);
		kotaMapper.remove(propinsi, kota);
		audittrail(Audittrail.Activity.TRANS, Audittrail.TransType.DELETE, tmp.getClass().getSimpleName(), tmp.getItemId(), CommonUtil.getIpAddr(httpServletRequest), "DELETE KOTA", CommonUtil.getCurrentUser(), changes);
	}

	/** Ambil jumlah seluruh data **/
	public int selectPagingCount(String search) {
		Kota kota = new Kota();
		kota.setSearch(search);
		return kotaMapper.selectPagingCount(kota);
	}

	/** Ambil data paging **/
	public List<Kota> selectPagingList(String search, String sort, String sortOrder, int page, int rowcount) {
		Kota kota = new Kota();
		kota.setSearch(search);
		if (sort != null)
			kota.setSort(sort + " " + sortOrder);
		kota.setPage(page);
		kota.setRowcount(rowcount);
		return kotaMapper.selectPagingList(kota);
	}

	/** Save Model **/
	@Transactional
	public Kota save(Kota kota) {
		if (!exists(kota.propinsi, kota.kota)) {

			kota.setTgl_create(selectSysdate());
			kota.setUser_create(CommonUtil.getCurrentUserId());

			Set<AudittrailDetail> changes = CommonUtil.changes(kota, get(kota.propinsi, kota.kota));

			kotaMapper.insert(kota);
			audittrail(Audittrail.Activity.TRANS, Audittrail.TransType.ADD, kota.getClass().getSimpleName(), kota.getItemId(), CommonUtil.getIpAddr(httpServletRequest), "ADD kota", CommonUtil.getCurrentUser(), changes);
		} else {

			kota.setTgl_update(selectSysdate());
			kota.setUser_update(CommonUtil.getCurrentUserId());

			Set<AudittrailDetail> changes = CommonUtil.changes(kota, get(kota.propinsi, kota.kota));

			kotaMapper.update(kota);

			audittrail(Audittrail.Activity.TRANS, Audittrail.TransType.UPDATE, kota.getClass().getSimpleName(), kota.getItemId(), CommonUtil.getIpAddr(httpServletRequest), "UPDATE kota", CommonUtil.getCurrentUser(), changes);
		}
		return kota;
	}
	/** Others Method **/
	
	@Transactional
	public void save(List<Kota> lskota) {
		for (Kota kota : lskota) {
			Kota tmpkota = get(kota.propinsi, kota.kota);

			if (tmpkota != null) {
				kota.tgl_create = tmpkota.tgl_create;
			} else {
				kota.tgl_create = null;
			}

			save(kota);
		}

	}

}
