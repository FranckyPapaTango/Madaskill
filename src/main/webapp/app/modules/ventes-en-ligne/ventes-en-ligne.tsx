import './ventes-en-ligne.scss';
import React, { useEffect, useState } from 'react';
import { Translate } from 'react-jhipster';
import { connect } from 'react-redux';
import { Row, Col } from 'reactstrap';
import ProductList from './product-list';
import { FaShoppingCart } from 'react-icons/fa';
import Cart from './cart';
import { useCart, CartProvider, CartItem } from './CartContext'; // Importez les types et le context depuis le fichier CartContext
import { IProduct } from 'app/shared/model/product.model';

export const VentesEnLigne = () => {
  const { cartItems } = useCart();

  const [isCartModalVisible, setIsCartModalVisible] = useState(false);
  const toggleCartModal = () => {
    setIsCartModalVisible(!isCartModalVisible);
  };

  // Utilisez useEffect pour mettre à jour la quantité totale lorsque les cartItems changent
  useEffect(() => {
    const totalQuantity = cartItems.reduce((total, item) => total + item.quantity, 0);
    // Mettez à jour la quantité totale dans l'état local si vous en avez besoin ailleurs dans le composant
    // setTotalQuantity(totalQuantity);
  }, [cartItems]);

  return (
    <Row>
      <div className="cart-button" onClick={toggleCartModal}>
        <FaShoppingCart />
        &nbsp;<span className="qty-cart-symb">{cartItems.reduce((total, item) => total + item.quantity, 0)}</span>
      </div>
      {isCartModalVisible && (
        <div className="cart-modal">
          <Cart
            onClose={toggleCartModal}
            cartItems={cartItems}
            updateCartItems={function (): void {
              throw new Error('Function not implemented.');
            }}
            resetCart={function (): void {
              throw new Error('Function not implemented.');
            }}
          />
        </div>
      )}

      <h1 className="text-capitalize text-center">
        <Translate contentKey="ventesEnLigne.title">Title</Translate>
      </h1>
      <div className="subtitle">
        <div className="text-subtitle">
          <Translate contentKey="ventesEnLigne.subtitle">Subtitle</Translate>
        </div>
      </div>
      <hr />
      {/* <p className="text-justify">
      <Translate contentKey="ventesEnLigne.content">Content</Translate>
    </p> */}
      {/* <ProductList /> */}
      <ProductList toggleCartModal={toggleCartModal} />
    </Row>
  );
};

const mapStateToProps = storeState => ({
  message: storeState.message,
});

type StateProps = ReturnType<typeof mapStateToProps>;

export default connect(mapStateToProps)(VentesEnLigne);
