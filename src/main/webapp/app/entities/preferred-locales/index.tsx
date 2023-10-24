import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import PreferredLocales from './preferred-locales';
import PreferredLocalesDetail from './preferred-locales-detail';
import PreferredLocalesUpdate from './preferred-locales-update';
import PreferredLocalesDeleteDialog from './preferred-locales-delete-dialog';

const PreferredLocalesRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<PreferredLocales />} />
    <Route path="new" element={<PreferredLocalesUpdate />} />
    <Route path=":id">
      <Route index element={<PreferredLocalesDetail />} />
      <Route path="edit" element={<PreferredLocalesUpdate />} />
      <Route path="delete" element={<PreferredLocalesDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default PreferredLocalesRoutes;
