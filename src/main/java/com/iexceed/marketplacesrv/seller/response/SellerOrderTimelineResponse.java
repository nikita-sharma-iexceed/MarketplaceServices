package com.iexceed.marketplacesrv.seller.response;

import java.util.List;

import com.iexceed.marketplacesrv.response.StageData;

public class SellerOrderTimelineResponse {

	private String serviceTitle;

	private String buyerName;

	private String buyerAddress;

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

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getBuyerAddress() {
		return buyerAddress;
	}

	public void setBuyerAddress(String buyerAddress) {
		this.buyerAddress = buyerAddress;
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
