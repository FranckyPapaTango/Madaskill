import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, getSortState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ASC, DESC, ITEMS_PER_PAGE, SORT } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IInvoice } from 'app/shared/model/invoice.model';
import { getEntities } from './invoice.reducer';

export const Invoice = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getSortState(location, ITEMS_PER_PAGE, 'id'), location.search)
  );

  const invoiceList = useAppSelector(state => state.invoice.entities);
  const loading = useAppSelector(state => state.invoice.loading);
  const totalItems = useAppSelector(state => state.invoice.totalItems);

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
      <h2 id="invoice-heading" data-cy="InvoiceHeading">
        <Translate contentKey="madaskillApp.invoice.home.title">Invoices</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="madaskillApp.invoice.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/invoice/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="madaskillApp.invoice.home.createLabel">Create new Invoice</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {invoiceList && invoiceList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="madaskillApp.invoice.id">Id</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('object')}>
                  <Translate contentKey="madaskillApp.invoice.object">Object</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('accountCountry')}>
                  <Translate contentKey="madaskillApp.invoice.accountCountry">Account Country</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('accountName')}>
                  <Translate contentKey="madaskillApp.invoice.accountName">Account Name</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('accountTaxIds')}>
                  <Translate contentKey="madaskillApp.invoice.accountTaxIds">Account Tax Ids</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('amountDue')}>
                  <Translate contentKey="madaskillApp.invoice.amountDue">Amount Due</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('amountPaid')}>
                  <Translate contentKey="madaskillApp.invoice.amountPaid">Amount Paid</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('amountRemaining')}>
                  <Translate contentKey="madaskillApp.invoice.amountRemaining">Amount Remaining</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('amountShipping')}>
                  <Translate contentKey="madaskillApp.invoice.amountShipping">Amount Shipping</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('application')}>
                  <Translate contentKey="madaskillApp.invoice.application">Application</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('applicationFeeAmount')}>
                  <Translate contentKey="madaskillApp.invoice.applicationFeeAmount">Application Fee Amount</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('attemptCount')}>
                  <Translate contentKey="madaskillApp.invoice.attemptCount">Attempt Count</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('attempted')}>
                  <Translate contentKey="madaskillApp.invoice.attempted">Attempted</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('autoAdvance')}>
                  <Translate contentKey="madaskillApp.invoice.autoAdvance">Auto Advance</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('billingReason')}>
                  <Translate contentKey="madaskillApp.invoice.billingReason">Billing Reason</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('charge')}>
                  <Translate contentKey="madaskillApp.invoice.charge">Charge</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('collectionMethod')}>
                  <Translate contentKey="madaskillApp.invoice.collectionMethod">Collection Method</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('created')}>
                  <Translate contentKey="madaskillApp.invoice.created">Created</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('currency')}>
                  <Translate contentKey="madaskillApp.invoice.currency">Currency</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('customFields')}>
                  <Translate contentKey="madaskillApp.invoice.customFields">Custom Fields</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('customerStringValue')}>
                  <Translate contentKey="madaskillApp.invoice.customerStringValue">Customer String Value</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('customerAddress')}>
                  <Translate contentKey="madaskillApp.invoice.customerAddress">Customer Address</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('customerEmail')}>
                  <Translate contentKey="madaskillApp.invoice.customerEmail">Customer Email</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('customerName')}>
                  <Translate contentKey="madaskillApp.invoice.customerName">Customer Name</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('customerPhone')}>
                  <Translate contentKey="madaskillApp.invoice.customerPhone">Customer Phone</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('customerShipping')}>
                  <Translate contentKey="madaskillApp.invoice.customerShipping">Customer Shipping</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('customerTaxExempt')}>
                  <Translate contentKey="madaskillApp.invoice.customerTaxExempt">Customer Tax Exempt</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('customerTaxIds')}>
                  <Translate contentKey="madaskillApp.invoice.customerTaxIds">Customer Tax Ids</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('defaultPaymentMethod')}>
                  <Translate contentKey="madaskillApp.invoice.defaultPaymentMethod">Default Payment Method</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('defaultSource')}>
                  <Translate contentKey="madaskillApp.invoice.defaultSource">Default Source</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('defaultTaxRates')}>
                  <Translate contentKey="madaskillApp.invoice.defaultTaxRates">Default Tax Rates</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('description')}>
                  <Translate contentKey="madaskillApp.invoice.description">Description</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('discount')}>
                  <Translate contentKey="madaskillApp.invoice.discount">Discount</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('discounts')}>
                  <Translate contentKey="madaskillApp.invoice.discounts">Discounts</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('dueDate')}>
                  <Translate contentKey="madaskillApp.invoice.dueDate">Due Date</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('effectiveAt')}>
                  <Translate contentKey="madaskillApp.invoice.effectiveAt">Effective At</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('endingBalance')}>
                  <Translate contentKey="madaskillApp.invoice.endingBalance">Ending Balance</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('footer')}>
                  <Translate contentKey="madaskillApp.invoice.footer">Footer</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('fromInvoice')}>
                  <Translate contentKey="madaskillApp.invoice.fromInvoice">From Invoice</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('hostedInvoiceUrl')}>
                  <Translate contentKey="madaskillApp.invoice.hostedInvoiceUrl">Hosted Invoice Url</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('invoicePdf')}>
                  <Translate contentKey="madaskillApp.invoice.invoicePdf">Invoice Pdf</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('lastFinalizationError')}>
                  <Translate contentKey="madaskillApp.invoice.lastFinalizationError">Last Finalization Error</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('latestRevision')}>
                  <Translate contentKey="madaskillApp.invoice.latestRevision">Latest Revision</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('livemode')}>
                  <Translate contentKey="madaskillApp.invoice.livemode">Livemode</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('metadata')}>
                  <Translate contentKey="madaskillApp.invoice.metadata">Metadata</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('nextPaymentAttempt')}>
                  <Translate contentKey="madaskillApp.invoice.nextPaymentAttempt">Next Payment Attempt</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('number')}>
                  <Translate contentKey="madaskillApp.invoice.number">Number</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('onBehalfOf')}>
                  <Translate contentKey="madaskillApp.invoice.onBehalfOf">On Behalf Of</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('paid')}>
                  <Translate contentKey="madaskillApp.invoice.paid">Paid</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('paidOutOfBand')}>
                  <Translate contentKey="madaskillApp.invoice.paidOutOfBand">Paid Out Of Band</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('paymentIntent')}>
                  <Translate contentKey="madaskillApp.invoice.paymentIntent">Payment Intent</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('paymentSettings')}>
                  <Translate contentKey="madaskillApp.invoice.paymentSettings">Payment Settings</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('periodEnd')}>
                  <Translate contentKey="madaskillApp.invoice.periodEnd">Period End</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('periodStart')}>
                  <Translate contentKey="madaskillApp.invoice.periodStart">Period Start</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('postPaymentCreditNotesAmount')}>
                  <Translate contentKey="madaskillApp.invoice.postPaymentCreditNotesAmount">Post Payment Credit Notes Amount</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('prePaymentCreditNotesAmount')}>
                  <Translate contentKey="madaskillApp.invoice.prePaymentCreditNotesAmount">Pre Payment Credit Notes Amount</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('quote')}>
                  <Translate contentKey="madaskillApp.invoice.quote">Quote</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('receiptNumber')}>
                  <Translate contentKey="madaskillApp.invoice.receiptNumber">Receipt Number</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('rendering')}>
                  <Translate contentKey="madaskillApp.invoice.rendering">Rendering</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('renderingOptions')}>
                  <Translate contentKey="madaskillApp.invoice.renderingOptions">Rendering Options</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('shippingCost')}>
                  <Translate contentKey="madaskillApp.invoice.shippingCost">Shipping Cost</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('shippingDetails')}>
                  <Translate contentKey="madaskillApp.invoice.shippingDetails">Shipping Details</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('startingBalance')}>
                  <Translate contentKey="madaskillApp.invoice.startingBalance">Starting Balance</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('statementDescriptor')}>
                  <Translate contentKey="madaskillApp.invoice.statementDescriptor">Statement Descriptor</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('status')}>
                  <Translate contentKey="madaskillApp.invoice.status">Status</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('statusTransitions')}>
                  <Translate contentKey="madaskillApp.invoice.statusTransitions">Status Transitions</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('subscription')}>
                  <Translate contentKey="madaskillApp.invoice.subscription">Subscription</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('subscriptionDetails')}>
                  <Translate contentKey="madaskillApp.invoice.subscriptionDetails">Subscription Details</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('subtotal')}>
                  <Translate contentKey="madaskillApp.invoice.subtotal">Subtotal</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('subtotalExcludingTax')}>
                  <Translate contentKey="madaskillApp.invoice.subtotalExcludingTax">Subtotal Excluding Tax</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('tax')}>
                  <Translate contentKey="madaskillApp.invoice.tax">Tax</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('testClock')}>
                  <Translate contentKey="madaskillApp.invoice.testClock">Test Clock</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('total')}>
                  <Translate contentKey="madaskillApp.invoice.total">Total</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('totalDiscountAmounts')}>
                  <Translate contentKey="madaskillApp.invoice.totalDiscountAmounts">Total Discount Amounts</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('totalExcludingTax')}>
                  <Translate contentKey="madaskillApp.invoice.totalExcludingTax">Total Excluding Tax</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('totalTaxAmounts')}>
                  <Translate contentKey="madaskillApp.invoice.totalTaxAmounts">Total Tax Amounts</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('transferData')}>
                  <Translate contentKey="madaskillApp.invoice.transferData">Transfer Data</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('webhooksDeliveredAt')}>
                  <Translate contentKey="madaskillApp.invoice.webhooksDeliveredAt">Webhooks Delivered At</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="madaskillApp.invoice.commande">Commande</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="madaskillApp.invoice.customer">Customer</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {invoiceList.map((invoice, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/invoice/${invoice.id}`} color="link" size="sm">
                      {invoice.id}
                    </Button>
                  </td>
                  <td>{invoice.object}</td>
                  <td>{invoice.accountCountry}</td>
                  <td>{invoice.accountName}</td>
                  <td>{invoice.accountTaxIds}</td>
                  <td>{invoice.amountDue}</td>
                  <td>{invoice.amountPaid}</td>
                  <td>{invoice.amountRemaining}</td>
                  <td>{invoice.amountShipping}</td>
                  <td>{invoice.application}</td>
                  <td>{invoice.applicationFeeAmount}</td>
                  <td>{invoice.attemptCount}</td>
                  <td>{invoice.attempted ? 'true' : 'false'}</td>
                  <td>{invoice.autoAdvance ? 'true' : 'false'}</td>
                  <td>{invoice.billingReason}</td>
                  <td>{invoice.charge}</td>
                  <td>{invoice.collectionMethod}</td>
                  <td>{invoice.created}</td>
                  <td>{invoice.currency}</td>
                  <td>{invoice.customFields}</td>
                  <td>{invoice.customerStringValue}</td>
                  <td>{invoice.customerAddress}</td>
                  <td>{invoice.customerEmail}</td>
                  <td>{invoice.customerName}</td>
                  <td>{invoice.customerPhone}</td>
                  <td>{invoice.customerShipping}</td>
                  <td>{invoice.customerTaxExempt}</td>
                  <td>{invoice.customerTaxIds}</td>
                  <td>{invoice.defaultPaymentMethod}</td>
                  <td>{invoice.defaultSource}</td>
                  <td>{invoice.defaultTaxRates}</td>
                  <td>{invoice.description}</td>
                  <td>{invoice.discount}</td>
                  <td>{invoice.discounts}</td>
                  <td>{invoice.dueDate}</td>
                  <td>{invoice.effectiveAt}</td>
                  <td>{invoice.endingBalance}</td>
                  <td>{invoice.footer}</td>
                  <td>{invoice.fromInvoice}</td>
                  <td>{invoice.hostedInvoiceUrl}</td>
                  <td>{invoice.invoicePdf}</td>
                  <td>{invoice.lastFinalizationError}</td>
                  <td>{invoice.latestRevision}</td>
                  <td>{invoice.livemode ? 'true' : 'false'}</td>
                  <td>{invoice.metadata}</td>
                  <td>{invoice.nextPaymentAttempt}</td>
                  <td>{invoice.number}</td>
                  <td>{invoice.onBehalfOf}</td>
                  <td>{invoice.paid ? 'true' : 'false'}</td>
                  <td>{invoice.paidOutOfBand ? 'true' : 'false'}</td>
                  <td>{invoice.paymentIntent}</td>
                  <td>{invoice.paymentSettings}</td>
                  <td>{invoice.periodEnd}</td>
                  <td>{invoice.periodStart}</td>
                  <td>{invoice.postPaymentCreditNotesAmount}</td>
                  <td>{invoice.prePaymentCreditNotesAmount}</td>
                  <td>{invoice.quote}</td>
                  <td>{invoice.receiptNumber}</td>
                  <td>{invoice.rendering}</td>
                  <td>{invoice.renderingOptions}</td>
                  <td>{invoice.shippingCost}</td>
                  <td>{invoice.shippingDetails}</td>
                  <td>{invoice.startingBalance}</td>
                  <td>{invoice.statementDescriptor}</td>
                  <td>{invoice.status}</td>
                  <td>{invoice.statusTransitions}</td>
                  <td>{invoice.subscription}</td>
                  <td>{invoice.subscriptionDetails}</td>
                  <td>{invoice.subtotal}</td>
                  <td>{invoice.subtotalExcludingTax}</td>
                  <td>{invoice.tax}</td>
                  <td>{invoice.testClock}</td>
                  <td>{invoice.total}</td>
                  <td>{invoice.totalDiscountAmounts}</td>
                  <td>{invoice.totalExcludingTax}</td>
                  <td>{invoice.totalTaxAmounts}</td>
                  <td>{invoice.transferData}</td>
                  <td>{invoice.webhooksDeliveredAt}</td>
                  <td>{invoice.commande ? <Link to={`/commande/${invoice.commande.id}`}>{invoice.commande.id}</Link> : ''}</td>
                  <td>{invoice.customer ? <Link to={`/customer/${invoice.customer.id}`}>{invoice.customer.id}</Link> : ''}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/invoice/${invoice.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/invoice/${invoice.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
                        to={`/invoice/${invoice.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
              <Translate contentKey="madaskillApp.invoice.home.notFound">No Invoices found</Translate>
            </div>
          )
        )}
      </div>
      {totalItems ? (
        <div className={invoiceList && invoiceList.length > 0 ? '' : 'd-none'}>
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

export default Invoice;
