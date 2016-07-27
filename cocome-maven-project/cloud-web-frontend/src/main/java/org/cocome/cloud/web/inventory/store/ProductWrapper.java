package org.cocome.cloud.web.inventory.store;

import org.apache.log4j.Logger;
import org.cocome.tradingsystem.inventory.application.store.ProductTO;
import org.cocome.tradingsystem.inventory.application.store.ProductWithSupplierTO;
import org.cocome.tradingsystem.inventory.application.store.StockItemTO;
import org.cocome.tradingsystem.inventory.application.store.SupplierTO;

/**
 * Wraps the {@link ProductTO} and {@link StockItemTO} objects received from 
 * the backend to better accommodate the needs of the UI.
 * 
 * @author Tobias Pöppke
 * @author Robert Heinrich
 */
public class ProductWrapper {
	private static final Logger LOG = Logger.getLogger(ProductWrapper.class);
	private ProductTO product;
	private StockItemTO stockItem;
	
	// TODO should be included in the database product
	private String description;
	
	private Store originStore;
	
	private boolean editingEnabled = false;
	
	private double newSalesPrice;
	
	private boolean inCurrentOrder = false;
	
	public ProductWrapper(ProductTO product) {
		this.product = product;
	}
	
	public ProductWrapper(ProductTO product, StockItemTO stockItem, Store originStore) {
		this(product);
		this.stockItem = stockItem;
		this.originStore = originStore;
		this.newSalesPrice = stockItem.getSalesPrice();
	}
	
	public void setStockItem(StockItemTO stockItem) {
		this.stockItem = stockItem;
	}
	
	public String getName() {
		return product.getName();
	}

	public double getSalesPrice() {
		return stockItem != null ? stockItem.getSalesPrice() : -1;
	}

	public String getDescription() {
		return description;
	}

	public long getAmount() {
		return stockItem != null ? stockItem.getAmount() : -1;
	}

	public long getBarcode() {
		return product.getBarcode();
	}
	
	public long getID() {
		return product.getId();
	}

	public Store getOriginStore() {
		return originStore;
	}
	
	public void setOriginStore(Store store) {
		if (store != null) {
			this.originStore = store;
		}
	}
	
	public ProductTO getProductTO() {
		return product;
	}
	
	public StockItemTO getStockItemTO() {
		return stockItem;
	}

	public boolean isEditingEnabled() {
		return editingEnabled;
	}

	public void setEditingEnabled(boolean editingEnabled) {
		LOG.debug(String.format("Editing enabled set to %b for item %s.", editingEnabled, product.getName()));
		this.editingEnabled = editingEnabled;
	}
	
	public void resetEditSalesPrice() {
		newSalesPrice = stockItem.getSalesPrice();
		setEditingEnabled(false);
	}
	
	public void setNewSalesPrice(double newPrice) {
		LOG.debug(String.format("New sales price set to %f", newPrice));
		newSalesPrice = newPrice;
	}
	
	public double getNewSalesPrice() {
		return newSalesPrice;
	}
	
	public void submitSalesPrice() {
		LOG.debug(String.format("Setting sales price of %s to %f", product.getName(), newSalesPrice));
		stockItem.setSalesPrice(newSalesPrice);
		setEditingEnabled(false);
	}

	public boolean isInCurrentOrder() {
		return inCurrentOrder;
	}

	public void setInCurrentOrder(boolean inCurrentOrder) {
		this.inCurrentOrder = inCurrentOrder;
	}
	
	public static ProductWithSupplierTO convertToProductTO(ProductWrapper product) {
		ProductWithSupplierTO productTO = new ProductWithSupplierTO();
		productTO.setBarcode(product.getBarcode());
		productTO.setId(product.getID());
		productTO.setName(product.getName());
		productTO.setPurchasePrice(product.getProductTO().getPurchasePrice());
		productTO.setSupplierTO(new SupplierTO());
		return productTO;
	}
}
