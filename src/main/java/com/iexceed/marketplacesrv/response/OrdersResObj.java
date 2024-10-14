package com.iexceed.marketplacesrv.response;

public class OrdersResObj {

	private String orderId;

	private String serviceName;

	private String serviceImg;

	private String spName;

	private String spProfile;

	private String initiatedDate;

	private String status;

	private String stage;

	private String stageTitle;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceImg() {
		return serviceImg;
	}

	public void setServiceImg(String serviceImg) {
		this.serviceImg = serviceImg;
	}

	public String getSpName() {
		return spName;
	}

	public void setSpName(String spName) {
		this.spName = spName;
	}

	public String getSpProfile() {
		return spProfile;
	}

	public void setSpProfile(String spProfile) {
		this.spProfile = spProfile;
	}

	public String getInitiatedDate() {
		return initiatedDate;
	}

	public void setInitiatedDate(String initiatedDate) {
		this.initiatedDate = initiatedDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public String getStageTitle() {
		return stageTitle;
	}

	public void setStageTitle(String stageTitle) {
		this.stageTitle = stageTitle;
	}

}
