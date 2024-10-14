package com.iexceed.marketplacesrv.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "TB_APZS_SERVICE_PROVIDER_INFO")
public class ServiceProviderInfo {

	@Id
	@Column(name = "sp_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "custom_seq")
	@SequenceGenerator(name = "custom_seq", sequenceName = "custom_seq", allocationSize = 1)
	private Long spid;

	private String custId;

	@Column(name = "sp_name")
	private String spName;

	@Column(name = "profile_summary")
	private String profileSummary;

	@Column(length = 10000)
	private String description;

	private String email;

	private String phoneNo;

	private String location;

	private String ordersCmpltd;

	private String deliveryExpt;

	private String successRate;

	private String avgRating;

	private String address;

	private String totalEarnings;

	@Column(name = "service_listings")
	@OneToMany(mappedBy = "serviceProviderId")
	private List<ServiceListings> serviceListings;

	@Column(length = 1000000000)
	private String profileImgPath;

	private String accountNo;

	private String reviewsCount;

	private String minPriceRange;

	@Transient
	private List<Review> reviewsTotal;

	// Column to store languages as JSON string
	@Column(name = "languages", length = 1000)
	private String languages;

	// Column to store the professional details object as a string (JSON format)
	@Column(name = "professional_details", length = 4000) // Adjust length as needed
	private String professionalDetails;

	public void setLanguages(String languages) {
		this.languages = languages;
	}

	public void setProfessionalDetails(String professionalDetails) {
		this.professionalDetails = professionalDetails;
	}

	public String getLanguages() {
		return languages;
	}

	public String getProfessionalDetails() {
		return professionalDetails;
	}

	public Long getSpid() {
		return spid;
	}

	public void setSpid(Long spid) {
		this.spid = spid;
	}

	public String getSpName() {
		return spName;
	}

	public void setSpName(String spName) {
		this.spName = spName;
	}

	public String getProfileSummary() {
		return profileSummary;
	}

	public void setProfileSummary(String profileSummary) {
		this.profileSummary = profileSummary;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
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

	public List<ServiceListings> getServiceListings() {
		return serviceListings;
	}

	public void setServiceListings(List<ServiceListings> serviceListings) {
		this.serviceListings = serviceListings;
	}

	public String getProfileImgPath() {
		return profileImgPath;
	}

	public void setProfileImgPath(String profileImgPath) {
		this.profileImgPath = profileImgPath;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
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

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTotalEarnings() {
		return totalEarnings;
	}

	public void setTotalEarnings(String totalEarnings) {
		this.totalEarnings = totalEarnings;
	}

	public List<Review> getReviewsTotal() {
		return reviewsTotal;
	}

	public void setReviewsTotal(List<Review> reviewsTotal) {
		this.reviewsTotal = reviewsTotal;
	}

}
