package com.melawai.ppuc.services;

import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.melawai.ppuc.model.Realisasi;
import com.melawai.ppuc.persistence.RealisasiMapper;

/**
 * GENERATE BY BraisSpringMVCHelp
 * 
 * @since : Sun Sep 28 22:48:14 ICT 2014
 * @Description: Services for table realisasi
 * @Revision :
 */

@Service("realisasiManager")
public class RealisasiManager {

	private static Logger logger = Logger.getLogger(RealisasiManager.class);

	@Autowired
	private RealisasiMapper realisasiMapper;

	/**
	 * Ambil DATA berdasarkan divisi_kd, subdiv_kd, dept_kd, lok_kd, no_ppuc,
	 * tgl_ppuc, no_realisasi
	 **/
	public Realisasi get(String divisi_kd, String subdiv_kd, String dept_kd, String lok_kd, String no_ppuc, Date tgl_ppuc, String no_realisasi) {
		return realisasiMapper.get(divisi_kd, subdiv_kd, dept_kd, lok_kd, no_ppuc, tgl_ppuc, no_realisasi);
	}

	/**
	 * Apakah data dengan divisi_kd, subdiv_kd, dept_kd, lok_kd, no_ppuc,
	 * tgl_ppuc, no_realisasi ini ada?
	 **/
	public boolean exists(String divisi_kd, String subdiv_kd, String dept_kd, String lok_kd, String no_ppuc, Date tgl_ppuc, String no_realisasi) {
		return get(divisi_kd, subdiv_kd, dept_kd, lok_kd, no_ppuc, tgl_ppuc, no_realisasi) != null;
	}

	/** Delete data berdasarkan id **/
	@Transactional
	public void remove(String divisi_kd, String subdiv_kd, String dept_kd, String lok_kd, String no_ppuc, Date tgl_ppuc, String no_realisasi) {
		realisasiMapper.remove(divisi_kd, subdiv_kd, dept_kd, lok_kd, no_ppuc, tgl_ppuc, no_realisasi);
	}

	/** Ambil jumlah seluruh data **/
	public int selectPagingCount(String search) {
		Realisasi realisasi = new Realisasi();
		realisasi.setSearch(search);
		return realisasiMapper.selectPagingCount(realisasi);
	}

	/** Ambil data paging **/
	public List<Realisasi> selectPagingList(String search, String sort, String sortOrder, int page, int rowcount) {
		Realisasi realisasi = new Realisasi();
		realisasi.setSearch(search);
		if (sort != null)
			realisasi.setSort(sort + " " + sortOrder);
		realisasi.setPage(page);
		realisasi.setRowcount(rowcount);
		return realisasiMapper.selectPagingList(realisasi);
	}

	/** Save Model **/
	@Transactional
	public Realisasi save(Realisasi realisasi) {
		if (!exists(realisasi.divisi_kd, realisasi.subdiv_kd, realisasi.dept_kd, realisasi.lok_kd, realisasi.no_ppuc, realisasi.tgl_ppuc, realisasi.no_realisasi)) {
			realisasiMapper.insert(realisasi);
		} else {
			realisasiMapper.update(realisasi);
		}
		return realisasi;
	}
	/** Others Method **/

}
