package com.melawai.ppuc.services;

import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.ResourceAccessException;

import com.melawai.ppuc.model.Audittrail;
import com.melawai.ppuc.model.AudittrailDetail;
import com.melawai.ppuc.model.DetailBiaya;
import com.melawai.ppuc.model.HakApprove;
import com.melawai.ppuc.model.HakBiaya;
import com.melawai.ppuc.model.HakBiayaHist;
import com.melawai.ppuc.persistence.HakApproveMapper;
import com.melawai.ppuc.utils.CommonUtil;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Thu Jun 19 23:42:33 ICT 2014
 * @Description: Services for table hak_approve
 * @Revision	:
 */

@Service("hakapproveManager")
public class HakApproveManager extends BaseService {

	private static Logger logger = Logger.getLogger(HakApproveManager.class);

	@Autowired
	private HakApproveMapper hakapproveMapper;
	
	@Autowired
	private DetailBiayaManager detailBiayaManager;

	/** Ambil DATA berdasarkan user_id, divisi_kd, subdiv_kd, dept_kd, kd_group, kd_biaya **/
	public HakApprove get(String user_id, String divisi_kd, String subdiv_kd, String dept_kd, String kd_group, String kd_biaya,Integer f_aktif) {
		return hakapproveMapper.get(user_id, divisi_kd, subdiv_kd, dept_kd, kd_group, kd_biaya, f_aktif);
	}
	
	/** Ambil DATA berdasarkan user_id, divisi_kd, subdiv_kd, dept_kd, kd_group, kd_biaya **/
	public HakApprove get(String user_id, String divisi_kd, String subdiv_kd, String dept_kd, String kd_group, String kd_biaya) {
		return hakapproveMapper.get(user_id, divisi_kd, subdiv_kd, dept_kd, kd_group, kd_biaya, null);
	}

	/** Apakah data dengan user_id, divisi_kd, subdiv_kd, dept_kd, kd_group, kd_biaya ini ada? **/
	public boolean exists(String user_id, String divisi_kd, String subdiv_kd, String dept_kd, String kd_group, String kd_biaya,Integer f_aktif) {	
		return get(user_id, divisi_kd, subdiv_kd, dept_kd, kd_group, kd_biaya, f_aktif)!=null;
	}
	
	/** Apakah data dengan user_id, divisi_kd, subdiv_kd, dept_kd, kd_group, kd_biaya ini ada? **/
	public boolean exists(String user_id, String divisi_kd, String subdiv_kd, String dept_kd, String kd_group, String kd_biaya) {	
		return get(user_id, divisi_kd, subdiv_kd, dept_kd, kd_group, kd_biaya, null)!=null;
	}

	/** Delete data berdasarkan id **/
	@Transactional
	public void remove(String user_id, String divisi_kd, String subdiv_kd, String dept_kd, String kd_group, String kd_biaya) {
//		hakapproveMapper.remove(user_id, divisi_kd, subdiv_kd, dept_kd, kd_group, kd_biaya);
		throw new ResourceAccessException("Method Remove not implement");
	}

	/** Ambil jumlah seluruh data **/
	public int selectPagingCount(String search) {
		HakApprove hakapprove=new HakApprove();
		hakapprove.setSearch(search);
		return hakapproveMapper.selectPagingCount(hakapprove);
	}

	/** Ambil data paging **/
	public List<HakApprove> selectPagingList(String search, String sort,String sortOrder, int page, int rowcount) {
		HakApprove hakapprove=new HakApprove();
		hakapprove.setSearch(search);
		 if(sort!=null)hakapprove.setSort(sort+" "+sortOrder);
		hakapprove.setPage(page);
		hakapprove.setRowcount(rowcount);
		return hakapproveMapper.selectPagingList(hakapprove);
	}

	/** Save Model **/
	@Transactional
	public HakApprove save(HakApprove hakapprove) {
		hakapprove.subdiv_kd = hakapprove.subdiv_kd.substring(hakapprove.subdiv_kd.lastIndexOf(".") + 1);
		hakapprove.dept_kd = hakapprove.dept_kd.substring(hakapprove.dept_kd.lastIndexOf(".") + 1);
		if (!exists(hakapprove.user_id, hakapprove.divisi_kd, hakapprove.subdiv_kd, hakapprove.dept_kd, hakapprove.kd_group, hakapprove.kd_biaya,1)) {
			
			hakapprove.setTgl_create(selectSysdate());
			hakapprove.setUser_create(CommonUtil.getCurrentUserId());

			Set<AudittrailDetail> changes = CommonUtil.changes(hakapprove, get(hakapprove.user_id, hakapprove.divisi_kd, hakapprove.subdiv_kd, hakapprove.dept_kd, hakapprove.kd_group, hakapprove.kd_biaya,1));

			hakapproveMapper.insert(hakapprove);
			
			detailBiayaManager.save(new DetailBiaya(hakapprove.kd_biaya, hakapprove.kd_group, null, null, 1, null));

			audittrail(Audittrail.Activity.TRANS, Audittrail.TransType.ADD, hakapprove.getClass().getSimpleName(), hakapprove.getItemId(), CommonUtil.getIpAddr(httpServletRequest), "ADD HAK APPROVE", CommonUtil.getCurrentUser(), changes);
		} else {
			
			HakApprove tmp = get(hakapprove.user_id, hakapprove.divisi_kd, hakapprove.subdiv_kd, hakapprove.dept_kd, hakapprove.kd_group, hakapprove.kd_biaya,1);
			Set<AudittrailDetail> changes = CommonUtil.changes(hakapprove, tmp);

			hakapproveMapper.update(hakapprove);

			if (tmp.getF_aktif() != hakapprove.getF_aktif()) {
				if (hakapprove.getF_aktif() == 1) {//klo mau diaktifkan maka detail biaya ikut diaktifkan
					detailBiayaManager.save(new DetailBiaya(hakapprove.kd_biaya, hakapprove.kd_group, null, null, 1, null));
				}else{//klo mau dinonaktifkan maka detail biaya ikut dinonaktifkan
					detailBiayaManager.save(new DetailBiaya(hakapprove.kd_biaya, hakapprove.kd_group, null, null, 0, null));
				}
			}

			audittrail(Audittrail.Activity.TRANS, Audittrail.TransType.UPDATE, hakapprove.getClass().getSimpleName(), hakapprove.getItemId(), CommonUtil.getIpAddr(httpServletRequest), "UPDATE HAK APPROVE", CommonUtil.getCurrentUser(), changes);
		} 
		return hakapprove;
	}
	/** Others Method **/

	}
