package com.assignment.bhc.utilities.audit;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class LogRecord {

	private String customerID;
	private String action;
	private String actionDate;
	private String layer;
	private long responseTime;
	private String errorMessage;
	private String transactionID;

	public LogRecord(String action, String layer) {
		this.action = action;
		this.layer = layer;
	}
}
