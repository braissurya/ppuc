package com.melawai.ppuc.model;

import java.io.Serializable;

public class Posisi implements Serializable {
	
	static public class PosisiDesc {
		public static final Integer INPUT_PPUC  = 1;
		public static final Integer CONFIRM_PPUC = 2;
		public static final Integer APPROVAL_PPUC  = 10;
		public static final Integer DECLINE_PPUC  = 12;
		public static final Integer PURCHASING = 21;
		public static final Integer OVER_BUDGET = 25;
		public static final Integer CONFIRM_OVER_BUDGET = 26;
		public static final Integer NEW_BUDGET = 27;
		public static final Integer INPUT_REALIZATION = 31;
		public static final Integer CONFIRM_REALIZATION = 32;
		public static final Integer TRANSFER_ACCOUNTING = 40;
		public static final Integer INPUT_REAL_REALIZATION = 50;
		public static final Integer DUMP_TO_FINANCE = 99;
		
	}
	
	/*//normal flow
	public Integer getNextPosisi(Integer posisiNow){
		Integer nextPosisi=null;
		if(posisiNow==Integer.parseInt(PosisiDesc.INPUT_PPUC))nextPosisi = Integer.parseInt(PosisiDesc.APPROVAL_PPUC);
		else if(posisiNow==Integer.parseInt(PosisiDesc.APPROVAL_PPUC))nextPosisi = Integer.parseInt(PosisiDesc.PURCHASING);
		else if(posisiNow==Integer.parseInt(PosisiDesc.PURCHASING))nextPosisi = Integer.parseInt(PosisiDesc.INPUT_REALIZATION);
		else if(posisiNow==Integer.parseInt(PosisiDesc.INPUT_REALIZATION))nextPosisi = Integer.parseInt(PosisiDesc.INPUT_REAL_REALITATION);
			
		return nextPosisi;
	}*/
	
	
	
}
