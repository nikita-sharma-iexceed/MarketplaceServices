package com.iexceed.marketplacesrv.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_APZS_BUYER_ANSWERS")
public class WolAnswers {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long qId;
	
	private String answer;
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	@JsonIgnore
	private SellerOrders sellerOrders;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getqId() {
		return qId;
	}

	public void setqId(Long qId) {
		this.qId = qId;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public SellerOrders getSellerOrders() {
		return sellerOrders;
	}

	public void setSellerOrders(SellerOrders sellerOrders) {
		this.sellerOrders = sellerOrders;
	}
	
	
}
