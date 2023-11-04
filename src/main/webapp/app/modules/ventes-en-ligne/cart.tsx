import React, { useState } from 'react';
import './cart.scss';

interface CartProps {
  cartItems: any[]; // Déclarez le type des cartItems ici
  onClose: any;
}

const Cart: React.FC<CartProps> = ({ cartItems, onClose }) => {
  const [cart, setCart] = useState([]); // Renommez la variable locale

  // Fonction pour ajouter un produit au panier
  const addToCart = product => {
    const existingItemIndex = cartItems.findIndex(item => item.id === product.id);
    if (existingItemIndex !== -1) {
      // Le produit existe déjà dans le panier, mettez à jour la quantité
      const updatedCartItems = [...cartItems];
      updatedCartItems[existingItemIndex].quantity++;
      setCart(updatedCartItems);
    } else {
      // Le produit n'est pas encore dans le panier, ajoutez-le
      setCart([...cartItems, { ...product, quantity: 1 }]);
    }
  };

  // Fonction pour calculer le sous-total d'un produit
  const calculateSubtotal = product => {
    return product.price * product.quantity;
  };

  // Fonction pour calculer le total global du panier
  const calculateTotal = () => {
    return cartItems.reduce((total, product) => total + calculateSubtotal(product), 0);
  };

  return (
    <div className="cart-modal-content">
      <h2>Mon Panier</h2>
      <ul>
        {cartItems.map(item => (
          <li key={item.id}>
            {item.title} - Quantité : {item.quantity} - Sous-total : {calculateSubtotal(item)} €
          </li>
        ))}
      </ul>
      <p>Total Global : {calculateTotal()} €</p>
      <button onClick={onClose}>Fermer</button>
    </div>
  );
};

export default Cart;
