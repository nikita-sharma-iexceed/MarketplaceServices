package com.iexceed.marketplacesrv.request;

import java.util.List;

public class ServiceRequestDto {

	private String title;
	private String category;
	private PricingDto pricing;
	private DeliveryTimeDto deliveryTime;
	private String description;
	private List<WorkOrderRequestDto> workRequirements;
	private String mediaUploads;

	public static class PricingDto {
		private int minValue;
		private int maxValue;

		public int getMinValue() {
			return minValue;
		}

		public void setMinValue(int minValue) {
			this.minValue = minValue;
		}

		public int getMaxValue() {
			return maxValue;
		}

		public void setMaxValue(int maxValue) {
			this.maxValue = maxValue;
		}

		// Getters and Setters
	}

	public static class DeliveryTimeDto {
		private Integer minValue;
		private Integer maxValue;

		public Integer getMinValue() {
			return minValue;
		}

		public void setMinValue(Integer minValue) {
			this.minValue = minValue;
		}

		public Integer getMaxValue() {
			return maxValue;
		}

		public void setMaxValue(Integer maxValue) {
			this.maxValue = maxValue;
		}

	}

	public static class WorkOrderRequestDto {
		private String question;

		public String getQuestion() {
			return question;
		}

		public void setQuestion(String question) {
			this.question = question;
		}

	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public PricingDto getPricing() {
		return pricing;
	}

	public void setPricing(PricingDto pricing) {
		this.pricing = pricing;
	}

	public DeliveryTimeDto getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(DeliveryTimeDto deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<WorkOrderRequestDto> getWorkRequirements() {
		return workRequirements;
	}

	public void setWorkRequirements(List<WorkOrderRequestDto> workRequirements) {
		this.workRequirements = workRequirements;
	}

	public String getMediaUploads() {
		return mediaUploads;
	}

	public void setMediaUploads(String mediaUploads) {
		this.mediaUploads = mediaUploads;
	}

}