package arqsys.application;

import arqsys.domain.model.Sale;
import arqsys.domain.model.SaleItem;
import arqsys.domain.model.SaleType;

import arqsys.domain.service.SaleProcessComposite;

public class Main {
	public static void main(String[] args) {
		Sale sale = new Sale();
		sale.add(new SaleItem("Intel Processor i5-11500T", 1000, SaleType.PRODUCT));
		sale.add(new SaleItem("Processor instalation service", 500, SaleType.SERVICE));

		SaleProcessComposite processSale = new SaleProcessComposite(sale);
		processSale.process();
	}
}
