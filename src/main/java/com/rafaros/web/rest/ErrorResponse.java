package com.rafaros.web.rest;

public class ErrorResponse {

    private String error;

    public ErrorResponse() {}

    public ErrorResponse(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
    // Ajoutez les autres getters et setters si nécessaire
}
