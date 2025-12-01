/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.lasalle.modastore.controller;
import org.lasalle.modastore.model.Inventory;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class InventoryController {

    public List<Inventory> getAll() {
        List<Inventory> list = new ArrayList<>();
        String query = "SELECT * FROM inventory";
        try {
            ConexionMysql connMysql = new ConexionMysql();
            Connection conn = connMysql.open();
            PreparedStatement pstm = conn.prepareStatement(query);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                Inventory i = new Inventory();
                i.setIdInventory(rs.getInt("id_inventory"));
                i.setIdProduct(rs.getInt("id_product"));
                i.setCategory(rs.getString("category"));
                i.setCurrentStock(rs.getInt("current_stock"));
                i.setLastUpdate(rs.getTimestamp("last_update"));
                list.add(i);
            }

            connMysql.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void save(Inventory i) {
        try {
            String query = "INSERT INTO inventory (id_product, category, current_stock) VALUES (?,?,?)";
            ConexionMysql connMysql = new ConexionMysql();
            Connection conn = connMysql.open();
            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setInt(1, i.getIdProduct());
            pstm.setString(2, i.getCategory());
            pstm.setInt(3, i.getCurrentStock());
            pstm.executeUpdate();
            pstm.close();
            connMysql.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Inventory getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
