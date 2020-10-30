package org.tcs.hackathon.kart.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.bson.types.ObjectId;
import org.tcs.hackathon.kart.model.Kart;
import org.tcs.hackathon.kart.model.KartResponse;
import org.tcs.hackathon.kart.model.Product;
import org.tcs.hackathon.kart.model.ProductDetails;


//import io.vertx.axle.core.eventbus.EventBus;
import io.vertx.axle.core.eventbus.Message;

//import javax.inject.Inject;
import javax.enterprise.context.ApplicationScoped;


@Path("/kart")
@Consumes("application/json")
@Produces("application/json")
public class KartResource {
	
	
		@POST
		public Response addToKart(Kart kart){
			if(kart != null && kart.getUserId() != null){
				Kart karObj  = Kart.findByUserId(kart.getUserId());
				if(karObj != null){
					HashMap<String,Integer> myKartMap = new HashMap<String,Integer>();
					List<String> prodListexisting = karObj.getProductList();
					List<String> prodListNew = kart.getProductList();
					System.out.println("Putting in map  before: " + prodListexisting.size());
			    	if(prodListexisting != null && prodListNew != null){
			    		Iterator itr = karObj.getQtyList().iterator();
			    		for(String currentProductInDB : prodListexisting){
			    			if(itr.hasNext()){
			    				Integer qty = (Integer)(itr.next());
			    				System.out.println("Putting in map : " + currentProductInDB);
				    			myKartMap.put(currentProductInDB, qty);
			    			}else{
			    				myKartMap.put(currentProductInDB,0);
			    			}
			    			
			    		}
			    		
			    		List<Integer> updatedQtyList = new ArrayList<Integer>();
			    		Iterator itrKart = kart.getQtyList().iterator();
			    		for(String currentProductInKart : prodListNew){
			    			Integer existingQty = 0;
			    			if(myKartMap.get(currentProductInKart) != null){
			    				System.out.println("This is getting interesting Match Found currentProductInKart : " + currentProductInKart);
			    				existingQty = myKartMap.get(currentProductInKart);
			    				Integer newQty = (Integer)(itrKart.next());
			    				System.out.println("Existing Qty is  : " + existingQty);
			    				System.out.println("New Qty is  : " + newQty);
			    				int updatedQty = existingQty + newQty;
			    				System.out.println("This is getting interesting updatedQty : " + updatedQty);
			    				updatedQtyList.add(updatedQty);
			    			}else{
			    				Integer qty = (Integer)itrKart.next();
			    				System.out.println("No match found for  : " + currentProductInKart);
			    				System.out.println("No match found for  : " + qty);
			    				updatedQtyList.add(qty);
			    			}
			    		}
			    		System.out.println("My updatedQtyList is " + updatedQtyList);
			    		kart.setQtyList(updatedQtyList);
			    		kart.setUserIdentification(kart.getUserId());
			    		kart.update();
			    	}else{
			    		kart.setUserIdentification(kart.getUserId());
			    		kart.update();
			    	}
				}else{
					kart.setUserIdentification(kart.getUserId());
					kart.persist();
				}
			}
	        return Response.status(201).build();
		}
	
	    @GET
	    @Path("/count")
	    public Long count() {
	        return Kart.count();
	    }
	    
	    @DELETE
	    @Path("/{id}")
	    public void deleteKart(@PathParam("id") String id) {
	        Kart review = Kart.findById(new ObjectId(id));
	        review.delete();
	    }
		
		
	    /*
	    @GET
	    @Path("/{id}")
	    public KartResponse get(@PathParam("id") String id) {
	    	Kart karObj  = Kart.findById(new ObjectId(id));
	    	KartResponse kartResponseObj = new KartResponse();
	    	List<ProductDetails> prodDetailsList = new ArrayList<ProductDetails>();
	    	kartResponseObj.setProdDetails(prodDetailsList);
	    	//Kart karObj  = Kart.findById(new ObjectId(id));
	    	List<String> prodList = karObj.getProductList();
	    	List<Integer> qtyList = karObj.getQtyList();
	    	Iterator it = qtyList.iterator();
	    	for(String currentProduct : prodList){
	    		ProductDetails prodDetails = new ProductDetails();
	    		prodDetails.setItemId(Integer.valueOf(currentProduct));
	    		prodDetails.setQty(Integer.valueOf(it.next().toString()));
	    		Product prod = Product.findByItemId(Integer.valueOf(currentProduct));
	    		System.out.println("The product I am searching for is : " + Integer.valueOf(currentProduct) + " The object I got is : " + prod);
	    		if(prod!= null){
	    			prodDetails.setTitle(prod.getTitle());
		    		prodDetails.setPrice(prod.getPrice());
	    		}
	    		prodDetailsList.add(prodDetails);
	    	}
	    	kartResponseObj.setUserId(karObj.getUserId());
	        return kartResponseObj;
	    }
	    */
	    
	    @GET
	    @Path("/userId/{userId}")
	    public KartResponse getKartForUserId(@PathParam("userId") String userId) {
	    	KartResponse kartResponseObj = new KartResponse();
	    	if(userId == null)
	    		return kartResponseObj;
	    	Kart karObj  = Kart.findByUserId(userId);
	    	if(karObj == null)
	    		return kartResponseObj;
	    	List<ProductDetails> prodDetailsList = new ArrayList<ProductDetails>();
	    	kartResponseObj.setProdDetails(prodDetailsList);
	    	if(karObj.getProductList() != null){
	    		List<String> prodList = karObj.getProductList();
		    	List<Integer> qtyList = karObj.getQtyList();
		    	Iterator it = null;
		    	if(qtyList != null){
		    		it = qtyList.iterator();
		    	}
		    	for(String currentProduct : prodList){
		    		ProductDetails prodDetails = new ProductDetails();
		    		prodDetails.setItemId(Integer.valueOf(currentProduct));
		    		if(it != null)
		    			prodDetails.setQty(Integer.valueOf(it.next().toString()));
		    		Product prod = Product.findByItemId(Integer.valueOf(currentProduct));
		    		System.out.println("The product I am searching for is : " + Integer.valueOf(currentProduct) + " The object I got is : " + prod);
		    		if(prod!= null){
		    			prodDetails.setTitle(prod.getTitle());
			    		prodDetails.setPrice(prod.getPrice());
		    		}
		    		prodDetailsList.add(prodDetails);
		    	}
	    	}
	    	kartResponseObj.setUserId(userId);
	        return kartResponseObj;
	    }
	    
	    
	    
	    //@Inject EventBus bus;
	    
	    /* Need to remove this. This is jsut for checking if the delete-kart event is being consumed properly*/
	    /*@GET
	    @Path("/event/{userId}")
	    public Response event(@PathParam("userId") String userId) {
	    	//orderObj.setOrderIdentification(String.valueOf(orderObj.getOrderId()));
	    	//orderObj.persist();
	    	 Now that the order creation is a success. Kindly remove the kart
	    	
	    	bus.<String>send("delete-kart",userId);
	        return Response.status(201).build();
	    }*/
	    
}
