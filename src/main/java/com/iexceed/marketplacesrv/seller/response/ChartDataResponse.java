package com.iexceed.marketplacesrv.seller.response;
import java.util.List;

public class ChartDataResponse {

	private String profileImg;
	private String overallRevenue;

	private OrdersChart ordersChart;
	private EarningsChart earningsChart;

	public ChartDataResponse(String profileImg, String overallRevenue, OrdersChart ordersChart,
			EarningsChart earningsChart) {
		super();
		this.profileImg = profileImg;
		this.overallRevenue = overallRevenue;
		this.ordersChart = ordersChart;
		this.earningsChart = earningsChart;
	}

	public OrdersChart getOrdersChart() {
		return ordersChart;
	}

	public void setOrdersChart(OrdersChart ordersChart) {
		this.ordersChart = ordersChart;
	}

	public EarningsChart getEarningsChart() {
		return earningsChart;
	}

	public void setEarningsChart(EarningsChart earningsChart) {
		this.earningsChart = earningsChart;
	}

	// Inner classes for OrdersChart and EarningsChart

	public static class OrdersChart {
		private String chartType;
		private int totalOrders;
		private List<Category> categories;

		public OrdersChart(String chartType, int totalOrders, List<Category> categories) {
			super();
			this.chartType = chartType;
			this.totalOrders = totalOrders;
			this.categories = categories;
		}

		// Getters and Setters
		public String getChartType() {
			return chartType;
		}

		public void setChartType(String chartType) {
			this.chartType = chartType;
		}

		public int getTotalOrders() {
			return totalOrders;
		}

		public void setTotalOrders(int totalOrders) {
			this.totalOrders = totalOrders;
		}

		public List<Category> getCategories() {
			return categories;
		}

		public void setCategories(List<Category> categories) {
			this.categories = categories;
		}

		// Inner class for Category
		public static class Category {
			private String name;
			private int count;

			public Category(String name, int count) {
				super();
				this.name = name;
				this.count = count;
			}

			// Getters and Setters
			public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
			}

			public int getCount() {
				return count;
			}

			public void setCount(int count) {
				this.count = count;
			}
		}
	}

	public static class EarningsChart {
		private String chartType;
		private List<Earning> earnings;

		// Getters and Setters
		public String getChartType() {
			return chartType;
		}

		public void setChartType(String chartType) {
			this.chartType = chartType;
		}

		public List<Earning> getEarnings() {
			return earnings;
		}

		public void setEarnings(List<Earning> earnings) {
			this.earnings = earnings;
		}

		// Inner class for Earning
		public static class Earning {
			private String month;
			private int amount;

			// Getters and Setters

			public int getAmount() {
				return amount;
			}

			public String getMonth() {
				return month;
			}

			public void setMonth(String month) {
				this.month = month;
			}

			public void setAmount(int amount) {
				this.amount = amount;
			}
		}
	}

	public String getOverallRevenue() {
		return overallRevenue;
	}

	public void setOverallRevenue(String overallRevenue) {
		this.overallRevenue = overallRevenue;
	}

	public String getProfileImg() {
		return profileImg;
	}

	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}

}
