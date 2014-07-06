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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.melawai.ppuc.model.UserDivisi;
import com.melawai.ppuc.services.UserDivisiManager;
import com.melawai.ppuc.web.validator.UserDivisiValidator;

@RequestMapping("/master/userdivisi")
@Controller
public class UserDivisiController extends ParentController{

	protected static Logger logger = Logger.getLogger(UserDivisiController.class);

	@Autowired
	private UserDivisiManager userdivisiManager;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new UserDivisiValidator());
	}
	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
	public String create(@Valid UserDivisi userdivisi, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, userdivisi);
			return "userdivisi/create";
		}
		uiModel.asMap().clear();
		userdivisiManager.save(userdivisi);
		return "redirect:/master/userdivisi/" + encodeUrlPathSegment(userdivisi.getId_user_divisi().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(userdivisi.getUser_id().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(userdivisi.getDivisi_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(userdivisi.getSubdiv_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(userdivisi.getDept_kd().toString(), httpServletRequest);
	}

	@RequestMapping(params = "form", produces = "text/html")
	public String createForm(Model uiModel) {
		populateEditForm(uiModel, new UserDivisi());
		return "userdivisi/create";
	}

	@RequestMapping(value = "/{id_user_divisi}/{user_id}/{divisi_kd}/{subdiv_kd}/{dept_kd}", produces = "text/html")
	public String show(@PathVariable("id_user_divisi") Long id_user_divisi, @PathVariable("user_id") String user_id, @PathVariable("divisi_kd") String divisi_kd, @PathVariable("subdiv_kd") String subdiv_kd, @PathVariable("dept_kd") String dept_kd, Model uiModel) {
		addDateTimeFormatPatterns(uiModel);
		uiModel.addAttribute("userdivisi", userdivisiManager.get(id_user_divisi, user_id, divisi_kd, subdiv_kd, dept_kd));
		uiModel.addAttribute("itemId", id_user_divisi+"/"+user_id+"/"+divisi_kd+"/"+subdiv_kd+"/"+dept_kd);
		return "userdivisi/show";
	}

	@RequestMapping(produces = "text/html")
	public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size,@RequestParam(value = "search", required = false) String search, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
		if (page == null) {
			page=1;
		}

			int sizeNo = size == null ? 10 : size.intValue();
			final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
			uiModel.addAttribute("userdivisiList",userdivisiManager.selectPagingList(search,sortFieldName,sortOrder, firstResult, sizeNo) );
			float nrOfPages = (float) userdivisiManager.selectPagingCount(search) / sizeNo;
			uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
		addDateTimeFormatPatterns(uiModel);
		return "userdivisi/list";
	}

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
	public String update(@Valid UserDivisi userdivisi, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, userdivisi);
			return "userdivisi/update";
		}
		uiModel.asMap().clear();
		userdivisiManager.save(userdivisi);
		return "redirect:/master/userdivisi/" + encodeUrlPathSegment(userdivisi.getId_user_divisi().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(userdivisi.getUser_id().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(userdivisi.getDivisi_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(userdivisi.getSubdiv_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(userdivisi.getDept_kd().toString(), httpServletRequest);
	}

	@RequestMapping(value = "/{id_user_divisi}/{user_id}/{divisi_kd}/{subdiv_kd}/{dept_kd}", params = "form", produces = "text/html")
	public String updateForm(@PathVariable("id_user_divisi") Long id_user_divisi, @PathVariable("user_id") String user_id, @PathVariable("divisi_kd") String divisi_kd, @PathVariable("subdiv_kd") String subdiv_kd, @PathVariable("dept_kd") String dept_kd, Model uiModel) {
		populateEditForm(uiModel, userdivisiManager.get(id_user_divisi, user_id, divisi_kd, subdiv_kd, dept_kd));
		return "userdivisi/update";
	}

	@RequestMapping(value = "/{id_user_divisi}/{user_id}/{divisi_kd}/{subdiv_kd}/{dept_kd}", method = RequestMethod.DELETE, produces = "text/html")
	public String delete(@PathVariable("id_user_divisi") Long id_user_divisi, @PathVariable("user_id") String user_id, @PathVariable("divisi_kd") String divisi_kd, @PathVariable("subdiv_kd") String subdiv_kd, @PathVariable("dept_kd") String dept_kd, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
		UserDivisi userdivisi = userdivisiManager.get(id_user_divisi, user_id, divisi_kd, subdiv_kd, dept_kd);
		userdivisiManager.remove(id_user_divisi, user_id, divisi_kd, subdiv_kd, dept_kd);
		uiModel.asMap().clear();
		uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
		uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
		return "redirect:/master/userdivisi";
	}
	void addDateTimeFormatPatterns(Model uiModel) {
		uiModel.addAttribute("userdivisi_sys_tgl_create_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
	}
	void populateEditForm(Model uiModel, UserDivisi userdivisi) {
		uiModel.addAttribute("userdivisi", userdivisi);
		addDateTimeFormatPatterns(uiModel);
	}
}
