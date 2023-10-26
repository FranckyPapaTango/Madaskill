import React, { useState, useEffect } from 'react';
import { Translate } from 'react-jhipster';
import './product-list.scss';
import axios from 'axios'; // Exemple d'utilisation d'Axios

const ProductList: React.FC = () => {
  const [products, setProducts] = useState([]); // State pour stocker les produits

  useEffect(() => {
    // Appel à l'API pour récupérer les produits
    axios
      .get('/api/products') // Remplacez par l'URL de votre API
      .then(response => {
        setProducts(response.data); // Met à jour le state avec les données des produits
      })
      .catch(error => {
        console.error('Error fetching products:', error);
      });
  }, []);

  return (
    <div>
      <h2>Liste des Produits</h2>
      <div className="product-list">
        <div className="product-container">
          {products && products.length > 0 ? (
            products.map(product => (
              <div key={product.id} className="product-card">
                <img src={product.linkToGenericPhotoFile} alt={product.title} />
                <p>{product.title}</p>
                <p>{product.description}</p>
                <p>{product.price} Euros</p>
              </div>
            ))
          ) : (
            <div className="alert alert-warning">
              <Translate contentKey="jhipsterMadaskillApp.product.home.notFound">No Products found</Translate>
            </div>
          )}
        </div>
      </div>
    </div>
  );
};

export default ProductList;
