package com.episen.ms.Service;

import com.episen.ms.Model.AuthenticateData;
import com.episen.ms.Model.Basket;
import com.episen.ms.Model.Item;
import com.episen.ms.Model.Product;
import com.episen.ms.Repository.BasketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasketService {
    @Autowired
    private BasketRepository repository;

    public Basket addProduct(Product prod) {
        if ("".equals(prod.getName()) || "".equals(prod.getPrice())) {
            throw new RuntimeException("user exception");
        }
        List<Item> items = repository.addItem(prod);
        float total = 0;
        for (Item i : items) {
            total += i.getPrice();
        }
        Basket basket = new Basket(AuthenticateData.authenticatedUser, items, "created", total);
        return basket;
    }

    public Basket deleteProduct(String gtin) {
               List<Item> items = repository.deleteItem(gtin);
        float total = 0;
        for (Item i : items) {
            total += i.getPrice();
        }
        Basket basket = new Basket(AuthenticateData.authenticatedUser, items, "created", total);
        return basket;
    }






}
