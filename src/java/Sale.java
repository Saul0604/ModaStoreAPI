/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.lasalle.modastore.model;
import java.util.Date;

public class Sale {

    private int idSale;
    private int idCustomer;
    private int idUser;
    private Date saleDate;
    private double total;

    public Sale() {
    }

    public Sale(int idSale, int idCustomer, int idUser, Date saleDate, double total) {
        this.idSale = idSale;
        this.idCustomer = idCustomer;
        this.idUser = idUser;
        this.saleDate = saleDate;
        this.total = total;
    }

    public int getIdSale() {
        return idSale;
    }

    public void setIdSale(int idSale) {
        this.idSale = idSale;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
