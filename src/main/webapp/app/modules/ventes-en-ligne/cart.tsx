import './cart.scss';
import { IProduct, useCart } from './CartContext';
import { Elements } from '@stripe/react-stripe-js';
import CheckoutForm from './CheckoutForm'; // Ajustez l'import

import { loadStripe } from '@stripe/stripe-js';
import { useState } from 'react';
import React from 'react';

const stripePromise = loadStripe(
  'pk_test_51NnDxjGKnxxYnXbnKEE7DnYJofjqUv8K1tPCVT6VnGRBAKQac419adnctQgJQWDa7GnvKyrJk9VMvxWVmNA6l1FQ00aahVPN1M'
);

interface CartItem {
  linkToGenericPhotoFile: string;
  id: number;
  title: string;
  price: number;
  quantity: number;
}

interface CartProps {
  cartItems: CartItem[];
  onClose: () => void;
  updateCartItems: (cartItems: CartItem[]) => void;
  resetCart: () => void;
}

const Cart: React.FC<CartProps> = ({ cartItems, onClose, updateCartItems }) => {
  const { resetCart, addToCart, removeFromCart } = useCart();
  const [clientSecret, setClientSecret] = useState<string | null>(null);
  const [showCheckoutForm, setShowCheckoutForm] = useState(false);

  // Fonction pour calculer le sous-total d'un produit
  const calculateSubtotal = product => {
    return product.price * product.quantity;
  };

  // Fonction pour calculer le total global du panier
  const calculateTotal = () => {
    return cartItems.reduce((total, product) => total + calculateSubtotal(product), 0);
  };

  const handleResetClick = () => {
    resetCart();
  };

  // Fonction pour formater un nombre avec séparateur de milliers et centimes (si non nuls)
  const formatNumber = number => {
    const parts = parseFloat(number).toFixed(2).split('.');
    const integerPart = parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, ' ');

    if (parseFloat(parts[1]) === 0) {
      return integerPart;
    } else {
      return integerPart + ',' + parts[1];
    }
  };

  const handleIncrement = (product: IProduct) => {
    addToCart(product); // Ajoute 1 à la quantité du produit
  };

  const handleDecrement = (product: IProduct) => {
    removeFromCart(product); // Ajoute 1 à la quantité du produit
  };

  const handleBuying = async (cartItems: CartItem[]) => {
    try {
      const totalAmount = calculateTotal() * 100;
      const response = await fetch('/api/payment/intents', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          Accept: 'application/json',
        },
        body: JSON.stringify({ amount: totalAmount }),
      });

      if (response.ok) {
        const result = await response.json();
        setClientSecret(result.client_secret);
        /*         alert(clientSecret); */ /* debugage verification */
        setShowCheckoutForm(true);
      } else {
        console.error('Erreur lors de la création de la session de paiement');
      }
    } catch (error) {
      console.error('Erreur réseau lors de la création de la session de paiement', error);
    }
  };

  /*   const stripe = useStripe();

  const elements = stripe.elements({ clientSecret });
  const paymentElement = elements.create('cardNumber'); // Utilisez 'cardNumber' au lieu de 'payment'
  paymentElement.mount('#payment-element'); */

  return (
    <>
      <div className="cart-modal-content">
        {clientSecret && stripePromise && showCheckoutForm ? (
          <>
            <h1>Paiement</h1>
            <Elements stripe={stripePromise} options={{ clientSecret }}>
              <CheckoutForm />
            </Elements>
          </>
        ) : (
          <>
            <h2>Mon Panier</h2>
            <button onClick={handleResetClick}>Réinitialiser le panier</button>
            <div className="cart-item-list-container">
              {cartItems.map(item => (
                <div className="cart-item" key={item.id}>
                  <img src={item.linkToGenericPhotoFile} alt={item.title} />
                  <div className="cart-item-text">
                    {item.title} - Prix : {formatNumber(item.price)} - Quantité : {formatNumber(item.quantity)} - Sous-total :{' '}
                    {formatNumber(calculateSubtotal(item))} €
                  </div>
                  &nbsp;&nbsp;
                  <button onClick={() => handleDecrement(item)}>-</button>
                  <button onClick={() => handleIncrement(item)}>+</button>
                </div>
              ))}
            </div>
            <div>
              Total Global :<p className="cart-total"> {formatNumber(calculateTotal())} €</p>
            </div>
            <button onClick={onClose}>Retour</button>
            {/* <button onClick={() => handleBuying(cartItems)}>Acheter</button> */}
            <button onClick={() => handleBuying(cartItems)}>Acheter</button>
          </>
        )}
      </div>
    </>
  );
};
export default Cart;
