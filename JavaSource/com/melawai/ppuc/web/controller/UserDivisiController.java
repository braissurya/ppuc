package com.melawai.ppuc.web.controller;

import java.util.List;

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

import com.melawai.ppuc.model.DropDown;
import com.melawai.ppuc.model.UserDivisi;
import com.melawai.ppuc.services.UserDivisiManager;
import com.melawai.ppuc.utils.Utils;
import com.melawai.ppuc.web.validator.UserDivisiValidator;

@RequestMapping("/master/userdivisi")
@Controller
public class UserDivisiController extends ParentController{

	protected static Logger logger = Logger.getLogger(UserDivisiController.class);

	@Autowired
	private UserDivisiManager userdivisiManager;
	
	@Autowired
	private UserDivisiValidator userDivisiValidator;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(this.userDivisiValidator);
	}
	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
	public String create(@ModelAttribute("userdivisi") @Valid UserDivisi userdivisi, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		// tambahan validasi khusus
		if (userdivisiManager.exists(null,userdivisi.user_id, userdivisi.divisi_kd, userdivisi.subdiv_kd, userdivisi.dept_kd,userdivisi.getLok_kd())) {
			bindingResult.rejectValue("lok_kd", "duplicate", new String[] { "LOKASI KD : " + userdivisi.lok_kd + " | DIVISI KD : " + userdivisi.divisi_kd + " | SUBDIVISI KD : " + userdivisi.subdiv_kd
					+ " | DEPARTMEN KD : " + userdivisi.dept_kd+ " | User ID : " + userdivisi.user_id  + ", " }, null);
		}
		
		if(userdivisiManager.selectCountTable("user_divisi", "user_id='"+userdivisi.user_id+"'")>0){
			UserDivisi tmp=userdivisiManager.getDivisiNSubdivUser(userdivisi.user_id);
			if(!tmp.divisi_kd.equals(userdivisi.divisi_kd)||!tmp.subdiv_kd.equals(userdivisi.subdiv_kd)){
				bindingResult.rejectValue("subdiv_kd", "existWith", new String[]{"This User ID","DIVISI KD : " + userdivisi.divisi_kd + " and SUBDIVISI KD : " + userdivisi.subdiv_kd+""},null);
			}
			
		}

		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, userdivisi);
			return "userdivisi/create";
		}
		uiModel.asMap().clear();
		userdivisiManager.save(userdivisi);
		return "redirect:/master/userdivisi/" +  encodeUrlPathSegment(userdivisi.getId_user_divisi().toString(), httpServletRequest);
	}

	@RequestMapping(params = "form", produces = "text/html")
	public String createForm(Model uiModel) {
		populateEditForm(uiModel, new UserDivisi());
		return "userdivisi/create";
	}

	@RequestMapping(value = "/{id_user_divisi}", produces = "text/html")
	public String show(@PathVariable("id_user_divisi") Long id_user_divisi,Model uiModel) {
		addDateTimeFormatPatterns(uiModel);
		UserDivisi userdivisi=userdivisiManager.get(id_user_divisi);
		uiModel.addAttribute("userdivisi",userdivisi );
		uiModel.addAttribute("itemId", id_user_divisi);
		
		//FIXME : belum bisa keluarin data show yang dropdown !!
		
		uiModel.addAttribute("useridList", baseService.selectDropDown("concat(user_id,' [ ',user_name,' ]')", "user_id", "user", null, "user_id"));
		
		uiModel.addAttribute("divisiList", baseService.selectDropDown("divisi_nm", "divisi_kd", "divisi", null, "divisi_nm"));
		
		if (!Utils.isEmpty(userdivisi.divisi_kd))
			uiModel.addAttribute("subdivList", baseService.selectDropDown("subdiv_nm", "concat(divisi_kd, '.', subdiv_kd)", "subdivisi", "divisi_kd = '" + userdivisi.divisi_kd + "'", "subdiv_nm"));
		else
			uiModel.addAttribute("subdivList", baseService.selectDropDown("subdiv_nm", "concat(divisi_kd, '.', subdiv_kd)", "subdivisi", null, "subdiv_nm"));

		if (userdivisiManager.selectCountTable("departmen", " divisi_kd = '" + userdivisi.divisi_kd + "' and subdiv_kd = '" + userdivisi.subdiv_kd + "'")>0)
			uiModel.addAttribute("deptList", baseService.selectDropDown("dept_nm","concat(divisi_kd, '.', subdiv_kd, '.', dept_kd)", "departmen", " divisi_kd = '" + userdivisi.divisi_kd + "' and subdiv_kd = '" + userdivisi.subdiv_kd + "'", "dept_nm"));
		else
			uiModel.addAttribute("deptList", baseService.selectDropDown("dept_nm","concat(divisi_kd, '.', subdiv_kd, '.', dept_kd)",  "departmen", null, "dept_nm"));

		if (userdivisiManager.selectCountTable("lokasi", " divisi_kd = '" + userdivisi.divisi_kd + "' and subdiv_kd = '" + userdivisi.subdiv_kd + "' and dept_kd = '" + userdivisi.dept_kd + "'")>0)
			uiModel.addAttribute("lokList", baseService.selectDropDown("lok_nm","concat(divisi_kd, '.', subdiv_kd, '.', dept_kd, '.', lok_kd)", "lokasi", " divisi_kd = '" + userdivisi.divisi_kd + "' and subdiv_kd = '" + userdivisi.subdiv_kd + "' and dept_kd = '" + userdivisi.dept_kd + "'", "lok_nm"));
		else
			uiModel.addAttribute("lokList", baseService.selectDropDown("lok_nm","concat(divisi_kd, '.', subdiv_kd, '.', dept_kd, '.', lok_kd)",  "lokasi", null, "lok_nm"));

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
	public String update(@ModelAttribute("userdivisi")@Valid UserDivisi userdivisi, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, userdivisi);
			return "userdivisi/update";
		}
		uiModel.asMap().clear();
		userdivisiManager.save(userdivisi);
		return "redirect:/master/userdivisi/"  + encodeUrlPathSegment(userdivisi.getId_user_divisi().toString(), httpServletRequest);
	}

	@RequestMapping(value = "/{id_user_divisi}", params = "form", produces = "text/html")
	public String updateForm( @PathVariable("id_user_divisi") Long id_user_divisi, Model uiModel) {
		populateEditForm(uiModel, userdivisiManager.get(id_user_divisi));
		return "userdivisi/update";
	}
	
	@RequestMapping(value = "/{id_user_divisi}", method = RequestMethod.DELETE, produces = "text/html")
	public String delete(@PathVariable("id_user_divisi") Long id_user_divisi, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
		UserDivisi userdivisi = userdivisiManager.get(id_user_divisi);
		userdivisiManager.remove(id_user_divisi);
		uiModel.asMap().clear();
		uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
		uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
		return "redirect:/master/userdivisi";
	}
	
	void addDateTimeFormatPatterns(Model uiModel) {
		uiModel.addAttribute("userdivisi_tgl_create_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
	}
	void populateEditForm(Model uiModel, UserDivisi userdivisi) {
		uiModel.addAttribute("userdivisi", userdivisi);
		addDateTimeFormatPatterns(uiModel);
		
		uiModel.addAttribute("useridList", baseService.selectDropDown("concat(user_id,' [ ',user_name,' ]')", "user_id", "user", null, "user_id"));
		
		uiModel.addAttribute("divisiList", baseService.selectDropDown("divisi_nm", "divisi_kd", "divisi", null, "divisi_nm"));
		
		if (userdivisiManager.selectCountTable("subdivisi", "divisi_kd = '" + userdivisi.divisi_kd + "'")>0)
			uiModel.addAttribute("subdivList", baseService.selectDropDown("subdiv_nm", "concat(divisi_kd, '.', subdiv_kd)", "subdivisi", "divisi_kd = '" + userdivisi.divisi_kd + "'", "subdiv_nm"));
		else
			uiModel.addAttribute("subdivList", baseService.selectDropDown("subdiv_nm", "concat(divisi_kd, '.', subdiv_kd)", "subdivisi", null, "subdiv_nm"));

		if (userdivisiManager.selectCountTable("departmen", " divisi_kd = '" + userdivisi.divisi_kd + "' and subdiv_kd = '" + userdivisi.subdiv_kd + "'")>0)
			uiModel.addAttribute("deptList", baseService.selectDropDown("dept_nm","concat(divisi_kd, '.', subdiv_kd, '.', dept_kd)", "departmen", " divisi_kd = '" + userdivisi.divisi_kd + "' and subdiv_kd = '" + userdivisi.subdiv_kd + "'", "dept_nm"));
		else
			uiModel.addAttribute("deptList", baseService.selectDropDown("dept_nm","concat(divisi_kd, '.', subdiv_kd, '.', dept_kd)",  "departmen", null, "dept_nm"));

		if (userdivisiManager.selectCountTable("lokasi", " divisi_kd = '" + userdivisi.divisi_kd + "' and subdiv_kd = '" + userdivisi.subdiv_kd + "' and dept_kd = '" + userdivisi.dept_kd + "'")>0)
			uiModel.addAttribute("lokList", baseService.selectDropDown("lok_nm","concat(divisi_kd, '.', subdiv_kd, '.', dept_kd, '.', lok_kd)", "lokasi", " divisi_kd = '" + userdivisi.divisi_kd + "' and subdiv_kd = '" + userdivisi.subdiv_kd + "' and dept_kd = '" + userdivisi.dept_kd + "'", "lok_nm"));
		else
			uiModel.addAttribute("lokList", baseService.selectDropDown("lok_nm","concat(divisi_kd, '.', subdiv_kd, '.', dept_kd, '.', lok_kd)",  "lokasi", null, "lok_nm"));

	}
}
