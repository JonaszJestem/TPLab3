package eu.jpereira.trainings.designpatterns.creational.factorymethod;

public class XMLReportGenerator extends ReportGenerator {
    @Override
    public Report generateReport(ReportData data) {
        Report report = new XMLReport();
        report.generateReport(data);
        return report;
    }
}
