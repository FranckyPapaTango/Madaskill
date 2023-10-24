import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, getSortState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ASC, DESC, ITEMS_PER_PAGE, SORT } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IProduct } from 'app/shared/model/product.model';
import { getEntities } from './product.reducer';

export const Product = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getSortState(location, ITEMS_PER_PAGE, 'id'), location.search)
  );

  const productList = useAppSelector(state => state.product.entities);
  const loading = useAppSelector(state => state.product.loading);
  const totalItems = useAppSelector(state => state.product.totalItems);

  const getAllEntities = () => {
    dispatch(
      getEntities({
        page: paginationState.activePage - 1,
        size: paginationState.itemsPerPage,
        sort: `${paginationState.sort},${paginationState.order}`,
      })
    );
  };

  const sortEntities = () => {
    getAllEntities();
    const endURL = `?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`;
    if (location.search !== endURL) {
      navigate(`${location.pathname}${endURL}`);
    }
  };

  useEffect(() => {
    sortEntities();
  }, [paginationState.activePage, paginationState.order, paginationState.sort]);

  useEffect(() => {
    const params = new URLSearchParams(location.search);
    const page = params.get('page');
    const sort = params.get(SORT);
    if (page && sort) {
      const sortSplit = sort.split(',');
      setPaginationState({
        ...paginationState,
        activePage: +page,
        sort: sortSplit[0],
        order: sortSplit[1],
      });
    }
  }, [location.search]);

  const sort = p => () => {
    setPaginationState({
      ...paginationState,
      order: paginationState.order === ASC ? DESC : ASC,
      sort: p,
    });
  };

  const handlePagination = currentPage =>
    setPaginationState({
      ...paginationState,
      activePage: currentPage,
    });

  const handleSyncList = () => {
    sortEntities();
  };

  return (
    <div>
      <h2 id="product-heading" data-cy="ProductHeading">
        <Translate contentKey="madaskillApp.product.home.title">Products</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="madaskillApp.product.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/product/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="madaskillApp.product.home.createLabel">Create new Product</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {productList && productList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="madaskillApp.product.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('object')}>
                  <Translate contentKey="madaskillApp.product.object">Object</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('active')}>
                  <Translate contentKey="madaskillApp.product.active">Active</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('created')}>
                  <Translate contentKey="madaskillApp.product.created">Created</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('defaultPrice')}>
                  <Translate contentKey="madaskillApp.product.defaultPrice">Default Price</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('description')}>
                  <Translate contentKey="madaskillApp.product.description">Description</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('livemode')}>
                  <Translate contentKey="madaskillApp.product.livemode">Livemode</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('name')}>
                  <Translate contentKey="madaskillApp.product.name">Name</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('shippable')}>
                  <Translate contentKey="madaskillApp.product.shippable">Shippable</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('statementDescriptor')}>
                  <Translate contentKey="madaskillApp.product.statementDescriptor">Statement Descriptor</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('taxCode')}>
                  <Translate contentKey="madaskillApp.product.taxCode">Tax Code</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('unitLabel')}>
                  <Translate contentKey="madaskillApp.product.unitLabel">Unit Label</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('updated')}>
                  <Translate contentKey="madaskillApp.product.updated">Updated</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('url')}>
                  <Translate contentKey="madaskillApp.product.url">Url</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('sku')}>
                  <Translate contentKey="madaskillApp.product.sku">Sku</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('title')}>
                  <Translate contentKey="madaskillApp.product.title">Title</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('linkToGenericPhotoFile')}>
                  <Translate contentKey="madaskillApp.product.linkToGenericPhotoFile">Link To Generic Photo File</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('availableSizes')}>
                  <Translate contentKey="madaskillApp.product.availableSizes">Available Sizes</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('currencyFormat')}>
                  <Translate contentKey="madaskillApp.product.currencyFormat">Currency Format</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('isFreeShipping')}>
                  <Translate contentKey="madaskillApp.product.isFreeShipping">Is Free Shipping</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('price')}>
                  <Translate contentKey="madaskillApp.product.price">Price</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('style')}>
                  <Translate contentKey="madaskillApp.product.style">Style</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('installments')}>
                  <Translate contentKey="madaskillApp.product.installments">Installments</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="madaskillApp.product.user">User</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {productList.map((product, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/product/${product.id}`} color="link" size="sm">
                      {product.id}
                    </Button>
                  </td>
                  <td>{product.object}</td>
                  <td>{product.active ? 'true' : 'false'}</td>
                  <td>{product.created}</td>
                  <td>{product.defaultPrice}</td>
                  <td>{product.description}</td>
                  <td>{product.livemode ? 'true' : 'false'}</td>
                  <td>{product.name}</td>
                  <td>{product.shippable ? 'true' : 'false'}</td>
                  <td>{product.statementDescriptor}</td>
                  <td>{product.taxCode}</td>
                  <td>{product.unitLabel}</td>
                  <td>{product.updated}</td>
                  <td>{product.url}</td>
                  <td>{product.sku}</td>
                  <td>{product.title}</td>
                  <td>{product.linkToGenericPhotoFile}</td>
                  <td>{product.availableSizes}</td>
                  <td>{product.currencyFormat}</td>
                  <td>{product.isFreeShipping ? 'true' : 'false'}</td>
                  <td>{product.price}</td>
                  <td>{product.style}</td>
                  <td>{product.installments}</td>
                  <td>{product.user ? <Link to={`/application-user/${product.user.id}`}>{product.user.id}</Link> : ''}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/product/${product.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/product/${product.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                      >
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/product/${product.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="madaskillApp.product.home.notFound">No Products found</Translate>
            </div>
          )
        )}
      </div>
      {totalItems ? (
        <div className={productList && productList.length > 0 ? '' : 'd-none'}>
          <div className="justify-content-center d-flex">
            <JhiItemCount page={paginationState.activePage} total={totalItems} itemsPerPage={paginationState.itemsPerPage} i18nEnabled />
          </div>
          <div className="justify-content-center d-flex">
            <JhiPagination
              activePage={paginationState.activePage}
              onSelect={handlePagination}
              maxButtons={5}
              itemsPerPage={paginationState.itemsPerPage}
              totalItems={totalItems}
            />
          </div>
        </div>
      ) : (
        ''
      )}
    </div>
  );
};

export default Product;
