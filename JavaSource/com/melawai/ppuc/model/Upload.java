package com.melawai.ppuc.model;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author : Bertho Rafitya Iwasurya
 * @since : Feb 6, 2013 2:43:00 PM
 * @Description :
 * @Revision :
 *           #====#===========#===================#===========================#
 *           | ID | Date | User | Description |
 *           #====#===========#===================#===========================#
 *           | | | | |
 *           #====#===========#===================#===========================#
 */
public class Upload implements Serializable {

	private static final long serialVersionUID = -8590880740701254294L;
	
	public final Integer size=5000000;
	
	public Integer importStartLine = 1;
	public MultipartFile uploadFile;
	public Integer maxSize ;// < default 5 mb
	public boolean required = false;
	
	public String filetypeAllow;
	

	public Upload(Integer importStartLine, Integer maxSize, boolean required, String filetypeAllow) {
		super();
		this.importStartLine = importStartLine;
		this.maxSize = maxSize;
		this.required = required;
		this.filetypeAllow = filetypeAllow;
	}

	public Upload() {

	}

	public Integer getImportStartLine() {
		return importStartLine;
	}

	public void setImportStartLine(Integer importStartLine) {
		this.importStartLine = importStartLine;
	}

	public MultipartFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}

	public Integer getMaxSize() {
		if(maxSize==null)maxSize=size;
		return maxSize;
	}

	public void setMaxSize(Integer maxSize) {
		this.maxSize = maxSize;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public String getFiletypeAllow() {
		return filetypeAllow;
	}

	public void setFiletypeAllow(String filetypeAllow) {
		this.filetypeAllow = filetypeAllow;
	}

	public Integer getSize() {
		return size;
	}

	
	public String getOriginalFilename(){
		String filename=null;
		if(uploadFile.getSize() != 0){
			filename=uploadFile.getOriginalFilename();
		}
		return filename;
	}
	public String getFileName(){
		String filename=null;
		if(uploadFile.getSize() != 0){
			filename=uploadFile.getOriginalFilename().split("\\.")[0];
		}
		return filename;
	}
	
	public String getFileExt(){
		String filename=null;
		if(uploadFile.getSize() != 0){
			filename=uploadFile.getOriginalFilename().split("\\.")[1];
		}
		return filename;
	}
	
}
