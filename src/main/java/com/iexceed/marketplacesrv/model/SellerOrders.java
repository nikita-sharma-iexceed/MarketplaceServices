package com.iexceed.marketplacesrv.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_APZS_ORDERS_SELLER")
public class SellerOrders {

	@Id
	private String orderId;

	private String custId;

	private Long serviceId;

	private String initiateDate;

	private String status;

	private String stage;

	private String stageTitle;

	private String buyerId;

	@OneToMany(mappedBy = "sellerOrders", cascade = CascadeType.ALL)
	private List<WolAnswers> wolAnswers;

	private String buyerName;

	private String buyerProfile;

	private String buyerAddress;

	private String additionalInfo;

	private String additionalFilePath;

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

	public String getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}

	public List<WolAnswers> getWolAnswers() {
		return wolAnswers;
	}

	public void setWolAnswers(List<WolAnswers> wolAnswers) {
		this.wolAnswers = wolAnswers;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getBuyerProfile() {
		return buyerProfile;
	}

	public void setBuyerProfile(String buyerProfile) {
		this.buyerProfile = buyerProfile;
	}

	public String getBuyerAddress() {
		return buyerAddress;
	}

	public void setBuyerAddress(String buyerAddress) {
		this.buyerAddress = buyerAddress;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public String getAdditionalFilePath() {
		return additionalFilePath;
	}

	public void setAdditionalFilePath(String additionalFilePath) {
		this.additionalFilePath = additionalFilePath;
	}

}
