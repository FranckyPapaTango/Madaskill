import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Metadata from './metadata';
import MetadataDetail from './metadata-detail';
import MetadataUpdate from './metadata-update';
import MetadataDeleteDialog from './metadata-delete-dialog';

const MetadataRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<Metadata />} />
    <Route path="new" element={<MetadataUpdate />} />
    <Route path=":id">
      <Route index element={<MetadataDetail />} />
      <Route path="edit" element={<MetadataUpdate />} />
      <Route path="delete" element={<MetadataDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default MetadataRoutes;
