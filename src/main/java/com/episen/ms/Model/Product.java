package com.episen.ms.Model;

public class Product {
    private String name;
    private float price;
    private float vat;
    private String gtin;

    public Product(String name, float price, float vat, String gtin) {
        this.name = name;
        this.price = price;
        this.vat = vat;
        this.gtin = gtin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getVat() {
        return vat;
    }

    public void setVat(float vat) {
        this.vat = vat;
    }

    public String getGtin() {
        return gtin;
    }

    public void setGin(String gtin) {
        this.gtin = gtin;
    }
}
