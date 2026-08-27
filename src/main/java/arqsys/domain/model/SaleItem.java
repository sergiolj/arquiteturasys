package arqsys.domain.model;

import lombok.Data;

@Data
public class SaleItem {
	private double value;
	private SaleType type; 
	private String productName;
	
	
	public SaleItem(String productName, double value, SaleType saleType) {
		this.productName = productName;
		this.value = value;
		this.type = saleType;
	}
	
}
