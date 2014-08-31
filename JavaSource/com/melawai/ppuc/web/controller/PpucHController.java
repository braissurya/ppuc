	package com.melawai.ppuc.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.melawai.ppuc.model.HakApprove;
import com.melawai.ppuc.model.Posisi;
import com.melawai.ppuc.model.Posisi.PosisiDesc;
import com.melawai.ppuc.model.PpucD;
import com.melawai.ppuc.model.PpucH;
import com.melawai.ppuc.model.User;
import com.melawai.ppuc.model.UserDivisi;
import com.melawai.ppuc.services.GroupBiayaManager;
import com.melawai.ppuc.services.PpucDManager;
import com.melawai.ppuc.services.PpucHManager;
import com.melawai.ppuc.services.UserDivisiManager;
import com.melawai.ppuc.utils.CommonUtil;
import com.melawai.ppuc.utils.Utils;
import com.melawai.ppuc.web.validator.PpucHValidator;

@RequestMapping("/trans/ppuch")
@Controller
public class PpucHController extends ParentController{

	protected static Logger logger = Logger.getLogger(PpucHController.class);

	@Autowired
	private PpucHManager ppuchManager;
	
	@Autowired
	private PpucDManager ppucdManager;
	
	@Autowired
	private UserDivisiManager userDivisiManager;
	
	@Autowired
	private PpucHValidator ppucHValidator;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(this.ppucHValidator);
	}
	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
	public String create(@ModelAttribute("ppuch") @Valid PpucH ppuch, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
	
		if(!bindingResult.hasErrors()){
			for(PpucD ppucd:ppuch.ppucds){
				DataBinder binder2 = new DataBinder(ppucd);
				binder2.addValidators(validator);
				// bind to the target object
				binder2.validate();

				if (binder2.getBindingResult().hasErrors()) {
					bindingResult.reject("idx", null, Utils.errorListToString(Utils.errorBinderToList(binder2.getBindingResult(), messageSource)));
				}
			}
		}
		
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, ppuch);
			return "ppuch/create";
		}
		uiModel.asMap().clear();
		ppuchManager.saveAll(ppuch);
		return "redirect:/trans/ppuch/batch/"+encodeUrlPathSegment(ppuch.getNo_batch().toString(), httpServletRequest)+"?form";
	}

	@RequestMapping(params = "form", produces = "text/html")
	public String createForm(Model uiModel) {
		PpucH ppuch= new PpucH();
		UserDivisi userdivisi=userDivisiManager.getDivisiNSubdivUser(CommonUtil.getCurrentUserId());
		ppuch.divisi_kd = userdivisi.getDivisi_kd();
		ppuch.subdiv_kd = userdivisi.getSubdiv_kd();
		ppuch.dept_kd=userdivisi.getDept_kd();
		ppuch.lok_kd=userdivisi.getLok_kd();
		
		populateEditForm(uiModel,ppuch);
		return "ppuch/create";
	}

	@RequestMapping(value = "/{divisi_kd}/{subdiv_kd}/{dept_kd}/{lok_kd}/{no_ppuc}/{tgl_ppuc}", produces = "text/html")
	public String show(@PathVariable("divisi_kd") String divisi_kd, @PathVariable("subdiv_kd") String subdiv_kd, @PathVariable("dept_kd") String dept_kd, @PathVariable("lok_kd") String lok_kd, @PathVariable("no_ppuc") String no_ppuc, @PathVariable("tgl_ppuc") Date tgl_ppuc, Model uiModel) {
		addDateTimeFormatPatterns(uiModel);
		uiModel.addAttribute("ppuch", ppuchManager.get(divisi_kd, subdiv_kd, dept_kd, lok_kd, no_ppuc, tgl_ppuc));
		uiModel.addAttribute("itemId", divisi_kd+"/"+subdiv_kd+"/"+dept_kd+"/"+lok_kd+"/"+no_ppuc+"/"+tgl_ppuc);
		return "ppuch/show";
	}

	@RequestMapping(produces = "text/html")
	public String listInput( @RequestParam(value = "groupBy", required = false) Integer groupBy, 
							 @RequestParam(value = "nb", required = false) String nb,
							 @RequestParam(value = "np", required = false) String np,
							 @RequestParam(value = "lk", required = false) String lk,
							 @RequestParam(value = "gb", required = false) String gb,
							 @RequestParam(value = "kb", required = false) String kb,
							 @RequestParam(value = "ps", required = false) Integer ps,
							 @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size,@RequestParam(value = "search", required = false) String search, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
		if (page == null) {
			page=1;
		}
		
		if(groupBy==null)groupBy=1;
		
		if (Utils.isEmpty(nb))	nb = null;
		else nb = nb.substring(nb.lastIndexOf(".") + 1);
		
		if (Utils.isEmpty(np)) 	np = null;
		else np = np.substring(np.lastIndexOf(".") + 1);
		
		if (Utils.isEmpty(lk)) lk = null;
		else lk = lk.substring(lk.lastIndexOf(".") + 1);
		
		if (Utils.isEmpty(gb))	gb = null;
		else gb = gb.substring(gb.lastIndexOf(".") + 1);
		
		if (Utils.isEmpty(kb)) kb = null;
		else kb = kb.substring(kb.lastIndexOf(".") + 1);
		
		if(groupBy==1) sortFieldName = "no_batch";
		else  sortFieldName = "no_ppuc";

		int sizeNo = size == null ? 10000 : size.intValue();
		final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
		uiModel.addAttribute("ppuchList",ppuchManager.selectPagingList(search,sortFieldName,sortOrder, firstResult, sizeNo,groupBy, nb, np, lk, gb, kb,null,PosisiDesc.INPUT_PPUC,null));
		float nrOfPages = (float) ppuchManager.selectPagingCount(search,groupBy,nb, np, lk, gb, kb,null,PosisiDesc.INPUT_PPUC,null) / sizeNo;
		uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
		addDateTimeFormatPatterns(uiModel);
		populateEditForm(uiModel, nb, np, lk, gb, kb);
		return "ppuch/list";
	}
	
	@RequestMapping(value = "/approval",produces = "text/html")
	public String listApp( @RequestParam(value = "groupBy", required = false) Integer groupBy, 
							 @RequestParam(value = "nb", required = false) String nb,
							 @RequestParam(value = "np", required = false) String np,
							 @RequestParam(value = "lk", required = false) String lk,
							 @RequestParam(value = "gb", required = false) String gb,
							 @RequestParam(value = "kb", required = false) String kb,
							 @RequestParam(value = "ps", required = false) Integer ps,
							 @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size,@RequestParam(value = "search", required = false) String search, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
		if (page == null) {
			page=1;
		}
		
		if(groupBy==null)groupBy=3;
		
		if (Utils.isEmpty(nb))	nb = null;
		else nb = nb.substring(nb.lastIndexOf(".") + 1);
		
		if (Utils.isEmpty(np)) 	np = null;
		else np = np.substring(np.lastIndexOf(".") + 1);
		
		if (Utils.isEmpty(lk)) lk = null;
		else lk = lk.substring(lk.lastIndexOf(".") + 1);
		
		if (Utils.isEmpty(gb))	gb = null;
		else gb = gb.substring(gb.lastIndexOf(".") + 1);
		
		if (Utils.isEmpty(kb)) kb = null;
		else kb = kb.substring(kb.lastIndexOf(".") + 1);
		
		if(groupBy==1) sortFieldName = "no_batch";
		else  sortFieldName = "no_ppuc";

		//FIXME : belum ada blok data hanya per divisi approval aja
		int sizeNo = size == null ? 10000 : size.intValue();
		final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
		uiModel.addAttribute("ppuchList",ppuchManager.selectPagingList(search,sortFieldName,sortOrder, firstResult, sizeNo,groupBy, nb, np, lk, gb, kb,null,PosisiDesc.APPROVAL_PPUC,null));
		float nrOfPages = (float) ppuchManager.selectPagingCount(search,groupBy,nb, np, lk, gb, kb,null,PosisiDesc.APPROVAL_PPUC,null) / sizeNo;
		uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
		addDateTimeFormatPatterns(uiModel);
		populateEditForm(uiModel, nb, np, lk, gb, kb);
		return "ppuch/listApp";
	}
	
	
	@RequestMapping(value = "/approval/save",method = RequestMethod.POST, produces = "text/html")
	public String saveListApp(@RequestParam(value = "ppuchs", required = true) String [] ppuchs,Model uiModel, HttpServletRequest request) {
		List<String> errorMessages=new ArrayList<String>();
		String pesan="";
		List<PpucH> lsPPuch=new ArrayList<PpucH>();
		for(String noppuc:ppuchs){
			int[]ids=ServletRequestUtils.getIntParameters(request, "ids_"+noppuc);
			PpucH ppuch=new PpucH(ServletRequestUtils.getStringParameter(request, "divisi_kd_"+noppuc, null),
								  ServletRequestUtils.getStringParameter(request, "subdiv_kd_"+noppuc, null), 
								  ServletRequestUtils.getStringParameter(request, "dept_kd_"+noppuc, null), 
								  ServletRequestUtils.getStringParameter(request, "lok_kd_"+noppuc, null), 
								  noppuc, 
								  Utils.convertStringToDate(ServletRequestUtils.getStringParameter(request, "tgl_ppuc_"+noppuc, null),DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale())),
								  ServletRequestUtils.getStringParameter(request, "no_batch_"+noppuc, null));
			for(Integer id:ids){
				PpucD ppucd= new PpucD(ppuch.divisi_kd, 
							ppuch.subdiv_kd,
							ppuch.dept_kd,
							ppuch.lok_kd, 
							ppuch.no_ppuc,
							ppuch.tgl_ppuc,
							ServletRequestUtils.getStringParameter(request, "kd_group_"+noppuc+"_" + id, null),
							ServletRequestUtils.getStringParameter(request, "kd_biaya_"+noppuc+"_" + id,null), 
							CommonUtil.convertToLong(ServletRequestUtils.getStringParameter(request, "qty_"+noppuc+"_" + id, null)), 
							CommonUtil.convertCurrencyToDouble(ServletRequestUtils.getStringParameter(request, "harga_"+noppuc+"_" + id, null)), 
							null, 
							null,
							null,
							null);
				
				
				String approval=ServletRequestUtils.getStringParameter(request, "status_"+noppuc+"_" + id,null);
				if(approval.equals("ACCEPTED")){
					if(ppucd.qty==null)errorMessages.add("Harap Isi QTY pada baris "+id);
					if(ppucd.harga==null)errorMessages.add("Harap Isi Harga pada baris "+id);
					
					if(!errorMessages.isEmpty())break;
					
					ppucd.total=ppucd.qty*ppucd.harga;
					ppucd.f_approval=1;
				}else if(approval.equals("DECLINED")){
					ppucd.qty=0l;
					ppucd.total=0.0;
					ppucd.f_approval=0;
				}
				ppucd.qty_old=CommonUtil.convertToLong(ServletRequestUtils.getStringParameter(request, "qty_old_"+noppuc+"_" + id, null));
				ppucd.harga_old=CommonUtil.convertCurrencyToDouble(ServletRequestUtils.getStringParameter(request, "harga_old_"+noppuc+"_" + id, null));
				
				ppucd.total_old=ppucd.qty_old*ppucd.harga_old;
				ppucd.ket_approve=ServletRequestUtils.getStringParameter(request, "ket_approve_"+noppuc+"_" + id, null);
				
				ppuch.ppucds.add(ppucd);
			}
			lsPPuch.add(ppuch);
		}
		
		if(errorMessages.isEmpty()){
			ppuchManager.saveAllApproval(lsPPuch);
			uiModel.addAttribute("pesan", messageSource.getMessage("entity_success", new String[]{"Approval PPUC"}, LocaleContextHolder.getLocale()));
			
		}else{
			uiModel.addAttribute("errorMessages",Utils.errorListToString(errorMessages));
		}
		
		return "redirect:/trans/ppuch/approval";
	}

	@RequestMapping(value = "/realisasi/cabang",produces = "text/html")
	public String listRealCabang( @RequestParam(value = "groupBy", required = false) Integer groupBy, 
							 @RequestParam(value = "nb", required = false) String nb,
							 @RequestParam(value = "np", required = false) String np,
							 @RequestParam(value = "lk", required = false) String lk,
							 @RequestParam(value = "gb", required = false) String gb,
							 @RequestParam(value = "kb", required = false) String kb,
							 @RequestParam(value = "ps", required = false) Integer ps,
							 @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size,@RequestParam(value = "search", required = false) String search, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
		if (page == null) {
			page=1;
		}
		
		if(groupBy==null)groupBy=2;
		
		if (Utils.isEmpty(nb))	nb = null;
		else nb = nb.substring(nb.lastIndexOf(".") + 1);
		
		if (Utils.isEmpty(np)) 	np = null;
		else np = np.substring(np.lastIndexOf(".") + 1);
		
		if (Utils.isEmpty(lk)) lk = null;
		else lk = lk.substring(lk.lastIndexOf(".") + 1);
		
		if (Utils.isEmpty(gb))	gb = null;
		else gb = gb.substring(gb.lastIndexOf(".") + 1);
		
		if (Utils.isEmpty(kb)) kb = null;
		else kb = kb.substring(kb.lastIndexOf(".") + 1);
		
		if(groupBy==1) sortFieldName = "no_batch";
		else  sortFieldName = "no_batch asc , no_ppuc";

		//FIXME : belum ada blok data hanya per divisi approval aja
		int sizeNo = size == null ? 10000 : size.intValue();
		final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
		uiModel.addAttribute("ppuchList",ppuchManager.selectPagingList(search,sortFieldName,sortOrder, firstResult, sizeNo,groupBy, nb, np, lk, gb, kb,null,null,new Integer []{PosisiDesc.PURCHASING}));
		float nrOfPages = (float) ppuchManager.selectPagingCount(search,groupBy,nb, np, lk, gb, kb,null,null,new Integer []{PosisiDesc.PURCHASING}) / sizeNo;
		uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
		addDateTimeFormatPatterns(uiModel);
		populateEditForm(uiModel, nb, np, lk, gb, kb);
		return "ppuch/listRealCabang";
	}

	@RequestMapping(value = "/realisasi/cabang/noppuc/save",method = RequestMethod.POST, produces = "text/html")
	public String saveRealCabang(@RequestParam(value = "ids", required = true) Integer [] ids,
			@RequestParam(value = "no_batch", required = false) String no_batch,
			@RequestParam(value = "no_ppuc", required = false) String no_ppuc,
			@RequestParam(value = "divisi_kd", required = false) String divisi_kd,
			@RequestParam(value = "subdiv_kd", required = false) String subdiv_kd,
			@RequestParam(value = "dept_kd", required = false) String dept_kd,
			@RequestParam(value = "lok_kd", required = false) String lok_kd,
			@RequestParam(value = "tgl_ppuc", required = false) String tgl_ppuc,
			Model uiModel, HttpServletRequest request) {
			List<String> errorMessages=new ArrayList<String>();
			String pesan="";
			List<PpucH> lsPPuch=new ArrayList<PpucH>();
			
			PpucH ppuch=new PpucH(divisi_kd,
								  subdiv_kd, 
								  dept_kd, 
								  lok_kd, 
								  no_ppuc, 
								  Utils.convertStringToDate(tgl_ppuc,DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale())),
								  no_batch);
			for(Integer id:ids){
				PpucD ppucd= ppucdManager.get(divisi_kd, subdiv_kd, dept_kd, lok_kd, no_ppuc, ppuch.tgl_ppuc, ServletRequestUtils.getStringParameter(request, "kd_biaya_"+id,null));
				
				ppucd.qty_real_cbg=CommonUtil.convertToLong(ServletRequestUtils.getStringParameter(request, "qty_"+ id, null));
				ppucd.harga_real_cbg=CommonUtil.convertCurrencyToDouble(ServletRequestUtils.getStringParameter(request, "harga_"+ id, null));
				
				if(ppucd.qty_real_cbg==null)errorMessages.add("Harap Isi QTY pada baris "+id);
				if(ppucd.harga_real_cbg==null)errorMessages.add("Harap Isi Harga pada baris "+id);
				
				if(!errorMessages.isEmpty())break;
				
				ppucd.total_real_cbg=ppucd.qty_real_cbg*ppucd.harga_real_cbg;
				
				
				ppuch.ppucds.add(ppucd);
			}
			lsPPuch.add(ppuch);
			
			if(errorMessages.isEmpty()){
				ppuchManager.saveAllRealCabang(lsPPuch);
				uiModel.addAttribute("pesan", messageSource.getMessage("entity_success", new String[]{"Realisasi Cabang PPUC"}, LocaleContextHolder.getLocale()));
				
			}else{
				uiModel.addAttribute("errorMessages",Utils.errorListToString(errorMessages));
				return "redirect:/trans/ppuch/realisasi/cabang/noppuc/"+encodeUrlPathSegment(no_ppuc, request)+"?form";
			}
			
			return "redirect:/trans/ppuch/realisasi/cabang";
		}


	@RequestMapping(value = "/realisasi/cabang/noppuc/{no_ppuc}", params = "form", produces = "text/html")
	public String updateFormRealisasiCabang(@PathVariable("no_ppuc") String no_ppuc, Model uiModel) {
		List<PpucH> ppuchs=ppuchManager.getBynoppuc(no_ppuc);
		if(!ppuchs.isEmpty()){
			PpucH ppuch=ppuchs.get(0);
			List<PpucD> tmp=new ArrayList<PpucD>();
			for(PpucH pp:ppuchs){
				tmp.addAll(pp.ppucds);
			}
			
			List<PpucD> tmp2=new ArrayList<PpucD>();
			for(PpucD pd:tmp){
				if(tmp2.isEmpty())tmp2.add(pd);
				else {
					boolean add=true;
					for (int i = 0; i < tmp2.size(); i++) {
						if(pd.kd_biaya.equals(tmp2.get(i).kd_biaya)){
							tmp2.get(i).qty+=pd.qty;
							tmp2.get(i).no_ppuc+=";"+pd.no_ppuc;
							add=false;
							break;
						}
					}
					
					if(add)tmp2.add(pd);
				}
			}
			ppuch.ppucds=tmp2;
			populateEditForm(uiModel,ppuch);
		}
		return "ppuch/updateRealCabang";
	}

	@RequestMapping(value = "/batch",method = RequestMethod.PUT, produces = "text/html")
	public String updateInput(@ModelAttribute("ppuch") @Valid PpucH ppuch, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, ppuch);
			return "ppuch/update";
		}
		uiModel.asMap().clear();
		ppuchManager.saveAll(ppuch);
		return "redirect:/trans/ppuch/batch/"+encodeUrlPathSegment(ppuch.getNo_batch().toString(), httpServletRequest)+"?form";
	}

	@RequestMapping(value = "/batch/{no_batch}", params = "form", produces = "text/html")
	public String updateFormInput(@PathVariable("no_batch") String no_batch, Model uiModel) {
		List<PpucH> ppuchs=ppuchManager.get(no_batch);
		if(!ppuchs.isEmpty()){
			PpucH ppuch=ppuchs.get(0);
			List<PpucD> tmp=new ArrayList<PpucD>();
			for(PpucH pp:ppuchs){
				tmp.addAll(pp.ppucds);
			}
			
			List<PpucD> tmp2=new ArrayList<PpucD>();
			for(PpucD pd:tmp){
				if(tmp2.isEmpty())tmp2.add(pd);
				else {
					boolean add=true;
					for (int i = 0; i < tmp2.size(); i++) {
						if(pd.kd_biaya.equals(tmp2.get(i).kd_biaya)){
							tmp2.get(i).qty+=pd.qty;
							tmp2.get(i).no_ppuc+=";"+pd.no_ppuc;
							add=false;
							break;
						}
					}
					
					if(add)tmp2.add(pd);
				}
			}
			ppuch.ppucds=tmp2;
			populateEditForm(uiModel,ppuch);
		}
		return "ppuch/update";
	}
	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
	public String update(@ModelAttribute("ppuch") @Valid PpucH ppuch, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, ppuch);
			return "ppuch/update";
		}
		uiModel.asMap().clear();
		ppuchManager.saveAll(ppuch);
		return "redirect:/trans/ppuch/" + encodeUrlPathSegment(ppuch.getDivisi_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(ppuch.getSubdiv_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(ppuch.getDept_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(ppuch.getLok_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(ppuch.getNo_ppuc().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(ppuch.getTgl_ppuc().toString(), httpServletRequest);
	}

	@RequestMapping(value = "/{divisi_kd}/{subdiv_kd}/{dept_kd}/{lok_kd}/{no_ppuc}/{tgl_ppuc}", params = "form", produces = "text/html")
	public String updateForm(@PathVariable("divisi_kd") String divisi_kd, @PathVariable("subdiv_kd") String subdiv_kd, @PathVariable("dept_kd") String dept_kd, @PathVariable("lok_kd") String lok_kd, @PathVariable("no_ppuc") String no_ppuc, @PathVariable("tgl_ppuc") Date tgl_ppuc, Model uiModel) {
		populateEditForm(uiModel, ppuchManager.get(divisi_kd, subdiv_kd, dept_kd, lok_kd, no_ppuc, tgl_ppuc));
		return "ppuch/update";
	}

	@RequestMapping(value = "/{divisi_kd}/{subdiv_kd}/{dept_kd}/{lok_kd}/{no_ppuc}/{tgl_ppuc}", method = RequestMethod.DELETE, produces = "text/html")
	public String delete(@PathVariable("divisi_kd") String divisi_kd, @PathVariable("subdiv_kd") String subdiv_kd, @PathVariable("dept_kd") String dept_kd, @PathVariable("lok_kd") String lok_kd, @PathVariable("no_ppuc") String no_ppuc, @PathVariable("tgl_ppuc") Date tgl_ppuc, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
		ppuchManager.remove(divisi_kd, subdiv_kd, dept_kd, lok_kd, no_ppuc, tgl_ppuc);
		uiModel.asMap().clear();
		uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
		uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
		return "redirect:/trans/ppuch";
	}
	
	@RequestMapping(value = "/{no_batch}", method = RequestMethod.DELETE, produces = "text/html")
	public String delete(@PathVariable("no_batch") String no_batch, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
		
		String pesan=messageSource.getMessage("entity_success", new String[]{"Delete PPUC : "+no_batch+","}, LocaleContextHolder.getLocale());
		if(ppuchManager.get(no_batch).isEmpty()){
			pesan="No Batch "+no_batch+" tidak ditemukan";
			messageSource.getMessage("entity_not_found", new String[]{"No Batch : "+no_batch+","}, LocaleContextHolder.getLocale());
		}else{
			ppuchManager.remove(no_batch);
		}
		uiModel.asMap().clear();
		uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
		uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
		uiModel.addAttribute("pesan", pesan);
		return "redirect:/trans/ppuch";
	}
	
	@RequestMapping(value = "/confirm/{no_batch}",method = RequestMethod.PUT, produces = "text/html")
	public String confirmInput(@PathVariable("no_batch") String no_batch, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
		String pesan=messageSource.getMessage("entity_success", new String[]{"Confirm Input PPUC : "+no_batch+","}, LocaleContextHolder.getLocale());
		if(ppuchManager.get(no_batch).isEmpty()){
			pesan="No Batch "+no_batch+" tidak ditemukan";
			messageSource.getMessage("entity_not_found", new String[]{"No Batch : "+no_batch+","}, LocaleContextHolder.getLocale());
		}else{
			ppuchManager.confirmInput(no_batch);
		}
		uiModel.asMap().clear();
		uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
		uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
		uiModel.addAttribute("pesan", pesan);
		return "redirect:/trans/ppuch";
	}
	
	void addDateTimeFormatPatterns(Model uiModel) {
		uiModel.addAttribute("ppuch_tgl_ppuc_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
		uiModel.addAttribute("ppuch_tgl_create_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
		uiModel.addAttribute("ppuch_tgl_confirm_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
		uiModel.addAttribute("ppuch_tgl_approve_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
		uiModel.addAttribute("ppuch_tgl_realisasi_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
		uiModel.addAttribute("ppuch_tgl_conf_real_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
		uiModel.addAttribute("ppuch_tgl_conf_oc_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
		uiModel.addAttribute("ppuch_tgl_batal_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
	}
	void populateEditForm(Model uiModel, PpucH ppuch) {
		uiModel.addAttribute("ppuch", ppuch);
		addDateTimeFormatPatterns(uiModel);
		populateEditFormAdditional( uiModel,  ppuch);
	}
	
	void populateEditFormAdditional(Model uiModel, PpucH ppuch){
		String user_div="";
		
		if("MK".contains(CommonUtil.getCurrentUserKdFungsi()))
			user_div="and ud.user_id='"+CommonUtil.getCurrentUserId()+"'";
		
		uiModel.addAttribute("divisiList", baseService.selectDropDown("DISTINCT d.divisi_nm", "d.divisi_kd", "user_divisi ud, divisi d", "ud.divisi_kd = d.divisi_kd "+user_div+"  group by d.divisi_nm, d.divisi_kd", "d.divisi_nm"));
		
		if (!Utils.isEmpty( ppuch.divisi_kd ))
			uiModel.addAttribute("subdivList", baseService.selectDropDown("DISTINCT concat(sd.divisi_kd, '.', sd.subdiv_kd)", "sd.subdiv_nm", "user_divisi ud, subdivisi sd", "ud.divisi_kd = sd.divisi_kd and ud.subdiv_kd = sd.subdiv_kd and ud.divisi_kd = '" + ppuch.divisi_kd + "' "+user_div+" group by sd.subdiv_nm, sd.subdiv_kd", "sd.subdiv_nm"));

		if (!Utils.isEmpty( ppuch.divisi_kd )&&!Utils.isEmpty( ppuch.subdiv_kd ))
			uiModel.addAttribute("deptList", baseService.selectDropDown("DISTINCT concat(dp.divisi_kd, '.', dp.subdiv_kd, '.', dp.dept_kd)","dp.dept_nm", "user_divisi ud, departmen dp", "ud.divisi_kd = dp.divisi_kd and ud.subdiv_kd = dp.subdiv_kd and ud.dept_kd = dp.dept_kd and ud.divisi_kd = '" + ppuch.divisi_kd + "' and ud.subdiv_kd = '" + ppuch.subdiv_kd + "'  "+user_div+" group by dp.dept_nm, dp.dept_kd", "dept_nm"));

		if (!Utils.isEmpty( ppuch.divisi_kd )&&!Utils.isEmpty( ppuch.subdiv_kd )&&!Utils.isEmpty( ppuch.dept_kd ))
			uiModel.addAttribute("lokList", baseService.selectDropDown("DISTINCT concat(lk.divisi_kd, '.', lk.subdiv_kd, '.', lk.dept_kd, '.', lk.lok_kd)","lk.lok_nm", "user_divisi ud,lokasi lk", "ud.divisi_kd = lk.divisi_kd and ud.subdiv_kd = lk.subdiv_kd and ud.dept_kd = lk.dept_kd and ud.lok_kd = lk.lok_kd and ud.divisi_kd = '" + ppuch.divisi_kd + "' and ud.subdiv_kd = '" + ppuch.subdiv_kd + "' and ud.dept_kd = '" + ppuch.dept_kd + "' "+user_div+" group by lk.lok_nm, lk.lok_kd", "lk.lok_nm"));
		else if (!Utils.isEmpty( ppuch.divisi_kd )&&!Utils.isEmpty( ppuch.subdiv_kd ))
			uiModel.addAttribute("lokList", baseService.selectDropDown("DISTINCT concat(lk.divisi_kd, '.', lk.subdiv_kd, '.', lk.dept_kd, '.', lk.lok_kd)","lk.lok_nm", "user_divisi ud,lokasi lk", "ud.divisi_kd = lk.divisi_kd and ud.subdiv_kd = lk.subdiv_kd and ud.dept_kd = lk.dept_kd and ud.lok_kd = lk.lok_kd and ud.divisi_kd = '" + ppuch.divisi_kd + "' and ud.subdiv_kd = '" + ppuch.subdiv_kd + "' "+user_div+" group by lk.lok_nm, lk.lok_kd", "lk.lok_nm"));
		
		if (!Utils.isEmpty( ppuch.divisi_kd )&&!Utils.isEmpty( ppuch.subdiv_kd )&&!Utils.isEmpty( ppuch.dept_kd )&&!Utils.isEmpty( ppuch.lok_kd))
			uiModel.addAttribute("groupbiayaList", baseService.selectDropDown("DISTINCT concat(hb.divisi_kd, '.', hb.subdiv_kd, '.', hb.dept_kd, '.', hb.lok_kd,'.',hb.kd_group)", "gb.nm_group", "hak_biaya hb, group_biaya gb", "hb.kd_group = gb.kd_group and hb.f_aktif=1 and hb.divisi_kd = '" + ppuch.divisi_kd + "' and hb.subdiv_kd = '" + ppuch.subdiv_kd + "' and hb.dept_kd = '" + ppuch.dept_kd + "' and hb.lok_kd = '" + ppuch.lok_kd + "' group by gb.nm_group, gb.kd_group", "gb.nm_group"));
		else if (!Utils.isEmpty( ppuch.divisi_kd )&&!Utils.isEmpty( ppuch.subdiv_kd )&&!Utils.isEmpty( ppuch.dept_kd ))
			uiModel.addAttribute("groupbiayaList", baseService.selectDropDown("DISTINCT concat(hb.divisi_kd, '.', hb.subdiv_kd, '.', hb.dept_kd, '.', hb.lok_kd,'.',hb.kd_group)", "gb.nm_group", "hak_biaya hb, group_biaya gb", "hb.kd_group = gb.kd_group and hb.f_aktif=1 and hb.divisi_kd = '" + ppuch.divisi_kd + "' and hb.subdiv_kd = '" + ppuch.subdiv_kd + "' and hb.dept_kd = '" + ppuch.dept_kd + "' group by gb.nm_group, gb.kd_group", "gb.nm_group"));
			
		if (!Utils.isEmpty( ppuch.divisi_kd )&&!Utils.isEmpty( ppuch.subdiv_kd )&&!Utils.isEmpty( ppuch.dept_kd )&&!Utils.isEmpty( ppuch.lok_kd)&&!Utils.isEmpty( ppuch.kd_group))
			uiModel.addAttribute("detailbiayaList", baseService.selectDropDown("DISTINCT concat(hb.divisi_kd, '.', hb.subdiv_kd, '.', hb.dept_kd, '.', hb.lok_kd,'.',hb.kd_group,'.',db.kd_biaya)", "db.kd_biaya", "hak_biaya hb, detail_biaya db", "hb.kd_group = db.kd_group and db.f_used = 1 and hb.kd_biaya = db.kd_biaya and hb.f_aktif=1 and hb.divisi_kd = '" + ppuch.divisi_kd + "' and hb.subdiv_kd = '" + ppuch.subdiv_kd + "' and hb.dept_kd = '" + ppuch.dept_kd + "' and hb.lok_kd = '" + ppuch.lok_kd + "'  and db.kd_group = '" + ppuch.kd_group + "'  group by db.kd_biaya", "db.kd_biaya"));
		else if (!Utils.isEmpty( ppuch.divisi_kd )&&!Utils.isEmpty( ppuch.subdiv_kd )&&!Utils.isEmpty( ppuch.dept_kd )&&!Utils.isEmpty( ppuch.kd_group))
			uiModel.addAttribute("detailbiayaList", baseService.selectDropDown("DISTINCT concat(hb.divisi_kd, '.', hb.subdiv_kd, '.', hb.dept_kd, '.', hb.lok_kd,'.',hb.kd_group,'.',db.kd_biaya)", "db.kd_biaya", "hak_biaya hb, detail_biaya db", "hb.kd_group = db.kd_group and  db.f_used = 1 and hb.kd_biaya = db.kd_biaya and hb.f_aktif=1 and hb.divisi_kd = '" + ppuch.divisi_kd + "' and hb.subdiv_kd = '" + ppuch.subdiv_kd + "' and hb.dept_kd = '" + ppuch.dept_kd + "' and db.kd_group = '" + ppuch.kd_group + "'  group by db.kd_biaya", "db.kd_biaya"));
		
	}
	
	void populateEditForm(Model uiModel,  String nb,String np, String lk,String gb,String kb) {
		addDateTimeFormatPatterns(uiModel);
		
		//TODO : tambahan validasi untuk hak akses data belum ada
		User currentUser=CommonUtil.getCurrentUser();
		String filter_by_user="";
		
		
		uiModel.addAttribute("f_nb", baseService.selectDropDown("DISTINCT no_batch","no_batch",  "ppuc_h", "1=1  "+filter_by_user+" group by no_batch", "no_batch"));
		
		if (!Utils.isEmpty(nb))
			uiModel.addAttribute("f_np", baseService.selectDropDown("DISTINCT sys_no_ppuc", "sys_no_ppuc", "ppuc_h", "1=1 and no_batch='"+nb+"' "+filter_by_user+" group by sys_no_ppuc", "sys_no_ppuc"));
		else
			uiModel.addAttribute("f_np", baseService.selectDropDown("DISTINCT sys_no_ppuc", "sys_no_ppuc", "ppuc_h", "1=1 "+filter_by_user+" group by sys_no_ppuc", "sys_no_ppuc"));

		if (!Utils.isEmpty(np))
			uiModel.addAttribute("f_lk", baseService.selectDropDown("DISTINCT a.lok_kd","lok_nm",  "lokasi  a left join ppuc_h b ON a.lok_kd = b.lok_kd ", " 1=1 and b.sys_no_ppuc='"+np+"' "+filter_by_user+" group by a.lok_nm", "a.lok_nm"));
		else
			uiModel.addAttribute("f_lk", baseService.selectDropDown("DISTINCT a.lok_kd","lok_nm",  "lokasi  a left join ppuc_h b ON a.lok_kd = b.lok_kd ", " 1=1 "+filter_by_user+" group by a.lok_nm", "a.lok_nm"));
		
		if(!Utils.isEmpty(lk))
			uiModel.addAttribute("f_gb", baseService.selectDropDown("DISTINCT a.kd_group","nm_group",  "group_biaya a left join ppuc_d b ON a.kd_group = b.kd_group ", "1=1 and b.lok_kd = '"+lk+"' "+filter_by_user+"  group by a.nm_group", "a.nm_group"));
		else
			uiModel.addAttribute("f_gb", baseService.selectDropDown("DISTINCT a.kd_group","nm_group",  "group_biaya a left join ppuc_d b ON a.kd_group = b.kd_group ", "1=1 "+filter_by_user+"  group by a.nm_group", "a.nm_group"));
		
		if(!Utils.isEmpty(gb))
			uiModel.addAttribute("f_kb", baseService.selectDropDown("DISTINCT a.kd_biaya","a.kd_biaya",  "detail_biaya a left join ppuc_d b ON a.kd_biaya = b.kd_biaya ", "1=1 and a.kd_group = '"+gb+"' "+filter_by_user+" group by a.kd_biaya", "a.kd_biaya"));
		else
			uiModel.addAttribute("f_kb", baseService.selectDropDown("DISTINCT a.kd_biaya","a.kd_biaya",  "detail_biaya a left join ppuc_d b ON a.kd_biaya = b.kd_biaya ", "1=1  "+filter_by_user+" group by a.kd_biaya", "a.kd_biaya"));
		
		uiModel.addAttribute("nb", nb);
		uiModel.addAttribute("np", np);
		uiModel.addAttribute("lk", lk);
		uiModel.addAttribute("gb", gb);
		uiModel.addAttribute("kb", kb);
	}
	
	
	/**
	 * JSON HELP
	 */
	
	@RequestMapping("/json/getData")
	public String jsonGetData(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam(value = "no_batch", required = true, defaultValue="") String no_batch) throws IOException, ParseException {
		response.setContentType("application/json");

		List<PpucH> lsPPuch=ppuchManager.get(no_batch);


			if (!lsPPuch.isEmpty()) {
				int rowNum = 1;

				List<Map> ppuchList = new ArrayList<Map>();
				for (PpucH ppuch : lsPPuch) {
					for(PpucD ppucd:ppuch.getPpucds()){
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("idx", rowNum);
						map.put("no_ppuc", ppucd.no_ppuc);
						map.put("tgl_ppuc", Utils.convertDateToString( ppucd.tgl_ppuc,DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale())));
						map.put("kd_group", ppucd.kd_group);
						map.put("nm_group", ppucd.groupBiaya.nm_group);
						map.put("kd_biaya", ppucd.kd_biaya);
						map.put("nm_biaya", Utils.getLastDelimiterString(ppucd.kd_biaya, "."));
						map.put("qty", Utils.formatNumber(props.getProperty("number.curr.format"), ppucd.qty));
						if(ppucd.harga != null)map.put("harga", Utils.formatNumber(props.getProperty("number.curr.format"), ppucd.harga));
						if(ppucd.total != null)map.put("total", Utils.formatNumber(props.getProperty("number.curr.format"), ppucd.total));
						map.put("keterangan", ppucd.keterangan);
						ppuchList.add(map);
						rowNum++;
					}
				}

				Map<String, Object> map = new HashMap<String, Object>();
				map.put("no_batch", "" + no_batch);
				map.put("ppuchList", ppuchList);


				response.setContentType("application/json");
				PrintWriter write = response.getWriter();
				Gson gson = new Gson();
				write.print(gson.toJson(map));
				write.close();
			} else {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("no_batch", "" + no_batch);
				map.put("grouplokasidList", "");

				response.setContentType("application/json");
				PrintWriter write = response.getWriter();
				Gson gson = new Gson();
				write.print(gson.toJson(map));
				write.close();
			}
		return null;

	}

	@RequestMapping("/json/addData")
	public String jsonAddData(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "divisi_kd", required = false, defaultValue = "") String divisi_kd,
			@RequestParam(value = "subdiv_kd", required = false, defaultValue = "") String subdiv_kd,
			@RequestParam(value = "dept_kd", required = false, defaultValue = "") String dept_kd,
			@RequestParam(value = "lok_kd", required = false, defaultValue = "") String lok_kd,
			@RequestParam(value = "kd_group", required = false, defaultValue = "") String kd_group,
			@RequestParam(value = "no_ppuc", required = false, defaultValue = "") String no_ppuc,
			@RequestParam(value = "tgl_ppuc", required = false, defaultValue = "") String tgl_ppuc,
			@RequestParam(value = "kd_biaya", required = false, defaultValue = "") String kd_biaya,
			@RequestParam(value = "qty", required = false, defaultValue = "") String qty,
			@RequestParam(value = "harga", required = false, defaultValue = "") String harga,
			@RequestParam(value = "total", required = false, defaultValue = "") String total,
			@RequestParam(value = "keterangan", required = false, defaultValue = "") String keterangan,
			@RequestParam(value = "idx", required = false, defaultValue = "")Integer[] idxList ) throws IOException, ParseException {
		response.setContentType("application/json");

		boolean isSame = false;
		int rowNum = 1;

		List<Map> ppuchList = new ArrayList<Map>();
		if (idxList.length != 0) {

			for (int idx : idxList) {
				PpucD ppucd= new PpucD(divisi_kd, subdiv_kd, dept_kd, lok_kd,
						ServletRequestUtils.getStringParameter(request, "no_ppuc_" + idx, null),
						Utils.convertStringToDate(ServletRequestUtils.getStringParameter(request, "tgl_ppuc_" + idx, null),
								DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale())),
								ServletRequestUtils.getStringParameter(request, "kd_group_" + idx, null),
								ServletRequestUtils.getStringParameter(request, "kd_biaya_" + idx,null), 
								CommonUtil.convertToLong(ServletRequestUtils.getStringParameter(request, "qty_" + idx, null)), 
								CommonUtil.convertCurrencyToDouble(ServletRequestUtils.getStringParameter(request, "harga_" + idx, null)), 
								CommonUtil.convertCurrencyToDouble(ServletRequestUtils.getStringParameter(request, "total_" + idx, null)), 
								ServletRequestUtils.getStringParameter(request, "keterangan_" + idx, null),
								ServletRequestUtils.getStringParameter(request, "nm_group_" + idx, ""),
								ServletRequestUtils.getStringParameter(request, "nm_biaya_" + idx, ""));
				if (kd_biaya.equals(ppucd.kd_biaya)) {
					isSame = true;
					ppucd.qty=ppucd.qty+CommonUtil.convertToLong(qty);
				}

				Map<String, Object> map = new HashMap<String, Object>();
				map.put("idx", rowNum);
				map.put("no_ppuc", ppucd.no_ppuc);
				map.put("tgl_ppuc", Utils.convertDateToString( ppucd.tgl_ppuc,DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale())));
				map.put("kd_group", ppucd.kd_group);
				map.put("nm_group", ppucd.nm_group);
				map.put("kd_biaya", ppucd.kd_biaya);
				map.put("nm_biaya", Utils.getLastDelimiterString( ppucd.kd_biaya, "."));
				map.put("qty", Utils.formatNumber(props.getProperty("number.curr.format"), ppucd.qty));
				if(ppucd.harga != null)map.put("harga", Utils.formatNumber(props.getProperty("number.curr.format"), ppucd.harga));
				if(ppucd.harga != null && ppucd.qty != null)map.put("total", Utils.formatNumber(props.getProperty("number.curr.format"), ppucd.harga * ppucd.qty));
				map.put("keterangan", ppucd.keterangan);
				ppuchList.add(map);
				rowNum++;
			}
		}

		if (!isSame) {
			Long qtyV=CommonUtil.convertToLong(qty);
			Double hargaV= CommonUtil.convertCurrencyToDouble(harga);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("idx", rowNum);
			map.put("no_ppuc", no_ppuc);
			map.put("tgl_ppuc", tgl_ppuc);
			map.put("kd_group", kd_group);
			map.put("nm_group", ppuchManager.getGroupBiaya(Utils.getLastDelimiterString(kd_group, ".")));
			map.put("kd_biaya", kd_biaya);
			map.put("nm_biaya", Utils.getLastDelimiterString(kd_biaya, "."));
			map.put("qty", Utils.formatNumber(props.getProperty("number.curr.format"), qtyV));
			map.put("harga", Utils.formatNumber(props.getProperty("number.curr.format"), hargaV));
			map.put("total", Utils.formatNumber(props.getProperty("number.curr.format"), qtyV*hargaV));
			map.put("keterangan", keterangan);
			ppuchList.add(map);
		}

		response.setContentType("application/json");
		PrintWriter write = response.getWriter();
		Gson gson = new Gson();
		write.print(gson.toJson(ppuchList));
		write.close();

		return null;
	}

	@RequestMapping("/json/removeData")
	public String jsonRemoveData(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam(value = "divisi_kd", required = false, defaultValue = "") String divisi_kd,
			@RequestParam(value = "subdiv_kd", required = false, defaultValue = "") String subdiv_kd,
			@RequestParam(value = "dept_kd", required = false, defaultValue = "") String dept_kd,
			@RequestParam(value = "lok_kd", required = false, defaultValue = "") String lok_kd,
			@RequestParam(value = "kd_group", required = false, defaultValue = "") String kd_group,
			@RequestParam(value = "kd_biaya", required = false, defaultValue = "") String kd_biaya,
			@RequestParam(value = "id", required = false, defaultValue = "0") Integer id,
			@RequestParam(value = "idx", required = false, defaultValue = "")Integer[] idxList) throws IOException, ParseException {
		response.setContentType("application/json");

		
		int rowNum = 1;
		List<Map> ppuchList = new ArrayList<Map>();

		if (idxList.length != 0) {
			for (int idx : idxList) {
				if (idx == id)
					continue;

				PpucD ppucd= new PpucD(divisi_kd, subdiv_kd, dept_kd, lok_kd, ServletRequestUtils.getStringParameter(request, "no_ppuc_" + idx, null), Utils.convertStringToDate(ServletRequestUtils.getStringParameter(request, "tgl_ppuc_" + idx, null),DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale())), ServletRequestUtils.getStringParameter(request, "kd_group_" + idx, null), ServletRequestUtils.getStringParameter(request, "kd_biaya_" + idx,null), CommonUtil.convertToLong(ServletRequestUtils.getStringParameter(request, "qty_" + idx, null)), CommonUtil.convertCurrencyToDouble(ServletRequestUtils.getStringParameter(request, "harga_" + idx, null)), CommonUtil.convertCurrencyToDouble(ServletRequestUtils.getStringParameter(request, "total_" + idx, null)), ServletRequestUtils.getStringParameter(request, "keterangan_" + idx, null),ServletRequestUtils.getStringParameter(request, "nm_group_" + idx, ""),ServletRequestUtils.getStringParameter(request, "nm_biaya_" + idx, ""));


				Map<String, Object> map = new HashMap<String, Object>();
				map.put("idx", rowNum);
				map.put("no_ppuc", ppucd.no_ppuc);
				map.put("tgl_ppuc", Utils.convertDateToString( ppucd.tgl_ppuc,DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale())));
				map.put("kd_group", ppucd.kd_group);
				map.put("nm_group", ServletRequestUtils.getStringParameter(request, "nm_group_" + idx, ""));
				map.put("kd_biaya", ppucd.kd_biaya);
				map.put("nm_biaya", Utils.getLastDelimiterString( ppucd.kd_biaya, "."));
				map.put("qty", Utils.formatNumber(props.getProperty("number.curr.format"), ppucd.qty));
				if(ppucd.harga != null)map.put("harga", Utils.formatNumber(props.getProperty("number.curr.format"), ppucd.harga));
				if(ppucd.harga != null && ppucd.qty != null)map.put("total", Utils.formatNumber(props.getProperty("number.curr.format"), ppucd.harga * ppucd.qty));
				map.put("keterangan", ppucd.keterangan);
				ppuchList.add(map);
			}

		}

		response.setContentType("application/json");
		PrintWriter write = response.getWriter();
		Gson gson = new Gson();
		write.print(gson.toJson(ppuchList));
		write.close();

		return null;
	}
}
