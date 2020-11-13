package com.episen.ms.Ressource;

import com.episen.ms.Model.LoginForm;
import com.episen.ms.Model.Product;
import com.episen.ms.Service.BasketService;
import com.google.gson.Gson;
import net.minidev.json.JSONObject;

import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@RestController
@RequestMapping(value = "basket", produces = {"application/json"})
public class BasketRessource {
    @Autowired
    private BasketService service;

    @PostMapping()
    public ResponseEntity<Object> addProductBasket(@RequestBody Product prod, @RequestHeader(value = "token", required = false) String token, LoginForm loginForm) throws ParseException {
        WebClient.RequestHeadersSpec<?> requestSpec2 = WebClient
                .create("http://localhost:8070/episen/api/v1/authenticate/validate")
                .get()
                .header("token", token);
                String response = requestSpec2.exchange().block().bodyToMono(String.class).block();
        JSONParser parser = new JSONParser();
        System.out.println(response);
        JSONObject json = (JSONObject) parser.parse(response);
            Gson gson = new Gson();
            String role = gson.fromJson(json.get("role").toString(), String.class);
            if (!role.equals("non authentifier")){
                return ResponseEntity.status(200).body(service.addProduct(prod));

            }
        JSONObject json2 = new JSONObject();
        json.put("message", "vous devez être connecté pour ajouter un produit à votre panier");
        return ResponseEntity.status(400).body(json2);
    }

    @DeleteMapping()
    public ResponseEntity<Object> deleteProductBasket(@RequestParam String gtin, @RequestHeader(value = "token", required = false) String token) throws ParseException {
        WebClient.RequestHeadersSpec<?> requestSpec2 = WebClient
                .create("http://localhost:8070/episen/api/v1/authenticate/validate")
                .get()
                .header("token", token);
        String response = requestSpec2.exchange().block().bodyToMono(String.class).block();
        JSONParser parser = new JSONParser();
        System.out.println(response);
        JSONObject json = (JSONObject) parser.parse(response);
        Gson gson = new Gson();
        String role = gson.fromJson(json.get("role").toString(), String.class);
        if (!role.equals("non authentifier")){
            return ResponseEntity.status(204).body(service.deleteProduct(gtin));

        }
        JSONObject json2 = new JSONObject();
        json.put("message", "vous devez être connecté pour ajouter un produit à votre panier");
        return ResponseEntity.status(400).body(json2);
    }







}
