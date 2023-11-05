import React, { useState } from 'react';
import './cart.scss';
import { useCart } from './CartContext';

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

  return (
    <div className="cart-modal-content">
      <h2>Mon Panier</h2>
      {/* <button onClick={resetCart}>Reset</button> */}
      <button onClick={handleResetClick}>Réinitialiser le panier</button>
      <div className="cart-item-list-container">
        {cartItems.map(item => (
          <div className="cart-item" key={item.id}>
            <img src={item.linkToGenericPhotoFile} alt={item.title} />
            <div className="cart-item-text">
              {item.title} - Prix : {formatNumber(item.price)} - Quantité : {formatNumber(item.quantity)} - Sous-total :{' '}
              {formatNumber(calculateSubtotal(item))} €
            </div>
          </div>
        ))}
      </div>
      <div>
        Total Global :<p className="cart-total"> {formatNumber(calculateTotal())} €</p>
      </div>
      <button onClick={onClose}>Retour</button>
    </div>
  );
};

export default Cart;
