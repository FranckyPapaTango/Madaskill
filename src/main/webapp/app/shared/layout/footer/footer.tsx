import './footer.scss';

// Footer.tsx
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faFacebook, faTwitter, faInstagram, faSnapchat, faLinkedin } from '@fortawesome/free-brands-svg-icons';
import React from 'react';
import { Translate } from 'react-jhipster';
import { Col, Row } from 'reactstrap';

const Footer = () => (
  <div className="footer page-content">
    <footer className="footer-container">
      <p>
        <Translate contentKey="footer">Your footer</Translate>
      </p>

      <div className="text-blocks">
        <Col md={3} sm={6}>
          <div className="text-block">
            Sté François Yvon RAFARALAHY
            <br />
            Freelance Programmation Informatique
            <br />
            92120 Montrouge - FRANCE
          </div>
        </Col>
        <Col md={3} sm={6}>
          <div className="text-block">
            &#9829; Contact :<br />
            rafaralahyf@gmail.com
            <br />
            +33 6 95 09 68 56
          </div>
        </Col>
        <Col md={3} sm={6}>
          <div className="text-block">Copyright &copy; 2023</div>
        </Col>
        {/* <Col md={3} sm={6}>
          <div className="text-block">Bloc 4</div>
        </Col> */}
      </div>
      <div className="social-icons">
        <div className="social-icon-container">
          <a href="lien_vers_facebook" target="_blank">
            <FontAwesomeIcon icon={faFacebook} className="social-icon" />
          </a>
        </div>
        <div className="social-icon-container">
          <a href="lien_vers_twitter" target="_blank">
            <FontAwesomeIcon icon={faTwitter} className="social-icon" />
          </a>
        </div>
        <div className="social-icon-container">
          <a href="lien_vers_instagram" target="_blank">
            <FontAwesomeIcon icon={faInstagram} className="social-icon" />
          </a>
        </div>
        <div className="social-icon-container">
          <a href="lien_vers_snapchat" target="_blank">
            <FontAwesomeIcon icon={faSnapchat} className="social-icon" />
          </a>
        </div>
        <div className="social-icon-container">
          <a href="lien_vers_linkedin" target="_blank">
            <FontAwesomeIcon icon={faLinkedin} className="social-icon" />
          </a>
        </div>
      </div>
    </footer>
  </div>
);

export default Footer;
