/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.lasalle.modastore.controller;

import org.lasalle.modastore.model.Sale;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SaleController {

    public List<Sale> getAll() {
        List<Sale> list = new ArrayList<>();
        String query = "SELECT * FROM sale";

        ConexionMysql connMysql = new ConexionMysql();
        Connection conn = null;

        try {
            conn = connMysql.open();
            PreparedStatement pstm = conn.prepareStatement(query);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                Sale s = new Sale();
                s.setIdSale(rs.getInt("id_sale"));
                s.setIdCustomer(rs.getInt("id_customer"));
                s.setIdUser(rs.getInt("id_user"));
                s.setSaleDate(rs.getTimestamp("sale_date"));
                s.setTotal(rs.getDouble("total"));
                list.add(s);
            }

            rs.close();
            pstm.close();
        } catch (Exception e) {
            System.err.println("Error getting all sales: " + e.getMessage());
        } finally {
            try {
                if (conn != null) conn.close();
                connMysql.close();
            } catch (Exception e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
        return list;
    }

    public void save(Sale s) {
        String query = "INSERT INTO sale (id_customer, id_user, sale_date, total) VALUES (?,?,?,?)";

        ConexionMysql connMysql = new ConexionMysql();
        Connection conn = null;

        try {
            conn = connMysql.open();
            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setInt(1, s.getIdCustomer());
            pstm.setInt(2, s.getIdUser());

            Timestamp saleTimestamp = (s.getSaleDate() != null)
                    ? new Timestamp(s.getSaleDate().getTime())
                    : new Timestamp(System.currentTimeMillis());
            pstm.setTimestamp(3, saleTimestamp);

            pstm.setDouble(4, s.getTotal());
            pstm.executeUpdate();

            pstm.close();
        } catch (Exception e) {
            System.err.println("Error saving sale: " + e.getMessage());
        } finally {
            try {
                if (conn != null) conn.close();
                connMysql.close();
            } catch (Exception e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }

    public boolean update(int id, Sale s) {
        String query = "UPDATE sale SET id_customer=?, id_user=?, sale_date=?, total=? WHERE id_sale=?";
        boolean updated = false;

        ConexionMysql connMysql = new ConexionMysql();
        Connection conn = null;

        try {
            conn = connMysql.open();
            PreparedStatement pstm = conn.prepareStatement(query);

            pstm.setInt(1, s.getIdCustomer());
            pstm.setInt(2, s.getIdUser());

            Timestamp saleTimestamp = (s.getSaleDate() != null)
                    ? new Timestamp(s.getSaleDate().getTime())
                    : new Timestamp(System.currentTimeMillis());
            pstm.setTimestamp(3, saleTimestamp);

            pstm.setDouble(4, s.getTotal());
            pstm.setInt(5, id);

            int rows = pstm.executeUpdate();
            updated = rows > 0;

            pstm.close();
        } catch (Exception e) {
            System.err.println("Error updating sale: " + e.getMessage());
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
        String query = "DELETE FROM sale WHERE id_sale=?";
        boolean deleted = false;

        ConexionMysql connMysql = new ConexionMysql();
        Connection conn = null;

        try {
            conn = connMysql.open();
            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setInt(1, id);
            int rows = pstm.executeUpdate();
            deleted = rows > 0;
            pstm.close();
        } catch (Exception e) {
            System.err.println("Error deleting sale: " + e.getMessage());
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
