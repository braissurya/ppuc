package com.melawai.ppuc.web.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import au.com.bytecode.opencsv.CSVReader;

import com.melawai.ppuc.model.Audittrail;
import com.melawai.ppuc.model.Departmen;
import com.melawai.ppuc.model.Upload;
import com.melawai.ppuc.services.DepartmenManager;
import com.melawai.ppuc.utils.CommonUtil;
import com.melawai.ppuc.utils.Utils;
import com.melawai.ppuc.web.validator.DepartmenValidator;

@RequestMapping("/master/departmen")
@Controller
public class DepartmenController extends ParentController {

	protected static Logger logger = Logger.getLogger(DepartmenController.class);

	@Autowired
	private DepartmenManager departmenManager;

	@Autowired
	private DepartmenValidator departmenValidator;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(this.departmenValidator);
	}

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
	public String create(@Valid Departmen departmen, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		// tambahan validasi khusus
		if (departmenManager.exists(departmen.dept_kd, departmen.subdiv_kd, departmen.divisi_kd)) {
			bindingResult.rejectValue("dept_kd", "duplicate", new String[] { "DIVISI KD : " + departmen.divisi_kd + " | SUBDIVISI KD : " + departmen.subdiv_kd + " | DEPARTMEN KD : "
					+ departmen.dept_kd + ", " }, null);
		}

		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, departmen);
			return "departmen/create";
		}
		uiModel.asMap().clear();
		departmenManager.save(departmen);
		return "redirect:/master/departmen/" + encodeUrlPathSegment(departmen.getDept_kd().toString(), httpServletRequest) + "/"
				+ encodeUrlPathSegment(departmen.getSubdiv_kd().toString(), httpServletRequest) + "/" + encodeUrlPathSegment(departmen.getDivisi_kd().toString(), httpServletRequest);
	}

	@RequestMapping(params = "form", produces = "text/html")
	public String createForm(Model uiModel) {
		populateEditForm(uiModel, new Departmen());
		return "departmen/create";
	}

	@RequestMapping(value = "/{dept_kd}/{subdiv_kd}/{divisi_kd}", produces = "text/html")
	public String show(@PathVariable("dept_kd") String dept_kd, @PathVariable("subdiv_kd") String subdiv_kd, @PathVariable("divisi_kd") String divisi_kd, Model uiModel) {
		addDateTimeFormatPatterns(uiModel);
		uiModel.addAttribute("departmen", departmenManager.get(dept_kd, subdiv_kd, divisi_kd));
		uiModel.addAttribute("itemId", dept_kd + "/" + subdiv_kd + "/" + divisi_kd);
		return "departmen/show";
	}

	@RequestMapping(produces = "text/html")
	public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size,
			@RequestParam(value = "search", required = false) String search, @RequestParam(value = "sortFieldName", required = false) String sortFieldName,
			@RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
		if (page == null) {
			page = 1;
		}

		int sizeNo = size == null ? 10 : size.intValue();
		final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
		uiModel.addAttribute("departmenList", departmenManager.selectPagingList(search, sortFieldName, sortOrder, firstResult, sizeNo));
		float nrOfPages = (float) departmenManager.selectPagingCount(search) / sizeNo;
		uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
		addDateTimeFormatPatterns(uiModel);
		return "departmen/list";
	}

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
	public String update(@Valid Departmen departmen, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, departmen);
			return "departmen/update";
		}
		uiModel.asMap().clear();
		departmenManager.save(departmen);
		return "redirect:/master/departmen/" + encodeUrlPathSegment(departmen.getDept_kd().toString(), httpServletRequest) + "/"
				+ encodeUrlPathSegment(departmen.getSubdiv_kd().toString(), httpServletRequest) + "/" + encodeUrlPathSegment(departmen.getDivisi_kd().toString(), httpServletRequest);
	}

	@RequestMapping(value = "/{dept_kd}/{subdiv_kd}/{divisi_kd}", params = "form", produces = "text/html")
	public String updateForm(@PathVariable("dept_kd") String dept_kd, @PathVariable("subdiv_kd") String subdiv_kd, @PathVariable("divisi_kd") String divisi_kd, Model uiModel) {
		populateEditForm(uiModel, departmenManager.get(dept_kd, subdiv_kd, divisi_kd));
		return "departmen/update";
	}

	@RequestMapping(value = "/{dept_kd}/{subdiv_kd}/{divisi_kd}", method = RequestMethod.DELETE, produces = "text/html")
	public String delete(@PathVariable("dept_kd") String dept_kd, @PathVariable("subdiv_kd") String subdiv_kd, @PathVariable("divisi_kd") String divisi_kd,
			@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
		departmenManager.remove(dept_kd, subdiv_kd, divisi_kd);
		;
		uiModel.asMap().clear();
		uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
		uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
		return "redirect:/master/departmen";
	}

	void addDateTimeFormatPatterns(Model uiModel) {
		uiModel.addAttribute("departmen_sys_tgl_update_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
		uiModel.addAttribute("departmen_sys_tgl_create_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
	}

	void populateEditForm(Model uiModel, Departmen departmen) {
		uiModel.addAttribute("departmen", departmen);
		uiModel.addAttribute("divisiList", baseService.selectDropDown("divisi_nm", "divisi_kd", "divisi", null, "divisi_nm"));

		if (!Utils.isEmpty(departmen.divisi_kd))
			uiModel.addAttribute("subdivList", baseService.selectDropDown("subdiv_nm", "concat(divisi_kd, '.', subdiv_kd)", "subdivisi", "divisi_kd = '" + departmen.divisi_kd + "'", "subdiv_nm"));
		else
			uiModel.addAttribute("subdivList", baseService.selectDropDown("subdiv_nm", "concat(divisi_kd, '.', subdiv_kd)", "subdivisi", null, "subdiv_nm"));

		addDateTimeFormatPatterns(uiModel);
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST, produces = "text/html")
	public String upload(Departmen departmen, BindingResult bindingResult, Model uiModel, RedirectAttributes ra, HttpServletRequest httpServletRequest) {
		BindException errors = new BindException(bindingResult);

		DataBinder binder = new DataBinder(departmen.upload);
		binder.setValidator(this.uploadValidator);
		binder.validate();

		List<String> errorMessage = new ArrayList<String>();
		int baris = 1;
		List<Departmen> lsdepartmen = new ArrayList<Departmen>();

		if (binder.getBindingResult().hasErrors()) {
			errors.rejectValue("upload.uploadFile", null, Utils.errorBinderToList(binder.getBindingResult(), messageSource).get(0));
		} else {
			Date sekarang = new Date();
			// nama file yang ingin disimpan
			String filename = "departmen" + Utils.convertDateToString(sekarang, "ddMMyyyyHHmmss") + ".csv";
			// jumlah coloumn data yang ingin di proses
			Integer colomnSize = 4;

			// buat directory di server bila belum ada
			String path = props.getProperty("dir.departmen.temp") + "\\";
			File directory = new File(path);
			if (!directory.exists())
				directory.mkdirs();

			// buat file di directory yg sudah dibuat diatas, dan diisi dengan
			// data yang sama dgn yg diupload

			File file = null;
			// copy file ke server
			try {
				file = new File(path + filename);
				FileUtils.writeByteArrayToFile(file, departmen.upload.uploadFile.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
				errorMessage.add(" (filename= " + departmen.upload.getOriginalFilename() + ")\n" + Utils.errorExtract(e));
			}

			// baca filenya
			CSVReader reader = null;

			if (errorMessage.isEmpty()) {
				try {

					reader = new CSVReader(new FileReader(file), ',');

					int colCount1 = colomnSize; // jumlah kolom harus 2
												// divisi_kd divisi_nm
					int colCount2 = 0;
					String[] nextLine;

					while ((nextLine = reader.readNext()) != null) {
						if (baris >= departmen.upload.importStartLine) {
							colCount2 = nextLine.length;

							if (colCount2 < colCount1) { // bila ada kolom yg
															// error, kasih
															// pesan
								errorMessage.add(" (filename= " + departmen.upload.getOriginalFilename() + ")\n Jumlah kolom pada baris ke-" + baris + " tidak sama dengan requirement");
								break;
							} else { // selain itu tambahkan daftar insert
								Departmen tempDepartmen = new Departmen();

								try {
									tempDepartmen = new Departmen(Utils.isEmpty(nextLine[0]) ? null : nextLine[0].trim(), Utils.isEmpty(nextLine[1]) ? null : nextLine[1].trim(),
											Utils.isEmpty(nextLine[2]) ? null : nextLine[2].trim(), Utils.isEmpty(nextLine[3]) ? null : nextLine[3].trim());
								} catch (Exception e) {
									e.printStackTrace();
									errorMessage.add("(filename= " + departmen.upload.getOriginalFilename() + ")\n Format kolom salah pada baris ke-" + baris + " :\n" + e.getMessage());
									break;
								}

								// validasi

								DataBinder binder2 = new DataBinder(tempDepartmen);
								binder2.setValidator(this.departmenValidator);
								binder2.validate();

								if (binder2.getBindingResult().hasErrors()) {
									errorMessage.add(" (filename= " + departmen.upload.getOriginalFilename() + ")\n pada baris ke-" + baris + "<br/>");
									errorMessage.addAll(Utils.errorBinderToList(binder2.getBindingResult(), messageSource));
									errors.rejectValue("upload.uploadFile", null, Utils.errorBinderToList(binder2.getBindingResult(), messageSource).get(0));
									break;
								} else {// kalau tidak ada error add ke list
									lsdepartmen.add(tempDepartmen);
								}

							}

						}
						baris++;
					}

				} catch (FileNotFoundException e) {
					e.printStackTrace();
					errorMessage.add(" (filename= " + departmen.upload.getOriginalFilename() + ") File tidak dapat dibackup.\n");
				} catch (IOException e) {
					e.printStackTrace();
					errorMessage.add(" (filename= " + departmen.upload.getOriginalFilename() + ") File gagal diproses");
				} catch (DataAccessException e) {
					e.printStackTrace();
					errorMessage.add(" (filename= " + departmen.upload.getOriginalFilename() + ") Gagal di simpan ke database");
				}

			}

			// tutup file nya
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		if (errors.hasErrors() || !errorMessage.isEmpty()) {
			errorMessage.addAll(Utils.errorBinderToList(errors, messageSource));
			uiModel.addAttribute("errorList", errorMessage);
			errors.rejectValue("upload.uploadFile", null, Utils.errorListToString(errorMessage));
			populateEditForm(uiModel, departmen);
			departmenManager.audittrail(Audittrail.Activity.EXIM, Audittrail.EximType.FAILED, departmen.getClass().getSimpleName(), departmen.upload.getOriginalFilename(),
					CommonUtil.getIpAddr(httpServletRequest), errorMessage.toString(), CommonUtil.getCurrentUser(), null);
			return "departmen/upload";
		}

		uiModel.asMap().clear();
		departmenManager.audittrail(Audittrail.Activity.EXIM, Audittrail.EximType.SUCCESS, departmen.getClass().getSimpleName(), departmen.upload.getOriginalFilename(),
				CommonUtil.getIpAddr(httpServletRequest), "IMPORT DATA SUCCESS", CommonUtil.getCurrentUser(), null);
		departmenManager.save(lsdepartmen);
		String pesan = "File [" + departmen.upload.getOriginalFilename() + "] berhasil diupload, jumlah data yang diproses = " + (baris - 1);
		ra.addFlashAttribute("pesan", pesan);

		return "redirect:/master/departmen/";
	}

	@RequestMapping(value = "/upload", params = "form", produces = "text/html")
	public String upload(Model uiModel) {
		Departmen departmen = new Departmen();
		departmen.upload = new Upload(2, 5000000, true, "*.csv,*.txt");
		populateEditForm(uiModel, departmen);
		return "departmen/upload";
	}
}
