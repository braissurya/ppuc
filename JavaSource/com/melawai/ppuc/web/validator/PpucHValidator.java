package com.melawai.ppuc.web.validator;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.ServletRequestUtils;

import com.melawai.ppuc.model.PpucD;
import com.melawai.ppuc.model.PpucH;
import com.melawai.ppuc.utils.CommonUtil;
import com.melawai.ppuc.utils.Utils;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: $date.long 
 * @Description	: Validator for table PpucH
 * @Revision	:
 */
@Component
public class PpucHValidator implements Validator {
	
	private static Logger logger = Logger.getLogger(PpucHValidator.class);
	
	@Autowired
	protected HttpServletRequest httpServletRequest;
	
	@Override
	public boolean supports(Class cls) {
		return PpucH.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors e) {
		PpucH ppuch=(PpucH) obj;
		try{
			ppuch.dept_kd=Utils.getLastDelimiterString(ppuch.dept_kd, ".");
			ppuch.lok_kd=Utils.getLastDelimiterString(ppuch.lok_kd, ".");
			ppuch.kd_group=Utils.getLastDelimiterString(ppuch.kd_group, ".");
			ppuch.kd_biaya=Utils.getLastDelimiterString(ppuch.kd_biaya, ".");
		}catch(Exception ex){
			
		}
		if(!e.hasErrors()){
			if (ppuch.idx.length != 0) {
				for (int idx : ppuch.idx) {
					PpucD ppucd=new PpucD(ppuch.divisi_kd, ppuch.subdiv_kd, ppuch.dept_kd, ppuch.lok_kd, 
							ServletRequestUtils.getStringParameter(httpServletRequest, "no_ppuc_" + idx, null), 
							Utils.convertStringToDate(ServletRequestUtils.getStringParameter(httpServletRequest, "tgl_ppuc_" + idx, null), DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale())), 
							Utils.getLastDelimiterString(ServletRequestUtils.getStringParameter(httpServletRequest, "kd_group_" + idx, null), "."), 
							Utils.getLastDelimiterString(ServletRequestUtils.getStringParameter(httpServletRequest, "kd_biaya_" + idx,null), "."), 
							CommonUtil.convertToLong(ServletRequestUtils.getStringParameter(httpServletRequest, "qty_" + idx, null)), 
							CommonUtil.convertCurrencyToDouble(ServletRequestUtils.getStringParameter(httpServletRequest, "harga_" + idx, null)), 
							CommonUtil.convertCurrencyToDouble(ServletRequestUtils.getStringParameter(httpServletRequest, "total_" + idx, null)), 
							ServletRequestUtils.getStringParameter(httpServletRequest, "keterangan_" + idx, null),
							ServletRequestUtils.getStringParameter(httpServletRequest, "nm_group_" + idx, null),
							ServletRequestUtils.getStringParameter(httpServletRequest, "kd_group_" + idx, null));
					ppuch.ppucds.add(ppucd);
				}
			}
		}
	}

	public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
		this.httpServletRequest = httpServletRequest;
	}


}

