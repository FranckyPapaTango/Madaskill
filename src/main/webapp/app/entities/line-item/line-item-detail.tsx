import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './line-item.reducer';

export const LineItemDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const lineItemEntity = useAppSelector(state => state.lineItem.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="lineItemDetailsHeading">
          <Translate contentKey="madaskillApp.lineItem.detail.title">LineItem</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="madaskillApp.lineItem.id">Id</Translate>
            </span>
          </dt>
          <dd>{lineItemEntity.id}</dd>
          <dt>
            <span id="object">
              <Translate contentKey="madaskillApp.lineItem.object">Object</Translate>
            </span>
          </dt>
          <dd>{lineItemEntity.object}</dd>
          <dt>
            <span id="amount">
              <Translate contentKey="madaskillApp.lineItem.amount">Amount</Translate>
            </span>
          </dt>
          <dd>{lineItemEntity.amount}</dd>
          <dt>
            <span id="amountExcludingTax">
              <Translate contentKey="madaskillApp.lineItem.amountExcludingTax">Amount Excluding Tax</Translate>
            </span>
          </dt>
          <dd>{lineItemEntity.amountExcludingTax}</dd>
          <dt>
            <span id="currency">
              <Translate contentKey="madaskillApp.lineItem.currency">Currency</Translate>
            </span>
          </dt>
          <dd>{lineItemEntity.currency}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="madaskillApp.lineItem.description">Description</Translate>
            </span>
          </dt>
          <dd>{lineItemEntity.description}</dd>
          <dt>
            <span id="discountAmounts">
              <Translate contentKey="madaskillApp.lineItem.discountAmounts">Discount Amounts</Translate>
            </span>
          </dt>
          <dd>{lineItemEntity.discountAmounts}</dd>
          <dt>
            <span id="discountable">
              <Translate contentKey="madaskillApp.lineItem.discountable">Discountable</Translate>
            </span>
          </dt>
          <dd>{lineItemEntity.discountable ? 'true' : 'false'}</dd>
          <dt>
            <span id="discounts">
              <Translate contentKey="madaskillApp.lineItem.discounts">Discounts</Translate>
            </span>
          </dt>
          <dd>{lineItemEntity.discounts}</dd>
          <dt>
            <span id="invoiceItem">
              <Translate contentKey="madaskillApp.lineItem.invoiceItem">Invoice Item</Translate>
            </span>
          </dt>
          <dd>{lineItemEntity.invoiceItem}</dd>
          <dt>
            <span id="livemode">
              <Translate contentKey="madaskillApp.lineItem.livemode">Livemode</Translate>
            </span>
          </dt>
          <dd>{lineItemEntity.livemode ? 'true' : 'false'}</dd>
          <dt>
            <span id="metadata">
              <Translate contentKey="madaskillApp.lineItem.metadata">Metadata</Translate>
            </span>
          </dt>
          <dd>{lineItemEntity.metadata}</dd>
          <dt>
            <span id="periodEnd">
              <Translate contentKey="madaskillApp.lineItem.periodEnd">Period End</Translate>
            </span>
          </dt>
          <dd>{lineItemEntity.periodEnd}</dd>
          <dt>
            <span id="periodStart">
              <Translate contentKey="madaskillApp.lineItem.periodStart">Period Start</Translate>
            </span>
          </dt>
          <dd>{lineItemEntity.periodStart}</dd>
          <dt>
            <span id="price">
              <Translate contentKey="madaskillApp.lineItem.price">Price</Translate>
            </span>
          </dt>
          <dd>{lineItemEntity.price}</dd>
          <dt>
            <span id="proration">
              <Translate contentKey="madaskillApp.lineItem.proration">Proration</Translate>
            </span>
          </dt>
          <dd>{lineItemEntity.proration ? 'true' : 'false'}</dd>
          <dt>
            <span id="prorationDetails">
              <Translate contentKey="madaskillApp.lineItem.prorationDetails">Proration Details</Translate>
            </span>
          </dt>
          <dd>{lineItemEntity.prorationDetails}</dd>
          <dt>
            <span id="quantity">
              <Translate contentKey="madaskillApp.lineItem.quantity">Quantity</Translate>
            </span>
          </dt>
          <dd>{lineItemEntity.quantity}</dd>
          <dt>
            <span id="subscription">
              <Translate contentKey="madaskillApp.lineItem.subscription">Subscription</Translate>
            </span>
          </dt>
          <dd>{lineItemEntity.subscription}</dd>
          <dt>
            <span id="taxAmounts">
              <Translate contentKey="madaskillApp.lineItem.taxAmounts">Tax Amounts</Translate>
            </span>
          </dt>
          <dd>{lineItemEntity.taxAmounts}</dd>
          <dt>
            <span id="taxRates">
              <Translate contentKey="madaskillApp.lineItem.taxRates">Tax Rates</Translate>
            </span>
          </dt>
          <dd>{lineItemEntity.taxRates}</dd>
          <dt>
            <span id="type">
              <Translate contentKey="madaskillApp.lineItem.type">Type</Translate>
            </span>
          </dt>
          <dd>{lineItemEntity.type}</dd>
          <dt>
            <span id="unitAmountExcludingTax">
              <Translate contentKey="madaskillApp.lineItem.unitAmountExcludingTax">Unit Amount Excluding Tax</Translate>
            </span>
          </dt>
          <dd>{lineItemEntity.unitAmountExcludingTax}</dd>
          <dt>
            <Translate contentKey="madaskillApp.lineItem.product">Product</Translate>
          </dt>
          <dd>{lineItemEntity.product ? lineItemEntity.product.id : ''}</dd>
          <dt>
            <Translate contentKey="madaskillApp.lineItem.commande">Commande</Translate>
          </dt>
          <dd>{lineItemEntity.commande ? lineItemEntity.commande.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/line-item" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/line-item/${lineItemEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default LineItemDetail;
