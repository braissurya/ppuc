package com.melawai.ppuc.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.melawai.ppuc.model.Audittrail;
import com.melawai.ppuc.model.HakApprove;
import com.melawai.ppuc.services.HakApproveManager;
import com.melawai.ppuc.utils.CommonUtil;
import com.melawai.ppuc.utils.Utils;
import com.melawai.ppuc.web.validator.HakApproveValidator;

@RequestMapping("/master/hakapprove")
@Controller
public class HakApproveController extends ParentController{

	protected static Logger logger = Logger.getLogger(HakApproveController.class);

	@Autowired
	private HakApproveManager hakapproveManager;
	
	@Autowired
	private HakApproveValidator hakApproveValidator;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(hakApproveValidator);
	}
	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
	public String create(@Valid HakApprove hakapprove, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (hakapproveManager.exists(hakapprove.user_id, hakapprove.divisi_kd, hakapprove.subdiv_kd, hakapprove.dept_kd, hakapprove.kd_group, hakapprove.kd_biaya)) {
			bindingResult.rejectValue("kd_biaya", "duplicate", new String[] { "DIVISI KD : " + hakapprove.divisi_kd + " | SUBDIVISI KD : " + hakapprove.subdiv_kd + " | DEPARTMEN KD : " + hakapprove.dept_kd + " | KD HAKBIAYA : " + hakapprove.kd_biaya + ", " }, null);
		}
		
		if(hakapproveManager.selectCountTable("detail_biaya", "kd_biaya = '"+hakapprove.kd_biaya+"' and f_used =1")>0){
			bindingResult.rejectValue("kd_biaya", "entity_not_exist", new String[] { "KD BIAYA" }, null);
		}
		
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, hakapprove);
			return "hakapprove/create";
		}
		uiModel.asMap().clear();
		hakapproveManager.save(hakapprove);
		return "redirect:/master/hakapprove/" + encodeUrlPathSegment(hakapprove.getUser_id().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(hakapprove.getDivisi_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(hakapprove.getSubdiv_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(hakapprove.getDept_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(hakapprove.getKd_group().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(hakapprove.getKd_biaya().toString(), httpServletRequest);
	}

	@RequestMapping(params = "form", produces = "text/html")
	public String createForm(Model uiModel) {
		populateEditForm(uiModel, new HakApprove());
		return "hakapprove/create";
	}

	@RequestMapping(value = "/{user_id}/{divisi_kd}/{subdiv_kd}/{dept_kd}/{kd_group}/{kd_biaya}", produces = "text/html")
	public String show(@PathVariable("user_id") String user_id, @PathVariable("divisi_kd") String divisi_kd, @PathVariable("subdiv_kd") String subdiv_kd, @PathVariable("dept_kd") String dept_kd, @PathVariable("kd_group") String kd_group, @PathVariable("kd_biaya") String kd_biaya, Model uiModel) {
		addDateTimeFormatPatterns(uiModel);
		HakApprove hakapprove=hakapproveManager.get(user_id, divisi_kd, subdiv_kd, dept_kd, kd_group, kd_biaya);
		uiModel.addAttribute("hakapprove",hakapprove );
		uiModel.addAttribute("itemId", user_id+"/"+divisi_kd+"/"+subdiv_kd+"/"+dept_kd+"/"+kd_group+"/"+kd_biaya);
		
		populateEditFormAdditional(uiModel, hakapprove);
		return "hakapprove/show";
	}

	@RequestMapping(produces = "text/html")
	public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size,@RequestParam(value = "search", required = false) String search, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
		if (page == null) {
			page=1;
		}

			int sizeNo = size == null ? 10 : size.intValue();
			final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
			uiModel.addAttribute("hakapproveList",hakapproveManager.selectPagingList(search,sortFieldName,sortOrder, firstResult, sizeNo) );
			float nrOfPages = (float) hakapproveManager.selectPagingCount(search) / sizeNo;
			uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
		addDateTimeFormatPatterns(uiModel);
		return "hakapprove/list";
	}

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
	public String update(@Valid HakApprove hakapprove, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, hakapprove);
			return "hakapprove/update";
		}
		uiModel.asMap().clear();
		hakapproveManager.save(hakapprove);
		return "redirect:/master/hakapprove/" + encodeUrlPathSegment(hakapprove.getUser_id().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(hakapprove.getDivisi_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(hakapprove.getSubdiv_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(hakapprove.getDept_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(hakapprove.getKd_group().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(hakapprove.getKd_biaya().toString(), httpServletRequest);
	}

	@RequestMapping(value = "/{user_id}/{divisi_kd}/{subdiv_kd}/{dept_kd}/{kd_group}/{kd_biaya}", params = "form", produces = "text/html")
	public String updateForm(@PathVariable("user_id") String user_id, @PathVariable("divisi_kd") String divisi_kd, @PathVariable("subdiv_kd") String subdiv_kd, @PathVariable("dept_kd") String dept_kd, @PathVariable("kd_group") String kd_group, @PathVariable("kd_biaya") String kd_biaya, Model uiModel) {
		populateEditForm(uiModel, hakapproveManager.get(user_id, divisi_kd, subdiv_kd, dept_kd, kd_group, kd_biaya));
		return "hakapprove/update";
	}

	@RequestMapping(value = "/{user_id}/{divisi_kd}/{subdiv_kd}/{dept_kd}/{kd_group}/{kd_biaya}", method = RequestMethod.DELETE, produces = "text/html")
	public String delete(@PathVariable("user_id") String user_id, @PathVariable("divisi_kd") String divisi_kd, @PathVariable("subdiv_kd") String subdiv_kd, @PathVariable("dept_kd") String dept_kd, @PathVariable("kd_group") String kd_group, @PathVariable("kd_biaya") String kd_biaya, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
		HakApprove hakapprove = hakapproveManager.get(user_id, divisi_kd, subdiv_kd, dept_kd, kd_group, kd_biaya);
		hakapproveManager.remove(user_id, divisi_kd, subdiv_kd, dept_kd, kd_group, kd_biaya);
		uiModel.asMap().clear();
		uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
		uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
		return "redirect:/master/hakapprove";
	}
	void addDateTimeFormatPatterns(Model uiModel) {
		uiModel.addAttribute("hakapprove_drtgl_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
		uiModel.addAttribute("hakapprove_sptgl_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
		uiModel.addAttribute("hakapprove_tgl_create_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
		uiModel.addAttribute("hakapprove_tgl_create_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
	}
	void populateEditForm(Model uiModel, HakApprove hakapprove) {
		uiModel.addAttribute("hakapprove", hakapprove);
		addDateTimeFormatPatterns(uiModel);
		
		populateEditFormAdditional(uiModel, hakapprove);
	}
	
	void populateEditFormAdditional(Model uiModel, HakApprove hakapprove){
		uiModel.addAttribute("useridList", baseService.selectDropDown("concat(user_id,' [ ',user_name,' ]')", "user_id", "user", "kd_fungsi='AP'", "user_id"));
		
		uiModel.addAttribute("divisiList", baseService.selectDropDown("divisi_nm", "divisi_kd", "divisi", null, "divisi_nm"));
		
		uiModel.addAttribute("groupbiayaList", baseService.selectDropDown("nm_group", "kd_group", "group_biaya", null, "nm_group"));

		
		if (hakapproveManager.selectCountTable("subdivisi", "divisi_kd = '" + hakapprove.divisi_kd + "'")>0)
			uiModel.addAttribute("subdivList", baseService.selectDropDown("subdiv_nm", "concat(divisi_kd, '.', subdiv_kd)", "subdivisi", "divisi_kd = '" + hakapprove.divisi_kd + "'", "subdiv_nm"));
		else
			uiModel.addAttribute("subdivList", baseService.selectDropDown("subdiv_nm", "concat(divisi_kd, '.', subdiv_kd)", "subdivisi", null, "subdiv_nm"));

		if (hakapproveManager.selectCountTable("departmen", " divisi_kd = '" + hakapprove.divisi_kd + "' and subdiv_kd = '" + hakapprove.subdiv_kd + "'")>0)
			uiModel.addAttribute("deptList", baseService.selectDropDown("dept_nm","concat(divisi_kd, '.', subdiv_kd, '.', dept_kd)", "departmen", " divisi_kd = '" + hakapprove.divisi_kd + "' and subdiv_kd = '" + hakapprove.subdiv_kd + "'", "dept_nm"));
		else
			uiModel.addAttribute("deptList", baseService.selectDropDown("dept_nm","concat(divisi_kd, '.', subdiv_kd, '.', dept_kd)",  "departmen", null, "dept_nm"));

		if (hakapproveManager.selectCountTable("lokasi", " divisi_kd = '" + hakapprove.divisi_kd + "' and subdiv_kd = '" + hakapprove.subdiv_kd + "' and dept_kd = '" + hakapprove.dept_kd + "'")>0)
			uiModel.addAttribute("lokList", baseService.selectDropDown("lok_nm","concat(divisi_kd, '.', subdiv_kd, '.', dept_kd, '.', lok_kd)", "lokasi", " divisi_kd = '" + hakapprove.divisi_kd + "' and subdiv_kd = '" + hakapprove.subdiv_kd + "' and dept_kd = '" + hakapprove.dept_kd + "'", "lok_nm"));
		else
			uiModel.addAttribute("lokList", baseService.selectDropDown("lok_nm","concat(divisi_kd, '.', subdiv_kd, '.', dept_kd, '.', lok_kd)",  "lokasi", null, "lok_nm"));
		
		if (hakapproveManager.selectCountTable("detail_biaya", "kd_group ='" + hakapprove.kd_group + "'") > 0)
			uiModel.addAttribute("detailbiayaList", baseService.selectDropDown("kd_biaya", "kd_biaya", "detail_biaya", "kd_group = '" + hakapprove.kd_group + "' and f_used <> 1", "kd_biaya"));
		else
			uiModel.addAttribute("detailbiayaList", baseService.selectDropDown("kd_biaya", "kd_biaya", "detail_biaya", null, "kd_biaya"));
		
	}
	
	@RequestMapping(value = "/aktifnonaktif", method = RequestMethod.POST, produces = "text/html")
	public String nonaktif(@RequestParam(value = "tgl", required = true) String tgl,@RequestParam(value = "user_id", required = true) String user_id, @RequestParam(value = "divisi_kd", required = true) String divisi_kd, @RequestParam(value = "subdiv_kd", required = true) String subdiv_kd,@RequestParam(value = "dept_kd", required = true) String dept_kd, @RequestParam(value = "kd_group", required = true) String kd_group, @RequestParam(value = "kd_biaya", required = true) String kd_biaya, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size,
			Model uiModel, HttpServletRequest request) {
		HakApprove hakapprove = hakapproveManager.get(user_id, divisi_kd, subdiv_kd, dept_kd, kd_group, kd_biaya);
		
		if(hakapprove.getF_aktif()==1){
			hakapprove.setF_aktif(0);
			hakapprove.setTgl_nonaktif(hakapproveManager.selectSysdate());
			hakapprove.setUser_nonaktif(CommonUtil.getCurrentUserId());
			hakapprove.setSptgl(Utils.convertStringToDate(tgl, "dd/MM/yyyy"));
		}else{
			hakapprove.setF_aktif(1);
			hakapprove.setTgl_nonaktif(null);
			hakapprove.setJam_nonaktif(null);
			hakapprove.setUser_nonaktif(null);
			hakapprove.setDrtgl(Utils.convertStringToDate(tgl, "dd/MM/yyyy"));
			hakapprove.setSptgl(null);
		}
		
		
		hakapproveManager.save(hakapprove);
		
		if(hakapprove.getF_aktif()==1){	
			hakapproveManager.audittrail(Audittrail.Activity.TRANS, Audittrail.TransType.AKTIF, hakapprove.getClass().getSimpleName(), hakapprove.getItemId(), CommonUtil.getIpAddr(request), "AKTIFKAN HAK APPROVE",CommonUtil.getCurrentUser(), null);
		}else{
			hakapproveManager.audittrail(Audittrail.Activity.TRANS, Audittrail.TransType.NONAKTIF, hakapprove.getClass().getSimpleName(), hakapprove.getItemId(), CommonUtil.getIpAddr(request), "NON AKTIFKAN HAK APPROVE",CommonUtil.getCurrentUser(), null);
		}
		uiModel.asMap().clear();
		uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
		uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
		uiModel.addAttribute("pesan", messageSource.getMessage("entity_success", new String[]{hakapprove.getIsActive()+" Hak Approve "+hakapprove.kd_biaya}, LocaleContextHolder.getLocale()));
		
		return "redirect:/master/hakapprove";
	}
}
