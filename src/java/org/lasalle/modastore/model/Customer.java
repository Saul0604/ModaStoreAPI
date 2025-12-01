/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.lasalle.modastore.model;

import java.util.Date;

public class Customer {

    private int idCustomer;
    private String fullName;
    private String phone;
    private Date registrationDate;

    public Customer() {
    }

    public Customer(int idCustomer, String fullName, String phone, Date registrationDate) {
        this.idCustomer = idCustomer;
        this.fullName = fullName;
        this.phone = phone;
        this.registrationDate = registrationDate;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
}
