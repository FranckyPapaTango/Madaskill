import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IInvoiceSettings } from 'app/shared/model/invoice-settings.model';
import { getEntities as getInvoiceSettings } from 'app/entities/invoice-settings/invoice-settings.reducer';
import { IMetadata } from 'app/shared/model/metadata.model';
import { getEntities as getMetadata } from 'app/entities/metadata/metadata.reducer';
import { IPreferredLocales } from 'app/shared/model/preferred-locales.model';
import { getEntities as getPreferredLocales } from 'app/entities/preferred-locales/preferred-locales.reducer';
import { ICustomer } from 'app/shared/model/customer.model';
import { getEntity, updateEntity, createEntity, reset } from './customer.reducer';

export const CustomerUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const invoiceSettings = useAppSelector(state => state.invoiceSettings.entities);
  const metadata = useAppSelector(state => state.metadata.entities);
  const preferredLocales = useAppSelector(state => state.preferredLocales.entities);
  const customerEntity = useAppSelector(state => state.customer.entity);
  const loading = useAppSelector(state => state.customer.loading);
  const updating = useAppSelector(state => state.customer.updating);
  const updateSuccess = useAppSelector(state => state.customer.updateSuccess);

  const handleClose = () => {
    navigate('/customer' + location.search);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getInvoiceSettings({}));
    dispatch(getMetadata({}));
    dispatch(getPreferredLocales({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    values.testClock = convertDateTimeToServer(values.testClock);

    const entity = {
      ...customerEntity,
      ...values,
      preferredLocales: mapIdList(values.preferredLocales),
      invoiceSettings: invoiceSettings.find(it => it.id.toString() === values.invoiceSettings.toString()),
      metadata: metadata.find(it => it.id.toString() === values.metadata.toString()),
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {
          testClock: displayDefaultDateTime(),
        }
      : {
          ...customerEntity,
          testClock: convertDateTimeFromServer(customerEntity.testClock),
          invoiceSettings: customerEntity?.invoiceSettings?.id,
          metadata: customerEntity?.metadata?.id,
          preferredLocales: customerEntity?.preferredLocales?.map(e => e.id.toString()),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="madaskillApp.customer.home.createOrEditLabel" data-cy="CustomerCreateUpdateHeading">
            <Translate contentKey="madaskillApp.customer.home.createOrEditLabel">Create or edit a Customer</Translate>
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
                  id="customer-id"
                  label={translate('madaskillApp.customer.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('madaskillApp.customer.object')}
                id="customer-object"
                name="object"
                data-cy="object"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.customer.address')}
                id="customer-address"
                name="address"
                data-cy="address"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.customer.balance')}
                id="customer-balance"
                name="balance"
                data-cy="balance"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.customer.created')}
                id="customer-created"
                name="created"
                data-cy="created"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.customer.currency')}
                id="customer-currency"
                name="currency"
                data-cy="currency"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.customer.defaultSource')}
                id="customer-defaultSource"
                name="defaultSource"
                data-cy="defaultSource"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.customer.delinquent')}
                id="customer-delinquent"
                name="delinquent"
                data-cy="delinquent"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('madaskillApp.customer.description')}
                id="customer-description"
                name="description"
                data-cy="description"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.customer.discount')}
                id="customer-discount"
                name="discount"
                data-cy="discount"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.customer.email')}
                id="customer-email"
                name="email"
                data-cy="email"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.customer.invoicePrefix')}
                id="customer-invoicePrefix"
                name="invoicePrefix"
                data-cy="invoicePrefix"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.customer.livemode')}
                id="customer-livemode"
                name="livemode"
                data-cy="livemode"
                check
                type="checkbox"
              />
              <ValidatedField label={translate('madaskillApp.customer.name')} id="customer-name" name="name" data-cy="name" type="text" />
              <ValidatedField
                label={translate('madaskillApp.customer.nextInvoiceSequence')}
                id="customer-nextInvoiceSequence"
                name="nextInvoiceSequence"
                data-cy="nextInvoiceSequence"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.customer.phone')}
                id="customer-phone"
                name="phone"
                data-cy="phone"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.customer.shipping')}
                id="customer-shipping"
                name="shipping"
                data-cy="shipping"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.customer.taxExempt')}
                id="customer-taxExempt"
                name="taxExempt"
                data-cy="taxExempt"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.customer.testClock')}
                id="customer-testClock"
                name="testClock"
                data-cy="testClock"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                id="customer-invoiceSettings"
                name="invoiceSettings"
                data-cy="invoiceSettings"
                label={translate('madaskillApp.customer.invoiceSettings')}
                type="select"
              >
                <option value="" key="0" />
                {invoiceSettings
                  ? invoiceSettings.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="customer-metadata"
                name="metadata"
                data-cy="metadata"
                label={translate('madaskillApp.customer.metadata')}
                type="select"
              >
                <option value="" key="0" />
                {metadata
                  ? metadata.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                label={translate('madaskillApp.customer.preferredLocales')}
                id="customer-preferredLocales"
                data-cy="preferredLocales"
                type="select"
                multiple
                name="preferredLocales"
              >
                <option value="" key="0" />
                {preferredLocales
                  ? preferredLocales.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/customer" replace color="info">
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

export default CustomerUpdate;
