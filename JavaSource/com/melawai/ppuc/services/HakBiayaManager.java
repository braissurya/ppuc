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
import com.melawai.ppuc.model.Divisi;
import com.melawai.ppuc.model.HakBiaya;
import com.melawai.ppuc.model.HakBiayaHist;
import com.melawai.ppuc.persistence.HakBiayaMapper;
import com.melawai.ppuc.utils.CommonUtil;

/**
 * GENERATE BY BraisSpringMVCHelp
 * 
 * @since : Thu Jun 19 23:42:34 ICT 2014
 * @Description: Services for table hak_biaya
 * @Revision :
 */

@Service("hakbiayaManager")
public class HakBiayaManager extends BaseService {

	private static Logger logger = Logger.getLogger(HakBiayaManager.class);

	@Autowired
	private HakBiayaMapper hakbiayaMapper;

	@Autowired
	private HakBiayaHistManager hakBiayaHistManager;

	/** Ambil DATA berdasarkan divisi_kd, subdiv_kd, kd_group, kd_biaya **/
	public HakBiaya get(String divisi_kd, String subdiv_kd, String dept_kd, String lok_kd, String kd_group, String kd_biaya) {
		return hakbiayaMapper.get(null, divisi_kd, subdiv_kd, dept_kd, lok_kd, kd_group, kd_biaya);
	}

	public HakBiaya get(Long id) {
		return hakbiayaMapper.get(id, null, null, null, null, null, null);
	}

	/** Apakah data dengan divisi_kd, subdiv_kd, kd_group, kd_biaya ini ada? **/
	public boolean exists(String divisi_kd, String subdiv_kd, String dept_kd, String lok_kd, String kd_group, String kd_biaya) {
		return get(divisi_kd, subdiv_kd, dept_kd, lok_kd, kd_group, kd_biaya) != null;
	}

	public Date getLastSpTgl(String divisi_kd, String subdiv_kd, String dept_kd, String lok_kd, String kd_group, String kd_biaya) {
		return hakBiayaHistManager.getLastSpTgl(divisi_kd, subdiv_kd, dept_kd, lok_kd, kd_group, kd_biaya);
	}

	/** Delete data berdasarkan id **/
	@Transactional
	public void remove(Long id/*
							 * ,String divisi_kd, String subdiv_kd,String
							 * dept_kd,String lok_kd, String kd_group, String
							 * kd_biaya
							 */) {

		HakBiaya tmp = get(id/*
							 * ,divisi_kd, subdiv_kd, dept_kd, lok_kd, kd_group,
							 * kd_biaya
							 */);
		Set<AudittrailDetail> changes = CommonUtil.changes(tmp, null);
		hakbiayaMapper.remove(id/*
								 * ,divisi_kd, subdiv_kd, dept_kd, lok_kd,
								 * kd_group, kd_biaya
								 */);
		audittrail(Audittrail.Activity.TRANS, Audittrail.TransType.DELETE, tmp.getClass().getSimpleName(), tmp.getItemId(), CommonUtil.getIpAddr(httpServletRequest), "DELETE HAK AKSES", CommonUtil.getCurrentUser(), changes);

	}

	/** Ambil jumlah seluruh data **/
	public int selectPagingCount(String search, String gl, String pr, String kt, String lk, String gb, String kb, Integer aktif) {
		HakBiaya hakbiaya = new HakBiaya();
		hakbiaya.setSearch(search);
		hakbiaya.setGl(gl);
		hakbiaya.setPr(pr);
		hakbiaya.setKt(kt);
		hakbiaya.setLk(lk);
		hakbiaya.setGb(gb);
		hakbiaya.setKb(kb);
		hakbiaya.setAktif(aktif);
		return hakbiayaMapper.selectPagingCount(hakbiaya);
	}

	/** Ambil data paging **/
	public List<HakBiaya> selectPagingList(String search, String sort, String sortOrder, int page, int rowcount, String gl, String pr, String kt, String lk, String gb, String kb, Integer aktif) {
		HakBiaya hakbiaya = new HakBiaya();
		hakbiaya.setSearch(search);
		if (sort != null)
			hakbiaya.setSort(sort + " " + sortOrder);
		hakbiaya.setPage(page);
		hakbiaya.setRowcount(rowcount);
		hakbiaya.setGl(gl);
		hakbiaya.setPr(pr);
		hakbiaya.setKt(kt);
		hakbiaya.setLk(lk);
		hakbiaya.setGb(gb);
		hakbiaya.setKb(kb);
		hakbiaya.setAktif(aktif);
		return hakbiayaMapper.selectPagingList(hakbiaya);
	}

	/** Save Model **/
	@Transactional
	public HakBiaya save(HakBiaya hakbiaya) {
		if (!exists(hakbiaya.divisi_kd, hakbiaya.subdiv_kd, hakbiaya.dept_kd, hakbiaya.lok_kd, hakbiaya.kd_group, hakbiaya.kd_biaya)) {

			hakbiaya.setTgl_create(selectSysdate());
			hakbiaya.setUser_create(CommonUtil.getCurrentUserId());

			Set<AudittrailDetail> changes = CommonUtil.changes(hakbiaya, get(hakbiaya.divisi_kd, hakbiaya.subdiv_kd, hakbiaya.dept_kd, hakbiaya.lok_kd, hakbiaya.kd_group, hakbiaya.kd_biaya));

			hakbiayaMapper.insert(hakbiaya);

			audittrail(Audittrail.Activity.TRANS, Audittrail.TransType.ADD, hakbiaya.getClass().getSimpleName(), hakbiaya.getItemId(), CommonUtil.getIpAddr(httpServletRequest), "ADD HAK BIAYA", CommonUtil.getCurrentUser(), changes);
		} else {
			HakBiaya tmp = get(hakbiaya.id);
			Set<AudittrailDetail> changes = CommonUtil.changes(hakbiaya, tmp);

			hakbiayaMapper.update(hakbiaya);

			if (tmp.getF_aktif() != hakbiaya.getF_aktif()) {
				if (hakbiaya.getF_aktif() == 1) {
					hakBiayaHistManager.save(new HakBiayaHist(tmp.divisi_kd, tmp.subdiv_kd, tmp.dept_kd, tmp.lok_kd, tmp.kd_group, tmp.kd_biaya, tmp.f_aktif, tmp.drtgl, tmp.sptgl, tmp.user_nonaktif, tmp.tgl_nonaktif, tmp.jam_nonaktif));
				}
			}

			audittrail(Audittrail.Activity.TRANS, Audittrail.TransType.UPDATE, hakbiaya.getClass().getSimpleName(), hakbiaya.getItemId(), CommonUtil.getIpAddr(httpServletRequest), "UPDATE HAK BIAYA", CommonUtil.getCurrentUser(), changes);

		}
		return hakbiaya;
	}
	/** Others Method **/
	
	@Transactional
	public void save(List<HakBiaya> hakbiayas) {
		for(HakBiaya hakbiaya:hakbiayas){
			save(hakbiaya);
		}
	}

}
