package org.lasalle.modastore.controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.lasalle.modastore.model.*;
import java.math.BigDecimal;

public class ReportController {
    
    // =====================================================
    // MÉTODOS DE REPORTES DE VENTAS
    // =====================================================
    
    /**
     * Obtener reporte de ventas del día actual
     */
    public ReportSales getDailySalesReport() {
        String query = "SELECT * FROM report_sales WHERE report_date = CURDATE() AND period_type = 'daily'";
        ReportSales report = null;
        
        try {
            ConexionMysql connMysql = new ConexionMysql();
            Connection conn = connMysql.open();
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                report = mapReportSales(rs);
            }
            
            rs.close();
            pstmt.close();
            connMysql.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return report;
    }
    
    /**
     * Obtener reportes de ventas por período
     */
    public List<ReportSales> getSalesReportByPeriod(String period, String date) {
        List<ReportSales> reports = new ArrayList<>();
        String query = "SELECT * FROM report_sales WHERE period_type = ? " +
                      (date != null ? "AND report_date = ? " : "") + 
                      "ORDER BY report_date DESC";
        
        try {
            ConexionMysql connMysql = new ConexionMysql();
            Connection conn = connMysql.open();
            PreparedStatement pstmt = conn.prepareStatement(query);
            
            pstmt.setString(1, period);
            if (date != null) {
                pstmt.setString(2, date);
            }
            
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                reports.add(mapReportSales(rs));
            }
            
            rs.close();
            pstmt.close();
            connMysql.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reports;
    }
    
    /**
     * Generar reporte de ventas para una fecha específica
     */
    public boolean generateDailySalesReport(String dateStr) {
        String call = "{CALL sp_generate_daily_sales_report(?)}";
        
        try {
            ConexionMysql connMysql = new ConexionMysql();
            Connection conn = connMysql.open();
            CallableStatement cstmt = conn.prepareCall(call);
            
            cstmt.setString(1, dateStr);
            cstmt.execute();
            
            cstmt.close();
            connMysql.close();
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // =====================================================
    // MÉTODOS DE PRODUCTOS MÁS VENDIDOS
    // =====================================================
    
    /**
     * Obtener top productos más vendidos
     */
    public List<ReportTopProducts> getTopProducts(String period, int limit) {
        List<ReportTopProducts> products = new ArrayList<>();
        String query = "SELECT * FROM report_top_products " +
                      "WHERE period_type = ? " +
                      "ORDER BY quantity_sold DESC LIMIT ?";
        
        try {
            ConexionMysql connMysql = new ConexionMysql();
            Connection conn = connMysql.open();
            PreparedStatement pstmt = conn.prepareStatement(query);
            
            pstmt.setString(1, period);
            pstmt.setInt(2, limit);
            
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                products.add(mapReportTopProducts(rs));
            }
            
            rs.close();
            pstmt.close();
            connMysql.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }
    
    /**
     * Generar reporte de productos más vendidos
     */
    /**
 * Generar reporte de productos más vendidos con parámetros
 */
public boolean generateTopProductsReport(String date, String period) {
    String call = "{CALL sp_generate_top_products_report(?, ?)}";
    
    try {
        ConexionMysql connMysql = new ConexionMysql();
        Connection conn = connMysql.open();
        CallableStatement cstmt = conn.prepareCall(call);
        
        // Si date es null, usa la fecha actual
        if (date == null || date.isEmpty()) {
            cstmt.setDate(1, new java.sql.Date(System.currentTimeMillis()));
        } else {
            cstmt.setString(1, date);
        }
        
        // Si period es null, usa 'monthly'
        cstmt.setString(2, period != null ? period : "monthly");
        
        cstmt.execute();
        
        cstmt.close();
        connMysql.close();
        conn.close();
        
        System.out.println("✅ Reporte de productos generado: " + period + " - " + date);
        return true;
    } catch (Exception e) {
        System.err.println("❌ Error al generar reporte de productos: " + e.getMessage());
        e.printStackTrace();
        return false;
    }
}

/**
 * Generar reporte de ventas por categoría
 */
public boolean generateSalesByCategoryReport(String date, String period) {
    String call = "{CALL sp_generate_sales_by_category_report(?, ?)}";
    
    try {
        ConexionMysql connMysql = new ConexionMysql();
        Connection conn = connMysql.open();
        CallableStatement cstmt = conn.prepareCall(call);
        
        if (date == null || date.isEmpty()) {
            cstmt.setDate(1, new java.sql.Date(System.currentTimeMillis()));
        } else {
            cstmt.setString(1, date);
        }
        
        cstmt.setString(2, period != null ? period : "monthly");
        cstmt.execute();
        
        cstmt.close();
        connMysql.close();
        conn.close();
        
        System.out.println("✅ Reporte de categorías generado: " + period + " - " + date);
        return true;
    } catch (Exception e) {
        System.err.println("❌ Error al generar reporte de categorías: " + e.getMessage());
        e.printStackTrace();
        return false;
    }
}

/**
 * Generar reporte de ventas por usuario
 */
public boolean generateSalesByUserReport(String date, String period) {
    String call = "{CALL sp_generate_sales_by_user_report(?, ?)}";
    
    try {
        ConexionMysql connMysql = new ConexionMysql();
        Connection conn = connMysql.open();
        CallableStatement cstmt = conn.prepareCall(call);
        
        if (date == null || date.isEmpty()) {
            cstmt.setDate(1, new java.sql.Date(System.currentTimeMillis()));
        } else {
            cstmt.setString(1, date);
        }
        
        cstmt.setString(2, period != null ? period : "monthly");
        cstmt.execute();
        
        cstmt.close();
        connMysql.close();
        conn.close();
        
        System.out.println("✅ Reporte de usuarios generado: " + period + " - " + date);
        return true;
    } catch (Exception e) {
        System.err.println("❌ Error al generar reporte de usuarios: " + e.getMessage());
        e.printStackTrace();
        return false;
    }
}
    
    // =====================================================
    // MÉTODOS DE INVENTARIO BAJO
    // =====================================================
    
    /**
     * Obtener productos con stock bajo
     */
    public List<ReportLowStock> getLowStockProducts(String status) {
        List<ReportLowStock> products = new ArrayList<>();
        String query = "SELECT * FROM report_low_stock ";
        
        if (!status.equals("all")) {
            query += "WHERE status = ? ";
        }
        query += "ORDER BY current_stock ASC";
        
        try {
            ConexionMysql connMysql = new ConexionMysql();
            Connection conn = connMysql.open();
            PreparedStatement pstmt = conn.prepareStatement(query);
            
            if (!status.equals("all")) {
                pstmt.setString(1, status);
            }
            
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                products.add(mapReportLowStock(rs));
            }
            
            rs.close();
            pstmt.close();
            connMysql.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }
    
    /**
     * Actualizar reporte de inventario bajo
     */
    public boolean updateLowStockReport() {
        String call = "{CALL sp_update_low_stock_report()}";
        
        try {
            ConexionMysql connMysql = new ConexionMysql();
            Connection conn = connMysql.open();
            CallableStatement cstmt = conn.prepareCall(call);
            
            cstmt.execute();
            
            cstmt.close();
            connMysql.close();
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // =====================================================
    // MÉTODOS DE VENTAS POR CATEGORÍA
    // =====================================================
    
    /**
     * Obtener ventas por categoría
     */
    public List<ReportSalesByCategory> getSalesByCategory(String period) {
        List<ReportSalesByCategory> categories = new ArrayList<>();
        String query = "SELECT * FROM report_sales_by_category " +
                      "WHERE period_type = ? " +
                      "ORDER BY total_sales DESC";
        
        try {
            ConexionMysql connMysql = new ConexionMysql();
            Connection conn = connMysql.open();
            PreparedStatement pstmt = conn.prepareStatement(query);
            
            pstmt.setString(1, period);
            
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                categories.add(mapReportSalesByCategory(rs));
            }
            
            rs.close();
            pstmt.close();
            connMysql.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categories;
    }
    
    // =====================================================
    // MÉTODOS DE VENTAS POR USUARIO
    // =====================================================
    
    /**
     * Obtener ventas por usuario
     */
    public List<ReportSalesByUser> getSalesByUser(String period) {
        List<ReportSalesByUser> users = new ArrayList<>();
        String query = "SELECT * FROM report_sales_by_user " +
                      "WHERE period_type = ? " +
                      "ORDER BY total_sales DESC";
        
        try {
            ConexionMysql connMysql = new ConexionMysql();
            Connection conn = connMysql.open();
            PreparedStatement pstmt = conn.prepareStatement(query);
            
            pstmt.setString(1, period);
            
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                users.add(mapReportSalesByUser(rs));
            }
            
            rs.close();
            pstmt.close();
            connMysql.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }
    
    /**
     * Obtener mejor vendedor
     */
    public ReportSalesByUser getTopSeller(String period) {
        ReportSalesByUser topSeller = null;
        String query = "SELECT * FROM report_sales_by_user " +
                      "WHERE period_type = ? " +
                      "ORDER BY total_sales DESC LIMIT 1";
        
        try {
            ConexionMysql connMysql = new ConexionMysql();
            Connection conn = connMysql.open();
            PreparedStatement pstmt = conn.prepareStatement(query);
            
            pstmt.setString(1, period);
            
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                topSeller = mapReportSalesByUser(rs);
            }
            
            rs.close();
            pstmt.close();
            connMysql.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return topSeller;
    }
    
    // =====================================================
    // GENERAR TODOS LOS REPORTES
    // =====================================================
    
    /**
     * Generar todos los reportes del día
     */
    public boolean generateAllReports(String date) {
        String call = "{CALL sp_generate_all_daily_reports(?)}";
        
        try {
            ConexionMysql connMysql = new ConexionMysql();
            Connection conn = connMysql.open();
            CallableStatement cstmt = conn.prepareCall(call);
            
            cstmt.setString(1, date != null ? date : "CURDATE()");
            cstmt.execute();
            
            cstmt.close();
            connMysql.close();
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // =====================================================
    // MÉTODOS DE TIEMPO REAL (VISTAS)
    // =====================================================
    
    /**
     * Obtener productos más vendidos en tiempo real
     */
    public List<ReportTopProducts> getRealtimeTopProducts(int limit) {
        List<ReportTopProducts> products = new ArrayList<>();
        String query = "SELECT * FROM v_top_selling_products LIMIT ?";
        
        try {
            ConexionMysql connMysql = new ConexionMysql();
            Connection conn = connMysql.open();
            PreparedStatement pstmt = conn.prepareStatement(query);
            
            pstmt.setInt(1, limit);
            
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                ReportTopProducts product = new ReportTopProducts();
                product.setIdProduct(rs.getInt("id_product"));
                product.setProductName(rs.getString("product_name"));
                product.setCategory(rs.getString("category"));
                product.setQuantitySold(rs.getInt("total_quantity_sold"));
                product.setTotalRevenue(rs.getBigDecimal("total_revenue"));
                products.add(product);
            }
            
            rs.close();
            pstmt.close();
            connMysql.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }
    
    /**
     * Obtener ventas diarias en tiempo real
     */
    public List<ReportSales> getRealtimeDailySales(int days) {
        List<ReportSales> sales = new ArrayList<>();
        String query = "SELECT * FROM v_daily_sales ORDER BY sale_day DESC LIMIT ?";
        
        try {
            ConexionMysql connMysql = new ConexionMysql();
            Connection conn = connMysql.open();
            PreparedStatement pstmt = conn.prepareStatement(query);
            
            pstmt.setInt(1, days);
            
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                ReportSales report = new ReportSales();
                report.setReportDate(rs.getDate("sale_day"));
                report.setTotalTransactions(rs.getInt("total_transactions"));
                report.setTotalSales(rs.getBigDecimal("total_sales"));
                report.setAverageTicket(rs.getBigDecimal("average_ticket"));
                report.setTotalProductsSold(rs.getInt("total_items_sold"));
                sales.add(report);
            }
            
            rs.close();
            pstmt.close();
            connMysql.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sales;
    }
    
    /**
     * Obtener datos del dashboard
     */
    public String getDashboardData() {
        StringBuilder json = new StringBuilder("{");
        
        try {
            // Ventas del día
            ReportSales todaySales = getDailySalesReport();
            BigDecimal todayTotal = todaySales != null ? todaySales.getTotalSales() : BigDecimal.ZERO;
            json.append("\"todaySales\": ").append(todayTotal).append(",");
            
            // Top 5 productos
            List<ReportTopProducts> topProducts = getRealtimeTopProducts(5);
            json.append("\"topProductsCount\": ").append(topProducts.size()).append(",");
            
            // Productos con stock crítico
            List<ReportLowStock> criticalStock = getLowStockProducts("critical");
            json.append("\"criticalStockCount\": ").append(criticalStock.size()).append(",");
            
            // Total ventas del mes
            List<ReportSales> monthlySales = getSalesReportByPeriod("monthly", null);
            BigDecimal monthlyTotal = monthlySales.isEmpty() ? BigDecimal.ZERO : monthlySales.get(0).getTotalSales();
            json.append("\"monthlyTotal\": ").append(monthlyTotal);
            
            json.append("}");
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"error\": \"" + e.getMessage() + "\"}";
        }
        
        return json.toString();
    }
    
    // =====================================================
    // MÉTODOS AUXILIARES DE MAPEO
    // =====================================================
    
    private ReportSales mapReportSales(ResultSet rs) throws Exception {
        ReportSales report = new ReportSales();
        report.setIdReportSales(rs.getInt("id_report_sales"));
        report.setReportDate(rs.getDate("report_date"));
        report.setPeriodType(rs.getString("period_type"));
        report.setTotalSales(rs.getBigDecimal("total_sales"));
        report.setTotalTransactions(rs.getInt("total_transactions"));
        report.setTotalProductsSold(rs.getInt("total_products_sold"));
        report.setAverageTicket(rs.getBigDecimal("average_ticket"));
        report.setGenerationDate(rs.getTimestamp("generation_date"));
        return report;
    }
    
    private ReportTopProducts mapReportTopProducts(ResultSet rs) throws Exception {
        ReportTopProducts product = new ReportTopProducts();
        product.setIdReportTop(rs.getInt("id_report_top"));
        product.setReportDate(rs.getDate("report_date"));
        product.setPeriodType(rs.getString("period_type"));
        product.setIdProduct(rs.getInt("id_product"));
        product.setProductName(rs.getString("product_name"));
        product.setCategory(rs.getString("category"));
        product.setQuantitySold(rs.getInt("quantity_sold"));
        product.setTotalRevenue(rs.getBigDecimal("total_revenue"));
        product.setGenerationDate(rs.getTimestamp("generation_date"));
        return product;
    }
    
    private ReportLowStock mapReportLowStock(ResultSet rs) throws Exception {
        ReportLowStock stock = new ReportLowStock();
        stock.setIdReportStock(rs.getInt("id_report_stock"));
        stock.setIdProduct(rs.getInt("id_product"));
        stock.setProductName(rs.getString("product_name"));
        stock.setCategory(rs.getString("category"));
        stock.setCurrentStock(rs.getInt("current_stock"));
        stock.setMinStockRequired(rs.getInt("min_stock_required"));
        stock.setStatus(rs.getString("status"));
        stock.setLastCheck(rs.getTimestamp("last_check"));
        return stock;
    }
    
    private ReportSalesByCategory mapReportSalesByCategory(ResultSet rs) throws Exception {
        ReportSalesByCategory category = new ReportSalesByCategory();
        category.setIdReportCategory(rs.getInt("id_report_category"));
        category.setReportDate(rs.getDate("report_date"));
        category.setPeriodType(rs.getString("period_type"));
        category.setCategory(rs.getString("category"));
        category.setTotalSales(rs.getBigDecimal("total_sales"));
        category.setQuantitySold(rs.getInt("quantity_sold"));
        category.setGenerationDate(rs.getTimestamp("generation_date"));
        return category;
    }
    
    private ReportSalesByUser mapReportSalesByUser(ResultSet rs) throws Exception {
        ReportSalesByUser user = new ReportSalesByUser();
        user.setIdReportUser(rs.getInt("id_report_user"));
        user.setReportDate(rs.getDate("report_date"));
        user.setPeriodType(rs.getString("period_type"));
        user.setIdUser(rs.getInt("id_user"));
        user.setUserName(rs.getString("user_name"));
        user.setTotalSales(rs.getBigDecimal("total_sales"));
        user.setTotalTransactions(rs.getInt("total_transactions"));
        user.setAverageSale(rs.getBigDecimal("average_sale"));
        user.setGenerationDate(rs.getTimestamp("generation_date"));
        return user;
    }
}