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

import com.melawai.ppuc.services.DepartmenManager;
import com.melawai.ppuc.web.controller.ParentController;
import com.melawai.ppuc.model.Departmen;
import com.melawai.ppuc.web.validator.DepartmenValidator;

@RequestMapping("/master/departmen")
@Controller
public class DepartmenController extends ParentController{

	protected static Logger logger = Logger.getLogger(DepartmenController.class);

	@Autowired
	private DepartmenManager departmenManager;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new DepartmenValidator());
	}
	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
	public String create(@Valid Departmen departmen, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, departmen);
			return "departmen/create";
		}
		uiModel.asMap().clear();
		departmenManager.save(departmen);
		return "redirect:/master/departmen/" + encodeUrlPathSegment(departmen.getDept_kd().toString(), httpServletRequest);
	}

	@RequestMapping(params = "form", produces = "text/html")
	public String createForm(Model uiModel) {
		populateEditForm(uiModel, new Departmen());
		return "departmen/create";
	}

	@RequestMapping(value = "/{dept_kd}", produces = "text/html")
	public String show(@PathVariable("dept_kd") String dept_kd, Model uiModel) {
		addDateTimeFormatPatterns(uiModel);
		uiModel.addAttribute("departmen", departmenManager.get(dept_kd));
		uiModel.addAttribute("itemId", dept_kd);
		return "departmen/show";
	}

	@RequestMapping(produces = "text/html")
	public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size,@RequestParam(value = "search", required = false) String search, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
		if (page == null) {
			page=1;
		}

			int sizeNo = size == null ? 10 : size.intValue();
			final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
			uiModel.addAttribute("departmenList",departmenManager.selectPagingList(search,sortFieldName,sortOrder, firstResult, sizeNo) );
			float nrOfPages = (float) departmenManager.selectPagingCount(search) / sizeNo;
			uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
		addDateTimeFormatPatterns(uiModel);
		return "departmen/list";
	}

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
	public String update(@Valid Departmen departmen, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, departmen);
			return "departmen/update";
		}
		uiModel.asMap().clear();
		departmenManager.save(departmen);
		return "redirect:/master/departmen/" + encodeUrlPathSegment(departmen.getDept_kd().toString(), httpServletRequest);
	}

	@RequestMapping(value = "/{dept_kd}", params = "form", produces = "text/html")
	public String updateForm(@PathVariable("dept_kd") String dept_kd, Model uiModel) {
		populateEditForm(uiModel, departmenManager.get(dept_kd));
		return "departmen/update";
	}

	@RequestMapping(value = "/{dept_kd}", method = RequestMethod.DELETE, produces = "text/html")
	public String delete(@PathVariable("dept_kd") String dept_kd, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
		Departmen departmen = departmenManager.get(dept_kd);
		departmenManager.remove(dept_kd);
		uiModel.asMap().clear();
		uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
		uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
		return "redirect:/master/departmen";
	}
	void addDateTimeFormatPatterns(Model uiModel) {
		uiModel.addAttribute("departmen_sys_tgl_update_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
		uiModel.addAttribute("departmen_sys_tgl_create_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
	}
	void populateEditForm(Model uiModel, Departmen departmen) {
		uiModel.addAttribute("departmen", departmen);
		addDateTimeFormatPatterns(uiModel);
	}
}
