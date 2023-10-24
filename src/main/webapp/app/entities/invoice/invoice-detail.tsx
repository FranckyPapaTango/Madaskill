import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './invoice.reducer';

export const InvoiceDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const invoiceEntity = useAppSelector(state => state.invoice.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="invoiceDetailsHeading">
          <Translate contentKey="madaskillApp.invoice.detail.title">Invoice</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="madaskillApp.invoice.id">Id</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.id}</dd>
          <dt>
            <span id="object">
              <Translate contentKey="madaskillApp.invoice.object">Object</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.object}</dd>
          <dt>
            <span id="accountCountry">
              <Translate contentKey="madaskillApp.invoice.accountCountry">Account Country</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.accountCountry}</dd>
          <dt>
            <span id="accountName">
              <Translate contentKey="madaskillApp.invoice.accountName">Account Name</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.accountName}</dd>
          <dt>
            <span id="accountTaxIds">
              <Translate contentKey="madaskillApp.invoice.accountTaxIds">Account Tax Ids</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.accountTaxIds}</dd>
          <dt>
            <span id="amountDue">
              <Translate contentKey="madaskillApp.invoice.amountDue">Amount Due</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.amountDue}</dd>
          <dt>
            <span id="amountPaid">
              <Translate contentKey="madaskillApp.invoice.amountPaid">Amount Paid</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.amountPaid}</dd>
          <dt>
            <span id="amountRemaining">
              <Translate contentKey="madaskillApp.invoice.amountRemaining">Amount Remaining</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.amountRemaining}</dd>
          <dt>
            <span id="amountShipping">
              <Translate contentKey="madaskillApp.invoice.amountShipping">Amount Shipping</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.amountShipping}</dd>
          <dt>
            <span id="application">
              <Translate contentKey="madaskillApp.invoice.application">Application</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.application}</dd>
          <dt>
            <span id="applicationFeeAmount">
              <Translate contentKey="madaskillApp.invoice.applicationFeeAmount">Application Fee Amount</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.applicationFeeAmount}</dd>
          <dt>
            <span id="attemptCount">
              <Translate contentKey="madaskillApp.invoice.attemptCount">Attempt Count</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.attemptCount}</dd>
          <dt>
            <span id="attempted">
              <Translate contentKey="madaskillApp.invoice.attempted">Attempted</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.attempted ? 'true' : 'false'}</dd>
          <dt>
            <span id="autoAdvance">
              <Translate contentKey="madaskillApp.invoice.autoAdvance">Auto Advance</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.autoAdvance ? 'true' : 'false'}</dd>
          <dt>
            <span id="billingReason">
              <Translate contentKey="madaskillApp.invoice.billingReason">Billing Reason</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.billingReason}</dd>
          <dt>
            <span id="charge">
              <Translate contentKey="madaskillApp.invoice.charge">Charge</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.charge}</dd>
          <dt>
            <span id="collectionMethod">
              <Translate contentKey="madaskillApp.invoice.collectionMethod">Collection Method</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.collectionMethod}</dd>
          <dt>
            <span id="created">
              <Translate contentKey="madaskillApp.invoice.created">Created</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.created}</dd>
          <dt>
            <span id="currency">
              <Translate contentKey="madaskillApp.invoice.currency">Currency</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.currency}</dd>
          <dt>
            <span id="customFields">
              <Translate contentKey="madaskillApp.invoice.customFields">Custom Fields</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.customFields}</dd>
          <dt>
            <span id="customerStringValue">
              <Translate contentKey="madaskillApp.invoice.customerStringValue">Customer String Value</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.customerStringValue}</dd>
          <dt>
            <span id="customerAddress">
              <Translate contentKey="madaskillApp.invoice.customerAddress">Customer Address</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.customerAddress}</dd>
          <dt>
            <span id="customerEmail">
              <Translate contentKey="madaskillApp.invoice.customerEmail">Customer Email</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.customerEmail}</dd>
          <dt>
            <span id="customerName">
              <Translate contentKey="madaskillApp.invoice.customerName">Customer Name</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.customerName}</dd>
          <dt>
            <span id="customerPhone">
              <Translate contentKey="madaskillApp.invoice.customerPhone">Customer Phone</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.customerPhone}</dd>
          <dt>
            <span id="customerShipping">
              <Translate contentKey="madaskillApp.invoice.customerShipping">Customer Shipping</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.customerShipping}</dd>
          <dt>
            <span id="customerTaxExempt">
              <Translate contentKey="madaskillApp.invoice.customerTaxExempt">Customer Tax Exempt</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.customerTaxExempt}</dd>
          <dt>
            <span id="customerTaxIds">
              <Translate contentKey="madaskillApp.invoice.customerTaxIds">Customer Tax Ids</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.customerTaxIds}</dd>
          <dt>
            <span id="defaultPaymentMethod">
              <Translate contentKey="madaskillApp.invoice.defaultPaymentMethod">Default Payment Method</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.defaultPaymentMethod}</dd>
          <dt>
            <span id="defaultSource">
              <Translate contentKey="madaskillApp.invoice.defaultSource">Default Source</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.defaultSource}</dd>
          <dt>
            <span id="defaultTaxRates">
              <Translate contentKey="madaskillApp.invoice.defaultTaxRates">Default Tax Rates</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.defaultTaxRates}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="madaskillApp.invoice.description">Description</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.description}</dd>
          <dt>
            <span id="discount">
              <Translate contentKey="madaskillApp.invoice.discount">Discount</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.discount}</dd>
          <dt>
            <span id="discounts">
              <Translate contentKey="madaskillApp.invoice.discounts">Discounts</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.discounts}</dd>
          <dt>
            <span id="dueDate">
              <Translate contentKey="madaskillApp.invoice.dueDate">Due Date</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.dueDate}</dd>
          <dt>
            <span id="effectiveAt">
              <Translate contentKey="madaskillApp.invoice.effectiveAt">Effective At</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.effectiveAt}</dd>
          <dt>
            <span id="endingBalance">
              <Translate contentKey="madaskillApp.invoice.endingBalance">Ending Balance</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.endingBalance}</dd>
          <dt>
            <span id="footer">
              <Translate contentKey="madaskillApp.invoice.footer">Footer</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.footer}</dd>
          <dt>
            <span id="fromInvoice">
              <Translate contentKey="madaskillApp.invoice.fromInvoice">From Invoice</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.fromInvoice}</dd>
          <dt>
            <span id="hostedInvoiceUrl">
              <Translate contentKey="madaskillApp.invoice.hostedInvoiceUrl">Hosted Invoice Url</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.hostedInvoiceUrl}</dd>
          <dt>
            <span id="invoicePdf">
              <Translate contentKey="madaskillApp.invoice.invoicePdf">Invoice Pdf</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.invoicePdf}</dd>
          <dt>
            <span id="lastFinalizationError">
              <Translate contentKey="madaskillApp.invoice.lastFinalizationError">Last Finalization Error</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.lastFinalizationError}</dd>
          <dt>
            <span id="latestRevision">
              <Translate contentKey="madaskillApp.invoice.latestRevision">Latest Revision</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.latestRevision}</dd>
          <dt>
            <span id="livemode">
              <Translate contentKey="madaskillApp.invoice.livemode">Livemode</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.livemode ? 'true' : 'false'}</dd>
          <dt>
            <span id="metadata">
              <Translate contentKey="madaskillApp.invoice.metadata">Metadata</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.metadata}</dd>
          <dt>
            <span id="nextPaymentAttempt">
              <Translate contentKey="madaskillApp.invoice.nextPaymentAttempt">Next Payment Attempt</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.nextPaymentAttempt}</dd>
          <dt>
            <span id="number">
              <Translate contentKey="madaskillApp.invoice.number">Number</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.number}</dd>
          <dt>
            <span id="onBehalfOf">
              <Translate contentKey="madaskillApp.invoice.onBehalfOf">On Behalf Of</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.onBehalfOf}</dd>
          <dt>
            <span id="paid">
              <Translate contentKey="madaskillApp.invoice.paid">Paid</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.paid ? 'true' : 'false'}</dd>
          <dt>
            <span id="paidOutOfBand">
              <Translate contentKey="madaskillApp.invoice.paidOutOfBand">Paid Out Of Band</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.paidOutOfBand ? 'true' : 'false'}</dd>
          <dt>
            <span id="paymentIntent">
              <Translate contentKey="madaskillApp.invoice.paymentIntent">Payment Intent</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.paymentIntent}</dd>
          <dt>
            <span id="paymentSettings">
              <Translate contentKey="madaskillApp.invoice.paymentSettings">Payment Settings</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.paymentSettings}</dd>
          <dt>
            <span id="periodEnd">
              <Translate contentKey="madaskillApp.invoice.periodEnd">Period End</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.periodEnd}</dd>
          <dt>
            <span id="periodStart">
              <Translate contentKey="madaskillApp.invoice.periodStart">Period Start</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.periodStart}</dd>
          <dt>
            <span id="postPaymentCreditNotesAmount">
              <Translate contentKey="madaskillApp.invoice.postPaymentCreditNotesAmount">Post Payment Credit Notes Amount</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.postPaymentCreditNotesAmount}</dd>
          <dt>
            <span id="prePaymentCreditNotesAmount">
              <Translate contentKey="madaskillApp.invoice.prePaymentCreditNotesAmount">Pre Payment Credit Notes Amount</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.prePaymentCreditNotesAmount}</dd>
          <dt>
            <span id="quote">
              <Translate contentKey="madaskillApp.invoice.quote">Quote</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.quote}</dd>
          <dt>
            <span id="receiptNumber">
              <Translate contentKey="madaskillApp.invoice.receiptNumber">Receipt Number</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.receiptNumber}</dd>
          <dt>
            <span id="rendering">
              <Translate contentKey="madaskillApp.invoice.rendering">Rendering</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.rendering}</dd>
          <dt>
            <span id="renderingOptions">
              <Translate contentKey="madaskillApp.invoice.renderingOptions">Rendering Options</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.renderingOptions}</dd>
          <dt>
            <span id="shippingCost">
              <Translate contentKey="madaskillApp.invoice.shippingCost">Shipping Cost</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.shippingCost}</dd>
          <dt>
            <span id="shippingDetails">
              <Translate contentKey="madaskillApp.invoice.shippingDetails">Shipping Details</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.shippingDetails}</dd>
          <dt>
            <span id="startingBalance">
              <Translate contentKey="madaskillApp.invoice.startingBalance">Starting Balance</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.startingBalance}</dd>
          <dt>
            <span id="statementDescriptor">
              <Translate contentKey="madaskillApp.invoice.statementDescriptor">Statement Descriptor</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.statementDescriptor}</dd>
          <dt>
            <span id="status">
              <Translate contentKey="madaskillApp.invoice.status">Status</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.status}</dd>
          <dt>
            <span id="statusTransitions">
              <Translate contentKey="madaskillApp.invoice.statusTransitions">Status Transitions</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.statusTransitions}</dd>
          <dt>
            <span id="subscription">
              <Translate contentKey="madaskillApp.invoice.subscription">Subscription</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.subscription}</dd>
          <dt>
            <span id="subscriptionDetails">
              <Translate contentKey="madaskillApp.invoice.subscriptionDetails">Subscription Details</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.subscriptionDetails}</dd>
          <dt>
            <span id="subtotal">
              <Translate contentKey="madaskillApp.invoice.subtotal">Subtotal</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.subtotal}</dd>
          <dt>
            <span id="subtotalExcludingTax">
              <Translate contentKey="madaskillApp.invoice.subtotalExcludingTax">Subtotal Excluding Tax</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.subtotalExcludingTax}</dd>
          <dt>
            <span id="tax">
              <Translate contentKey="madaskillApp.invoice.tax">Tax</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.tax}</dd>
          <dt>
            <span id="testClock">
              <Translate contentKey="madaskillApp.invoice.testClock">Test Clock</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.testClock}</dd>
          <dt>
            <span id="total">
              <Translate contentKey="madaskillApp.invoice.total">Total</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.total}</dd>
          <dt>
            <span id="totalDiscountAmounts">
              <Translate contentKey="madaskillApp.invoice.totalDiscountAmounts">Total Discount Amounts</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.totalDiscountAmounts}</dd>
          <dt>
            <span id="totalExcludingTax">
              <Translate contentKey="madaskillApp.invoice.totalExcludingTax">Total Excluding Tax</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.totalExcludingTax}</dd>
          <dt>
            <span id="totalTaxAmounts">
              <Translate contentKey="madaskillApp.invoice.totalTaxAmounts">Total Tax Amounts</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.totalTaxAmounts}</dd>
          <dt>
            <span id="transferData">
              <Translate contentKey="madaskillApp.invoice.transferData">Transfer Data</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.transferData}</dd>
          <dt>
            <span id="webhooksDeliveredAt">
              <Translate contentKey="madaskillApp.invoice.webhooksDeliveredAt">Webhooks Delivered At</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.webhooksDeliveredAt}</dd>
          <dt>
            <Translate contentKey="madaskillApp.invoice.commande">Commande</Translate>
          </dt>
          <dd>{invoiceEntity.commande ? invoiceEntity.commande.id : ''}</dd>
          <dt>
            <Translate contentKey="madaskillApp.invoice.customer">Customer</Translate>
          </dt>
          <dd>{invoiceEntity.customer ? invoiceEntity.customer.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/invoice" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/invoice/${invoiceEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default InvoiceDetail;
