/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.lasalle.modastore.controller;

import org.lasalle.modastore.model.Customer;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CustomerController {

    // Obtener todos los clientes
    public List<Customer> getAll() {
        List<Customer> list = new ArrayList<>();
        String query = "SELECT * FROM customer";
        try {
            ConexionMysql connMysql = new ConexionMysql();
            Connection conn = connMysql.open();
            PreparedStatement pstm = conn.prepareStatement(query);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                Customer c = new Customer();
                c.setIdCustomer(rs.getInt("id_customer"));
                c.setFullName(rs.getString("full_name"));
                c.setPhone(rs.getString("phone"));
                c.setRegistrationDate(rs.getTimestamp("registration_date"));
                list.add(c);
            }

            rs.close();
            pstm.close();
            connMysql.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Insertar nuevo cliente
    public void save(Customer c) {
        try {
            String query = "INSERT INTO customer (full_name, phone) VALUES (?, ?)";
            ConexionMysql connMysql = new ConexionMysql();
            Connection conn = connMysql.open();
            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setString(1, c.getFullName());
            pstm.setString(2, c.getPhone());
            pstm.executeUpdate();
            pstm.close();
            connMysql.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Actualizar cliente existente
    public void update(Customer c) {
        try {
            String query = "UPDATE customer SET full_name = ?, phone = ? WHERE id_customer = ?";
            ConexionMysql connMysql = new ConexionMysql();
            Connection conn = connMysql.open();
            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setString(1, c.getFullName());
            pstm.setString(2, c.getPhone());
            pstm.setInt(3, c.getIdCustomer());
            pstm.executeUpdate();
            pstm.close();
            connMysql.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Eliminar cliente
    public void delete(int idCustomer) {
        try {
            String query = "DELETE FROM customer WHERE id_customer = ?";
            ConexionMysql connMysql = new ConexionMysql();
            Connection conn = connMysql.open();
            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setInt(1, idCustomer);
            pstm.executeUpdate();
            pstm.close();
            connMysql.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
