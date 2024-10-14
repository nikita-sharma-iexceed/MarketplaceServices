package com.iexceed.marketplacesrv.response;

import java.util.List;

import com.iexceed.marketplacesrv.model.WorkOrderListing;

public class RequestQuoteResponse {

	private Long spId;

	private String spName;

	private String spProfile;

	private String location;

	private String ordersCmpltd;

	private String deliveryExpt;

	private String successRate;

	private String avgRating;

	private String profileSummary;

	private String reviewsCount;

	private String minPriceRange;

	private List<WorkOrderListing> wol;

	public RequestQuoteResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RequestQuoteResponse(Long spId, String spName, String spProfile, String location, String ordersCmpltd,
			String deliveryExpt, String successRate, String avgRating, String profileSummary, String reviewsCount,
			String minPriceRange, List<WorkOrderListing> wol) {
		super();
		this.spId = spId;
		this.spName = spName;
		this.spProfile = spProfile;
		this.location = location;
		this.ordersCmpltd = ordersCmpltd;
		this.deliveryExpt = deliveryExpt;
		this.successRate = successRate;
		this.avgRating = avgRating;
		this.profileSummary = profileSummary;
		this.reviewsCount = reviewsCount;
		this.minPriceRange = minPriceRange;
		this.wol = wol;
	}

	public Long getSpId() {
		return spId;
	}

	public void setSpId(Long spId) {
		this.spId = spId;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getOrdersCmpltd() {
		return ordersCmpltd;
	}

	public void setOrdersCmpltd(String ordersCmpltd) {
		this.ordersCmpltd = ordersCmpltd;
	}

	public String getDeliveryExpt() {
		return deliveryExpt;
	}

	public void setDeliveryExpt(String deliveryExpt) {
		this.deliveryExpt = deliveryExpt;
	}

	public String getSuccessRate() {
		return successRate;
	}

	public void setSuccessRate(String successRate) {
		this.successRate = successRate;
	}

	public String getAvgRating() {
		return avgRating;
	}

	public void setAvgRating(String avgRating) {
		this.avgRating = avgRating;
	}

	public String getProfileSummary() {
		return profileSummary;
	}

	public void setProfileSummary(String profileSummary) {
		this.profileSummary = profileSummary;
	}

	public String getReviewsCount() {
		return reviewsCount;
	}

	public void setReviewsCount(String reviewsCount) {
		this.reviewsCount = reviewsCount;
	}

	public String getMinPriceRange() {
		return minPriceRange;
	}

	public void setMinPriceRange(String minPriceRange) {
		this.minPriceRange = minPriceRange;
	}

	public List<WorkOrderListing> getWol() {
		return wol;
	}

	public void setWol(List<WorkOrderListing> wol) {
		this.wol = wol;
	}

}
