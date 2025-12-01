package org.lasalle.modastore.model;

import java.util.Date;
import java.math.BigDecimal;


public class ReportSales {
    private int idReportSales;
    private Date reportDate;
    private String periodType; // daily, weekly, monthly, yearly
    private BigDecimal totalSales;
    private int totalTransactions;
    private int totalProductsSold;
    private BigDecimal averageTicket;
    private Date generationDate;

    // Constructor vacío
    public ReportSales() {
    }

    // Constructor completo
    public ReportSales(int idReportSales, Date reportDate, String periodType, 
                       BigDecimal totalSales, int totalTransactions, 
                       int totalProductsSold, BigDecimal averageTicket, Date generationDate) {
        this.idReportSales = idReportSales;
        this.reportDate = reportDate;
        this.periodType = periodType;
        this.totalSales = totalSales;
        this.totalTransactions = totalTransactions;
        this.totalProductsSold = totalProductsSold;
        this.averageTicket = averageTicket;
        this.generationDate = generationDate;
    }

    // Getters y Setters
    public int getIdReportSales() { 
        return idReportSales; 
    }
    
    public void setIdReportSales(int idReportSales) { 
        this.idReportSales = idReportSales; 
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
    
    public BigDecimal getTotalSales() { 
        return totalSales; 
    }
    
    public void setTotalSales(BigDecimal totalSales) { 
        this.totalSales = totalSales; 
    }
    
    public int getTotalTransactions() { 
        return totalTransactions; 
    }
    
    public void setTotalTransactions(int totalTransactions) { 
        this.totalTransactions = totalTransactions; 
    }
    
    public int getTotalProductsSold() { 
        return totalProductsSold; 
    }
    
    public void setTotalProductsSold(int totalProductsSold) { 
        this.totalProductsSold = totalProductsSold; 
    }
    
    public BigDecimal getAverageTicket() { 
        return averageTicket; 
    }
    
    public void setAverageTicket(BigDecimal averageTicket) { 
        this.averageTicket = averageTicket; 
    }
    
    public Date getGenerationDate() { 
        return generationDate; 
    }
    
    public void setGenerationDate(Date generationDate) { 
        this.generationDate = generationDate; 
    }

    @Override
    public String toString() {
        return "ReportSales{" +
                "idReportSales=" + idReportSales +
                ", reportDate=" + reportDate +
                ", periodType='" + periodType + '\'' +
                ", totalSales=" + totalSales +
                ", totalTransactions=" + totalTransactions +
                ", totalProductsSold=" + totalProductsSold +
                ", averageTicket=" + averageTicket +
                ", generationDate=" + generationDate +
                '}';
    }
}