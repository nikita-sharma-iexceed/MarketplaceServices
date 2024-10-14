package com.iexceed.marketplacesrv.response;

public class QuotationRequest {

	private String advAmount;

	private String totalAmount;

	private String exptdDelivery;

	private String invoicePath;

	public String getAdvAmount() {
		return advAmount;
	}

	public void setAdvAmount(String advAmount) {
		this.advAmount = advAmount;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getExptdDelivery() {
		return exptdDelivery;
	}

	public void setExptdDelivery(String exptdDelivery) {
		this.exptdDelivery = exptdDelivery;
	}

	public String getInvoicePath() {
		return invoicePath;
	}

	public void setInvoicePath(String invoicePath) {
		this.invoicePath = invoicePath;
	}

}
