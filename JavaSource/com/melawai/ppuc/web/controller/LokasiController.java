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
import org.springframework.beans.propertyeditors.CustomBooleanEditor;
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
import com.melawai.ppuc.model.Lokasi;
import com.melawai.ppuc.model.Upload;
import com.melawai.ppuc.services.LokasiManager;
import com.melawai.ppuc.utils.CommonUtil;
import com.melawai.ppuc.utils.Utils;
import com.melawai.ppuc.web.validator.LokasiValidator;

@RequestMapping("/master/lokasi")
@Controller
public class LokasiController extends ParentController {

	protected static Logger logger = Logger.getLogger(LokasiController.class);

	@Autowired
	private LokasiManager lokasiManager;

	@Autowired
	private LokasiValidator lokasiValidator;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(this.lokasiValidator);

	}

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
	public String create(@Valid Lokasi lokasi, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		// tambahan validasi khusus
		if (lokasiManager.exists(lokasi.lok_kd, lokasi.dept_kd, lokasi.subdiv_kd, lokasi.divisi_kd)) {
			bindingResult.rejectValue("lok_kd", "duplicate", new String[] { "LOKASI KD : " + lokasi.lok_kd + " | DIVISI KD : " + lokasi.divisi_kd + " | SUBDIVISI KD : " + lokasi.subdiv_kd
					+ " | DEPARTMEN KD : " + lokasi.dept_kd + ", " }, null);
		}

		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, lokasi);
			return "lokasi/create";
		}
		uiModel.asMap().clear();
		lokasiManager.save(lokasi);
		return "redirect:/master/lokasi/" + encodeUrlPathSegment(lokasi.getLok_kd().toString(), httpServletRequest) + "/" + encodeUrlPathSegment(lokasi.getDept_kd().toString(), httpServletRequest)
				+ "/" + encodeUrlPathSegment(lokasi.getSubdiv_kd().toString(), httpServletRequest) + "/" + encodeUrlPathSegment(lokasi.getDivisi_kd().toString(), httpServletRequest);
	}

	@RequestMapping(params = "form", produces = "text/html")
	public String createForm(Model uiModel) {
		populateEditForm(uiModel, new Lokasi());
		return "lokasi/create";
	}

	@RequestMapping(value = "/{lok_kd}/{dept_kd}/{subdiv_kd}/{divisi_kd}", produces = "text/html")
	public String show(@PathVariable("lok_kd") String lok_kd, @PathVariable("dept_kd") String dept_kd, @PathVariable("subdiv_kd") String subdiv_kd, @PathVariable("divisi_kd") String divisi_kd,
			Model uiModel) {
		addDateTimeFormatPatterns(uiModel);
		uiModel.addAttribute("lokasi", lokasiManager.get(lok_kd, dept_kd, subdiv_kd, divisi_kd));
		uiModel.addAttribute("itemId", lok_kd + "/" + dept_kd + "/" + subdiv_kd + "/" + divisi_kd);
		return "lokasi/show";
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
		uiModel.addAttribute("lokasiList", lokasiManager.selectPagingList(search, sortFieldName, sortOrder, firstResult, sizeNo));
		float nrOfPages = (float) lokasiManager.selectPagingCount(search) / sizeNo;
		uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
		addDateTimeFormatPatterns(uiModel);
		return "lokasi/list";
	}

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
	public String update(@Valid Lokasi lokasi, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, lokasi);
			return "lokasi/update";
		}
		uiModel.asMap().clear();
		lokasiManager.save(lokasi);
		return "redirect:/master/lokasi/" + encodeUrlPathSegment(lokasi.getLok_kd().toString(), httpServletRequest) + "/" + encodeUrlPathSegment(lokasi.getDept_kd().toString(), httpServletRequest)
				+ "/" + encodeUrlPathSegment(lokasi.getSubdiv_kd().toString(), httpServletRequest) + "/" + encodeUrlPathSegment(lokasi.getDivisi_kd().toString(), httpServletRequest);
	}

	@RequestMapping(value = "/{lok_kd}/{dept_kd}/{subdiv_kd}/{divisi_kd}", params = "form", produces = "text/html")
	public String updateForm(@PathVariable("lok_kd") String lok_kd, @PathVariable("dept_kd") String dept_kd, @PathVariable("subdiv_kd") String subdiv_kd, @PathVariable("divisi_kd") String divisi_kd,
			Model uiModel) {
		populateEditForm(uiModel, lokasiManager.get(lok_kd, dept_kd, subdiv_kd, divisi_kd));
		return "lokasi/update";
	}

	@RequestMapping(value = "/{lok_kd}/{dept_kd}/{subdiv_kd}/{divisi_kd}", method = RequestMethod.DELETE, produces = "text/html")
	public String delete(@PathVariable("lok_kd") String lok_kd, @PathVariable("dept_kd") String dept_kd, @PathVariable("subdiv_kd") String subdiv_kd, @PathVariable("divisi_kd") String divisi_kd,
			@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
		lokasiManager.remove(lok_kd, dept_kd, subdiv_kd, divisi_kd);
		;
		uiModel.asMap().clear();
		uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
		uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
		return "redirect:/master/lokasi";
	}

	void addDateTimeFormatPatterns(Model uiModel) {
		uiModel.addAttribute("lokasi_tgl_tutup_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
		uiModel.addAttribute("lokasi_sys_tgl_update_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
		uiModel.addAttribute("lokasi_sys_tgl_create_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
	}

	void populateEditForm(Model uiModel, Lokasi lokasi) {
		uiModel.addAttribute("lokasi", lokasi);
		
		uiModel.addAttribute("divisiList", baseService.selectDropDown("divisi_nm", "divisi_kd", "divisi", null, "divisi_nm"));

		if (!Utils.isEmpty(lokasi.divisi_kd))
			uiModel.addAttribute("subdivList", baseService.selectDropDown("subdiv_nm", "concat(divisi_kd, '.', subdiv_kd)", "subdivisi", "divisi_kd = '" + lokasi.divisi_kd + "'", "subdiv_nm"));
		else
			uiModel.addAttribute("subdivList", baseService.selectDropDown("subdiv_nm", "concat(divisi_kd, '.', subdiv_kd)", "subdivisi", null, "subdiv_nm"));

		uiModel.addAttribute("divisiList", baseService.selectDropDown("divisi_nm", "divisi_kd", "divisi", null, "divisi_nm"));

		if (!Utils.isEmpty(lokasi.divisi_kd)&&!Utils.isEmpty(lokasi.subdiv_kd))
			uiModel.addAttribute("deptList", baseService.selectDropDown("dept_nm","concat(divisi_kd, '.', subdiv_kd, '.', dept_kd)", "departmen", " divisi_kd = '" + lokasi.divisi_kd + "' and subdiv_kd = '" + lokasi.subdiv_kd + "'", "dept_nm"));
		else
			uiModel.addAttribute("deptList", baseService.selectDropDown("dept_nm","concat(divisi_kd, '.', subdiv_kd, '.', dept_kd)",  "departmen", null, "dept_nm"));

		
		addDateTimeFormatPatterns(uiModel);
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST, produces = "text/html")
	public String upload(Lokasi lokasi, BindingResult bindingResult, Model uiModel, RedirectAttributes ra, HttpServletRequest httpServletRequest) {
		BindException errors = new BindException(bindingResult);

		DataBinder binder = new DataBinder(lokasi.upload);
		binder.setValidator(this.uploadValidator);
		binder.validate();

		List<String> errorMessage = new ArrayList<String>();
		int baris = 1;
		List<Lokasi> lsLokasi = new ArrayList<Lokasi>();

		if (binder.getBindingResult().hasErrors()) {
			errors.rejectValue("upload.uploadFile", null, Utils.errorBinderToList(binder.getBindingResult(), messageSource).get(0));
		} else {
			Date sekarang = new Date();
			// nama file yang ingin disimpan
			String filename = "lokasi" + Utils.convertDateToString(sekarang, "ddMMyyyyHHmmss") + ".csv";
			// jumlah coloumn data yang ingin di proses
			Integer colomnSize = 9;

			// buat directory di server bila belum ada
			String path = props.getProperty("dir.lokasi.temp") + "\\";
			File directory = new File(path);
			if (!directory.exists())
				directory.mkdirs();

			// buat file di directory yg sudah dibuat diatas, dan diisi dengan
			// data yang sama dgn yg diupload

			File file = null;
			// copy file ke server
			try {
				file = new File(path + filename);
				FileUtils.writeByteArrayToFile(file, lokasi.upload.uploadFile.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
				errorMessage.add(" (filename= " + lokasi.upload.getOriginalFilename() + ")\n" + Utils.errorExtract(e));
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
						if (baris >= lokasi.upload.importStartLine) {
							colCount2 = nextLine.length;

							if (colCount2 < colCount1) { // bila ada kolom yg
															// error, kasih
															// pesan
								errorMessage.add(" (filename= " + lokasi.upload.getOriginalFilename() + ")\n Jumlah kolom pada baris ke-" + baris + " tidak sama dengan requirement");
								break;
							} else { // selain itu tambahkan daftar insert
								Lokasi tempLokasi = new Lokasi();

								try {
									tempLokasi = new Lokasi(Utils.nvl(nextLine[0]), Utils.nvl(nextLine[1]), Utils.nvl(nextLine[2]), Utils.nvl(nextLine[3]), Utils.nvl(nextLine[4]),
											Utils.nvl(nextLine[5]), Utils.nvl(nextLine[6]), Utils.nvl(nextLine[7]), Utils.convertImportDate(Utils.nvl(nextLine[8]), props));
								} catch (Exception e) {
									e.printStackTrace();
									errorMessage.add("(filename= " + lokasi.upload.getOriginalFilename() + ")\n Format kolom salah pada baris ke-" + baris + " :\n" + e.getMessage());
									break;
								}

								// validasi

								DataBinder binder2 = new DataBinder(tempLokasi);
								binder2.setValidator(this.lokasiValidator);
								binder2.validate();

								if (binder2.getBindingResult().hasErrors()) {
									errorMessage.add(" (filename= " + lokasi.upload.getOriginalFilename() + ")\n pada baris ke-" + baris + "<br/>");
									errorMessage.addAll(Utils.errorBinderToList(binder2.getBindingResult(), messageSource));
									errors.rejectValue("upload.uploadFile", null, Utils.errorBinderToList(binder2.getBindingResult(), messageSource).get(0));
									break;
								} else {// kalau tidak ada error add ke list
									lsLokasi.add(tempLokasi);
								}

							}

						}
						baris++;
					}

				} catch (FileNotFoundException e) {
					e.printStackTrace();
					errorMessage.add(" (filename= " + lokasi.upload.getOriginalFilename() + ") File tidak dapat dibackup.\n");
				} catch (IOException e) {
					e.printStackTrace();
					errorMessage.add(" (filename= " + lokasi.upload.getOriginalFilename() + ") File gagal diproses");
				} catch (DataAccessException e) {
					e.printStackTrace();
					errorMessage.add(" (filename= " + lokasi.upload.getOriginalFilename() + ") Gagal di simpan ke database");
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
			populateEditForm(uiModel, lokasi);
			lokasiManager.audittrail(Audittrail.Activity.EXIM, Audittrail.EximType.FAILED, lokasi.getClass().getSimpleName(), lokasi.upload.getOriginalFilename(),
					CommonUtil.getIpAddr(httpServletRequest), errorMessage.toString(), CommonUtil.getCurrentUser(), null);
			return "lokasi/upload";
		}

		uiModel.asMap().clear();
		lokasiManager.audittrail(Audittrail.Activity.EXIM, Audittrail.EximType.SUCCESS, lokasi.getClass().getSimpleName(), lokasi.upload.getOriginalFilename(),
				CommonUtil.getIpAddr(httpServletRequest), "IMPORT DATA SUCCESS", CommonUtil.getCurrentUser(), null);
		lokasiManager.save(lsLokasi);
		String pesan = "File [" + lokasi.upload.getOriginalFilename() + "] berhasil diupload, jumlah data yang diproses = " + (baris - 1);
		ra.addFlashAttribute("pesan", pesan);

		return "redirect:/master/lokasi/";
	}

	@RequestMapping(value = "/upload", params = "form", produces = "text/html")
	public String upload(Model uiModel) {
		Lokasi lokasi = new Lokasi();
		lokasi.upload = new Upload(2, 5000000, true, "*.csv,*.txt");
		populateEditForm(uiModel, lokasi);
		return "lokasi/upload";
	}
}
