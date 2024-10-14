package com.iexceed.marketplacesrv.seller.response;

import java.util.List;

public class WolSellerResponse {

	private List<WolResObj> Wol;

	private String additionalInfoDesc;

	private String additionalFilePath;

	public WolSellerResponse(List<WolResObj> wol, String additionalInfoDesc, String additionalFilePath) {
		super();
		Wol = wol;
		this.additionalInfoDesc = additionalInfoDesc;
		this.additionalFilePath = additionalFilePath;
	}

	public List<WolResObj> getWol() {
		return Wol;
	}

	public void setWol(List<WolResObj> wol) {
		Wol = wol;
	}

	public String getAdditionalInfoDesc() {
		return additionalInfoDesc;
	}

	public void setAdditionalInfoDesc(String additionalInfoDesc) {
		this.additionalInfoDesc = additionalInfoDesc;
	}

	public String getAdditionalFilePath() {
		return additionalFilePath;
	}

	public void setAdditionalFilePath(String additionalFilePath) {
		this.additionalFilePath = additionalFilePath;
	}

}
