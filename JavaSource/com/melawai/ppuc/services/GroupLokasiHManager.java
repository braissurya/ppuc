package com.melawai.ppuc.services;

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
import com.melawai.ppuc.persistence.GroupLokasiHMapper;
import com.melawai.ppuc.utils.CommonUtil;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Sun Jul 06 16:28:25 ICT 2014
 * @Description: Services for table group_lokasi_h
 * @Revision	:
 */

@Service("grouplokasihManager")
public class GroupLokasiHManager extends BaseService {

	private static Logger logger = Logger.getLogger(GroupLokasiHManager.class);

	@Autowired
	private GroupLokasiHMapper grouplokasihMapper;
	
	@Autowired
	private GroupLokasiDManager grouplokasidManager;

	/** Ambil DATA berdasarkan divisi_kd, subdiv_kd, group_lok **/
	public GroupLokasiH get(String divisi_kd, String subdiv_kd, String group_lok) {
		GroupLokasiH groupLokasiH=grouplokasihMapper.get(divisi_kd, subdiv_kd, group_lok);
		if (groupLokasiH != null) {
			groupLokasiH.groupLokasiDList = grouplokasidManager.get(divisi_kd, subdiv_kd, group_lok);
			if (!groupLokasiH.groupLokasiDList.isEmpty()) {
				String tmpLok = "";
				int i = 0;
				for (GroupLokasiD tmp : groupLokasiH.groupLokasiDList) {
					if (i == 0)
						tmpLok += groupLokasiH.divisi_kd + "." + groupLokasiH.subdiv_kd + "." + tmp.lok_kd;
					else
						tmpLok += "#" + groupLokasiH.divisi_kd + "." + groupLokasiH.subdiv_kd + "." + tmp.lok_kd;
					i++;
				}
				groupLokasiH.lok_kd = tmpLok.split("#");
			}
		}
		return groupLokasiH ;
	}

	/** Apakah data dengan divisi_kd, subdiv_kd, group_lok ini ada? **/
	public boolean exists(String divisi_kd, String subdiv_kd, String group_lok) {	
		return get(divisi_kd, subdiv_kd, group_lok)!=null;
	}

	/** Delete data berdasarkan id **/
	@Transactional
	public void remove(String divisi_kd, String subdiv_kd, String group_lok) {
		
		//kalau ada child hapus dulu
		grouplokasidManager.remove(divisi_kd, subdiv_kd, group_lok, null);
		
		GroupLokasiH tmp = get(divisi_kd, subdiv_kd, group_lok);
		Set<AudittrailDetail> changes = CommonUtil.changes(tmp, null);
		grouplokasihMapper.remove(divisi_kd, subdiv_kd, group_lok);
		audittrail(Audittrail.Activity.TRANS, Audittrail.TransType.DELETE, tmp.getClass().getSimpleName(), tmp.getItemId(), CommonUtil.getIpAddr(httpServletRequest), "DELETE DEPARTMENT",
				CommonUtil.getCurrentUser(), changes);
		
	}

	/** Ambil jumlah seluruh data **/
	public int selectPagingCount(String search) {
		GroupLokasiH grouplokasih=new GroupLokasiH();
		grouplokasih.setSearch(search);
		return grouplokasihMapper.selectPagingCount(grouplokasih);
	}

	/** Ambil data paging **/
	public List<GroupLokasiH> selectPagingList(String search, String sort,String sortOrder, int page, int rowcount) {
		GroupLokasiH grouplokasih=new GroupLokasiH();
		grouplokasih.setSearch(search);
		 if(sort!=null)grouplokasih.setSort(sort+" "+sortOrder);
		grouplokasih.setPage(page);
		grouplokasih.setRowcount(rowcount);
		return grouplokasihMapper.selectPagingList(grouplokasih);
	}

	/** Save Model **/
	@Transactional
	public GroupLokasiH save(GroupLokasiH grouplokasih) {
		if (!exists(grouplokasih.divisi_kd, grouplokasih.subdiv_kd, grouplokasih.group_lok)) {
			grouplokasih.setTgl_create(selectSysdate());
			grouplokasih.setUser_create(CommonUtil.getCurrentUserId());

			Set<AudittrailDetail> changes = CommonUtil.changes(grouplokasih, get(grouplokasih.divisi_kd, grouplokasih.subdiv_kd, grouplokasih.group_lok));

			grouplokasihMapper.insert(grouplokasih);
			audittrail(Audittrail.Activity.TRANS, Audittrail.TransType.ADD, grouplokasih.getClass().getSimpleName(), grouplokasih.getItemId(), CommonUtil.getIpAddr(httpServletRequest), "ADD GROUP LOKASI HEADER",
					CommonUtil.getCurrentUser(), changes);
			for(String lok_kd:grouplokasih.lok_kd){
				lok_kd=lok_kd.substring(lok_kd.lastIndexOf(".") + 1);
				GroupLokasiD grouplokasid=new GroupLokasiD(grouplokasih.divisi_kd, grouplokasih.subdiv_kd, grouplokasih.group_lok, lok_kd);
				grouplokasidManager.save(grouplokasid);
			}

		} else {
			
			Set<AudittrailDetail> changes = CommonUtil.changes(grouplokasih,get(grouplokasih.divisi_kd, grouplokasih.subdiv_kd, grouplokasih.group_lok));

			grouplokasihMapper.update(grouplokasih);

			audittrail(Audittrail.Activity.TRANS, Audittrail.TransType.UPDATE, grouplokasih.getClass().getSimpleName(), grouplokasih.getItemId(), CommonUtil.getIpAddr(httpServletRequest),
					"UPDATE GROUP LOKASI HEADER", CommonUtil.getCurrentUser(), changes);
			grouplokasidManager.remove(grouplokasih.divisi_kd, grouplokasih.subdiv_kd, grouplokasih.group_lok, null);
			for(String lok_kd:grouplokasih.lok_kd){
				lok_kd=lok_kd.substring(lok_kd.lastIndexOf(".") + 1);
				GroupLokasiD grouplokasid=new GroupLokasiD(grouplokasih.divisi_kd, grouplokasih.subdiv_kd, grouplokasih.group_lok, lok_kd);
				grouplokasidManager.save(grouplokasid);
			}
		} 
		return grouplokasih;
	}
	/** Others Method **/

	}
