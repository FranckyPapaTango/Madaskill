import React from 'react';
import { Translate } from 'react-jhipster';

import MenuItem from 'app/shared/layout/menus/menu-item';

const EntitiesMenu = () => {
  return (
    <>
      {/* prettier-ignore */}
      <MenuItem icon="asterisk" to="/post">
        <Translate contentKey="global.menu.entities.post" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/product">
        <Translate contentKey="global.menu.entities.product" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/application-user">
        <Translate contentKey="global.menu.entities.applicationUser" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/customer">
        <Translate contentKey="global.menu.entities.customer" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/invoice-settings">
        <Translate contentKey="global.menu.entities.invoiceSettings" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/metadata">
        <Translate contentKey="global.menu.entities.metadata" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/preferred-locales">
        <Translate contentKey="global.menu.entities.preferredLocales" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/commande">
        <Translate contentKey="global.menu.entities.commande" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/photo">
        <Translate contentKey="global.menu.entities.photo" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/invoice">
        <Translate contentKey="global.menu.entities.invoice" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/line-item">
        <Translate contentKey="global.menu.entities.lineItem" />
      </MenuItem>
      {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
    </>
  );
};

export default EntitiesMenu;
