import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './customer.reducer';

export const CustomerDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const customerEntity = useAppSelector(state => state.customer.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="customerDetailsHeading">
          <Translate contentKey="madaskillApp.customer.detail.title">Customer</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="madaskillApp.customer.id">Id</Translate>
            </span>
          </dt>
          <dd>{customerEntity.id}</dd>
          <dt>
            <span id="object">
              <Translate contentKey="madaskillApp.customer.object">Object</Translate>
            </span>
          </dt>
          <dd>{customerEntity.object}</dd>
          <dt>
            <span id="address">
              <Translate contentKey="madaskillApp.customer.address">Address</Translate>
            </span>
          </dt>
          <dd>{customerEntity.address}</dd>
          <dt>
            <span id="balance">
              <Translate contentKey="madaskillApp.customer.balance">Balance</Translate>
            </span>
          </dt>
          <dd>{customerEntity.balance}</dd>
          <dt>
            <span id="created">
              <Translate contentKey="madaskillApp.customer.created">Created</Translate>
            </span>
          </dt>
          <dd>{customerEntity.created}</dd>
          <dt>
            <span id="currency">
              <Translate contentKey="madaskillApp.customer.currency">Currency</Translate>
            </span>
          </dt>
          <dd>{customerEntity.currency}</dd>
          <dt>
            <span id="defaultSource">
              <Translate contentKey="madaskillApp.customer.defaultSource">Default Source</Translate>
            </span>
          </dt>
          <dd>{customerEntity.defaultSource}</dd>
          <dt>
            <span id="delinquent">
              <Translate contentKey="madaskillApp.customer.delinquent">Delinquent</Translate>
            </span>
          </dt>
          <dd>{customerEntity.delinquent ? 'true' : 'false'}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="madaskillApp.customer.description">Description</Translate>
            </span>
          </dt>
          <dd>{customerEntity.description}</dd>
          <dt>
            <span id="discount">
              <Translate contentKey="madaskillApp.customer.discount">Discount</Translate>
            </span>
          </dt>
          <dd>{customerEntity.discount}</dd>
          <dt>
            <span id="email">
              <Translate contentKey="madaskillApp.customer.email">Email</Translate>
            </span>
          </dt>
          <dd>{customerEntity.email}</dd>
          <dt>
            <span id="invoicePrefix">
              <Translate contentKey="madaskillApp.customer.invoicePrefix">Invoice Prefix</Translate>
            </span>
          </dt>
          <dd>{customerEntity.invoicePrefix}</dd>
          <dt>
            <span id="livemode">
              <Translate contentKey="madaskillApp.customer.livemode">Livemode</Translate>
            </span>
          </dt>
          <dd>{customerEntity.livemode ? 'true' : 'false'}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="madaskillApp.customer.name">Name</Translate>
            </span>
          </dt>
          <dd>{customerEntity.name}</dd>
          <dt>
            <span id="nextInvoiceSequence">
              <Translate contentKey="madaskillApp.customer.nextInvoiceSequence">Next Invoice Sequence</Translate>
            </span>
          </dt>
          <dd>{customerEntity.nextInvoiceSequence}</dd>
          <dt>
            <span id="phone">
              <Translate contentKey="madaskillApp.customer.phone">Phone</Translate>
            </span>
          </dt>
          <dd>{customerEntity.phone}</dd>
          <dt>
            <span id="shipping">
              <Translate contentKey="madaskillApp.customer.shipping">Shipping</Translate>
            </span>
          </dt>
          <dd>{customerEntity.shipping}</dd>
          <dt>
            <span id="taxExempt">
              <Translate contentKey="madaskillApp.customer.taxExempt">Tax Exempt</Translate>
            </span>
          </dt>
          <dd>{customerEntity.taxExempt}</dd>
          <dt>
            <span id="testClock">
              <Translate contentKey="madaskillApp.customer.testClock">Test Clock</Translate>
            </span>
          </dt>
          <dd>{customerEntity.testClock ? <TextFormat value={customerEntity.testClock} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <Translate contentKey="madaskillApp.customer.invoiceSettings">Invoice Settings</Translate>
          </dt>
          <dd>{customerEntity.invoiceSettings ? customerEntity.invoiceSettings.id : ''}</dd>
          <dt>
            <Translate contentKey="madaskillApp.customer.metadata">Metadata</Translate>
          </dt>
          <dd>{customerEntity.metadata ? customerEntity.metadata.id : ''}</dd>
          <dt>
            <Translate contentKey="madaskillApp.customer.preferredLocales">Preferred Locales</Translate>
          </dt>
          <dd>
            {customerEntity.preferredLocales
              ? customerEntity.preferredLocales.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {customerEntity.preferredLocales && i === customerEntity.preferredLocales.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
        </dl>
        <Button tag={Link} to="/customer" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/customer/${customerEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default CustomerDetail;
