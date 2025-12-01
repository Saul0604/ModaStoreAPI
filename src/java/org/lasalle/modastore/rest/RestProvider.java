/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.lasalle.modastore.rest;

import com.google.gson.Gson;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.util.List;
import org.lasalle.modastore.controller.ProviderController;
import org.lasalle.modastore.model.Provider;

@Path("provider")
public class RestProvider {
    
    /**
     * Obtener todos los proveedores activos
     * GET: /provider/getAll
     */
    @Path("getAll")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        Gson gson = new Gson();
        String out;
        try {
            ProviderController pc = new ProviderController();
            List<Provider> list = pc.getAll();
            out = gson.toJson(list);
        } catch (Exception e) {
            out = "{\"error\": \"Error fetching providers: " + e.getMessage() + "\"}";
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(out).build();
        }
        return Response.ok(out).build();
    }
    
    /**
     * Obtener proveedor por ID
     * GET: /provider/get/{id}
     */
    @Path("get/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") int id) {
        Gson gson = new Gson();
        String out;
        try {
            ProviderController pc = new ProviderController();
            Provider provider = pc.getById(id);
            
            if (provider != null) {
                out = gson.toJson(provider);
                return Response.ok(out).build();
            } else {
                out = "{\"error\": \"Provider not found\"}";
                return Response.status(Response.Status.NOT_FOUND).entity(out).build();
            }
        } catch (Exception e) {
            out = "{\"error\": \"Error fetching provider: " + e.getMessage() + "\"}";
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(out).build();
        }
    }
    
    /**
     * Crear nuevo proveedor
     * POST: /provider/save
     */
    @Path("save")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(String jsonProvider) {
        Gson gson = new Gson();
        String out;
        try {
            Provider provider = gson.fromJson(jsonProvider, Provider.class);
            ProviderController pc = new ProviderController();
            boolean success = pc.save(provider);
            
            if (success) {
                out = "{\"success\": true, \"message\": \"Provider saved successfully\"}";
                return Response.status(Response.Status.CREATED).entity(out).build();
            } else {
                out = "{\"success\": false, \"error\": \"Failed to save provider\"}";
                return Response.status(Response.Status.BAD_REQUEST).entity(out).build();
            }
        } catch (Exception e) {
            out = "{\"error\": \"Error saving provider: " + e.getMessage() + "\"}";
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(out).build();
        }
    }
    
    /**
     * Actualizar proveedor
     * PUT: /provider/update
     */
    @Path("update")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(String jsonProvider) {
        Gson gson = new Gson();
        String out;
        try {
            Provider provider = gson.fromJson(jsonProvider, Provider.class);
            ProviderController pc = new ProviderController();
            boolean success = pc.update(provider);
            
            if (success) {
                out = "{\"success\": true, \"message\": \"Provider updated successfully\"}";
                return Response.ok(out).build();
            } else {
                out = "{\"success\": false, \"error\": \"Provider not found or failed to update\"}";
                return Response.status(Response.Status.NOT_FOUND).entity(out).build();
            }
        } catch (Exception e) {
            out = "{\"error\": \"Error updating provider: " + e.getMessage() + "\"}";
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(out).build();
        }
    }
    
    /**
     * Eliminar proveedor (Soft Delete)
     * DELETE: /provider/delete/{id}
     */
    @Path("delete/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") int id) {
        String out;
        try {
            ProviderController pc = new ProviderController();
            boolean success = pc.delete(id);
            
            if (success) {
                out = "{\"success\": true, \"message\": \"Provider deleted successfully\"}";
                return Response.ok(out).build();
            } else {
                out = "{\"success\": false, \"message\": \"Provider not found or already deleted\"}";
                return Response.status(Response.Status.NOT_FOUND).entity(out).build();
            }
        } catch (Exception e) {
            out = "{\"success\": false, \"error\": \"Error deleting provider: " + e.getMessage() + "\"}";
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(out).build();
        }
    }
}