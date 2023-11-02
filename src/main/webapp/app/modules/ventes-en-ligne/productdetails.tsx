import { IProduct } from 'app/shared/model/product.model';
import React from 'react';

interface ProductDetailsProps {
  product: IProduct; // Utilisez le type IProduct approprié
  onBack: () => void;
}

const ProductDetails: React.FC<ProductDetailsProps> = ({ product, onBack }) => {
  return (
    <div>
      <h2>Détails du Produit</h2>
      <div className="product-details">
        <img src={product.linkToGenericPhotoFile} alt={product.title} />
        <p>{product.title}</p>
        <p>{product.description}</p>
        <p>Nombre de paiements:{product.installments}</p>
        <p>{product.price} Euros</p>
        <button onClick={onBack}>Retour</button>
      </div>
    </div>
  );
};

export default ProductDetails;
