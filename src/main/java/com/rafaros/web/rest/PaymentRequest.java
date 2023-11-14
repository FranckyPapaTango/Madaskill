package com.rafaros.web.rest;

import com.stripe.param.PaymentIntentCreateParams;

public class PaymentRequest {

    private Integer amount;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
    // Ajoutez les autres getters et setters si nécessaire
}
