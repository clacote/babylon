/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.plug.babylon.rest;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.plug.babylon.model.Account;

/**
 *
 * @author Sryl <cyril.lacote@gmail.com>
 */
@Stateless
@Path("account")
public class AccountFacadeREST extends AbstractFacade<Account> {
    @PersistenceContext(unitName = "PU")
    private EntityManager em;

    public AccountFacadeREST() {
        super(Account.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Account entity) {
        super.create(entity);
    }

    @PUT
    @Override
    @Consumes({"application/xml", "application/json"})
    public void edit(Account entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id")
    Long id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Account find(@PathParam("id")
    Long id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Account> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Account> findRange(@PathParam("from")
    Integer from, @PathParam("to")
    Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @java.lang.Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
