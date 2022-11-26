package model;

public abstract class SellableObject {
    private int id;
    private String name;
    private float unitPrice;
    private String calculationUnit;
    private String description;

    public SellableObject(int id, String name, float unitPrice, String calculationUnit, String description) {
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;
        this.calculationUnit = calculationUnit;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getCalculationUnit() {
        return calculationUnit;
    }

    public void setCalculationUnit(String calculationUnit) {
        this.calculationUnit = calculationUnit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
