package com.melawai.ppuc.services;

import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.melawai.ppuc.model.Audittrail;
import com.melawai.ppuc.model.AudittrailDetail;
import com.melawai.ppuc.model.Divisi;
import com.melawai.ppuc.model.GroupBiaya;
import com.melawai.ppuc.persistence.GroupBiayaMapper;
import com.melawai.ppuc.utils.CommonUtil;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Thu Jun 19 23:42:32 ICT 2014
 * @Description: Services for table group_biaya
 * @Revision	:
 */

@Service("groupbiayaManager")
public class GroupBiayaManager extends BaseService {

	private static Logger logger = Logger.getLogger(GroupBiayaManager.class);

	@Autowired
	private GroupBiayaMapper groupbiayaMapper;

	/** Ambil DATA berdasarkan kd_group **/
	public GroupBiaya get(String kd_group) {
		return groupbiayaMapper.get(kd_group);
	}

	/** Apakah data dengan kd_group ini ada? **/
	public boolean exists(String kd_group) {	
		return get(kd_group)!=null;
	}

	/** Delete data berdasarkan kd_group **/
	@Transactional
	public void remove(String kd_group) {
		
		GroupBiaya tmp=get(kd_group);
		Set<AudittrailDetail> changes=CommonUtil.changes(tmp,null);
		groupbiayaMapper.remove(kd_group);	
		audittrail(Audittrail.Activity.TRANS, Audittrail.TransType.DELETE, tmp.getClass().getSimpleName(), tmp.getItemId(), CommonUtil.getIpAddr(httpServletRequest), "DELETE GROUP BIAYA", CommonUtil.getCurrentUser(), changes);
	}

	/** Ambil jumlah seluruh data **/
	public int selectPagingCount(String search) {
		GroupBiaya groupbiaya=new GroupBiaya();
		groupbiaya.setSearch(search);
		return groupbiayaMapper.selectPagingCount(groupbiaya);
	}

	/** Ambil data paging **/
	public List<GroupBiaya> selectPagingList(String search, String sort,String sortOrder, int page, int rowcount) {
		GroupBiaya groupbiaya=new GroupBiaya();
		groupbiaya.setSearch(search);
		 if(sort!=null)groupbiaya.setSort(sort+" "+sortOrder);
		groupbiaya.setPage(page);
		groupbiaya.setRowcount(rowcount);
		return groupbiayaMapper.selectPagingList(groupbiaya);
	}

	/** Save Model **/
	@Transactional
	public GroupBiaya save(GroupBiaya groupbiaya) {
		if (!exists(groupbiaya.kd_group)) {
			groupbiaya.setTgl_create(selectSysdate());
			groupbiaya.setUser_create(CommonUtil.getCurrentUserId());
			
			Set<AudittrailDetail> changes=CommonUtil.changes(groupbiaya,get(groupbiaya.kd_group));
			
			groupbiayaMapper.insert(groupbiaya);
			
			audittrail(Audittrail.Activity.TRANS, Audittrail.TransType.ADD, groupbiaya.getClass().getSimpleName(), groupbiaya.getItemId(), CommonUtil.getIpAddr(httpServletRequest), "ADD GROUP BIAYA", CommonUtil.getCurrentUser(), changes);
			
		} else {
			
			Set<AudittrailDetail> changes=CommonUtil.changes(groupbiaya,get(groupbiaya.kd_group));			
			
			groupbiayaMapper.update(groupbiaya);
			
			audittrail(Audittrail.Activity.TRANS, Audittrail.TransType.UPDATE, groupbiaya.getClass().getSimpleName(), groupbiaya.getItemId(), CommonUtil.getIpAddr(httpServletRequest), "UPDATE GROUP BIAYA", CommonUtil.getCurrentUser(),changes );
			
		} 
		return groupbiaya;
	}
	/** Others Method **/

	}
