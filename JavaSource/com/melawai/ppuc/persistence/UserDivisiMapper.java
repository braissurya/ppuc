package com.melawai.ppuc.persistence;

import java.util.Date;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.apache.ibatis.annotations.Param;
import com.melawai.ppuc.model.UserDivisi;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Sun Jul 06 16:28:38 ICT 2014
 * @Description: Mapper Interface for table user_divisi
 * @Revision	:
 */

public interface UserDivisiMapper {

	public void insert(UserDivisi userdivisi) throws DataAccessException;

	public void update(UserDivisi userdivisi) throws DataAccessException;

	public void remove(@Param("id_user_divisi") Long id_user_divisi, @Param("user_id") String user_id, @Param("divisi_kd") String divisi_kd, @Param("subdiv_kd") String subdiv_kd, @Param("dept_kd") String dept_kd) throws DataAccessException;

	public UserDivisi get(@Param("id_user_divisi") Long id_user_divisi, @Param("user_id") String user_id, @Param("divisi_kd") String divisi_kd, @Param("subdiv_kd") String subdiv_kd, @Param("dept_kd") String dept_kd) throws DataAccessException;

	public List<UserDivisi> getAll() throws DataAccessException;

	public List<UserDivisi> selectPagingList(UserDivisi userdivisi) throws DataAccessException;

	public Integer selectPagingCount(UserDivisi userdivisi) throws DataAccessException;

	// QUERY CUSTOM yang lain dibawah sini


}
