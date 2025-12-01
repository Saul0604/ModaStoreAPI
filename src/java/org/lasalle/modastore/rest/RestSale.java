/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.lasalle.modastore.rest;

import com.google.gson.Gson;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.util.List;
import org.lasalle.modastore.controller.SaleController;
import org.lasalle.modastore.model.Sale;

@Path("sale")
public class RestSale {

    @GET
    @Path("getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        Gson gson = new Gson();
        String out;
        try {
            SaleController sc = new SaleController();
            List<Sale> list = sc.getAll();
            out = gson.toJson(list);
        } catch (Exception e) {
            out = "{\"error\": \"Error fetching sales: " + e.getMessage() + "\"}";
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(out).build();
        }
        return Response.ok(out).build();
    }

    @POST
    @Path("save")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(String jsonSale) {
        Gson gson = new Gson();
        String out;
        try {
            Sale sale = gson.fromJson(jsonSale, Sale.class);
            SaleController sc = new SaleController();
            sc.save(sale);
            out = "{\"message\": \"Sale saved successfully\"}";
            return Response.status(Response.Status.CREATED).entity(out).build();
        } catch (Exception e) {
            out = "{\"error\": \"Error saving sale: " + e.getMessage() + "\"}";
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(out).build();
        }
    }

    @PUT
    @Path("update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") int id, String jsonSale) {
        Gson gson = new Gson();
        String out;
        try {
            Sale sale = gson.fromJson(jsonSale, Sale.class);
            SaleController sc = new SaleController();
            boolean updated = sc.update(id, sale);
            if (updated) {
                out = "{\"message\": \"Sale updated successfully\"}";
                return Response.ok(out).build();
            } else {
                out = "{\"error\": \"Sale not found\"}";
                return Response.status(Response.Status.NOT_FOUND).entity(out).build();
            }
        } catch (Exception e) {
            out = "{\"error\": \"Error updating sale: " + e.getMessage() + "\"}";
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(out).build();
        }
    }

    @DELETE
    @Path("delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") int id) {
        Gson gson = new Gson();
        String out;
        try {
            SaleController sc = new SaleController();
            boolean deleted = sc.delete(id);
            if (deleted) {
                out = "{\"message\": \"Sale deleted successfully\"}";
                return Response.ok(out).build();
            } else {
                out = "{\"error\": \"Sale not found\"}";
                return Response.status(Response.Status.NOT_FOUND).entity(out).build();
            }
        } catch (Exception e) {
            out = "{\"error\": \"Error deleting sale: " + e.getMessage() + "\"}";
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(out).build();
        }
    }
}
