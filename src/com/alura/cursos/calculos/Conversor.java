package com.alura.cursos.calculos;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Conversor {
    private String codigoMonetario, codigoCambio;
    private double  valorCambio;

    HttpClient client = HttpClient.newHttpClient();

    public Conversor(String codigoMonetario, String codigoCambio) {
        this.codigoMonetario = codigoMonetario;
        this.codigoCambio = codigoCambio;
    }

    public void obtenerCambio() throws IOException, InterruptedException {
        // Construir la solicitud HTTP
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://v6.exchangerate-api.com/v6/d9ab927967aafa1b77da8a39/latest/" + codigoMonetario))
                .build();

        // Enviar la solicitud y obtener la respuesta
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        System.out.println("Respuesta JSON: " + json);

        // Deserializar el JSON
        Gson gson = new Gson();
        RespuestaCambio datos = gson.fromJson(json, RespuestaCambio.class);

        // Obtener el valor de cambio
        valorCambio = datos.getRates().get(codigoCambio);
        System.out.println("Valor del cambio (" + codigoCambio + "): " + valorCambio);
    }

    // Clase interna para mapear la estructura del JSON
    private static class RespuestaCambio {
        private String base_code;
        private java.util.Map<String, Double> conversion_rates;

        public java.util.Map<String, Double> getRates() {
            return conversion_rates;
        }
    }

    public double getValorCambio() {
        return valorCambio;
    }
}