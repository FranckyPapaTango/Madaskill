import React, { useState, useEffect } from 'react';
import axios from 'axios';
import ProductDetails from './productdetails';
import { IProduct } from 'app/shared/model/product.model';
import { Translate } from 'react-jhipster';
import './product-list.scss';
import Cart from './cart';

const ProductList: React.FC = () => {
  const [products, setProducts] = useState([]);
  const [selectedProduct, setSelectedProduct] = useState<IProduct | null>(null);

  // État pour le panier
  const [cartItems, setCartItems] = useState([]);
  // Fonction pour ajouter un produit au panier
  const addToCart = (product: IProduct) => {
    // Vérifiez si le produit existe déjà dans le panier
    const existingItem = cartItems.find(item => item.id === product.id);

    if (existingItem) {
      // Le produit existe déjà, mettez à jour la quantité
      const updatedCartItems = cartItems.map(item => {
        if (item.id === product.id) {
          return { ...item, quantity: item.quantity + 1 };
        }
        return item;
      });
      setCartItems(updatedCartItems);
    } else {
      // Le produit n'est pas encore dans le panier, ajoutez-le avec une quantité de 1
      setCartItems([...cartItems, { ...product, quantity: 1 }]);
    }
  };

  useEffect(() => {
    axios
      .get('/api/productsall')
      .then(response => {
        setProducts(response.data);
      })
      .catch(error => {
        console.error('Error fetching products:', error);
      });
  }, []);

  const showProductDetails = (product: IProduct) => {
    setSelectedProduct(product);
  };

  const goBackToList = () => {
    setSelectedProduct(null);
  };

  return (
    <div>
      {selectedProduct ? (
        <ProductDetails product={selectedProduct} onBack={goBackToList} />
      ) : (
        <div>
          <h2>Liste des Produits</h2>
          <div className="product-list">
            <div className="product-container">
              {products && products.length > 0 ? (
                products.map(product => (
                  <div>
                    <div
                      key={product.id}
                      className="product-card"
                      onClick={() => showProductDetails(product)} // Ajout du gestionnaire de clic
                      onTouchStart={() => showProductDetails(product)} // Réagit aux touchés sur smartphones
                    >
                      <img src={product.linkToGenericPhotoFile} alt={product.title} />
                      <p>{product.title}</p>
                      &nbsp;&nbsp;&nbsp;
                      <p>{product.description}</p>
                      <div className="price-and-button">
                        &nbsp;&nbsp;&nbsp;
                        {product.price % 1 === 0
                          ? product.price.toFixed(0).replace(/\d(?=(\d{3})+(?!\d))/g, '$& ') + ' €'
                          : product.price.toFixed(2).replace(/\d(?=(\d{3})+\.)/g, '$& ') + ' €'}
                      </div>
                    </div>
                    <button className="cartbtn" onClick={() => addToCart(product)}>
                      Ajouter au Panier
                    </button>{' '}
                    {/* Bouton "Add to Cart" */}
                  </div>
                ))
              ) : (
                <div className="alert alert-warning">No Products found</div>
              )}
            </div>
          </div>
        </div>
      )}
      <Cart cartItems={cartItems} /> {/*  Affichez le panier avec les produits ajoutés */}
    </div>
  );
};

export default ProductList;
