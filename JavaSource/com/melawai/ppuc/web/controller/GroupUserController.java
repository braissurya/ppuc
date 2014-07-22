		package com.melawai.ppuc.web.controller;

import java.util.ArrayList;
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

import com.melawai.ppuc.model.GroupUser;
import com.melawai.ppuc.model.Menu;
import com.melawai.ppuc.services.AksesMenuManager;
import com.melawai.ppuc.services.GroupUserManager;
import com.melawai.ppuc.services.MenuManager;
import com.melawai.ppuc.web.validator.GroupUserValidator;

@RequestMapping("/master/groupuser")
@Controller
public class GroupUserController extends ParentController{

	protected static Logger logger = Logger.getLogger(GroupUserController.class);

	@Autowired
	private GroupUserManager groupuserManager;
	
	@Autowired
	private MenuManager menuManager;
	
	@Autowired
	private AksesMenuManager aksesMenuManager;
	
	@Autowired
	private GroupUserValidator groupUserValidator;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(this.groupUserValidator);
	}
	
	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
	public String create(@ModelAttribute("groupuser")@Valid GroupUser groupuser, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, groupuser);
			return "groupuser/create";
		}
		uiModel.asMap().clear();
		groupuserManager.save(groupuser);
		uiModel.addAttribute("pesan", messageSource.getMessage("entity_success", new String[]{"Create Group User : "+groupuser.group_kd+","}, LocaleContextHolder.getLocale()));
		return "redirect:/master/groupuser/" + encodeUrlPathSegment(groupuser.getGroup_kd().toString(), httpServletRequest);
	}

	@RequestMapping(params = "form", produces = "text/html")
	public String createForm(Model uiModel) {
		GroupUser groupUser=new GroupUser();
		groupUser.menus = menuManager.getAll();
		populateEditForm(uiModel,groupUser );
		return "groupuser/create";
	}

	@RequestMapping(value = "/{group_kd}", produces = "text/html")
	public String show(@PathVariable("group_kd") String group_kd, Model uiModel) {
		addDateTimeFormatPatterns(uiModel);
		GroupUser groupUser=groupuserManager.get(group_kd);
		List<Menu> lsMenu=new ArrayList<Menu>();			
		for(Menu mn:menuManager.getAll()){
			if(aksesMenuManager.exists(groupUser.group_kd, mn.menu_id)){
				mn.akses=true;
			}
			lsMenu.add(mn);
		}
		groupUser.menus=lsMenu;
		uiModel.addAttribute("groupuser", groupUser);
		uiModel.addAttribute("itemId", group_kd);
		return "groupuser/show";
	}

	@RequestMapping(produces = "text/html")
	public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size,@RequestParam(value = "search", required = false) String search, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
		if (page == null) {
			page=1;
		}

			int sizeNo = size == null ? 10 : size.intValue();
			final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
			uiModel.addAttribute("groupuserList",groupuserManager.selectPagingList(search,sortFieldName,sortOrder, firstResult, sizeNo) );
			float nrOfPages = (float) groupuserManager.selectPagingCount(search) / sizeNo;
			uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
		addDateTimeFormatPatterns(uiModel);
		return "groupuser/list";
	}

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
	public String update(@ModelAttribute("groupuser")@Valid GroupUser groupuser, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, groupuser);
			return "groupuser/update";
		}
		uiModel.asMap().clear();
		groupuserManager.save(groupuser);
		uiModel.addAttribute("pesan", messageSource.getMessage("entity_success", new String[]{"Update Group User : "+groupuser.group_kd+","}, LocaleContextHolder.getLocale()));
		return "redirect:/master/groupuser/" + encodeUrlPathSegment(groupuser.getGroup_kd().toString(), httpServletRequest);
	}

	@RequestMapping(value = "/{group_kd}", params = "form", produces = "text/html")
	public String updateForm(@PathVariable("group_kd") String group_kd, Model uiModel) {
		GroupUser groupUser=groupuserManager.get(group_kd);
		List<Menu> lsMenu=new ArrayList<Menu>();			
		for(Menu mn:menuManager.getAll()){
			if(aksesMenuManager.exists(groupUser.group_kd, mn.menu_id)){
				mn.akses=true;
			}
			lsMenu.add(mn);
		}
		groupUser.menus=lsMenu;
		populateEditForm(uiModel,groupUser );
		return "groupuser/update";
	}

	@RequestMapping(value = "/{group_kd}", method = RequestMethod.DELETE, produces = "text/html")
	public String delete(@PathVariable("group_kd") String group_kd, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
		String pesan=messageSource.getMessage("entity_success", new String[]{"Delete Group User : "+group_kd+","}, LocaleContextHolder.getLocale());
		
		if(!groupuserManager.exists(group_kd)){
			pesan="Group User "+group_kd+" tidak ditemukan";
			messageSource.getMessage("entity_not_found", new String[]{"Group User : "+group_kd+","}, LocaleContextHolder.getLocale());
		}else if(groupuserManager.selectCountTable("user", "group_kd='"+group_kd+"'")>0){
			pesan=messageSource.getMessage("entity_used_by", new String[]{"Group User : "+group_kd+",","Master User"}, LocaleContextHolder.getLocale());
		}else{
			groupuserManager.remove(group_kd);
		}
		uiModel.asMap().clear();
		uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
		uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
		uiModel.addAttribute("pesan", pesan);
		return "redirect:/master/groupuser";
	}
	void addDateTimeFormatPatterns(Model uiModel) {
		uiModel.addAttribute("groupuser_tgl_create_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
	}
	void populateEditForm(Model uiModel, GroupUser groupuser) {
		uiModel.addAttribute("groupuser", groupuser);
		uiModel.addAttribute("roleList",baseService.selectDropDown("role_name", "id_role", "role", null, "role_name"));
		addDateTimeFormatPatterns(uiModel);
	}
}
