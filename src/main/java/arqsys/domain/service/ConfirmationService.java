package arqsys.domain.service;

import arqsys.domain.model.Sale;
import shared.MyLogger;

public class ConfirmationService implements ProcessSale {
	private MyLogger logger = new MyLogger(this.getClass().getName());

	@Override
	public void process(Sale sale) {
		
		if(sale.isPaymentAccepted()) {
			logger.info("Pagamento efetivado, enviando email de confirmação");
		}else {
			logger.warning("Venda com pagamento pendende");
		}
	
	}

}
