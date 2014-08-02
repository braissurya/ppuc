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

import com.melawai.ppuc.model.DetailBiaya;
import com.melawai.ppuc.services.DetailBiayaManager;
import com.melawai.ppuc.web.validator.DetailBiayaValidator;

@RequestMapping("/master/detailbiaya")
@Controller
public class DetailBiayaController extends ParentController{

	protected static Logger logger = Logger.getLogger(DetailBiayaController.class);

	@Autowired
	private DetailBiayaManager detailbiayaManager;

	@Autowired
	private DetailBiayaValidator detailBiayaValidator;
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(this.detailBiayaValidator);
	}
	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
	public String create(@ModelAttribute("detailbiaya") @Valid DetailBiaya detailbiaya, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if(detailbiayaManager.selectCountTable("detail_biaya", "kd_biaya = '"+detailbiaya.kd_biaya+"'")>0){
			bindingResult.rejectValue("kd_biaya", "duplicate", new String[]{"Kode Biaya"}, null);
		}
		// tambahan validasi khusus
		if (detailbiayaManager.exist(detailbiaya.kd_group, detailbiaya.kd_biaya)) {
			bindingResult.rejectValue("kd_biaya", "duplicate", new String[] { "KD Group: " + detailbiaya.kd_group + " | KD Biaya : " + detailbiaya.kd_biaya+ ", " }, null);
		}
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, detailbiaya);
			return "detailbiaya/create";
		}
		uiModel.asMap().clear();
		detailbiayaManager.save(detailbiaya);
		return "redirect:/master/detailbiaya/" + encodeUrlPathSegment(detailbiaya.getKd_biaya().toString(), httpServletRequest);
	}

	@RequestMapping(params = "form", produces = "text/html")
	public String createForm(Model uiModel) {
		DetailBiaya detailBiaya=new DetailBiaya();
		detailBiaya.f_putus=1;
		populateEditForm(uiModel,detailBiaya );
		return "detailbiaya/create";
	}

	@RequestMapping(value = "/{kd_biaya}", produces = "text/html")
	public String show(@PathVariable("kd_biaya") String kd_biaya, Model uiModel) {
		populateEditForm(uiModel,  detailbiayaManager.get(kd_biaya));
		uiModel.addAttribute("itemId", kd_biaya);
		return "detailbiaya/show";
	}

	@RequestMapping(produces = "text/html")
	public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size,@RequestParam(value = "search", required = false) String search, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
		if (page == null) {
			page=1;
		}

			int sizeNo = size == null ? 10 : size.intValue();
			final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
			uiModel.addAttribute("detailbiayaList",detailbiayaManager.selectPagingList(search,sortFieldName,sortOrder, firstResult, sizeNo) );
			float nrOfPages = (float) detailbiayaManager.selectPagingCount(search) / sizeNo;
			uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
		addDateTimeFormatPatterns(uiModel);
		return "detailbiaya/list";
	}

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
	public String update(@ModelAttribute("detailbiaya") @Valid DetailBiaya detailbiaya, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, detailbiaya);
			return "detailbiaya/update";
		}
		uiModel.asMap().clear();
		detailbiayaManager.save(detailbiaya);
		return "redirect:/master/detailbiaya/" + encodeUrlPathSegment(detailbiaya.getKd_biaya().toString(), httpServletRequest);
	}

	@RequestMapping(value = "/{kd_biaya}", params = "form", produces = "text/html")
	public String updateForm(@PathVariable("kd_biaya") String kd_biaya, Model uiModel) {
		populateEditForm(uiModel, detailbiayaManager.get(kd_biaya));
		return "detailbiaya/update";
	}

	@RequestMapping(value = "/{kd_biaya}", method = RequestMethod.DELETE, produces = "text/html")
	public String delete(@PathVariable("kd_biaya") String kd_biaya, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
		String pesan=messageSource.getMessage("entity_success", new String[]{"Delete Detail Biaya : "+kd_biaya+","}, LocaleContextHolder.getLocale());
		if(!detailbiayaManager.exists(kd_biaya)){
			pesan="Detail Biaya "+kd_biaya+" tidak ditemukan";
			messageSource.getMessage("entity_not_found", new String[]{"Detail Biaya : "+kd_biaya+","}, LocaleContextHolder.getLocale());
		}else{
			detailbiayaManager.remove(kd_biaya);
		}
		
		uiModel.asMap().clear();
		uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
		uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
		uiModel.addAttribute("pesan", pesan);
		return "redirect:/master/detailbiaya";
	}
	void addDateTimeFormatPatterns(Model uiModel) {
		uiModel.addAttribute("detailbiaya_tgl_create_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
	}
	void populateEditForm(Model uiModel, DetailBiaya detailbiaya) {
		uiModel.addAttribute("detailbiaya", detailbiaya);
		uiModel.addAttribute("groupBiayaList", baseService.selectDropDown("nm_group", "kd_group", "group_biaya", null, "nm_group"));
		addDateTimeFormatPatterns(uiModel);
	}
}
