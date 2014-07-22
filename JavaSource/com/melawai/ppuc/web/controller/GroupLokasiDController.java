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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

import com.melawai.ppuc.services.GroupLokasiDManager;
import com.melawai.ppuc.web.controller.ParentController;
import com.melawai.ppuc.model.GroupLokasiD;
import com.melawai.ppuc.web.validator.GroupLokasiDValidator;

@RequestMapping("/master/grouplokasid")
@Controller
public class GroupLokasiDController extends ParentController{

	protected static Logger logger = Logger.getLogger(GroupLokasiDController.class);

	@Autowired
	private GroupLokasiDManager grouplokasidManager;
	
	@Autowired
	private GroupLokasiDValidator grouplokasidValidator;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(this.grouplokasidValidator);
	}
	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
	public String create(@ModelAttribute("grouplokasid") @Valid GroupLokasiD grouplokasid, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, grouplokasid);
			return "grouplokasid/create";
		}
		uiModel.asMap().clear();
		grouplokasidManager.save(grouplokasid);
		return "redirect:/master/grouplokasid/" + encodeUrlPathSegment(grouplokasid.getDivisi_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(grouplokasid.getSubdiv_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(grouplokasid.getGroup_lok().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(grouplokasid.getLok_kd().toString(), httpServletRequest);
	}

	@RequestMapping(params = "form", produces = "text/html")
	public String createForm(Model uiModel) {
		populateEditForm(uiModel, new GroupLokasiD());
		return "grouplokasid/create";
	}

	@RequestMapping(value = "/{divisi_kd}/{subdiv_kd}/{group_lok}/{lok_kd}", produces = "text/html")
	public String show(@PathVariable("divisi_kd") String divisi_kd, @PathVariable("subdiv_kd") String subdiv_kd, @PathVariable("group_lok") String group_lok, @PathVariable("lok_kd") String lok_kd, Model uiModel) {
		addDateTimeFormatPatterns(uiModel);
		uiModel.addAttribute("grouplokasid", grouplokasidManager.get(divisi_kd, subdiv_kd, group_lok, lok_kd));
		uiModel.addAttribute("itemId", divisi_kd+"/"+subdiv_kd+"/"+group_lok+"/"+lok_kd);
		return "grouplokasid/show";
	}

	@RequestMapping(produces = "text/html")
	public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size,@RequestParam(value = "search", required = false) String search, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
		if (page == null) {
			page=1;
		}

			int sizeNo = size == null ? 10 : size.intValue();
			final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
			uiModel.addAttribute("grouplokasidList",grouplokasidManager.selectPagingList(search,sortFieldName,sortOrder, firstResult, sizeNo) );
			float nrOfPages = (float) grouplokasidManager.selectPagingCount(search) / sizeNo;
			uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
		addDateTimeFormatPatterns(uiModel);
		return "grouplokasid/list";
	}

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
	public String update(@ModelAttribute("grouplokasid")@Valid GroupLokasiD grouplokasid, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, grouplokasid);
			return "grouplokasid/update";
		}
		uiModel.asMap().clear();
		grouplokasidManager.save(grouplokasid);
		return "redirect:/master/grouplokasid/" + encodeUrlPathSegment(grouplokasid.getDivisi_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(grouplokasid.getSubdiv_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(grouplokasid.getGroup_lok().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(grouplokasid.getLok_kd().toString(), httpServletRequest);
	}

	@RequestMapping(value = "/{divisi_kd}/{subdiv_kd}/{group_lok}/{lok_kd}", params = "form", produces = "text/html")
	public String updateForm(@PathVariable("divisi_kd") String divisi_kd, @PathVariable("subdiv_kd") String subdiv_kd, @PathVariable("group_lok") String group_lok, @PathVariable("lok_kd") String lok_kd, Model uiModel) {
		populateEditForm(uiModel, grouplokasidManager.get(divisi_kd, subdiv_kd, group_lok, lok_kd));
		return "grouplokasid/update";
	}

	@RequestMapping(value = "/{divisi_kd}/{subdiv_kd}/{group_lok}/{lok_kd}", method = RequestMethod.DELETE, produces = "text/html")
	public String delete(@PathVariable("divisi_kd") String divisi_kd, @PathVariable("subdiv_kd") String subdiv_kd, @PathVariable("group_lok") String group_lok, @PathVariable("lok_kd") String lok_kd, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
		GroupLokasiD grouplokasid = grouplokasidManager.get(divisi_kd, subdiv_kd, group_lok, lok_kd);
		grouplokasidManager.remove(divisi_kd, subdiv_kd, group_lok, lok_kd);
		uiModel.asMap().clear();
		uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
		uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
		return "redirect:/master/grouplokasid";
	}
	void addDateTimeFormatPatterns(Model uiModel) {
		uiModel.addAttribute("grouplokasid_tgl_create_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
	}
	void populateEditForm(Model uiModel, GroupLokasiD grouplokasid) {
		uiModel.addAttribute("grouplokasid", grouplokasid);
		addDateTimeFormatPatterns(uiModel);
	}
}
