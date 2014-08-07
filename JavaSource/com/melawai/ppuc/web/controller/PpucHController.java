	package com.melawai.ppuc.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
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

import com.google.gson.Gson;
import com.melawai.ppuc.model.HakApprove;
import com.melawai.ppuc.model.PpucD;
import com.melawai.ppuc.model.PpucH;
import com.melawai.ppuc.model.UserDivisi;
import com.melawai.ppuc.services.GroupBiayaManager;
import com.melawai.ppuc.services.PpucHManager;
import com.melawai.ppuc.services.UserDivisiManager;
import com.melawai.ppuc.utils.CommonUtil;
import com.melawai.ppuc.utils.Utils;
import com.melawai.ppuc.web.validator.PpucHValidator;

@RequestMapping("/trans/ppuch")
@Controller
public class PpucHController extends ParentController{

	protected static Logger logger = Logger.getLogger(PpucHController.class);

	@Autowired
	private PpucHManager ppuchManager;
	
	@Autowired
	private UserDivisiManager userDivisiManager;
	
	@Autowired
	private PpucHValidator ppucHValidator;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(ppucHValidator);
	}
	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
	public String create(@Valid PpucH ppuch, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, ppuch);
			return "ppuch/create";
		}
		uiModel.asMap().clear();
		ppuchManager.save(ppuch);
		return "redirect:/master/ppuch/" + encodeUrlPathSegment(ppuch.getDivisi_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(ppuch.getSubdiv_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(ppuch.getDept_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(ppuch.getLok_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(ppuch.getNo_ppuc().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(ppuch.getTgl_ppuc().toString(), httpServletRequest);
	}

	@RequestMapping(params = "form", produces = "text/html")
	public String createForm(Model uiModel) {
		PpucH ppuch= new PpucH();
		UserDivisi userdivisi=userDivisiManager.getDivisiNSubdivUser(CommonUtil.getCurrentUserId());
		ppuch.divisi_kd = userdivisi.getDivisi_kd();
		ppuch.subdiv_kd = userdivisi.getSubdiv_kd();
		
		populateEditForm(uiModel,ppuch);
		return "ppuch/create";
	}

	@RequestMapping(value = "/{divisi_kd}/{subdiv_kd}/{dept_kd}/{lok_kd}/{no_ppuc}/{tgl_ppuc}", produces = "text/html")
	public String show(@PathVariable("divisi_kd") String divisi_kd, @PathVariable("subdiv_kd") String subdiv_kd, @PathVariable("dept_kd") String dept_kd, @PathVariable("lok_kd") String lok_kd, @PathVariable("no_ppuc") String no_ppuc, @PathVariable("tgl_ppuc") Date tgl_ppuc, Model uiModel) {
		addDateTimeFormatPatterns(uiModel);
		uiModel.addAttribute("ppuch", ppuchManager.get(divisi_kd, subdiv_kd, dept_kd, lok_kd, no_ppuc, tgl_ppuc));
		uiModel.addAttribute("itemId", divisi_kd+"/"+subdiv_kd+"/"+dept_kd+"/"+lok_kd+"/"+no_ppuc+"/"+tgl_ppuc);
		return "ppuch/show";
	}

	@RequestMapping(produces = "text/html")
	public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size,@RequestParam(value = "search", required = false) String search, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
		if (page == null) {
			page=1;
		}

			int sizeNo = size == null ? 10 : size.intValue();
			final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
			uiModel.addAttribute("ppuchList",ppuchManager.selectPagingList(search,sortFieldName,sortOrder, firstResult, sizeNo) );
			float nrOfPages = (float) ppuchManager.selectPagingCount(search) / sizeNo;
			uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
		addDateTimeFormatPatterns(uiModel);
		return "ppuch/list";
	}

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
	public String update(@Valid PpucH ppuch, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, ppuch);
			return "ppuch/update";
		}
		uiModel.asMap().clear();
		ppuchManager.save(ppuch);
		return "redirect:/master/ppuch/" + encodeUrlPathSegment(ppuch.getDivisi_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(ppuch.getSubdiv_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(ppuch.getDept_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(ppuch.getLok_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(ppuch.getNo_ppuc().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(ppuch.getTgl_ppuc().toString(), httpServletRequest);
	}

	@RequestMapping(value = "/{divisi_kd}/{subdiv_kd}/{dept_kd}/{lok_kd}/{no_ppuc}/{tgl_ppuc}", params = "form", produces = "text/html")
	public String updateForm(@PathVariable("divisi_kd") String divisi_kd, @PathVariable("subdiv_kd") String subdiv_kd, @PathVariable("dept_kd") String dept_kd, @PathVariable("lok_kd") String lok_kd, @PathVariable("no_ppuc") String no_ppuc, @PathVariable("tgl_ppuc") Date tgl_ppuc, Model uiModel) {
		populateEditForm(uiModel, ppuchManager.get(divisi_kd, subdiv_kd, dept_kd, lok_kd, no_ppuc, tgl_ppuc));
		return "ppuch/update";
	}

	@RequestMapping(value = "/{divisi_kd}/{subdiv_kd}/{dept_kd}/{lok_kd}/{no_ppuc}/{tgl_ppuc}", method = RequestMethod.DELETE, produces = "text/html")
	public String delete(@PathVariable("divisi_kd") String divisi_kd, @PathVariable("subdiv_kd") String subdiv_kd, @PathVariable("dept_kd") String dept_kd, @PathVariable("lok_kd") String lok_kd, @PathVariable("no_ppuc") String no_ppuc, @PathVariable("tgl_ppuc") Date tgl_ppuc, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
		ppuchManager.remove(divisi_kd, subdiv_kd, dept_kd, lok_kd, no_ppuc, tgl_ppuc);
		uiModel.asMap().clear();
		uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
		uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
		return "redirect:/master/ppuch";
	}
	void addDateTimeFormatPatterns(Model uiModel) {
		uiModel.addAttribute("ppuch_tgl_ppuc_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
		uiModel.addAttribute("ppuch_tgl_create_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
		uiModel.addAttribute("ppuch_sys_tgl_confirm_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
		uiModel.addAttribute("ppuch_sys_tgl_approve_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
		uiModel.addAttribute("ppuch_sys_tgl_realisasi_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
		uiModel.addAttribute("ppuch_sys_tgl_conf_real_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
		uiModel.addAttribute("ppuch_sys_tgl_conf_oc_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
		uiModel.addAttribute("ppuch_sys_tgl_batal_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
	}
	void populateEditForm(Model uiModel, PpucH ppuch) {
		uiModel.addAttribute("ppuch", ppuch);
		addDateTimeFormatPatterns(uiModel);
		populateEditFormAdditional( uiModel,  ppuch);
	}
	
	void populateEditFormAdditional(Model uiModel, PpucH ppuch){
		
		uiModel.addAttribute("divisiList", baseService.selectDropDown("divisi_nm", "divisi_kd", "divisi", null, "divisi_nm"));
		
		//FIXME: group sesuai divisi
		uiModel.addAttribute("groupbiayaList", baseService.selectDropDown("nm_group", "kd_group", "group_biaya", null, "nm_group"));

		
		if (ppuchManager.selectCountTable("subdivisi", "divisi_kd = '" + ppuch.divisi_kd + "'")>0)
			uiModel.addAttribute("subdivList", baseService.selectDropDown("subdiv_nm", "concat(divisi_kd, '.', subdiv_kd)", "subdivisi", "divisi_kd = '" + ppuch.divisi_kd + "'", "subdiv_nm"));
		else
			uiModel.addAttribute("subdivList", baseService.selectDropDown("subdiv_nm", "concat(divisi_kd, '.', subdiv_kd)", "subdivisi", null, "subdiv_nm"));

		if (ppuchManager.selectCountTable("departmen", " divisi_kd = '" + ppuch.divisi_kd + "' and subdiv_kd = '" + ppuch.subdiv_kd + "'")>0)
			uiModel.addAttribute("deptList", baseService.selectDropDown("dept_nm","concat(divisi_kd, '.', subdiv_kd, '.', dept_kd)", "departmen", " divisi_kd = '" + ppuch.divisi_kd + "' and subdiv_kd = '" + ppuch.subdiv_kd + "'", "dept_nm"));
		else
			uiModel.addAttribute("deptList", baseService.selectDropDown("dept_nm","concat(divisi_kd, '.', subdiv_kd, '.', dept_kd)",  "departmen", null, "dept_nm"));

		if (ppuchManager.selectCountTable("lokasi", " divisi_kd = '" + ppuch.divisi_kd + "' and subdiv_kd = '" + ppuch.subdiv_kd + "' and dept_kd = '" + ppuch.dept_kd + "'")>0)
			uiModel.addAttribute("lokList", baseService.selectDropDown("lok_nm","concat(divisi_kd, '.', subdiv_kd, '.', dept_kd, '.', lok_kd)", "lokasi", " divisi_kd = '" + ppuch.divisi_kd + "' and subdiv_kd = '" + ppuch.subdiv_kd + "' and dept_kd = '" + ppuch.dept_kd + "'", "lok_nm"));
		else
			uiModel.addAttribute("lokList", baseService.selectDropDown("lok_nm","concat(divisi_kd, '.', subdiv_kd, '.', dept_kd, '.', lok_kd)",  "lokasi", null, "lok_nm"));
		
		if (ppuchManager.selectCountTable("detail_biaya", "kd_group ='" + ppuch.kd_group + "'") > 0)
			uiModel.addAttribute("detailbiayaList", baseService.selectDropDown("kd_biaya", "kd_biaya", "detail_biaya", "kd_group = '" + ppuch.kd_group + "' and f_used <> 1", "kd_biaya"));
		else
			uiModel.addAttribute("detailbiayaList", baseService.selectDropDown("kd_biaya", "kd_biaya", "detail_biaya", null, "kd_biaya"));
		
	}
	
	
	/**
	 * JSON HELP
	 */
	
	@RequestMapping("/json/getData")
	public String jsonGrouplokasihGetData(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam(value = "no_batch", required = true, defaultValue="") String no_batch) throws IOException, ParseException {
		response.setContentType("application/json");

		List<PpucH> lsPPuch=ppuchManager.get(no_batch);


			if (!lsPPuch.isEmpty()) {
				String row = "";
				int rowNum = 1;

				List<Map> ppuchList = new ArrayList<Map>();
				for (PpucH ppuch : lsPPuch) {
					for(PpucD ppucd:ppuch.getPpucds()){
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("idx", rowNum);
						map.put("ppuc_no", ppucd.lok_kd);
						map.put("tgl_ppuc", Utils.convertDateToString( ppucd.tgl_ppuc,"dd/MM/yyyy"));
						map.put("kd_group", ppucd.kd_group);
						map.put("nm_group", ppucd.groupBiaya.nm_group);
						map.put("kd_biaya", ppucd.kd_biaya);
						map.put("qty", ppucd.qty);
						if(ppucd.harga != null)map.put("harga", Utils.formatNumber(props.getProperty("number.curr.format"), ppucd.harga));
						if(ppucd.total != null)map.put("total", Utils.formatNumber(props.getProperty("number.curr.format"), ppucd.total));
						map.put("keterangan", ppucd.keterangan);
						ppuchList.add(map);
						rowNum++;
					}
				}

				Map<String, Object> map = new HashMap<String, Object>();
				map.put("no_batch", "" + no_batch);
				map.put("ppuchList", ppuchList);


				response.setContentType("application/json");
				PrintWriter write = response.getWriter();
				Gson gson = new Gson();
				write.print(gson.toJson(map));
				write.close();
			} else {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("no_batch", "" + no_batch);
				map.put("grouplokasidList", "");

				response.setContentType("application/json");
				PrintWriter write = response.getWriter();
				Gson gson = new Gson();
				write.print(gson.toJson(map));
				write.close();
			}
		return null;

	}

	@RequestMapping("/json/addData")
	public String jsonPPUCHAddData(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "divisi_kd", required = false, defaultValue = "") String divisi_kd,
			@RequestParam(value = "subdiv_kd", required = false, defaultValue = "") String subdiv_kd,
			@RequestParam(value = "dept_kd", required = false, defaultValue = "") String dept_kd,
			@RequestParam(value = "lok_kd", required = false, defaultValue = "") String lok_kd,
			@RequestParam(value = "kd_group", required = false, defaultValue = "") String kd_group,
			@RequestParam(value = "kd_biaya", required = false, defaultValue = "") String kd_biaya,
			@RequestParam(value = "qty", required = false, defaultValue = "") String qty,
			@RequestParam(value = "harga", required = false, defaultValue = "") String harga,
			@RequestParam(value = "total", required = false, defaultValue = "") String total,
			@RequestParam(value = "keterangan", required = false, defaultValue = "") String keterangan,
			@RequestParam(value = "idx", required = false, defaultValue = "")Integer[] idxList ) throws IOException, ParseException {
		response.setContentType("application/json");

		boolean isSame = false;
		int rowNum = 1;

		List<Map> ppuchList = new ArrayList<Map>();
		if (idxList.length != 0) {

			for (int idx : idxList) {
				PpucD ppucd= new PpucD(divisi_kd, subdiv_kd, dept_kd, lok_kd, ServletRequestUtils.getStringParameter(request, "no_ppuc_" + idx, null), Utils.convertStringToDate(ServletRequestUtils.getStringParameter(request, "tgl_ppuc_" + idx, null), "dd/MM/yyyy"), ServletRequestUtils.getStringParameter(request, "kd_group_" + idx, null), ServletRequestUtils.getStringParameter(request, "kd_biaya_" + idx,null), CommonUtil.convertToLong(ServletRequestUtils.getStringParameter(request, "qty_" + idx, null)), CommonUtil.convertCurrencyToDouble(ServletRequestUtils.getStringParameter(request, "harga_" + idx, null)), CommonUtil.convertCurrencyToDouble(ServletRequestUtils.getStringParameter(request, "total_" + idx, null)), ServletRequestUtils.getStringParameter(request, "keterangan_" + idx, null));

				if (kd_biaya.equals(ppucd.kd_biaya)) {
					isSame = true;
				}

				Map<String, Object> map = new HashMap<String, Object>();
				map.put("idx", rowNum);
				map.put("ppuc_no", ppucd.lok_kd);
				map.put("tgl_ppuc", Utils.convertDateToString( ppucd.tgl_ppuc,"dd/MM/yyyy"));
				map.put("kd_group", ppucd.kd_group);
				map.put("nm_group", ServletRequestUtils.getStringParameter(request, "nm_group_" + idx, ""));
				map.put("kd_biaya", ppucd.kd_biaya);
				map.put("qty", ppucd.qty);
				if(ppucd.harga != null)map.put("harga", Utils.formatNumber(props.getProperty("number.curr.format"), ppucd.harga));
				if(ppucd.harga != null && ppucd.qty != null)map.put("total", Utils.formatNumber(props.getProperty("number.curr.format"), ppucd.harga * ppucd.qty));
				map.put("keterangan", ppucd.keterangan);
				ppuchList.add(map);
				rowNum++;
			}

		}

		if (!isSame) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("idx", rowNum);
			map.put("ppuc_no", "");
			map.put("tgl_ppuc", "");
			map.put("kd_group", kd_group);
			map.put("nm_group", ppuchManager.getGroupBiaya(kd_group));
			map.put("kd_biaya", kd_biaya);
			map.put("qty", qty);
			map.put("harga", harga);
			map.put("total", total);
			map.put("keterangan", keterangan);
			ppuchList.add(map);
		}

		response.setContentType("application/json");
		PrintWriter write = response.getWriter();
		Gson gson = new Gson();
		write.print(gson.toJson(ppuchList));
		write.close();

		return null;
	}

	@RequestMapping("/json/removeData")
	public String jsonPPUCHRemoveData(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam(value = "divisi_kd", required = false, defaultValue = "") String divisi_kd,
			@RequestParam(value = "subdiv_kd", required = false, defaultValue = "") String subdiv_kd,
			@RequestParam(value = "dept_kd", required = false, defaultValue = "") String dept_kd,
			@RequestParam(value = "lok_kd", required = false, defaultValue = "") String lok_kd,
			@RequestParam(value = "kd_group", required = false, defaultValue = "") String kd_group,
			@RequestParam(value = "kd_biaya", required = false, defaultValue = "") String kd_biaya,
			@RequestParam(value = "id", required = false, defaultValue = "0") Integer id,
			@RequestParam(value = "idx", required = false, defaultValue = "")Integer[] idxList) throws IOException, ParseException {
		response.setContentType("application/json");

		
		int rowNum = 1;
		List<Map> ppuchList = new ArrayList<Map>();

		if (idxList.length != 0) {
			for (int idx : idxList) {
				if (idx == id)
					continue;

				PpucD ppucd= new PpucD(divisi_kd, subdiv_kd, dept_kd, lok_kd, ServletRequestUtils.getStringParameter(request, "no_ppuc_" + idx, null), Utils.convertStringToDate(ServletRequestUtils.getStringParameter(request, "tgl_ppuc_" + idx, null), "dd/MM/yyyy"), ServletRequestUtils.getStringParameter(request, "kd_group_" + idx, null), ServletRequestUtils.getStringParameter(request, "kd_biaya_" + idx,null), CommonUtil.convertToLong(ServletRequestUtils.getStringParameter(request, "qty_" + idx, null)), CommonUtil.convertCurrencyToDouble(ServletRequestUtils.getStringParameter(request, "harga_" + idx, null)), CommonUtil.convertCurrencyToDouble(ServletRequestUtils.getStringParameter(request, "total_" + idx, null)), ServletRequestUtils.getStringParameter(request, "keterangan_" + idx, null));


				Map<String, Object> map = new HashMap<String, Object>();
				map.put("idx", rowNum);
				map.put("idx", rowNum);
				map.put("ppuc_no", ppucd.lok_kd);
				map.put("tgl_ppuc", Utils.convertDateToString( ppucd.tgl_ppuc,"dd/MM/yyyy"));
				map.put("kd_group", ppucd.kd_group);
				map.put("nm_group", ServletRequestUtils.getStringParameter(request, "nm_group_" + idx, ""));
				map.put("kd_biaya", ppucd.kd_biaya);
				map.put("qty", ppucd.qty);
				if(ppucd.harga != null)map.put("harga", Utils.formatNumber(props.getProperty("number.curr.format"), ppucd.harga));
				if(ppucd.harga != null && ppucd.qty != null)map.put("total", Utils.formatNumber(props.getProperty("number.curr.format"), ppucd.harga * ppucd.qty));
				map.put("keterangan", ppucd.keterangan);
				ppuchList.add(map);
			}

		}

		response.setContentType("application/json");
		PrintWriter write = response.getWriter();
		Gson gson = new Gson();
		write.print(gson.toJson(ppuchList));
		write.close();

		return null;
	}
}
