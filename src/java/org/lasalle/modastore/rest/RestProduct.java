/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.lasalle.modastore.rest;

import com.google.gson.Gson;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.util.List;
import org.lasalle.modastore.controller.ProductController;
import org.lasalle.modastore.model.Product;

@Path("product")
public class RestProduct {

    @Path("getAll")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        Gson gson = new Gson();
        String out;
        try {
            ProductController pc = new ProductController();
            List<Product> list = pc.getAll();
            out = gson.toJson(list);
        } catch (Exception e) {
            out = "{\"error\": \"Error fetching products: " + e.getMessage() + "\"}";
        }
        return Response.ok(out).build();
    }

    @Path("save")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(String jsonProduct) {
        Gson gson = new Gson();
        String out;
        try {
            Product product = gson.fromJson(jsonProduct, Product.class);
            ProductController pc = new ProductController();
            pc.save(product);
            out = "{\"message\": \"Product saved successfully\"}";
            return Response.status(Response.Status.CREATED).entity(out).build();
        } catch (Exception e) {
            out = "{\"error\": \"Error saving product: " + e.getMessage() + "\"}";
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(out).build();
        }
    }

    @Path("update")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(String jsonProduct) {
        Gson gson = new Gson();
        String out;
        try {
            Product product = gson.fromJson(jsonProduct, Product.class);
            ProductController pc = new ProductController();
            pc.update(product);
            out = "{\"message\": \"Product updated successfully\"}";
            return Response.ok(out).build();
        } catch (Exception e) {
            out = "{\"error\": \"Error updating product: " + e.getMessage() + "\"}";
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(out).build();
        }
    }

    @Path("delete/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProduct(@PathParam("id") int id) {
        Gson gson = new Gson();
        String out;
        try {
            ProductController pc = new ProductController();
            boolean success = pc.delete(id);

            if (success) {
                out = "{\"success\": true, \"message\": \"Product deleted successfully\"}";
                return Response.ok(out).build();
            } else {
                out = "{\"success\": false, \"message\": \"Product not found or already deleted\"}";
                return Response.status(Response.Status.NOT_FOUND).entity(out).build();
            }
        } catch (Exception e) {
            out = "{\"success\": false, \"error\": \"" + e.getMessage() + "\"}";
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(out).build();
        }
    }
}
