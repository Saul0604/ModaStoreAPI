
package org.lasalle.modastore.model;
import java.util.Date;

public class Inventory {

    private int idInventory;
    private int idProduct;
    private String category;
    private int currentStock;
    private Date lastUpdate;

    public Inventory() {
    }

    public Inventory(int idInventory, int idProduct, String category, int currentStock, Date lastUpdate) {
        this.idInventory = idInventory;
        this.idProduct = idProduct;
        this.category = category;
        this.currentStock = currentStock;
        this.lastUpdate = lastUpdate;
    }

    public int getIdInventory() {
        return idInventory;
    }

    public void setIdInventory(int idInventory) {
        this.idInventory = idInventory;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getCurrentStock() {
        return currentStock;
    }

    public void setCurrentStock(int currentStock) {
        this.currentStock = currentStock;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}

