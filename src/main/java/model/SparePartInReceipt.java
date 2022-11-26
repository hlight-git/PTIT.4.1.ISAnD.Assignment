package model;

public class SparePartInReceipt {
    private int id;
    private int quantity;
    private float unitPrice;
    private SparePart sparePart;
    private Receipt receipt;

    public SparePartInReceipt(int quantity, float unitPrice, SparePart sparePart, Receipt receipt) {
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.sparePart = sparePart;
        this.receipt = receipt;
    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public SparePart getSparePart() {
        return sparePart;
    }

    public Receipt getReceipt() {
        return receipt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setSparePart(SparePart sparePart) {
        this.sparePart = sparePart;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }
}
