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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.melawai.ppuc.model.HakBiaya;
import com.melawai.ppuc.services.HakBiayaManager;
import com.melawai.ppuc.web.validator.HakBiayaValidator;

@RequestMapping("/master/hakbiaya")
@Controller
public class HakBiayaController extends ParentController{

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
		if (hakbiayaManager.exists(hakbiaya.divisi_kd,hakbiaya.subdiv_kd, hakbiaya.dept_kd, hakbiaya.lok_kd, hakbiaya.kd_group, hakbiaya.kd_biaya)) {
			bindingResult.rejectValue("kd_biaya", "duplicate", new String[] { "LOKASI KD : " + hakbiaya.lok_kd + " | DIVISI KD : " + hakbiaya.divisi_kd + " | SUBDIVISI KD : " + hakbiaya.subdiv_kd
					+ " | DEPARTMEN KD : " + hakbiaya.dept_kd+ " | KD HAKBIAYA : " + hakbiaya.kd_biaya  + ", " }, null);
		}
		
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, hakbiaya);
			return "hakbiaya/create";
		}
		uiModel.asMap().clear();
		hakbiayaManager.save(hakbiaya);
		return "redirect:/master/hakbiaya/" + encodeUrlPathSegment(hakbiaya.getDivisi_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(hakbiaya.getSubdiv_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(hakbiaya.getDept_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(hakbiaya.getLok_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(hakbiaya.getKd_group().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(hakbiaya.getKd_biaya().toString(), httpServletRequest);
	}

	@RequestMapping(params = "form", produces = "text/html")
	public String createForm(Model uiModel) {
		HakBiaya hakbiaya=new HakBiaya();
		hakbiaya.f_aktif=1;
		populateEditForm(uiModel, hakbiaya);
		return "hakbiaya/create";
	}
	
	@RequestMapping(value="/group",method = RequestMethod.POST, produces = "text/html")
	public String creategroup(@ModelAttribute("hakbiaya") @Valid HakBiaya hakbiaya, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		// tambahan validasi khusus
		if (hakbiayaManager.exists(hakbiaya.divisi_kd,hakbiaya.subdiv_kd, hakbiaya.dept_kd, hakbiaya.lok_kd, hakbiaya.kd_group, hakbiaya.kd_biaya)) {
			bindingResult.rejectValue("kd_biaya", "duplicate", new String[] { "LOKASI KD : " + hakbiaya.lok_kd + " | DIVISI KD : " + hakbiaya.divisi_kd + " | SUBDIVISI KD : " + hakbiaya.subdiv_kd
					+ " | DEPARTMEN KD : " + hakbiaya.dept_kd+ " | KD HAKBIAYA : " + hakbiaya.kd_biaya  + ", " }, null);
		}
		
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, hakbiaya);
			return "hakbiaya/create";
		}
		uiModel.asMap().clear();
		hakbiayaManager.save(hakbiaya);
		return "redirect:/master/hakbiaya/" + encodeUrlPathSegment(hakbiaya.getDivisi_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(hakbiaya.getSubdiv_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(hakbiaya.getDept_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(hakbiaya.getLok_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(hakbiaya.getKd_group().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(hakbiaya.getKd_biaya().toString(), httpServletRequest);
	}

	@RequestMapping(value="/group",params = "form", produces = "text/html")
	public String creategroupForm(Model uiModel) {
		HakBiaya hakbiaya=new HakBiaya();
		hakbiaya.f_aktif=1;
		populateEditForm(uiModel, hakbiaya);
		return "hakbiaya/create";
	}

	@RequestMapping(value = "/{divisi_kd}/{subdiv_kd}/{dept_kd}/{lok_kd}/{kd_group}/{kd_biaya}", produces = "text/html")
	public String show(@PathVariable("divisi_kd") String divisi_kd, @PathVariable("subdiv_kd") String subdiv_kd, @PathVariable("dept_kd") String dept_kd, @PathVariable("lok_kd") String lok_kd, @PathVariable("kd_group") String kd_group, @PathVariable("kd_biaya") String kd_biaya, Model uiModel) {
		addDateTimeFormatPatterns(uiModel);
		uiModel.addAttribute("hakbiaya", hakbiayaManager.get(divisi_kd, subdiv_kd, dept_kd, lok_kd, kd_group, kd_biaya));
		uiModel.addAttribute("itemId", divisi_kd+"/"+subdiv_kd+"/"+dept_kd+"/"+lok_kd+"/"+kd_group+"/"+kd_biaya);
		
		uiModel.addAttribute("divisiList", baseService.selectDropDown("divisi_nm", "divisi_kd", "divisi", null, "divisi_nm"));
		
		if (hakbiayaManager.selectCountTable("subdivisi", "divisi_kd = '" + divisi_kd + "'")>0)
			uiModel.addAttribute("subdivList", baseService.selectDropDown("subdiv_nm", "concat(divisi_kd, '.', subdiv_kd)", "subdivisi", "divisi_kd = '" + divisi_kd + "'", "subdiv_nm"));
		else
			uiModel.addAttribute("subdivList", baseService.selectDropDown("subdiv_nm", "concat(divisi_kd, '.', subdiv_kd)", "subdivisi", null, "subdiv_nm"));

		if (hakbiayaManager.selectCountTable("departmen", " divisi_kd = '" + divisi_kd + "' and subdiv_kd = '" + subdiv_kd + "'")>0)
			uiModel.addAttribute("deptList", baseService.selectDropDown("dept_nm","concat(divisi_kd, '.', subdiv_kd, '.', dept_kd)", "departmen", " divisi_kd = '" + divisi_kd + "' and subdiv_kd = '" + subdiv_kd + "'", "dept_nm"));
		else
			uiModel.addAttribute("deptList", baseService.selectDropDown("dept_nm","concat(divisi_kd, '.', subdiv_kd, '.', dept_kd)",  "departmen", null, "dept_nm"));

		if (hakbiayaManager.selectCountTable("lokasi", " divisi_kd = '" + divisi_kd + "' and subdiv_kd = '" + subdiv_kd + "' and dept_kd = '" + dept_kd + "'")>0)
			uiModel.addAttribute("lokList", baseService.selectDropDown("lok_nm","concat(divisi_kd, '.', subdiv_kd, '.', dept_kd, '.', lok_kd)", "lokasi", " divisi_kd = '" + divisi_kd + "' and subdiv_kd = '" + subdiv_kd + "' and dept_kd = '" + dept_kd + "'", "lok_nm"));
		else
			uiModel.addAttribute("lokList", baseService.selectDropDown("lok_nm","concat(divisi_kd, '.', subdiv_kd, '.', dept_kd, '.', lok_kd)",  "lokasi", null, "lok_nm"));

		return "hakbiaya/show";
	}

	@RequestMapping(produces = "text/html")
	public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size,@RequestParam(value = "search", required = false) String search, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
		if (page == null) {
			page=1;
		}

			int sizeNo = size == null ? 10 : size.intValue();
			final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
			uiModel.addAttribute("hakbiayaList",hakbiayaManager.selectPagingList(search,sortFieldName,sortOrder, firstResult, sizeNo) );
			float nrOfPages = (float) hakbiayaManager.selectPagingCount(search) / sizeNo;
			uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
		addDateTimeFormatPatterns(uiModel);
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
		return "redirect:/master/hakbiaya/" + encodeUrlPathSegment(hakbiaya.getDivisi_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(hakbiaya.getSubdiv_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(hakbiaya.getDept_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(hakbiaya.getLok_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(hakbiaya.getKd_group().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(hakbiaya.getKd_biaya().toString(), httpServletRequest);
	}

	@RequestMapping(value = "/{divisi_kd}/{subdiv_kd}/{dept_kd}/{lok_kd}/{kd_group}/{kd_biaya}", params = "form", produces = "text/html")
	public String updateForm(@PathVariable("divisi_kd") String divisi_kd, @PathVariable("subdiv_kd") String subdiv_kd, @PathVariable("dept_kd") String dept_kd, @PathVariable("lok_kd") String lok_kd, @PathVariable("kd_group") String kd_group, @PathVariable("kd_biaya") String kd_biaya, Model uiModel) {
		populateEditForm(uiModel, hakbiayaManager.get(divisi_kd, subdiv_kd, dept_kd, lok_kd, kd_group, kd_biaya));
		return "hakbiaya/update";
	}

	@RequestMapping(value = "/{divisi_kd}/{subdiv_kd}/{dept_kd}/{lok_kd}/{kd_group}/{kd_biaya}", method = RequestMethod.DELETE, produces = "text/html")
	public String delete(@PathVariable("divisi_kd") String divisi_kd, @PathVariable("subdiv_kd") String subdiv_kd, @PathVariable("dept_kd") String dept_kd, @PathVariable("lok_kd") String lok_kd, @PathVariable("kd_group") String kd_group, @PathVariable("kd_biaya") String kd_biaya, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
		HakBiaya hakbiaya = hakbiayaManager.get(divisi_kd, subdiv_kd, dept_kd, lok_kd, kd_group, kd_biaya);
		hakbiayaManager.remove(divisi_kd, subdiv_kd, dept_kd, lok_kd, kd_group, kd_biaya);
		uiModel.asMap().clear();
		uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
		uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
		return "redirect:/master/hakbiaya";
	}
	void addDateTimeFormatPatterns(Model uiModel) {
		uiModel.addAttribute("hakbiaya_drtgl_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
		uiModel.addAttribute("hakbiaya_sptgl_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
		uiModel.addAttribute("hakbiaya_tgl_create_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
		uiModel.addAttribute("hakbiaya_tgl_create_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
	}
	void populateEditForm(Model uiModel, HakBiaya hakbiaya) {
		uiModel.addAttribute("hakbiaya", hakbiaya);
		addDateTimeFormatPatterns(uiModel);
		
		uiModel.addAttribute("divisiList", baseService.selectDropDown("divisi_nm", "divisi_kd", "divisi", null, "divisi_nm"));
		
		uiModel.addAttribute("groupbiayaList", baseService.selectDropDown("nm_group", "kd_group", "group_biaya", null, "nm_group"));
		
		if(hakbiayaManager.selectCountTable("detail_biaya", "kd_group ='"+hakbiaya.kd_group+"'")>0)
			uiModel.addAttribute("detailbiayaList", baseService.selectDropDown("kd_biaya", "kd_biaya", "detail_biaya", "kd_group = '" + hakbiaya.kd_group + "'", "kd_biaya"));
		else
			uiModel.addAttribute("detailbiayaList", baseService.selectDropDown("kd_biaya", "kd_biaya", "detail_biaya", null, "kd_biaya"));
		
		if (hakbiayaManager.selectCountTable("subdivisi", "divisi_kd = '" + hakbiaya.divisi_kd + "'")>0)
			uiModel.addAttribute("subdivList", baseService.selectDropDown("subdiv_nm", "concat(divisi_kd, '.', subdiv_kd)", "subdivisi", "divisi_kd = '" + hakbiaya.divisi_kd + "'", "subdiv_nm"));
		else
			uiModel.addAttribute("subdivList", baseService.selectDropDown("subdiv_nm", "concat(divisi_kd, '.', subdiv_kd)", "subdivisi", null, "subdiv_nm"));

		if (hakbiayaManager.selectCountTable("departmen", " divisi_kd = '" + hakbiaya.divisi_kd + "' and subdiv_kd = '" + hakbiaya.subdiv_kd + "'")>0)
			uiModel.addAttribute("deptList", baseService.selectDropDown("dept_nm","concat(divisi_kd, '.', subdiv_kd, '.', dept_kd)", "departmen", " divisi_kd = '" + hakbiaya.divisi_kd + "' and subdiv_kd = '" + hakbiaya.subdiv_kd + "'", "dept_nm"));
		else
			uiModel.addAttribute("deptList", baseService.selectDropDown("dept_nm","concat(divisi_kd, '.', subdiv_kd, '.', dept_kd)",  "departmen", null, "dept_nm"));

		if (hakbiayaManager.selectCountTable("lokasi", " divisi_kd = '" + hakbiaya.divisi_kd + "' and subdiv_kd = '" + hakbiaya.subdiv_kd + "' and dept_kd = '" + hakbiaya.dept_kd + "'")>0)
			uiModel.addAttribute("lokList", baseService.selectDropDown("lok_nm","concat(divisi_kd, '.', subdiv_kd, '.', dept_kd, '.', lok_kd)", "lokasi", " divisi_kd = '" + hakbiaya.divisi_kd + "' and subdiv_kd = '" + hakbiaya.subdiv_kd + "' and dept_kd = '" + hakbiaya.dept_kd + "'", "lok_nm"));
		else
			uiModel.addAttribute("lokList", baseService.selectDropDown("lok_nm","concat(divisi_kd, '.', subdiv_kd, '.', dept_kd, '.', lok_kd)",  "lokasi", null, "lok_nm"));

	}
}
