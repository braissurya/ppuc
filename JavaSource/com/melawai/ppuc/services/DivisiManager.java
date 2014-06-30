package com.melawai.ppuc.services;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.melawai.ppuc.model.Audittrail;
import com.melawai.ppuc.model.AudittrailDetail;
import com.melawai.ppuc.model.Divisi;
import com.melawai.ppuc.persistence.DivisiMapper;
import com.melawai.ppuc.utils.CommonUtil;

/**
 * GENERATE BY BraisSpringMVCHelp
 * 
 * @since : Thu Jun 19 23:42:32 ICT 2014
 * @Description: Services for table divisi
 * @Revision :
 */

@Service("divisiManager")
public class DivisiManager extends BaseService  {

	private static Logger logger = Logger.getLogger(DivisiManager.class);

	@Autowired
	private DivisiMapper divisiMapper;

	/** Ambil DATA berdasarkan divisi_kd **/
	public Divisi get(String divisi_kd) {
		return divisiMapper.get(divisi_kd);
	}

	/** Apakah data dengan divisi_kd ini ada? **/
	public boolean exists(String divisi_kd) {
		return get(divisi_kd) != null;
	}

	/** Delete data berdasarkan divisi_kd **/
	@Transactional
	public void remove(String divisi_kd) {
		Divisi tmp=get(divisi_kd);
		Set<AudittrailDetail> changes=CommonUtil.changes(tmp,null);
		divisiMapper.remove(divisi_kd);		
		audittrail(Audittrail.Activity.TRANS, Audittrail.TransType.DELETE, tmp.getClass().getSimpleName(), tmp.getItemId(), CommonUtil.getIpAddr(httpServletRequest), "DELETE DIVISI", CommonUtil.getCurrentUser(), changes);
	}

	/** Ambil jumlah seluruh data **/
	public int selectPagingCount(String search) {
		Divisi divisi = new Divisi();
		divisi.setSearch(search);
		return divisiMapper.selectPagingCount(divisi);
	}

	/** Ambil data paging **/
	public List<Divisi> selectPagingList(String search, String sort, String sortOrder, int page, int rowcount) {
		Divisi divisi = new Divisi();
		divisi.setSearch(search);
		if (sort != null)
			divisi.setSort(sort + " " + sortOrder);
		divisi.setPage(page);
		divisi.setRowcount(rowcount);
		return divisiMapper.selectPagingList(divisi);
	}

	/** Save Model **/
	@Transactional
	public Divisi save(Divisi divisi) {
		if (divisi.getTgl_create() == null) {
			divisi.setTgl_create(new Date());
			divisi.setUser_create(CommonUtil.getCurrentUserId());
			
			Set<AudittrailDetail> changes=CommonUtil.changes(divisi,get(divisi.getDivisi_kd()));
			
			divisiMapper.insert(divisi);
			
			audittrail(Audittrail.Activity.TRANS, Audittrail.TransType.ADD, divisi.getClass().getSimpleName(), divisi.getItemId(), CommonUtil.getIpAddr(httpServletRequest), "ADD DIVISI", CommonUtil.getCurrentUser(), changes);
		} else {
			divisi.setTgl_update(new Date());
			divisi.setUser_update(CommonUtil.getCurrentUserId());
			
			Set<AudittrailDetail> changes=CommonUtil.changes(divisi,get(divisi.getDivisi_kd()));			
			
			divisiMapper.update(divisi);
			
			audittrail(Audittrail.Activity.TRANS, Audittrail.TransType.UPDATE, divisi.getClass().getSimpleName(), divisi.getItemId(), CommonUtil.getIpAddr(httpServletRequest), "UPDATE DIVISI", CommonUtil.getCurrentUser(),changes );
		}
		
		return divisi;
	}
	/** Others Method **/

	@Transactional
	public void save(List<Divisi> lsdivisi) {
		for(Divisi divisi:lsdivisi){
			Divisi tmpdiv=get(divisi.divisi_kd);
			
			if(tmpdiv!=null){
				divisi.tgl_create=tmpdiv.tgl_create;				
			}else{
				divisi.tgl_create=null;
			}
			
			save(divisi);
		}
		
	}
}
