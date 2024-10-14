package com.iexceed.marketplacesrv.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_APZS_SERVICE_PROVIDER_REVIEWS")
public class Review {

	@Id
	@Column(name = "r_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long rId;

	private String custId;

	private String custName;

	private String custProfile;

	@Column(name = "review_desc", length = 1000)
	private String reviewDesc;

	private String rating;

	private String date;

	@ManyToOne
	@JoinColumn(name = "service_id")
	@JsonIgnore
	private ServiceListings serviceId;

	public Long getrId() {
		return rId;
	}

	public void setrId(Long rId) {
		this.rId = rId;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustProfile() {
		return custProfile;
	}

	public void setCustProfile(String custProfile) {
		this.custProfile = custProfile;
	}

	public String getReviewDesc() {
		return reviewDesc;
	}

	public void setReviewDesc(String reviewDesc) {
		this.reviewDesc = reviewDesc;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public ServiceListings getServiceId() {
		return serviceId;
	}

	public void setServiceId(ServiceListings serviceId) {
		this.serviceId = serviceId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
