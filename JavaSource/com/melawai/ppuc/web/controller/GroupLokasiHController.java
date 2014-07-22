package com.melawai.ppuc.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

import com.google.gson.Gson;
import com.melawai.ppuc.services.GroupLokasiHManager;
import com.melawai.ppuc.services.LokasiManager;
import com.melawai.ppuc.utils.CommonUtil;
import com.melawai.ppuc.web.controller.ParentController;
import com.melawai.ppuc.model.GroupLokasiD;
import com.melawai.ppuc.model.GroupLokasiH;
import com.melawai.ppuc.model.Lokasi;
import com.melawai.ppuc.web.validator.GroupLokasiHValidator;

@RequestMapping("/master/grouplokasih")
@Controller
public class GroupLokasiHController extends ParentController{

	protected static Logger logger = Logger.getLogger(GroupLokasiHController.class);

	@Autowired
	private GroupLokasiHManager grouplokasihManager;
	
	@Autowired
	private LokasiManager lokasiManager;

	@Autowired
	private GroupLokasiHValidator grouplokasihValidator;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(this.grouplokasihValidator);
	}
	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
	public String create(@Valid GroupLokasiH grouplokasih, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, grouplokasih);
			return "grouplokasih/create";
		}
		uiModel.asMap().clear();
		grouplokasihManager.save(grouplokasih);
		return "redirect:/master/grouplokasih/" + encodeUrlPathSegment(grouplokasih.getDivisi_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(grouplokasih.getSubdiv_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(grouplokasih.getGroup_lok().toString(), httpServletRequest);
	}

	@RequestMapping(params = "form", produces = "text/html")
	public String createForm(Model uiModel) {
		populateEditForm(uiModel, new GroupLokasiH());
		return "grouplokasih/create";
	}

	@RequestMapping(value = "/{divisi_kd}/{subdiv_kd}/{group_lok}", produces = "text/html")
	public String show(@PathVariable("divisi_kd") String divisi_kd, @PathVariable("subdiv_kd") String subdiv_kd, @PathVariable("group_lok") String group_lok, Model uiModel) {
		addDateTimeFormatPatterns(uiModel);
		uiModel.addAttribute("grouplokasih", grouplokasihManager.get(divisi_kd, subdiv_kd, group_lok));
		uiModel.addAttribute("itemId", divisi_kd+"/"+subdiv_kd+"/"+group_lok);
		return "grouplokasih/show";
	}

	@RequestMapping(produces = "text/html")
	public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size,@RequestParam(value = "search", required = false) String search, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
		if (page == null) {
			page=1;
		}

			int sizeNo = size == null ? 10 : size.intValue();
			final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
			uiModel.addAttribute("grouplokasihList",grouplokasihManager.selectPagingList(search,sortFieldName,sortOrder, firstResult, sizeNo) );
			float nrOfPages = (float) grouplokasihManager.selectPagingCount(search) / sizeNo;
			uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
		addDateTimeFormatPatterns(uiModel);
		return "grouplokasih/list";
	}

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
	public String update(@Valid GroupLokasiH grouplokasih, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, grouplokasih);
			return "grouplokasih/update";
		}
		uiModel.asMap().clear();
		grouplokasihManager.save(grouplokasih);
		return "redirect:/master/grouplokasih/" + encodeUrlPathSegment(grouplokasih.getDivisi_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(grouplokasih.getSubdiv_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(grouplokasih.getGroup_lok().toString(), httpServletRequest);
	}

	@RequestMapping(value = "/{divisi_kd}/{subdiv_kd}/{group_lok}", params = "form", produces = "text/html")
	public String updateForm(@PathVariable("divisi_kd") String divisi_kd, @PathVariable("subdiv_kd") String subdiv_kd, @PathVariable("group_lok") String group_lok, Model uiModel) {
		populateEditForm(uiModel, grouplokasihManager.get(divisi_kd, subdiv_kd, group_lok));
		return "grouplokasih/update";
	}

	@RequestMapping(value = "/{divisi_kd}/{subdiv_kd}/{group_lok}", method = RequestMethod.DELETE, produces = "text/html")
	public String delete(@PathVariable("divisi_kd") String divisi_kd, @PathVariable("subdiv_kd") String subdiv_kd, @PathVariable("group_lok") String group_lok, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
		GroupLokasiH grouplokasih = grouplokasihManager.get(divisi_kd, subdiv_kd, group_lok);
		grouplokasihManager.remove(divisi_kd, subdiv_kd, group_lok);
		uiModel.asMap().clear();
		uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
		uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
		return "redirect:/master/grouplokasih";
	}
	void addDateTimeFormatPatterns(Model uiModel) {
		uiModel.addAttribute("grouplokasih_tgl_create_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
	}
	void populateEditForm(Model uiModel, GroupLokasiH grouplokasih) {
		uiModel.addAttribute("grouplokasih", grouplokasih);
		addDateTimeFormatPatterns(uiModel);
	}
	
	/**
	 * JSON HELP
	 */
	
	@RequestMapping("/json/getData")
	public String jsonGrouplokasihGetData(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "divisi_kd", required = false, defaultValue="") String divisi_kd, @RequestParam(value = "subdiv_kd", required = false, defaultValue = "") String subdiv_kd,
			@RequestParam(value = "group_lok", required = false, defaultValue = "") String group_lok) throws IOException, ParseException {
		response.setContentType("application/json");

		GroupLokasiH groupLokasiH = grouplokasihManager.get(divisi_kd, subdiv_kd, group_lok);

		if (groupLokasiH != null) {

			if (!groupLokasiH.getGroupLokasiDList().isEmpty()) {
				String row = "";
				int rowNum = 1;

				List<Map> grouplokasidList = new ArrayList<Map>();
				for (GroupLokasiD groupLokasiD : groupLokasiH.getGroupLokasiDList()) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("idx", rowNum);
					map.put("lok_kd", groupLokasiD.lok_kd);
					map.put("lok_nm", groupLokasiD.lokasi.lok_nm);
					map.put("propinsi", groupLokasiD.lokasi.propinsi);
					map.put("kota", groupLokasiD.lokasi.kota);
					map.put("email", groupLokasiD.lokasi.email);
					grouplokasidList.add(map);
					rowNum++;
				}

				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", "" + groupLokasiH.getGroup_lok());
				map.put("grouplokasidList", grouplokasidList);


				response.setContentType("application/json");
				PrintWriter write = response.getWriter();
				Gson gson = new Gson();
				write.print(gson.toJson(map));
				write.close();
			} else {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", "" + groupLokasiH.getGroup_lok());
				map.put("grouplokasidList", "");

				response.setContentType("application/json");
				PrintWriter write = response.getWriter();
				Gson gson = new Gson();
				write.print(gson.toJson(map));
				write.close();
			}
		} else {
			Map<String, String> map = new HashMap<String, String>();
			map.put("failed", "<p>false</p>");
			response.setContentType("application/json");
			PrintWriter write = response.getWriter();
			Gson gson = new Gson();
			write.print(gson.toJson(map));
			write.close();
		}
		return null;

	}

	@RequestMapping("/json/addData")
	public String jsonGrouplokasihAddData(HttpServletRequest request, HttpServletResponse response,@RequestParam(value = "lok_kd", required = false, defaultValue = "") String lok_kd,@RequestParam(value = "idx", required = false, defaultValue = "")Integer[] idxList ) throws IOException, ParseException {
		response.setContentType("application/json");

		boolean isSame = false;
		int rowNum = 1;

		List<Map> grouplokasiDList = new ArrayList<Map>();
		if (idxList.length != 0) {

			for (int idx : idxList) {
				GroupLokasiD groupLokasiD = new GroupLokasiD(idx, ServletRequestUtils.getStringParameter(request, "lok_kd_" + idx, "0"), ServletRequestUtils.getStringParameter(request, "lok_nm_" + idx, ""), ServletRequestUtils.getStringParameter(request, "propinsi_" + idx, ""), ServletRequestUtils.getStringParameter(request, "kota_" + idx, ""), ServletRequestUtils.getStringParameter(request, "email_" + idx, ""));

				if (lok_kd.equals(groupLokasiD.lok_kd)) {
					isSame = true;
				}

				Map<String, Object> map = new HashMap<String, Object>();
				map.put("idx", rowNum);
				map.put("lok_kd", groupLokasiD.lok_kd);
				map.put("lok_nm", groupLokasiD.lokasi.lok_nm);
				map.put("propinsi", groupLokasiD.lokasi.propinsi);
				map.put("kota", groupLokasiD.lokasi.kota);
				map.put("email", groupLokasiD.lokasi.email);
				grouplokasiDList.add(map);
				rowNum++;
			}

		}

		if (!isSame) {
			Map<String, Object> map = new HashMap<String, Object>();
			Lokasi lokasi=lokasiManager.get(lok_kd, null, null, null);
			map.put("idx", rowNum);
			map.put("lok_kd", lokasi.lok_kd);
			map.put("lok_nm", lokasi.lok_nm);
			map.put("propinsi", lokasi.propinsi);
			map.put("kota", lokasi.kota);
			map.put("email", lokasi.email);
			grouplokasiDList.add(map);
		}

		response.setContentType("application/json");
		PrintWriter write = response.getWriter();
		Gson gson = new Gson();
		write.print(gson.toJson(grouplokasiDList));
		write.close();

		return null;
	}

	@RequestMapping("/json/removeData")
	public String jsonGrouplokasihRemoveData(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "id", required = false, defaultValue = "0") Integer id,@RequestParam(value = "idx", required = false, defaultValue = "")Integer[] idxList) throws IOException, ParseException {
		response.setContentType("application/json");

		
		int rowNum = 1;
		List<Map> grouplokasiDList = new ArrayList<Map>();

		if (idxList.length != 0) {
			for (int idx : idxList) {
				if (idx == id)
					continue;

				GroupLokasiD groupLokasiD = new GroupLokasiD(idx, ServletRequestUtils.getStringParameter(request, "lok_kd_" + idx, "0"), ServletRequestUtils.getStringParameter(request, "lok_nm_" + idx, ""), ServletRequestUtils.getStringParameter(request, "propinsi_" + idx, ""), ServletRequestUtils.getStringParameter(request, "kota_" + idx, ""), ServletRequestUtils.getStringParameter(request, "email_" + idx, ""));


				Map<String, Object> map = new HashMap<String, Object>();
				map.put("idx", rowNum);
				map.put("lok_kd", groupLokasiD.lok_kd);
				map.put("lok_nm", groupLokasiD.lokasi.lok_nm);
				map.put("propinsi", groupLokasiD.lokasi.propinsi);
				map.put("kota", groupLokasiD.lokasi.kota);
				map.put("email", groupLokasiD.lokasi.email);
				grouplokasiDList.add(map);
			}

		}

		response.setContentType("application/json");
		PrintWriter write = response.getWriter();
		Gson gson = new Gson();
		write.print(gson.toJson(grouplokasiDList));
		write.close();

		return null;
	}
}
