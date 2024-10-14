package com.iexceed.marketplacesrv.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_APZS_STAGE_ORDER_RECEIVED_DETAILS")
public class QuoteReceivedDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String orderId;

	@Column(length = 1000)
	private String invoicePath;

	private String adv_amount;

	private String total_amount;

	private String exptdDeliveryDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getInvoicePath() {
		return invoicePath;
	}

	public void setInvoicePath(String invoicePath) {
		this.invoicePath = invoicePath;
	}

	public String getAdv_amount() {
		return adv_amount;
	}

	public void setAdv_amount(String adv_amount) {
		this.adv_amount = adv_amount;
	}

	public String getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(String total_amount) {
		this.total_amount = total_amount;
	}

	public String getExptdDeliveryDate() {
		return exptdDeliveryDate;
	}

	public void setExptdDeliveryDate(String exptdDeliveryDate) {
		this.exptdDeliveryDate = exptdDeliveryDate;
	}

}
