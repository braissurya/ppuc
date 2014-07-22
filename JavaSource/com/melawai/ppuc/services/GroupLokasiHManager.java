package com.melawai.ppuc.services;

import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.melawai.ppuc.model.GroupLokasiH;
import com.melawai.ppuc.persistence.GroupLokasiHMapper;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Sun Jul 06 16:28:25 ICT 2014
 * @Description: Services for table group_lokasi_h
 * @Revision	:
 */

@Service("grouplokasihManager")
public class GroupLokasiHManager {

	private static Logger logger = Logger.getLogger(GroupLokasiHManager.class);

	@Autowired
	private GroupLokasiHMapper grouplokasihMapper;
	
	@Autowired
	private GroupLokasiDManager grouplokasidManager;

	/** Ambil DATA berdasarkan divisi_kd, subdiv_kd, group_lok **/
	public GroupLokasiH get(String divisi_kd, String subdiv_kd, String group_lok) {
		GroupLokasiH groupLokasiH=grouplokasihMapper.get(divisi_kd, subdiv_kd, group_lok);
		groupLokasiH.groupLokasiDList=grouplokasidManager.get(divisi_kd, subdiv_kd, group_lok);
		return groupLokasiH ;
	}

	/** Apakah data dengan divisi_kd, subdiv_kd, group_lok ini ada? **/
	public boolean exists(String divisi_kd, String subdiv_kd, String group_lok) {	
		return get(divisi_kd, subdiv_kd, group_lok)!=null;
	}

	/** Delete data berdasarkan id **/
	@Transactional
	public void remove(String divisi_kd, String subdiv_kd, String group_lok) {
		grouplokasihMapper.remove(divisi_kd, subdiv_kd, group_lok);
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
		if (grouplokasih.getTgl_create()==null) {
			grouplokasihMapper.insert(grouplokasih);
		} else {
			grouplokasihMapper.update(grouplokasih);
		} 
		return grouplokasih;
	}
	/** Others Method **/

	}
