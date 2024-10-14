package com.iexceed.marketplacesrv.model;

import jakarta.persistence.*;

@Entity
@Table(name = "TB_APZS_SELLER_QUOTATION")
public class Quotation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String orderId;
	private String spId;
	private String buyerName;
	private String buyerAddress;
	private String natureOfWork;
	private String description;
	private String timeOfDelivery;
	private String serviceCost;
	private String advancePayment;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getNatureOfWork() {
		return natureOfWork;
	}

	public void setNatureOfWork(String natureOfWork) {
		this.natureOfWork = natureOfWork;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSpId() {
		return spId;
	}

	public void setSpId(String spId) {
		this.spId = spId;
	}

	public String getTimeOfDelivery() {
		return timeOfDelivery;
	}

	public void setTimeOfDelivery(String timeOfDelivery) {
		this.timeOfDelivery = timeOfDelivery;
	}

	public String getServiceCost() {
		return serviceCost;
	}

	public void setServiceCost(String serviceCost) {
		this.serviceCost = serviceCost;
	}

	public String getAdvancePayment() {
		return advancePayment;
	}

	public void setAdvancePayment(String advancePayment) {
		this.advancePayment = advancePayment;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

}
