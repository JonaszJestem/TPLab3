package eu.jpereira.trainings.designpatterns.creational.factorymethod;

public class PDFReportGenerator extends ReportGenerator {
    @Override
    public Report generateReport(ReportData data) {
        Report report = new PDFReport();
        report.generateReport(data);
        return report;
    }
}
