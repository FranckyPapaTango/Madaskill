import React, { useState, useEffect } from 'react';
import axios from 'axios';
import ProductDetails from './productdetails';
import { IProduct } from 'app/shared/model/product.model';
import { Translate } from 'react-jhipster';
import './product-list.scss';
import Cart from './cart';
//import { useCart } from './CartContext';

const ProductList: React.FC = () => {
  const [products, setProducts] = useState([]);
  const [selectedProduct, setSelectedProduct] = useState<IProduct | null>(null);

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

  // Fonction pour ajouter un produit au panier
  //const { addToCart } = useCart(); // Utilisez useCart pour ajouter des produits au panier

  return (
    <div>
      {selectedProduct ? (
        <ProductDetails product={selectedProduct} onBack={goBackToList} />
      ) : (
        <>
          <div>
            <h2>Liste des Produits</h2>
            <div className="product-list">
              {products && products.length > 0 ? (
                <div className="product-container">
                  {products.map(product => (
                    <div className="product-card" key={product.id}>
                      <div onClick={() => showProductDetails(product)}>
                        <img src={product.linkToGenericPhotoFile} alt={product.title} />
                        <div className="content">
                          <p>{product.title}</p>
                          &nbsp;&nbsp;&nbsp;
                          <p>{product.description}</p>
                          <p className="price">
                            &nbsp;&nbsp;&nbsp;
                            {product.price % 1 === 0
                              ? product.price.toFixed(0).replace(/\d(?=(\d{3})+(?!\d))/g, '$& ') + ' €'
                              : product.price.toFixed(2).replace(/\d(?=(\d{3})+\.)/g, '$& ') + ' €'}
                          </p>
                        </div>
                      </div>
                      {/*                       <button className="cartbtn" onClick={() => addToCart(product)}>
                        Ajouter au Panier
                      </button> */}{' '}
                      {/* Bouton "Add to Cart" */}
                    </div>
                  ))}
                </div>
              ) : (
                <div className="alert alert-warning">No Products found</div>
              )}
            </div>
          </div>
        </>
      )}
    </div>
  );
};

export default ProductList;
