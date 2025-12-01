
package org.lasalle.modastore.model;
import java.util.Date;

public class Report {

    private int idReport;
    private String reportType;
    private Date generationDate;
    private String description;

    public Report() {
    }

    public Report(int idReport, String reportType, Date generationDate, String description) {
        this.idReport = idReport;
        this.reportType = reportType;
        this.generationDate = generationDate;
        this.description = description;
    }

    public int getIdReport() {
        return idReport;
    }

    public void setIdReport(int idReport) {
        this.idReport = idReport;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public Date getGenerationDate() {
        return generationDate;
    }

    public void setGenerationDate(Date generationDate) {
        this.generationDate = generationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
