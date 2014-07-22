package com.melawai.ppuc.services;

import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.melawai.ppuc.model.Audittrail;
import com.melawai.ppuc.model.AudittrailDetail;
import com.melawai.ppuc.model.MFungsi;
import com.melawai.ppuc.model.Menu;
import com.melawai.ppuc.persistence.MFungsiMapper;
import com.melawai.ppuc.utils.CommonUtil;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Thu Jun 19 23:42:37 ICT 2014
 * @Description: Services for table m_fungsi
 * @Revision	:
 */

@Service("mfungsiManager")
public class MFungsiManager extends BaseService{

	private static Logger logger = Logger.getLogger(MFungsiManager.class);

	@Autowired
	private MFungsiMapper mfungsiMapper;

	/** Ambil DATA berdasarkan kd_fungsi **/
	public MFungsi get(String kd_fungsi) {
		return mfungsiMapper.get(kd_fungsi);
	}
	
	public List<MFungsi> getAll() {
		return mfungsiMapper.getAll();
	}

	/** Apakah data dengan kd_fungsi ini ada? **/
	public boolean exists(String kd_fungsi) {	
		return get(kd_fungsi)!=null;
	}

	/** Delete data berdasarkan kd_fungsi **/
	@Transactional
	public void remove(String kd_fungsi) {
		
		MFungsi tmp = get(kd_fungsi);
		Set<AudittrailDetail> changes = CommonUtil.changes(tmp, null);
		mfungsiMapper.remove(kd_fungsi);
		audittrail(Audittrail.Activity.TRANS, Audittrail.TransType.DELETE, tmp.getClass().getSimpleName(), tmp.getItemId(), CommonUtil.getIpAddr(httpServletRequest), "DELETE mfungsi",
				CommonUtil.getCurrentUser(), changes);
	}

	/** Ambil jumlah seluruh data **/
	public int selectPagingCount(String search) {
		MFungsi mfungsi=new MFungsi();
		mfungsi.setSearch(search);
		return mfungsiMapper.selectPagingCount(mfungsi);
	}

	/** Ambil data paging **/
	public List<MFungsi> selectPagingList(String search, String sort,String sortOrder, int page, int rowcount) {
		MFungsi mfungsi=new MFungsi();
		mfungsi.setSearch(search);
		 if(sort!=null)mfungsi.setSort(sort+" "+sortOrder);
		mfungsi.setPage(page);
		mfungsi.setRowcount(rowcount);
		return mfungsiMapper.selectPagingList(mfungsi);
	}

	/** Save Model **/
	@Transactional
	public MFungsi save(MFungsi mfungsi) {
		if (!exists(mfungsi.kd_fungsi)) {
			
			mfungsi.user_create = CommonUtil.getCurrentUserId();
			mfungsi.tgl_create = selectSysdate();
			Set<AudittrailDetail> changes = CommonUtil.changes(mfungsi, get(mfungsi.kd_fungsi));
			mfungsiMapper.insert(mfungsi);
			audittrail(Audittrail.Activity.TRANS, Audittrail.TransType.ADD, mfungsi.getClass().getSimpleName(), mfungsi.getItemId(), CommonUtil.getIpAddr(httpServletRequest), "ADD mfungsi",
					CommonUtil.getCurrentUser(), changes);
		} else {
			
			Set<AudittrailDetail> changes = CommonUtil.changes(mfungsi, get(mfungsi.kd_fungsi));
			mfungsiMapper.update(mfungsi);
			audittrail(Audittrail.Activity.TRANS, Audittrail.TransType.UPDATE, mfungsi.getClass().getSimpleName(), mfungsi.getItemId(), CommonUtil.getIpAddr(httpServletRequest), "UPDATE mfungsi",
					CommonUtil.getCurrentUser(), changes);
		} 
		return mfungsi;
	}
	/** Others Method **/

	}
