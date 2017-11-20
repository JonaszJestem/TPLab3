package eu.jpereira.trainings.designpatterns.creational.factorymethod;

public class HTMLReportGenerator extends ReportGenerator {
    public static volatile HTMLReportGenerator instance;

    @Override
    public Report generateReport(ReportData data) {
        Report report = new HTMLReport();
        report.generateReport(data);
        return report;
    }

    public static HTMLReportGenerator getInstance() {
        if(instance == null) {
            synchronized (HTMLReportGenerator.class) {
                if(instance == null) {
                    instance = new HTMLReportGenerator();
                }
            }
        }
        return instance;
    }
}
