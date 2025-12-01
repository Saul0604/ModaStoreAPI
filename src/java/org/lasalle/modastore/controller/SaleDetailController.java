/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.lasalle.modastore.controller;

import org.lasalle.modastore.model.SaleDetail;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SaleDetailController {

    public List<SaleDetail> getAll() {
        List<SaleDetail> listDetail = new ArrayList<>();
        String query = "SELECT * FROM sale_detail";
        ConexionMysql connMysql = new ConexionMysql();
        Connection conn = null;

        try {
            conn = connMysql.open();
            PreparedStatement pstm = conn.prepareStatement(query);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                SaleDetail detail = new SaleDetail();
                detail.setIdDetail(rs.getInt("id_detail"));
                detail.setIdSale(rs.getInt("id_sale"));
                detail.setIdProduct(rs.getInt("id_product"));
                detail.setQuantity(rs.getInt("quantity"));
                detail.setUnitPrice(rs.getDouble("unit_price"));
                detail.setSubtotal(rs.getDouble("subtotal"));
                listDetail.add(detail);
            }

            rs.close();
            pstm.close();
        } catch (Exception e) {
            System.err.println("Error getting sale details: " + e.getMessage());
        } finally {
            try {
                if (conn != null) conn.close();
                connMysql.close();
            } catch (Exception e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }

        return listDetail;
    }

    public List<SaleDetail> getBySale(int idSale) {
        List<SaleDetail> listDetail = new ArrayList<>();
        String query = "SELECT * FROM sale_detail WHERE id_sale = ?";
        ConexionMysql connMysql = new ConexionMysql();
        Connection conn = null;

        try {
            conn = connMysql.open();
            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setInt(1, idSale);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                SaleDetail detail = new SaleDetail();
                detail.setIdDetail(rs.getInt("id_detail"));
                detail.setIdSale(rs.getInt("id_sale"));
                detail.setIdProduct(rs.getInt("id_product"));
                detail.setQuantity(rs.getInt("quantity"));
                detail.setUnitPrice(rs.getDouble("unit_price"));
                detail.setSubtotal(rs.getDouble("subtotal"));
                listDetail.add(detail);
            }

            rs.close();
            pstm.close();
        } catch (Exception e) {
            System.err.println("Error getting sale details by sale ID: " + e.getMessage());
        } finally {
            try {
                if (conn != null) conn.close();
                connMysql.close();
            } catch (Exception e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }

        return listDetail;
    }

    public void save(SaleDetail detail) {
        String query = "INSERT INTO sale_detail (id_sale, id_product, quantity, unit_price, subtotal) VALUES(?,?,?,?,?)";
        ConexionMysql connMysql = new ConexionMysql();
        Connection conn = null;

        try {
            conn = connMysql.open();
            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setInt(1, detail.getIdSale());
            pstm.setInt(2, detail.getIdProduct());
            pstm.setInt(3, detail.getQuantity());
            pstm.setDouble(4, detail.getUnitPrice());
            pstm.setDouble(5, detail.getQuantity() * detail.getUnitPrice());
            pstm.executeUpdate();
            pstm.close();
        } catch (Exception e) {
            System.err.println("Error saving sale detail: " + e.getMessage());
        } finally {
            try {
                if (conn != null) conn.close();
                connMysql.close();
            } catch (Exception e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }

    public boolean update(int id, SaleDetail detail) {
        String query = "UPDATE sale_detail SET id_sale=?, id_product=?, quantity=?, unit_price=?, subtotal=? WHERE id_detail=?";
        ConexionMysql connMysql = new ConexionMysql();
        Connection conn = null;
        boolean updated = false;

        try {
            conn = connMysql.open();
            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setInt(1, detail.getIdSale());
            pstm.setInt(2, detail.getIdProduct());
            pstm.setInt(3, detail.getQuantity());
            pstm.setDouble(4, detail.getUnitPrice());
            pstm.setDouble(5, detail.getQuantity() * detail.getUnitPrice());
            pstm.setInt(6, id);

            int rows = pstm.executeUpdate();
            updated = rows > 0;
            pstm.close();
        } catch (Exception e) {
            System.err.println("Error updating sale detail: " + e.getMessage());
        } finally {
            try {
                if (conn != null) conn.close();
                connMysql.close();
            } catch (Exception e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }

        return updated;
    }

    public boolean delete(int id) {
        String query = "DELETE FROM sale_detail WHERE id_detail=?";
        ConexionMysql connMysql = new ConexionMysql();
        Connection conn = null;
        boolean deleted = false;

        try {
            conn = connMysql.open();
            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setInt(1, id);
            int rows = pstm.executeUpdate();
            deleted = rows > 0;
            pstm.close();
        } catch (Exception e) {
            System.err.println("Error deleting sale detail: " + e.getMessage());
        } finally {
            try {
                if (conn != null) conn.close();
                connMysql.close();
            } catch (Exception e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }

        return deleted;
    }
}
