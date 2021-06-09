package com.johnbryce.cs.exceptions;

import java.sql.Date;

@SuppressWarnings("serial")
public class PurchaseUnavailableException extends Exception {
	
	public PurchaseUnavailableException(String msg, Date date) {
		super(msg + " " + date.toString());
	}
	
	public PurchaseUnavailableException(String msg) {
		super(msg);
	}
}
