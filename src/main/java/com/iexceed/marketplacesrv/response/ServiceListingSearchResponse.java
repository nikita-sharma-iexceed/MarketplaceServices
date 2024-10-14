package com.iexceed.marketplacesrv.response;

import com.iexceed.marketplacesrv.model.ServiceListings;

public class ServiceListingSearchResponse {

	private ServiceListings serviceListing;

	private Long spId;

	private String spName;

	private String profileImgPath;

	public ServiceListingSearchResponse() {
		super();
	}

	public ServiceListingSearchResponse(ServiceListings serviceListing, Long spId, String spName,
			String profileImgPath) {
		super();
		this.serviceListing = serviceListing;
		this.spId = spId;
		this.spName = spName;
		this.profileImgPath = profileImgPath;
	}

	public ServiceListings getServiceListing() {
		return serviceListing;
	}

	public void setServiceListing(ServiceListings serviceListing) {
		this.serviceListing = serviceListing;
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

	public String getProfileImgPath() {
		return profileImgPath;
	}

	public void setProfileImgPath(String profileImgPath) {
		this.profileImgPath = profileImgPath;
	}

}
