package com.melawai.ppuc.persistence;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.dao.DataAccessException;
import org.apache.ibatis.annotations.Param;

import com.melawai.ppuc.model.Menu;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Thu Jun 19 23:42:35 ICT 2014
 * @Description: Mapper Interface for table menu
 * @Revision	:
 */

public interface MenuMapper {

	public void insert(Menu menu) throws DataAccessException;

	public void update(Menu menu) throws DataAccessException;

	public void remove(Long menu_id) throws DataAccessException;

	public Menu get(Long menu_id) throws DataAccessException;

	public List<Menu> getAll() throws DataAccessException;

	public List<Menu> selectPagingList(Menu menu) throws DataAccessException;

	public Integer selectPagingCount(Menu menu) throws DataAccessException;

	// QUERY CUSTOM yang lain dibawah sini

	public Set<Menu> selectMenuAccess(@Param("group_kd")String group_kd,@Param("root")Integer root,@Param("path")String path) throws DataAccessException;
}
