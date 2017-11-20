package eu.jpereira.trainings.designpatterns.creational.builder;

import eu.jpereira.trainings.designpatterns.creational.builder.model.ReportBody;
import eu.jpereira.trainings.designpatterns.creational.builder.model.SaleEntry;
import eu.jpereira.trainings.designpatterns.creational.builder.model.SoldItem;

import java.util.Iterator;

public class HTMLReportBody implements ReportBody {

	private StringBuffer delegate = new StringBuffer();

	@Override
	public Object getAsString() {
		return this.delegate .toString();
	}

	@Override
	public void construct(SaleEntry saleEntry) {
		delegate.append("<span class=\"customerName\">");
		delegate.append(saleEntry.getCustomer().getName());
		delegate.append("</span><span class=\"customerPhone\">");
		delegate.append(saleEntry.getCustomer().getPhone());
		delegate.append("</span>");

		delegate.append("<items>");

		Iterator<SoldItem> it = saleEntry.getSoldItems().iterator();
		while ( it.hasNext() ) {
			SoldItem soldEntry= it.next();
			delegate.append("<item><name>");
			delegate.append(soldEntry.getName());
			delegate.append("</name><quantity>");
			delegate.append(soldEntry.getQuantity());
			delegate.append("</quantity><price>");
			delegate.append(soldEntry.getUnitPrice());
			delegate.append("</price></item>");
		}
		delegate.append("</items>");
	}


}
