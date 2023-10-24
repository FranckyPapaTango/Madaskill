import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { ICommande } from 'app/shared/model/commande.model';
import { getEntities as getCommandes } from 'app/entities/commande/commande.reducer';
import { ICustomer } from 'app/shared/model/customer.model';
import { getEntities as getCustomers } from 'app/entities/customer/customer.reducer';
import { IInvoice } from 'app/shared/model/invoice.model';
import { getEntity, updateEntity, createEntity, reset } from './invoice.reducer';

export const InvoiceUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const commandes = useAppSelector(state => state.commande.entities);
  const customers = useAppSelector(state => state.customer.entities);
  const invoiceEntity = useAppSelector(state => state.invoice.entity);
  const loading = useAppSelector(state => state.invoice.loading);
  const updating = useAppSelector(state => state.invoice.updating);
  const updateSuccess = useAppSelector(state => state.invoice.updateSuccess);

  const handleClose = () => {
    navigate('/invoice' + location.search);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getCommandes({}));
    dispatch(getCustomers({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...invoiceEntity,
      ...values,
      commande: commandes.find(it => it.id.toString() === values.commande.toString()),
      customer: customers.find(it => it.id.toString() === values.customer.toString()),
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          ...invoiceEntity,
          commande: invoiceEntity?.commande?.id,
          customer: invoiceEntity?.customer?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="madaskillApp.invoice.home.createOrEditLabel" data-cy="InvoiceCreateUpdateHeading">
            <Translate contentKey="madaskillApp.invoice.home.createOrEditLabel">Create or edit a Invoice</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField
                  name="id"
                  required
                  readOnly
                  id="invoice-id"
                  label={translate('madaskillApp.invoice.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('madaskillApp.invoice.object')}
                id="invoice-object"
                name="object"
                data-cy="object"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.invoice.accountCountry')}
                id="invoice-accountCountry"
                name="accountCountry"
                data-cy="accountCountry"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.invoice.accountName')}
                id="invoice-accountName"
                name="accountName"
                data-cy="accountName"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.invoice.accountTaxIds')}
                id="invoice-accountTaxIds"
                name="accountTaxIds"
                data-cy="accountTaxIds"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.invoice.amountDue')}
                id="invoice-amountDue"
                name="amountDue"
                data-cy="amountDue"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.invoice.amountPaid')}
                id="invoice-amountPaid"
                name="amountPaid"
                data-cy="amountPaid"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.invoice.amountRemaining')}
                id="invoice-amountRemaining"
                name="amountRemaining"
                data-cy="amountRemaining"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.invoice.amountShipping')}
                id="invoice-amountShipping"
                name="amountShipping"
                data-cy="amountShipping"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.invoice.application')}
                id="invoice-application"
                name="application"
                data-cy="application"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.invoice.applicationFeeAmount')}
                id="invoice-applicationFeeAmount"
                name="applicationFeeAmount"
                data-cy="applicationFeeAmount"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.invoice.attemptCount')}
                id="invoice-attemptCount"
                name="attemptCount"
                data-cy="attemptCount"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.invoice.attempted')}
                id="invoice-attempted"
                name="attempted"
                data-cy="attempted"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('madaskillApp.invoice.autoAdvance')}
                id="invoice-autoAdvance"
                name="autoAdvance"
                data-cy="autoAdvance"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('madaskillApp.invoice.billingReason')}
                id="invoice-billingReason"
                name="billingReason"
                data-cy="billingReason"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.invoice.charge')}
                id="invoice-charge"
                name="charge"
                data-cy="charge"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.invoice.collectionMethod')}
                id="invoice-collectionMethod"
                name="collectionMethod"
                data-cy="collectionMethod"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.invoice.created')}
                id="invoice-created"
                name="created"
                data-cy="created"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.invoice.currency')}
                id="invoice-currency"
                name="currency"
                data-cy="currency"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.invoice.customFields')}
                id="invoice-customFields"
                name="customFields"
                data-cy="customFields"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.invoice.customerStringValue')}
                id="invoice-customerStringValue"
                name="customerStringValue"
                data-cy="customerStringValue"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.invoice.customerAddress')}
                id="invoice-customerAddress"
                name="customerAddress"
                data-cy="customerAddress"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.invoice.customerEmail')}
                id="invoice-customerEmail"
                name="customerEmail"
                data-cy="customerEmail"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.invoice.customerName')}
                id="invoice-customerName"
                name="customerName"
                data-cy="customerName"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.invoice.customerPhone')}
                id="invoice-customerPhone"
                name="customerPhone"
                data-cy="customerPhone"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.invoice.customerShipping')}
                id="invoice-customerShipping"
                name="customerShipping"
                data-cy="customerShipping"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.invoice.customerTaxExempt')}
                id="invoice-customerTaxExempt"
                name="customerTaxExempt"
                data-cy="customerTaxExempt"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.invoice.customerTaxIds')}
                id="invoice-customerTaxIds"
                name="customerTaxIds"
                data-cy="customerTaxIds"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.invoice.defaultPaymentMethod')}
                id="invoice-defaultPaymentMethod"
                name="defaultPaymentMethod"
                data-cy="defaultPaymentMethod"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.invoice.defaultSource')}
                id="invoice-defaultSource"
                name="defaultSource"
                data-cy="defaultSource"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.invoice.defaultTaxRates')}
                id="invoice-defaultTaxRates"
                name="defaultTaxRates"
                data-cy="defaultTaxRates"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.invoice.description')}
                id="invoice-description"
                name="description"
                data-cy="description"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.invoice.discount')}
                id="invoice-discount"
                name="discount"
                data-cy="discount"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.invoice.discounts')}
                id="invoice-discounts"
                name="discounts"
                data-cy="discounts"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.invoice.dueDate')}
                id="invoice-dueDate"
                name="dueDate"
                data-cy="dueDate"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.invoice.effectiveAt')}
                id="invoice-effectiveAt"
                name="effectiveAt"
                data-cy="effectiveAt"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.invoice.endingBalance')}
                id="invoice-endingBalance"
                name="endingBalance"
                data-cy="endingBalance"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.invoice.footer')}
                id="invoice-footer"
                name="footer"
                data-cy="footer"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.invoice.fromInvoice')}
                id="invoice-fromInvoice"
                name="fromInvoice"
                data-cy="fromInvoice"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.invoice.hostedInvoiceUrl')}
                id="invoice-hostedInvoiceUrl"
                name="hostedInvoiceUrl"
                data-cy="hostedInvoiceUrl"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.invoice.invoicePdf')}
                id="invoice-invoicePdf"
                name="invoicePdf"
                data-cy="invoicePdf"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.invoice.lastFinalizationError')}
                id="invoice-lastFinalizationError"
                name="lastFinalizationError"
                data-cy="lastFinalizationError"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.invoice.latestRevision')}
                id="invoice-latestRevision"
                name="latestRevision"
                data-cy="latestRevision"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.invoice.livemode')}
                id="invoice-livemode"
                name="livemode"
                data-cy="livemode"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('madaskillApp.invoice.metadata')}
                id="invoice-metadata"
                name="metadata"
                data-cy="metadata"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.invoice.nextPaymentAttempt')}
                id="invoice-nextPaymentAttempt"
                name="nextPaymentAttempt"
                data-cy="nextPaymentAttempt"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.invoice.number')}
                id="invoice-number"
                name="number"
                data-cy="number"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.invoice.onBehalfOf')}
                id="invoice-onBehalfOf"
                name="onBehalfOf"
                data-cy="onBehalfOf"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.invoice.paid')}
                id="invoice-paid"
                name="paid"
                data-cy="paid"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('madaskillApp.invoice.paidOutOfBand')}
                id="invoice-paidOutOfBand"
                name="paidOutOfBand"
                data-cy="paidOutOfBand"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('madaskillApp.invoice.paymentIntent')}
                id="invoice-paymentIntent"
                name="paymentIntent"
                data-cy="paymentIntent"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.invoice.paymentSettings')}
                id="invoice-paymentSettings"
                name="paymentSettings"
                data-cy="paymentSettings"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.invoice.periodEnd')}
                id="invoice-periodEnd"
                name="periodEnd"
                data-cy="periodEnd"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.invoice.periodStart')}
                id="invoice-periodStart"
                name="periodStart"
                data-cy="periodStart"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.invoice.postPaymentCreditNotesAmount')}
                id="invoice-postPaymentCreditNotesAmount"
                name="postPaymentCreditNotesAmount"
                data-cy="postPaymentCreditNotesAmount"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.invoice.prePaymentCreditNotesAmount')}
                id="invoice-prePaymentCreditNotesAmount"
                name="prePaymentCreditNotesAmount"
                data-cy="prePaymentCreditNotesAmount"
                type="text"
              />
              <ValidatedField label={translate('madaskillApp.invoice.quote')} id="invoice-quote" name="quote" data-cy="quote" type="text" />
              <ValidatedField
                label={translate('madaskillApp.invoice.receiptNumber')}
                id="invoice-receiptNumber"
                name="receiptNumber"
                data-cy="receiptNumber"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.invoice.rendering')}
                id="invoice-rendering"
                name="rendering"
                data-cy="rendering"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.invoice.renderingOptions')}
                id="invoice-renderingOptions"
                name="renderingOptions"
                data-cy="renderingOptions"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.invoice.shippingCost')}
                id="invoice-shippingCost"
                name="shippingCost"
                data-cy="shippingCost"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.invoice.shippingDetails')}
                id="invoice-shippingDetails"
                name="shippingDetails"
                data-cy="shippingDetails"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.invoice.startingBalance')}
                id="invoice-startingBalance"
                name="startingBalance"
                data-cy="startingBalance"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.invoice.statementDescriptor')}
                id="invoice-statementDescriptor"
                name="statementDescriptor"
                data-cy="statementDescriptor"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.invoice.status')}
                id="invoice-status"
                name="status"
                data-cy="status"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.invoice.statusTransitions')}
                id="invoice-statusTransitions"
                name="statusTransitions"
                data-cy="statusTransitions"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.invoice.subscription')}
                id="invoice-subscription"
                name="subscription"
                data-cy="subscription"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.invoice.subscriptionDetails')}
                id="invoice-subscriptionDetails"
                name="subscriptionDetails"
                data-cy="subscriptionDetails"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.invoice.subtotal')}
                id="invoice-subtotal"
                name="subtotal"
                data-cy="subtotal"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.invoice.subtotalExcludingTax')}
                id="invoice-subtotalExcludingTax"
                name="subtotalExcludingTax"
                data-cy="subtotalExcludingTax"
                type="text"
              />
              <ValidatedField label={translate('madaskillApp.invoice.tax')} id="invoice-tax" name="tax" data-cy="tax" type="text" />
              <ValidatedField
                label={translate('madaskillApp.invoice.testClock')}
                id="invoice-testClock"
                name="testClock"
                data-cy="testClock"
                type="text"
              />
              <ValidatedField label={translate('madaskillApp.invoice.total')} id="invoice-total" name="total" data-cy="total" type="text" />
              <ValidatedField
                label={translate('madaskillApp.invoice.totalDiscountAmounts')}
                id="invoice-totalDiscountAmounts"
                name="totalDiscountAmounts"
                data-cy="totalDiscountAmounts"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.invoice.totalExcludingTax')}
                id="invoice-totalExcludingTax"
                name="totalExcludingTax"
                data-cy="totalExcludingTax"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.invoice.totalTaxAmounts')}
                id="invoice-totalTaxAmounts"
                name="totalTaxAmounts"
                data-cy="totalTaxAmounts"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.invoice.transferData')}
                id="invoice-transferData"
                name="transferData"
                data-cy="transferData"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.invoice.webhooksDeliveredAt')}
                id="invoice-webhooksDeliveredAt"
                name="webhooksDeliveredAt"
                data-cy="webhooksDeliveredAt"
                type="text"
              />
              <ValidatedField
                id="invoice-commande"
                name="commande"
                data-cy="commande"
                label={translate('madaskillApp.invoice.commande')}
                type="select"
              >
                <option value="" key="0" />
                {commandes
                  ? commandes.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="invoice-customer"
                name="customer"
                data-cy="customer"
                label={translate('madaskillApp.invoice.customer')}
                type="select"
              >
                <option value="" key="0" />
                {customers
                  ? customers.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/invoice" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default InvoiceUpdate;
