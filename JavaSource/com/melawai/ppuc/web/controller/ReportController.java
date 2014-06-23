package com.melawai.ppuc.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRCsvExporterParameter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.j2ee.servlets.ImageServlet;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.melawai.ppuc.model.User;
import com.melawai.ppuc.utils.CommonUtil;



/**
 * Report Controller
 *
 * @author Rudy
 * @since Apr 21, 2013 (9:41:40 AM)
 *
 */
@Controller
@RequestMapping("/report")
public class ReportController extends ParentController{

	@Autowired
	private BasicDataSource dbDataSource;

	private Connection connection;

	private Connection getConnection() {
		if(this.connection==null)
			try { this.connection = dbDataSource.getConnection(); }
			catch (SQLException e) { e.printStackTrace(); }
		return this.connection;
	}

	//@ModelAttribute pada deklarasi method berarti:
	//bisa lebih dari satu model attribute, bisa juga digunakan sebagai reference data
	@ModelAttribute("reff")
	public Map<String, Object> reff(){
		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("AllKategori", dbService.selectDropDown("id", "concat(inisial, ' - ', nama)", "lst_kategori", "active = 1", "nama"));
		return map;
	}

	/**
	 * Fungsi untuk generate report, dipanggil oleh seluruh report
	 * @param jenis
	 * @param params
	 * @param session
	 * @param request
	 * @param response
	 * @return
	 * @throws JRException
	 * @throws IOException
	 */
	private String generateReport(String jenis, Map params, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws JRException, IOException{
		ServletContext context = session.getServletContext();
		String format = (String) params.get("format");

		//Generate report
		JasperPrint jasperPrint = JasperFillManager.fillReport(
			context.getRealPath("/WEB-INF/classes/" + props.getProperty("dir.report") + "/" +
			props.getProperty("report." + jenis) + ".jasper"), //report path
			params, //report parameters
			getConnection() //connection object
			);

		//Put generated report into session
		session.setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jasperPrint);

		//Text File
		if(format.equalsIgnoreCase("txt")){
			JRCsvExporter exporter = new JRCsvExporter();

			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_WRITER, response.getWriter());
			//tambahan header khusus file CSV
			response.setHeader("Content-Disposition","attachment; filename=\"report.txt\";");

			exporter.exportReport();
			return null;

		//csv File
		}else if(format.equalsIgnoreCase("csv")){
				JRCsvExporter exporter = new JRCsvExporter();
				exporter.setParameter(JRCsvExporterParameter.FIELD_DELIMITER,",");
				exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
				exporter.setParameter(JRExporterParameter.OUTPUT_WRITER, response.getWriter());
				//tambahan header khusus file CSV
				response.setHeader("Content-Disposition","attachment; filename=\"report.csv\";");

				exporter.exportReport();
				return null;

			//HTML File
		}else if(format.equalsIgnoreCase("html")){
			JRHtmlExporter exporter = new JRHtmlExporter();

			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_WRITER, response.getWriter());
			//HTML Specific parameters nya
			exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, request.getContextPath() + "/jasper/image?image=");
			exporter.setParameter(JRHtmlExporterParameter.IGNORE_PAGE_MARGINS, true); //biar gak terlalu banyak white space
			exporter.setParameter(JRHtmlExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, true); //biar gak terlalu banyak white space
			exporter.setParameter(JRHtmlExporterParameter.BETWEEN_PAGES_HTML, ""); //biar tidak ada paging (khusus html)

			exporter.exportReport();
			return null;

		
		}else if(format.equalsIgnoreCase("htmlprint")){
			JRHtmlExporter exporter = new JRHtmlExporter();

			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_WRITER, response.getWriter());
			//HTML Specific parameters
			exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, request.getContextPath() + "/jasper/image?image=");
			exporter.setParameter(JRHtmlExporterParameter.IGNORE_PAGE_MARGINS, true); //biar gak terlalu banyak white space
			exporter.setParameter(JRHtmlExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, true); //biar gak terlalu banyak white space
			exporter.setParameter(JRHtmlExporterParameter.BETWEEN_PAGES_HTML, ""); //biar tidak ada paging (khusus html)
			exporter.setParameter(JRHtmlExporterParameter.HTML_HEADER, "<html><head>"
				+ "<script type=\"text/javascript\">window.print();</script><style media=\"screen\">.printing{display:block;}</style><style media=\"print\">.printing{display:none;}</style><style></style></head><body>"
				+ "");
			exporter.setParameter(JRHtmlExporterParameter.HTML_FOOTER, "<input type=\"button\" class=\"printing\" value=\"print\" onclick=\"window.print();\"/></body></html>");

			exporter.exportReport();
			return null;

		//format selain HTML dan TXT
		}else{
			return "redirect:/jasper/" + format; //redirect ke JasperReports Servlet sesuai format
		}
	}

	
	@RequestMapping("/test/{payment_id}/{format}")
	public String print_test(HttpSession session, HttpServletRequest request, HttpServletResponse response,@PathVariable Integer payment_id,@PathVariable String format) throws Exception{
		String jenisReport = "test";
		logger.debug("Halaman: REPORT " + jenisReport);
		
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("format",format);
		params.put("payment_id",payment_id);
		
		return generateReport(jenisReport, params, session, request, response);
	}

	

		

}