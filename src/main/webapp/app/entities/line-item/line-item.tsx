import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, getSortState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ASC, DESC, ITEMS_PER_PAGE, SORT } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { ILineItem } from 'app/shared/model/line-item.model';
import { getEntities } from './line-item.reducer';

export const LineItem = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getSortState(location, ITEMS_PER_PAGE, 'id'), location.search)
  );

  const lineItemList = useAppSelector(state => state.lineItem.entities);
  const loading = useAppSelector(state => state.lineItem.loading);
  const totalItems = useAppSelector(state => state.lineItem.totalItems);

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
      <h2 id="line-item-heading" data-cy="LineItemHeading">
        <Translate contentKey="madaskillApp.lineItem.home.title">Line Items</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="madaskillApp.lineItem.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/line-item/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="madaskillApp.lineItem.home.createLabel">Create new Line Item</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {lineItemList && lineItemList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="madaskillApp.lineItem.id">Id</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('object')}>
                  <Translate contentKey="madaskillApp.lineItem.object">Object</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('amount')}>
                  <Translate contentKey="madaskillApp.lineItem.amount">Amount</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('amountExcludingTax')}>
                  <Translate contentKey="madaskillApp.lineItem.amountExcludingTax">Amount Excluding Tax</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('currency')}>
                  <Translate contentKey="madaskillApp.lineItem.currency">Currency</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('description')}>
                  <Translate contentKey="madaskillApp.lineItem.description">Description</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('discountAmounts')}>
                  <Translate contentKey="madaskillApp.lineItem.discountAmounts">Discount Amounts</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('discountable')}>
                  <Translate contentKey="madaskillApp.lineItem.discountable">Discountable</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('discounts')}>
                  <Translate contentKey="madaskillApp.lineItem.discounts">Discounts</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('invoiceItem')}>
                  <Translate contentKey="madaskillApp.lineItem.invoiceItem">Invoice Item</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('livemode')}>
                  <Translate contentKey="madaskillApp.lineItem.livemode">Livemode</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('metadata')}>
                  <Translate contentKey="madaskillApp.lineItem.metadata">Metadata</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('periodEnd')}>
                  <Translate contentKey="madaskillApp.lineItem.periodEnd">Period End</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('periodStart')}>
                  <Translate contentKey="madaskillApp.lineItem.periodStart">Period Start</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('price')}>
                  <Translate contentKey="madaskillApp.lineItem.price">Price</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('proration')}>
                  <Translate contentKey="madaskillApp.lineItem.proration">Proration</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('prorationDetails')}>
                  <Translate contentKey="madaskillApp.lineItem.prorationDetails">Proration Details</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('quantity')}>
                  <Translate contentKey="madaskillApp.lineItem.quantity">Quantity</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('subscription')}>
                  <Translate contentKey="madaskillApp.lineItem.subscription">Subscription</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('taxAmounts')}>
                  <Translate contentKey="madaskillApp.lineItem.taxAmounts">Tax Amounts</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('taxRates')}>
                  <Translate contentKey="madaskillApp.lineItem.taxRates">Tax Rates</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('type')}>
                  <Translate contentKey="madaskillApp.lineItem.type">Type</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('unitAmountExcludingTax')}>
                  <Translate contentKey="madaskillApp.lineItem.unitAmountExcludingTax">Unit Amount Excluding Tax</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="madaskillApp.lineItem.product">Product</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="madaskillApp.lineItem.commande">Commande</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {lineItemList.map((lineItem, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/line-item/${lineItem.id}`} color="link" size="sm">
                      {lineItem.id}
                    </Button>
                  </td>
                  <td>{lineItem.object}</td>
                  <td>{lineItem.amount}</td>
                  <td>{lineItem.amountExcludingTax}</td>
                  <td>{lineItem.currency}</td>
                  <td>{lineItem.description}</td>
                  <td>{lineItem.discountAmounts}</td>
                  <td>{lineItem.discountable ? 'true' : 'false'}</td>
                  <td>{lineItem.discounts}</td>
                  <td>{lineItem.invoiceItem}</td>
                  <td>{lineItem.livemode ? 'true' : 'false'}</td>
                  <td>{lineItem.metadata}</td>
                  <td>{lineItem.periodEnd}</td>
                  <td>{lineItem.periodStart}</td>
                  <td>{lineItem.price}</td>
                  <td>{lineItem.proration ? 'true' : 'false'}</td>
                  <td>{lineItem.prorationDetails}</td>
                  <td>{lineItem.quantity}</td>
                  <td>{lineItem.subscription}</td>
                  <td>{lineItem.taxAmounts}</td>
                  <td>{lineItem.taxRates}</td>
                  <td>{lineItem.type}</td>
                  <td>{lineItem.unitAmountExcludingTax}</td>
                  <td>{lineItem.product ? <Link to={`/product/${lineItem.product.id}`}>{lineItem.product.id}</Link> : ''}</td>
                  <td>{lineItem.commande ? <Link to={`/commande/${lineItem.commande.id}`}>{lineItem.commande.id}</Link> : ''}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/line-item/${lineItem.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/line-item/${lineItem.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
                        to={`/line-item/${lineItem.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
              <Translate contentKey="madaskillApp.lineItem.home.notFound">No Line Items found</Translate>
            </div>
          )
        )}
      </div>
      {totalItems ? (
        <div className={lineItemList && lineItemList.length > 0 ? '' : 'd-none'}>
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

export default LineItem;
