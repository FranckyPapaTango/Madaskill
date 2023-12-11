import React from 'react';
import { DropdownItem } from 'reactstrap';
import { NavLink as Link } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IconProp } from '@fortawesome/fontawesome-svg-core';

export interface IMenuItem {
  children: React.ReactNode;
  icon: IconProp;
  to: string;
  id?: string;
  'data-cy'?: string;
  onClick?: () => void; // Ajoutez cette ligne
}

const MenuItem = (props: IMenuItem) => {
  const { to, icon, id, children, onClick } = props;

  return (
    <DropdownItem tag={Link} to={to} id={id} data-cy={props['data-cy']} onClick={onClick}>
      <FontAwesomeIcon icon={icon} fixedWidth /> {children}
    </DropdownItem>
  );
};

export default MenuItem;
