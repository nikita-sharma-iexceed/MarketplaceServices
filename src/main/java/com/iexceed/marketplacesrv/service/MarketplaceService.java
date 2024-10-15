package com.iexceed.marketplacesrv.service;

import java.security.SecureRandom;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.iexceed.marketplacesrv.exception.ServicesNotFoundException;
import com.iexceed.marketplacesrv.exception.SomethingWentWrongException;
import com.iexceed.marketplacesrv.model.BuyerOrders;
import com.iexceed.marketplacesrv.model.BuyerStages;
import com.iexceed.marketplacesrv.model.Categories;
import com.iexceed.marketplacesrv.model.Categories.CategoryData;
import com.iexceed.marketplacesrv.model.QuoteReceivedDetails;
import com.iexceed.marketplacesrv.model.Review;
import com.iexceed.marketplacesrv.model.SellerOrders;
import com.iexceed.marketplacesrv.model.SellerStages;
import com.iexceed.marketplacesrv.model.ServiceListings;
import com.iexceed.marketplacesrv.model.ServiceProviderInfo;
import com.iexceed.marketplacesrv.model.WolAnswers;
import com.iexceed.marketplacesrv.model.WorkOrderListing;
import com.iexceed.marketplacesrv.repository.BuyerOrdersRepository;
import com.iexceed.marketplacesrv.repository.BuyerStageRepository;
import com.iexceed.marketplacesrv.repository.CategoryRepository;
import com.iexceed.marketplacesrv.repository.QuoteReceivedRepository;
import com.iexceed.marketplacesrv.repository.ReviewRepository;
import com.iexceed.marketplacesrv.repository.SellerOrdersRepository;
import com.iexceed.marketplacesrv.repository.SellerStagesRepository;
import com.iexceed.marketplacesrv.repository.ServiceListingsRepository;
import com.iexceed.marketplacesrv.repository.ServiceProviderRepository;
import com.iexceed.marketplacesrv.request.WolRequest;
import com.iexceed.marketplacesrv.request.WolSubmitObject;
import com.iexceed.marketplacesrv.response.CategoriesResponse;
import com.iexceed.marketplacesrv.response.CategoriesResponse.CategoryObj;
import com.iexceed.marketplacesrv.response.OrderTimelineResponse;
import com.iexceed.marketplacesrv.response.OrdersResObj;
import com.iexceed.marketplacesrv.response.OrdersResponse;
import com.iexceed.marketplacesrv.response.RequestQuoteResponse;
import com.iexceed.marketplacesrv.response.SPCategoryResponse;
import com.iexceed.marketplacesrv.response.SearchKeywordResponse;
import com.iexceed.marketplacesrv.response.SendQuoteToastResponse;
import com.iexceed.marketplacesrv.response.ServiceListingSearchResponse;
import com.iexceed.marketplacesrv.response.ServiceProviderResponse;
import com.iexceed.marketplacesrv.response.StageData;
import com.iexceed.marketplacesrv.response.StandardResponse;
import com.iexceed.marketplacesrv.seller.response.IsSellerResponse;

@Service
public class MarketplaceService {

	@Autowired
	private CategoryRepository categoryRepo;

	@Autowired
	private ServiceProviderRepository serviceProviderRepo;

	@Autowired
	private ServiceListingsRepository serviceListingsRepo;

	@Autowired
	private ReviewRepository reviewRepo;

	@Autowired
	private BuyerOrdersRepository buyerOrdersRepo;

	@Autowired
	private SellerStagesRepository sellerStagesRepo;

	@Autowired
	private BuyerStageRepository buyerStagesRepo;

	@Autowired
	private QuoteReceivedRepository quoteReceivedRepo;

	@Autowired
	private SellerOrdersRepository sellerOrdersRepo;

	@Autowired
	private StageDataService stageDataService;

	public CategoriesResponse getCategories() {
		List<Categories> categories = categoryRepo.findAll();
		List<CategoryObj> categoryData = new ArrayList<>();
		for (Categories cat : categories) {
			CategoryData data = cat.getCategoryData();
			CategoryObj catObj = new CategoriesResponse.CategoryObj(
				cat.getCId(),
				data.getCategory(),
				data.getImgUrl(),
				data.getCategoryDesc()
			);
			categoryData.add(catObj);
		}
		return new CategoriesResponse(categoryData);
	}

	public String getCategoryById(String id) {
		Categories category = categoryRepo.findById(id).get();
		return category.getCategoryData().getCategory();
	}

	public ServiceProviderResponse getSPByCategory(String categoryId) {
		String category = getCategoryById(categoryId);
		List<ServiceProviderInfo> serviceProviders = serviceProviderRepo.findByServiceListingsCategory(category);
		if (serviceProviders.isEmpty() || serviceProviders == null) {
			throw new ServicesNotFoundException("No services with this category were listed yet!");
		}
		List<SPCategoryResponse> finalSPRes = new ArrayList<>();
		for (ServiceProviderInfo sp : serviceProviders) {
			SPCategoryResponse spRes = new SPCategoryResponse();
			spRes.setSpid(sp.getSpid());
			spRes.setSpName(sp.getSpName());
			spRes.setSuccessRate(sp.getSuccessRate());
			spRes.setAvgRating(sp.getAvgRating());
			spRes.setDeliveryExpt(sp.getDeliveryExpt());
			spRes.setLocation(sp.getLocation());
			spRes.setOrdersCmpltd(sp.getOrdersCmpltd() + " orders completed");
			spRes.setProfileSummary(sp.getProfileSummary());
			spRes.setProfileImg(sp.getProfileImgPath());

			if (sp.getReviewsCount() != null) {
				String cnt = sp.getReviewsCount();
				spRes.setReviewsCount("(" + cnt + " reviews)");
			} else {
				int reviewCount = 0;
				for (ServiceListings sl : sp.getServiceListings()) {
					int count = reviewCount(sl);
					reviewCount += count;
				}
				String cnt = String.valueOf(reviewCount);
				spRes.setReviewsCount("(" + cnt + " reviews)");
			}

			if (sp.getMinPriceRange() != null) {
				NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("en", "GB"));
				spRes.setMinPriceRange(formatter.format(Integer.valueOf(sp.getMinPriceRange()).intValue())
						.replace("¤", "GBP").replace(".00", ""));
			} else {
				spRes.setMinPriceRange(minimumPriceRange(sp.getServiceListings()));
			}

			finalSPRes.add(spRes);

		}
		return new ServiceProviderResponse(finalSPRes);
	}

	public ServiceProviderInfo getSPById(String spId) {
		Optional<ServiceProviderInfo> serviceProvider = serviceProviderRepo.findById(Long.valueOf(spId));
		if (!serviceProvider.isPresent()) {
			throw new ServicesNotFoundException("No service with this id was listed yet!");
		}
		ServiceProviderInfo sp = serviceProvider.get();
		List<Review> reviews = new ArrayList<>();

		if (sp.getMinPriceRange() == null) {
			sp.setMinPriceRange(minimumPriceRange(sp.getServiceListings()));
		}
		for (ServiceListings sl : sp.getServiceListings()) {
			sl.setWol(null);
			if (sl.getReviewsCount() == null) {
				String cnt = String.valueOf(reviewCount(sl));
				sl.setReviewsCount("(" + cnt + " reviews)");
			} else {
				sl.setReviewsCount("(" + sl.getReviewsCount() + " reviews)");
			}
			for (Review rev : sl.getReviews()) {
				reviews.add(rev);
			}
			String range = addCurrencyToRange(sl);
			sl.setPriceRange(range);
		}
		if (sp.getReviewsCount() == null) {
			int reviewCount = 0;
			for (ServiceListings sl : sp.getServiceListings()) {
				int count = reviewCount(sl);
				reviewCount += count;
				sl.setReviews(null);
			}
			String cnt = String.valueOf(reviewCount);
			sp.setReviewsCount("(" + cnt + " reviews)");
		}
		sp.setReviewsTotal(reviews);

		return sp;
	}

	public ServiceListings getServiceById(String slId) {
		Optional<ServiceListings> service = serviceListingsRepo.findById(Long.valueOf(slId));
		if (!service.isPresent()) {
			throw new ServicesNotFoundException("No service with this id was listed yet!");
		}
		ServiceListings serviceListing = service.get();
		if (serviceListing.getReviewsCount() == null) {
			String cnt = String.valueOf(reviewCount(serviceListing));
			serviceListing.setReviewsCount("(" + cnt + " reviews)");
		} else {
			serviceListing.setReviewsCount("(" + serviceListing.getReviewsCount() + " reviews)");
		}
		serviceListing.setWol(null);
		return serviceListing;
	}

	public ServiceProviderInfo getSPByName(String spName) {
		Optional<ServiceProviderInfo> serviceProvider = serviceProviderRepo.findBySpNameContaining(spName);
		if (!serviceProvider.isPresent()) {
			throw new ServicesNotFoundException("No service with this name was listed yet!");
		}
		return serviceProvider.get();
	}

	public StandardResponse writeReview(String slId, Review review) {
		try {
			ServiceListings service = serviceListingsRepo.findById(Long.valueOf(slId)).get();
			review.setServiceId(service);
			reviewRepo.save(review);
			return new StandardResponse("Success", "Review added successfully!");
		} catch (Exception e) {
			e.printStackTrace();
			return new StandardResponse("Failure", "Unexpected error occured");
		}
	}

	public SearchKeywordResponse searchSeviceLisingsByCategory(String keyword) {
		List<ServiceListingSearchResponse> serviceListings = serviceListingsRepo
				.findListingsByCategoryOrTitleWithProviderInfo(keyword);
		if (serviceListings == null || serviceListings.isEmpty()) {
			throw new ServicesNotFoundException("No services with this category was listed yet!");
		}
		return new SearchKeywordResponse(serviceListings);
	}

	public RequestQuoteResponse requestForQuoteButton(String slId) {
		Optional<ServiceListings> service = serviceListingsRepo.findById(Long.valueOf(slId));
		if (!service.isPresent()) {
			throw new ServicesNotFoundException("No service with this id was listed yet!");
		}
		ServiceListings serviceListing = service.get();
		ServiceProviderInfo provider = serviceListing.getServiceProviderId();
		if (provider.getReviewsCount() == null) {
			int reviewCount = 0;
			for (ServiceListings sl : provider.getServiceListings()) {
				int count = reviewCount(sl);
				reviewCount += count;
			}
			provider.setReviewsCount("(" + String.valueOf(reviewCount) + " reviews)");
		}else {
			provider.setReviewsCount("(" + provider.getReviewsCount() + " reviews)");
		}

		if (provider.getMinPriceRange() != null) {
			NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("en", "GB"));
			provider.setMinPriceRange(formatter.format(Integer.valueOf(provider.getMinPriceRange()).intValue())
					.replace("¤", "GBP").replace(".00", ""));
		} else {
			provider.setMinPriceRange(minimumPriceRange(provider.getServiceListings()));
		}
		List<WorkOrderListing> wol = serviceListing.getWol();
		return new RequestQuoteResponse(provider.getSpid(), provider.getSpName(), provider.getProfileImgPath(),
				provider.getLocation(), provider.getOrdersCmpltd()+" orders completed", provider.getDeliveryExpt(),
				provider.getSuccessRate(), provider.getAvgRating(), provider.getProfileSummary(),
				provider.getReviewsCount(), provider.getMinPriceRange(), wol);
	}

	public SendQuoteToastResponse quoteRequested(String serviceId, String custId, WolRequest wol) {
		try {
			String orderId = generateOrderId();
			BuyerOrders order = new BuyerOrders();
			order.setOrderId(orderId);
			order.setCustId(custId);
			order.setServiceId(Long.valueOf(serviceId));
			order.setInitiateDate(LocalDateTime.now().toString());
			order.setStatus("In review");
			order.setStageTitle("Quote Requested");
			order.setStage("1");
			buyerOrdersRepo.save(order);

			BuyerStages stage = new BuyerStages();
			stage.setOrderId(orderId);
			stage.setStageId("1");
			stage.setDateTime(LocalDateTime.now().toString());
			buyerStagesRepo.save(stage);

			SellerOrders sellerOd = new SellerOrders();
			sellerOd.setOrderId(orderId);
			sellerOd.setBuyerId(custId);
			sellerOd.setServiceId(Long.valueOf(serviceId));
			sellerOd.setInitiateDate(LocalDateTime.now().toString());
			sellerOd.setStatus("In review");
			sellerOd.setStageTitle("Order Received");
			sellerOd.setBuyerName(wol.getCustName());
			sellerOd.setBuyerProfile(wol.getCustProfile());
			sellerOd.setBuyerAddress(wol.getCustAddress());
			sellerOd.setAdditionalInfo(wol.getAdditionalInfo());
			sellerOd.setAdditionalFilePath(wol.getAttachDocPath());
			sellerOd.setStage("1");
			sellerOd.setCustId(
					serviceListingsRepo.findById(Long.valueOf(serviceId)).get().getServiceProviderId().getCustId());
			SellerOrders od = sellerOrdersRepo.save(sellerOd);

			List<WolAnswers> wolSaveObj = new ArrayList<>();
			for (WolSubmitObject wolObj : wol.getWolObj()) {
				WolAnswers obj = new WolAnswers();
				obj.setqId(wolObj.getqId());
				obj.setAnswer(wolObj.getAnswer());
				obj.setSellerOrders(od);
				wolSaveObj.add(obj);
			}
			sellerOd.setWolAnswers(wolSaveObj);
			sellerOrdersRepo.save(sellerOd);

			SellerStages stage1 = new SellerStages();
			stage1.setOrderId(orderId);
			stage1.setStageId("1");
			stage1.setDateTime(LocalDateTime.now().toString());
			sellerStagesRepo.save(stage1);

			return new SendQuoteToastResponse("Success", "Order request successfully sent!", orderId);
		} catch (SomethingWentWrongException e) {
			e.printStackTrace();
			return new SendQuoteToastResponse("Failure", "Something went wrong!", null);
		}
	}

	public OrdersResponse fetchOrdersByCustId(String custId) {
		List<BuyerOrders> ordersList = buyerOrdersRepo.findByCustId(custId);
		if (ordersList.isEmpty() || ordersList == null) {
			throw new ServicesNotFoundException("No services with this category were listed yet!");
		}
		int cnt = 0;
		List<OrdersResObj> odList = new ArrayList<>();
		for (BuyerOrders order : ordersList) {
			OrdersResObj od = new OrdersResObj();
			od.setOrderId(order.getOrderId());
			od.setInitiatedDate(order.getInitiateDate());
			od.setStage(order.getStage());
			od.setStageTitle(order.getStageTitle());
			od.setStatus(order.getStatus());

			Optional<ServiceListings> serviceListing = serviceListingsRepo.findById(order.getServiceId());
			ServiceListings service = serviceListing.get();
			od.setServiceName(service.getServiceTitle());
			od.setServiceImg(service.getImages());
			od.setSpName(service.getServiceProviderId().getSpName());
			od.setSpProfile(service.getServiceProviderId().getProfileImgPath());
			cnt++;

			odList.add(od);
		}
		return new OrdersResponse(String.valueOf(cnt), odList);
	}

	public OrderTimelineResponse getOrderTimeline(String custId, String orderId) {
		Optional<BuyerOrders> orderDets = buyerOrdersRepo.findById(orderId);
		if (!orderDets.isPresent()) {
			throw new ServicesNotFoundException("No order with this id was listed yet!");
		}
		BuyerOrders order = orderDets.get();
		Optional<ServiceListings> serviceListing = serviceListingsRepo.findById(Long.valueOf(order.getServiceId()));
		if (!serviceListing.isPresent()) {
			throw new ServicesNotFoundException("No service with this order id was listed yet!");
		}
		ServiceListings service = serviceListing.get();
		OrderTimelineResponse res = new OrderTimelineResponse();
		res.setOrderId(orderId);
		res.setServiceTitle(service.getServiceTitle());
		res.setSpName(service.getServiceProviderId().getSpName());

		int stageCnt = 0;

		List<StageData> stageDataList = new ArrayList<>();
		for (char ch : order.getStage().toCharArray()) {
			stageCnt = performActionForStage("" + ch, ++stageCnt, orderId, stageDataList);
			if (ch == '2') {
//				QuoteReceivedDetails quoteDets = quoteReceivedRepo.findByOrderId(orderId);
//				res.setInvoicePath(quoteDets.getInvoicePath());
//				NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("en", "GB"));
//				int advAmt = Integer.valueOf(quoteDets.getAdv_amount()).intValue();
//				int totalAmt = Integer.valueOf(quoteDets.getTotal_amount()).intValue();
//				res.setAdvAmount(formatter.format(advAmt).replace("¤", "GBP").replace(".00", ""));
//				res.setTotalAmount(formatter.format(totalAmt).replace("¤", "GBP").replace(".00", ""));
				QuoteReceivedDetails quoteDets = quoteReceivedRepo.findByOrderId(orderId);
				res.setInvoicePath(quoteDets.getInvoicePath());

				NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("en", "GB"));

				// Remove "GBP" and commas before parsing to an integer
				String advAmtStr = quoteDets.getAdv_amount().replace("GBP", "").replace(",", "").trim();
				String totalAmtStr = quoteDets.getTotal_amount().replace("GBP", "").replace(",", "").trim();

				try {
				    // Convert cleaned string to integer
				    int advAmt = Integer.parseInt(advAmtStr);
				    int totalAmt = Integer.parseInt(totalAmtStr);

				    // Format and set the amounts with GBP currency format
				    res.setAdvAmount(formatter.format(advAmt).replace("¤", "GBP").replace(".00", ""));
				    res.setTotalAmount(formatter.format(totalAmt).replace("¤", "GBP").replace(".00", ""));
				} catch (NumberFormatException e) {
				    // Handle parsing error (log it, return a default value, etc.)
				    System.out.println("Error parsing amount: " + e.getMessage());
				    // Set default values if necessary
				    res.setAdvAmount("GBP0");
				    res.setTotalAmount("GBP0");
				}
				res.setExptdDeliveryDate(quoteDets.getExptdDeliveryDate());
			}
		}
		res.setStageCnt(String.valueOf(stageCnt));
		res.setStatus(order.getStatus());
		res.setStageData(stageDataList);

		return res;
	}

	private int performActionForStage(String stageId, int stageCnt, String orderId, List<StageData> stageDataList) {
		JsonNode data = stageDataService.getStageDataById(stageId);
		JsonNode stageList = data.get("stageList");
		int cnt = 0;
		for (JsonNode stage : stageList) {
			StageData stageData = new StageData();
			stageData.setStageNo(String.valueOf(stageCnt));
			stageData.setTitle(stage.get("title").asText());
			stageData.setDesc(stage.get("desc").asText());

			BuyerStages stageHistory = buyerStagesRepo.findByOrderIdAndStageId(orderId, stageId);
			stageData.setDate(formatDate(stageHistory.getDateTime()));

			stageDataList.add(stageData);
			cnt++;
			if (stageList.size() - cnt != 0)
				stageCnt++;
		}
		return stageCnt;
	}

	public StandardResponse processPayment(String custId, String orderId, Boolean totalPayment) {
		try {
			BuyerOrders order = buyerOrdersRepo.findByCustIdAndOrderId(custId, orderId);
			String currStage = order.getStage();
			if (totalPayment == false) {
				order.setStatus("Active");
				order.setStage(currStage + "4");
				order.setStageTitle("Work in Progress");

				BuyerStages stage1 = new BuyerStages();
				stage1.setOrderId(orderId);
				stage1.setDateTime(LocalDateTime.now().toString());
				stage1.setStageId("4");
				buyerStagesRepo.save(stage1);

				SellerOrders sellerOrders = sellerOrdersRepo.findById(orderId).get();
				sellerOrders.setStatus("Active");
				sellerOrders.setStage(sellerOrders.getStage() + "3");
				sellerOrders.setStageTitle("Service In Progress");
				sellerOrdersRepo.save(sellerOrders);

				SellerStages sellerStage = new SellerStages();
				sellerStage.setOrderId(orderId);
				sellerStage.setStageId("3");
				sellerStage.setDateTime(LocalDateTime.now().toString());
				sellerStagesRepo.save(sellerStage);

			} else {
				order.setStatus("Completed");
				order.setStage(currStage + "6");
				order.setStageTitle("Order Completed");

				BuyerStages stage = new BuyerStages();
				stage.setOrderId(orderId);
				stage.setDateTime(LocalDateTime.now().toString());
				stage.setStageId("6");
				buyerStagesRepo.save(stage);

				SellerOrders sellerOrders = sellerOrdersRepo.findById(orderId).get();
				sellerOrders.setStatus("Completed");
				sellerOrders.setStage(sellerOrders.getStage() + "5");
				sellerOrders.setStageTitle("Order Completed");
				sellerOrdersRepo.save(sellerOrders);

				SellerStages sellerStage = new SellerStages();
				sellerStage.setOrderId(orderId);
				sellerStage.setStageId("5");
				sellerStage.setDateTime(LocalDateTime.now().toString());
				sellerStagesRepo.save(sellerStage);

				ServiceListings service = serviceListingsRepo.findById(order.getServiceId()).get();
				ServiceProviderInfo sp = service.getServiceProviderId();
				Integer totalAmount = 0;
				String amt = quoteReceivedRepo.findByOrderId(orderId).getTotal_amount();
				if (amt != null) {
					amt = amt.replaceAll("[^\\d]", "");
					totalAmount = Integer.valueOf(amt);
				}
				String earnings = sp.getTotalEarnings();
				Integer earningsAmt = 0;
				if (earnings != null) {
					earnings = sp.getTotalEarnings().replaceAll("[^\\d]", "");
					earningsAmt = Integer.valueOf(earnings);
				}
				sp.setTotalEarnings(String.valueOf(Integer.valueOf(totalAmount + earningsAmt)));
				serviceProviderRepo.save(sp);
			}
			buyerOrdersRepo.save(order);

			return new StandardResponse("Success", "Stages added successfully!");
		} catch (Exception e) {
			e.printStackTrace();
			return new StandardResponse("Failure", "Something went wrong!");
		}
	}

	public IsSellerResponse isSeller(String custId) {
		Optional<ServiceProviderInfo> serviceProvider = serviceProviderRepo.findByCustId(custId);
		if (!serviceProvider.isPresent()) {
			return new IsSellerResponse("No");
		}
		return new IsSellerResponse("Yes");
	}

	public String generateOrderId() {
		StringBuilder orderId = new StringBuilder();
		SecureRandom random = new SecureRandom();
		// Generate a random 6-character order ID
		for (int i = 0; i < 6; i++) {
			int randomIndex = random.nextInt("ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".length());
			orderId.append("ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".charAt(randomIndex));
		}

		return orderId.toString();
	}

	public int reviewCount(ServiceListings sl) {
		int count = 0;
		for (Review rev : sl.getReviews()) {
			count++;
		}
		return count;
	}

	public String minimumPriceRange(List<ServiceListings> serviceList) {
		int minValue = Integer.MAX_VALUE;

		for (ServiceListings sl : serviceList) {
			// Extract the minimum value from the string (part before '-')
			String minPart = sl.getPriceRange().split("-")[0].replace("GBP", "").replace(",", "").trim();

			// Convert the extracted string to an integer
			int minValueInRange = Integer.parseInt(minPart);

			// Update the minimum value if the current one is smaller
			if (minValueInRange < minValue) {
				minValue = minValueInRange;
			}
		}

		NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("en", "GB"));
		return formatter.format(minValue).replace("¤", "GBP").replace(".00", "");
	}

	public String addCurrencyToRange(ServiceListings service) {
		StringBuilder formattedRange = new StringBuilder();

		String minPart = service.getPriceRange().split("-")[0].replace("GBP", "").replace(",", "").trim();
		String maxPart = service.getPriceRange().split("-")[1].replace("GBP", "").replace(",", "").trim();
		int minValueInRange = Integer.parseInt(minPart);
		int maxValueInRange = Integer.parseInt(maxPart);

		NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("en", "GB"));
		String formattedMinValue = formatter.format(minValueInRange).replace("¤", "GBP").replace(".00", "");
		String formattedMaxValue = formatter.format(maxValueInRange).replace("¤", "GBP").replace(".00", "");

		return (formattedRange.append(formattedMinValue).append(" - ").append(formattedMaxValue)).toString();
	}

	public String formatDate(String dbDateTime) {
		// Parse the input date in the format: "2024-10-07T09:39:52.276495489"
		LocalDateTime dateTime = LocalDateTime.parse(dbDateTime);

		// Define the desired output format: "24 Oct 2024, 10:30 AM"
		DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a");

		// Format the parsed date into the desired output format
		String formattedDate = dateTime.format(outputFormatter);

		return formattedDate;
	}

}
