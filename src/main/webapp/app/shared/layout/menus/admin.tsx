import React from 'react';
import MenuItem from 'app/shared/layout/menus/menu-item';
import { DropdownItem } from 'reactstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { NavDropdown } from './menu-components';
import { Translate, translate } from 'react-jhipster';

const adminMenuItems = () => (
  <>
    <MenuItem icon="users" to="/admin/user-management">
      <Translate contentKey="global.menu.admin.userManagement">User management</Translate>
    </MenuItem>
    <MenuItem icon="eye" to="/admin/tracker">
      <Translate contentKey="global.menu.admin.tracker">User tracker</Translate>
    </MenuItem>
    <MenuItem icon="tachometer-alt" to="/admin/metrics">
      <Translate contentKey="global.menu.admin.metrics">Metrics</Translate>
    </MenuItem>
    <MenuItem icon="heart" to="/admin/health">
      <Translate contentKey="global.menu.admin.health">Health</Translate>
    </MenuItem>
    <MenuItem icon="cogs" to="/admin/configuration">
      <Translate contentKey="global.menu.admin.configuration">Configuration</Translate>
    </MenuItem>
    <MenuItem icon="tasks" to="/admin/logs">
      <Translate contentKey="global.menu.admin.logs">Logs</Translate>
    </MenuItem>
    <MenuItem icon="database" to="/admin/backup">
      {/* <Translate contentKey="global.menu.admin.backup">Create Database Backup</Translate> */}
      <p>Create Database Backup</p>
    </MenuItem>
    {/* jhipster-needle-add-element-to-admin-menu - JHipster will add entities to the admin menu here */}
  </>
);

const openAPIItem = onClick => (
  <MenuItem icon="book" to="/admin/docs" onClick={onClick}>
    <Translate contentKey="global.menu.admin.apidocs">API</Translate>
  </MenuItem>
);

const databaseItem = () => (
  <DropdownItem tag="a" href="./h2-console/" target="_tab">
    <FontAwesomeIcon icon="database" fixedWidth /> <Translate contentKey="global.menu.admin.database">Database</Translate>
  </DropdownItem>
);

export const AdminMenu = ({ showOpenAPI, showDatabase, onClick }) => (
  <NavDropdown icon="users-cog" name={translate('global.menu.admin.main')} id="admin-menu" data-cy="adminMenu">
    {adminMenuItems()}
    {showOpenAPI && openAPIItem(onClick)}
    {showDatabase && databaseItem()}
  </NavDropdown>
);

export default AdminMenu;
