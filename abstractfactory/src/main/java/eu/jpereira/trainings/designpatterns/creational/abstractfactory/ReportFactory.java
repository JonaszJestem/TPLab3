package eu.jpereira.trainings.designpatterns.creational.abstractfactory;

public abstract class ReportFactory {
        protected ReportBody body;
        protected ReportFooter footer;
        protected ReportHeader header;

        public ReportBody getBody() {
                return body;
        }

        public void setBody(ReportBody body) {
                this.body = body;
        }

        public ReportFooter getFooter() {
                return footer;
        }

        public void setFooter(ReportFooter footer) {
                this.footer = footer;
        }

        public ReportHeader getHeader() {
                return header;
        }

        public void setHeader(ReportHeader header) {
                this.header = header;
        }
}
