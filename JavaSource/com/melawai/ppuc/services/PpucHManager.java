package com.melawai.ppuc.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.melawai.ppuc.model.Audittrail;
import com.melawai.ppuc.model.AudittrailDetail;
import com.melawai.ppuc.model.DetailBiaya;
import com.melawai.ppuc.model.HakApprove;
import com.melawai.ppuc.model.Posisi.PosisiDesc;
import com.melawai.ppuc.model.PpucD;
import com.melawai.ppuc.model.PpucH;
import com.melawai.ppuc.model.Subdivisi;
import com.melawai.ppuc.model.User;
import com.melawai.ppuc.persistence.PpucHMapper;
import com.melawai.ppuc.utils.CommonUtil;
import com.melawai.ppuc.utils.Utils;

/**
 * GENERATE BY BraisSpringMVCHelp
 * 
 * @since : Thu Jun 19 23:42:37 ICT 2014
 * @Description: Services for table ppuc_h
 * @Revision :
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

	@Autowired
	private DetailBiayaManager detailBiayaManager;

	/**
	 * Ambil DATA berdasarkan divisi_kd, subdiv_kd, dept_kd, lok_kd, no_ppuc,
	 * tgl_ppuc
	 **/
	public List<PpucH> get(String divisi_kd, String subdiv_kd, String dept_kd, String lok_kd, String no_ppuc, Date tgl_ppuc, String no_batch) {
		return ppuchMapper.get(divisi_kd, subdiv_kd, dept_kd, lok_kd, no_ppuc, tgl_ppuc, no_batch);
	}

	public PpucH get(String divisi_kd, String subdiv_kd, String dept_kd, String lok_kd, String no_ppuc, Date tgl_ppuc) {
		List<PpucH> ppucHs = ppuchMapper.get(divisi_kd, subdiv_kd, dept_kd, lok_kd, no_ppuc, tgl_ppuc, null);
		return ppucHs.isEmpty() ? null : ppucHs.get(0);
	}

	public List<PpucH> get(String no_batch) {
		return ppuchMapper.get(null, null, null, null, null, null, no_batch);
	}
	
	public List<PpucH> getBynoppuc(String no_ppuc) {
		return ppuchMapper.get(null, null, null, null, no_ppuc, null, null);
	}

	/**
	 * Apakah data dengan divisi_kd, subdiv_kd, dept_kd, lok_kd, no_ppuc,
	 * tgl_ppuc ini ada?
	 **/
	public boolean exists(String divisi_kd, String subdiv_kd, String dept_kd, String lok_kd, String no_ppuc, Date tgl_ppuc) {
		return get(divisi_kd, subdiv_kd, dept_kd, lok_kd, no_ppuc, tgl_ppuc) != null;
	}

	public String getGroupBiaya(String kd_group) {
		return groupBiayaManager.get(kd_group).nm_group;
	}

	/** Delete data berdasarkan id **/
	@Transactional
	public void remove(String divisi_kd, String subdiv_kd, String dept_kd, String lok_kd, String no_ppuc, Date tgl_ppuc) {
		PpucH tmp = get(divisi_kd, subdiv_kd, dept_kd, lok_kd, no_ppuc, tgl_ppuc);
		Set<AudittrailDetail> changes = CommonUtil.changes(tmp, null);
		ppuchMapper.remove(divisi_kd, subdiv_kd, dept_kd, lok_kd, no_ppuc, tgl_ppuc);
		audittrail(Audittrail.Activity.TRANS, Audittrail.TransType.DELETE, tmp.getClass().getSimpleName(), tmp.getItemId(), CommonUtil.getIpAddr(httpServletRequest), "DELETE PPUCH",
				CommonUtil.getCurrentUser(), changes);
	}

	/** Ambil jumlah seluruh data **/
	public int selectPagingCount(String search, Integer type, String nb, String np, String lk, String gb, String kb,Integer posisi_min,Integer posisi,Integer [] posisiGroup) {
		PpucH ppuch = new PpucH();
		ppuch.setSearch(search);
		
		if(!Utils.isEmpty(nb))ppuch.setNo_batch(nb);
		if(!Utils.isEmpty(np))ppuch.setNo_ppuc(np);
		if(!Utils.isEmpty(lk))ppuch.setLok_kd(lk);
		if(!Utils.isEmpty(gb))ppuch.setKd_group(gb);
		if(!Utils.isEmpty(kb))ppuch.setKd_biaya(kb);
		
		ppuch.setPosisi_min(posisi_min);
		ppuch.setPosisi(posisi);
		
		if(posisiGroup!=null){
			String posGroup="";
			int i=0;
			for(Integer ps:posisiGroup){
				if(i==0) posGroup+=ps;
				else posGroup+=", "+ps;
			}
			ppuch.posisiGroup=posGroup;
		}

		if (type == 1)
			return ppuchMapper.selectPagingCountSatu(ppuch);
		else
			return ppuchMapper.selectPagingCount(ppuch);
	}

	/** Ambil data paging **/
	public List<PpucH> selectPagingList(String search, String sort, String sortOrder, int page, int rowcount, Integer type, String nb, String np, String lk, String gb, String kb,Integer posisi_min,Integer posisi,Integer [] posisiGroup) {
		PpucH ppuch = new PpucH();
		ppuch.setSearch(search);
		
		if(sortOrder==null)sortOrder="asc";
		if (sort != null)
			ppuch.setSort(sort + " " + sortOrder);
		ppuch.setPage(page);
		ppuch.setRowcount(rowcount);
		
		if(!Utils.isEmpty(nb))ppuch.setNo_batch(nb);
		if(!Utils.isEmpty(np))ppuch.setNo_ppuc(np);
		if(!Utils.isEmpty(lk))ppuch.setLok_kd(lk);
		if(!Utils.isEmpty(gb))ppuch.setKd_group(gb);
		if(!Utils.isEmpty(kb))ppuch.setKd_biaya(kb);
		
		ppuch.setPosisi_min(posisi_min);
		ppuch.setPosisi(posisi);
		
		if(posisiGroup!=null){
			String posGroup="";
			int i=0;
			for(Integer ps:posisiGroup){
				if(i==0) posGroup+=ps;
				else posGroup+=", "+ps;
				i++;
			}
			ppuch.posisiGroup=posGroup;
		}
		
		if (type == 1)
			return ppuchMapper.selectPagingListSatu(ppuch);
		else if (type == 2){
			List<PpucH> ppucHs=new ArrayList<PpucH>();
			
			int i=0;
			String no_batch="";
			PpucH tmp=new PpucH();
			for(PpucH pp: ppuchMapper.selectPagingList(ppuch)){
				if(no_batch.equals("")){//baru masuk
					tmp=pp;
					tmp.ppuchs.add(pp);
					no_batch=pp.no_batch;
				}else if(no_batch.equals(pp.no_batch)){ // klo no batch masih sama masuk ke group sebelumnya;
					tmp.ppuchs.add(pp);
				}else{// klo sudah beda buat group baru  
					ppucHs.add(tmp);
					tmp=pp;
					tmp.ppuchs.add(pp);
					no_batch=pp.no_batch;
				}
			}
			ppucHs.add(tmp);
			
			return ppucHs;
		}else {
			return ppuchMapper.selectPagingList(ppuch);
		}
	}

	/** Save Model **/
	@Transactional
	public PpucH save(PpucH ppuch) {
		if (ppuch.getTgl_create()==null) {
			ppuch.setTgl_create(selectSysdate());
			ppuch.setUser_create(CommonUtil.getCurrentUserId());

			Set<AudittrailDetail> changes = CommonUtil.changes(ppuch, get(ppuch.divisi_kd, ppuch.subdiv_kd, ppuch.dept_kd, ppuch.lok_kd, ppuch.no_ppuc, ppuch.tgl_ppuc));

			ppuchMapper.insert(ppuch);

			audittrail(Audittrail.Activity.TRANS, Audittrail.TransType.ADD, ppuch.getClass().getSimpleName(), ppuch.getItemId(), CommonUtil.getIpAddr(httpServletRequest), "ADD PPUCH",
					CommonUtil.getCurrentUser(), changes);
		} else {
			ppuch.setTgl_update(selectSysdate());
			ppuch.setUser_update(CommonUtil.getCurrentUserId());

			Set<AudittrailDetail> changes = CommonUtil.changes(ppuch,  get(ppuch.divisi_kd, ppuch.subdiv_kd, ppuch.dept_kd, ppuch.lok_kd, ppuch.no_ppuc, ppuch.tgl_ppuc));

			ppuchMapper.update(ppuch);

			audittrail(Audittrail.Activity.TRANS, Audittrail.TransType.UPDATE, ppuch.getClass().getSimpleName(), ppuch.getItemId(), CommonUtil.getIpAddr(httpServletRequest),
					"UPDATE PPUCH", CommonUtil.getCurrentUser(), changes);
		}

		return ppuch;
	}
	/** Others Method **/
	
	@Transactional
	public PpucH saveAll(PpucH ppuch) {
		List<HakApprove> lsHakApprove=new ArrayList<HakApprove>();
		for(PpucD ppucd:ppuch.ppucds){
			HakApprove hakapp=hakApproveManager.get(null,null,null, null, ppucd.kd_group, ppucd.kd_biaya,1);
			if(!lsHakApprove.isEmpty()){
				boolean add=true;
				for (int i = 0; i < lsHakApprove.size(); i++) {
					HakApprove tmp=lsHakApprove.get(i);
					if(tmp.divisi_kd.equals(hakapp.divisi_kd) ){//jika dalam satu inputan data PPUC, ada divisi tujuan approval lebih dari satu, maka secara otomatis system akan memecah transaksi inputan cabang menjadi beberapa nomor PPUC
						lsHakApprove.get(i).ppucds.add(ppucd);
						add=false;
						break;
					}
				}
				
				if(add){
					hakapp.ppucds=new ArrayList<PpucD>();
					hakapp.ppucds.add(ppucd);
					lsHakApprove.add(hakapp);
				}
			}else{
				hakapp.ppucds=new ArrayList<PpucD>();
				hakapp.ppucds.add(ppucd);
				lsHakApprove.add(hakapp);
			}
		}
		
		if (Utils.isEmpty(ppuch.no_batch)) {
			String no_batch=lokasiManager.getCounterBatch(ppuch.lok_kd, ppuch.dept_kd, ppuch.subdiv_kd, ppuch.divisi_kd);
			ppuch.no_batch=no_batch;
			
			User currentUser=CommonUtil.getCurrentUser();
			ppuch.setEmail_asal_create(currentUser.getEmail());
			ppuch.setHp_asal_create(currentUser.getNo_hp());
			
			for(HakApprove hakApprove : lsHakApprove){//jika dalam satu inputan data PPUC, ada divisi tujuan approval lebih dari satu, maka secara otomatis system akan memecah transaksi inputan cabang menjadi beberapa nomor PPUC
				String no_ppuc=lokasiManager.getCounterPPUC(ppuch.lok_kd, ppuch.dept_kd, ppuch.subdiv_kd, ppuch.divisi_kd);
				int no=0;
				for(PpucD ppucd:hakApprove.ppucds){
					DetailBiaya detailBiaya=detailBiayaManager.get(ppucd.kd_biaya);
					if(detailBiaya.f_putus==0){//akan create record transaksi 'ppuc-h' dan 'ppuc-d' sebanyak qty yang diinput 
						Integer qty=ppucd.qty.intValue();
						for (int i = 0; i < qty; i++) {
							ppuch.no_ppuc=lokasiManager.getCounterPPUC(ppuch.lok_kd, ppuch.dept_kd, ppuch.subdiv_kd, ppuch.divisi_kd);
							
							ppuch.hp_tujuan_create=hakApprove.user.no_hp;
							ppuch.email_tujuan_create=hakApprove.user.email;
							ppuch.divisi_kd_apprv=hakApprove.divisi_kd;
							ppuch.subdiv_kd_apprv=hakApprove.subdiv_kd;
							ppuch.dept_kd_apprv=hakApprove.dept_kd;
							ppuch.posisi=PosisiDesc.INPUT_PPUC;
							ppuch.tgl_create=null;
							save(ppuch);
							
							ppucd.no_ppuc=ppuch.no_ppuc;
							ppucd.no_batch=no_batch;
							ppucd.qty=1l;
							ppucd.tgl_create=null;
							ppucd.posisi=PosisiDesc.INPUT_PPUC;
							ppucdManager.save(ppucd);
						}
					}else{
						if(no==0){
							ppuch.no_ppuc=no_ppuc;
							ppuch.hp_tujuan_create=hakApprove.user.no_hp;
							ppuch.email_tujuan_create=hakApprove.user.email;
							ppuch.divisi_kd_apprv=hakApprove.divisi_kd;
							ppuch.subdiv_kd_apprv=hakApprove.subdiv_kd;
							ppuch.dept_kd_apprv=hakApprove.dept_kd;
							ppuch.posisi=1;
							ppuch.tgl_create=null;
							save(ppuch);
						}
						ppucd.no_ppuc=no_ppuc;
						ppucd.no_batch=no_batch;
						ppucd.tgl_create=null;
						ppucd.posisi=PosisiDesc.INPUT_PPUC;
						ppucdManager.save(ppucd);
						no++;
						
					}
				}
			}	
		} else {
			List<PpucH> ppuchs=get(ppuch.no_batch);
			List<PpucD> tmp=new ArrayList<PpucD>();
			for(PpucH pp:ppuchs){
				tmp.addAll(pp.ppucds);
			}
			
			//delete ppucd
			for(PpucD ppucd:tmp){
				ppucdManager.remove(ppucd.divisi_kd, ppucd.subdiv_kd, ppucd.dept_kd, ppucd.lok_kd, ppucd.no_ppuc, ppucd.tgl_ppuc, ppucd.kd_biaya);
			}
			
			for(PpucH pp:ppuchs){
				remove(pp.divisi_kd, pp.subdiv_kd, pp.dept_kd, pp.lok_kd, pp.no_ppuc, pp.tgl_ppuc);
			}
			
			String no_batch=ppuch.no_batch;
			
			User currentUser=CommonUtil.getCurrentUser();
			ppuch.setEmail_asal_create(currentUser.getEmail());
			ppuch.setHp_asal_create(currentUser.getNo_hp());
			
			for(HakApprove hakApprove : lsHakApprove){//jika dalam satu inputan data PPUC, ada divisi tujuan approval lebih dari satu, maka secara otomatis system akan memecah transaksi inputan cabang menjadi beberapa nomor PPUC
				String no_ppuc=null;
				for(PpucD ppucd:hakApprove.ppucds){
					if(!Utils.isEmpty(ppucd.no_ppuc)){
						DetailBiaya detailBiaya=detailBiayaManager.get(ppucd.kd_biaya);
						if(detailBiaya.f_putus!=0){
							no_ppuc=ppucd.no_ppuc;
							break;
						}
					}
				}
				
				if(Utils.isEmpty(no_ppuc))no_ppuc=lokasiManager.getCounterPPUC(ppuch.lok_kd, ppuch.dept_kd, ppuch.subdiv_kd, ppuch.divisi_kd);
				int no=0;
				for(PpucD ppucd:hakApprove.ppucds){
					DetailBiaya detailBiaya=detailBiayaManager.get(ppucd.kd_biaya);
					if(detailBiaya.f_putus==0){//akan create record transaksi 'ppuc-h' dan 'ppuc-d' sebanyak qty yang diinput 
						Integer qty=ppucd.qty.intValue();
						if(ppucd.no_ppuc==null)ppucd.no_ppuc="";
						String [] splitNo=ppucd.no_ppuc.split(";");
						for (int i = 0; i < qty; i++) {
							if(splitNo.length==0){
								ppuch.no_ppuc=lokasiManager.getCounterPPUC(ppuch.lok_kd, ppuch.dept_kd, ppuch.subdiv_kd, ppuch.divisi_kd);
							}else{
								if(i>=splitNo.length){
									ppuch.no_ppuc=lokasiManager.getCounterPPUC(ppuch.lok_kd, ppuch.dept_kd, ppuch.subdiv_kd, ppuch.divisi_kd);
								}else{
									ppuch.no_ppuc=splitNo[i];
									
									if(Utils.isEmpty(ppuch.no_ppuc))ppuch.no_ppuc=lokasiManager.getCounterPPUC(ppuch.lok_kd, ppuch.dept_kd, ppuch.subdiv_kd, ppuch.divisi_kd);
								}
							}
							
							ppuch.hp_tujuan_create=hakApprove.user.no_hp;
							ppuch.email_tujuan_create=hakApprove.user.email;
							ppuch.divisi_kd_apprv=hakApprove.divisi_kd;
							ppuch.subdiv_kd_apprv=hakApprove.subdiv_kd;
							ppuch.dept_kd_apprv=hakApprove.dept_kd;
							ppuch.posisi=PosisiDesc.INPUT_PPUC;
							ppuch.tgl_create=null;
							save(ppuch);//harusnya selalu insert
							
							ppucd.no_ppuc=ppuch.no_ppuc;
							ppucd.no_batch=no_batch;
							ppucd.qty=1l;
							ppucd.tgl_create=null;
							ppucd.posisi=PosisiDesc.INPUT_PPUC;
							ppucdManager.save(ppucd);//harusnya selalu insert
						}
					}else{
						if(no==0){
							ppuch.no_ppuc=no_ppuc;
							ppuch.hp_tujuan_create=hakApprove.user.no_hp;
							ppuch.email_tujuan_create=hakApprove.user.email;
							ppuch.divisi_kd_apprv=hakApprove.divisi_kd;
							ppuch.subdiv_kd_apprv=hakApprove.subdiv_kd;
							ppuch.dept_kd_apprv=hakApprove.dept_kd;
							ppuch.posisi=PosisiDesc.INPUT_PPUC;
							ppuch.tgl_create=null;
							save(ppuch);//harusnya selalu insert
						}
						ppucd.no_ppuc=ppuch.no_ppuc;
						ppucd.no_batch=no_batch;
						ppucd.tgl_create=null;
						ppucd.posisi=PosisiDesc.INPUT_PPUC;
						ppucdManager.save(ppucd);//harusnya selalu insert
						no++;
						
					}
				}
			}	
		} 
		return ppuch;
	}

	@Transactional
	public void remove(String no_batch) {
		List<PpucH> ppuchs=get(no_batch);
		List<PpucD> tmp=new ArrayList<PpucD>();
		for(PpucH pp:ppuchs){
			tmp.addAll(pp.ppucds);
		}
		
		//delete ppucd
		for(PpucD ppucd:tmp){
			ppucdManager.remove(ppucd.divisi_kd, ppucd.subdiv_kd, ppucd.dept_kd, ppucd.lok_kd, ppucd.no_ppuc, ppucd.tgl_ppuc, ppucd.kd_biaya);
		}
		
		for(PpucH pp:ppuchs){
			remove(pp.divisi_kd, pp.subdiv_kd, pp.dept_kd, pp.lok_kd, pp.no_ppuc, pp.tgl_ppuc);
		}
	}
	
	@Transactional
	public void confirmInput(String no_batch) {
		List<PpucH> ppuchs=get(no_batch);
		List<PpucD> tmp=new ArrayList<PpucD>();
		Date sysdate= selectSysdate();
		for(PpucH pp:ppuchs){
			pp.posisi = PosisiDesc.APPROVAL_PPUC;//ke posisi
			pp.user_confirm = CommonUtil.getCurrentUserId();
			pp.tgl_confirm = sysdate;
			for(PpucD ppucd:pp.ppucds){
				ppucd.setTgl_create(sysdate);// ini untuk pancing biar bisa masuk ke update
				ppucd.setPosisi(pp.posisi);
				ppucdManager.save(ppucd);
			}
			save(pp);
		}
		
		//TODO : kirim email & sms klo tidak ada error
		
	}
	
	
	@Transactional
	public void saveAllApproval(List<PpucH> ppuchs) {
		User currentUser =CommonUtil.getCurrentUser();
		Date sysdate=selectSysdate();
		for(PpucH ppuch:ppuchs){
			ppuch.setTgl_approve(sysdate);
			ppuch.setTgl_create(sysdate);
			ppuch.setUser_approve(currentUser.getUser_id());
			ppuch.setPosisi(PosisiDesc.PURCHASING);
			save(ppuch);
			for(PpucD ppucd:ppuch.ppucds){
				ppucd.setTgl_create(sysdate);
				if(ppucd.qty==0){
					ppucd.setPosisi(PosisiDesc.DECLINE_PPUC);
					ppucd.setF_approval(0);
				}else{
					ppucd.setPosisi(ppuch.posisi);
					ppucd.setF_approval(1);
				}
				ppucdManager.save(ppucd);
			}
		}
		
		//TODO :send email dan sms
		for(PpucH ppuch:ppuchs){
			for(PpucD ppucd:ppuch.ppucds){
				
			}
		}
		
		
	}
	
	@Transactional
	public void saveAllRealCabang(List<PpucH> ppuchs) {
		User currentUser =CommonUtil.getCurrentUser();
		Date sysdate=selectSysdate();
		for(PpucH ppuch:ppuchs){
			ppuch.setTgl_approve(sysdate);
			ppuch.setTgl_create(sysdate);
			ppuch.setUser_approve(currentUser.getUser_id());
			ppuch.setPosisi(PosisiDesc.INPUT_REALIZATION);
			save(ppuch);
			for(PpucD ppucd:ppuch.ppucds){
				ppucd.setTgl_create(sysdate);
				if(ppucd.qty_real_cbg > ppucd.qty || ppucd.harga_real_cbg > ppucd.harga || ppucd.total_real_cbg > ppucd.total)
					ppucd.setPosisi(PosisiDesc.OVER_BUDGET);
				else
					ppucd.setPosisi(PosisiDesc.INPUT_REALIZATION);
				ppucdManager.save(ppucd);
			}
		}
		
		//TODO :send email dan sms
		for(PpucH ppuch:ppuchs){
			for(PpucD ppucd:ppuch.ppucds){
				
			}
		}
		
		
	}
}
