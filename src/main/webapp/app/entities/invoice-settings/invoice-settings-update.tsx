import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IInvoiceSettings } from 'app/shared/model/invoice-settings.model';
import { getEntity, updateEntity, createEntity, reset } from './invoice-settings.reducer';

export const InvoiceSettingsUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const invoiceSettingsEntity = useAppSelector(state => state.invoiceSettings.entity);
  const loading = useAppSelector(state => state.invoiceSettings.loading);
  const updating = useAppSelector(state => state.invoiceSettings.updating);
  const updateSuccess = useAppSelector(state => state.invoiceSettings.updateSuccess);

  const handleClose = () => {
    navigate('/invoice-settings' + location.search);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...invoiceSettingsEntity,
      ...values,
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
          ...invoiceSettingsEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="madaskillApp.invoiceSettings.home.createOrEditLabel" data-cy="InvoiceSettingsCreateUpdateHeading">
            <Translate contentKey="madaskillApp.invoiceSettings.home.createOrEditLabel">Create or edit a InvoiceSettings</Translate>
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
                  id="invoice-settings-id"
                  label={translate('madaskillApp.invoiceSettings.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('madaskillApp.invoiceSettings.customFields')}
                id="invoice-settings-customFields"
                name="customFields"
                data-cy="customFields"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.invoiceSettings.defaultPaymentMethod')}
                id="invoice-settings-defaultPaymentMethod"
                name="defaultPaymentMethod"
                data-cy="defaultPaymentMethod"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.invoiceSettings.footer')}
                id="invoice-settings-footer"
                name="footer"
                data-cy="footer"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.invoiceSettings.renderingOptions')}
                id="invoice-settings-renderingOptions"
                name="renderingOptions"
                data-cy="renderingOptions"
                type="text"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/invoice-settings" replace color="info">
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

export default InvoiceSettingsUpdate;
