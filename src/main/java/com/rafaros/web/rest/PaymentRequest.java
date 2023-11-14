package com.rafaros.web.rest;

import com.stripe.param.PaymentIntentCreateParams;

public class PaymentRequest {

    private Integer amount;

    public PaymentIntentCreateParams toPaymentIntentCreateParams() {
        return PaymentIntentCreateParams
            .builder()
            .setAmount((long) amount)
            .setCurrency("eur")
            .setPaymentMethod("card")
            .setConfirmationMethod(PaymentIntentCreateParams.ConfirmationMethod.valueOf("automatic"))
            .build();
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
    // Ajoutez les autres getters et setters si nécessaire
}
