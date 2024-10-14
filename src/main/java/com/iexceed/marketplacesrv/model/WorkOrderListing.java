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
@Table(name = "TB_APZS_SERVICE_WOL")
public class WorkOrderListing {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String question;

	@ManyToOne
	@JoinColumn(name = "service_id")
	@JsonIgnore
	private ServiceListings serviceId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public ServiceListings getServiceId() {
		return serviceId;
	}

	public void setServiceId(ServiceListings serviceId) {
		this.serviceId = serviceId;
	}

}
