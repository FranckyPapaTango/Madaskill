package com.rafaros.web.rest;

public class PaymentResponse {

    private String clientSecret;

    public PaymentResponse() {
        // Constructeur sans argument requis par Jackson
    }

    public PaymentResponse(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }
    // Ajoutez les autres getters et setters si nécessaire
}
