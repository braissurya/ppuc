package com.melawai.ppuc.services;

import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.melawai.ppuc.model.Audittrail;
import com.melawai.ppuc.model.AudittrailDetail;
import com.melawai.ppuc.model.DetailBiaya;
import com.melawai.ppuc.persistence.DetailBiayaMapper;
import com.melawai.ppuc.utils.CommonUtil;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Thu Jun 19 23:42:31 ICT 2014
 * @Description: Services for table detail_biaya
 * @Revision	:
 */

@Service("detailbiayaManager")
public class DetailBiayaManager extends BaseService {

	private static Logger logger = Logger.getLogger(DetailBiayaManager.class);

	@Autowired
	private DetailBiayaMapper detailbiayaMapper;

	/** Ambil DATA berdasarkan kd_biaya **/
	public DetailBiaya get(String kd_biaya) {
		return detailbiayaMapper.get(kd_biaya);
	}

	/** Apakah data dengan kd_biaya ini ada? **/
	public boolean exists(String kd_biaya) {	
		return get(kd_biaya)!=null;
	}
	
	public boolean exist(String kd_group,String kd_biaya){
		return selectCountTable("detail_biaya", "kd_biaya = '"+kd_biaya+"' and kd_group='"+kd_group+"'")>0;
	}

	/** Delete data berdasarkan kd_biaya **/
	@Transactional
	public void remove(String kd_biaya) {
		
		DetailBiaya tmp = get(kd_biaya);
		Set<AudittrailDetail> changes = CommonUtil.changes(tmp, null);
		detailbiayaMapper.remove(kd_biaya);
		audittrail(Audittrail.Activity.TRANS, Audittrail.TransType.DELETE, tmp.getClass().getSimpleName(), tmp.getItemId(), CommonUtil.getIpAddr(httpServletRequest), "DELETE DETAIL BIAYA",
				CommonUtil.getCurrentUser(), changes);
	}

	/** Ambil jumlah seluruh data **/
	public int selectPagingCount(String search) {
		DetailBiaya detailbiaya=new DetailBiaya();
		detailbiaya.setSearch(search);
		return detailbiayaMapper.selectPagingCount(detailbiaya);
	}

	/** Ambil data paging **/
	public List<DetailBiaya> selectPagingList(String search, String sort,String sortOrder, int page, int rowcount) {
		DetailBiaya detailbiaya=new DetailBiaya();
		detailbiaya.setSearch(search);
		 if(sort!=null)detailbiaya.setSort(sort+" "+sortOrder);
		detailbiaya.setPage(page);
		detailbiaya.setRowcount(rowcount);
		return detailbiayaMapper.selectPagingList(detailbiaya);
	}

	/** Save Model **/
	@Transactional
	public DetailBiaya save(DetailBiaya detailbiaya) {
		if (!exist(detailbiaya.kd_group, detailbiaya.kd_biaya)) {
			
			detailbiaya.setTgl_create(selectSysdate());
			detailbiaya.setUser_create(CommonUtil.getCurrentUserId());

			Set<AudittrailDetail> changes = CommonUtil.changes(detailbiaya, get(detailbiaya.kd_biaya));

			detailbiayaMapper.insert(detailbiaya);
			audittrail(Audittrail.Activity.TRANS, Audittrail.TransType.ADD, detailbiaya.getClass().getSimpleName(), detailbiaya.getItemId(), CommonUtil.getIpAddr(httpServletRequest), "ADD KD BIAYA",
					CommonUtil.getCurrentUser(), changes);
		} else {
			
			Set<AudittrailDetail> changes = CommonUtil.changes(detailbiaya, get(detailbiaya.kd_biaya));

			detailbiayaMapper.update(detailbiaya);

			audittrail(Audittrail.Activity.TRANS, Audittrail.TransType.UPDATE, detailbiaya.getClass().getSimpleName(), detailbiaya.getItemId(), CommonUtil.getIpAddr(httpServletRequest),
					"UPDATE KD BIAYA", CommonUtil.getCurrentUser(), changes);
			
		} 
		return detailbiaya;
	}
	/** Others Method **/

	}
