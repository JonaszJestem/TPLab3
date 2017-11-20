package eu.jpereira.trainings.designpatterns.creational.factorymethod;

public class JSONReportGenerator extends ReportGenerator {
    @Override
    public Report generateReport(ReportData data) {
        Report report = new JSONReport();
        report.generateReport(data);
        return report;
    }
}
