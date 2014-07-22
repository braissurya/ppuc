package com.melawai.ppuc.web.controller;

import java.util.Set;

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

import com.melawai.ppuc.model.Audittrail;
import com.melawai.ppuc.model.AudittrailDetail;
import com.melawai.ppuc.model.User;
import com.melawai.ppuc.services.GroupUserManager;
import com.melawai.ppuc.services.MFungsiManager;
import com.melawai.ppuc.services.UserManager;
import com.melawai.ppuc.utils.CommonUtil;
import com.melawai.ppuc.utils.Utils;
import com.melawai.ppuc.web.validator.UserValidator;

@RequestMapping("/master/user")
@Controller
public class UserController extends ParentController {

	protected static Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	private UserManager userManager;
	
	@Autowired
	private UserValidator userValidator;


	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(this.userValidator);
	}

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
	public String create(@ModelAttribute("user")@Valid User user, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		
		if (!Utils.isEmpty(user.getUser_id())) {
			if (userManager.exists(user.getUser_id()))
				bindingResult.rejectValue("user_id", "duplicate", new String[] { "User ID" }, null);
		}
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, user);
			return "user/create";
		}
		uiModel.asMap().clear();
		userManager.saveUserLogin(user);
		return "redirect:/master/user/" + encodeUrlPathSegment(user.getUser_id().toString(), httpServletRequest);
	}

	@RequestMapping(params = "form", produces = "text/html")
	public String createForm(Model uiModel) {
		populateEditForm(uiModel, new User());
		return "user/create";
	}

	@RequestMapping(value = "/{user_id}", produces = "text/html")
	public String show(@PathVariable("user_id") String user_id, Model uiModel) {
		addDateTimeFormatPatterns(uiModel);
		uiModel.addAttribute("user", userManager.get(user_id));
		uiModel.addAttribute("itemId", user_id);
		return "user/show";
	}

	@RequestMapping(produces = "text/html")
	public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size,
			@RequestParam(value = "search", required = false) String search, @RequestParam(value = "sortFieldName", required = false) String sortFieldName,
			@RequestParam(value = "sortOrder", required = false) String sortOrder,@RequestParam(value = "group", required = false) String group,@RequestParam(value = "aktif", required = false) Integer aktif, Model uiModel) {
		if (page == null) {
			page = 1;
		}
		
		if(Utils.isEmpty(group))group=null;

		int sizeNo = size == null ? 10 : size.intValue();
		final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
		uiModel.addAttribute("userList", userManager.selectPagingList(search, sortFieldName, sortOrder, firstResult, sizeNo,group,aktif));
		float nrOfPages = (float) userManager.selectPagingCount(search,group,aktif) / sizeNo;
		uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
		uiModel.addAttribute("group", group);
		uiModel.addAttribute("aktif", aktif);
		addDateTimeFormatPatterns(uiModel);
		return "user/list";
	}

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
	public String update(@ModelAttribute("user")@Valid User user, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, user);
			return "user/update";
		}
		uiModel.asMap().clear();
		userManager.saveUserLogin(user);
		return "redirect:/master/user/" + encodeUrlPathSegment(user.getUser_id().toString(), httpServletRequest);
	}

	@RequestMapping(value = "/{user_id}", params = "form", produces = "text/html")
	public String updateForm(@PathVariable("user_id") String user_id, Model uiModel) {
		populateEditForm(uiModel, userManager.get(user_id));
		return "user/update";
	}

	@RequestMapping(value = "/{user_id}", method = RequestMethod.DELETE, produces = "text/html")
	public String delete(@PathVariable("user_id") String user_id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size,
			Model uiModel) {
		User user = userManager.get(user_id);
		userManager.remove(user_id);
		uiModel.asMap().clear();
		uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
		uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
		uiModel.addAttribute("pesan", messageSource.getMessage("entity_success", new String[]{"Delete User "+user_id}, LocaleContextHolder.getLocale()));
		return "redirect:/master/user";
	}

	void addDateTimeFormatPatterns(Model uiModel) {
		uiModel.addAttribute("user_tgl_update_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
		uiModel.addAttribute("user_tgl_create_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
	}

	void populateEditForm(Model uiModel, User user) {
		uiModel.addAttribute("user", user);
		addDateTimeFormatPatterns(uiModel);
		uiModel.addAttribute("mfungsiList",baseService.selectDropDown("nm_fungsi", "kd_fungsi", "m_fungsi", null, "nm_fungsi"));
		uiModel.addAttribute("groupKodeList", baseService.selectDropDown("group_nm", "group_kd", "group_user", null, "group_nm"));
	}

	@RequestMapping(value = "/reset/{user_id}", method = RequestMethod.GET, produces = "text/html")
	public String reset(@PathVariable("user_id") String user_id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size,
			Model uiModel, HttpServletRequest request) {
		User user = userManager.get(user_id);
		user.setNewPassword(props.getProperty("password.default"));
		userManager.saveUserLogin(user);
		
		userManager.audittrail(Audittrail.Activity.TRANS, Audittrail.TransType.RESET, user.getClass().getSimpleName(), user.getItemId(), CommonUtil.getIpAddr(request), "RESER PASSWORD",CommonUtil.getCurrentUser(), null);
		
		uiModel.asMap().clear();
		uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
		uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
		uiModel.addAttribute("pesan", messageSource.getMessage("entity_success", new String[]{"Reset Password User "+user_id}, LocaleContextHolder.getLocale()));
		return "redirect:/master/user";
	}
	
	@RequestMapping(value = "/aktifnonaktif/{user_id}", method = RequestMethod.GET, produces = "text/html")
	public String nonaktif(@PathVariable("user_id") String user_id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size,
			Model uiModel, HttpServletRequest request) {
		User user = userManager.get(user_id);
		
		if(user.getF_aktif()==1){
			user.setF_aktif(0);
			user.setTgl_nonaktif(userManager.selectSysdate());
			user.setUser_nonaktif(CommonUtil.getCurrentUserId());
		}else{
			user.setF_aktif(1);
			user.setTgl_nonaktif(null);
			user.setJam_nonaktif(null);
			user.setUser_nonaktif(null);
		}
		
		
		userManager.saveUserLogin(user);
		
		if(user.getF_aktif()==1){	
			userManager.audittrail(Audittrail.Activity.TRANS, Audittrail.TransType.AKTIF, user.getClass().getSimpleName(), user.getItemId(), CommonUtil.getIpAddr(request), "AKTIFKAN USER",CommonUtil.getCurrentUser(), null);
		}else{
			userManager.audittrail(Audittrail.Activity.TRANS, Audittrail.TransType.NONAKTIF, user.getClass().getSimpleName(), user.getItemId(), CommonUtil.getIpAddr(request), "NON AKTIFKAN USER",CommonUtil.getCurrentUser(), null);
		}
		uiModel.asMap().clear();
		uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
		uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
		uiModel.addAttribute("pesan", messageSource.getMessage("entity_success", new String[]{user.getIsActive()+" User "+user_id}, LocaleContextHolder.getLocale()));
		
		return "redirect:/master/user";
	}
}
