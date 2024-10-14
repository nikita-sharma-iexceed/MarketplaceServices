package com.iexceed.marketplacesrv.response;

import java.util.List;

public class OrderTimelineResponse {

	private String serviceTitle;

	private String spName;

	private String orderId;

	private String totalAmount;

	private String advAmount;

	private String status;

	private String exptdDeliveryDate;

	private String stageCnt;

	private List<StageData> stageData;

	private String invoicePath;

	public String getServiceTitle() {
		return serviceTitle;
	}

	public void setServiceTitle(String serviceTitle) {
		this.serviceTitle = serviceTitle;
	}

	public String getSpName() {
		return spName;
	}

	public void setSpName(String spName) {
		this.spName = spName;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getAdvAmount() {
		return advAmount;
	}

	public void setAdvAmount(String advAmount) {
		this.advAmount = advAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getExptdDeliveryDate() {
		return exptdDeliveryDate;
	}

	public void setExptdDeliveryDate(String exptdDeliveryDate) {
		this.exptdDeliveryDate = exptdDeliveryDate;
	}

	public String getStageCnt() {
		return stageCnt;
	}

	public void setStageCnt(String stageCnt) {
		this.stageCnt = stageCnt;
	}

	public List<StageData> getStageData() {
		return stageData;
	}

	public void setStageData(List<StageData> stageData) {
		this.stageData = stageData;
	}

	public String getInvoicePath() {
		return invoicePath;
	}

	public void setInvoicePath(String invoicePath) {
		this.invoicePath = invoicePath;
	}

}
