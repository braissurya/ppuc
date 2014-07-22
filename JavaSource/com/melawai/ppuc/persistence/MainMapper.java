package com.melawai.ppuc.persistence;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import com.melawai.ppuc.model.DropDown;

/**
 * GENERATE BY BraisSpringMVCHelp
 * 
 * @since : Thu Jun 19 23:42:35 ICT 2014
 * @Description: Mapper Interface for table menu
 * @Revision :
 */

public interface MainMapper {

	public Date selectSysdate() throws DataAccessException;

	public List<DropDown> selectDropDown(@Param("keycol") String key,
			@Param("valcol") String value, @Param("tablename") String table,
			@Param("where") String where, @Param("ordercol") String orderby)
			throws DataAccessException;

	public Integer selectCountTable(@Param("tablename") String table,
			@Param("where") String where) throws DataAccessException;

	public Integer selectMaxValue(@Param("coloumnName") String coloumnName,@Param("tablename") String table,
			@Param("where") String where) throws DataAccessException;
}
