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

	

	private List<String> productList;
	
	public List<String> getProductList() {
		return productList;
	}

	public void setProductList(List<String> productList) {
		this.productList = productList;
	}



	private List<String> qtyList;
	
	
	public List<String> getQtyList() {
		return qtyList;
	}

	public void setQtyList(List<String> qtyList) {
		this.qtyList = qtyList;
	}

	public static Kart findByUserId(String userId) {
        return find("userId", userId).firstResult();
    }
	
	
	
	
	public String toString(){
		String msg = "Item listing is:";
		if(productList == null){
			System.out.println("I am getting nothing");
		}else{
			System.out.println("I am getting nothing ***************** " + productList);
		}
		return msg;
	}
	
}
