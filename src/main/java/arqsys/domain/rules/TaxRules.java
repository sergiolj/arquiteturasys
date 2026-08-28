package arqsys.domain.rules;

import arqsys.domain.model.SaleItem;

public interface TaxRules {
	boolean apliesTo(SaleItem saleItem);
	double calculateTax(SaleItem saleItem);
}
