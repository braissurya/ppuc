package com.melawai.ppuc.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateMidnight;
import org.joda.time.Months;
import org.joda.time.Years;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.ServletRequestUtils;

import com.melawai.ppuc.model.DropDown;


/**
 * Utility classes, rata2 function/vars disini static saja
 * 
 * @author Yusuf
 * @since Jan 23, 2013 (9:12:00 AM)
 *
 */
public class Utils{
	
	// Formatter2 default ada disini, tidak perlu di-register satu2 di spring xml
	public static final DateFormat defaultDF = new SimpleDateFormat("dd-MM-yyyy");
	public static final DateFormat defaultDFLong = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	public static final DateFormat yearDF = new SimpleDateFormat("yyyy");
	public static final NumberFormat defaultNF =new DecimalFormat("#,##0.00;(#,##0.00)");// NumberFormat.getInstance();
	public static final NumberFormat defaultCF =new DecimalFormat("#,##0;(#,##0)");// NumberFormat.getInstance();
	
	
	/**
	 * Fungsi untuk Tambah Tanggal (contoh: FormatDate.add(tanggal, Calendar.DATE, 30) atau add(new Date(), Calendar.MONTH, 1)),
	 * bisa juga menggunakan negatif (untuk mengurangi)
	 * 
 	 * @author Yusuf
	 * @since Nov 24, 2011
	 * 
	 * @param tanggal
	 *            Tanggal yang ingin ditambahkan
	 * @param kalendar
	 *            Konstanta penambah, sesuai dengan konstanta yang ada di class Calendar
	 * @param angka
	 *            Jumlah angka yang ingin ditambahkan ke tanggal bersangkutan
	 * @return Date hasil setelah ditambahkan (atau dikurangi)
	 * @see Date, Calendar
	 */
	public static Date add(Date tanggal, int kalendar, int angka) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(tanggal);
		cal.add(kalendar, angka);
		Date result = cal.getTime(); 
		return result;
	}
	
	
	/**
	 * Tarik data tahun aplikasi. Contoh hasilnya "2006-2011" 
	 * 
	 * @param now
	 * @return
	 */
	public static String getCopyrightYears(Date now){
		int tahunAwal = 2013;
		int tahunSekarang = Integer.parseInt(yearDF.format(now));
		
		String tahun;
		if(tahunSekarang > tahunAwal) tahun = tahunAwal + "-" + tahunSekarang;
		else tahun = String.valueOf(tahunAwal);
		return tahun;
	}
	
	

	public static String formatNumber(String format,Object amount) {
		if (amount == null)return "";
		else return new DecimalFormat(format+";("+format+")").format(amount);
	}
	
	/**
	 * Fungsi untuk me-listing semua report yang ada di file properties, dimana key nya harus dimulai dengan report atau subreport 
	 * 
	 * @author Yusuf
	 * @since Jul 8, 2008 (10:56:24 AM)
	 * @param props
	 * @return
	 */
	public static List<String> listAllReports(Properties props) {
		List<String> reportList = new ArrayList<String>();
		for(Iterator it = props.keySet().iterator(); it.hasNext();) {
			String key = (String) it.next();
			if(key.startsWith("report") || key.startsWith("subreport")) {
				reportList.add(key);
			}
		}
		Collections.sort(reportList);
		return reportList;
	}
	
	
	
	/**
	 * 
	 * @Method_name	: errorExtract
	 * @author 		: Bertho Rafitya Iwasurya
	 * @since		: Jan 28, 2013 10:25:56 PM
	 * @return_type : String
	 * @Description : mengekstrak error message ke dalam String
	 * @Revision	:
	 * #====#===========#===================#===========================#
	 * | ID	|    Date	|	    User		|			Description		|
	 * #====#===========#===================#===========================#
	 * |	|			|					|							|
	 * #====#===========#===================#===========================#
	 */
	public static String errorExtract(Exception e){
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		String	exception = sw.toString();
		
		try {
			sw.close();
			pw.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return exception;
	}
	
	/**
	 * Fungsi ini untuk mengecek apakah suatu field ada isinya
	 * @author Yusuf Sutarko
	 * @since May 2, 2007 (7:40:39 AM)
	 * @param object
	 * @return
	 */
	public static boolean isEmpty(Object object) {
		if(object==null) return true;
		else	if(object instanceof String) {
			String tmp = (String) object;
			if(tmp.trim().equals("")) return true;
			else return false;
		}else if(object instanceof List) {
			List<?> tmp = (List<?>) object;
			return tmp.isEmpty();
		}else if(object instanceof Map){
			return ((Map<?, ?>) object).isEmpty();
//		}else if(object instanceof Integer || object instanceof Long|| object instanceof Double|| object instanceof Float|| 
//				object instanceof BigDecimal || object instanceof Date){
//			return false;
		}
		return true;
	}
	
	public static String convertDateToString(Date tanggal,String format){
		if(tanggal==null)return null;
		else{
			try{
				return new SimpleDateFormat(format).format(tanggal);
			}catch (Exception e) {
				
				return null;
			}
		}
	}
	
	
	public static String convertDateToString2(Date tanggal,String format)  throws Exception{
		if(tanggal==null)return null;
		else{
			return new SimpleDateFormat(format).format(tanggal);
			
		}
	}
	
	public static Date convertStringToDate(String tanggal,String format){
		if(tanggal==null)return null;
		else{
			try{
				return new SimpleDateFormat(format).parse(tanggal);
			}catch (Exception e) {
				
				return null;
			}
		}
	}
	
	public static Date convertStringToDate2(String tanggal,String format) throws ParseException{
		if(tanggal==null)return null;
		else{
			return new SimpleDateFormat(format).parse(tanggal);
		
		}
	}
	
	public static List<String> errorBinderToList(BindingResult bindingResult,MessageSource messageSource){
		List<String> errorMessage=new ArrayList<String>();
		if (bindingResult.hasErrors()) {
			for (Object object : bindingResult.getAllErrors()) {
				    if(object instanceof FieldError) {
				        FieldError fieldError = (FieldError) object;
				        /**
				          * Use null as second parameter if you do not use i18n (internationalization)
				          */
				        errorMessage.add( messageSource.getMessage(fieldError, null));
				    }
				}
		 }
		return errorMessage;
	}
	
	
	/**
	 * 
	 * @Method_name	: isFileExist
	 * @author 		: Bertho Rafitya Iwasurya
	 * @since		: Feb 12, 2013 10:43:11 PM
	 * @return_type : boolean
	 * @Description : cek apakah file exist
	 * @Revision	:
	 * #====#===========#===================#===========================#
	 * | ID	|    Date	|	    User		|			Description		|
	 * #====#===========#===================#===========================#
	 * |	|			|					|							|
	 * #====#===========#===================#===========================#
	 */
	public static boolean isFileExist(String filename){
		boolean scFile=false;
		FileInputStream in=null;
		try{
			  File l_file = new File(filename);
		     in = new FileInputStream(l_file);				      
		      in.close();
		   
		      scFile=true;
		}catch (Exception e) {
			//e.printStackTrace();
		}finally{
			if(in!=null)
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
//					e.printStackTrace();
				}
		}
		return scFile;
	}
	
	/**
	 * Method untuk menghapus suatu file dari server
	 * 
	 * @author Yusuf
	 * @since Jul 3, 2008 (1:49:03 PM)
	 * @param destDir lokasi file
	 * @param fileName nama file
	 * @param response
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static boolean deleteFile(String destDir, String fileName, HttpServletResponse response) throws FileNotFoundException,
			IOException {
		File file = new File(destDir + "/" + fileName);
		if (file.exists()) return file.delete();
		return false;
	}
	
	/**
	 * Method untuk men-download sebuah file 
	 * 
	 * @author Yusuf
	 * @since Jul 3, 2008 (1:47:27 PM)
	 * @param location lokasi file yg ingin didownload
	 * @param fileName nama file yang ingin didownload
	 * @param response object response
	 * @param inlineOrAttached "inline" apabila ingin langsung ditampilkan di browser atau "attachment" bila ingin keluar dialog "Save As"
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void downloadFile(String location, String fileName, HttpServletResponse response, String inlineOrAttached) 
			throws FileNotFoundException, IOException{
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new FileInputStream(location);
			if (in != null) {
				out = new BufferedOutputStream(response.getOutputStream());
				in = new BufferedInputStream(in);
				//String contentType = "application/unknown";
				response.setHeader("Content-Disposition", inlineOrAttached + "; filename=\"" + fileName + "\"");
				int c;
				while ((c = in.read()) != -1) out.write(c);
			}
		} finally {
			if (in != null) try {in.close(); } catch (Exception e) {}
			if (out != null) try {out.close(); } catch (Exception e) {}
		}				
	}
	
	/**
	 * Method untuk melist file2 yang ada dalam suatu directory
	 * 
	 * @author Yusuf
	 * @since Jul 3, 2008 (1:51:15 PM)
	 * @param dir lokasi file2 yang ingin di listing
	 * @return
	 */
	public static List<DropDown> listFilesInDirectory(String dir) {
		File destDir = new File(dir);
		List<DropDown> daftar = new ArrayList<DropDown>();
		if(destDir.exists()) {
			String[] children = destDir.list();
			daftar = new ArrayList<DropDown>();
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			for(int i=0; i<children.length; i++) {
				File file = new File(destDir+"/"+children[i]);
				daftar.add(new DropDown(children[i], df.format(new Date(file.lastModified())), dir));
			}
		}
		return daftar;
	}
	
	/**
	 * Fungsi yang mengikuti fungsi RPAD di Oracle, contoh: rpad("0", "YUSUF", 10) menghasilkan "00000YUSUF"
	 * 
	 * @author Yusuf
	 * @since Feb 21, 2011 (7:41:43 PM)
	 * @param karakter untuk melengkapi sisa string
	 * @param kata (String) yang mau dipanjangkan
	 * @param panjang dari string hasilnya
	 * @return String hasil penggabungan dari karakter dan kata
	 * @see Fungsi RPAD di Oracle
	 */
	public static String rpad(String karakter, String kata, int panjang) {
		if(kata==null) return null;
		StringBuffer result = new StringBuffer();
		if (kata.length() < panjang) {
			for (int i = 0; i < panjang - kata.length(); i++) {
				result.append(karakter);
			}
			result.append(kata);
			return result.toString();
		} else {
			return kata;
		}
	}
	
	
	
	
	
	public static double nvl(Double value){
		if(value != null) return value;
		else return 0.;
	}
	
	public static int nvl(Integer value){
		if(value != null) return value;
		else return 0;
	}
	
	public static String nvl(String value){
		if(value != null) return value;
		else return "";
	}
	
}