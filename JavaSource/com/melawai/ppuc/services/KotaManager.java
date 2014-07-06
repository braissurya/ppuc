package com.melawai.ppuc.services;

import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.melawai.ppuc.model.Kota;
import com.melawai.ppuc.persistence.KotaMapper;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Sun Jul 06 16:28:28 ICT 2014
 * @Description: Services for table kota
 * @Revision	:
 */

@Service("kotaManager")
public class KotaManager {

	private static Logger logger = Logger.getLogger(KotaManager.class);

	@Autowired
	private KotaMapper kotaMapper;

	/** Ambil DATA berdasarkan propinsi, kota **/
	public Kota get(String propinsi, String kota) {
		return kotaMapper.get(propinsi, kota);
	}

	/** Apakah data dengan propinsi, kota ini ada? **/
	public boolean exists(String propinsi, String kota) {	
		return get(propinsi, kota)!=null;
	}

	/** Delete data berdasarkan id **/
	@Transactional
	public void remove(String propinsi, String kota) {
		kotaMapper.remove(propinsi, kota);
	}

	/** Ambil jumlah seluruh data **/
	public int selectPagingCount(String search) {
		Kota kota=new Kota();
		kota.setSearch(search);
		return kotaMapper.selectPagingCount(kota);
	}

	/** Ambil data paging **/
	public List<Kota> selectPagingList(String search, String sort,String sortOrder, int page, int rowcount) {
		Kota kota=new Kota();
		kota.setSearch(search);
		 if(sort!=null)kota.setSort(sort+" "+sortOrder);
		kota.setPage(page);
		kota.setRowcount(rowcount);
		return kotaMapper.selectPagingList(kota);
	}

	/** Save Model **/
	@Transactional
	public Kota save(Kota kota) {
		if (kota.getTgl_create()==null) {
			kotaMapper.insert(kota);
		} else {
			kotaMapper.update(kota);
		} 
		return kota;
	}
	/** Others Method **/

	}
