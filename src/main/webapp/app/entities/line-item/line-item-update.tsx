import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IProduct } from 'app/shared/model/product.model';
import { getEntities as getProducts } from 'app/entities/product/product.reducer';
import { ICommande } from 'app/shared/model/commande.model';
import { getEntities as getCommandes } from 'app/entities/commande/commande.reducer';
import { ILineItem } from 'app/shared/model/line-item.model';
import { getEntity, updateEntity, createEntity, reset } from './line-item.reducer';

export const LineItemUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const products = useAppSelector(state => state.product.entities);
  const commandes = useAppSelector(state => state.commande.entities);
  const lineItemEntity = useAppSelector(state => state.lineItem.entity);
  const loading = useAppSelector(state => state.lineItem.loading);
  const updating = useAppSelector(state => state.lineItem.updating);
  const updateSuccess = useAppSelector(state => state.lineItem.updateSuccess);

  const handleClose = () => {
    navigate('/line-item' + location.search);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getProducts({}));
    dispatch(getCommandes({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...lineItemEntity,
      ...values,
      product: products.find(it => it.id.toString() === values.product.toString()),
      commande: commandes.find(it => it.id.toString() === values.commande.toString()),
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
          ...lineItemEntity,
          product: lineItemEntity?.product?.id,
          commande: lineItemEntity?.commande?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="madaskillApp.lineItem.home.createOrEditLabel" data-cy="LineItemCreateUpdateHeading">
            <Translate contentKey="madaskillApp.lineItem.home.createOrEditLabel">Create or edit a LineItem</Translate>
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
                  id="line-item-id"
                  label={translate('madaskillApp.lineItem.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('madaskillApp.lineItem.object')}
                id="line-item-object"
                name="object"
                data-cy="object"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.lineItem.amount')}
                id="line-item-amount"
                name="amount"
                data-cy="amount"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.lineItem.amountExcludingTax')}
                id="line-item-amountExcludingTax"
                name="amountExcludingTax"
                data-cy="amountExcludingTax"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.lineItem.currency')}
                id="line-item-currency"
                name="currency"
                data-cy="currency"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.lineItem.description')}
                id="line-item-description"
                name="description"
                data-cy="description"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.lineItem.discountAmounts')}
                id="line-item-discountAmounts"
                name="discountAmounts"
                data-cy="discountAmounts"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.lineItem.discountable')}
                id="line-item-discountable"
                name="discountable"
                data-cy="discountable"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('madaskillApp.lineItem.discounts')}
                id="line-item-discounts"
                name="discounts"
                data-cy="discounts"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.lineItem.invoiceItem')}
                id="line-item-invoiceItem"
                name="invoiceItem"
                data-cy="invoiceItem"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.lineItem.livemode')}
                id="line-item-livemode"
                name="livemode"
                data-cy="livemode"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('madaskillApp.lineItem.metadata')}
                id="line-item-metadata"
                name="metadata"
                data-cy="metadata"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.lineItem.periodEnd')}
                id="line-item-periodEnd"
                name="periodEnd"
                data-cy="periodEnd"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.lineItem.periodStart')}
                id="line-item-periodStart"
                name="periodStart"
                data-cy="periodStart"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.lineItem.price')}
                id="line-item-price"
                name="price"
                data-cy="price"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.lineItem.proration')}
                id="line-item-proration"
                name="proration"
                data-cy="proration"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('madaskillApp.lineItem.prorationDetails')}
                id="line-item-prorationDetails"
                name="prorationDetails"
                data-cy="prorationDetails"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.lineItem.quantity')}
                id="line-item-quantity"
                name="quantity"
                data-cy="quantity"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.lineItem.subscription')}
                id="line-item-subscription"
                name="subscription"
                data-cy="subscription"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.lineItem.taxAmounts')}
                id="line-item-taxAmounts"
                name="taxAmounts"
                data-cy="taxAmounts"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.lineItem.taxRates')}
                id="line-item-taxRates"
                name="taxRates"
                data-cy="taxRates"
                type="text"
              />
              <ValidatedField label={translate('madaskillApp.lineItem.type')} id="line-item-type" name="type" data-cy="type" type="text" />
              <ValidatedField
                label={translate('madaskillApp.lineItem.unitAmountExcludingTax')}
                id="line-item-unitAmountExcludingTax"
                name="unitAmountExcludingTax"
                data-cy="unitAmountExcludingTax"
                type="text"
              />
              <ValidatedField
                id="line-item-product"
                name="product"
                data-cy="product"
                label={translate('madaskillApp.lineItem.product')}
                type="select"
              >
                <option value="" key="0" />
                {products
                  ? products.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="line-item-commande"
                name="commande"
                data-cy="commande"
                label={translate('madaskillApp.lineItem.commande')}
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
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/line-item" replace color="info">
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

export default LineItemUpdate;
