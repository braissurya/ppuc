package com.melawai.ppuc.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.melawai.ppuc.model.HakApprove;
import com.melawai.ppuc.model.PpucD;
import com.melawai.ppuc.model.PpucH;
import com.melawai.ppuc.model.User;
import com.melawai.ppuc.persistence.PpucHMapper;
import com.melawai.ppuc.utils.CommonUtil;
import com.melawai.ppuc.utils.Utils;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Thu Jun 19 23:42:37 ICT 2014
 * @Description: Services for table ppuc_h
 * @Revision	:
 */

@Service("ppuchManager")
public class PpucHManager extends BaseService {

	private static Logger logger = Logger.getLogger(PpucHManager.class);

	@Autowired
	private PpucHMapper ppuchMapper;
	
	@Autowired
	private PpucDManager ppucdManager;
	
	@Autowired
	private LokasiManager lokasiManager;
	
	@Autowired
	private HakApproveManager hakApproveManager;
	
	@Autowired
	private GroupBiayaManager groupBiayaManager;

	/** Ambil DATA berdasarkan divisi_kd, subdiv_kd, dept_kd, lok_kd, no_ppuc, tgl_ppuc **/
	public List<PpucH> get(String divisi_kd, String subdiv_kd, String dept_kd, String lok_kd, String no_ppuc, Date tgl_ppuc,String no_batch) {
		return ppuchMapper.get(divisi_kd, subdiv_kd, dept_kd, lok_kd, no_ppuc, tgl_ppuc, no_batch);
	}
	
	public PpucH get(String divisi_kd, String subdiv_kd, String dept_kd, String lok_kd, String no_ppuc, Date tgl_ppuc) {
		List<PpucH> ppucHs=ppuchMapper.get(divisi_kd, subdiv_kd, dept_kd, lok_kd, no_ppuc, tgl_ppuc, null);
		return ppucHs.isEmpty()?null:ppucHs.get(0);
	}
	
	public List<PpucH> get(String no_batch) {
		return ppuchMapper.get(null, null,  null, null, null, null, no_batch);
	}

	/** Apakah data dengan divisi_kd, subdiv_kd, dept_kd, lok_kd, no_ppuc, tgl_ppuc ini ada? **/
	public boolean exists(String divisi_kd, String subdiv_kd, String dept_kd, String lok_kd, String no_ppuc, Date tgl_ppuc) {	
		return get(divisi_kd, subdiv_kd, dept_kd, lok_kd, no_ppuc, tgl_ppuc)!=null;
	}
	
	public String getGroupBiaya(String kd_group){
		return groupBiayaManager.get(kd_group).nm_group;
	}

	/** Delete data berdasarkan id **/
	@Transactional
	public void remove(String divisi_kd, String subdiv_kd, String dept_kd, String lok_kd, String no_ppuc, Date tgl_ppuc) {
		ppuchMapper.remove(divisi_kd, subdiv_kd, dept_kd, lok_kd, no_ppuc, tgl_ppuc);
	}

	/** Ambil jumlah seluruh data **/
	public int selectPagingCount(String search,Integer type) {
		PpucH ppuch=new PpucH();
		ppuch.setSearch(search);
		
		if(type==1)return ppuchMapper.selectPagingCountSatu(ppuch);
		else return ppuchMapper.selectPagingCount(ppuch);
	}

	/** Ambil data paging **/
	public List<PpucH> selectPagingList(String search, String sort,String sortOrder, int page, int rowcount,Integer type) {
		PpucH ppuch=new PpucH();
		ppuch.setSearch(search);
		 if(sort!=null)ppuch.setSort(sort+" "+sortOrder);
		ppuch.setPage(page);
		ppuch.setRowcount(rowcount);
		if(type==1)return ppuchMapper.selectPagingListSatu(ppuch);
		else return ppuchMapper.selectPagingList(ppuch);
	}

	/** Save Model **/
	@Transactional
	public PpucH save(PpucH ppuch) {
		if (Utils.isEmpty(ppuch.no_batch)) {
			List<HakApprove> lsHakApprove=new ArrayList<HakApprove>();
			for(PpucD ppucd:ppuch.ppucds){
				if(!lsHakApprove.isEmpty()){
					for (int i = 0; i < lsHakApprove.size(); i++) {
						HakApprove tmp=lsHakApprove.get(i);
						if(/*tmp.divisi_kd.equals(ppucd.divisi_kd) && tmp.subdiv_kd.equals( ppucd.subdiv_kd) && tmp.dept_kd.equals( ppucd.dept_kd) &&*/ tmp.kd_group.equals(ppucd.kd_group) && tmp.kd_biaya.equals( ppucd.kd_biaya)){
							lsHakApprove.get(i).ppucds.add(ppucd);
							continue;
						}
					}
				}

				HakApprove hakapp=hakApproveManager.get(null,null,null, null, ppucd.kd_group, ppucd.kd_biaya,1);
				hakapp.ppucds.add(ppucd);
				lsHakApprove.add(hakapp);
			}
			
			String no_batch=lokasiManager.getCounterBatch(ppuch.lok_kd, ppuch.dept_kd, ppuch.subdiv_kd, ppuch.divisi_kd);
			ppuch.no_batch=no_batch;
			
			ppuch.setTgl_create(selectSysdate());
			ppuch.setUser_create(CommonUtil.getCurrentUserId());
			
			User currentUser=CommonUtil.getCurrentUser();
			ppuch.setEmail_asal_create(currentUser.getEmail());
			ppuch.setHp_asal_create(currentUser.getNo_hp());
			
			for(HakApprove hakApprove : lsHakApprove){//jika dalam satu inputan data PPUC, ada divisi tujuan approval lebih dari satu, maka secara otomatis system akan memecah transaksi inputan cabang menjadi beberapa nomor PPUC
				if(hakApprove.detailbiaya.f_putus==0){//akan create record transaksi 'ppuc-h' dan 'ppuc-d' sebanyak qty yang diinput 
					
					for(PpucD ppucd:ppuch.ppucds){
						Integer qty=ppucd.qty.intValue();
						for (int i = 0; i < qty; i++) {
							String no_ppuc=lokasiManager.getCounterPPUC(ppuch.lok_kd, ppuch.dept_kd, ppuch.subdiv_kd, ppuch.divisi_kd);
							ppuch.no_ppuc=no_ppuc;
							
							ppuch.hp_tujuan_create=hakApprove.user.no_hp;
							ppuch.email_tujuan_create=hakApprove.user.email;
							ppuch.divisi_kd_apprv=hakApprove.divisi_kd;
							ppuch.subdiv_kd_apprv=hakApprove.subdiv_kd;
							ppuch.dept_kd_apprv=hakApprove.dept_kd;
							ppuchMapper.insert(ppuch);
							
							ppucd.no_ppuc=no_ppuc;
							ppucd.no_batch=no_batch;
							ppucd.qty=1l;
							ppucdManager.save(ppucd);
						}
					}
					
				}else{
					String no_ppuc=lokasiManager.getCounterPPUC(ppuch.lok_kd, ppuch.dept_kd, ppuch.subdiv_kd, ppuch.divisi_kd);
					ppuch.no_ppuc=no_ppuc;
					
					ppuch.hp_tujuan_create=hakApprove.user.no_hp;
					ppuch.email_tujuan_create=hakApprove.user.email;
					ppuch.divisi_kd_apprv=hakApprove.divisi_kd;
					ppuch.subdiv_kd_apprv=hakApprove.subdiv_kd;
					ppuch.dept_kd_apprv=hakApprove.dept_kd;
					ppuchMapper.insert(ppuch);
					
					for(PpucD ppucd:ppuch.ppucds){
						ppucd.no_ppuc=no_ppuc;
						ppucd.no_batch=no_batch;
						ppucdManager.save(ppucd);
					}
				}
			}
		} else {
			ppuchMapper.update(ppuch);
		} 
		return ppuch;
	}
	/** Others Method **/

	}
