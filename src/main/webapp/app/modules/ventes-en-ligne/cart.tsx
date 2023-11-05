import React, { useState } from 'react';
import './cart.scss';
import { useCart } from './CartContext';

interface CartItem {
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
  //const [cart, setCart] = useState(cartItems);
  const { resetCart } = useCart();

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

  return (
    <div className="cart-modal-content">
      <h2>Mon Panier</h2>
      {/* <button onClick={resetCart}>Reset</button> */}
      <button onClick={handleResetClick}>Réinitialiser le panier</button>
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
