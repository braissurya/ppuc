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

import com.melawai.ppuc.services.KotaManager;
import com.melawai.ppuc.web.controller.ParentController;
import com.melawai.ppuc.model.Kota;
import com.melawai.ppuc.web.validator.KotaValidator;

@RequestMapping("/master/kota")
@Controller
public class KotaController extends ParentController{

	protected static Logger logger = Logger.getLogger(KotaController.class);

	@Autowired
	private KotaManager kotaManager;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new KotaValidator());
	}
	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
	public String create(@Valid Kota kota, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, kota);
			return "kota/create";
		}
		uiModel.asMap().clear();
		kotaManager.save(kota);
		return "redirect:/master/kota/" + encodeUrlPathSegment(kota.getPropinsi().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(kota.getKota().toString(), httpServletRequest);
	}

	@RequestMapping(params = "form", produces = "text/html")
	public String createForm(Model uiModel) {
		populateEditForm(uiModel, new Kota());
		return "kota/create";
	}

	@RequestMapping(value = "/{propinsi}/{kota}", produces = "text/html")
	public String show(@PathVariable("propinsi") String propinsi, @PathVariable("kota") String kota, Model uiModel) {
		addDateTimeFormatPatterns(uiModel);
		uiModel.addAttribute("kota", kotaManager.get(propinsi, kota));
		uiModel.addAttribute("itemId", propinsi+"/"+kota);
		return "kota/show";
	}

	@RequestMapping(produces = "text/html")
	public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size,@RequestParam(value = "search", required = false) String search, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
		if (page == null) {
			page=1;
		}

			int sizeNo = size == null ? 10 : size.intValue();
			final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
			uiModel.addAttribute("kotaList",kotaManager.selectPagingList(search,sortFieldName,sortOrder, firstResult, sizeNo) );
			float nrOfPages = (float) kotaManager.selectPagingCount(search) / sizeNo;
			uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
		addDateTimeFormatPatterns(uiModel);
		return "kota/list";
	}

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
	public String update(@Valid Kota kota, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, kota);
			return "kota/update";
		}
		uiModel.asMap().clear();
		kotaManager.save(kota);
		return "redirect:/master/kota/" + encodeUrlPathSegment(kota.getPropinsi().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(kota.getKota().toString(), httpServletRequest);
	}

	@RequestMapping(value = "/{propinsi}/{kota}", params = "form", produces = "text/html")
	public String updateForm(@PathVariable("propinsi") String propinsi, @PathVariable("kota") String kota, Model uiModel) {
		populateEditForm(uiModel, kotaManager.get(propinsi, kota));
		return "kota/update";
	}

	@RequestMapping(value = "/{propinsi}/{kota}", method = RequestMethod.DELETE, produces = "text/html")
	public String delete(@PathVariable("propinsi") String propinsi, @PathVariable("kota") String kota, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
		kotaManager.remove(propinsi, kota);
		uiModel.asMap().clear();
		uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
		uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
		return "redirect:/master/kota";
	}
	void addDateTimeFormatPatterns(Model uiModel) {
		uiModel.addAttribute("kota_tgl_create_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
	}
	void populateEditForm(Model uiModel, Kota kota) {
		uiModel.addAttribute("kota", kota);
		addDateTimeFormatPatterns(uiModel);
	}
}
