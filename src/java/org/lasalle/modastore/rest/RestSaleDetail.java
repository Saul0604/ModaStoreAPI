/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.lasalle.modastore.rest;

import com.google.gson.Gson;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.util.List;
import org.lasalle.modastore.controller.SaleDetailController;
import org.lasalle.modastore.model.SaleDetail;

@Path("saledetail")
public class RestSaleDetail {

    @Path("getBySale/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBySale(@PathParam("id") int id) {
        Gson gson = new Gson();
        String out;
        try {
            SaleDetailController sdc = new SaleDetailController();
            List<SaleDetail> list = sdc.getBySale(id);
            out = gson.toJson(list);
        } catch (Exception e) {
            out = "{\"error\": \"Error fetching sale details: " + e.getMessage() + "\"}";
        }
        return Response.ok(out).build();
    }

    @Path("save")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(String jsonDetail) {
        Gson gson = new Gson();
        String out;
        try {
            SaleDetail detail = gson.fromJson(jsonDetail, SaleDetail.class);
            SaleDetailController sdc = new SaleDetailController();
            sdc.save(detail);
            out = "{\"message\": \"Sale detail saved successfully\"}";
            return Response.status(Response.Status.CREATED).entity(out).build();
        } catch (Exception e) {
            out = "{\"error\": \"Error saving sale detail: " + e.getMessage() + "\"}";
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(out).build();
        }
    }

  
    @Path("delete/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") int id) {
        Gson gson = new Gson();
        String out;
        try {
            SaleDetailController sdc = new SaleDetailController();
            boolean deleted = sdc.delete(id);
            if (deleted) {
                out = "{\"message\": \"Sale detail deleted successfully\"}";
                return Response.ok(out).build();
            } else {
                out = "{\"error\": \"No sale detail found with that ID\"}";
                return Response.status(Response.Status.NOT_FOUND).entity(out).build();
            }
        } catch (Exception e) {
            out = "{\"error\": \"Error deleting sale detail: " + e.getMessage() + "\"}";
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(out).build();
        }
    }
}
