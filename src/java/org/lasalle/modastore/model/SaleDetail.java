package org.lasalle.modastore.model;
public class SaleDetail {

    private int idDetail;
    private int idSale;
    private int idProduct;
    private int quantity;
    private double unitPrice;
    private double subtotal;

    public SaleDetail() {
    }

    public SaleDetail(int idDetail, int idSale, int idProduct, int quantity, double unitPrice, double subtotal) {
        this.idDetail = idDetail;
        this.idSale = idSale;
        this.idProduct = idProduct;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.subtotal = subtotal;
    }

    public int getIdDetail() {
        return idDetail;
    }

    public void setIdDetail(int idDetail) {
        this.idDetail = idDetail;
    }

    public int getIdSale() {
        return idSale;
    }

    public void setIdSale(int idSale) {
        this.idSale = idSale;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
    
}

