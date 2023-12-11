import React from 'react';
import { DropdownItem } from 'reactstrap';
import { NavDropdown } from './menu-components';
import { locales, languages } from 'app/config/translation';

export const LocaleMenu = ({
  currentLocale,
  onLocaleChange,
  onClick,
}: {
  currentLocale: string;
  onLocaleChange: (event: any) => void;
  onClick: (event: any) => void;
}) =>
  Object.keys(languages).length > 1 ? (
    <NavDropdown icon="flag" name={currentLocale ? languages[currentLocale].name : undefined}>
      {locales.map(locale => (
        <DropdownItem
          key={locale}
          value={locale}
          onClick={e => {
            onLocaleChange(e);
            onClick(e);
          }}
        >
          {languages[locale].name}
        </DropdownItem>
      ))}
    </NavDropdown>
  ) : null;
