package model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Receipt {
    private int id;
    private Supplier supplier;
    private float totalPrice;
    private Timestamp createdTime;
    private WarehouseWorker creator;
    private List<SparePartInReceipt> spareParts;

    public Receipt(WarehouseWorker creator) {
        spareParts = new ArrayList<>();
        this.creator = creator;
    }

    public Receipt(int id, Supplier supplier, float totalPrice, Timestamp createdTime, WarehouseWorker creator) {
        this.id = id;
        this.supplier = supplier;
        this.totalPrice = totalPrice;
        this.createdTime = createdTime;
        this.creator = creator;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public WarehouseWorker getCreator() {
        return creator;
    }

    public void setCreator(WarehouseWorker creator) {
        this.creator = creator;
    }

    public List<SparePartInReceipt> getSpareParts() {
        return spareParts;
    }

    public void setSpareParts(List<SparePartInReceipt> spareParts) {
        this.spareParts = spareParts;
    }
}
