package org.lasalle.modastore.rest;

import com.google.gson.Gson;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.util.List;
import org.lasalle.modastore.controller.UserController;
import org.lasalle.modastore.model.User;

@Path("user")
public class RestUser {

    private final Gson gson = new Gson();

    @GET
    @Path("getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        try {
            UserController uc = new UserController();
            List<User> list = uc.getAll();
            return Response.ok(gson.toJson(list)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\":\"Error fetching users: " + e.getMessage() + "\"}").build();
        }
    }

    @POST
    @Path("save")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(String jsonUser) {
        try {
            User user = gson.fromJson(jsonUser, User.class);
            UserController uc = new UserController();
            boolean success = uc.save(user);

            if (success) {
                return Response.status(Response.Status.CREATED)
                        .entity("{\"success\": true, \"message\":\"User saved successfully\"}").build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("{\"success\": false, \"error\":\"Failed to save user\"}").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\":\"Error saving user: " + e.getMessage() + "\"}").build();
        }
    }

    @PUT
    @Path("update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") int id, String jsonUser) {
        try {
            User user = gson.fromJson(jsonUser, User.class);
            UserController uc = new UserController();
            boolean success = uc.update(id, user);

            if (success) {
                return Response.ok("{\"message\":\"User updated successfully\"}").build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("{\"error\":\"User not found\"}").build();
            }

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\":\"Error updating user: " + e.getMessage() + "\"}").build();
        }
    }

    @DELETE
    @Path("delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") int id) {
        try {
            UserController uc = new UserController();
            boolean success = uc.delete(id);

            if (success) {
                return Response.ok("{\"message\":\"User deleted successfully\"}").build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("{\"error\":\"User not found\"}").build();
            }

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\":\"Error deleting user: " + e.getMessage() + "\"}").build();
        }
    }

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(String jsonUser) {
        try {
            User input = gson.fromJson(jsonUser, User.class);
            UserController uc = new UserController();
            User user = uc.login(input.getUsername(), input.getPassword());

            if (user != null) {
                return Response.ok(gson.toJson(user)).build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED)
                        .entity("{\"error\":\"Invalid username or password\"}").build();
            }

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\":\"Error during login: " + e.getMessage() + "\"}").build();
        }
    }
}
