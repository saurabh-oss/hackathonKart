package org.tcs.hackathon.kart.model;

import java.util.List;

import io.quarkus.mongodb.panache.MongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntity;

@MongoEntity(collection = "Kart")
public class Kart extends PanacheMongoEntity {
	
	private String userId;
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<String> getProductIdList() {
		return productIdList;
	}

	public void setProductIdList(List<String> productIdList) {
		this.productIdList = productIdList;
	}

	

	private List<String> productIdList;
	
	
	/*public static Kart findByKartId(String kartId) {
        return find("id", kartId).firstResult();
    }*/
	
}
