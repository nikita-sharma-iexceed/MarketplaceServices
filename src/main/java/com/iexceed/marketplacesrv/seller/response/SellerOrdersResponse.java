package com.iexceed.marketplacesrv.seller.response;

import java.util.List;

public class SellerOrdersResponse {

	private String ordersCount;

	private List<SellerOrdersResObj> orders;

	public SellerOrdersResponse(String ordersCount, List<SellerOrdersResObj> orders) {
		super();
		this.ordersCount = ordersCount;
		this.orders = orders;
	}

	public String getOrdersCount() {
		return ordersCount;
	}

	public void setOrdersCount(String ordersCount) {
		this.ordersCount = ordersCount;
	}

	public List<SellerOrdersResObj> getOrders() {
		return orders;
	}

	public void setOrders(List<SellerOrdersResObj> orders) {
		this.orders = orders;
	}

}
