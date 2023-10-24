import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './invoice-settings.reducer';

export const InvoiceSettingsDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const invoiceSettingsEntity = useAppSelector(state => state.invoiceSettings.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="invoiceSettingsDetailsHeading">
          <Translate contentKey="madaskillApp.invoiceSettings.detail.title">InvoiceSettings</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="madaskillApp.invoiceSettings.id">Id</Translate>
            </span>
          </dt>
          <dd>{invoiceSettingsEntity.id}</dd>
          <dt>
            <span id="customFields">
              <Translate contentKey="madaskillApp.invoiceSettings.customFields">Custom Fields</Translate>
            </span>
          </dt>
          <dd>{invoiceSettingsEntity.customFields}</dd>
          <dt>
            <span id="defaultPaymentMethod">
              <Translate contentKey="madaskillApp.invoiceSettings.defaultPaymentMethod">Default Payment Method</Translate>
            </span>
          </dt>
          <dd>{invoiceSettingsEntity.defaultPaymentMethod}</dd>
          <dt>
            <span id="footer">
              <Translate contentKey="madaskillApp.invoiceSettings.footer">Footer</Translate>
            </span>
          </dt>
          <dd>{invoiceSettingsEntity.footer}</dd>
          <dt>
            <span id="renderingOptions">
              <Translate contentKey="madaskillApp.invoiceSettings.renderingOptions">Rendering Options</Translate>
            </span>
          </dt>
          <dd>{invoiceSettingsEntity.renderingOptions}</dd>
        </dl>
        <Button tag={Link} to="/invoice-settings" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/invoice-settings/${invoiceSettingsEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default InvoiceSettingsDetail;
