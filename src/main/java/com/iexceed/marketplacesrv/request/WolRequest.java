package com.iexceed.marketplacesrv.request;

import java.util.List;

public class WolRequest {

	private List<WolSubmitObject> wolObj;

	private String additionalInfo;

	private String attachDocPath;

	private String custName;

	private String custProfile;

	private String custAddress;

	public List<WolSubmitObject> getWolObj() {
		return wolObj;
	}

	public void setWolObj(List<WolSubmitObject> wolObj) {
		this.wolObj = wolObj;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public String getAttachDocPath() {
		return attachDocPath;
	}

	public void setAttachDocPath(String attachDocPath) {
		this.attachDocPath = attachDocPath;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustProfile() {
		return custProfile;
	}

	public void setCustProfile(String custProfile) {
		this.custProfile = custProfile;
	}

	public String getCustAddress() {
		return custAddress;
	}

	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	}

}
