import React from 'react';
import { useStripe, useElements, CardElement } from '@stripe/react-stripe-js';

interface CheckoutFormProps {
  clientSecret: string | undefined;
}

const CheckoutForm: React.FC<CheckoutFormProps> = ({ clientSecret }) => {
  const stripe = useStripe();
  const elements = useElements();

  const handleSubmit = async (event: React.FormEvent) => {
    event.preventDefault();

    if (!stripe || !elements || !clientSecret) {
      return;
    }

    try {
      // Use the client secret to confirm the payment and handle any errors
      const result = await stripe.confirmCardPayment(clientSecret, {
        payment_method: {
          card: elements.getElement(CardElement)!, // Assume you have a CardElement in your form
          billing_details: {
            name: 'Jenny Rosen', // Replace with the actual billing details
          },
        },
      });

      if (result.error) {
        console.error(result.error.message);
        // Show error to your customer
      } else {
        // Payment succeeded, show a success message
        console.log('Payment succeeded:', result.paymentIntent);
      }
    } catch (error) {
      console.error('Error confirming payment:', error);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <CardElement />
      <button type="submit" disabled={!stripe}>
        Pay
      </button>
    </form>
  );
};

export default CheckoutForm;

/* const CheckoutForm = () => {
    return (
      <form>
        <PaymentElement />
        <button>Submit</button>
      </form>
    );
  }; */
