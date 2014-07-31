package com.melawai.ppuc.web.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;

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
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

import com.melawai.ppuc.services.HakBiayaHistManager;
import com.melawai.ppuc.web.controller.ParentController;
import com.melawai.ppuc.model.HakBiayaHist;
import com.melawai.ppuc.web.validator.HakBiayaHistValidator;

@RequestMapping("/master/hakbiayahist")
@Controller
public class HakBiayaHistController extends ParentController{

	protected static Logger logger = Logger.getLogger(HakBiayaHistController.class);

	@Autowired
	private HakBiayaHistManager hakbiayahistManager;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new HakBiayaHistValidator());
	}
	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
	public String create(@Valid HakBiayaHist hakbiayahist, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, hakbiayahist);
			return "hakbiayahist/create";
		}
		uiModel.asMap().clear();
		hakbiayahistManager.save(hakbiayahist);
		return "redirect:/master/hakbiayahist/" + encodeUrlPathSegment(hakbiayahist.getId().toString(), httpServletRequest)/*encodeUrlPathSegment(hakbiayahist.getDivisi_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(hakbiayahist.getSubdiv_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(hakbiayahist.getDept_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(hakbiayahist.getLok_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(hakbiayahist.getKd_group().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(hakbiayahist.getKd_biaya().toString(), httpServletRequest)*/;
	}

	@RequestMapping(params = "form", produces = "text/html")
	public String createForm(Model uiModel) {
		populateEditForm(uiModel, new HakBiayaHist());
		return "hakbiayahist/create";
	}

	@RequestMapping(value ="/{id}" /*"/{divisi_kd}/{subdiv_kd}/{dept_kd}/{lok_kd}/{kd_group}/{kd_biaya}"*/, produces = "text/html")
	public String show(@PathVariable("id") Long id,/*@PathVariable("divisi_kd") String divisi_kd, @PathVariable("subdiv_kd") String subdiv_kd, @PathVariable("dept_kd") String dept_kd, @PathVariable("lok_kd") String lok_kd, @PathVariable("kd_group") String kd_group, @PathVariable("kd_biaya") String kd_biaya,*/ Model uiModel) {
		addDateTimeFormatPatterns(uiModel);
		uiModel.addAttribute("hakbiayahist", hakbiayahistManager.get(id/*divisi_kd, subdiv_kd, dept_kd, lok_kd, kd_group, kd_biaya*/));
		uiModel.addAttribute("itemId",""+id /*divisi_kd+"/"+subdiv_kd+"/"+dept_kd+"/"+lok_kd+"/"+kd_group+"/"+kd_biaya*/);
		return "hakbiayahist/show";
	}

	@RequestMapping(produces = "text/html")
	public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size,@RequestParam(value = "search", required = false) String search, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
		if (page == null) {
			page=1;
		}

			int sizeNo = size == null ? 10 : size.intValue();
			final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
			uiModel.addAttribute("hakbiayahistList",hakbiayahistManager.selectPagingList(search,sortFieldName,sortOrder, firstResult, sizeNo) );
			float nrOfPages = (float) hakbiayahistManager.selectPagingCount(search) / sizeNo;
			uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
		addDateTimeFormatPatterns(uiModel);
		return "hakbiayahist/list";
	}

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
	public String update(@Valid HakBiayaHist hakbiayahist, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, hakbiayahist);
			return "hakbiayahist/update";
		}
		uiModel.asMap().clear();
		hakbiayahistManager.save(hakbiayahist);
		return "redirect:/master/hakbiayahist/" +  encodeUrlPathSegment(hakbiayahist.getId().toString(), httpServletRequest)/* encodeUrlPathSegment(hakbiayahist.getDivisi_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(hakbiayahist.getSubdiv_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(hakbiayahist.getDept_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(hakbiayahist.getLok_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(hakbiayahist.getKd_group().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(hakbiayahist.getKd_biaya().toString(), httpServletRequest)*/;
	}

	@RequestMapping(value = "/{id}"/*"/{divisi_kd}/{subdiv_kd}/{dept_kd}/{lok_kd}/{kd_group}/{kd_biaya}"*/, params = "form", produces = "text/html")
	public String updateForm(@PathVariable("id") Long id,/*@PathVariable("divisi_kd") String divisi_kd, @PathVariable("subdiv_kd") String subdiv_kd, @PathVariable("dept_kd") String dept_kd, @PathVariable("lok_kd") String lok_kd, @PathVariable("kd_group") String kd_group, @PathVariable("kd_biaya") String kd_biaya,*/ Model uiModel) {
		populateEditForm(uiModel, hakbiayahistManager.get(id/*divisi_kd, subdiv_kd, dept_kd, lok_kd, kd_group, kd_biaya*/));
		return "hakbiayahist/update";
	}

	@RequestMapping(value = "/{id}" /*"/{divisi_kd}/{subdiv_kd}/{dept_kd}/{lok_kd}/{kd_group}/{kd_biaya}"*/, method = RequestMethod.DELETE, produces = "text/html")
	public String delete(@PathVariable("id") Long id,/*@PathVariable("divisi_kd") String divisi_kd, @PathVariable("subdiv_kd") String subdiv_kd, @PathVariable("dept_kd") String dept_kd, @PathVariable("lok_kd") String lok_kd, @PathVariable("kd_group") String kd_group, @PathVariable("kd_biaya") String kd_biaya,*/ @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
		HakBiayaHist hakbiayahist = hakbiayahistManager.get(id/*divisi_kd, subdiv_kd, dept_kd, lok_kd, kd_group, kd_biaya*/);
		hakbiayahistManager.remove(id/*divisi_kd, subdiv_kd, dept_kd, lok_kd, kd_group, kd_biaya*/);
		uiModel.asMap().clear();
		uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
		uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
		return "redirect:/master/hakbiayahist";
	}
	void addDateTimeFormatPatterns(Model uiModel) {
		uiModel.addAttribute("hakbiayahist_drtgl_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
		uiModel.addAttribute("hakbiayahist_sptgl_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
		uiModel.addAttribute("hakbiayahist_tgl_create_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
		uiModel.addAttribute("hakbiayahist_tgl_create_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
	}
	void populateEditForm(Model uiModel, HakBiayaHist hakbiayahist) {
		uiModel.addAttribute("hakbiayahist", hakbiayahist);
		addDateTimeFormatPatterns(uiModel);
	}
}
