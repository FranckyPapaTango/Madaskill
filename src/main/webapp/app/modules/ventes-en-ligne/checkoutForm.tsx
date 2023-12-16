import { PaymentElement, LinkAuthenticationElement, useStripe, useElements } from '@stripe/react-stripe-js';
import React, { useState } from 'react';

export default function CheckoutForm() {
  const stripe = useStripe();
  const elements = useElements();
  const [message, setMessage] = useState<string | null>(null);
  const [isLoading, setIsLoading] = useState(false);

  const handleLinkAuthenticationChange = () => {
    // Access the email value like so:
    // setEmail(event.value.email);
    //
    // Prefill the email field like so:
    // options={{defaultValues: {email: 'foo@bar.com'}}}
    // Notez que vous ne devriez pas utiliser async ici
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();

    if (!stripe || !elements) {
      return;
    }

    setIsLoading(true);

    stripe
      .confirmPayment({
        elements,
        confirmParams: {
          return_url: `${window.location.origin}/success`,
        },
      })
      .then(({ error }) => {
        if (error?.type === 'card_error' || error?.type === 'validation_error') {
          setMessage(error.message);
        } else {
          setMessage('An unexpected error occurred.');
        }
      })
      .catch(err => {
        console.error('Error during payment confirmation', err);
      })
      .finally(() => {
        setIsLoading(false);
      });
  };

  return (
    <form id="payment-form" onSubmit={handleSubmit}>
      <LinkAuthenticationElement
        id="link-authentication-element"
        onChange={handleLinkAuthenticationChange} // Ne pas utiliser async ici
        // ...
      />
      <PaymentElement id="payment-element" />
      <button disabled={isLoading || !stripe || !elements} id="submit">
        <span id="button-text">{isLoading ? <div className="spinner" id="spinner"></div> : 'Payer maintenant'}</span>
      </button>
      {message && <div id="payment-message">{message}</div>}
    </form>
  );
}
