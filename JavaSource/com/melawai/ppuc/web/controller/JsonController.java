package com.melawai.ppuc.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.melawai.ppuc.model.DropDown;
import com.melawai.ppuc.model.GroupLokasiH;
import com.melawai.ppuc.model.User;
import com.melawai.ppuc.services.GroupLokasiHManager;
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
		List<DropDown> result = new ArrayList<DropDown>();

		String param = ServletRequestUtils.getStringParameter(request, "param", "").trim();
		String param2 = ServletRequestUtils.getStringParameter(request, "param2", "").trim();
		String param3 = ServletRequestUtils.getStringParameter(request, "param3", "").trim();

		if (tipe.equals("kota")) {
			if (param.equals("")) {
				result = baseService.selectDropDown("kota", "kota", "kota", null, "kota");
			} else {
				result = baseService.selectDropDown("kota", "kota", "kota", "propinsi = '" + param + "'", "kota");
				if(result.isEmpty()){
					result = baseService.selectDropDown("propinsi", "propinsi", "propinsi", "propinsi = '" + param + "'", "propinsi");
				}
			}
		} else if (tipe.equals("kota2")) {
			if (param.equals("")) {
				result = baseService.selectDropDown("DISTINCT concat(a.kota )","kota",  "lokasi  a left join group_lokasi_d b ON a.lok_kd = b.lok_kd ", "1=1  group by kota", "kota");
			} else {
				result = baseService.selectDropDown("DISTINCT concat(a.kota )","kota",  "lokasi  a left join group_lokasi_d b ON a.lok_kd = b.lok_kd ", "propinsi = '" + param + "' group by kota", "kota");
			}
		}else if (tipe.equals("kota3")) {
			if(!param.equals("")) param = " and b.group_lok = '"+param+"' ";
			
			if(!param2.equals("")) param2 = " and a.propinsi = '"+param2+"' ";
			
			result = baseService.selectDropDown("DISTINCT concat( a.kota)","kota",  "lokasi a left join group_lokasi_d b ON a.lok_kd = b.lok_kd ", "1 = 1 "+param+" "+param2+" group by a.kota", "a.kota");
			
		} else if (tipe.equals("propinsi")) {
			if (param.equals("")) {
				result = baseService.selectDropDown("propinsi", "propinsi", "propinsi", null, "propinsi");
			} else {
				result = baseService.selectDropDown("propinsi", "propinsi", "kota", "kota = '" + param + "'", "propinsi");
			}
		} else if (tipe.equals("propinsi2")) {
			if (param.equals("")) {
				result = baseService.selectDropDown("DISTINCT concat( propinsi )","propinsi",  "lokasi  a left join group_lokasi_d b ON a.lok_kd = b.lok_kd ", "1 = 1 group by propinsi", "propinsi");
			} else {
				result = baseService.selectDropDown("DISTINCT concat( propinsi )","propinsi",  "lokasi  a left join group_lokasi_d b ON a.lok_kd = b.lok_kd ", "b.group_lok='"+ param +"' group by propinsi", "propinsi");
			}
		} else if (tipe.equals("subdivisi")) {
			if (param.equals("")) {
//				result = baseService.selectDropDown("subdiv_kd", "subdiv_nm", "subdivisi", null, "subdiv_nm");
			} else {
				result = baseService.selectDropDown("subdiv_kd", "subdiv_nm", "subdivisi", "divisi_kd = '" + param + "'", "subdiv_nm");
			}
		} else if (tipe.equals("subdivisi2")) {
			if (param.equals("")) {
//				result = baseService.selectDropDown("concat(divisi_kd, '.', subdiv_kd)", "subdiv_nm", "subdivisi", null, "subdiv_nm");
			} else {
				result = baseService.selectDropDown("concat(divisi_kd, '.', subdiv_kd)", "subdiv_nm", "subdivisi", "divisi_kd = '" + param + "'", "subdiv_nm");
			}
		} else if (tipe.equals("departmen")) {
			if (param.equals("")) {
//				result = baseService.selectDropDown("concat(divisi_kd, '.', subdiv_kd, '.', dept_kd)", "dept_nm", "departmen", null, "dept_nm");
			} else {
				result = baseService.selectDropDown("concat(divisi_kd, '.', subdiv_kd, '.', dept_kd)", "dept_nm", "departmen", "concat(divisi_kd, '.', subdiv_kd) = '" + param + "'", "dept_nm");
			}
		} else if (tipe.equals("lokasi")) {
			if (param.equals("")) {
//				result = baseService.selectDropDown("concat(divisi_kd, '.', subdiv_kd, '.', dept_kd, '.', lok_kd)", "lok_nm", "lokasi", null, "lok_nm");
			} else {
				result = baseService.selectDropDown("concat(divisi_kd, '.', subdiv_kd, '.', dept_kd, '.', lok_kd)", "lok_nm", "lokasi", "concat(divisi_kd, '.', subdiv_kd, '.', dept_kd) = '" + param + "'", "lok_nm");
			}

		} else if (tipe.equals("lokasi2")) {
			if (param.equals("")) {
//				result = baseService.selectDropDown("concat(divisi_kd, '.', subdiv_kd, '.', lok_kd)", "lok_nm", "lokasi", null, "lok_nm");
			} else {
				result = baseService.selectDropDown("concat(divisi_kd, '.', subdiv_kd, '.', lok_kd)", "lok_nm", "lokasi", "concat(divisi_kd, '.', subdiv_kd) = '" + param + "'", "lok_nm");
			}

		} else  if (tipe.equals("lokasi3")) {
			if (param.equals("")) {
				result = baseService.selectDropDown("DISTINCT concat(a.lok_kd)","lok_nm",  "lokasi a left join group_lokasi_d b ON a.lok_kd = b.lok_kd ", "1 = 1  group by a.lok_nm", "a.lok_nm");
			} else {
				result = baseService.selectDropDown("DISTINCT concat( a.lok_kd)","lok_nm",  "lokasi a left join group_lokasi_d b ON a.lok_kd = b.lok_kd ", "a.kota = '"+ param +"' group by a.lok_nm", "a.lok_nm");
			}

		}  else  if (tipe.equals("lokasi4")) {
			if(!param.equals("")) param = " and a.propinsi = '"+param+"' ";
			
			if(!param2.equals("")) param2 = " and a.kota = '"+param2+"' ";
			
			result = baseService.selectDropDown("DISTINCT concat(a.lok_kd)","lok_nm",  "lokasi a left join group_lokasi_d b ON a.lok_kd = b.lok_kd ", "1 = 1 "+param+" "+param2+" group by a.lok_nm", "a.lok_nm");

		} else  if (tipe.equals("lokasi5")) {
			if(!param.equals("")) param = " and b.group_lok = '"+param+"' ";
			
			if(!param2.equals("")) param2 = " and a.propinsi = '"+param2+"' ";
			
			if(!param3.equals("")) param3 = " and a.kota = '"+param3+"' ";
			
			result = baseService.selectDropDown("DISTINCT concat( a.lok_kd)","lok_nm",  "lokasi a left join group_lokasi_d b ON a.lok_kd = b.lok_kd ", "1 = 1 "+param+" "+param2+" "+param3+" group by a.lok_nm", "a.lok_nm");
			

		} else if (tipe.equals("detailbiaya")) {
			if (param.equals("")) {
//				result = baseService.selectDropDown("kd_biaya", "kd_biaya", "detail_biaya", null, "kd_biaya");
			} else {
				result = baseService.selectDropDown("kd_biaya", "kd_biaya", "detail_biaya", "kd_group = '" + param + "'", "kd_biaya");
			}

		} else if (tipe.equals("detailbiaya2")) {
			if (param.equals("")) {
				result = baseService.selectDropDown("DISTINCT kd_biaya","kd_biaya",  "detail_biaya", "1=1 group by kd_biaya", "kd_biaya");
			} else {
				result = baseService.selectDropDown("DISTINCT kd_biaya","kd_biaya",  "detail_biaya", "kd_group = '"+ param +"' group by kd_biaya", "kd_biaya");
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
