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
import com.melawai.ppuc.model.GroupLokasiD;
import com.melawai.ppuc.model.GroupLokasiH;
import com.melawai.ppuc.persistence.GroupLokasiDMapper;
import com.melawai.ppuc.utils.CommonUtil;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Sun Jul 06 16:28:25 ICT 2014
 * @Description: Services for table group_lokasi_d
 * @Revision	:
 */

@Service("grouplokasidManager")
public class GroupLokasiDManager extends BaseService{

	private static Logger logger = Logger.getLogger(GroupLokasiDManager.class);

	@Autowired
	private GroupLokasiDMapper grouplokasidMapper;

	/** Ambil DATA berdasarkan divisi_kd, subdiv_kd, group_lok, lok_kd **/
	public GroupLokasiD get(String divisi_kd, String subdiv_kd, String group_lok, String lok_kd) {
		return grouplokasidMapper.get(divisi_kd, subdiv_kd, group_lok, lok_kd);
	}
	
	public List<GroupLokasiD> get(String divisi_kd, String subdiv_kd, String group_lok) {
		return grouplokasidMapper.getList(divisi_kd, subdiv_kd, group_lok);
	}

	/** Apakah data dengan divisi_kd, subdiv_kd, group_lok, lok_kd ini ada? **/
	public boolean exists(String divisi_kd, String subdiv_kd, String group_lok, String lok_kd) {	
		return get(divisi_kd, subdiv_kd, group_lok, lok_kd)!=null;
	}

	/** Delete data berdasarkan id **/
	@Transactional
	public void remove(String divisi_kd, String subdiv_kd, String group_lok, String lok_kd) {
		List<GroupLokasiD> lsTmp = get(divisi_kd, subdiv_kd, group_lok);
		for(GroupLokasiD tmp:  lsTmp){
			Set<AudittrailDetail> changes = CommonUtil.changes(tmp, null);
			grouplokasidMapper.remove(tmp.divisi_kd, tmp.subdiv_kd, tmp.group_lok, tmp.lok_kd);
			audittrail(Audittrail.Activity.TRANS, Audittrail.TransType.DELETE, tmp.getClass().getSimpleName(), tmp.getItemId(), CommonUtil.getIpAddr(httpServletRequest), "DELETE GROUP LOKASI DETAIL",
					CommonUtil.getCurrentUser(), changes);
		}
		
	}

	/** Ambil jumlah seluruh data **/
	public int selectPagingCount(String search) {
		GroupLokasiD grouplokasid=new GroupLokasiD();
		grouplokasid.setSearch(search);
		return grouplokasidMapper.selectPagingCount(grouplokasid);
	}

	/** Ambil data paging **/
	public List<GroupLokasiD> selectPagingList(String search, String sort,String sortOrder, int page, int rowcount) {
		GroupLokasiD grouplokasid=new GroupLokasiD();
		grouplokasid.setSearch(search);
		 if(sort!=null)grouplokasid.setSort(sort+" "+sortOrder);
		grouplokasid.setPage(page);
		grouplokasid.setRowcount(rowcount);
		return grouplokasidMapper.selectPagingList(grouplokasid);
	}

	/** Save Model **/
	@Transactional
	public GroupLokasiD save(GroupLokasiD grouplokasid) {
		if (!exists(grouplokasid.divisi_kd, grouplokasid.subdiv_kd, grouplokasid.group_lok, grouplokasid.lok_kd)) {
			
			grouplokasid.setTgl_create(selectSysdate());
			grouplokasid.setUser_create(CommonUtil.getCurrentUserId());

			Set<AudittrailDetail> changes = CommonUtil.changes(grouplokasid, get(grouplokasid.divisi_kd, grouplokasid.subdiv_kd, grouplokasid.group_lok, grouplokasid.lok_kd));

			grouplokasidMapper.insert(grouplokasid);

			audittrail(Audittrail.Activity.TRANS, Audittrail.TransType.ADD, grouplokasid.getClass().getSimpleName(), grouplokasid.getItemId(), CommonUtil.getIpAddr(httpServletRequest), "ADD GROUP LOKASI DETAIL",
					CommonUtil.getCurrentUser(), changes);
		} else {
			Set<AudittrailDetail> changes = CommonUtil.changes(grouplokasid,get(grouplokasid.divisi_kd, grouplokasid.subdiv_kd, grouplokasid.group_lok, grouplokasid.lok_kd));

			grouplokasidMapper.update(grouplokasid);

			audittrail(Audittrail.Activity.TRANS, Audittrail.TransType.UPDATE, grouplokasid.getClass().getSimpleName(), grouplokasid.getItemId(), CommonUtil.getIpAddr(httpServletRequest),
					"UPDATE GROUP LOKASI DETAIL", CommonUtil.getCurrentUser(), changes);
		} 
		return grouplokasid;
	}
	/** Others Method **/

	}
