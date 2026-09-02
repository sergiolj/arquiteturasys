package mda.domain.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mda.application.ProcessSale;
import mda.domain.model.Sale;
import mda.domain.model.SaleItem;
import mda.domain.rules.ProductRule;
import mda.domain.rules.ServiceRule;
import mda.domain.rules.TaxRules;
import shared.MyLogger;

public class CalculateTaxService implements ProcessSale{
	private MyLogger logger = new MyLogger(this.getClass().getName());
	private List<TaxRules> taxesTypesList = new ArrayList<>();
	
	private final Map<String, Double> calculatedTaxes = new HashMap<String, Double>();
	
	
	public CalculateTaxService(List<TaxRules> taxRules) {
		this.taxesTypesList = taxRules;
	}
	
	public CalculateTaxService() {
		this(Arrays.asList(new ProductRule(), new ServiceRule()));
	}
	
	@Override
	public void process(Sale sale) {
		logger.info("Calculando Taxas conforme tipo de serviço aguarde...");
		double totalTaxes = 0;
		List<SaleItem> salesList = sale.getSaleList();
		calculatedTaxes.clear();
		
		for(SaleItem item : salesList) {
			double itemTax = taxesTypesList.stream()
					.filter(itemRule ->itemRule.apliesTo(item))
					.findFirst()
					.map(itemRule ->itemRule.calculateTax(item))
					.orElse(0.0);
					
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
