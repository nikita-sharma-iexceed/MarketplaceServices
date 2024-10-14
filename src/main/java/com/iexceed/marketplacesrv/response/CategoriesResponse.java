package com.iexceed.marketplacesrv.response;

import java.util.List;

import com.iexceed.marketplacesrv.model.Categories;

public class CategoriesResponse {

	private List<Categories> categories;

	public List<Categories> getCategories() {
		return categories;
	}

	public void setCategories(List<Categories> categories) {
		this.categories = categories;
	}

}
