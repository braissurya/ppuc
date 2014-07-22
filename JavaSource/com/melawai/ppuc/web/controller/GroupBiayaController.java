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

import com.melawai.ppuc.model.GroupBiaya;
import com.melawai.ppuc.services.GroupBiayaManager;
import com.melawai.ppuc.web.validator.GroupBiayaValidator;

@RequestMapping("/master/groupbiaya")
@Controller
public class GroupBiayaController extends ParentController{

	protected static Logger logger = Logger.getLogger(GroupBiayaController.class);

	@Autowired
	private GroupBiayaManager groupbiayaManager;

	@Autowired
	private GroupBiayaValidator groupBiayaValidator;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(this.groupBiayaValidator);
	}
	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
	public String create(@ModelAttribute("groupbiaya")@Valid GroupBiaya groupbiaya, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		// tambahan validasi khusus
		if (groupbiayaManager.exists(groupbiaya.kd_group)) {
			bindingResult.rejectValue("kd_group", "duplicate", new String[] { "Kd Group : " + groupbiaya.kd_group + ", " }, null);
		}
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, groupbiaya);
			return "groupbiaya/create";
		}
		uiModel.asMap().clear();
		groupbiayaManager.save(groupbiaya);
		return "redirect:/master/groupbiaya/" + encodeUrlPathSegment(groupbiaya.getKd_group().toString(), httpServletRequest);
	}

	@RequestMapping(params = "form", produces = "text/html")
	public String createForm(Model uiModel) {
		populateEditForm(uiModel, new GroupBiaya());
		return "groupbiaya/create";
	}

	@RequestMapping(value = "/{kd_group}", produces = "text/html")
	public String show(@PathVariable("kd_group") String kd_group, Model uiModel) {
		addDateTimeFormatPatterns(uiModel);
		uiModel.addAttribute("groupbiaya", groupbiayaManager.get(kd_group));
		uiModel.addAttribute("itemId", kd_group);
		return "groupbiaya/show";
	}

	@RequestMapping(produces = "text/html")
	public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size,@RequestParam(value = "search", required = false) String search, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
		if (page == null) {
			page=1;
		}

			int sizeNo = size == null ? 10 : size.intValue();
			final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
			uiModel.addAttribute("groupbiayaList",groupbiayaManager.selectPagingList(search,sortFieldName,sortOrder, firstResult, sizeNo) );
			float nrOfPages = (float) groupbiayaManager.selectPagingCount(search) / sizeNo;
			uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
		addDateTimeFormatPatterns(uiModel);
		return "groupbiaya/list";
	}

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
	public String update(@ModelAttribute("groupbiaya")@Valid GroupBiaya groupbiaya, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, groupbiaya);
			return "groupbiaya/update";
		}
		uiModel.asMap().clear();
		groupbiayaManager.save(groupbiaya);
		return "redirect:/master/groupbiaya/" + encodeUrlPathSegment(groupbiaya.getKd_group().toString(), httpServletRequest);
	}

	@RequestMapping(value = "/{kd_group}", params = "form", produces = "text/html")
	public String updateForm(@PathVariable("kd_group") String kd_group, Model uiModel) {
		populateEditForm(uiModel, groupbiayaManager.get(kd_group));
		return "groupbiaya/update";
	}

	@RequestMapping(value = "/{kd_group}", method = RequestMethod.DELETE, produces = "text/html")
	public String delete(@PathVariable("kd_group") String kd_group, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
		
		String pesan=messageSource.getMessage("entity_success", new String[]{"Delete Group Biaya : "+kd_group+","}, LocaleContextHolder.getLocale());
		if(!groupbiayaManager.exists(kd_group)){
			pesan="Group Biaya "+kd_group+" tidak ditemukan";
			messageSource.getMessage("entity_not_found", new String[]{"Group Biaya : "+kd_group+","}, LocaleContextHolder.getLocale());
		}else if(groupbiayaManager.selectCountTable("detail_biaya", "kd_group='"+kd_group+"'")>0){
			pesan=messageSource.getMessage("entity_used_by", new String[]{"Group Biaya : "+kd_group+",","Master Detail Biaya"}, LocaleContextHolder.getLocale());
		}else{
			groupbiayaManager.remove(kd_group);
		}
		uiModel.asMap().clear();
		uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
		uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
		uiModel.addAttribute("pesan", pesan);
		return "redirect:/master/groupbiaya";
	}
	void addDateTimeFormatPatterns(Model uiModel) {
		uiModel.addAttribute("groupbiaya_tgl_create_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
	}
	void populateEditForm(Model uiModel, GroupBiaya groupbiaya) {
		uiModel.addAttribute("groupbiaya", groupbiaya);
		addDateTimeFormatPatterns(uiModel);
	}
}
