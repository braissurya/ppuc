package com.melawai.ppuc.services;

import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.melawai.ppuc.model.GroupLokasiD;
import com.melawai.ppuc.persistence.GroupLokasiDMapper;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Sun Jul 06 16:28:25 ICT 2014
 * @Description: Services for table group_lokasi_d
 * @Revision	:
 */

@Service("grouplokasidManager")
public class GroupLokasiDManager {

	private static Logger logger = Logger.getLogger(GroupLokasiDManager.class);

	@Autowired
	private GroupLokasiDMapper grouplokasidMapper;

	/** Ambil DATA berdasarkan divisi_kd, subdiv_kd, group_lok, lok_kd **/
	public GroupLokasiD get(String divisi_kd, String subdiv_kd, String group_lok, String lok_kd) {
		return grouplokasidMapper.get(divisi_kd, subdiv_kd, group_lok, lok_kd);
	}

	/** Apakah data dengan divisi_kd, subdiv_kd, group_lok, lok_kd ini ada? **/
	public boolean exists(String divisi_kd, String subdiv_kd, String group_lok, String lok_kd) {	
		return get(divisi_kd, subdiv_kd, group_lok, lok_kd)!=null;
	}

	/** Delete data berdasarkan id **/
	@Transactional
	public void remove(String divisi_kd, String subdiv_kd, String group_lok, String lok_kd) {
		grouplokasidMapper.remove(divisi_kd, subdiv_kd, group_lok, lok_kd);
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
		if (grouplokasid.getTgl_create()==null) {
			grouplokasidMapper.insert(grouplokasid);
		} else {
			grouplokasidMapper.update(grouplokasid);
		} 
		return grouplokasid;
	}
	/** Others Method **/

	}
