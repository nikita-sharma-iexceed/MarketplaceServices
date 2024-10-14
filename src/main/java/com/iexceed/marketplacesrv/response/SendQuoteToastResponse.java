package com.iexceed.marketplacesrv.response;

public class SendQuoteToastResponse {

	private String status;

	private String message;

	private String orderId;

	public SendQuoteToastResponse() {
		super();
	}

	public SendQuoteToastResponse(String status, String message, String orderId) {
		super();
		this.status = status;
		this.message = message;
		this.orderId = orderId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

}
