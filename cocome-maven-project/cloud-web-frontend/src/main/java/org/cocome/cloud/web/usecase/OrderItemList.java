package org.cocome.cloud.web.usecase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.cocome.tradingsystem.inventory.application.store.ComplexOrderEntryTO;


public class OrderItemList {
	private HashMap<edu.kit.ipd.sdq.evaluation.Barcode, ComplexOrderEntryTO> orderItems = new HashMap<edu.kit.ipd.sdq.evaluation.Barcode, ComplexOrderEntryTO>();
	
	public List<ComplexOrderEntryTO> getOrderItems() {
		return new ArrayList<ComplexOrderEntryTO>(orderItems.values());
	}
	
	public void setOrderEntries(List<ComplexOrderEntryTO> orderEntries) {
		orderItems.clear();
		for (ComplexOrderEntryTO entry : orderEntries) {
			orderItems.put(entry.getProductTO().getBarcode(), entry);
		}
	}
	
	public ComplexOrderEntryTO getOrderByBarcode(edu.kit.ipd.sdq.evaluation.Barcode productBarcode) {
		return orderItems.get(productBarcode);
	}
	
	public void setOrderWithBarcode(edu.kit.ipd.sdq.evaluation.Barcode productBarcode, ComplexOrderEntryTO order) {
		orderItems.put(productBarcode, order);
	}
	
	public void insertOrders(Collection<ComplexOrderEntryTO> orders) {
		for (ComplexOrderEntryTO order : orders) {
			this.setOrderWithBarcode(order.getProductTO().getBarcode(), order);
		}
	}
	
	public void clear() {
		orderItems.clear();
	}
}
