package org.lasalle.modastore.model;

import java.util.Date;
import java.math.BigDecimal;

/**
 * Modelo para reportes de ventas por categoría
 * Tabla: report_sales_by_category
 */
public class ReportSalesByCategory {
    private int idReportCategory;
    private Date reportDate;
    private String periodType; // daily, weekly, monthly, yearly
    private String category;
    private BigDecimal totalSales;
    private int quantitySold;
    private Date generationDate;

    // Constructor vacío
    public ReportSalesByCategory() {
    }

    // Constructor completo
    public ReportSalesByCategory(int idReportCategory, Date reportDate, String periodType, 
                                String category, BigDecimal totalSales, int quantitySold, 
                                Date generationDate) {
        this.idReportCategory = idReportCategory;
        this.reportDate = reportDate;
        this.periodType = periodType;
        this.category = category;
        this.totalSales = totalSales;
        this.quantitySold = quantitySold;
        this.generationDate = generationDate;
    }

    // Getters y Setters
    public int getIdReportCategory() { 
        return idReportCategory; 
    }
    
    public void setIdReportCategory(int idReportCategory) { 
        this.idReportCategory = idReportCategory; 
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
    
    public String getCategory() { 
        return category; 
    }
    
    public void setCategory(String category) { 
        this.category = category; 
    }
    
    public BigDecimal getTotalSales() { 
        return totalSales; 
    }
    
    public void setTotalSales(BigDecimal totalSales) { 
        this.totalSales = totalSales; 
    }
    
    public int getQuantitySold() { 
        return quantitySold; 
    }
    
    public void setQuantitySold(int quantitySold) { 
        this.quantitySold = quantitySold; 
    }
    
    public Date getGenerationDate() { 
        return generationDate; 
    }
    
    public void setGenerationDate(Date generationDate) { 
        this.generationDate = generationDate; 
    }

    @Override
    public String toString() {
        return "ReportSalesByCategory{" +
                "idReportCategory=" + idReportCategory +
                ", reportDate=" + reportDate +
                ", periodType='" + periodType + '\'' +
                ", category='" + category + '\'' +
                ", totalSales=" + totalSales +
                ", quantitySold=" + quantitySold +
                ", generationDate=" + generationDate +
                '}';
    }
}