package com.melawai.ppuc.services;

import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.melawai.ppuc.model.Propinsi;
import com.melawai.ppuc.persistence.PropinsiMapper;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Sun Jul 06 16:28:31 ICT 2014
 * @Description: Services for table propinsi
 * @Revision	:
 */

@Service("propinsiManager")
public class PropinsiManager {

	private static Logger logger = Logger.getLogger(PropinsiManager.class);

	@Autowired
	private PropinsiMapper propinsiMapper;

	/** Ambil DATA berdasarkan propinsi **/
	public Propinsi get(String propinsi) {
		return propinsiMapper.get(propinsi);
	}

	/** Apakah data dengan propinsi ini ada? **/
	public boolean exists(String propinsi) {	
		return get(propinsi)!=null;
	}

	/** Delete data berdasarkan propinsi **/
	@Transactional
	public void remove(String propinsi) {
		propinsiMapper.remove(propinsi);
	}

	/** Ambil jumlah seluruh data **/
	public int selectPagingCount(String search) {
		Propinsi propinsi=new Propinsi();
		propinsi.setSearch(search);
		return propinsiMapper.selectPagingCount(propinsi);
	}

	/** Ambil data paging **/
	public List<Propinsi> selectPagingList(String search, String sort,String sortOrder, int page, int rowcount) {
		Propinsi propinsi=new Propinsi();
		propinsi.setSearch(search);
		 if(sort!=null)propinsi.setSort(sort+" "+sortOrder);
		propinsi.setPage(page);
		propinsi.setRowcount(rowcount);
		return propinsiMapper.selectPagingList(propinsi);
	}

	/** Save Model **/
	@Transactional
	public Propinsi save(Propinsi propinsi) {
		if (propinsi.getTgl_create()==null) {
			propinsiMapper.insert(propinsi);
		} else {
			propinsiMapper.update(propinsi);
		} 
		return propinsi;
	}
	/** Others Method **/

	}
