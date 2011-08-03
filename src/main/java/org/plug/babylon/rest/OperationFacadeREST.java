package org.plug.babylon.rest;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.plug.babylon.model.Ope;
import org.plug.babylon.service.OperationService;

/**
 * REST facade for operations
 * @author Sryl <cyril.lacote@gmail.com>
 */
@Stateless
@Path("operation")
public class OperationFacadeREST {
    
    @EJB
    private OperationService service;

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Ope entity) {
        service.create(entity);
    }

    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(Ope entity) {
        service.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(
        @PathParam("id") Long id) {
        service.remove(service.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Ope find(
        @PathParam("id") Long id) {
        return service.find(id);
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Ope> findAll() {
        return service.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Ope> findRange(
        @PathParam("from") Integer from,
        @PathParam("to") Integer to) {
        return service.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String count() {
        return String.valueOf(service.count());
    }
}
