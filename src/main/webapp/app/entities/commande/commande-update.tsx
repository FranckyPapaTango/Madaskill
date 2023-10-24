import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IInvoice } from 'app/shared/model/invoice.model';
import { getEntities as getInvoices } from 'app/entities/invoice/invoice.reducer';
import { ICommande } from 'app/shared/model/commande.model';
import { getEntity, updateEntity, createEntity, reset } from './commande.reducer';

export const CommandeUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const invoices = useAppSelector(state => state.invoice.entities);
  const commandeEntity = useAppSelector(state => state.commande.entity);
  const loading = useAppSelector(state => state.commande.loading);
  const updating = useAppSelector(state => state.commande.updating);
  const updateSuccess = useAppSelector(state => state.commande.updateSuccess);

  const handleClose = () => {
    navigate('/commande' + location.search);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getInvoices({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    values.dateCommande = convertDateTimeToServer(values.dateCommande);

    const entity = {
      ...commandeEntity,
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
      ? {
          dateCommande: displayDefaultDateTime(),
        }
      : {
          ...commandeEntity,
          dateCommande: convertDateTimeFromServer(commandeEntity.dateCommande),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="madaskillApp.commande.home.createOrEditLabel" data-cy="CommandeCreateUpdateHeading">
            <Translate contentKey="madaskillApp.commande.home.createOrEditLabel">Create or edit a Commande</Translate>
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
                  id="commande-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('madaskillApp.commande.dateCommande')}
                id="commande-dateCommande"
                name="dateCommande"
                data-cy="dateCommande"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('madaskillApp.commande.tva')}
                id="commande-tva"
                name="tva"
                data-cy="tva"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  validate: v => isNumber(v) || translate('entity.validation.number'),
                }}
              />
              <ValidatedField
                label={translate('madaskillApp.commande.taxesTotales')}
                id="commande-taxesTotales"
                name="taxesTotales"
                data-cy="taxesTotales"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  validate: v => isNumber(v) || translate('entity.validation.number'),
                }}
              />
              <ValidatedField
                label={translate('madaskillApp.commande.montantAmountTtc')}
                id="commande-montantAmountTtc"
                name="montantAmountTtc"
                data-cy="montantAmountTtc"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  validate: v => isNumber(v) || translate('entity.validation.number'),
                }}
              />
              <ValidatedField
                label={translate('madaskillApp.commande.isPayedIsFacture')}
                id="commande-isPayedIsFacture"
                name="isPayedIsFacture"
                data-cy="isPayedIsFacture"
                check
                type="checkbox"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/commande" replace color="info">
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

export default CommandeUpdate;
