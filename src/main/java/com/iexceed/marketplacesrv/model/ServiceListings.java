package com.iexceed.marketplacesrv.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_APZS_SERVICE_PROVIDER_LISTINGS")
public class ServiceListings {

	@Id
	@Column(name = "sl_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long slId;

	private String category;

	@Column(name = "service_title")
	private String serviceTitle;

	@Column(name = "service_desc", length = 10000)
	private String serviceDesc;

	private String priceRange;

	private String avgRating;

	@OneToMany(mappedBy = "serviceId")
	private List<WorkOrderListing> wol;

	@ManyToOne
	@JoinColumn(name = "service_provider_id")
	@JsonIgnore
	private ServiceProviderInfo serviceProviderId;

	@OneToMany(mappedBy = "serviceId")
	private List<Review> reviews;

	@Column(length = 1000)
	private String images;

	private String reviewsCount;

	private String deliveryExpt;

	public Long getSlId() {
		return slId;
	}

	public void setSlId(Long slId) {
		this.slId = slId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getServiceTitle() {
		return serviceTitle;
	}

	public void setServiceTitle(String serviceTitle) {
		this.serviceTitle = serviceTitle;
	}

	public String getServiceDesc() {
		return serviceDesc;
	}

	public void setServiceDesc(String serviceDesc) {
		this.serviceDesc = serviceDesc;
	}

	public String getPriceRange() {
		return priceRange;
	}

	public void setPriceRange(String priceRange) {
		this.priceRange = priceRange;
	}

	public String getAvgRating() {
		return avgRating;
	}

	public void setAvgRating(String avgRating) {
		this.avgRating = avgRating;
	}

	public List<WorkOrderListing> getWol() {
		return wol;
	}

	public void setWol(List<WorkOrderListing> wol) {
		this.wol = wol;
	}

	public ServiceProviderInfo getServiceProviderId() {
		return serviceProviderId;
	}

	public void setServiceProviderId(ServiceProviderInfo serviceProviderId) {
		this.serviceProviderId = serviceProviderId;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public String getReviewsCount() {
		return reviewsCount;
	}

	public void setReviewsCount(String reviewsCount) {
		this.reviewsCount = reviewsCount;
	}

	public String getDeliveryExpt() {
		return deliveryExpt;
	}

	public void setDeliveryExpt(String deliveryExpt) {
		this.deliveryExpt = deliveryExpt;
	}

}
