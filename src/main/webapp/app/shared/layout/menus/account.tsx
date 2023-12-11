import React from 'react';
import MenuItem from 'app/shared/layout/menus/menu-item';
import { Translate, translate } from 'react-jhipster';
import { NavDropdown } from './menu-components';

const accountMenuItemsAuthenticated = ({ onClick }: { onClick?: () => void }) => (
  <>
    <MenuItem icon="wrench" to="/account/settings" data-cy="settings" onClick={onClick}>
      <Translate contentKey="global.menu.account.settings">Settings</Translate>
    </MenuItem>
    <MenuItem icon="lock" to="/account/password" data-cy="passwordItem" onClick={onClick}>
      <Translate contentKey="global.menu.account.password">Password</Translate>
    </MenuItem>
    <MenuItem icon="sign-out-alt" to="/logout" data-cy="logout" onClick={onClick}>
      <Translate contentKey="global.menu.account.logout">Sign out</Translate>
    </MenuItem>
  </>
);

const accountMenuItems = ({ onClick }: { onClick?: () => void }) => (
  <>
    <MenuItem id="login-item" icon="sign-in-alt" to="/login" data-cy="login" onClick={onClick}>
      <Translate contentKey="global.menu.account.login">Sign in</Translate>
    </MenuItem>
    <MenuItem icon="user-plus" to="/account/register" data-cy="register" onClick={onClick}>
      <Translate contentKey="global.menu.account.register">Register</Translate>
    </MenuItem>
  </>
);

export const AccountMenu = ({ isAuthenticated = false, onClick }: { isAuthenticated?: boolean; onClick?: () => void }) => (
  <NavDropdown icon="user" name={translate('global.menu.account.main')} id="account-menu" data-cy="accountMenu">
    {isAuthenticated ? accountMenuItemsAuthenticated({ onClick }) : accountMenuItems({ onClick })}
  </NavDropdown>
);
export default AccountMenu;
