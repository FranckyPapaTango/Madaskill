import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import InvoiceSettings from './invoice-settings';
import InvoiceSettingsDetail from './invoice-settings-detail';
import InvoiceSettingsUpdate from './invoice-settings-update';
import InvoiceSettingsDeleteDialog from './invoice-settings-delete-dialog';

const InvoiceSettingsRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<InvoiceSettings />} />
    <Route path="new" element={<InvoiceSettingsUpdate />} />
    <Route path=":id">
      <Route index element={<InvoiceSettingsDetail />} />
      <Route path="edit" element={<InvoiceSettingsUpdate />} />
      <Route path="delete" element={<InvoiceSettingsDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default InvoiceSettingsRoutes;
