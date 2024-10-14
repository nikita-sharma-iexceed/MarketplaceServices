package com.iexceed.marketplacesrv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iexceed.marketplacesrv.exception.ServicesNotFoundException;
import com.iexceed.marketplacesrv.model.Quotation;
import com.iexceed.marketplacesrv.model.Review;
import com.iexceed.marketplacesrv.model.ServiceListings;
import com.iexceed.marketplacesrv.model.ServiceProviderInfo;
import com.iexceed.marketplacesrv.request.ServiceProviderDTO;
import com.iexceed.marketplacesrv.request.ServiceRequestDto;
import com.iexceed.marketplacesrv.request.WolRequest;
import com.iexceed.marketplacesrv.response.CategoriesResponse;
import com.iexceed.marketplacesrv.response.OrderTimelineResponse;
import com.iexceed.marketplacesrv.response.OrdersResponse;
import com.iexceed.marketplacesrv.response.QuotationRequest;
import com.iexceed.marketplacesrv.response.RequestQuoteResponse;
import com.iexceed.marketplacesrv.response.SearchKeywordResponse;
import com.iexceed.marketplacesrv.response.SendQuoteToastResponse;
//import com.iexceed.marketplacesrv.response.SearchKeywordResponse;
import com.iexceed.marketplacesrv.response.ServiceProviderResponse;
import com.iexceed.marketplacesrv.response.StandardResponse;
import com.iexceed.marketplacesrv.seller.response.ChartDataResponse;
import com.iexceed.marketplacesrv.seller.response.IsSellerResponse;
import com.iexceed.marketplacesrv.seller.response.SellerOrderTimelineResponse;
import com.iexceed.marketplacesrv.seller.response.SellerOrdersResponse;
import com.iexceed.marketplacesrv.seller.response.WolSellerResponse;
import com.iexceed.marketplacesrv.seller.service.SellerService;
import com.iexceed.marketplacesrv.service.MarketplaceService;

@RestController
@RequestMapping("/mkt-services/api")
public class ServicesController {

	@Autowired
	private MarketplaceService mktServices;
	
	@Autowired
	private SellerService sellerService;
	
    @ExceptionHandler(ServicesNotFoundException.class)
    public ResponseEntity<String> handleServicesNotFoundException(ServicesNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

	@GetMapping("/fetchCategories")
	public ResponseEntity<CategoriesResponse> fetchCategories(){
		return new ResponseEntity<>(mktServices.getCategories(), HttpStatus.OK);
	}
	
	@GetMapping("/fetchSPByCategory")
	public ResponseEntity<ServiceProviderResponse> fetchSPByCategory(@RequestParam String cId){
		return new ResponseEntity<>(mktServices.getSPByCategory(cId), HttpStatus.OK);
	}
	
	@GetMapping("/fetchSPById")
	public ResponseEntity<ServiceProviderInfo> fetchSPById(@RequestParam String spId){
		return new ResponseEntity<>(mktServices.getSPById(spId), HttpStatus.OK);
	}
	
	@GetMapping("/fetchSPByName")
	public ResponseEntity<ServiceProviderInfo> fetchSPByName(@RequestParam String spName){
		return new ResponseEntity<>(mktServices.getSPByName(spName), HttpStatus.OK);
	}
	
	@GetMapping("/fetchServiceById")
	public ResponseEntity<ServiceListings> fetchServiceById(@RequestParam String slId){
		return new ResponseEntity<>(mktServices.getServiceById(slId), HttpStatus.OK);
	}
	
	@GetMapping("/requestQuote")
	public ResponseEntity<RequestQuoteResponse> requestQuoteButton(@RequestParam String slId){
		return new ResponseEntity<>(mktServices.requestForQuoteButton(slId), HttpStatus.OK);
	}
	
	@PostMapping("/sendRequestOrder")
	public ResponseEntity<SendQuoteToastResponse> sendRequestOrderToSeller(@RequestParam String serviceId, @RequestParam String custId, @RequestBody WolRequest reqBody) {
		return new ResponseEntity<>(mktServices.quoteRequested(serviceId, custId, reqBody), HttpStatus.OK);
	}
	
	@GetMapping("/fetchOrders")
	public ResponseEntity<OrdersResponse> fetchOrders(@RequestParam String custId) {
		return new ResponseEntity<>(mktServices.fetchOrdersByCustId(custId), HttpStatus.OK);
	}
	
	@GetMapping("/getOrderTimeline")
	public ResponseEntity<OrderTimelineResponse> orderTimeline(@RequestParam String custId, @RequestParam String orderId) {
		return new ResponseEntity<>(mktServices.getOrderTimeline(custId, orderId), HttpStatus.OK);
	}
	
	@GetMapping("/sendPayment")
	public ResponseEntity<StandardResponse> sendAdvancePayment(@RequestParam String custId, @RequestParam String orderId, @RequestParam Boolean totalPayment) {
		return new ResponseEntity<>(mktServices.processPayment(custId, orderId, totalPayment), HttpStatus.OK);
	}
	
	@PostMapping("/writeReview")
	public ResponseEntity<StandardResponse> writeReview(@RequestParam String spId, @RequestBody Review review) {
		return new ResponseEntity<>(mktServices.writeReview(spId,review), HttpStatus.OK);
	}
	
	@GetMapping("/searchByCategory")
	public ResponseEntity<SearchKeywordResponse> searchServicesByKeyword(@RequestParam String keyword){
		return new ResponseEntity<>(mktServices.searchSeviceLisingsByCategory(keyword), HttpStatus.OK);
	}
	
	//Seller-side
	
	@PostMapping("/sellerOnboarding")
	public ResponseEntity<StandardResponse> sellerOnboard(@RequestParam String custId, @RequestBody ServiceProviderDTO reqBody) {
		return new ResponseEntity<>(sellerService.saveServiceProviderInfo(custId, reqBody), HttpStatus.OK);
	}
	
	@GetMapping("/fetchProfileDetailsByCustId")
	public ResponseEntity<ServiceProviderInfo> fetchProfile(@RequestParam String custId) {
		return new ResponseEntity<>(sellerService.getProfileByCustId(custId), HttpStatus.OK);
	}
	
	@PostMapping("/createService")
	public ResponseEntity<ServiceListings> createService(@RequestParam String custId, @RequestBody ServiceRequestDto reqBody) {
		return new ResponseEntity<>(sellerService.registerService(reqBody,custId), HttpStatus.OK);
	}
	
	@GetMapping("/fetchSellerOrders")
	public ResponseEntity<SellerOrdersResponse> fetchSellerOrders(@RequestParam String custId) {
		return new ResponseEntity<>(sellerService.retrieveSellerOrders(custId), HttpStatus.OK);
	}
	
	@GetMapping("/viewWolSentByBuyer")
	public ResponseEntity<WolSellerResponse> viewWorkDescription(@RequestParam String orderId) {
		return new ResponseEntity<>(sellerService.viewWorkOrderDescription(orderId), HttpStatus.OK);
	}
	
	@GetMapping("/getSellerOrderTimeline")
	public ResponseEntity<SellerOrderTimelineResponse> sellerOrderTimeline(@RequestParam String custId, @RequestParam String orderId) {
		return new ResponseEntity<>(sellerService.getSellerOrderTimeline(custId, orderId), HttpStatus.OK);
	}
	
	@PostMapping("/sendQuoteToBuyer")
	public ResponseEntity<StandardResponse> sendQuoteToBuyer(@RequestParam String orderId, @RequestBody QuotationRequest reqBody) {
		return new ResponseEntity<>(sellerService.sendQuoteToBuyer(orderId,reqBody), HttpStatus.OK);
	}
	
	@PostMapping("/quotationData")
	public ResponseEntity<Quotation> quotationData(@RequestBody Quotation reqBody) {
		return new ResponseEntity<>(sellerService.storeQuotationData(reqBody), HttpStatus.OK);
	}
	
	@GetMapping("/sellerChartData")
    public ResponseEntity<ChartDataResponse> getAllStageData(@RequestParam String custId) {
        return new ResponseEntity<>(sellerService.dashboardData(custId), HttpStatus.OK);
    }
	
	@GetMapping("/deliverServiceButton")
	public ResponseEntity<StandardResponse> serviceDeliveredButton(@RequestParam String orderId){
		return new ResponseEntity<>(sellerService.serviceDeliveredButton(orderId), HttpStatus.OK);
	}
	
	@GetMapping("/fetchChartDataDashboard")
	public ResponseEntity<ChartDataResponse> chartDataDashboard(@RequestParam String custId){
		return new ResponseEntity<>(sellerService.dashboardData(custId), HttpStatus.OK);
	}
	
	@GetMapping("/isSeller")
	public ResponseEntity<IsSellerResponse> isSeller(@RequestParam String custId){
		return new ResponseEntity<>(mktServices.isSeller(custId), HttpStatus.OK);
	}

}
