/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.lasalle.modastore.controller;

import org.lasalle.modastore.model.Provider;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProviderController {
    
    // Obtener todos los proveedores ACTIVOS
    public List<Provider> getAll() {
        List<Provider> listProvider = new ArrayList<>();
        String query = "SELECT * FROM provider WHERE active = TRUE";
        try {
            ConexionMysql connMysql = new ConexionMysql();
            Connection conn = connMysql.open();
            PreparedStatement pstm = conn.prepareStatement(query);
            ResultSet rs = pstm.executeQuery();
            
            while (rs.next()) {
                Provider provider = new Provider();
                provider.setIdProvider(rs.getInt("id_provider"));
                provider.setProviderName(rs.getString("provider_name"));
                provider.setPhone(rs.getString("phone"));
                provider.setMainProduct(rs.getString("main_product"));
                provider.setActive(rs.getBoolean("active"));
                listProvider.add(provider);
            }
            
            rs.close();
            pstm.close();
            connMysql.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listProvider;
    }
    
    // Obtener un proveedor por ID
    public Provider getById(int idProvider) {
        Provider provider = null;
        String query = "SELECT * FROM provider WHERE id_provider = ? AND active = TRUE";
        try {
            ConexionMysql connMysql = new ConexionMysql();
            Connection conn = connMysql.open();
            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setInt(1, idProvider);
            ResultSet rs = pstm.executeQuery();
            
            if (rs.next()) {
                provider = new Provider();
                provider.setIdProvider(rs.getInt("id_provider"));
                provider.setProviderName(rs.getString("provider_name"));
                provider.setPhone(rs.getString("phone"));
                provider.setMainProduct(rs.getString("main_product"));
                provider.setActive(rs.getBoolean("active"));
            }
            
            rs.close();
            pstm.close();
            connMysql.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return provider;
    }
    
    // Insertar nuevo proveedor (siempre activo por defecto)
    public boolean save(Provider provider) {
        try {
            String query = "INSERT INTO provider (provider_name, phone, main_product, active) VALUES(?,?,?, TRUE)";
            ConexionMysql connMysql = new ConexionMysql();
            Connection conn = connMysql.open();
            PreparedStatement pstm = conn.prepareStatement(query);
            
            pstm.setString(1, provider.getProviderName());
            pstm.setString(2, provider.getPhone());
            pstm.setString(3, provider.getMainProduct());
            
            int rowsAffected = pstm.executeUpdate();
            
            pstm.close();
            conn.close();
            connMysql.close();
            
            System.out.println("✅ Proveedor guardado. Filas afectadas: " + rowsAffected);
            return rowsAffected > 0;
        } catch (Exception e) {
            System.err.println("❌ Error al guardar proveedor: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    // Actualizar proveedor existente
    public boolean update(Provider provider) {
        try {
            String query = "UPDATE provider SET provider_name = ?, phone = ?, main_product = ? WHERE id_provider = ? AND active = TRUE";
            ConexionMysql connMysql = new ConexionMysql();
            Connection conn = connMysql.open();
            PreparedStatement pstm = conn.prepareStatement(query);
            
            pstm.setString(1, provider.getProviderName());
            pstm.setString(2, provider.getPhone());
            pstm.setString(3, provider.getMainProduct());
            pstm.setInt(4, provider.getIdProvider());
            
            int rowsAffected = pstm.executeUpdate();
            
            pstm.close();
            conn.close();
            connMysql.close();
            
            System.out.println("✅ Proveedor actualizado. Filas afectadas: " + rowsAffected);
            return rowsAffected > 0;
        } catch (Exception e) {
            System.err.println("❌ Error al actualizar proveedor: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    // Eliminar proveedor (SOFT DELETE - marca como inactivo)
    public boolean delete(int idProvider) {
        try {
            String query = "UPDATE provider SET active = FALSE WHERE id_provider = ?";
            ConexionMysql connMysql = new ConexionMysql();
            Connection conn = connMysql.open();
            PreparedStatement pstm = conn.prepareStatement(query);
            
            pstm.setInt(1, idProvider);
            int rowsAffected = pstm.executeUpdate();
            
            pstm.close();
            conn.close();
            connMysql.close();
            
            if (rowsAffected > 0) {
                System.out.println("✅ Proveedor eliminado (soft delete). ID: " + idProvider);
                return true;
            } else {
                System.err.println("⚠️ No se encontró el proveedor con ID: " + idProvider);
                return false;
            }
        } catch (Exception e) {
            System.err.println("❌ Error al eliminar proveedor: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}