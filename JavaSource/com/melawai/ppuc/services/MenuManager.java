package com.melawai.ppuc.services;

import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.melawai.ppuc.model.Audittrail;
import com.melawai.ppuc.model.AudittrailDetail;
import com.melawai.ppuc.model.Lokasi;
import com.melawai.ppuc.model.Menu;
import com.melawai.ppuc.persistence.MenuMapper;
import com.melawai.ppuc.utils.CommonUtil;

/**
 * GENERATE BY BraisSpringMVCHelp
 * 
 * @since : Thu Jun 19 23:42:35 ICT 2014
 * @Description: Services for table menu
 * @Revision :
 */

@Service("menuManager")
public class MenuManager extends BaseService {

	private static Logger logger = Logger.getLogger(MenuManager.class);

	@Autowired
	private MenuMapper menuMapper;
	
	@Autowired
	private AksesMenuManager aksesMenuManager;

	/** Ambil DATA berdasarkan menu_id **/
	public Menu get(Long menu_id) {
		return menuMapper.get(menu_id);
	}
	
	public List<Menu> getAll() {
		return menuMapper.getAll();
	}

	/** Apakah data dengan menu_id ini ada? **/
	public boolean exists(Long menu_id) {
		return get(menu_id) != null;
	}

	/** Delete data berdasarkan menu_id **/
	@Transactional
	public void remove(Long menu_id) {

		Menu tmp = get(menu_id);
		Set<AudittrailDetail> changes = CommonUtil.changes(tmp, null);
		aksesMenuManager.remove(null, menu_id);
		menuMapper.remove(menu_id);
		audittrail(Audittrail.Activity.TRANS, Audittrail.TransType.DELETE, tmp.getClass().getSimpleName(), tmp.getItemId(), CommonUtil.getIpAddr(httpServletRequest), "DELETE Menu",
				CommonUtil.getCurrentUser(), changes);
	}

	/** Ambil jumlah seluruh data **/
	public int selectPagingCount(String search) {
		Menu menu = new Menu();
		menu.setSearch(search);
		return menuMapper.selectPagingCount(menu);
	}

	/** Ambil data paging **/
	public List<Menu> selectPagingList(String search, String sort, String sortOrder, int page, int rowcount) {
		Menu menu = new Menu();
		menu.setSearch(search);
		if (sort != null)
			menu.setSort(sort + " " + sortOrder);
		menu.setPage(page);
		menu.setRowcount(rowcount);
		return menuMapper.selectPagingList(menu);
	}

	/** Save Model **/
	@Transactional
	public Menu save(Menu menu) {
		if (menu.getMenu_id() == null) {
			menu.user_create = CommonUtil.getCurrentUserId();
			menu.tgl_create = selectSysdate();
			menu.level = get(menu.parent).level + 1;
			Set<AudittrailDetail> changes = CommonUtil.changes(menu, get(menu.menu_id));
			menuMapper.insert(menu);
			audittrail(Audittrail.Activity.TRANS, Audittrail.TransType.ADD, menu.getClass().getSimpleName(), menu.getItemId(), CommonUtil.getIpAddr(httpServletRequest), "ADD Menu",
					CommonUtil.getCurrentUser(), changes);
		} else {
			menu.level = get(menu.parent).level + 1;
			Set<AudittrailDetail> changes = CommonUtil.changes(menu, get(menu.menu_id));
			menuMapper.update(menu);
			audittrail(Audittrail.Activity.TRANS, Audittrail.TransType.UPDATE, menu.getClass().getSimpleName(), menu.getItemId(), CommonUtil.getIpAddr(httpServletRequest), "UPDATE Menu",
					CommonUtil.getCurrentUser(), changes);
		}
		return menu;
	}

	/** Others Method **/

	public List<Menu> selectMenuAccess(String group_kd, Integer root, String path) {
		return menuMapper.selectMenuAccess(group_kd, root, path);
	}

}
