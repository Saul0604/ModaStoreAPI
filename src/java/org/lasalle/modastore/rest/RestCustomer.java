/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.lasalle.modastore.rest;

import com.google.gson.Gson;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.util.List;
import org.lasalle.modastore.controller.CustomerController;
import org.lasalle.modastore.model.Customer;

@Path("customer")
public class RestCustomer {

    @Path("getAll")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        Gson gson = new Gson();
        String out;
        try {
            CustomerController cc = new CustomerController();
            List<Customer> list = cc.getAll();
            out = gson.toJson(list);
        } catch (Exception e) {
            out = "{\"error\": \"Error fetching clients: " + e.getMessage() + "\"}";
        }
        return Response.ok(out).build();
    }

    @Path("save")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(String jsonClient) {
        Gson gson = new Gson();
        String out;
        try {
            Customer customer = gson.fromJson(jsonClient, Customer.class);
            CustomerController cc = new CustomerController();
            cc.save(customer);
            out = "{\"message\": \"Client saved successfully\"}";
            return Response.status(Response.Status.CREATED).entity(out).build();
        } catch (Exception e) {
            out = "{\"error\": \"Error saving client: " + e.getMessage() + "\"}";
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(out).build();
        }
    }

    @Path("update")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(String jsonClient) {
        Gson gson = new Gson();
        String out;
        try {
            Customer customer = gson.fromJson(jsonClient, Customer.class);
            CustomerController cc = new CustomerController();
            cc.update(customer);
            out = "{\"message\": \"Client updated successfully\"}";
        } catch (Exception e) {
            out = "{\"error\": \"Error updating client: " + e.getMessage() + "\"}";
        }
        return Response.ok(out).build();
    }

    @Path("delete/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") int id) {
        String out;
        try {
            CustomerController cc = new CustomerController();
            cc.delete(id);
            out = "{\"message\": \"Client deleted successfully\"}";
        } catch (Exception e) {
            out = "{\"error\": \"Error deleting client: " + e.getMessage() + "\"}";
        }
        return Response.ok(out).build();
    }
}
