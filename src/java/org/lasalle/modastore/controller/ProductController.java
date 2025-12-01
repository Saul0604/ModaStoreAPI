package org.lasalle.modastore.controller;

import org.lasalle.modastore.model.Product;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProductController {
    
    // Obtener todos los productos ACTIVOS
    public List<Product> getAll() {
        List<Product> listProducts = new ArrayList<>();
        String query = "SELECT * FROM products WHERE active = TRUE";
        try {
            ConexionMysql connMysql = new ConexionMysql();
            Connection conn = connMysql.open();
            PreparedStatement pstm = conn.prepareStatement(query);
            ResultSet rs = pstm.executeQuery();
            
            while (rs.next()) {
                Product p = new Product();
                p.setIdProduct(rs.getInt("id_product"));
                p.setProductName(rs.getString("product_name"));
                p.setCategory(rs.getString("category"));
                p.setSize(rs.getString("size"));
                p.setColor(rs.getString("color"));
                p.setPrice(rs.getDouble("price"));
                p.setStock(rs.getInt("stock"));
                p.setIdProvider(rs.getInt("id_provider"));
                p.setActive(rs.getBoolean("active"));
                listProducts.add(p);
            }
            
            rs.close();
            pstm.close();
            connMysql.close();
            conn.close();
            
            return listProducts;
        } catch (Exception e) {
            e.printStackTrace();
            return listProducts;
        }
    }
    
    // Insertar un nuevo producto (siempre activo por defecto)
    public boolean save(Product product) {
        try {
            String query = "INSERT INTO products (product_name, category, size, color, price, stock, id_provider, active) VALUES (?, ?, ?, ?, ?, ?, ?, TRUE)";
            ConexionMysql connMysql = new ConexionMysql();
            Connection conn = connMysql.open();
            PreparedStatement pstm = conn.prepareStatement(query);
            
            pstm.setString(1, product.getProductName());
            pstm.setString(2, product.getCategory());
            pstm.setString(3, product.getSize());
            pstm.setString(4, product.getColor());
            pstm.setDouble(5, product.getPrice());
            pstm.setInt(6, product.getStock());
            pstm.setInt(7, product.getIdProvider());
            
            int rowsAffected = pstm.executeUpdate();
            
            pstm.close();
            connMysql.close();
            conn.close();
            
            System.out.println("✅ Producto guardado. Filas afectadas: " + rowsAffected);
            return rowsAffected > 0;
            
        } catch (Exception e) {
            System.err.println("❌ Error al guardar producto: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Actualizar un producto existente
    public boolean update(Product product) {
        try {
            String query = "UPDATE products SET product_name = ?, category = ?, size = ?, color = ?, price = ?, stock = ?, id_provider = ? WHERE id_product = ? AND active = TRUE";
            ConexionMysql connMysql = new ConexionMysql();
            Connection conn = connMysql.open();
            PreparedStatement pstm = conn.prepareStatement(query);
            
            pstm.setString(1, product.getProductName());
            pstm.setString(2, product.getCategory());
            pstm.setString(3, product.getSize());
            pstm.setString(4, product.getColor());
            pstm.setDouble(5, product.getPrice());
            pstm.setInt(6, product.getStock());
            pstm.setInt(7, product.getIdProvider());
            pstm.setInt(8, product.getIdProduct());
            
            int rowsAffected = pstm.executeUpdate();
            
            pstm.close();
            connMysql.close();
            conn.close();
            
            System.out.println("✅ Producto actualizado. Filas afectadas: " + rowsAffected);
            return rowsAffected > 0;
            
        } catch (Exception e) {
            System.err.println("❌ Error al actualizar producto: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Eliminar un producto por su ID (SOFT DELETE - marca como inactivo)
    public boolean delete(int idProduct) {
        try {
            String query = "UPDATE products SET active = FALSE WHERE id_product = ?";
            ConexionMysql connMysql = new ConexionMysql();
            Connection conn = connMysql.open();
            PreparedStatement pstm = conn.prepareStatement(query);
            
            pstm.setInt(1, idProduct);
            int rowsAffected = pstm.executeUpdate();
            
            pstm.close();
            connMysql.close();
            conn.close();
            
            if (rowsAffected > 0) {
                System.out.println("✅ Producto eliminado (soft delete). ID: " + idProduct);
                return true;
            } else {
                System.err.println("⚠️ No se encontró el producto con ID: " + idProduct);
                return false;
            }
            
        } catch (Exception e) {
            System.err.println("❌ Error al eliminar producto: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    // Obtener un producto por ID
    public Product getById(int idProduct) {
        Product p = null;
        String query = "SELECT * FROM products WHERE id_product = ? AND active = TRUE";
        try {
            ConexionMysql connMysql = new ConexionMysql();
            Connection conn = connMysql.open();
            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setInt(1, idProduct);
            ResultSet rs = pstm.executeQuery();
            
            if (rs.next()) {
                p = new Product();
                p.setIdProduct(rs.getInt("id_product"));
                p.setProductName(rs.getString("product_name"));
                p.setCategory(rs.getString("category"));
                p.setSize(rs.getString("size"));
                p.setColor(rs.getString("color"));
                p.setPrice(rs.getDouble("price"));
                p.setStock(rs.getInt("stock"));
                p.setIdProvider(rs.getInt("id_provider"));
                p.setActive(rs.getBoolean("active"));
            }
            
            rs.close();
            pstm.close();
            connMysql.close();
            conn.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }
}