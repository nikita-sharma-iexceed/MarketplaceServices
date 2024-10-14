package com.iexceed.marketplacesrv.response;

import java.util.List;

public class ServiceProviderResponse {

	private List<SPCategoryResponse> serviceProviders;

	public ServiceProviderResponse(List<SPCategoryResponse> serviceProviders) {
		super();
		this.serviceProviders = serviceProviders;
	}

	public List<SPCategoryResponse> getServiceProviders() {
		return serviceProviders;
	}

	public void setServiceProviders(List<SPCategoryResponse> serviceProviders) {
		this.serviceProviders = serviceProviders;
	}

}
