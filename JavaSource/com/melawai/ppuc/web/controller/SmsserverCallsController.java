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

import com.melawai.ppuc.model.SmsserverCalls;
import com.melawai.ppuc.services.SmsserverCallsManager;
import com.melawai.ppuc.web.validator.SmsserverCallsValidator;

@RequestMapping("/master/smsservercalls")
@Controller
public class SmsserverCallsController extends ParentController{

	protected static Logger logger = Logger.getLogger(SmsserverCallsController.class);

	@Autowired
	private SmsserverCallsManager smsservercallsManager;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new SmsserverCallsValidator());
	}
	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
	public String create(@ModelAttribute("smsservercalls")@Valid SmsserverCalls smsservercalls, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, smsservercalls);
			return "smsservercalls/create";
		}
		uiModel.asMap().clear();
		smsservercallsManager.save(smsservercalls);
		return "redirect:/master/smsservercalls/" + encodeUrlPathSegment(smsservercalls.getId().toString(), httpServletRequest);
	}

	@RequestMapping(params = "form", produces = "text/html")
	public String createForm(Model uiModel) {
		populateEditForm(uiModel, new SmsserverCalls());
		return "smsservercalls/create";
	}

	@RequestMapping(value = "/{id}", produces = "text/html")
	public String show(@PathVariable("id") Long id, Model uiModel) {
		addDateTimeFormatPatterns(uiModel);
		uiModel.addAttribute("smsservercalls", smsservercallsManager.get(id));
		uiModel.addAttribute("itemId", id);
		return "smsservercalls/show";
	}

	@RequestMapping(produces = "text/html")
	public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size,@RequestParam(value = "search", required = false) String search, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
		if (page == null) {
			page=1;
		}

			int sizeNo = size == null ? 10 : size.intValue();
			final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
			uiModel.addAttribute("smsservercallsList",smsservercallsManager.selectPagingList(search,sortFieldName,sortOrder, firstResult, sizeNo) );
			float nrOfPages = (float) smsservercallsManager.selectPagingCount(search) / sizeNo;
			uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
		addDateTimeFormatPatterns(uiModel);
		return "smsservercalls/list";
	}

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
	public String update(@ModelAttribute("smsservercalls")@Valid SmsserverCalls smsservercalls, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, smsservercalls);
			return "smsservercalls/update";
		}
		uiModel.asMap().clear();
		smsservercallsManager.save(smsservercalls);
		return "redirect:/master/smsservercalls/" + encodeUrlPathSegment(smsservercalls.getId().toString(), httpServletRequest);
	}

	@RequestMapping(value = "/{id}", params = "form", produces = "text/html")
	public String updateForm(@PathVariable("id") Long id, Model uiModel) {
		populateEditForm(uiModel, smsservercallsManager.get(id));
		return "smsservercalls/update";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
	public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
		SmsserverCalls smsservercalls = smsservercallsManager.get(id);
		smsservercallsManager.remove(id);
		uiModel.asMap().clear();
		uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
		uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
		return "redirect:/master/smsservercalls";
	}
	void addDateTimeFormatPatterns(Model uiModel) {
		uiModel.addAttribute("smsservercalls_call_date_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
	}
	void populateEditForm(Model uiModel, SmsserverCalls smsservercalls) {
		uiModel.addAttribute("smsservercalls", smsservercalls);
		addDateTimeFormatPatterns(uiModel);
	}
}
