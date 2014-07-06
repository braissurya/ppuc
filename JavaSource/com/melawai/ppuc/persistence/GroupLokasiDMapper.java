package com.melawai.ppuc.persistence;

import java.util.Date;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.apache.ibatis.annotations.Param;
import com.melawai.ppuc.model.GroupLokasiD;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Sun Jul 06 16:28:25 ICT 2014
 * @Description: Mapper Interface for table group_lokasi_d
 * @Revision	:
 */

public interface GroupLokasiDMapper {

	public void insert(GroupLokasiD grouplokasid) throws DataAccessException;

	public void update(GroupLokasiD grouplokasid) throws DataAccessException;

	public void remove(@Param("divisi_kd") String divisi_kd, @Param("subdiv_kd") String subdiv_kd, @Param("group_lok") String group_lok, @Param("lok_kd") String lok_kd) throws DataAccessException;

	public GroupLokasiD get(@Param("divisi_kd") String divisi_kd, @Param("subdiv_kd") String subdiv_kd, @Param("group_lok") String group_lok, @Param("lok_kd") String lok_kd) throws DataAccessException;

	public List<GroupLokasiD> getAll() throws DataAccessException;

	public List<GroupLokasiD> selectPagingList(GroupLokasiD grouplokasid) throws DataAccessException;

	public Integer selectPagingCount(GroupLokasiD grouplokasid) throws DataAccessException;

	// QUERY CUSTOM yang lain dibawah sini


}
