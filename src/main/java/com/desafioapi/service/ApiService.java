package com.desafioapi.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.desafioapi.conversion.ConversionMoneda;
import com.desafioapi.conversion.RespuestaMonedas;
import com.google.gson.Gson;

public class ApiService {
    private static final String API_KEY = "ebf4637120e8bed5632a1494";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";
    private final HttpClient client;
    private final Gson gson;

    public ApiService() {
        client = HttpClient.newHttpClient();
        gson = new Gson();
    }

    public RespuestaMonedas obtenerConversiones(String moneda) throws Exception {
        URI uri = URI.create(BASE_URL + API_KEY + "/latest/" + moneda + "/");
        HttpRequest request = HttpRequest.newBuilder().uri(uri).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return gson.fromJson(response.body(), RespuestaMonedas.class);
    }

    public ConversionMoneda convertirMoneda(String from, String to, double amount) throws Exception {
        URI uri = URI.create(BASE_URL + API_KEY + "/pair/" + from + "/" + to + "/" + amount + "/");
        HttpRequest request = HttpRequest.newBuilder().uri(uri).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return gson.fromJson(response.body(), ConversionMoneda.class);
    }
}
