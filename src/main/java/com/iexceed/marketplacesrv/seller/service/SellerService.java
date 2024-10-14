package com.iexceed.marketplacesrv.seller.service;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iexceed.marketplacesrv.exception.ServicesNotFoundException;
import com.iexceed.marketplacesrv.model.BuyerOrders;
import com.iexceed.marketplacesrv.model.BuyerStages;
import com.iexceed.marketplacesrv.model.Quotation;
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
import com.iexceed.marketplacesrv.repository.QuotationRepository;
import com.iexceed.marketplacesrv.repository.QuoteReceivedRepository;
import com.iexceed.marketplacesrv.repository.SellerOrdersRepository;
import com.iexceed.marketplacesrv.repository.SellerStagesRepository;
import com.iexceed.marketplacesrv.repository.ServiceListingsRepository;
import com.iexceed.marketplacesrv.repository.ServiceProviderRepository;
import com.iexceed.marketplacesrv.repository.WolAnswersRepository;
import com.iexceed.marketplacesrv.repository.WolRepository;
import com.iexceed.marketplacesrv.request.ServiceProviderDTO;
import com.iexceed.marketplacesrv.request.ServiceRequestDto;
import com.iexceed.marketplacesrv.request.ServiceRequestDto.WorkOrderRequestDto;
import com.iexceed.marketplacesrv.response.QuotationRequest;
import com.iexceed.marketplacesrv.response.StageData;
import com.iexceed.marketplacesrv.response.StandardResponse;
import com.iexceed.marketplacesrv.seller.response.ChartDataResponse;
import com.iexceed.marketplacesrv.seller.response.ChartDataResponse.EarningsChart;
import com.iexceed.marketplacesrv.seller.response.ChartDataResponse.OrdersChart;
import com.iexceed.marketplacesrv.seller.response.ChartDataResponse.OrdersChart.Category;
import com.iexceed.marketplacesrv.seller.response.SellerOrderTimelineResponse;
import com.iexceed.marketplacesrv.seller.response.SellerOrdersResObj;
import com.iexceed.marketplacesrv.seller.response.SellerOrdersResponse;
import com.iexceed.marketplacesrv.seller.response.WolResObj;
import com.iexceed.marketplacesrv.seller.response.WolSellerResponse;

import jakarta.transaction.Transactional;

@Service
public class SellerService {

	@Autowired
	private ServiceProviderRepository serviceProviderRepo;

	@Autowired
	private ServiceListingsRepository serviceListingsRepo;

	@Autowired
	private WolRepository wolRepo;

	@Autowired
	private SellerOrdersRepository sellerOrdersRepo;

	@Autowired
	private WolAnswersRepository wolAnswersRepo;

	@Autowired
	private SellerStageDataService sellerDataService;

	@Autowired
	private QuoteReceivedRepository quoteReceivedRepo;

	@Autowired
	private SellerStagesRepository sellerStagesRepo;

	@Autowired
	private BuyerStageRepository buyerStagesRepo;

	@Autowired
	private BuyerOrdersRepository buyerOrdersRepo;

	@Autowired
	private QuotationRepository quotationRepo;
	
	@Autowired
	private ChartService chartService;

	@Autowired
	private ObjectMapper objectMapper;

	@Transactional
	public StandardResponse saveServiceProviderInfo(String custId, ServiceProviderDTO dto) {
		ServiceProviderInfo entity = new ServiceProviderInfo();

		entity.setCustId(custId);
		ServiceProviderDTO.BasicDetails basicDetails = dto.getBasicDetails();
		entity.setEmail(basicDetails.getEmail());
		entity.setPhoneNo(basicDetails.getPhoneNumber());
		entity.setSpName(basicDetails.getDisplayName());
		entity.setProfileSummary(basicDetails.getHeadline());
		entity.setDescription(basicDetails.getAboutMe());
		entity.setAccountNo(basicDetails.getAccountNo());
		entity.setAddress(basicDetails.getAddress());
		entity.setLocation(basicDetails.getLocation());
		entity.setProfileImgPath("https://consumerdigitalbanking.appzillon.com:9202/presales/service/ashwin/"
				+ basicDetails.getProfileImg());

		try {
			String languagesJson = objectMapper.writeValueAsString(basicDetails.getLanguages());
			entity.setLanguages(languagesJson);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		try {
			String professionalDetailsJson = objectMapper.writeValueAsString(dto.getProfessionalDetails());
			entity.setProfessionalDetails(professionalDetailsJson);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		serviceProviderRepo.save(entity);

		return new StandardResponse("Success", "Service Provider onboarded successfully!");
	}

	public ServiceProviderInfo getProfileByCustId(String custId) {
		Optional<ServiceProviderInfo> serviceProvider = serviceProviderRepo.findByCustId(custId);
		if (!serviceProvider.isPresent()) {
			throw new ServicesNotFoundException("No service with this id was listed yet!");
		}
		ServiceProviderInfo sp = serviceProvider.get();
		for (ServiceListings sl : sp.getServiceListings()) {
			sl.setWol(null);
		}
		if (sp.getReviewsCount() == null) {
			int reviewCount = 0;
			for (ServiceListings sl : sp.getServiceListings()) {
				int count = reviewCount(sl);
				reviewCount += count;
			}
			String cnt = String.valueOf(reviewCount);
			sp.setReviewsCount("(" + cnt + " reviews)");
		}

		if (sp.getMinPriceRange() == null) {
			sp.setMinPriceRange(minimumPriceRange(sp.getServiceListings()));
		}
		return sp;
	}

	public ServiceListings registerService(ServiceRequestDto serviceData, String custId) {
		// Create a new ServiceListings entity
		ServiceListings serviceListings = new ServiceListings();

		// Set category, service title, description, pricing range and delivery
		// expectation
		serviceListings.setCategory(serviceData.getCategory());
		serviceListings.setServiceTitle(serviceData.getTitle());
		serviceListings.setServiceDesc(serviceData.getDescription());
		serviceListings.setPriceRange(
				"GBP" + serviceData.getPricing().getMinValue() + "-GBP" + serviceData.getPricing().getMaxValue());
		serviceListings.setDeliveryExpt(serviceData.getDeliveryTime().getMaxValue().toString());
		serviceListings.setImages(serviceData.getMediaUploads());

		// Set the Service Provider Info
		ServiceProviderInfo provider = serviceProviderRepo.findByCustId(custId).get();
		serviceListings.setServiceProviderId(provider);

		// Save the service listing to get its ID
		ServiceListings savedServiceListing = serviceListingsRepo.save(serviceListings);

		// Create WorkOrderListing entries and associate them with the service listing
		List<WorkOrderListing> workOrderListings = new ArrayList<>();
		for (WorkOrderRequestDto workOrder : serviceData.getWorkRequirements()) {
			WorkOrderListing workOrderListing = new WorkOrderListing();
			workOrderListing.setQuestion(workOrder.getQuestion());
			workOrderListing.setServiceId(savedServiceListing);
			workOrderListings.add(workOrderListing);
		}

		// Save all work orders
		wolRepo.saveAll(workOrderListings);

		// Set the work order listings in the service listing (if necessary to return)
		savedServiceListing.setWol(workOrderListings);

		return savedServiceListing;
	}

	public SellerOrdersResponse retrieveSellerOrders(String custId) {
		List<SellerOrders> ordersList = sellerOrdersRepo.findByCustId(custId);
		if (ordersList.isEmpty() || ordersList == null) {
			throw new ServicesNotFoundException("No services with this category were listed yet!");
		}
		int cnt = 0;
		List<SellerOrdersResObj> odList = new ArrayList<>();
		for (SellerOrders order : ordersList) {
			SellerOrdersResObj od = new SellerOrdersResObj();
			od.setOrderId(order.getOrderId());
			od.setInitiatedDate(order.getInitiateDate());
			od.setStage(order.getStage());
			od.setStageTitle(order.getStageTitle());
			od.setStatus(order.getStatus());
			od.setBuyerName(order.getBuyerName());
			od.setBuyerImg(order.getBuyerProfile());

			Optional<ServiceListings> serviceListing = serviceListingsRepo.findById(order.getServiceId());
			ServiceListings service = serviceListing.get();
			od.setServiceName(service.getServiceTitle());
			od.setServiceImg(service.getImages());

			cnt++;

			odList.add(od);
		}
		return new SellerOrdersResponse(String.valueOf(cnt), odList);

	}

	public WolSellerResponse viewWorkOrderDescription(String orderId) {
		SellerOrders order = sellerOrdersRepo.findById(orderId).get();
		ServiceListings service = serviceListingsRepo.findById(Long.valueOf(order.getServiceId())).get();
		List<WorkOrderListing> wolQues = wolRepo.findByServiceId(service);
		List<WolAnswers> wolAns = wolAnswersRepo.findBySellerOrders(order);
		List<WolResObj> wolList = new ArrayList<>();
		for (int i = 0; i < wolQues.size(); i++) {
			WolResObj wolRes = new WolResObj();
			wolRes.setId(i+1);
			wolRes.setQues(wolQues.get(i).getQuestion());
			wolRes.setAns(wolAns.get(i).getAnswer());
			wolList.add(wolRes);
		}
		return new WolSellerResponse(wolList, order.getAdditionalInfo(), order.getAdditionalFilePath());
	}

	public SellerOrderTimelineResponse getSellerOrderTimeline(String custId, String orderId) {
		Optional<SellerOrders> orderDets = sellerOrdersRepo.findById(orderId);
		if (!orderDets.isPresent()) {
			throw new ServicesNotFoundException("No order with this id was listed yet!");
		}
		SellerOrders order = orderDets.get();
		Optional<ServiceListings> serviceListing = serviceListingsRepo.findById(Long.valueOf(order.getServiceId()));
		if (!serviceListing.isPresent()) {
			throw new ServicesNotFoundException("No service with this order id was listed yet!");
		}
		ServiceListings service = serviceListing.get();
		SellerOrderTimelineResponse res = new SellerOrderTimelineResponse();
		res.setOrderId(orderId);
		res.setServiceTitle(service.getServiceTitle());
		res.setBuyerName(order.getBuyerName());
		res.setBuyerAddress(order.getBuyerAddress());

		int stageCnt = 0;

		List<StageData> stageDataList = new ArrayList<>();
		for (char ch : order.getStage().toCharArray()) {
			stageCnt = performActionForStage("" + ch, ++stageCnt, orderId, stageDataList);
			if (ch == '2') {
				QuoteReceivedDetails quoteDets = quoteReceivedRepo.findByOrderId(orderId);
				res.setInvoicePath(quoteDets.getInvoicePath());
				res.setAdvAmount(quoteDets.getAdv_amount());
				res.setTotalAmount(quoteDets.getTotal_amount());
				res.setExptdDeliveryDate(quoteDets.getExptdDeliveryDate());
			}
		}
		res.setStageCnt(String.valueOf(stageCnt));
		res.setStatus(order.getStatus());
		res.setStageData(stageDataList);

		return res;
	}

	private int performActionForStage(String stageId, int stageCnt, String orderId, List<StageData> stageDataList) {
		JsonNode data = sellerDataService.getStageDataById(stageId);
		JsonNode stageList = data.get("stageList");
		int cnt = 0;
		for (JsonNode stage : stageList) {
			StageData stageData = new StageData();
			stageData.setStageNo(String.valueOf(stageCnt));
			stageData.setTitle(stage.get("title").asText());
			stageData.setDesc(stage.get("desc").asText());

			SellerStages stageHistory = sellerStagesRepo.findByOrderIdAndStageId(orderId, stageId);
			stageData.setDate(formatDate(stageHistory.getDateTime()));

			stageDataList.add(stageData);
			cnt++;
			if (stageList.size() - cnt != 0)
				stageCnt++;
		}
		return stageCnt;
	}

	public StandardResponse sendQuoteToBuyer(String orderId, QuotationRequest quotationObj) {
		try {
			QuoteReceivedDetails quote = new QuoteReceivedDetails();
			quote.setOrderId(orderId);
			quote.setAdv_amount(quotationObj.getAdvAmount());
			quote.setTotal_amount(quotationObj.getTotalAmount());
			quote.setExptdDeliveryDate(quotationObj.getExptdDelivery());
			quote.setInvoicePath(quotationObj.getInvoicePath());
			quoteReceivedRepo.save(quote);
			SellerOrders orders = sellerOrdersRepo.findById(orderId).get();
			orders.setStage("12");
			orders.setStageTitle("Query Sent");
			sellerOrdersRepo.save(orders);

			SellerStages stages = new SellerStages();
			stages.setOrderId(orders.getOrderId());
			stages.setStageId("2");
			stages.setDateTime(LocalDateTime.now().toString());
			sellerStagesRepo.save(stages);

			BuyerStages buyerStage = new BuyerStages();
			buyerStage.setOrderId(orderId);
			buyerStage.setId(buyerStage.getId());
			buyerStage.setStageId("2");
			buyerStage.setDateTime(LocalDateTime.now().toString());
			buyerStagesRepo.save(buyerStage);

			BuyerOrders buyerOrder = buyerOrdersRepo.findByCustIdAndOrderId(orders.getBuyerId(), orderId);
			buyerOrder.setOrderId(orderId);
			buyerOrder.setStage(buyerOrder.getStage() + "2");
			buyerOrder.setStageTitle("Quote Receieved");
			buyerOrder.setCustId(buyerOrder.getCustId());
			buyerOrder.setInitiateDate(buyerOrder.getInitiateDate());
			buyerOrder.setServiceId(buyerOrder.getServiceId());
			buyerOrder.setStatus(buyerOrder.getStatus());
			buyerOrdersRepo.save(buyerOrder);

			return new StandardResponse("Success", "Quote sent successfully!");
		} catch (Exception e) {
			e.printStackTrace();
			return new StandardResponse("Failure", "Something went wrong!");
		}
	}

	public StandardResponse serviceDeliveredButton(String orderId) {
		try {
			SellerOrders sellerOrders = sellerOrdersRepo.findById(orderId).get();
			sellerOrders.setStage(sellerOrders.getStage() + "4");
			sellerOrders.setStageTitle("Awaiting Buyer Review");
			sellerOrdersRepo.save(sellerOrders);

			SellerStages sellerStage = new SellerStages();
			sellerStage.setOrderId(orderId);
			sellerStage.setStageId("4");
			sellerStage.setDateTime(LocalDateTime.now().toString());
			sellerStagesRepo.save(sellerStage);

			BuyerOrders buyerOrders = buyerOrdersRepo.findById(orderId).get();
			buyerOrders.setStage(buyerOrders.getStage() + "5");
			buyerOrders.setStageTitle("Review Delivered Service");
			buyerOrdersRepo.save(buyerOrders);

			BuyerStages stage2 = new BuyerStages();
			stage2.setOrderId(orderId);
			stage2.setDateTime(LocalDateTime.now().toString());
			stage2.setStageId("5");
			buyerStagesRepo.save(stage2);

			return new StandardResponse("Success", "Service delivered successfully!");
		} catch (Exception e) {
			e.printStackTrace();
			return new StandardResponse("Failure", "Something went wrong!");
		}

	}
	
	public ChartDataResponse dashboardData(String custId) {
		ServiceProviderInfo sp = serviceProviderRepo.findByCustId(custId).get();
		List<ServiceListings> serviceListing = serviceListingsRepo.findByServiceProviderId(sp);
		List<Category> categories = new ArrayList<>();
		int totalOrders = 0;
		for(ServiceListings listing : serviceListing) {
			List<SellerOrders> sellerOrders = sellerOrdersRepo.findByCustIdAndServiceId(custId,Long.valueOf(listing.getSlId()));
			categories.add(new Category(listing.getServiceTitle(), sellerOrders.size()));
			totalOrders += sellerOrders.size();
		}
		OrdersChart ordersChart = new OrdersChart("Orders", totalOrders, categories);
		EarningsChart earningsChart = chartService.getEarningsChartData();
		
		Long amt = Long.valueOf(sp.getTotalEarnings());
		NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("en", "GB"));
		String totalAmt = formatter.format(amt).replace("¤", "GBP").replace(".00", "");
		
		return new ChartDataResponse(sp.getProfileImgPath(),totalAmt,ordersChart,earningsChart);
	}

	public Quotation storeQuotationData(Quotation req) {
		return quotationRepo.save(req);
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
			String minPart = sl.getPriceRange().split("-")[0].replace("Rs.", "").replace(",", "").trim();

			// Convert the extracted string to an integer
			int minValueInRange = Integer.parseInt(minPart);

			// Update the minimum value if the current one is smaller
			if (minValueInRange < minValue) {
				minValue = minValueInRange;
			}
		}

		NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));
		return formatter.format(minValue).replace("¤", "Rs.").replace(".00", "");
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
