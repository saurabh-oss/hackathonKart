package org.tcs.hackathon.kart.model;

import java.util.List;

public class KartResponse {
	private String userId;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<ProductDetails> getProdDetails() {
		return prodDetails;
	}

	public void setProdDetails(List<ProductDetails> prodDetails) {
		this.prodDetails = prodDetails;
	}

	private List<ProductDetails> prodDetails;
	
}
