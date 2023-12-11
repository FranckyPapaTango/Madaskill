import './header.scss';

import React, { useState, useEffect } from 'react';
import { Translate, Storage } from 'react-jhipster';
import { Navbar, Nav, NavbarToggler, Collapse } from 'reactstrap';
import LoadingBar from 'react-redux-loading-bar';

import { isRTL } from 'app/config/translation';
import { Home, Brand } from './header-components';
import { AdminMenu, EntitiesMenu, AccountMenu, LocaleMenu } from '../menus';
import { useAppDispatch } from 'app/config/store';
import { AboutUs } from './header-components';
import { VentesEnLigne } from './header-components';
import { Annonces } from './header-components';
import { setLocale } from 'app/shared/reducers/locale';

export interface IHeaderProps {
  isAuthenticated: boolean;
  isAdmin: boolean;
  ribbonEnv: string;
  isInProduction: boolean;
  isOpenAPIEnabled: boolean;
  currentLocale: string;
}

const Header = (props: IHeaderProps) => {
  const [menuOpen, setMenuOpen] = useState(false);
  useEffect(() => document.querySelector('html').setAttribute('dir', isRTL(Storage.session.get('locale')) ? 'rtl' : 'ltr'));

  const dispatch = useAppDispatch();

  const handleLocaleChange = event => {
    const langKey = event.target.value;
    Storage.session.set('locale', langKey);
    dispatch(setLocale(langKey));
    document.querySelector('html').setAttribute('dir', isRTL(langKey) ? 'rtl' : 'ltr');
  };

  const renderDevRibbon = () =>
    props.isInProduction === false ? (
      <div className="ribbon dev">
        <a href="">
          <Translate contentKey={`global.ribbon.${props.ribbonEnv}`} />
        </a>
      </div>
    ) : null;

  const toggleMenu = () => setMenuOpen(!menuOpen);
  const handleMenuItemClick = () => {
    // Appeler toggleMenu pour fermer le menu burger
    toggleMenu();
  };

  /* jhipster-needle-add-element-to-menu - JHipster will add new menu items here */

  return (
    <div id="app-header">
      {renderDevRibbon()}
      <LoadingBar className="loading-bar" />
      <Navbar data-cy="navbar" dark expand="md" fixed="top" className="navbarr">
        <NavbarToggler aria-label="Menu" onClick={toggleMenu} />
        <Brand />
        <Collapse isOpen={menuOpen} navbar>
          <Nav id="header-tabs" className="ms-auto" navbar>
            <Home onClick={handleMenuItemClick} />
            <VentesEnLigne onClick={handleMenuItemClick} />
            <Annonces onClick={handleMenuItemClick} />
            <AboutUs onClick={handleMenuItemClick} />
            {props.isAuthenticated && <EntitiesMenu onClick={handleMenuItemClick} />}
            {props.isAuthenticated && props.isAdmin && (
              <AdminMenu showOpenAPI={props.isOpenAPIEnabled} showDatabase={!props.isInProduction} onClick={handleMenuItemClick} />
            )}
            <LocaleMenu currentLocale={props.currentLocale} onLocaleChange={handleLocaleChange} onClick={handleMenuItemClick} />
            <AccountMenu isAuthenticated={props.isAuthenticated} onClick={handleMenuItemClick} />
          </Nav>
        </Collapse>
      </Navbar>
    </div>
  );
};

export default Header;
