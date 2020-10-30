package org.tcs.hackathon.kart.model;

import java.util.List;

import org.bson.codecs.pojo.annotations.BsonId;

import io.quarkus.mongodb.panache.MongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntityBase;

@MongoEntity(collection = "Cart")
public class Kart extends PanacheMongoEntityBase {
	
	
	@BsonId
	private String userIdentification;
	private String userId;
	private List<String> productList;
	private List<Integer> qtyList;
	
	public String getUserIdentification() {
		return userIdentification;
	}

	public void setUserIdentification(String userIdentification) {
		this.userIdentification = userIdentification;
	}



	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	

	
	
	public List<String> getProductList() {
		return productList;
	}

	public void setProductList(List<String> productList) {
		this.productList = productList;
	}

	public List<Integer> getQtyList() {
		return qtyList;
	}

	public void setQtyList(List<Integer> qtyList) {
		this.qtyList = qtyList;
	}

	public static Kart findByUserId(String userId) {
        return find("userId", userId).firstResult();
    }
}
