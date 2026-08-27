package arqsys.domain.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import arqsys.domain.model.MyLogger;
import arqsys.domain.model.Sale;
import arqsys.domain.model.SaleItem;
import arqsys.domain.model.SaleType;
import arqsys.domain.rules.ProcessSale;

public class CalculateTaxService implements ProcessSale{
	private MyLogger myLogger = new MyLogger(this.getClass().getName());
	
	private final Map<String, Double> calculatedTaxes = new HashMap<String, Double>();
	
	@Override
	public void process(Sale sale) {
		myLogger.setSimpleOutput();
		Logger logger = myLogger.getLogger();
		
		logger.info("Calculando Taxas conforme tipo de serviço aguarde...");
		double totalTaxes = 0;
		List<SaleItem> salesList = sale.getSaleList();
		calculatedTaxes.clear();
		
		for(SaleItem item : salesList) {
			double itemTax=0;
			if(item.getType().equals(SaleType.PRODUCT)) {
				itemTax += item.getValue() * 0.10;
			}else if(item.getType().equals(SaleType.SERVICE)) {
				itemTax += item.getValue() * 0.25;
			}
			calculatedTaxes.put(item.getProductName(), itemTax);
		}
		totalTaxes = calculatedTaxes.values().stream()
				.mapToDouble(Double::doubleValue)
				.sum();
		sale.setTaxAmount(totalTaxes);
		String message = "Total de taxas a pagar: " + sale.getTaxAmount();
		logger.info(message);
	}

	public Map<String, Double> getCalculatedTaxes(){
		return Collections.unmodifiableMap(calculatedTaxes);
	}
}
