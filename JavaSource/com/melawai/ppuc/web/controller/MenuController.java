package com.melawai.ppuc.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.BeanUtils;
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

import com.melawai.ppuc.model.Menu;
import com.melawai.ppuc.services.MenuManager;
import com.melawai.ppuc.web.validator.MenuValidator;

@RequestMapping("/master/menu")
@Controller
public class MenuController extends ParentController{

	protected static Logger logger = Logger.getLogger(MenuController.class);

	@Autowired
	private MenuManager menuManager;

	@Autowired
	private MenuValidator menuValidator;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(this.menuValidator);
	}
	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
	public String create(@ModelAttribute("menu")@Valid Menu menu, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, menu);
			return "menu/create";
		}
		uiModel.asMap().clear();
		menuManager.save(menu);
		return "redirect:/master/menu/" + encodeUrlPathSegment(menu.getMenu_id().toString(), httpServletRequest);
	}

	@RequestMapping(params = "form", produces = "text/html")
	public String createForm(@RequestParam(value = "parent_id", required = false)Long parent_id,Model uiModel) {
		Menu menu=new Menu();
		menu.parent=parent_id;
		if(parent_id!=null)
		menu.urut=menuManager.selectMaxValue("urut", "menu", "parent = "+parent_id).longValue()+1l;
		populateEditForm(uiModel,menu );
		return "menu/create";
	}

	@RequestMapping(value = "/{menu_id}", produces = "text/html",method=RequestMethod.GET)
	public String show(@ModelAttribute("menu") Menu menu,@PathVariable("menu_id") Long menu_id, Model uiModel) {
		addDateTimeFormatPatterns(uiModel);
		Menu tmp=menuManager.get(menu_id);
		BeanUtils.copyProperties(tmp, menu);
//		uiModel.addAttribute("menu",menu );
		uiModel.addAttribute("itemId", menu_id);
		uiModel.addAttribute("parentlist", menuManager.selectDropDown("concat(REPEAT('___', level),nama)", "sys_menu_id", "menu", "f_aktif =1","sys_path"));
		return "menu/show";
	}

	@RequestMapping(produces = "text/html")
	public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size,@RequestParam(value = "search", required = false) String search, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
		if (page == null) {
			page=1;
		}

			int sizeNo = size == null ? 100 : size.intValue();
			final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
			uiModel.addAttribute("menuList",menuManager.selectPagingList(search,sortFieldName,sortOrder, firstResult, sizeNo) );
			float nrOfPages = (float) menuManager.selectPagingCount(search) / sizeNo;
			uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
		addDateTimeFormatPatterns(uiModel);
		return "menu/list";
	}

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
	public String update(@ModelAttribute("menu")@Valid Menu menu, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, menu);
			return "menu/update";
		}
		uiModel.asMap().clear();
		menuManager.save(menu);
		return "redirect:/master/menu/" + encodeUrlPathSegment(menu.getMenu_id().toString(), httpServletRequest);
	}

	@RequestMapping(value = "/{menu_id}", params = "form", produces = "text/html")
	public String updateForm(@PathVariable("menu_id") Long menu_id, Model uiModel) {
		populateEditForm(uiModel, menuManager.get(menu_id));
		return "menu/update";
	}

	@RequestMapping(value = "/{menu_id}", method = RequestMethod.DELETE, produces = "text/html")
	public String delete(@PathVariable("menu_id") Long menu_id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
		Menu menu = menuManager.get(menu_id);
		menuManager.remove(menu_id);
		uiModel.asMap().clear();
		uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
		uiModel.addAttribute("size", (size == null) ? "100" : size.toString());
		return "redirect:/master/menu";
	}
	void addDateTimeFormatPatterns(Model uiModel) {
		uiModel.addAttribute("menu_tgl_create_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
		uiModel.addAttribute("menu_tgl_create_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
	}
	void populateEditForm(Model uiModel, Menu menu) {
		uiModel.addAttribute("menu", menu);
		uiModel.addAttribute("parentlist", menuManager.selectDropDown("concat(REPEAT('___', level),nama)", "sys_menu_id", "menu", "f_aktif =1","sys_path"));
		addDateTimeFormatPatterns(uiModel);
	}
}
