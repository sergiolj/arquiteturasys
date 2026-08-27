package arqsys.domain.service;


import java.util.logging.Logger;

import arqsys.domain.model.MyLogger;
import arqsys.domain.model.Sale;
import arqsys.domain.rules.ProcessSale;

public class PaymentService implements ProcessSale{
	private MyLogger myLogger = new MyLogger(this.getClass().getName());

	
	@Override
	public void process(Sale sale) {
		myLogger.setSimpleOutput();
		Logger logger = myLogger.getLogger();
		
		if(sale.isCalculatedTaxes) {
			logger.info("Comunicando com a fonte pagadora...");
			String message = "Valor Produtos: " + sale.getValueWithoutTaxes() + " | Valor dos impostos: " + sale.getTaxAmount();
			logger.info(message);
			logger.info("Pagamento Aceito");
			sale.setPayed(true);
		}else {
			throw new IllegalStateException("Taxas e impostos devem ser calculados antes do pagamento");
		}
		
	}


}
