package mda.domain.model;

import java.util.List;
import java.util.ArrayList;

import lombok.Data;

@Data
public class Sale {
	private double taxAmount = 0;
	private boolean payed = false;
	private List<SaleItem> saleList = new ArrayList<>();
	public boolean isCalculatedTaxes = false;
	
	public void add(SaleItem saleItem) {
		saleList.add(saleItem);
	}

	public boolean isPaymentAccepted() {
		return payed;
	}
	
	public void setTaxAmount(double calculatedTaxes) {
		this.taxAmount = calculatedTaxes;
		this.isCalculatedTaxes = true;
	}

	public double getTotalValue() {
		return getValueWithoutTaxes() + getTaxAmount();
	}
	
	public double getValueWithoutTaxes() {
		return saleList.stream().mapToDouble(SaleItem::getValue).sum();
	}
}
