package com.iexceed.marketplacesrv.response;

import java.util.List;

import com.iexceed.marketplacesrv.model.ServiceListings;

public class ServiceListingsResponse {

	private List<ServiceListings> servicesAddedToCart;

	public ServiceListingsResponse(List<ServiceListings> servicesAddedToCart) {
		super();
		this.servicesAddedToCart = servicesAddedToCart;
	}

	public List<ServiceListings> getServicesAddedToCart() {
		return servicesAddedToCart;
	}

	public void setServicesAddedToCart(List<ServiceListings> servicesAddedToCart) {
		this.servicesAddedToCart = servicesAddedToCart;
	}

}
