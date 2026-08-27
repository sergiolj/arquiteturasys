package arqsys.application;

import arqsys.domain.model.Sale;
import arqsys.domain.model.SaleItem;
import arqsys.domain.model.SaleType;
import arqsys.domain.service.CalculateTaxService;
import arqsys.domain.service.ConfirmationService;
import arqsys.domain.service.PaymentService;

public class Main {
	public static void main(String[] args) {
		Sale sale = new Sale();
		
		sale.add(new SaleItem("Intel Processor i5-11500T", 1000, SaleType.PRODUCT));
		sale.add(new SaleItem("Processor instalation service", 500, SaleType.SERVICE));

		CalculateTaxService taxService = new CalculateTaxService();
		PaymentService payment = new PaymentService();
		ConfirmationService confirmation = new ConfirmationService();
		taxService.process(sale);
		payment.process(sale);
		confirmation.process(sale);
			
	}

}
