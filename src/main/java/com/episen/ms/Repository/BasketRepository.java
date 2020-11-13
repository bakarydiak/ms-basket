package com.episen.ms.Repository;

import com.episen.ms.Model.Item;
import com.episen.ms.Model.Product;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class BasketRepository {

    private List<Item> itemInMemory = new ArrayList<>();

    public List<Item> addItem(Product prod) {
        if (itemInMemory.isEmpty()) {
            Item item = new Item(prod, 1, prod.getPrice());
            itemInMemory.add(item);
        } else {
            Iterator<Item> iterator = itemInMemory.iterator();
            Item i = iterator.next();
            Boolean found = false;
            while (i != null) {
                if (i.getProduct().getGtin().equals(prod.getGtin())) {
                    i.setQuantity(i.getQuantity() + 1);
                    i.setPrice(i.getPrice() + prod.getPrice());
                    found = true;
                    break;
                }
                if (iterator.hasNext()){
                    i = iterator.next();
                }else{
                    break;
                }
            }
            if (!found) {
                Item item = new Item(prod, 1, prod.getPrice());
                itemInMemory.add(item);
            }
        }
        return itemInMemory;
    }

    public List<Item> deleteItem(String gtin) {
            Iterator<Item> iterator = itemInMemory.iterator();
            Item i = iterator.next();
            while (i != null) {
                if (i.getProduct().getGtin().equals(gtin)) {
                    if (i.getQuantity() -1 == 0) {
                        itemInMemory.remove(i);
                    }else {
                        i.setQuantity(i.getQuantity() - 1);
                        i.setPrice(i.getPrice() - i.getProduct().getPrice());
                    }
                    break;
                }
                if (iterator.hasNext()){
                    i = iterator.next();
                }else{
                    break;
                }
            }
            return itemInMemory;
    }




}
