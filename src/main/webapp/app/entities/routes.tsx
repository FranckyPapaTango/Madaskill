import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Post from './post';
import Product from './product';
import ApplicationUser from './application-user';
import Customer from './customer';
import InvoiceSettings from './invoice-settings';
import Metadata from './metadata';
import PreferredLocales from './preferred-locales';
import Commande from './commande';
import Photo from './photo';
import Invoice from './invoice';
import LineItem from './line-item';
/* jhipster-needle-add-route-import - JHipster will add routes here */

export default () => {
  return (
    <div>
      <ErrorBoundaryRoutes>
        {/* prettier-ignore */}
        <Route path="post/*" element={<Post />} />
        <Route path="product/*" element={<Product />} />
        <Route path="application-user/*" element={<ApplicationUser />} />
        <Route path="customer/*" element={<Customer />} />
        <Route path="invoice-settings/*" element={<InvoiceSettings />} />
        <Route path="metadata/*" element={<Metadata />} />
        <Route path="preferred-locales/*" element={<PreferredLocales />} />
        <Route path="commande/*" element={<Commande />} />
        <Route path="photo/*" element={<Photo />} />
        <Route path="invoice/*" element={<Invoice />} />
        <Route path="line-item/*" element={<LineItem />} />
        {/* jhipster-needle-add-route-path - JHipster will add routes here */}
      </ErrorBoundaryRoutes>
    </div>
  );
};
