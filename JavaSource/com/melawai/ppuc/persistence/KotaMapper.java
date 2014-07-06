package com.melawai.ppuc.persistence;

import java.util.Date;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.apache.ibatis.annotations.Param;
import com.melawai.ppuc.model.Kota;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Sun Jul 06 16:28:28 ICT 2014
 * @Description: Mapper Interface for table kota
 * @Revision	:
 */

public interface KotaMapper {

	public void insert(Kota kota) throws DataAccessException;

	public void update(Kota kota) throws DataAccessException;

	public void remove(@Param("propinsi") String propinsi, @Param("kota") String kota) throws DataAccessException;

	public Kota get(@Param("propinsi") String propinsi, @Param("kota") String kota) throws DataAccessException;

	public List<Kota> getAll() throws DataAccessException;

	public List<Kota> selectPagingList(Kota kota) throws DataAccessException;

	public Integer selectPagingCount(Kota kota) throws DataAccessException;

	// QUERY CUSTOM yang lain dibawah sini


}
