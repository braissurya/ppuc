package com.melawai.ppuc.persistence;

import java.util.Date;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.apache.ibatis.annotations.Param;
import com.melawai.ppuc.model.GroupLokasiH;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Sun Jul 06 16:28:25 ICT 2014
 * @Description: Mapper Interface for table group_lokasi_h
 * @Revision	:
 */

public interface GroupLokasiHMapper {

	public void insert(GroupLokasiH grouplokasih) throws DataAccessException;

	public void update(GroupLokasiH grouplokasih) throws DataAccessException;

	public void remove(@Param("divisi_kd") String divisi_kd, @Param("subdiv_kd") String subdiv_kd, @Param("group_lok") String group_lok) throws DataAccessException;

	public GroupLokasiH get(@Param("divisi_kd") String divisi_kd, @Param("subdiv_kd") String subdiv_kd, @Param("group_lok") String group_lok) throws DataAccessException;

	public List<GroupLokasiH> getAll() throws DataAccessException;

	public List<GroupLokasiH> selectPagingList(GroupLokasiH grouplokasih) throws DataAccessException;

	public Integer selectPagingCount(GroupLokasiH grouplokasih) throws DataAccessException;

	// QUERY CUSTOM yang lain dibawah sini


}
