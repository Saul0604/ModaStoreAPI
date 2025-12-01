/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.lasalle.modastore.controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionMysql {
    Connection conn;
    
    public Connection open() throws SQLException {
        String user = "root";
        String password = "root";
        String url = "jdbc:mysql://127.0.0.1:3306/ModaStore";
        String parametros = "?useSSL=false&useUnicode=true&allowPublicKeyRetrieval=true&characterEncoding=UTF-8"; // Agregué el valor al parámetro
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Aquí está el cambio clave: 
            // Pasa la URL, el usuario y la contraseña como argumentos separados.
            conn = DriverManager.getConnection(url + parametros, user, password);
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al conectar con la base de datos.", e);
        }
    }
    
    public void close() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Error al cerrar la conexión.", e);
            }
        }
    }
}
