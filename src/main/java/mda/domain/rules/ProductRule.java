package mda.domain.rules;

import mda.domain.model.SaleItem;
import mda.domain.model.SaleType;

public class ProductRule implements TaxRules {

	@Override
	public boolean apliesTo(SaleItem saleItem) {
		return saleItem.getType().equals(SaleType.PRODUCT);
	}

	@Override
	public double calculateTax(SaleItem saleItem) {
		return saleItem.getValue() * 0.10;
	}

}
