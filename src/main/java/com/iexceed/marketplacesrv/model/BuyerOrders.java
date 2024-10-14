package com.iexceed.marketplacesrv.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_APZS_ORDERS_BUYER")
public class BuyerOrders {

	@Id
	private String orderId;

	private String custId;

	private Long serviceId;

	private String initiateDate;

	private String status;

	private String stage;

	private String stageTitle;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	public String getInitiateDate() {
		return initiateDate;
	}

	public void setInitiateDate(String initiateDate) {
		this.initiateDate = initiateDate;
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
