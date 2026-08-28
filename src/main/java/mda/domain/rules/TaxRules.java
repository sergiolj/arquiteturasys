package mda.domain.rules;

import mda.domain.model.SaleItem;

public interface TaxRules {
	boolean apliesTo(SaleItem saleItem);
	double calculateTax(SaleItem saleItem);
}
