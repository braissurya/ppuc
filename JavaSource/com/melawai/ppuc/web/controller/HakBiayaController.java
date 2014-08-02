package com.melawai.ppuc.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.melawai.ppuc.model.Audittrail;
import com.melawai.ppuc.model.DropDown;
import com.melawai.ppuc.model.HakBiaya;
import com.melawai.ppuc.model.User;
import com.melawai.ppuc.services.HakBiayaManager;
import com.melawai.ppuc.utils.CommonUtil;
import com.melawai.ppuc.utils.Utils;
import com.melawai.ppuc.web.validator.HakBiayaValidator;

@RequestMapping("/master/hakbiaya")
@Controller
public class HakBiayaController extends ParentController {

	protected static Logger logger = Logger.getLogger(HakBiayaController.class);

	@Autowired
	private HakBiayaManager hakbiayaManager;

	@Autowired
	private HakBiayaValidator hakBiayaValidator;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(this.hakBiayaValidator);
	}

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
	public String create(@ModelAttribute("hakbiaya") @Valid HakBiaya hakbiaya, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		// tambahan validasi khusus
		if (hakbiayaManager.exists(hakbiaya.divisi_kd, hakbiaya.subdiv_kd, hakbiaya.dept_kd, hakbiaya.lok_kd, hakbiaya.kd_group, hakbiaya.kd_biaya)) {
			bindingResult.rejectValue("kd_biaya", "duplicate", new String[] { "LOKASI KD : " + hakbiaya.lok_kd + " | DIVISI KD : " + hakbiaya.divisi_kd + " | SUBDIVISI KD : " + hakbiaya.subdiv_kd + " | DEPARTMEN KD : " + hakbiaya.dept_kd + " | KD HAKBIAYA : " + hakbiaya.kd_biaya + ", " }, null);
		}

		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, hakbiaya);
			return "hakbiaya/create";
		}
		uiModel.asMap().clear();
		hakbiayaManager.save(hakbiaya);
		return "redirect:/master/hakbiaya/" + encodeUrlPathSegment(hakbiaya.getId().toString(), httpServletRequest)/* encodeUrlPathSegment(hakbiaya.getDivisi_kd().toString(), httpServletRequest) + "/" + encodeUrlPathSegment(hakbiaya.getSubdiv_kd().toString(), httpServletRequest) + "/" + encodeUrlPathSegment(hakbiaya.getDept_kd().toString(), httpServletRequest) + "/"
				+ encodeUrlPathSegment(hakbiaya.getLok_kd().toString(), httpServletRequest) + "/" + encodeUrlPathSegment(hakbiaya.getKd_group().toString(), httpServletRequest) + "/" + encodeUrlPathSegment(hakbiaya.getKd_biaya().toString(), httpServletRequest)*/;
	}

	@RequestMapping(params = "form", produces = "text/html")
	public String createForm(Model uiModel) {
		HakBiaya hakbiaya = new HakBiaya();
		hakbiaya.f_aktif = 1;
		populateEditForm(uiModel, hakbiaya);
		return "hakbiaya/create";
	}

	@RequestMapping(value = "/group", method = RequestMethod.POST, produces = "text/html")
	public String creategroup(@ModelAttribute("hakbiaya") @Valid HakBiaya hakbiaya, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		

		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, hakbiaya.gl, hakbiaya.lk, hakbiaya.pr, hakbiaya.kt, hakbiaya.gb, hakbiaya.kb, hakbiaya.aktif);
			uiModel.addAttribute("errorMessages",Utils.errorListToString(Utils.errorBinderToList(bindingResult, messageSource)));
			return "hakbiaya/create_group";
		}
		uiModel.asMap().clear();
		hakbiayaManager.save(hakbiaya.hakBiayaList);
		String pesan=messageSource.getMessage("entity_success", new String[]{"Create Group Hakbiaya"}, LocaleContextHolder.getLocale());
		uiModel.addAttribute("pesan",pesan);
		return "redirect:/master/hakbiaya";
	}

	@RequestMapping(value = "/group", params = "form", produces = "text/html")
	public String creategroupForm(Model uiModel) {
		HakBiaya hakbiaya = new HakBiaya();
		hakbiaya.f_aktif = 1;
		hakbiaya.typeInput=1;
		uiModel.addAttribute("hakbiaya", hakbiaya);
		populateEditForm(uiModel, hakbiaya.gl, hakbiaya.lk, hakbiaya.pr, hakbiaya.kt, hakbiaya.gb, hakbiaya.kb, hakbiaya.aktif);
		return "hakbiaya/create_group";
	}

	@RequestMapping(value = "/{id}"/*"/{divisi_kd}/{subdiv_kd}/{dept_kd}/{lok_kd}/{kd_group}/{kd_biaya}"*/, produces = "text/html")
	public String show(@PathVariable("id") Long id,/*@PathVariable("divisi_kd") String divisi_kd, @PathVariable("subdiv_kd") String subdiv_kd, @PathVariable("dept_kd") String dept_kd, @PathVariable("lok_kd") String lok_kd, @PathVariable("kd_group") String kd_group, @PathVariable("kd_biaya") String kd_biaya,*/ Model uiModel) {
		addDateTimeFormatPatterns(uiModel);
		HakBiaya hakBiaya=hakbiayaManager.get(id/*divisi_kd, subdiv_kd, dept_kd, lok_kd, kd_group, kd_biaya*/);
		uiModel.addAttribute("hakbiaya", hakBiaya);
		uiModel.addAttribute("itemId", id/*divisi_kd + "/" + subdiv_kd + "/" + dept_kd + "/" + lok_kd + "/" + kd_group + "/" + kd_biaya*/);

		uiModel.addAttribute("divisiList", baseService.selectDropDown("divisi_nm", "divisi_kd", "divisi", null, "divisi_nm"));

		if (hakbiayaManager.selectCountTable("subdivisi", "divisi_kd = '" + hakBiaya.divisi_kd + "'") > 0)
			uiModel.addAttribute("subdivList", baseService.selectDropDown("subdiv_nm", "concat(divisi_kd, '.', subdiv_kd)", "subdivisi", "divisi_kd = '" + hakBiaya.divisi_kd + "'", "subdiv_nm"));
		else
			uiModel.addAttribute("subdivList", baseService.selectDropDown("subdiv_nm", "concat(divisi_kd, '.', subdiv_kd)", "subdivisi", null, "subdiv_nm"));

		if (hakbiayaManager.selectCountTable("departmen", " divisi_kd = '" + hakBiaya.divisi_kd + "' and subdiv_kd = '" + hakBiaya.subdiv_kd + "'") > 0)
			uiModel.addAttribute("deptList", baseService.selectDropDown("dept_nm", "concat(divisi_kd, '.', subdiv_kd, '.', dept_kd)", "departmen", " divisi_kd = '" + hakBiaya.divisi_kd + "' and subdiv_kd = '" + hakBiaya.subdiv_kd + "'", "dept_nm"));
		else
			uiModel.addAttribute("deptList", baseService.selectDropDown("dept_nm", "concat(divisi_kd, '.', subdiv_kd, '.', dept_kd)", "departmen", null, "dept_nm"));

		if (hakbiayaManager.selectCountTable("lokasi", " divisi_kd = '" + hakBiaya.divisi_kd + "' and subdiv_kd = '" + hakBiaya.subdiv_kd + "' and dept_kd = '" + hakBiaya.dept_kd + "'") > 0)
			uiModel.addAttribute("lokList", baseService.selectDropDown("lok_nm", "concat(divisi_kd, '.', subdiv_kd, '.', dept_kd, '.', lok_kd)", "lokasi", " divisi_kd = '" + hakBiaya.divisi_kd + "' and subdiv_kd = '" + hakBiaya.subdiv_kd + "' and dept_kd = '" + hakBiaya.dept_kd + "'", "lok_nm"));
		else
			uiModel.addAttribute("lokList", baseService.selectDropDown("lok_nm", "concat(divisi_kd, '.', subdiv_kd, '.', dept_kd, '.', lok_kd)", "lokasi", null, "lok_nm"));

		return "hakbiaya/show";
	}

	@RequestMapping(produces = "text/html")
	public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "search", required = false) String search, @RequestParam(value = "sortFieldName", required = false) String sortFieldName,
			@RequestParam(value = "sortOrder", required = false) String sortOrder,@RequestParam(value = "gl", required = false) String gl,@RequestParam(value = "lk", required = false) String lk,@RequestParam(value = "pr", required = false) String pr, 
			@RequestParam(value = "kt", required = false) String kt,@RequestParam(value = "gb", required = false) String gb,@RequestParam(value = "kb", required = false) String kb,@RequestParam(value = "aktif", required = false) Integer aktif,Model uiModel) {
		if (page == null) page = 1;
		
		if (Utils.isEmpty(gl))	gl = null;
		else gl = gl.substring(gl.lastIndexOf(".") + 1);
		
		if (Utils.isEmpty(pr)) 	pr = null;
		else pr = pr.substring(pr.lastIndexOf(".") + 1);
		
		if (Utils.isEmpty(kt)) kt = null;
		else kt = kt.substring(kt.lastIndexOf(".") + 1);
		
		if (Utils.isEmpty(lk)) lk = null;
		else lk = lk.substring(lk.lastIndexOf(".") + 1);
		
		if (Utils.isEmpty(gb))	gb = null;
		else gb = gb.substring(gb.lastIndexOf(".") + 1);
		
		if (Utils.isEmpty(kb)) kb = null;
		else kb = kb.substring(kb.lastIndexOf(".") + 1);
		
		int sizeNo = size == null ? 10 : size.intValue();
		final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
		uiModel.addAttribute("hakbiayaList", hakbiayaManager.selectPagingList(search, sortFieldName, sortOrder, firstResult, sizeNo, gl, pr, kt, lk, gb, kb, aktif));
		float nrOfPages = (float) hakbiayaManager.selectPagingCount(search, gl, pr, kt, lk, gb, kb, aktif) / sizeNo;
		
		uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
		
		populateEditForm(uiModel, gl, lk, pr, kt, gb, kb, aktif);
		return "hakbiaya/list";
	}

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
	public String update(@ModelAttribute("hakbiaya") @Valid HakBiaya hakbiaya, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, hakbiaya);
			return "hakbiaya/update";
		}
		uiModel.asMap().clear();
		hakbiayaManager.save(hakbiaya);
		return "redirect:/master/hakbiaya/" + encodeUrlPathSegment(hakbiaya.getId().toString(), httpServletRequest); /*encodeUrlPathSegment(hakbiaya.getDivisi_kd().toString(), httpServletRequest) + "/" + encodeUrlPathSegment(hakbiaya.getSubdiv_kd().toString(), httpServletRequest) + "/" + encodeUrlPathSegment(hakbiaya.getDept_kd().toString(), httpServletRequest) + "/"
				+ encodeUrlPathSegment(hakbiaya.getLok_kd().toString(), httpServletRequest) + "/" + encodeUrlPathSegment(hakbiaya.getKd_group().toString(), httpServletRequest) + "/" + encodeUrlPathSegment(hakbiaya.getKd_biaya().toString(), httpServletRequest);*/
	}

	@RequestMapping(value = "/{id}" /*"/{divisi_kd}/{subdiv_kd}/{dept_kd}/{lok_kd}/{kd_group}/{kd_biaya}"*/, params = "form", produces = "text/html")
	public String updateForm(@PathVariable("id") Long id,/*@PathVariable("divisi_kd") String divisi_kd, @PathVariable("subdiv_kd") String subdiv_kd, @PathVariable("dept_kd") String dept_kd, @PathVariable("lok_kd") String lok_kd, @PathVariable("kd_group") String kd_group, @PathVariable("kd_biaya") String kd_biaya, */Model uiModel) {
		populateEditForm(uiModel, hakbiayaManager.get(id/*divisi_kd, subdiv_kd, dept_kd, lok_kd, kd_group, kd_biaya*/));
		return "hakbiaya/update";
	}

	@RequestMapping(value = "/{id}" /*"/{divisi_kd}/{subdiv_kd}/{dept_kd}/{lok_kd}/{kd_group}/{kd_biaya}"*/, method = RequestMethod.DELETE, produces = "text/html")
	public String delete(@PathVariable("id") Long id,/*@PathVariable("divisi_kd") String divisi_kd, @PathVariable("subdiv_kd") String subdiv_kd, @PathVariable("dept_kd") String dept_kd, @PathVariable("lok_kd") String lok_kd, @PathVariable("kd_group") String kd_group, @PathVariable("kd_biaya") String kd_biaya,*/
			@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
		HakBiaya hakbiaya = hakbiayaManager.get(id/*divisi_kd, subdiv_kd, dept_kd, lok_kd, kd_group, kd_biaya*/);
		hakbiayaManager.remove(id/*divisi_kd, subdiv_kd, dept_kd, lok_kd, kd_group, kd_biaya*/);
		uiModel.asMap().clear();
		uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
		uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
		return "redirect:/master/hakbiaya";
	}

	void addDateTimeFormatPatterns(Model uiModel) {
		uiModel.addAttribute("hakbiaya_drtgl_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
		uiModel.addAttribute("hakbiaya_sptgl_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
		uiModel.addAttribute("hakbiaya_tgl_create_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
		uiModel.addAttribute("hakbiaya_tgl_nonaktif_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
	}

	void populateEditForm(Model uiModel, HakBiaya hakbiaya) {
		uiModel.addAttribute("hakbiaya", hakbiaya);
		addDateTimeFormatPatterns(uiModel);

		uiModel.addAttribute("divisiList", baseService.selectDropDown("divisi_nm", "divisi_kd", "divisi", null, "divisi_nm"));

		uiModel.addAttribute("groupbiayaList", baseService.selectDropDown("nm_group", "kd_group", "group_biaya", null, "nm_group"));

		if (hakbiayaManager.selectCountTable("detail_biaya", "kd_group ='" + hakbiaya.kd_group + "'") > 0)
			uiModel.addAttribute("detailbiayaList", baseService.selectDropDown("kd_biaya", "kd_biaya", "detail_biaya", "kd_group = '" + hakbiaya.kd_group + "'", "kd_biaya"));
		else
			uiModel.addAttribute("detailbiayaList", baseService.selectDropDown("kd_biaya", "kd_biaya", "detail_biaya", null, "kd_biaya"));

		if (hakbiayaManager.selectCountTable("subdivisi", "divisi_kd = '" + hakbiaya.divisi_kd + "'") > 0)
			uiModel.addAttribute("subdivList", baseService.selectDropDown("subdiv_nm", "concat(divisi_kd, '.', subdiv_kd)", "subdivisi", "divisi_kd = '" + hakbiaya.divisi_kd + "'", "subdiv_nm"));
		else
			uiModel.addAttribute("subdivList", baseService.selectDropDown("subdiv_nm", "concat(divisi_kd, '.', subdiv_kd)", "subdivisi", null, "subdiv_nm"));

		if (hakbiayaManager.selectCountTable("departmen", " divisi_kd = '" + hakbiaya.divisi_kd + "' and subdiv_kd = '" + hakbiaya.subdiv_kd + "'") > 0)
			uiModel.addAttribute("deptList", baseService.selectDropDown("dept_nm", "concat(divisi_kd, '.', subdiv_kd, '.', dept_kd)", "departmen", " divisi_kd = '" + hakbiaya.divisi_kd + "' and subdiv_kd = '" + hakbiaya.subdiv_kd + "'", "dept_nm"));
		else
			uiModel.addAttribute("deptList", baseService.selectDropDown("dept_nm", "concat(divisi_kd, '.', subdiv_kd, '.', dept_kd)", "departmen", null, "dept_nm"));

		if (hakbiayaManager.selectCountTable("lokasi", " divisi_kd = '" + hakbiaya.divisi_kd + "' and subdiv_kd = '" + hakbiaya.subdiv_kd + "' and dept_kd = '" + hakbiaya.dept_kd + "'") > 0)
			uiModel.addAttribute("lokList", baseService.selectDropDown("lok_nm", "concat(divisi_kd, '.', subdiv_kd, '.', dept_kd, '.', lok_kd)", "lokasi", " divisi_kd = '" + hakbiaya.divisi_kd + "' and subdiv_kd = '" + hakbiaya.subdiv_kd + "' and dept_kd = '" + hakbiaya.dept_kd + "'", "lok_nm"));
		else
			uiModel.addAttribute("lokList", baseService.selectDropDown("lok_nm", "concat(divisi_kd, '.', subdiv_kd, '.', dept_kd, '.', lok_kd)", "lokasi", null, "lok_nm"));

	}
	
	void populateEditForm(Model uiModel,  String gl,String lk, String pr, String kt,String gb,String kb,Integer aktif) {
		addDateTimeFormatPatterns(uiModel);
		
		uiModel.addAttribute("f_gl", baseService.selectDropDown("DISTINCT group_lok","group_lok",  "group_lokasi_h", "1=1 group by group_lok", "group_lok"));
		
		if (!Utils.isEmpty(gl))
			uiModel.addAttribute("f_pr", baseService.selectDropDown("DISTINCT concat( propinsi )", "propinsi", "lokasi  a left join group_lokasi_d b ON a.lok_kd = b.lok_kd ", " b.group_lok='" + gl + "' group by propinsi", "propinsi"));
		else
			uiModel.addAttribute("f_pr", baseService.selectDropDown("DISTINCT concat( propinsi )", "propinsi", "lokasi  a left join group_lokasi_d b ON a.lok_kd = b.lok_kd ", "1 = 1 group by propinsi", "propinsi"));

		if (!Utils.isEmpty(pr))
			uiModel.addAttribute("f_kt", baseService.selectDropDown("DISTINCT concat( a.kota )","kota",  "lokasi  a left join group_lokasi_d b ON a.lok_kd = b.lok_kd ", " propinsi='"+pr+"' group by kota", "kota"));
		else
			uiModel.addAttribute("f_kt", baseService.selectDropDown("DISTINCT concat( a.kota )","kota",  "lokasi  a left join group_lokasi_d b ON a.lok_kd = b.lok_kd ", "1=1 group by kota", "kota"));
		
		if(!Utils.isEmpty(kt))
			uiModel.addAttribute("f_lk", baseService.selectDropDown("DISTINCT concat(a.lok_kd)","lok_nm",  "lokasi a left join group_lokasi_d b ON a.lok_kd = b.lok_kd ", "kota = '"+kt+"' group by a.lok_nm", "a.lok_nm"));
		else
			uiModel.addAttribute("f_lk", baseService.selectDropDown("DISTINCT concat(a.lok_kd)","lok_nm",  "lokasi a left join group_lokasi_d b ON a.lok_kd = b.lok_kd ", "1 = 1 group by a.lok_nm", "a.lok_nm"));
		
		uiModel.addAttribute("f_gb", baseService.selectDropDown("DISTINCT nm_group","kd_group",  "group_biaya", "1=1 group by nm_group", "nm_group"));
		
		if(!Utils.isEmpty(gb))
			uiModel.addAttribute("f_kb", baseService.selectDropDown("DISTINCT kd_biaya","kd_biaya",  "detail_biaya", "kd_group = '"+gb+"' group by kd_biaya", "kd_biaya"));
		else
			uiModel.addAttribute("f_kb", baseService.selectDropDown("DISTINCT kd_biaya","kd_biaya",  "detail_biaya", "1=1 group by kd_biaya", "kd_biaya"));
		
		uiModel.addAttribute("gl", gl);
		uiModel.addAttribute("lk", lk);
		uiModel.addAttribute("pr", pr);
		uiModel.addAttribute("kt", kt);
		uiModel.addAttribute("gb", gb);
		uiModel.addAttribute("kb", kb);
		uiModel.addAttribute("aktif", aktif);
	}
	
	@RequestMapping(value = "/aktifnonaktif", method = RequestMethod.POST, produces = "text/html")
	public String nonaktif(@RequestParam(value = "id", required = true) String id,@RequestParam(value = "tgl", required = true) String tgl,@RequestParam(value = "faktif", required = true) String faktif, 
			@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size,
			@RequestParam(value = "gl", required = false) String gl,@RequestParam(value = "lk", required = false) String lk,@RequestParam(value = "pr", required = false) String pr, 
			@RequestParam(value = "kt", required = false) String kt,@RequestParam(value = "gb", required = false) String gb,@RequestParam(value = "kb", required = false) String kb,@RequestParam(value = "aktif", required = false) Integer aktif,
			Model uiModel, HttpServletRequest request) {
		String [] ids=id.split("#");
		String pesan="";
		List<String> errorMessages=new ArrayList<String>();
		for(String idSplit:ids){
			HakBiaya hakBiaya=hakbiayaManager.get(new Long(idSplit));
			if(hakBiaya.getF_aktif()==1){
				hakBiaya.setF_aktif(0);
				hakBiaya.setTgl_nonaktif(hakbiayaManager.selectSysdate());
				hakBiaya.setUser_nonaktif(CommonUtil.getCurrentUserId());
				hakBiaya.setSptgl(Utils.convertStringToDate(tgl, "dd/MM/yyyy"));
				if(hakBiaya.getSptgl().compareTo(hakBiaya.getTgl_nonaktif())<=0){
					errorMessages.add(messageSource.getMessage("future", new String[]{"Tanggal Berakhir [ID = "+idSplit+"]"}, LocaleContextHolder.getLocale()));
				}
			}else{
				hakBiaya.setF_aktif(1);
				hakBiaya.setTgl_nonaktif(null);
				hakBiaya.setUser_nonaktif(null);
				hakBiaya.setDrtgl(Utils.convertStringToDate(tgl, "dd/MM/yyyy"));
				hakBiaya.setSptgl(null);
				Date lastSpt=hakbiayaManager.getLastSpTgl(hakBiaya.divisi_kd, hakBiaya.subdiv_kd, hakBiaya.dept_kd, hakBiaya.lok_kd, hakBiaya.kd_group, hakBiaya.kd_biaya);
				if(lastSpt!=null)
				if(hakBiaya.getDrtgl().compareTo(lastSpt)<=0){
					errorMessages.add(messageSource.getMessage("compare_date", new String[]{"Tanggal Berlaku Baru [ID = "+idSplit+"]","Tanggal Berakhir Sebelumnya ["+Utils.convertDateToString(lastSpt, "dd/MM/yyyy")+"]"}, LocaleContextHolder.getLocale()));
				}
			}
			
			if(errorMessages.isEmpty()){
				hakbiayaManager.save(hakBiaya);
				
				if(hakBiaya.getF_aktif()==1){	
					hakbiayaManager.audittrail(Audittrail.Activity.TRANS, Audittrail.TransType.AKTIF, hakBiaya.getClass().getSimpleName(), hakBiaya.getItemId(), CommonUtil.getIpAddr(request), "AKTIFKAN HAK BIAYA",CommonUtil.getCurrentUser(), null);
				}else{
					hakbiayaManager.audittrail(Audittrail.Activity.TRANS, Audittrail.TransType.NONAKTIF, hakBiaya.getClass().getSimpleName(), hakBiaya.getItemId(), CommonUtil.getIpAddr(request), "NON AKTIFKAN HAK BIAYA",CommonUtil.getCurrentUser(), null);
				}
				
				pesan=messageSource.getMessage("entity_success", new String[]{faktif+" Hakbiaya "}, LocaleContextHolder.getLocale());
			}
		}
		
		uiModel.asMap().clear();
		uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
		uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
		uiModel.addAttribute("gl", gl);
		uiModel.addAttribute("lk", lk);
		uiModel.addAttribute("pr", pr);
		uiModel.addAttribute("kt", kt);
		uiModel.addAttribute("gb", gb);
		uiModel.addAttribute("kb", kb);
		uiModel.addAttribute("aktif", aktif);
		
		if(errorMessages.isEmpty()){
			uiModel.addAttribute("pesan", pesan);
		}else{
			uiModel.addAttribute("errorMessages",Utils.errorListToString(errorMessages));
		}
		
		return "redirect:/master/hakbiaya";
	}
}
