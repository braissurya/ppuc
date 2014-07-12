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
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import au.com.bytecode.opencsv.CSVReader;

import com.melawai.ppuc.model.Audittrail;
import com.melawai.ppuc.model.Departmen;
import com.melawai.ppuc.model.Divisi;
import com.melawai.ppuc.model.Upload;
import com.melawai.ppuc.services.DivisiManager;
import com.melawai.ppuc.utils.CommonUtil;
import com.melawai.ppuc.utils.Utils;
import com.melawai.ppuc.web.validator.DivisiValidator;

@RequestMapping("/master/divisi")
@Controller
public class DivisiController extends ParentController {

	protected static Logger logger = Logger.getLogger(DivisiController.class);

	@Autowired
	private DivisiManager divisiManager;

	@Autowired
	protected DivisiValidator divisiValidator;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(this.divisiValidator);
	}

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
	public String create(@ModelAttribute("divisi")@Valid Divisi divisi, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {

		// tambahan validasi khusus
		if (divisiManager.exists(divisi.divisi_kd)) {
			bindingResult.rejectValue("divisi_kd", "duplicate", new String[] { "DIVISI KD : " + divisi.divisi_kd + ", " }, null);
		}

		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, divisi);
			return "divisi/create";
		}
		uiModel.asMap().clear();
		divisiManager.save(divisi);
		return "redirect:/master/divisi/" + encodeUrlPathSegment(divisi.getDivisi_kd().toString(), httpServletRequest);
	}

	@RequestMapping(params = "form", produces = "text/html")
	public String createForm(Model uiModel) {
		populateEditForm(uiModel, new Divisi());
		return "divisi/create";
	}

	@RequestMapping(value = "/{divisi_kd}", produces = "text/html")
	public String show(@PathVariable("divisi_kd") String divisi_kd, Model uiModel) {
		populateEditForm(uiModel, new Divisi());
		uiModel.addAttribute("divisi", divisiManager.get(divisi_kd));
		uiModel.addAttribute("itemId", divisi_kd);
		return "divisi/show";
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
		uiModel.addAttribute("divisiList", divisiManager.selectPagingList(search, sortFieldName, sortOrder, firstResult, sizeNo));
		float nrOfPages = (float) divisiManager.selectPagingCount(search) / sizeNo;
		uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
		addDateTimeFormatPatterns(uiModel);
		return "divisi/list";
	}

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
	public String update(@ModelAttribute("divisi")@Valid Divisi divisi, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, divisi);
			return "divisi/update";
		}
		uiModel.asMap().clear();
		divisiManager.save(divisi);
		return "redirect:/master/divisi/" + encodeUrlPathSegment(divisi.getDivisi_kd().toString(), httpServletRequest);
	}

	@RequestMapping(value = "/{divisi_kd}", params = "form", produces = "text/html")
	public String updateForm(@PathVariable("divisi_kd") String divisi_kd, Model uiModel) {
		populateEditForm(uiModel, divisiManager.get(divisi_kd));
		return "divisi/update";
	}

	@RequestMapping(value = "/{divisi_kd}", method = RequestMethod.DELETE, produces = "text/html")
	public String delete(@PathVariable("divisi_kd") String divisi_kd, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size,
			Model uiModel) {
		String pesan=messageSource.getMessage("entity_success", new String[]{"Delete Divisi : "+divisi_kd+","}, LocaleContextHolder.getLocale());
		if(!divisiManager.exists(divisi_kd)){
			pesan="Divisi "+divisi_kd+" tidak ditemukan";
			messageSource.getMessage("entity_not_found", new String[]{"Divisi : "+divisi_kd+","}, LocaleContextHolder.getLocale());
		}else if(divisiManager.selectCountTable("subdivisi", "divisi_kd='"+divisi_kd+"'")>0){
			pesan=messageSource.getMessage("entity_used_by", new String[]{"Divisi : "+divisi_kd+",","Master Subdivisi"}, LocaleContextHolder.getLocale());
		}else{
			divisiManager.remove(divisi_kd);
		}
		uiModel.asMap().clear();
		uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
		uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
		uiModel.addAttribute("pesan", pesan);
		return "redirect:/master/divisi";
	}

	void addDateTimeFormatPatterns(Model uiModel) {
		uiModel.addAttribute("divisi_sys_tgl_update_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
		uiModel.addAttribute("divisi_sys_tgl_create_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
	}

	void populateEditForm(Model uiModel, Divisi divisi) {
		uiModel.addAttribute("divisi", divisi);
		addDateTimeFormatPatterns(uiModel);
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST, produces = "text/html")
	public String upload(Divisi divisi, BindingResult bindingResult, Model uiModel, RedirectAttributes ra, HttpServletRequest httpServletRequest) {
		BindException errors = new BindException(bindingResult);

		DataBinder binder = new DataBinder(divisi.upload);
		binder.setValidator(this.uploadValidator);
		binder.validate();

		List<String> errorMessage = new ArrayList<String>();
		int baris = 1;
		List<Divisi> lsDivisi = new ArrayList<Divisi>();

		if (binder.getBindingResult().hasErrors()) {
			errors.rejectValue("upload.uploadFile", null, Utils.errorBinderToList(binder.getBindingResult(), messageSource).get(0));
		} else {
			Date sekarang = divisiManager.selectSysdate();
			// nama file yang ingin disimpan
			String filename = "divisi" + Utils.convertDateToString(sekarang, "ddMMyyyyHHmmss") + ".csv";
			// jumlah coloumn data yang ingin di proses
			Integer colomnSize = 2;

			// buat directory di server bila belum ada
			String path = props.getProperty("dir.divisi.temp") + "\\";
			File directory = new File(path);
			if (!directory.exists())
				directory.mkdirs();

			// buat file di directory yg sudah dibuat diatas, dan diisi dengan
			// data yang sama dgn yg diupload

			File file = null;
			// copy file ke server
			try {
				file = new File(path + filename);
				FileUtils.writeByteArrayToFile(file, divisi.upload.uploadFile.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
				errorMessage.add(" (filename= " + divisi.upload.uploadFile.getOriginalFilename() + ")\n" + Utils.errorExtract(e));
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
						if (baris >= divisi.upload.importStartLine) {
							colCount2 = nextLine.length;

							if (colCount2 < colCount1) { // bila ada kolom yg
															// error, kasih
															// pesan
								errorMessage.add(" (filename= " + divisi.upload.uploadFile.getOriginalFilename() + ")\n Jumlah kolom pada baris ke-" + baris + " tidak sama dengan requirement");
								break;
							} else { // selain itu tambahkan daftar insert
								Divisi tempDivisi = new Divisi();

								try {
									tempDivisi = new Divisi(Utils.isEmpty(nextLine[0]) ? null : nextLine[0].trim(), Utils.isEmpty(nextLine[1]) ? null : nextLine[1].trim());
								} catch (Exception e) {
									e.printStackTrace();
									errorMessage.add("(filename= " + divisi.upload.uploadFile.getOriginalFilename() + ")\n Format kolom salah pada baris ke-" + baris + " :\n" + e.getMessage());
									break;
								}

								// validasi
								
								DataBinder binder2 = new DataBinder(tempDivisi);
								binder2.addValidators(validator,this.divisiValidator);
								// bind to the target object
								binder2.validate();

								if (binder2.getBindingResult().hasErrors()) {
									errorMessage.addAll(Utils.errorBinderToList(binder2.getBindingResult(), messageSource," (filename= " + divisi.upload.uploadFile.getOriginalFilename() + ") pada baris ke-" + baris));
//									errors.rejectValue("upload.uploadFile", null, Utils.errorBinderToList(binder2.getBindingResult(), messageSource).get(0));
//									break;
								} else {// kalau tidak ada error add ke list
									lsDivisi.add(tempDivisi);
								}

							}

						}
						baris++;
					}

				} catch (FileNotFoundException e) {
					e.printStackTrace();
					errorMessage.add(" (filename= " + divisi.upload.uploadFile.getOriginalFilename() + ") File tidak dapat dibackup.\n");
				} catch (IOException e) {
					e.printStackTrace();
					errorMessage.add(" (filename= " + divisi.upload.uploadFile.getOriginalFilename() + ") File gagal diproses");
				} catch (DataAccessException e) {
					e.printStackTrace();
					errorMessage.add(" (filename= " + divisi.upload.uploadFile.getOriginalFilename() + ") Gagal di simpan ke database");
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
			errors.rejectValue("upload.uploadFile", null, Utils.errorListToString(errorMessage).replace("<br/>", ";"));
			uiModel.addAttribute("errorMessages", Utils.errorListToString(errorMessage));
			populateEditForm(uiModel, divisi);
			divisiManager.audittrail(Audittrail.Activity.EXIM, Audittrail.EximType.FAILED, divisi.getClass().getSimpleName(), divisi.upload.uploadFile.getOriginalFilename(),
					CommonUtil.getIpAddr(httpServletRequest), errorMessage.toString(), CommonUtil.getCurrentUser(), null);
			return "divisi/upload";
		}

		uiModel.asMap().clear();
		divisiManager.audittrail(Audittrail.Activity.EXIM, Audittrail.EximType.SUCCESS, divisi.getClass().getSimpleName(), divisi.upload.uploadFile.getOriginalFilename(),
				CommonUtil.getIpAddr(httpServletRequest), "IMPORT DATA SUCCESS", CommonUtil.getCurrentUser(), null);
		divisiManager.save(lsDivisi);
		String pesan = "File [" + divisi.upload.uploadFile.getOriginalFilename() + "] berhasil diupload, jumlah data yang diproses = " + (baris - 2);
		ra.addFlashAttribute("pesan", pesan);

		return "redirect:/master/divisi/";
	}

	@RequestMapping(value = "/upload", params = "form", produces = "text/html")
	public String upload(Model uiModel) {
		Divisi divisi = new Divisi();
		divisi.upload = new Upload(2, 5000000, true, "*.csv,*.txt");
		populateEditForm(uiModel, divisi);
		return "divisi/upload";
	}

}
