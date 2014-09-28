package com.melawai.ppuc.persistence;

import java.util.Date;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.apache.ibatis.annotations.Param;
import com.melawai.ppuc.model.Realisasi;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Sun Sep 28 22:48:14 ICT 2014
 * @Description: Mapper Interface for table realisasi
 * @Revision	:
 */

public interface RealisasiMapper {

	public void insert(Realisasi realisasi) throws DataAccessException;

	public void update(Realisasi realisasi) throws DataAccessException;

	public void remove(@Param("divisi_kd") String divisi_kd, @Param("subdiv_kd") String subdiv_kd, @Param("dept_kd") String dept_kd, @Param("lok_kd") String lok_kd, @Param("no_ppuc") String no_ppuc, @Param("tgl_ppuc") Date tgl_ppuc, @Param("no_realisasi") String no_realisasi) throws DataAccessException;

	public Realisasi get(@Param("divisi_kd") String divisi_kd, @Param("subdiv_kd") String subdiv_kd, @Param("dept_kd") String dept_kd, @Param("lok_kd") String lok_kd, @Param("no_ppuc") String no_ppuc, @Param("tgl_ppuc") Date tgl_ppuc, @Param("no_realisasi") String no_realisasi) throws DataAccessException;

	public List<Realisasi> getAll() throws DataAccessException;

	public List<Realisasi> selectPagingList(Realisasi realisasi) throws DataAccessException;

	public Integer selectPagingCount(Realisasi realisasi) throws DataAccessException;

	// QUERY CUSTOM yang lain dibawah sini


}
