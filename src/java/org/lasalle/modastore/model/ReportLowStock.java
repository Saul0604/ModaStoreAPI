package org.lasalle.modastore.model;

import java.util.Date;


public class ReportLowStock {
    private int idReportStock;
    private int idProduct;
    private String productName;
    private String category;
    private int currentStock;
    private int minStockRequired;
    private String status; // critical, low, normal
    private Date lastCheck;

    // Constructor vacío
    public ReportLowStock() {
    }

    // Constructor completo
    public ReportLowStock(int idReportStock, int idProduct, String productName, 
                         String category, int currentStock, int minStockRequired, 
                         String status, Date lastCheck) {
        this.idReportStock = idReportStock;
        this.idProduct = idProduct;
        this.productName = productName;
        this.category = category;
        this.currentStock = currentStock;
        this.minStockRequired = minStockRequired;
        this.status = status;
        this.lastCheck = lastCheck;
    }

    // Getters y Setters
    public int getIdReportStock() { 
        return idReportStock; 
    }
    
    public void setIdReportStock(int idReportStock) { 
        this.idReportStock = idReportStock; 
    }
    
    public int getIdProduct() { 
        return idProduct; 
    }
    
    public void setIdProduct(int idProduct) { 
        this.idProduct = idProduct; 
    }
    
    public String getProductName() { 
        return productName; 
    }
    
    public void setProductName(String productName) { 
        this.productName = productName; 
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
    
    public int getMinStockRequired() { 
        return minStockRequired; 
    }
    
    public void setMinStockRequired(int minStockRequired) { 
        this.minStockRequired = minStockRequired; 
    }
    
    public String getStatus() { 
        return status; 
    }
    
    public void setStatus(String status) { 
        this.status = status; 
    }
    
    public Date getLastCheck() { 
        return lastCheck; 
    }
    
    public void setLastCheck(Date lastCheck) { 
        this.lastCheck = lastCheck; 
    }

    @Override
    public String toString() {
        return "ReportLowStock{" +
                "idReportStock=" + idReportStock +
                ", idProduct=" + idProduct +
                ", productName='" + productName + '\'' +
                ", category='" + category + '\'' +
                ", currentStock=" + currentStock +
                ", minStockRequired=" + minStockRequired +
                ", status='" + status + '\'' +
                ", lastCheck=" + lastCheck +
                '}';
    }
}