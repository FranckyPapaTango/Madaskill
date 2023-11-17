import React from 'react';
import { Translate } from 'react-jhipster';

import { NavItem, NavLink, NavbarBrand } from 'reactstrap';
import { NavLink as Link } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faHeart, faStar } from '@fortawesome/free-solid-svg-icons';

export const BrandIcon = props => (
  <div {...props} className="brand-icon">
    <img src="content/images/national-malagasy-flag.gif" alt="Logo" />
  </div>
);

export const Brand = () => (
  <NavbarBrand tag={Link} to="/" className="brand-logo">
    <BrandIcon />
    <span className="brand-title">
      <Translate contentKey="global.title">Madaskill</Translate>
    </span>
    <span className="navbar-version">{VERSION}</span>
  </NavbarBrand>
);

export const Home = () => (
  <NavItem>
    <NavLink tag={Link} to="/" className="d-flex align-items-center">
      <FontAwesomeIcon icon="home" />
      <span>
        <Translate contentKey="global.menu.home">Home</Translate>
      </span>
    </NavLink>
  </NavItem>
);

export const VentesEnLigne = props => (
  <NavItem>
    <NavLink tag={Link} to="/ventes-en-ligne" className="d-flex align-items-center">
      <FontAwesomeIcon icon={faStar} />
      <span>
        <Translate contentKey="global.menu.ventesEnLigne">Ventes En Ligne</Translate>
      </span>
    </NavLink>
  </NavItem>
);
export const AboutUs = props => (
  <NavItem>
    <NavLink tag={Link} to="/about-us" className="d-flex align-items-center">
      <FontAwesomeIcon icon="hand-spock" />
      <span>
        <Translate contentKey="global.menu.aboutUs">A propos</Translate>
      </span>
    </NavLink>
  </NavItem>
);
