import { IProduct } from 'app/shared/model/product.model';
import React, { useEffect, useState } from 'react';
import './ProductDetails.css';
import { useCart } from './CartContext';
import Cart from './cart';

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
  const { cartItems } = useCart();
  // Fonction pour ajouter un produit au panier
  const { addToCart } = useCart(); // Utilisez useCart pour ajouter des produits au panier
  const [isCartModalVisible, setIsCartModalVisible] = useState(false);
  const toggleCartModal = () => {
    setIsCartModalVisible(!isCartModalVisible);
  };

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
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        {/* Ajoutez le bouton "Ajouter au Panier" ici */}
        {/* <button onClick={() => addToCart(product)}>Ajouter au Panier</button> */}
        {/* <button onClick={() => addToCart({ ...product, quantity: 1 })}>Ajouter au Panier</button> */}
        {/* <button onClick={() => addToCart({ ...product, quantity: 1, id: product.id || 0 })}>Ajouter au Panier</button> */}
        {/* <button onClick={() => addToCart({ ...product, quantity: 1, id: product.id || 0, title: 'Product Title' })}>Ajouter au Panier</button> */}
        <button
          onClick={() =>
            addToCart({ ...product, quantity: 1, id: product.id || 0, title: 'Product Title', linkToGenericPhotoFile: 'default-photo.jpg' })
          }
        >
          Ajouter au Panier
        </button>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <button onClick={() => toggleCartModal()}>Voir Panier</button> {/* Ajoutez un bouton pour afficher le panier */}
        {/* Affichage du modal du panier */}
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
