package org.tcs.hackathon.kart.resource;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.bson.types.ObjectId;
import org.tcs.hackathon.kart.model.Kart;
import org.tcs.hackathon.kart.model.KartResponse;
import org.tcs.hackathon.kart.model.Product;
import org.tcs.hackathon.kart.model.ProductDetails;


@Path("/kart")
@Consumes("application/json")
@Produces("application/json")
public class KartResource {
	
	
		@POST
		public Response addToKart(Kart kart){
			kart.persist();
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
	    
	    @GET
	    @Path("/{id}")
	    public KartResponse get(@PathParam("id") String id) {
	    	KartResponse kartResponseObj = new KartResponse();
	    	List<ProductDetails> prodDetailsList = new ArrayList<ProductDetails>();
	    	kartResponseObj.setProdDetails(prodDetailsList);
	    	Kart karObj  = Kart.findById(new ObjectId(id));
	    	List<String> prodList = karObj.getProductList();
	    	List<String> qtyList = karObj.getQtyList();
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
	    
	    @GET
	    @Path("/userId/{userId}")
	    public KartResponse getKartForUserId(@PathParam("userId") String userId) {
	    	KartResponse kartResponseObj = new KartResponse();
	    	List<ProductDetails> prodDetailsList = new ArrayList<ProductDetails>();
	    	kartResponseObj.setProdDetails(prodDetailsList);
	    	Kart karObj  = Kart.findByUserId(userId);
	    	List<String> prodList = karObj.getProductList();
	    	List<String> qtyList = karObj.getQtyList();
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
	    	kartResponseObj.setUserId(userId);
	    	
	        return kartResponseObj;
	    }
}
