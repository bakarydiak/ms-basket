package com.episen.ms.Ressource;

import com.episen.ms.Model.AuthenticateData;
import com.episen.ms.Model.LoginForm;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.boot.actuate.endpoint.web.annotation.WebEndpoint;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping(value = "login", produces = {"application/json"})
public class AuthenticateRessource {

    @PostMapping
    public ResponseEntity<Object> authenticate(@RequestBody LoginForm loginForm) throws ParseException {
        WebClient.RequestHeadersSpec<?> requestSpec2 = WebClient
                .create("http://localhost:8070/episen/api/v1/authenticate")
                .post()
                .body(BodyInserters.fromObject(loginForm));
        String response = requestSpec2.exchange().block().bodyToMono(String.class).block();
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(response);
        if(response.contains("utilisateur introuvable")){
            System.out.println("toto");
        }else{
            Gson gson = new Gson();
            com.episen.ms.model.User user = gson.fromJson(json.get("User").toString(), com.episen.ms.model.User.class);
            String token = gson.fromJson(json.get("Token").toString(), String.class);
            AuthenticateData.authenticatedUser = user;
            AuthenticateData.token = token;
        }
        return ResponseEntity.status(200).body(AuthenticateData.token);
    }

}
