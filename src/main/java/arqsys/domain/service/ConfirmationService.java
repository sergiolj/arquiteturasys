package arqsys.domain.service;

import java.util.logging.*;

import arqsys.domain.model.MyLogger;
import arqsys.domain.model.Sale;
import arqsys.domain.rules.ProcessSale;

public class ConfirmationService implements ProcessSale {
	private MyLogger myLogger = new MyLogger(this.getClass().getName());
	//private static final Logger logger = Logger.getLogger(ConfirmationService.class.getName());

	@Override
	public void process(Sale sale) {
		myLogger.setSimpleOutput();
		Logger logger = myLogger.getLogger();
		
		
		if(sale.isPaymentAccepted()) {
			logger.info("Pagamento efetivado, enviando email de confirmação");
		}else {
			logger.warning("Venda com pagamento pendende");
		}
	
	}

}
