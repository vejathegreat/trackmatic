package com.velaphi.trackmatic.taxProblem;


public class SaleItem {
    private int quantity;
    private String itemName;
    private boolean taxFree;
    private boolean imported;
    private double price;

    public SaleItem() {
    }

    public SaleItem(int quantity, String itemName, boolean taxFree, boolean imported, double price) {
        this.quantity = quantity;
        this.itemName = itemName;
        this.taxFree = taxFree;
        this.imported = imported;
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public boolean isImported() {
        return imported;
    }

    public void setImported(boolean imported) {
        this.imported = imported;
    }

    public boolean isTaxFree() {
        return taxFree;
    }

    public void setTaxFree(boolean taxFree) {
        this.taxFree = taxFree;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
