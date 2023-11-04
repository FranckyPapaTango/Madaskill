import './ventes-en-ligne.scss';
import React, { useState } from 'react';
import { Translate } from 'react-jhipster';
import { connect } from 'react-redux';
import { Row, Col } from 'reactstrap';
import ProductList from './product-list';
import { FaShoppingCart } from 'react-icons/fa';
import Cart from './cart';
import { useCart, CartProvider, CartItem } from './CartContext'; // Importez les types et le context depuis le fichier CartContext
import { IProduct } from 'app/shared/model/product.model';
//import CartContext from './CartContext'; // Importez le context depuis le fichier CartContext

export const VentesEnLigne = () => {
  const { cartItems } = useCart();

  const [isCartModalVisible, setIsCartModalVisible] = useState(false);
  const toggleCartModal = () => {
    setIsCartModalVisible(!isCartModalVisible);
  };

  return (
    <Row>
      <div className="cart-button" onClick={toggleCartModal}>
        <FaShoppingCart />
      </div>
      {isCartModalVisible && (
        <div className="cart-modal">
          <Cart
            onClose={toggleCartModal}
            cartItems={cartItems}
            updateCartItems={function (cartItems: CartItem[]): void {
              throw new Error('Function not implemented.');
            }}
          />
        </div>
      )}

      <Col md="1" />
      <Col md="10">
        <h1 className="text-capitalize text-center">
          <Translate contentKey="ventesEnLigne.title">Title</Translate>
        </h1>
        <h5 className="text-center">
          <Translate contentKey="ventesEnLigne.subtitle">Subtitle</Translate>
        </h5>
        <hr />
        {/* <p className="text-justify">
      <Translate contentKey="ventesEnLigne.content">Content</Translate>
    </p> */}
        <ProductList />
      </Col>
      <Col md="1" />
    </Row>
  );
};

const mapStateToProps = storeState => ({
  message: storeState.message,
});

type StateProps = ReturnType<typeof mapStateToProps>;

export default connect(mapStateToProps)(VentesEnLigne);
