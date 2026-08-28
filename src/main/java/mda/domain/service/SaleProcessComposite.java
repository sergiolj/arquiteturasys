package mda.domain.service;

import java.util.List;
import java.util.Stack;

import mda.domain.model.Sale;

public class SaleProcessComposite implements ProcessSale {
	private final Sale sale;
	private List<ProcessSale> taskList = new Stack<>();
	
	
	public SaleProcessComposite(Sale sale) {
		this.sale = sale;
		createTasks();
	}
	
	public void addTask (ProcessSale task) {
		this.taskList.add(task);
	}

	private void createTasks() {
		taskList.add(new CalculateTaxService());
		taskList.add(new PaymentService());
		taskList.add(new ConfirmationService());
	}
	
	public void process() {
		process(this.sale);
	}

	@Override
	public void process(Sale sale) {
		for(ProcessSale task: taskList) {
			task.process(sale);
		}
		
	}
	
	

}
