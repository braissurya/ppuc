package com.melawai.ppuc.services;

import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.melawai.ppuc.model.AksesMenu;
import com.melawai.ppuc.model.Audittrail;
import com.melawai.ppuc.model.AudittrailDetail;
import com.melawai.ppuc.model.GroupUser;
import com.melawai.ppuc.model.Menu;
import com.melawai.ppuc.persistence.GroupUserMapper;
import com.melawai.ppuc.utils.CommonUtil;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Thu Jun 19 23:42:33 ICT 2014
 * @Description: Services for table group_user
 * @Revision	:
 */

@Service("groupuserManager")
public class GroupUserManager extends BaseService {

	private static Logger logger = Logger.getLogger(GroupUserManager.class);

	@Autowired
	private GroupUserMapper groupuserMapper;
	
	@Autowired
	private AksesMenuManager aksesMenuManager;

	/** Ambil DATA berdasarkan group_kd **/
	public GroupUser get(String group_kd) {
		return groupuserMapper.get(group_kd);
	}

	/** Apakah data dengan group_kd ini ada? **/
	public boolean exists(String group_kd) {	
		return get(group_kd)!=null;
	}

	/** Delete data berdasarkan group_kd **/
	@Transactional
	public void remove(String group_kd) {
		
		GroupUser tmp = get(group_kd);
		Set<AudittrailDetail> changes = CommonUtil.changes(tmp, null);
		aksesMenuManager.remove(group_kd, null);
		groupuserMapper.remove(group_kd);
		audittrail(Audittrail.Activity.TRANS, Audittrail.TransType.DELETE, tmp.getClass().getSimpleName(), tmp.getItemId(), CommonUtil.getIpAddr(httpServletRequest), "DELETE GROUP USER",
				CommonUtil.getCurrentUser(), changes);
	}

	/** Ambil jumlah seluruh data **/
	public int selectPagingCount(String search) {
		GroupUser groupuser=new GroupUser();
		groupuser.setSearch(search);
		return groupuserMapper.selectPagingCount(groupuser);
	}

	/** Ambil data paging **/
	public List<GroupUser> selectPagingList(String search, String sort,String sortOrder, int page, int rowcount) {
		GroupUser groupuser=new GroupUser();
		groupuser.setSearch(search);
		 if(sort!=null)groupuser.setSort(sort+" "+sortOrder);
		groupuser.setPage(page);
		groupuser.setRowcount(rowcount);
		return groupuserMapper.selectPagingList(groupuser);
	}

	/** Save Model **/
	@Transactional
	public GroupUser save(GroupUser groupuser) {
		if (!exists(groupuser.group_kd)) {
			Set<AudittrailDetail> changes = CommonUtil.changes(groupuser, get(groupuser.group_kd));
			groupuser.user_create=CommonUtil.getCurrentUserId();
			groupuser.tgl_create=selectSysdate();
			groupuserMapper.insert(groupuser);
			for (Menu mn:groupuser.menus) {
				
				if(mn.akses){
					aksesMenuManager.save(new AksesMenu(groupuser.group_kd, mn.menu_id));
				}
			}
			audittrail(Audittrail.Activity.TRANS, Audittrail.TransType.ADD, groupuser.getClass().getSimpleName(), groupuser.getItemId(), CommonUtil.getIpAddr(httpServletRequest), "ADD Group User",
					CommonUtil.getCurrentUser(), changes);
			
		} else {
			Set<AudittrailDetail> changes = CommonUtil.changes(groupuser, get(groupuser.group_kd));
			groupuserMapper.update(groupuser);
			
			for (Menu mn:groupuser.menus) {
				if(mn.akses){
					aksesMenuManager.save(new AksesMenu(groupuser.group_kd, mn.menu_id));
				}else{
					if(aksesMenuManager.exists(groupuser.group_kd, mn.menu_id)){
						aksesMenuManager.remove(groupuser.group_kd, mn.menu_id);
					}
				}
			}
			audittrail(Audittrail.Activity.TRANS, Audittrail.TransType.UPDATE, groupuser.getClass().getSimpleName(), groupuser.getItemId(), CommonUtil.getIpAddr(httpServletRequest), "UPDATE Group User",
					CommonUtil.getCurrentUser(), changes);
			
		} 
		return groupuser;
	}
	/** Others Method **/

	}
