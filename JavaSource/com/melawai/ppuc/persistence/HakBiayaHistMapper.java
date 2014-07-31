package com.melawai.ppuc.persistence;

import java.util.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.apache.ibatis.annotations.Param;

import com.melawai.ppuc.model.HakBiayaHist;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Sun Jul 06 16:28:27 ICT 2014
 * @Description: Mapper Interface for table hak_biaya_hist
 * @Revision	:
 */

public interface HakBiayaHistMapper {

	public void insert(HakBiayaHist hakbiayahist) throws DataAccessException;

	public void update(HakBiayaHist hakbiayahist) throws DataAccessException;

	public void remove(@Param("id") Long id/*,@Param("divisi_kd") String divisi_kd, @Param("subdiv_kd") String subdiv_kd, @Param("dept_kd") String dept_kd, @Param("lok_kd") String lok_kd, @Param("kd_group") String kd_group, @Param("kd_biaya") String kd_biaya*/) throws DataAccessException;

	public HakBiayaHist get(@Param("id") Long id/*,@Param("divisi_kd") String divisi_kd, @Param("subdiv_kd") String subdiv_kd, @Param("dept_kd") String dept_kd, @Param("lok_kd") String lok_kd, @Param("kd_group") String kd_group, @Param("kd_biaya") String kd_biaya*/) throws DataAccessException;
	
	public Date getLastSpTgl(@Param("divisi_kd") String divisi_kd, @Param("subdiv_kd") String subdiv_kd, @Param("dept_kd") String dept_kd, @Param("lok_kd") String lok_kd, @Param("kd_group") String kd_group, @Param("kd_biaya") String kd_biaya) throws DataAccessException;
	
	public List<HakBiayaHist> getAll() throws DataAccessException;

	public List<HakBiayaHist> selectPagingList(HakBiayaHist hakbiayahist) throws DataAccessException;

	public Integer selectPagingCount(HakBiayaHist hakbiayahist) throws DataAccessException;

	// QUERY CUSTOM yang lain dibawah sini


}
