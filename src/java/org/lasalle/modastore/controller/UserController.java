package org.lasalle.modastore.controller;

import org.lasalle.modastore.model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserController {

    // Obtener todos los usuarios ACTIVOS
    public List<User> getAll() {
        List<User> listUser = new ArrayList<>();
        String query = "SELECT * FROM `user` WHERE active = TRUE";

        ConexionMysql connMysql = new ConexionMysql();
        Connection conn = null;

        try {
            conn = connMysql.open();
            PreparedStatement pstm = conn.prepareStatement(query);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setIdUser(rs.getInt("id_user"));
                user.setFullName(rs.getString("full_name"));
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setIdRole(rs.getInt("id_role"));
                user.setActive(rs.getBoolean("active"));
                listUser.add(user);
            }

            rs.close();
            pstm.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connMysql.close();
            try { if (conn != null) conn.close(); } catch (Exception ignored) {}
        }

        return listUser;
    }

    // Insertar un nuevo usuario (siempre activo por defecto)
    public boolean save(User user) {
        String query = "INSERT INTO `user` (full_name, email, username, password, id_role, active) VALUES (?, ?, ?, ?, ?, TRUE)";
        ConexionMysql connMysql = new ConexionMysql();
        Connection conn = null;
        boolean saved = false;

        try {
            conn = connMysql.open();
            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setString(1, user.getFullName());
            pstm.setString(2, user.getEmail());
            pstm.setString(3, user.getUsername());
            pstm.setString(4, user.getPassword());
            pstm.setInt(5, user.getIdRole());
            
            int rows = pstm.executeUpdate();
            saved = rows > 0;
            
            pstm.close();
            System.out.println("✅ Usuario guardado. Filas afectadas: " + rows);
        } catch (Exception e) {
            System.err.println("❌ Error al guardar usuario: " + e.getMessage());
            e.printStackTrace();
        } finally {
            connMysql.close();
            try { if (conn != null) conn.close(); } catch (Exception ignored) {}
        }

        return saved;
    }

    // Actualizar un usuario existente
    public boolean update(int id, User user) {
        String query = "UPDATE `user` SET full_name=?, email=?, username=?, password=?, id_role=? WHERE id_user=? AND active = TRUE";
        ConexionMysql connMysql = new ConexionMysql();
        Connection conn = null;
        boolean updated = false;

        try {
            conn = connMysql.open();
            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setString(1, user.getFullName());
            pstm.setString(2, user.getEmail());
            pstm.setString(3, user.getUsername());
            pstm.setString(4, user.getPassword());
            pstm.setInt(5, user.getIdRole());
            pstm.setInt(6, id);

            int rows = pstm.executeUpdate();
            updated = rows > 0;

            pstm.close();
            System.out.println("✅ Usuario actualizado. Filas afectadas: " + rows);
        } catch (Exception e) {
            System.err.println("❌ Error al actualizar usuario: " + e.getMessage());
            e.printStackTrace();
        } finally {
            connMysql.close();
            try { if (conn != null) conn.close(); } catch (Exception ignored) {}
        }

        return updated;
    }

    // Eliminar un usuario por su ID (SOFT DELETE - marca como inactivo)
    public boolean delete(int id) {
        String query = "UPDATE `user` SET active = FALSE WHERE id_user=?";
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
            
            if (deleted) {
                System.out.println("✅ Usuario eliminado (soft delete). ID: " + id);
            } else {
                System.err.println("⚠️ No se encontró el usuario con ID: " + id);
            }
        } catch (Exception e) {
            System.err.println("❌ Error al eliminar usuario: " + e.getMessage());
            e.printStackTrace();
        } finally {
            connMysql.close();
            try { if (conn != null) conn.close(); } catch (Exception ignored) {}
        }

        return deleted;
    }

    // Login (solo usuarios activos)
    public User login(String username, String password) {
        String query = "SELECT * FROM `user` WHERE username=? AND password=? AND active = TRUE";
        ConexionMysql connMysql = new ConexionMysql();
        Connection conn = null;
        User user = null;

        try {
            conn = connMysql.open();
            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setString(1, username);
            pstm.setString(2, password);
            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setIdUser(rs.getInt("id_user"));
                user.setFullName(rs.getString("full_name"));
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setIdRole(rs.getInt("id_role"));
                user.setActive(rs.getBoolean("active"));
            }

            rs.close();
            pstm.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connMysql.close();
            try { if (conn != null) conn.close(); } catch (Exception ignored) {}
        }

        return user;
    }
    
    // Obtener usuario por ID
    public User getById(int id) {
        String query = "SELECT * FROM `user` WHERE id_user=? AND active = TRUE";
        ConexionMysql connMysql = new ConexionMysql();
        Connection conn = null;
        User user = null;

        try {
            conn = connMysql.open();
            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setIdUser(rs.getInt("id_user"));
                user.setFullName(rs.getString("full_name"));
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setIdRole(rs.getInt("id_role"));
                user.setActive(rs.getBoolean("active"));
            }

            rs.close();
            pstm.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connMysql.close();
            try { if (conn != null) conn.close(); } catch (Exception ignored) {}
        }

        return user;
    }
}