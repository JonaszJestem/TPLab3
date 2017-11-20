/**
 * Copyright 2011 Joao Miguel Pereira
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package eu.jpereira.trainings.designpatterns.creational.builder.json;

import eu.jpereira.trainings.designpatterns.creational.builder.model.ReportBody;
import eu.jpereira.trainings.designpatterns.creational.builder.model.SaleEntry;
import eu.jpereira.trainings.designpatterns.creational.builder.model.SoldItem;

import java.util.Iterator;

/**
 * For training purposes only!
 * @author jpereira
 *
 */
public class JSONReportBody implements ReportBody{

	private StringBuilder stringBuilder = new StringBuilder();
	/* (non-Javadoc)
	 * @see eu.jpereira.trainings.designpatterns.creational.builder.ReportBody#getAsString()
	 */
	@Override
	public Object getAsString() {
		return this.stringBuilder.toString();
	}

    @Override
    public void construct(SaleEntry saleEntry) {
        stringBuilder.append("sale:{customer:{");
        stringBuilder.append("name:\"");
        stringBuilder.append(saleEntry.getCustomer().getName());
        stringBuilder.append("\",phone:\"");
        stringBuilder.append(saleEntry.getCustomer().getPhone());
        stringBuilder.append("\"}");
        //add array of items
        stringBuilder.append(",items:[");
        Iterator<SoldItem> it = saleEntry.getSoldItems().iterator();
        while ( it.hasNext() ) {
            SoldItem item = it.next();
            stringBuilder.append("{name:\"");
            stringBuilder.append(item.getName());
            stringBuilder.append("\",quantity:");
            stringBuilder.append(String.valueOf(item.getQuantity()));
            stringBuilder.append(",price:");
            stringBuilder.append(String.valueOf(item.getUnitPrice()));
            stringBuilder.append("}");
            if ( it.hasNext() ) {
                stringBuilder.append(",");
            }
        }
        stringBuilder.append("]}");
    }


}
