package com.melawai.ppuc.web.controller;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.Validator;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

import com.melawai.ppuc.services.BaseService;
import com.melawai.ppuc.web.validator.UploadValidator;

/**
* Abstract ParentController sebagai parent dari semua controller
* Cuman untuk meletakkan reference data saja dan beberapa variable
*/
public abstract class ParentController {

	protected static Logger logger = Logger.getLogger(ParentController.class);
	
	@Autowired
	protected BaseService baseService;
	
	@Autowired
	protected Properties props;
	
	@Autowired
	protected Validator validator;
	
	@Autowired
	protected MessageSource messageSource;
	
	@Autowired
	protected UploadValidator uploadValidator;

	String encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
		if(pathSegment==null)return "";
		String enc = httpServletRequest.getCharacterEncoding();
		if (enc == null) {
			enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
		}
		try {
			pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
		} catch (UnsupportedEncodingException uee) {}
		return pathSegment;
	}
}
