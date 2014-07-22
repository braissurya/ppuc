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

import com.melawai.ppuc.services.PropinsiManager;
import com.melawai.ppuc.web.controller.ParentController;
import com.melawai.ppuc.model.Propinsi;
import com.melawai.ppuc.web.validator.PropinsiValidator;

@RequestMapping("/master/propinsi")
@Controller
public class PropinsiController extends ParentController{

	protected static Logger logger = Logger.getLogger(PropinsiController.class);

	@Autowired
	private PropinsiManager propinsiManager;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new PropinsiValidator());
	}
	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
	public String create(@Valid Propinsi propinsi, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, propinsi);
			return "propinsi/create";
		}
		uiModel.asMap().clear();
		propinsiManager.save(propinsi);
		return "redirect:/master/propinsi/" + encodeUrlPathSegment(propinsi.getPropinsi().toString(), httpServletRequest);
	}

	@RequestMapping(params = "form", produces = "text/html")
	public String createForm(Model uiModel) {
		populateEditForm(uiModel, new Propinsi());
		return "propinsi/create";
	}

	@RequestMapping(value = "/{propinsi}", produces = "text/html")
	public String show(@PathVariable("propinsi") String propinsi, Model uiModel) {
		addDateTimeFormatPatterns(uiModel);
		uiModel.addAttribute("propinsi", propinsiManager.get(propinsi));
		uiModel.addAttribute("itemId", propinsi);
		return "propinsi/show";
	}

	@RequestMapping(produces = "text/html")
	public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size,@RequestParam(value = "search", required = false) String search, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
		if (page == null) {
			page=1;
		}

			int sizeNo = size == null ? 10 : size.intValue();
			final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
			uiModel.addAttribute("propinsiList",propinsiManager.selectPagingList(search,sortFieldName,sortOrder, firstResult, sizeNo) );
			float nrOfPages = (float) propinsiManager.selectPagingCount(search) / sizeNo;
			uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
		addDateTimeFormatPatterns(uiModel);
		return "propinsi/list";
	}

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
	public String update(@Valid Propinsi propinsi, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, propinsi);
			return "propinsi/update";
		}
		uiModel.asMap().clear();
		propinsiManager.save(propinsi);
		return "redirect:/master/propinsi/" + encodeUrlPathSegment(propinsi.getPropinsi().toString(), httpServletRequest);
	}

	@RequestMapping(value = "/{propinsi}", params = "form", produces = "text/html")
	public String updateForm(@PathVariable("propinsi") String propinsi, Model uiModel) {
		populateEditForm(uiModel, propinsiManager.get(propinsi));
		return "propinsi/update";
	}

	@RequestMapping(value = "/{propinsi}", method = RequestMethod.DELETE, produces = "text/html")
	public String delete(@PathVariable("propinsi") String propinsi, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
		propinsiManager.remove(propinsi);
		uiModel.asMap().clear();
		uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
		uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
		return "redirect:/master/propinsi";
	}
	void addDateTimeFormatPatterns(Model uiModel) {
		uiModel.addAttribute("propinsi_tgl_create_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
	}
	void populateEditForm(Model uiModel, Propinsi propinsi) {
		uiModel.addAttribute("propinsi", propinsi);
		addDateTimeFormatPatterns(uiModel);
	}
}
