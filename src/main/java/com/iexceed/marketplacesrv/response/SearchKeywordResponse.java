package com.iexceed.marketplacesrv.response;

import java.util.List;

public class SearchKeywordResponse {

	private List<ServiceListingSearchResponse> searchedListings;

	public SearchKeywordResponse(List<ServiceListingSearchResponse> searchedListings) {
		super();
		this.searchedListings = searchedListings;
	}

	public List<ServiceListingSearchResponse> getSearchedListings() {
		return searchedListings;
	}

	public void setSearchedListings(List<ServiceListingSearchResponse> searchedListings) {
		this.searchedListings = searchedListings;
	}

}
