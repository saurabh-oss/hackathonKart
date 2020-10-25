package org.tcs.hackathon.kart.resource;

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


@Path("/kart")
@Consumes("application/json")
@Produces("application/json")
public class KartResource {
	
	
		@GET
	    public List<Kart> list() {
	        return Kart.listAll();
	    }
	
	    @POST
	    public Response createKart(Kart kart) {
	        kart.persist();
	        return Response.status(201).build();
	    }

	    @PUT
	    @Path("/{id}")
	    public void updateKart(@PathParam("id") String id, Kart review) {
	    	review.update();
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
	    public Kart get(@PathParam("id") String id) {
	        return Kart.findById(new ObjectId(id));
	    }
	
}
