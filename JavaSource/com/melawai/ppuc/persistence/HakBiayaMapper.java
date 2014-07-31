package com.melawai.ppuc.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import com.melawai.ppuc.model.HakBiaya;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Thu Jun 19 23:42:34 ICT 2014
 * @Description: Mapper Interface for table hak_biaya
 * @Revision	:
 */

public interface HakBiayaMapper {

	public void insert(HakBiaya hakbiaya) throws DataAccessException;

	public void update(HakBiaya hakbiaya) throws DataAccessException;

	public void remove(@Param("id") Long id/*,@Param("divisi_kd") String divisi_kd, @Param("subdiv_kd") String subdiv_kd,@Param("dept_kd") String dept_kd,@Param("lok_kd") String lok_kd, @Param("kd_group") String kd_group, @Param("kd_biaya") String kd_biaya*/) throws DataAccessException;

	public HakBiaya get(@Param("id") Long id,@Param("divisi_kd") String divisi_kd, @Param("subdiv_kd") String subdiv_kd,@Param("dept_kd") String dept_kd,@Param("lok_kd") String lok_kd, @Param("kd_group") String kd_group, @Param("kd_biaya") String kd_biaya) throws DataAccessException;

	public List<HakBiaya> getAll() throws DataAccessException;

	public List<HakBiaya> selectPagingList(HakBiaya hakbiaya) throws DataAccessException;

	public Integer selectPagingCount(HakBiaya hakbiaya) throws DataAccessException;

	// QUERY CUSTOM yang lain dibawah sini


}
