package arqsys.domain.service;

import arqsys.domain.model.Sale;
import shared.MyLogger;

public class PaymentService implements ProcessSale{
	private MyLogger logger = new MyLogger(this.getClass().getName());


	@Override
	public void process(Sale sale) {
		logger.addOutputFileHandler();
		
		if(sale.isCalculatedTaxes) {
			String message = "Produtos R$: " + sale.getValueWithoutTaxes() + " | Impostos R$: " + sale.getTaxAmount()
			 + " | Total R$: " + sale.getTotalValue() ;
			logger.info(message);
			logger.info("Comunicando com a fonte pagadora...");
			logger.info("Pagamento Aceito");
			sale.setPayed(true);
		}else {
			logger.warning("Erro no fluxo de pagamento, tentativa de pagamento sem previamente contabilizar impostos.");
			throw new IllegalStateException("Taxas e impostos devem ser calculados antes do pagamento");
		}
		
	}


}
