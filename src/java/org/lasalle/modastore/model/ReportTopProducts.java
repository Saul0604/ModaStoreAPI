package org.lasalle.modastore.model;

import java.util.Date;
import java.math.BigDecimal;

public class ReportTopProducts {
    private int idReportTop;
    private Date reportDate;
    private String periodType; // daily, weekly, monthly, yearly
    private int idProduct;
    private String productName;
    private String category;
    private int quantitySold;
    private BigDecimal totalRevenue;
    private Date generationDate;

    // Constructor vacío
    public ReportTopProducts() {
    }

    // Constructor completo
    public ReportTopProducts(int idReportTop, Date reportDate, String periodType, 
                            int idProduct, String productName, String category, 
                            int quantitySold, BigDecimal totalRevenue, Date generationDate) {
        this.idReportTop = idReportTop;
        this.reportDate = reportDate;
        this.periodType = periodType;
        this.idProduct = idProduct;
        this.productName = productName;
        this.category = category;
        this.quantitySold = quantitySold;
        this.totalRevenue = totalRevenue;
        this.generationDate = generationDate;
    }

    // Getters y Setters
    public int getIdReportTop() { 
        return idReportTop; 
    }
    
    public void setIdReportTop(int idReportTop) { 
        this.idReportTop = idReportTop; 
    }
    
    public Date getReportDate() { 
        return reportDate; 
    }
    
    public void setReportDate(Date reportDate) { 
        this.reportDate = reportDate; 
    }
    
    public String getPeriodType() { 
        return periodType; 
    }
    
    public void setPeriodType(String periodType) { 
        this.periodType = periodType; 
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
    
    public int getQuantitySold() { 
        return quantitySold; 
    }
    
    public void setQuantitySold(int quantitySold) { 
        this.quantitySold = quantitySold; 
    }
    
    public BigDecimal getTotalRevenue() { 
        return totalRevenue; 
    }
    
    public void setTotalRevenue(BigDecimal totalRevenue) { 
        this.totalRevenue = totalRevenue; 
    }
    
    public Date getGenerationDate() { 
        return generationDate; 
    }
    
    public void setGenerationDate(Date generationDate) { 
        this.generationDate = generationDate; 
    }

    @Override
    public String toString() {
        return "ReportTopProducts{" +
                "idReportTop=" + idReportTop +
                ", reportDate=" + reportDate +
                ", periodType='" + periodType + '\'' +
                ", idProduct=" + idProduct +
                ", productName='" + productName + '\'' +
                ", category='" + category + '\'' +
                ", quantitySold=" + quantitySold +
                ", totalRevenue=" + totalRevenue +
                ", generationDate=" + generationDate +
                '}';
    }
}