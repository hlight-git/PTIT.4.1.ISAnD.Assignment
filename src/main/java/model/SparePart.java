package model;

public class SparePart extends SellableObject{
    private int quantityInStock;
    public SparePart(int id, String name, float unitPrice, String calculationUnit, String description, int quantityInStock) {
        super(id, name, unitPrice, calculationUnit, description);
        this.quantityInStock = quantityInStock;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }
}
