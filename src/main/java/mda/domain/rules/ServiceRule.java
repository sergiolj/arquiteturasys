package mda.domain.rules;

import mda.domain.model.SaleItem;
import mda.domain.model.SaleType;

public class ServiceRule implements TaxRules{

	@Override
	public boolean apliesTo(SaleItem saleItem) {
		return saleItem.getType().equals(SaleType.SERVICE);
	}

	@Override
	public double calculateTax(SaleItem saleItem) {
		return saleItem.getValue() * 0.25;
	}

}
