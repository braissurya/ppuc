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
import com.melawai.ppuc.model.Lokasi;
import com.melawai.ppuc.model.Propinsi;
import com.melawai.ppuc.model.Upload;
import com.melawai.ppuc.services.PropinsiManager;
import com.melawai.ppuc.utils.CommonUtil;
import com.melawai.ppuc.utils.Utils;
import com.melawai.ppuc.web.validator.PropinsiValidator;

@RequestMapping("/master/propinsi")
@Controller
public class PropinsiController extends ParentController{

	protected static Logger logger = Logger.getLogger(PropinsiController.class);

	@Autowired
	private PropinsiManager propinsiManager;
	
	@Autowired
	private PropinsiValidator propinsiValidator;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(this.propinsiValidator);
	}
	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
	public String create(@Valid Propinsi propinsi, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		// tambahan validasi khusus
		if (propinsiManager.exists(propinsi.propinsi)) {
			bindingResult.rejectValue("propinsi", "duplicate", new String[] { "Propinsi : " + propinsi.propinsi + ", " }, null);
		}
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, propinsi);
			return "propinsi/create";
		}
		uiModel.asMap().clear();
		propinsiManager.save(propinsi);
		return "redirect:/master/propinsi/" + encodeUrlPathSegment(propinsi.getPropinsi().toString(), httpServletRequest);
	}

	@RequestMapping(params = "form", produces = "text/html")
	public String createForm(Model uiModel) {
		populateEditForm(uiModel, new Propinsi());
		return "propinsi/create";
	}

	@RequestMapping(value = "/{propinsi}", produces = "text/html")
	public String show(@PathVariable("propinsi") String propinsi, Model uiModel) {
		addDateTimeFormatPatterns(uiModel);
		uiModel.addAttribute("propinsi", propinsiManager.get(propinsi));
		uiModel.addAttribute("itemId", propinsi);
		return "propinsi/show";
	}

	@RequestMapping(produces = "text/html")
	public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size,@RequestParam(value = "search", required = false) String search, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
		if (page == null) {
			page=1;
		}

			int sizeNo = size == null ? 10 : size.intValue();
			final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
			uiModel.addAttribute("propinsiList",propinsiManager.selectPagingList(search,sortFieldName,sortOrder, firstResult, sizeNo) );
			float nrOfPages = (float) propinsiManager.selectPagingCount(search) / sizeNo;
			uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
		addDateTimeFormatPatterns(uiModel);
		return "propinsi/list";
	}

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
	public String update(@Valid Propinsi propinsi, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, propinsi);
			return "propinsi/update";
		}
		uiModel.asMap().clear();
		propinsiManager.save(propinsi);
		return "redirect:/master/propinsi/" + encodeUrlPathSegment(propinsi.getPropinsi().toString(), httpServletRequest);
	}

	@RequestMapping(value = "/{propinsi}", params = "form", produces = "text/html")
	public String updateForm(@PathVariable("propinsi") String propinsi, Model uiModel) {
		populateEditForm(uiModel, propinsiManager.get(propinsi));
		return "propinsi/update";
	}

	@RequestMapping(value = "/{propinsi}", method = RequestMethod.DELETE, produces = "text/html")
	public String delete(@PathVariable("propinsi") String propinsi, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
		propinsiManager.remove(propinsi);
		uiModel.asMap().clear();
		uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
		uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
		return "redirect:/master/propinsi";
	}
	void addDateTimeFormatPatterns(Model uiModel) {
		uiModel.addAttribute("propinsi_tgl_create_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
	}
	void populateEditForm(Model uiModel, Propinsi propinsi) {
		uiModel.addAttribute("propinsi", propinsi);
		addDateTimeFormatPatterns(uiModel);
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST, produces = "text/html")
	public String upload(Propinsi propinsi, BindingResult bindingResult, Model uiModel, RedirectAttributes ra, HttpServletRequest httpServletRequest) {
		BindException errors = new BindException(bindingResult);

		DataBinder binder = new DataBinder(propinsi.upload);
		binder.setValidator(this.uploadValidator);
		binder.validate();

		List<String> errorMessage = new ArrayList<String>();
		int baris = 1;
		List<Propinsi> lsPropinsi = new ArrayList<Propinsi>();

		if (binder.getBindingResult().hasErrors()) {
			errors.rejectValue("upload.uploadFile", null, Utils.errorBinderToList(binder.getBindingResult(), messageSource).get(0));
		} else {
			Date sekarang = propinsiManager.selectSysdate();
			// nama file yang ingin disimpan
			String filename = "propinsi" + Utils.convertDateToString(sekarang, "ddMMyyyyHHmmss") + ".csv";
			// jumlah coloumn data yang ingin di proses
			Integer colomnSize = 1;

			// buat directory di server bila belum ada
			String path = props.getProperty("dir.propinsi.temp") + "\\";
			File directory = new File(path);
			if (!directory.exists())
				directory.mkdirs();

			// buat file di directory yg sudah dibuat diatas, dan diisi dengan
			// data yang sama dgn yg diupload

			File file = null;
			// copy file ke server
			try {
				file = new File(path + filename);
				FileUtils.writeByteArrayToFile(file, propinsi.upload.uploadFile.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
				errorMessage.add(" (filename= " + propinsi.upload.getOriginalFilename() + ")\n" + Utils.errorExtract(e));
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
						if (baris >= propinsi.upload.importStartLine) {
							colCount2 = nextLine.length;

							if (colCount2 < colCount1) { // bila ada kolom yg
															// error, kasih
															// pesan
								errorMessage.add(" (filename= " + propinsi.upload.getOriginalFilename() + ")\n Jumlah kolom pada baris ke-" + baris + " tidak sama dengan requirement");
								break;
							} else { // selain itu tambahkan daftar insert
								Propinsi tempPropinsi = new Propinsi();

								try {
									tempPropinsi = new  Propinsi(Utils.nvl(nextLine[0])) ;
								} catch (Exception e) {
									e.printStackTrace();
									errorMessage.add("(filename= " + propinsi.upload.getOriginalFilename() + ")\n Format kolom salah pada baris ke-" + baris + " :\n" + e.getMessage());
									break;
								}

								// validasi

								DataBinder binder2 = new DataBinder(tempPropinsi);
								binder2.addValidators(validator,this.propinsiValidator);
								// bind to the target object
								binder2.validate();

								if (binder2.getBindingResult().hasErrors()) {
									errorMessage.addAll(Utils.errorBinderToList(binder2.getBindingResult(), messageSource," (filename= " + propinsi.upload.uploadFile.getOriginalFilename() + ") pada baris ke-" + baris));
									break;
								} else {// kalau tidak ada error add ke list
									lsPropinsi.add(tempPropinsi);
								}

							}

						}
						baris++;
					}

				} catch (FileNotFoundException e) {
					e.printStackTrace();
					errorMessage.add(" (filename= " + propinsi.upload.getOriginalFilename() + ") File tidak dapat dibackup.\n");
				} catch (IOException e) {
					e.printStackTrace();
					errorMessage.add(" (filename= " + propinsi.upload.getOriginalFilename() + ") File gagal diproses");
				} catch (DataAccessException e) {
					e.printStackTrace();
					errorMessage.add(" (filename= " + propinsi.upload.getOriginalFilename() + ") Gagal di simpan ke database");
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
			populateEditForm(uiModel, propinsi);
			propinsiManager.audittrail(Audittrail.Activity.EXIM, Audittrail.EximType.FAILED, propinsi.getClass().getSimpleName(), propinsi.upload.getOriginalFilename(),
					CommonUtil.getIpAddr(httpServletRequest), errorMessage.toString(), CommonUtil.getCurrentUser(), null);
			return "propinsi/upload";
		}

		uiModel.asMap().clear();
		propinsiManager.audittrail(Audittrail.Activity.EXIM, Audittrail.EximType.SUCCESS, propinsi.getClass().getSimpleName(), propinsi.upload.getOriginalFilename(),
				CommonUtil.getIpAddr(httpServletRequest), "IMPORT DATA SUCCESS", CommonUtil.getCurrentUser(), null);
		propinsiManager.save(lsPropinsi);
		String pesan = "File [" + propinsi.upload.getOriginalFilename() + "] berhasil diupload, jumlah data yang diproses = " + (baris - 2);
		ra.addFlashAttribute("pesan", pesan);

		return "redirect:/master/propinsi/";
	}

	@RequestMapping(value = "/upload", params = "form", produces = "text/html")
	public String upload(Model uiModel) {
		Propinsi propinsi = new Propinsi();
		propinsi.upload = new Upload(2, 5000000, true, "*.csv,*.txt");
		populateEditForm(uiModel, propinsi);
		return "propinsi/upload";
	}
}
