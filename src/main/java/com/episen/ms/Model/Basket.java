package com.episen.ms.Model;

import com.episen.ms.model.User;

import java.util.ArrayList;
import java.util.List;

public class Basket {
    private User user;
    private List<Item> items;
    private String status;
    private float total;

    public Basket(User user, List<Item> items, String status, float total) {
        this.user = user;
        this.items = items;
        this.status = status;
        this.total = total;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
