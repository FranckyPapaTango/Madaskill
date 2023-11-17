import React, { ReactNode, useEffect, useState } from 'react';
import axios from 'axios';
import { Translate } from 'react-jhipster';
import { Col, Row } from 'reactstrap';
import { connect } from 'react-redux';

// Définir le type pour l'objet Post
interface Post {
  id: number;
  title: string;
  body?: string;
  categorie?: string;
  resumee?: string;
  auteur?: string;
  datePersistence: string; // ou de type Instant selon votre besoin
  photos?: Photo[]; // Ajoutez le type pour les photos si nécessaire
  user?: ApplicationUser; // Ajoutez le type pour l'utilisateur si nécessaire
}

// Définir le type pour l'objet Photo
interface Photo {
  url: string;
  description: string;
  id: number;
  // Ajoutez les autres propriétés nécessaires pour une photo
}

// Définir le type pour l'objet ApplicationUser
interface ApplicationUser {
  username: ReactNode;
  // Ajoutez les propriétés nécessaires pour un utilisateur
}

// Composant d'affichage d'une carte de post
const PostCard: React.FC<Post> = ({ title, body, categorie, resumee, auteur, datePersistence, photos, user }) => {
  return (
    <div>
      <h2>{title}</h2>
      <p>{body}</p>
      <p>Categorie: {categorie}</p>
      <p>Resumee: {resumee}</p>
      <p>Auteur: {auteur}</p>
      <p>Date de Persistence: {datePersistence}</p>
      {/* Affichez les photos si nécessaire */}
      {photos && photos.map(photo => <img key={photo.id} src={photo.url} alt={photo.description} />)}
      {/* Affichez l'utilisateur si nécessaire */}
      {user && <p>Utilisateur: {user.username}</p>}
    </div>
  );
};

export type IAnnoncesProp = StateProps;
// Composant de la page de posts

export const Annonces = (props: IAnnoncesProp) => {
  const { message } = props;
  const [posts, setPosts] = useState<Array<Post>>([]);

  useEffect(() => {
    // Effectuer la requête HTTP pour récupérer les données des posts depuis l'API
    axios
      .get('/api/posts')
      .then(response => {
        setPosts(response.data);
      })
      .catch(error => {
        console.error('Erreur lors de la récupération des posts', error);
      });
  }, []); // Le tableau vide signifie que cet effet ne s'exécutera qu'une fois au montage du composant

  return (
    <Row>
      <Col md="1" />
      <Col md="10">
        <h6 className="text-right">{message}</h6>
        <h1 className="text-capitalize text-center">
          <Translate contentKey="annonces.title">Title</Translate>
        </h1>
        <h5 className="text-center">
          <Translate contentKey="annonces.subtitle">Subtitle</Translate>
        </h5>
        <hr />
        <p className="text-justify">
          <div>
            {posts.map(post => (
              <PostCard key={post.id} {...post} />
            ))}
          </div>
          );
        </p>
      </Col>
      <Col md="1" />
    </Row>
  );
};

const mapStateToProps = storeState => ({
  message: storeState.message,
});

type StateProps = ReturnType<typeof mapStateToProps>;

export default connect(mapStateToProps)(Annonces);
