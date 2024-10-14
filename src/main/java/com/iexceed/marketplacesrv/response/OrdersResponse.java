package com.iexceed.marketplacesrv.response;

import java.util.List;

public class OrdersResponse {

	private String ordersCount;

	private List<OrdersResObj> orders;

	public OrdersResponse(String ordersCount, List<OrdersResObj> orders) {
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

	public List<OrdersResObj> getOrders() {
		return orders;
	}

	public void setOrders(List<OrdersResObj> orders) {
		this.orders = orders;
	}

}
