import React, { createContext, useContext, useState } from 'react';

interface IProduct {
  price: number;
  quantity: number;
  id: number;
  title: string; // Ajoutez la propriété 'title'
  // Autres propriétés de produit
}

interface ICartContext {
  cartItems: IProduct[];
  addToCart: (product: IProduct) => void;
  resetCart: () => void;
  children?: React.ReactNode; // Propriété enfants optionnelle
}

export const CartContext = createContext<ICartContext | undefined>(undefined); // Définissez CartContext comme une exportation nommée

export interface CartItem extends IProduct {
  product: IProduct; // Référence au produit (IProduct)
  quantity: number; // Quantité de fois que le produit a été ajouté au panier
  subtotal: number; // Sous-total (prix total de la ligne de produit)
}

export const CartProvider: React.FC<ICartContext> = ({ children }) => {
  const [cartItems, setCartItems] = useState<CartItem[]>([]);

  const addToCart = (product: IProduct) => {
    const existingItemIndex = cartItems.findIndex(item => item.product.id === product.id);

    if (existingItemIndex !== -1) {
      const updatedCartItems = [...cartItems];
      updatedCartItems[existingItemIndex].quantity++;
      updatedCartItems[existingItemIndex].subtotal = updatedCartItems[existingItemIndex].quantity * product.price;
      setCartItems(updatedCartItems);
    } else {
      // Créez une nouvelle ligne de produit (CartItem)
      const newCartItem: CartItem = {
        product,
        quantity: 1,
        subtotal: 0,
        price: product.price,
        id: product.id,
        title: product.title,
      };
      setCartItems([...cartItems, newCartItem]);
    }
  };

  const resetCart = () => {
    setCartItems([]);
  };

  return <CartContext.Provider value={{ cartItems, addToCart, resetCart }}>{children}</CartContext.Provider>;
};

export const useCart = () => {
  const context = useContext(CartContext);
  if (!context) {
    throw new Error('useCart must be used within a CartProvider');
  }
  return context;
};
