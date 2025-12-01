package org.lasalle.modastore.model;

import java.util.Date;
import java.math.BigDecimal;

public class ReportSalesByUser {
    private int idReportUser;
    private Date reportDate;
    private String periodType; // daily, weekly, monthly, yearly
    private int idUser;
    private String userName;
    private BigDecimal totalSales;
    private int totalTransactions;
    private BigDecimal averageSale;
    private Date generationDate;

    // Constructor vacío
    public ReportSalesByUser() {
    }

    // Constructor completo
    public ReportSalesByUser(int idReportUser, Date reportDate, String periodType, 
                            int idUser, String userName, BigDecimal totalSales, 
                            int totalTransactions, BigDecimal averageSale, Date generationDate) {
        this.idReportUser = idReportUser;
        this.reportDate = reportDate;
        this.periodType = periodType;
        this.idUser = idUser;
        this.userName = userName;
        this.totalSales = totalSales;
        this.totalTransactions = totalTransactions;
        this.averageSale = averageSale;
        this.generationDate = generationDate;
    }

    // Getters y Setters
    public int getIdReportUser() { 
        return idReportUser; 
    }
    
    public void setIdReportUser(int idReportUser) { 
        this.idReportUser = idReportUser; 
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
    
    public int getIdUser() { 
        return idUser; 
    }
    
    public void setIdUser(int idUser) { 
        this.idUser = idUser; 
    }
    
    public String getUserName() { 
        return userName; 
    }
    
    public void setUserName(String userName) { 
        this.userName = userName; 
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
    
    public BigDecimal getAverageSale() { 
        return averageSale; 
    }
    
    public void setAverageSale(BigDecimal averageSale) { 
        this.averageSale = averageSale; 
    }
    
    public Date getGenerationDate() { 
        return generationDate; 
    }
    
    public void setGenerationDate(Date generationDate) { 
        this.generationDate = generationDate; 
    }

    @Override
    public String toString() {
        return "ReportSalesByUser{" +
                "idReportUser=" + idReportUser +
                ", reportDate=" + reportDate +
                ", periodType='" + periodType + '\'' +
                ", idUser=" + idUser +
                ", userName='" + userName + '\'' +
                ", totalSales=" + totalSales +
                ", totalTransactions=" + totalTransactions +
                ", averageSale=" + averageSale +
                ", generationDate=" + generationDate +
                '}';
    }
}