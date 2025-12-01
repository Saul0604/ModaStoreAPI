/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.lasalle.modastore.rest;

import com.google.gson.Gson;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import org.lasalle.modastore.model.Inventory;
import org.lasalle.modastore.controller.InventoryController;

/**
 * REST endpoints for Inventory
 * @author Saul
 */
@Path("inventory")
public class RestInventory {

    @Path("getAll")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        String out;
        Gson gson = new Gson();
        try {
            InventoryController ic = new InventoryController();
            List<Inventory> list = ic.getAll();
            out = gson.toJson(list);
        } catch (Exception e) {
            out = String.format("{\"error\":\"%s\"}", e.getMessage());
        }
        return Response.ok(out).build();
    }

    @Path("getById/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") int id) {
        String out;
        Gson gson = new Gson();
        try {
            InventoryController ic = new InventoryController();
            Inventory inv = ic.getById(id);
            if (inv != null) {
                out = gson.toJson(inv);
            } else {
                out = "{\"message\":\"Inventory item not found\"}";
            }
        } catch (Exception e) {
            out = String.format("{\"error\":\"%s\"}", e.getMessage());
        }
        return Response.ok(out).build();
    }

    @Path("save")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(String jsonInventory) {
        Gson gson = new Gson();
        String out;
        try {
            Inventory inv = gson.fromJson(jsonInventory, Inventory.class);
            InventoryController ic = new InventoryController();
            ic.save(inv);
            out = "{\"message\":\"Inventory saved successfully\"}";
            return Response.status(Response.Status.CREATED).entity(out).build();
        } catch (Exception e) {
            out = String.format("{\"error\":\"Error saving inventory: %s\"}", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(out).build();
        }
    }
}