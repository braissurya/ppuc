package com.melawai.ppuc.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.melawai.ppuc.model.DropDown;
import com.melawai.ppuc.model.User;
import com.melawai.ppuc.utils.CommonUtil;

/**
 * JSONC CONTROLLER
 * 
 * controller untuk semua request json
 * 
 * @author Bertho
 * 
 */
@Controller
public class JsonController extends ParentController {

	@RequestMapping("/json/{tipe}")
	public String main(HttpServletRequest request, HttpServletResponse response, @PathVariable String tipe) throws IOException, ParseException {
		response.setContentType("application/json");
		User currentUser = CommonUtil.getCurrentUser();
		List<DropDown> result = new ArrayList<DropDown>();
		
		String param=ServletRequestUtils.getStringParameter(request, "param", "");

		if (tipe.equals("kota")) {
			if (param.equals("")) {
				result = baseService.selectDropDown("kota", "kota", "kota", null, "kota");
			} else {
				result = baseService.selectDropDown("kota", "kota", "kota", "propinsi = '" + param + "'", "kota");
			}
		} else if (tipe.equals("propinsi")) {
			if (param.equals("")) {
				result = baseService.selectDropDown("propinsi", "propinsi", "propinsi", null, "propinsi");
			} else {
				result = baseService.selectDropDown("propinsi", "propinsi", "kota", "kota = '" + param + "'", "propinsi");
			}
		} else if (tipe.equals("subdivisi")) {
			if (param.equals("")) {
				result = baseService.selectDropDown("subdiv_kd", "subdiv_nm", "subdivisi", null, "subdiv_nm");
			} else {
				result = baseService.selectDropDown("subdiv_kd", "subdiv_nm", "subdivisi", "divisi_kd = '" + param + "'", "subdiv_nm");
			}
		} else if (tipe.equals("subdivisi2")) {
			if (param.equals("")) {
				result = baseService.selectDropDown("concat(divisi_kd, '.', subdiv_kd)", "subdiv_nm", "subdivisi", null, "subdiv_nm");
			} else {
				result = baseService.selectDropDown("concat(divisi_kd, '.', subdiv_kd)", "subdiv_nm", "subdivisi", "divisi_kd = '" + param + "'", "subdiv_nm");
			}
		} else if (tipe.equals("departmen")) {
			if (param.equals("")) {
				result = baseService.selectDropDown("concat(divisi_kd, '.', subdiv_kd, '.', dept_kd)","dept_nm",  "departmen", null, "dept_nm");
			} else {
				result = baseService.selectDropDown("concat(divisi_kd, '.', subdiv_kd, '.', dept_kd)", "dept_nm", "departmen", "concat(divisi_kd, '.', subdiv_kd) = '" + param + "'", "dept_nm");
			}
		} else if (tipe.equals("lokasi")) {
			if (param.equals("")) {
				result = baseService.selectDropDown("concat(divisi_kd, '.', subdiv_kd, '.', dept_kd, '.', lok_kd)", "lok_nm", "lokasi", null, "lok_nm");
			} else {
				result = baseService.selectDropDown("concat(divisi_kd, '.', subdiv_kd, '.', dept_kd, '.', lok_kd)", "lok_nm", "lokasi", "concat(divisi_kd, '.', subdiv_kd, '.', dept_kd) = '" + param + "'", "lok_nm");
			}

		}

		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		out.print(gson.toJson(result));
		out.close();

		return null;
	}

}
