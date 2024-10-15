package com.iexceed.marketplacesrv.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CategoriesResponse {

	private List<CategoryObj> categories;
	
	@AllArgsConstructor
	@NoArgsConstructor
	@Setter
	@Getter
	public static class CategoryObj {

	    @JsonProperty("cId")
		private String cId;
		private String category;
		private String imgUrl;
		private String categoryDesc;

	}

}
