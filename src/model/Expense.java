package model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;


public class Expense {
    private final SimpleStringProperty date;
    private final SimpleDoubleProperty price;

    public Expense(String date, double price) {
        this.date = new SimpleStringProperty(date);
        this.price = new SimpleDoubleProperty(price);
    }

    public String getDate() {
        return date.get();
    }

    public double getPrice() {
        return price.get();
    }
}