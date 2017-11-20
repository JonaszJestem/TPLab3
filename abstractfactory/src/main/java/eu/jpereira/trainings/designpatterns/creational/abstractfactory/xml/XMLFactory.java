package eu.jpereira.trainings.designpatterns.creational.abstractfactory.xml;

import eu.jpereira.trainings.designpatterns.creational.abstractfactory.ReportFactory;

public class XMLFactory extends ReportFactory {
    public XMLFactory() {
        this.body = new XMLReportBody();
        this.header = new XMLReportHeader();
        this.footer = new XMLReportFooter();
    }
}
