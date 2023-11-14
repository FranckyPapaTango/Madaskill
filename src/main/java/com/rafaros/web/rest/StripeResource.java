package com.rafaros.web.rest;

import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class StripeResource {

    @Value("${stripe.secretKey}")
    private String stripeSecretKey;

    @PostMapping("/intents")
    public ResponseEntity<Object> createPaymentIntent(@RequestBody PaymentRequest paymentRequest) {
        try {
            Stripe.apiKey = stripeSecretKey;

            // Créer un PaymentIntent
            PaymentIntent paymentIntent = PaymentIntent.create(
                new PaymentIntentCreateParams.Builder()
                    .setAmount(Long.valueOf(paymentRequest.getAmount()))
                    .setCurrency("eur")
                    .addPaymentMethodType("card")
                    .build()
            );

            // Retourner le client_secret et l'ID du client
            Map<String, String> response = new HashMap<>();
            response.put("client_secret", paymentIntent.getClientSecret());
            response.put("client_id", paymentIntent.getCustomer());

            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(e.getMessage()));
        }
    }
}
