import React, { createContext, useContext, useState } from 'react';

export interface IProduct {
  price: number;
  quantity: number;
  id: number;
  title: string; // Ajoutez la propriété 'title'
  linkToGenericPhotoFile: string; // Autres propriétés de produit
}

interface ICartContext {
  cartItems: IProduct[];
  addToCart: (product: IProduct) => void;
  resetCart: () => void;
  removeFromCart: (product: IProduct) => void; // Ajoutez la méthode removeFromCart
  children?: React.ReactNode;
}

export const CartContext = createContext<ICartContext | undefined>(undefined); // Définissez CartContext comme une exportation nommée

export interface CartItem extends IProduct {
  product: IProduct; // Référence au produit (IProduct)
  quantity: number; // Quantité de fois que le produit a été ajouté au panier
  subtotal: number; // Sous-total (prix total de la ligne de produit)
  linkToGenericPhotoFile: string; // Ajoutez l'attribut pour l'image
}

export const CartProvider: React.FC<ICartContext> = ({ children }) => {
  const [cartItems, setCartItems] = useState<CartItem[]>([]);

  const addToCart = (product: IProduct) => {
    //  const addToCart = (product: Omit<IProduct, 'quantity'>) => {
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
        linkToGenericPhotoFile: product.linkToGenericPhotoFile, // Ajoutez l'attribut pour l'image
      };
      setCartItems([...cartItems, newCartItem]);
    }
  };

  const removeFromCart = (product: IProduct) => {
    const existingItemIndex = cartItems.findIndex(item => item.id === product.id);

    if (existingItemIndex !== -1) {
      const updatedCartItems = [...cartItems];
      const existingItem = updatedCartItems[existingItemIndex];

      if (existingItem.quantity > 1) {
        existingItem.quantity--;
        existingItem.subtotal = existingItem.quantity * existingItem.price;
      } else {
        updatedCartItems.splice(existingItemIndex, 1);
      }

      setCartItems(updatedCartItems);
    }
  };

  const resetCart = () => {
    setCartItems([]);
  };

  return <CartContext.Provider value={{ cartItems, addToCart, resetCart, removeFromCart }}>{children}</CartContext.Provider>;
};

export const useCart = () => {
  const context = useContext(CartContext);
  if (!context) {
    throw new Error('useCart must be used within a CartProvider');
  }
  return context;
};
