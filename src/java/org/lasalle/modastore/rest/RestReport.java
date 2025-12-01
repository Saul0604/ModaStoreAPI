/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.lasalle.modastore.rest;

import com.google.gson.Gson;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.util.List;
import org.lasalle.modastore.controller.ReportController;
import org.lasalle.modastore.model.*;

@Path("reports")
public class RestReport {
    
    // =====================================================
    // ENDPOINTS DE REPORTES DE VENTAS
    // =====================================================
    
    /**
     * Obtener reporte de ventas del día actual
     * GET: /reports/sales/today
     */
    @Path("sales/today")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSalesToday() {
        Gson gson = new Gson();
        String out;
        try {
            ReportController rc = new ReportController();
            ReportSales report = rc.getDailySalesReport();
            out = gson.toJson(report);
        } catch (Exception e) {
            out = "{\"error\": \"" + e.getMessage() + "\"}";
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(out).build();
        }
        return Response.ok(out).build();
    }
    
    /**
     * Obtener reporte de ventas por período
     * GET: /reports/sales/{period}?date=2024-03-17
     * period: daily, weekly, monthly, yearly
     */
    @Path("sales/{period}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSalesByPeriod(
            @PathParam("period") String period,
            @QueryParam("date") String date) {
        Gson gson = new Gson();
        String out;
        try {
            ReportController rc = new ReportController();
            List<ReportSales> reports = rc.getSalesReportByPeriod(period, date);
            out = gson.toJson(reports);
        } catch (Exception e) {
            out = "{\"error\": \"" + e.getMessage() + "\"}";
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(out).build();
        }
        return Response.ok(out).build();
    }
    
    /**
     * Generar reporte de ventas para una fecha específica
     * POST: /reports/sales/generate?date=2024-03-17
     */
    @Path("sales/generate")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response generateSalesReport(@QueryParam("date") String date) {
        Gson gson = new Gson();
        String out;
        try {
            ReportController rc = new ReportController();
            boolean success = rc.generateDailySalesReport(date);
            out = "{\"success\": " + success + ", \"message\": \"Sales report generated\"}";
        } catch (Exception e) {
            out = "{\"error\": \"" + e.getMessage() + "\"}";
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(out).build();
        }
        return Response.status(Response.Status.CREATED).entity(out).build();
    }
    
    // =====================================================
    // ENDPOINTS DE PRODUCTOS MÁS VENDIDOS
    // =====================================================
    
    /**
     * Obtener top 10 productos más vendidos
     * GET: /reports/top-products?period=monthly&limit=10
     */
    @Path("top-products")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTopProducts(
            @QueryParam("period") @DefaultValue("monthly") String period,
            @QueryParam("limit") @DefaultValue("10") int limit) {
        Gson gson = new Gson();
        String out;
        try {
            ReportController rc = new ReportController();
            List<ReportTopProducts> products = rc.getTopProducts(period, limit);
            out = gson.toJson(products);
        } catch (Exception e) {
            out = "{\"error\": \"" + e.getMessage() + "\"}";
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(out).build();
        }
        return Response.ok(out).build();
    }
    
    /**
     * Generar reporte de productos más vendidos CON PARÁMETROS
     * POST: /reports/top-products/generate?date=2024-03-17&period=daily
     */
    @Path("top-products/generate")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response generateTopProductsReport(
            @QueryParam("date") String date,
            @QueryParam("period") @DefaultValue("monthly") String period) {
        Gson gson = new Gson();
        String out;
        try {
            ReportController rc = new ReportController();
            boolean success = rc.generateTopProductsReport(date, period);
            out = "{\"success\": " + success + ", \"message\": \"Top products report generated for " + period + "\"}";
        } catch (Exception e) {
            out = "{\"error\": \"" + e.getMessage() + "\"}";
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(out).build();
        }
        return Response.status(Response.Status.CREATED).entity(out).build();
    }
    
    // =====================================================
    // ENDPOINTS DE INVENTARIO BAJO
    // =====================================================
    
    /**
     * Obtener productos con stock bajo
     * GET: /reports/low-stock?status=critical
     * status: critical, low, all
     */
    @Path("low-stock")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLowStock(
            @QueryParam("status") @DefaultValue("all") String status) {
        Gson gson = new Gson();
        String out;
        try {
            ReportController rc = new ReportController();
            List<ReportLowStock> products = rc.getLowStockProducts(status);
            out = gson.toJson(products);
        } catch (Exception e) {
            out = "{\"error\": \"" + e.getMessage() + "\"}";
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(out).build();
        }
        return Response.ok(out).build();
    }
    
    /**
     * Actualizar reporte de inventario bajo
     * POST: /reports/low-stock/update
     */
    @Path("low-stock/update")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateLowStockReport() {
        Gson gson = new Gson();
        String out;
        try {
            ReportController rc = new ReportController();
            boolean success = rc.updateLowStockReport();
            out = "{\"success\": " + success + ", \"message\": \"Low stock report updated\"}";
        } catch (Exception e) {
            out = "{\"error\": \"" + e.getMessage() + "\"}";
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(out).build();
        }
        return Response.ok(out).build();
    }
    
    // =====================================================
    // ENDPOINTS DE VENTAS POR CATEGORÍA
    // =====================================================
    
    /**
     * Obtener ventas por categoría
     * GET: /reports/sales-by-category?period=monthly
     */
    @Path("sales-by-category")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSalesByCategory(
            @QueryParam("period") @DefaultValue("monthly") String period) {
        Gson gson = new Gson();
        String out;
        try {
            ReportController rc = new ReportController();
            List<ReportSalesByCategory> categories = rc.getSalesByCategory(period);
            out = gson.toJson(categories);
        } catch (Exception e) {
            out = "{\"error\": \"" + e.getMessage() + "\"}";
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(out).build();
        }
        return Response.ok(out).build();
    }
    
    /**
     * Generar reporte de ventas por categoría
     * POST: /reports/sales-by-category/generate?date=2024-03-17&period=monthly
     */
    @Path("sales-by-category/generate")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response generateSalesByCategoryReport(
            @QueryParam("date") String date,
            @QueryParam("period") @DefaultValue("monthly") String period) {
        Gson gson = new Gson();
        String out;
        try {
            ReportController rc = new ReportController();
            boolean success = rc.generateSalesByCategoryReport(date, period);
            out = "{\"success\": " + success + ", \"message\": \"Sales by category report generated\"}";
        } catch (Exception e) {
            out = "{\"error\": \"" + e.getMessage() + "\"}";
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(out).build();
        }
        return Response.status(Response.Status.CREATED).entity(out).build();
    }
    
    // =====================================================
    // ENDPOINTS DE VENTAS POR VENDEDOR
    // =====================================================
    
    /**
     * Obtener rendimiento de vendedores
     * GET: /reports/sales-by-user?period=monthly
     */
    @Path("sales-by-user")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSalesByUser(
            @QueryParam("period") @DefaultValue("monthly") String period) {
        Gson gson = new Gson();
        String out;
        try {
            ReportController rc = new ReportController();
            List<ReportSalesByUser> users = rc.getSalesByUser(period);
            out = gson.toJson(users);
        } catch (Exception e) {
            out = "{\"error\": \"" + e.getMessage() + "\"}";
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(out).build();
        }
        return Response.ok(out).build();
    }
    
    /**
     * Obtener mejor vendedor del período
     * GET: /reports/top-seller?period=monthly
     */
    @Path("top-seller")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTopSeller(
            @QueryParam("period") @DefaultValue("monthly") String period) {
        Gson gson = new Gson();
        String out;
        try {
            ReportController rc = new ReportController();
            ReportSalesByUser topSeller = rc.getTopSeller(period);
            out = gson.toJson(topSeller);
        } catch (Exception e) {
            out = "{\"error\": \"" + e.getMessage() + "\"}";
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(out).build();
        }
        return Response.ok(out).build();
    }
    
    /**
     * Generar reporte de ventas por usuario
     * POST: /reports/sales-by-user/generate?date=2024-03-17&period=monthly
     */
    @Path("sales-by-user/generate")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response generateSalesByUserReport(
            @QueryParam("date") String date,
            @QueryParam("period") @DefaultValue("monthly") String period) {
        Gson gson = new Gson();
        String out;
        try {
            ReportController rc = new ReportController();
            boolean success = rc.generateSalesByUserReport(date, period);
            out = "{\"success\": " + success + ", \"message\": \"Sales by user report generated\"}";
        } catch (Exception e) {
            out = "{\"error\": \"" + e.getMessage() + "\"}";
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(out).build();
        }
        return Response.status(Response.Status.CREATED).entity(out).build();
    }
    
    // =====================================================
    // ENDPOINT PARA GENERAR TODOS LOS REPORTES
    // =====================================================
    
    /**
     * Generar todos los reportes del día
     * POST: /reports/generate-all?date=2024-03-17
     */
    @Path("generate-all")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response generateAllReports(
            @QueryParam("date") String date) {
        Gson gson = new Gson();
        String out;
        try {
            ReportController rc = new ReportController();
            boolean success = rc.generateAllReports(date);
            out = "{\"success\": " + success + ", \"message\": \"All reports generated successfully\"}";
        } catch (Exception e) {
            out = "{\"error\": \"" + e.getMessage() + "\"}";
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(out).build();
        }
        return Response.status(Response.Status.CREATED).entity(out).build();
    }
    
    // =====================================================
    // ENDPOINTS DE VISTAS EN TIEMPO REAL
    // =====================================================
    
    /**
     * Ver productos más vendidos en tiempo real (sin esperar reportes)
     * GET: /reports/realtime/top-products?limit=10
     */
    @Path("realtime/top-products")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRealtimeTopProducts(
            @QueryParam("limit") @DefaultValue("10") int limit) {
        Gson gson = new Gson();
        String out;
        try {
            ReportController rc = new ReportController();
            List<ReportTopProducts> products = rc.getRealtimeTopProducts(limit);
            out = gson.toJson(products);
        } catch (Exception e) {
            out = "{\"error\": \"" + e.getMessage() + "\"}";
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(out).build();
        }
        return Response.ok(out).build();
    }
    
    /**
     * Ver ventas diarias en tiempo real
     * GET: /reports/realtime/daily-sales?days=7
     */
    @Path("realtime/daily-sales")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRealtimeDailySales(
            @QueryParam("days") @DefaultValue("7") int days) {
        Gson gson = new Gson();
        String out;
        try {
            ReportController rc = new ReportController();
            List<ReportSales> sales = rc.getRealtimeDailySales(days);
            out = gson.toJson(sales);
        } catch (Exception e) {
            out = "{\"error\": \"" + e.getMessage() + "\"}";
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(out).build();
        }
        return Response.ok(out).build();
    }
    
    /**
     * Ver dashboard completo (resumen de todos los reportes)
     * GET: /reports/dashboard
     */
    @Path("dashboard")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDashboard() {
        Gson gson = new Gson();
        String out;
        try {
            ReportController rc = new ReportController();
            out = rc.getDashboardData();
        } catch (Exception e) {
            out = "{\"error\": \"" + e.getMessage() + "\"}";
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(out).build();
        }
        return Response.ok(out).build();
    }
}