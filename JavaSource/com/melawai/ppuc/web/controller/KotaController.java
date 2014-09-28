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
import com.melawai.ppuc.model.Kota;
import com.melawai.ppuc.model.Upload;
import com.melawai.ppuc.services.KotaManager;
import com.melawai.ppuc.utils.CommonUtil;
import com.melawai.ppuc.utils.Utils;
import com.melawai.ppuc.web.validator.KotaValidator;

@RequestMapping("/master/kota")
@Controller
public class KotaController extends ParentController{

	protected static Logger logger = Logger.getLogger(KotaController.class);

	@Autowired
	private KotaManager kotaManager;
	
	@Autowired
	private KotaValidator kotaValidator;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(this.kotaValidator);
	}
	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
	public String create(@Valid Kota kota, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, kota);
			return "kota/create";
		}
		uiModel.asMap().clear();
		kotaManager.save(kota);
		return "redirect:/master/kota/" + encodeUrlPathSegment(kota.getPropinsi().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(kota.getKota().toString(), httpServletRequest);
	}

	@RequestMapping(params = "form", produces = "text/html")
	public String createForm(Model uiModel) {
		populateEditForm(uiModel, new Kota());
		return "kota/create";
	}

	@RequestMapping(value = "/{propinsi}/{kota}", produces = "text/html")
	public String show(@PathVariable("propinsi") String propinsi, @PathVariable("kota") String kota, Model uiModel) {
		addDateTimeFormatPatterns(uiModel);
		uiModel.addAttribute("kota", kotaManager.get(propinsi, kota));
		uiModel.addAttribute("itemId", propinsi+"/"+kota);
		return "kota/show";
	}

	@RequestMapping(produces = "text/html")
	public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size,@RequestParam(value = "search", required = false) String search, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
		if (page == null) {
			page=1;
		}

			int sizeNo = size == null ? 10 : size.intValue();
			final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
			uiModel.addAttribute("kotaList",kotaManager.selectPagingList(search,sortFieldName,sortOrder, firstResult, sizeNo) );
			float nrOfPages = (float) kotaManager.selectPagingCount(search) / sizeNo;
			uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
		addDateTimeFormatPatterns(uiModel);
		return "kota/list";
	}

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
	public String update(@Valid Kota kota, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, kota);
			return "kota/update";
		}
		uiModel.asMap().clear();
		kotaManager.save(kota);
		return "redirect:/master/kota/" + encodeUrlPathSegment(kota.getPropinsi().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(kota.getKota().toString(), httpServletRequest);
	}

	@RequestMapping(value = "/{propinsi}/{kota}", params = "form", produces = "text/html")
	public String updateForm(@PathVariable("propinsi") String propinsi, @PathVariable("kota") String kota, Model uiModel) {
		populateEditForm(uiModel, kotaManager.get(propinsi, kota));
		return "kota/update";
	}

	@RequestMapping(value = "/{propinsi}/{kota}", method = RequestMethod.DELETE, produces = "text/html")
	public String delete(@PathVariable("propinsi") String propinsi, @PathVariable("kota") String kota, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
		kotaManager.remove(propinsi, kota);
		uiModel.asMap().clear();
		uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
		uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
		return "redirect:/master/kota";
	}
	void addDateTimeFormatPatterns(Model uiModel) {
		uiModel.addAttribute("kota_tgl_create_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
	}
	void populateEditForm(Model uiModel, Kota kota) {
		uiModel.addAttribute("kota", kota);
		addDateTimeFormatPatterns(uiModel);
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST, produces = "text/html")
	public String upload(Kota kota, BindingResult bindingResult, Model uiModel, RedirectAttributes ra, HttpServletRequest httpServletRequest) {
		BindException errors = new BindException(bindingResult);

		DataBinder binder = new DataBinder(kota.upload);
		binder.setValidator(this.uploadValidator);
		binder.validate();

		List<String> errorMessage = new ArrayList<String>();
		int baris = 1;
		List<Kota> lsKota = new ArrayList<Kota>();

		if (binder.getBindingResult().hasErrors()) {
			errors.rejectValue("upload.uploadFile", null, Utils.errorBinderToList(binder.getBindingResult(), messageSource).get(0));
		} else {
			Date sekarang = kotaManager.selectSysdate();
			// nama file yang ingin disimpan
			String filename = "kota" + Utils.convertDateToString(sekarang, "ddMMyyyyHHmmss") + ".csv";
			// jumlah coloumn data yang ingin di proses
			Integer colomnSize = 2;

			// buat directory di server bila belum ada
			String path = props.getProperty("dir.kota.temp") + "\\";
			File directory = new File(path);
			if (!directory.exists())
				directory.mkdirs();

			// buat file di directory yg sudah dibuat diatas, dan diisi dengan
			// data yang sama dgn yg diupload

			File file = null;
			// copy file ke server
			try {
				file = new File(path + filename);
				FileUtils.writeByteArrayToFile(file, kota.upload.uploadFile.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
				errorMessage.add(" (filename= " + kota.upload.getOriginalFilename() + ")\n" + Utils.errorExtract(e));
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
						if (baris >= kota.upload.importStartLine) {
							colCount2 = nextLine.length;

							if (colCount2 < colCount1) { // bila ada kolom yg
															// error, kasih
															// pesan
								errorMessage.add(" (filename= " + kota.upload.getOriginalFilename() + ")\n Jumlah kolom pada baris ke-" + baris + " tidak sama dengan requirement");
								break;
							} else { // selain itu tambahkan daftar insert
								Kota tempKota = new Kota();

								try {
									tempKota = new  Kota(Utils.nvl(nextLine[0]), Utils.nvl(nextLine[1])) ;
								} catch (Exception e) {
									e.printStackTrace();
									errorMessage.add("(filename= " + kota.upload.getOriginalFilename() + ")\n Format kolom salah pada baris ke-" + baris + " :\n" + e.getMessage());
									break;
								}

								// validasi

								DataBinder binder2 = new DataBinder(tempKota);
								binder2.addValidators(validator,this.kotaValidator);
								// bind to the target object
								binder2.validate();

								if (binder2.getBindingResult().hasErrors()) {
									errorMessage.addAll(Utils.errorBinderToList(binder2.getBindingResult(), messageSource," (filename= " + kota.upload.uploadFile.getOriginalFilename() + ") pada baris ke-" + baris));
									break;
								} else {// kalau tidak ada error add ke list
									lsKota.add(tempKota);
								}

							}

						}
						baris++;
					}

				} catch (FileNotFoundException e) {
					e.printStackTrace();
					errorMessage.add(" (filename= " + kota.upload.getOriginalFilename() + ") File tidak dapat dibackup.\n");
				} catch (IOException e) {
					e.printStackTrace();
					errorMessage.add(" (filename= " + kota.upload.getOriginalFilename() + ") File gagal diproses");
				} catch (DataAccessException e) {
					e.printStackTrace();
					errorMessage.add(" (filename= " + kota.upload.getOriginalFilename() + ") Gagal di simpan ke database");
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
			populateEditForm(uiModel, kota);
			kotaManager.audittrail(Audittrail.Activity.EXIM, Audittrail.EximType.FAILED, kota.getClass().getSimpleName(), kota.upload.getOriginalFilename(),
					CommonUtil.getIpAddr(httpServletRequest), errorMessage.toString(), CommonUtil.getCurrentUser(), null);
			return "kota/upload";
		}

		uiModel.asMap().clear();
		kotaManager.audittrail(Audittrail.Activity.EXIM, Audittrail.EximType.SUCCESS, kota.getClass().getSimpleName(), kota.upload.getOriginalFilename(),
				CommonUtil.getIpAddr(httpServletRequest), "IMPORT DATA SUCCESS", CommonUtil.getCurrentUser(), null);
		kotaManager.save(lsKota);
		String pesan = "File [" + kota.upload.getOriginalFilename() + "] berhasil diupload, jumlah data yang diproses = " + (baris - 2);
		ra.addFlashAttribute("pesan", pesan);

		return "redirect:/master/kota/";
	}

	@RequestMapping(value = "/upload", params = "form", produces = "text/html")
	public String upload(Model uiModel) {
		Kota kota = new Kota();
		kota.upload = new Upload(2, 5000000, true, "*.csv,*.txt");
		populateEditForm(uiModel, kota);
		return "kota/upload";
	}
}
