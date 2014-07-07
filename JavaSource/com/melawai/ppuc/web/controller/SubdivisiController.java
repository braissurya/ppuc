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
import com.melawai.ppuc.model.Subdivisi;
import com.melawai.ppuc.model.Upload;
import com.melawai.ppuc.services.SubdivisiManager;
import com.melawai.ppuc.utils.CommonUtil;
import com.melawai.ppuc.utils.Utils;
import com.melawai.ppuc.web.validator.SubdivisiValidator;

@RequestMapping("/master/subdivisi")
@Controller
public class SubdivisiController extends ParentController {

	protected static Logger logger = Logger.getLogger(SubdivisiController.class);

	@Autowired
	private SubdivisiManager subdivisiManager;

	@Autowired
	protected SubdivisiValidator subdivisiValidator;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(this.subdivisiValidator);
	}

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
	public String create(@Valid Subdivisi subdivisi, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		// tambahan validasi khusus
		if (subdivisiManager.exists(subdivisi.subdiv_kd, subdivisi.divisi_kd)) {
			bindingResult.rejectValue("subdiv_kd", "duplicate", new String[] { "DIVISI KD : " + subdivisi.divisi_kd + " | SUBDIVISI KD : " + subdivisi.subdiv_kd + ", " }, null);
		}

		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, subdivisi);
			return "subdivisi/create";
		}
		uiModel.asMap().clear();
		subdivisiManager.save(subdivisi);
		return "redirect:/master/subdivisi/" + encodeUrlPathSegment(subdivisi.getSubdiv_kd().toString(), httpServletRequest) + "/"
				+ encodeUrlPathSegment(subdivisi.getDivisi_kd().toString(), httpServletRequest);
	}

	@RequestMapping(params = "form", produces = "text/html")
	public String createForm(Model uiModel) {
		populateEditForm(uiModel, new Subdivisi());
		return "subdivisi/create";
	}

	@RequestMapping(value = "/{subdiv_kd}/{divisi_kd}", produces = "text/html")
	public String show(@PathVariable("subdiv_kd") String subdiv_kd, @PathVariable("divisi_kd") String divisi_kd, Model uiModel) {
		addDateTimeFormatPatterns(uiModel);
		uiModel.addAttribute("subdivisi", subdivisiManager.get(subdiv_kd, divisi_kd));
		uiModel.addAttribute("itemId", subdiv_kd + "/" + divisi_kd);
		return "subdivisi/show";
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
		uiModel.addAttribute("subdivisiList", subdivisiManager.selectPagingList(search, sortFieldName, sortOrder, firstResult, sizeNo));
		float nrOfPages = (float) subdivisiManager.selectPagingCount(search) / sizeNo;
		uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
		addDateTimeFormatPatterns(uiModel);
		return "subdivisi/list";
	}

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
	public String update(@Valid Subdivisi subdivisi, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, subdivisi);
			return "subdivisi/update";
		}
		uiModel.asMap().clear();
		subdivisiManager.save(subdivisi);
		return "redirect:/master/subdivisi/" + encodeUrlPathSegment(subdivisi.getSubdiv_kd().toString(), httpServletRequest) + "/"
				+ encodeUrlPathSegment(subdivisi.getDivisi_kd().toString(), httpServletRequest);
	}

	@RequestMapping(value = "/{subdiv_kd}/{divisi_kd}", params = "form", produces = "text/html")
	public String updateForm(@PathVariable("subdiv_kd") String subdiv_kd, @PathVariable("divisi_kd") String divisi_kd, Model uiModel) {
		populateEditForm(uiModel, subdivisiManager.get(subdiv_kd, divisi_kd));
		return "subdivisi/update";
	}

	@RequestMapping(value = "/{subdiv_kd}/{divisi_kd}", method = RequestMethod.DELETE, produces = "text/html")
	public String delete(@PathVariable("subdiv_kd") String subdiv_kd, @PathVariable("divisi_kd") String divisi_kd, @RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size, Model uiModel) {
		subdivisiManager.remove(subdiv_kd, divisi_kd);
		uiModel.asMap().clear();
		uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
		uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
		return "redirect:/master/subdivisi";
	}

	void addDateTimeFormatPatterns(Model uiModel) {
		uiModel.addAttribute("subdivisi_sys_tgl_update_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
		uiModel.addAttribute("subdivisi_sys_tgl_create_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
	}

	void populateEditForm(Model uiModel, Subdivisi subdivisi) {
		uiModel.addAttribute("subdivisi", subdivisi);
		uiModel.addAttribute("divisiList", baseService.selectDropDown("divisi_nm", "divisi_kd", "divisi", null, "divisi_nm"));
		addDateTimeFormatPatterns(uiModel);
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST, produces = "text/html")
	public String upload(Subdivisi subdivisi, BindingResult bindingResult, Model uiModel, RedirectAttributes ra, HttpServletRequest httpServletRequest) {
		BindException errors = new BindException(bindingResult);

		DataBinder binder = new DataBinder(subdivisi.upload);
		binder.setValidator(this.uploadValidator);
		binder.validate();

		List<String> errorMessage = new ArrayList<String>();
		int baris = 1;
		List<Subdivisi> lssubdivisi = new ArrayList<Subdivisi>();

		if (binder.getBindingResult().hasErrors()) {
			errors.rejectValue("upload.uploadFile", null, Utils.errorBinderToList(binder.getBindingResult(), messageSource).get(0));
		} else {
			Date sekarang = subdivisiManager.selectSysdate();
			// nama file yang ingin disimpan
			String filename = "subdivisi" + Utils.convertDateToString(sekarang, "ddMMyyyyHHmmss") + ".csv";
			// jumlah coloumn data yang ingin di proses
			Integer colomnSize = 3;

			// buat directory di server bila belum ada
			String path = props.getProperty("dir.subdivisi.temp") + "\\";
			File directory = new File(path);
			if (!directory.exists())
				directory.mkdirs();

			// buat file di directory yg sudah dibuat diatas, dan diisi dengan
			// data yang sama dgn yg diupload

			File file = null;
			// copy file ke server
			try {
				file = new File(path + filename);
				FileUtils.writeByteArrayToFile(file, subdivisi.upload.uploadFile.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
				errorMessage.add(" (filename= " + subdivisi.upload.getOriginalFilename() + ")\n" + Utils.errorExtract(e));
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
						if (baris >= subdivisi.upload.importStartLine) {
							colCount2 = nextLine.length;

							if (colCount2 < colCount1) { // bila ada kolom yg
															// error, kasih
															// pesan
								errorMessage.add(" (filename= " + subdivisi.upload.getOriginalFilename() + ")\n Jumlah kolom pada baris ke-" + baris + " tidak sama dengan requirement");
								break;
							} else { // selain itu tambahkan daftar insert
								Subdivisi tempSubdivisi = new Subdivisi();

								try {
									tempSubdivisi = new Subdivisi(Utils.isEmpty(nextLine[0]) ? null : nextLine[0].trim(), Utils.isEmpty(nextLine[1]) ? null : nextLine[1].trim(),
											Utils.isEmpty(nextLine[2]) ? null : nextLine[2].trim());
								} catch (Exception e) {
									e.printStackTrace();
									errorMessage.add("(filename= " + subdivisi.upload.getOriginalFilename() + ")\n Format kolom salah pada baris ke-" + baris + " :\n" + e.getMessage());
									break;
								}

								// validasi

								DataBinder binder2 = new DataBinder(tempSubdivisi);
								binder2.setValidator(this.subdivisiValidator);
								binder2.validate();

								if (binder2.getBindingResult().hasErrors()) {
									errorMessage.add(" (filename= " + subdivisi.upload.getOriginalFilename() + ")\n pada baris ke-" + baris + "<br/>");
									errorMessage.addAll(Utils.errorBinderToList(binder.getBindingResult(), messageSource));
									errors.rejectValue("upload.uploadFile", null, Utils.errorBinderToList(binder2.getBindingResult(), messageSource).get(0));
									break;
								} else {// kalau tidak ada error add ke list
									lssubdivisi.add(tempSubdivisi);
								}

							}

						}
						baris++;
					}

				} catch (FileNotFoundException e) {
					e.printStackTrace();
					errorMessage.add(" (filename= " + subdivisi.upload.getOriginalFilename() + ") File tidak dapat dibackup.\n");
				} catch (IOException e) {
					e.printStackTrace();
					errorMessage.add(" (filename= " + subdivisi.upload.getOriginalFilename() + ") File gagal diproses");
				} catch (DataAccessException e) {
					e.printStackTrace();
					errorMessage.add(" (filename= " + subdivisi.upload.getOriginalFilename() + ") Gagal di simpan ke database");
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
			errors.rejectValue("upload.uploadFile", null, Utils.errorListToString(errorMessage));
			uiModel.addAttribute("errorList", errorMessage);
			populateEditForm(uiModel, subdivisi);
			subdivisiManager.audittrail(Audittrail.Activity.EXIM, Audittrail.EximType.FAILED, subdivisi.getClass().getSimpleName(), subdivisi.upload.getOriginalFilename(),
					CommonUtil.getIpAddr(httpServletRequest), errorMessage.toString(), CommonUtil.getCurrentUser(), null);
			return "subdivisi/upload";
		}

		uiModel.asMap().clear();
		subdivisiManager.audittrail(Audittrail.Activity.EXIM, Audittrail.EximType.SUCCESS, subdivisi.getClass().getSimpleName(), subdivisi.upload.getOriginalFilename(),
				CommonUtil.getIpAddr(httpServletRequest), "IMPORT DATA SUCCESS", CommonUtil.getCurrentUser(), null);
		subdivisiManager.save(lssubdivisi);
		String pesan = "File [" + subdivisi.upload.getOriginalFilename() + "] berhasil diupload, jumlah data yang diproses = " + (baris - 1);
		ra.addFlashAttribute("pesan", pesan);

		return "redirect:/master/subdivisi/";
	}

	@RequestMapping(value = "/upload", params = "form", produces = "text/html")
	public String upload(Model uiModel) {
		Subdivisi subdivisi = new Subdivisi();
		subdivisi.upload = new Upload(2, 5000000, true, "*.csv,*.txt");
		populateEditForm(uiModel, subdivisi);
		return "subdivisi/upload";
	}
}
