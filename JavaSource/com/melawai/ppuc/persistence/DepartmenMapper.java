package com.melawai.ppuc.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import com.melawai.ppuc.model.Departmen;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Thu Jun 19 23:42:31 ICT 2014
 * @Description: Mapper Interface for table departmen
 * @Revision	:
 */

public interface DepartmenMapper {

	public void insert(Departmen departmen) throws DataAccessException;

	public void update(Departmen departmen) throws DataAccessException;

	public void remove(@Param("dept_kd")String dept_kd,@Param("subdiv_kd")String subdiv_kd,@Param("divisi_kd")String divisi_kd) throws DataAccessException;

	public Departmen get(@Param("dept_kd")String dept_kd,@Param("subdiv_kd")String subdiv_kd,@Param("divisi_kd")String divisi_kd) throws DataAccessException;

	public List<Departmen> getAll() throws DataAccessException;

	public List<Departmen> selectPagingList(Departmen departmen) throws DataAccessException;

	public Integer selectPagingCount(Departmen departmen) throws DataAccessException;

	// QUERY CUSTOM yang lain dibawah sini


}
