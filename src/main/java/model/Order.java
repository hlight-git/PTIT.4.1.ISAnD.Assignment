package model;

import java.util.List;

public class Order {
    private int id;
    private Customer customer;
    private List<SparePartInOrder> spareParts;
    private List<ServiceInOrder> services;
}
