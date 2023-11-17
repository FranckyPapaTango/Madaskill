import { IProduct } from 'app/shared/model/product.model';
import React, { useEffect, useState } from 'react';
import './ProductDetails.css';

interface ProductDetailsProps {
  product: IProduct;
  onBack: () => void;
}

interface IPhoto {
  id: number;
  linkToPhotoFile: string;
  title: string;
  description: string;
}

const ProductDetails: React.FC<ProductDetailsProps> = ({ product, onBack }) => {
  const [photos, setPhotos] = useState<IPhoto[]>([]);

  useEffect(() => {
    const fetchPhotos = async () => {
      try {
        const response = await fetch(`/api/products/${product.id}/photos`);
        const data = await response.json();
        setPhotos(data);
      } catch (error) {
        console.error('Error fetching photos:', error);
      }
    };

    fetchPhotos();
  }, [product.id]);

  return (
    <div className="product-details">
      <div className="product-generic">
        <img src={product.linkToGenericPhotoFile} alt={product.title} />
        <p>{product.title}</p>
        <p>{product.description}</p>
        <p>Nombre de paiements: {product.installments}</p>
        <p>{product.price} Euros</p>
        <button onClick={onBack}>Retour</button>
      </div>

      {Array.isArray(photos) &&
        photos.map(photo => (
          <div key={photo.id}>
            <img src={photo.linkToPhotoFile} alt={photo.title} />
            <p>{photo.title}</p>
            <p>{photo.description}</p>
          </div>
        ))}
    </div>
  );
};

export default ProductDetails;
