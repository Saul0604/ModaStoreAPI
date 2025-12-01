/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.lasalle.modastore.rest;
import java.io.IOException;
import jakarta.servlet.Filter; // ¡Importante! Usamos jakarta.servlet
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;

public class CorsFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Inicialización
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // 1. Permitir cualquier origen ("*") 🌐
        httpResponse.setHeader("Access-Control-Allow-Origin", "*");

        // 2. Métodos HTTP permitidos
        httpResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");

        // 3. Cabeceras permitidas (incluyendo las necesarias para tokens de autenticación)
        httpResponse.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

        // 4. (Opcional) Cache para peticiones preflight OPTIONS
        httpResponse.setHeader("Access-Control-Max-Age", "3600"); 

        // Continúa con el procesamiento normal
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Limpieza
    }
}
