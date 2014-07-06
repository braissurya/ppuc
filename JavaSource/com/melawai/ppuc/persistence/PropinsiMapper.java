package com.melawai.ppuc.persistence;

import java.util.Date;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.apache.ibatis.annotations.Param;
import com.melawai.ppuc.model.Propinsi;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Sun Jul 06 16:28:31 ICT 2014
 * @Description: Mapper Interface for table propinsi
 * @Revision	:
 */

public interface PropinsiMapper {

	public void insert(Propinsi propinsi) throws DataAccessException;

	public void update(Propinsi propinsi) throws DataAccessException;

	public void remove(String propinsi) throws DataAccessException;

	public Propinsi get(String propinsi) throws DataAccessException;

	public List<Propinsi> getAll() throws DataAccessException;

	public List<Propinsi> selectPagingList(Propinsi propinsi) throws DataAccessException;

	public Integer selectPagingCount(Propinsi propinsi) throws DataAccessException;

	// QUERY CUSTOM yang lain dibawah sini


}
