package com.melawai.ppuc.utils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.melawai.ppuc.model.AudittrailDetail;
import com.melawai.ppuc.model.Divisi;
import com.melawai.ppuc.model.Order;
import com.melawai.ppuc.model.User;

public class CommonUtil {

	private static final Log log = LogFactory.getLog(CommonUtil.class);

	public static final DateFormat defaultDF = new SimpleDateFormat("dd-MM-yyyy");
	public static final DateFormat defaultDFLong = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	public static final DateFormat yearDF = new SimpleDateFormat("yyyy");
	public static final NumberFormat defaultNF = new DecimalFormat("#,##0.00;(#,##0.00)");// format
	// decimal
	public static final NumberFormat defaultCF = new DecimalFormat("#,##0;(#,##0)");// format

	// currency

	public static boolean isEmpty(String object) {
		if (object == null)
			return true;
		if (object.toString().length() == 0)
			return true;
		return false;
	}

	public static boolean isEmpty(String object, boolean trim) {
		if (object == null)
			return true;

		if (trim)
			object = object.trim();

		if (object.toString().length() == 0)
			return true;
		return false;
	}

	public static boolean isNotEmpty(String object) {
		return !isEmpty(object);
	}

	public static boolean isNotEmpty(String object, boolean trim) {
		return !isEmpty(object, trim);
	}

	public static boolean isEmpty(Collection objectList) {
		if (objectList == null)
			return true;
		if (objectList.size() == 0)
			return true;
		return false;
	}

	public static boolean isEmpty(BigDecimal input) {
		if (input == null)
			return true;
		if (input.equals(BigDecimal.ZERO))
			return true;
		return false;
	}

	public static boolean isEmpty(Object object) {
		if (object == null)
			return true;
		else if (object instanceof String) {
			String tmp = (String) object;
			if (tmp.trim().equals(""))
				return true;
			else
				return false;
		} else if (object instanceof List) {
			List<?> tmp = (List<?>) object;
			return tmp.isEmpty();
		} else if (object instanceof Map) {
			return ((Map<?, ?>) object).isEmpty();
		}
		return true;
	}

	public static String[] splitString(String input, String delimiter) {
		List<String> resultList = new ArrayList<String>();

		String inputSplited[] = input.split(delimiter);
		for (String tmpString : inputSplited) {
			if (!isEmpty(tmpString))
				resultList.add(tmpString);
		}

		return resultList.toArray(new String[resultList.size()]);
	}

	public static Integer convertToInteger(String input) {
		if (CommonUtil.isEmpty(input))
			return null;
		return new Integer(input);
	}

	public static Object[] reverseArray(Object[] input) {
		List<Object> tmpList = Arrays.asList(input);
		Collections.reverse(tmpList);
		return tmpList.toArray();
	}

	public static Object getPropertySave(Object input, String propertyPath, Object defaultValue) {

		try {
			String[] propertyPathSplited = propertyPath.split("[.]");

			Object currentObject = input;
			for (String propertyName : propertyPathSplited) {

				if (CommonUtil.isEmpty(propertyName))
					continue;

				currentObject = PropertyUtils.getProperty(currentObject, propertyName);
				if (currentObject == null)
					return defaultValue;
			}
			return currentObject;
		} catch (Exception e) {
			log.error("", e);
			return defaultValue;
		}
	}

	public static User getCurrentUser() {
		User currentUser = null;
		if (SecurityContextHolder.getContext() != null && SecurityContextHolder.getContext().getAuthentication() != null
				&& SecurityContextHolder.getContext().getAuthentication().getPrincipal() != null) {
			try {
				Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

				if (principal instanceof User) {
					currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				}
			} catch (Exception e) {
				log.error(e.getMessage());
			}

		}

		return currentUser;
	}

	public static String getCurrentUserId() {
		User loginUser = getCurrentUser();
		if (loginUser != null)
			return loginUser.getUser_id();
		return null;
	}

	/**
	 * @Method_name : convertCurrencyToDouble
	 * @author : Bertho Rafitya Iwasurya
	 * @since : Sep 12, 2013 9:37:36 AM
	 * @return_type : Double
	 * @Description : Convert String dengan format Currency menjadi Double
	 * @Revision :
	 *           #====#===========#===================#========================
	 *           ===# | ID | Date | User | Description |
	 *           #====#===========#======
	 *           =============#===========================# | | | | |
	 *           #====#======
	 *           =====#===================#===========================#
	 */
	public static Double convertCurrencyToDouble(String nilai) {
		return CommonUtil.isEmpty(nilai) ? 0.0 : new Double(nilai.replace(",", ""));
	}

	/**
	 * @Method_name : formatNumber
	 * @author : Bertho Rafitya Iwasurya
	 * @since : Sep 12, 2013 9:38:50 AM
	 * @return_type : String
	 * @Description : Merubah object (bigDecimal, DOuble, dll) menjadi format
	 *              sesuai pattern
	 * @Revision :
	 *           #====#===========#===================#========================
	 *           ===# | ID | Date | User | Description |
	 *           #====#===========#======
	 *           =============#===========================# | | | | |
	 *           #====#======
	 *           =====#===================#===========================#
	 */
	public static String formatNumber(String format, Object amount) {
		if (amount == null)
			return "";
		else
			return new DecimalFormat(format + ";(" + format + ")").format(amount);
	}

	/**
	 * @Method_name : errorBinderToList
	 * @author : Bertho Rafitya Iwasurya
	 * @since : Sep 18, 2013 10:52:30 AM
	 * @return_type : List<String>
	 * @Description : convert errors binder menjadi list String
	 * @Revision :
	 *           #====#===========#===================#========================
	 *           ===# | ID | Date | User | Description |
	 *           #====#===========#======
	 *           =============#===========================# | | | | |
	 *           #====#======
	 *           =====#===================#===========================#
	 */
	public static List<String> errorBinderToList(BindingResult bindingResult, MessageSource messageSource) {
		List<String> errorMessage = new ArrayList<String>();
		if (bindingResult.hasErrors()) {
			for (Object object : bindingResult.getAllErrors()) {
				if (object instanceof FieldError) {
					FieldError fieldError = (FieldError) object;
					/**
					 * Use null as second parameter if you do not use i18n
					 * (internationalization)
					 */
					errorMessage.add(messageSource.getMessage(fieldError, null));
				}
			}
		}
		return errorMessage;
	}

	public static String getRandomNumber8Digit() {
		long timeSeed = System.nanoTime();
		long randSeed = 13;
		String seed = (timeSeed * randSeed) + "";
		return seed.substring(0, 8);
	}

	public static String extractOrder(List<Order> orderList) {
		StringBuffer sbOrder = null;
		if (orderList != null && !orderList.isEmpty()) {
			sbOrder = new StringBuffer();
			boolean isFirst = true;
			for (Order order : orderList) {
				sbOrder.append(isFirst ? "" : "," + order.getName() + (order.isAscending() ? " asc" : " desc"));
				isFirst = false;
			}
		}
		return sbOrder.toString();
	}

	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public static Set<AudittrailDetail> changes(Object obj1, Object obj2) {
		Set<AudittrailDetail> audittrailDetails = new HashSet<AudittrailDetail>();
		for (Field field : obj1.getClass().getDeclaredFields()) {

			try {
				field.setAccessible(true);
				Object o = field.get(obj1);
				log.debug(field.getName() + " : ");
				log.debug(o);

				Field field2 = null;
				Object o2 = null;
				if (obj2 != null) {
					field2 = obj2.getClass().getDeclaredField(field.getName());
					field2.setAccessible(true);
					o2 = field2.get(obj2);
					log.debug(o2);

				}

				if (o == null && o2 == null) {
					// do nothing
				} else if (o == null && o2 != null) {
					audittrailDetails.add(new AudittrailDetail(field.getName(), null, field2.get(obj2).toString()));
				} else if (o2 == null && o != null) {
					audittrailDetails.add(new AudittrailDetail(field.getName(), field.get(obj1).toString(), null));
				} else if (!o.equals(o2)) {
					audittrailDetails.add(new AudittrailDetail(field.getName(), field.get(obj1).toString(), field2.get(obj2).toString()));
				}
			} catch (NoSuchFieldException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return audittrailDetails;

	}

	public static void main(String[] args) throws Exception {
		Divisi div = new Divisi("OPT", "OPTIK");
		Divisi div2 = new Divisi("SAT", "SATE");
		System.out.println(changes(div, div2).toString());
	}
}
