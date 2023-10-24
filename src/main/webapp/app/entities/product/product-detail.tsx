import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './product.reducer';

export const ProductDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const productEntity = useAppSelector(state => state.product.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="productDetailsHeading">
          <Translate contentKey="madaskillApp.product.detail.title">Product</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{productEntity.id}</dd>
          <dt>
            <span id="object">
              <Translate contentKey="madaskillApp.product.object">Object</Translate>
            </span>
          </dt>
          <dd>{productEntity.object}</dd>
          <dt>
            <span id="active">
              <Translate contentKey="madaskillApp.product.active">Active</Translate>
            </span>
          </dt>
          <dd>{productEntity.active ? 'true' : 'false'}</dd>
          <dt>
            <span id="created">
              <Translate contentKey="madaskillApp.product.created">Created</Translate>
            </span>
          </dt>
          <dd>{productEntity.created}</dd>
          <dt>
            <span id="defaultPrice">
              <Translate contentKey="madaskillApp.product.defaultPrice">Default Price</Translate>
            </span>
          </dt>
          <dd>{productEntity.defaultPrice}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="madaskillApp.product.description">Description</Translate>
            </span>
          </dt>
          <dd>{productEntity.description}</dd>
          <dt>
            <span id="livemode">
              <Translate contentKey="madaskillApp.product.livemode">Livemode</Translate>
            </span>
          </dt>
          <dd>{productEntity.livemode ? 'true' : 'false'}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="madaskillApp.product.name">Name</Translate>
            </span>
          </dt>
          <dd>{productEntity.name}</dd>
          <dt>
            <span id="shippable">
              <Translate contentKey="madaskillApp.product.shippable">Shippable</Translate>
            </span>
          </dt>
          <dd>{productEntity.shippable ? 'true' : 'false'}</dd>
          <dt>
            <span id="statementDescriptor">
              <Translate contentKey="madaskillApp.product.statementDescriptor">Statement Descriptor</Translate>
            </span>
          </dt>
          <dd>{productEntity.statementDescriptor}</dd>
          <dt>
            <span id="taxCode">
              <Translate contentKey="madaskillApp.product.taxCode">Tax Code</Translate>
            </span>
          </dt>
          <dd>{productEntity.taxCode}</dd>
          <dt>
            <span id="unitLabel">
              <Translate contentKey="madaskillApp.product.unitLabel">Unit Label</Translate>
            </span>
          </dt>
          <dd>{productEntity.unitLabel}</dd>
          <dt>
            <span id="updated">
              <Translate contentKey="madaskillApp.product.updated">Updated</Translate>
            </span>
          </dt>
          <dd>{productEntity.updated}</dd>
          <dt>
            <span id="url">
              <Translate contentKey="madaskillApp.product.url">Url</Translate>
            </span>
          </dt>
          <dd>{productEntity.url}</dd>
          <dt>
            <span id="sku">
              <Translate contentKey="madaskillApp.product.sku">Sku</Translate>
            </span>
          </dt>
          <dd>{productEntity.sku}</dd>
          <dt>
            <span id="title">
              <Translate contentKey="madaskillApp.product.title">Title</Translate>
            </span>
          </dt>
          <dd>{productEntity.title}</dd>
          <dt>
            <span id="linkToGenericPhotoFile">
              <Translate contentKey="madaskillApp.product.linkToGenericPhotoFile">Link To Generic Photo File</Translate>
            </span>
          </dt>
          <dd>{productEntity.linkToGenericPhotoFile}</dd>
          <dt>
            <span id="availableSizes">
              <Translate contentKey="madaskillApp.product.availableSizes">Available Sizes</Translate>
            </span>
          </dt>
          <dd>{productEntity.availableSizes}</dd>
          <dt>
            <span id="currencyFormat">
              <Translate contentKey="madaskillApp.product.currencyFormat">Currency Format</Translate>
            </span>
          </dt>
          <dd>{productEntity.currencyFormat}</dd>
          <dt>
            <span id="isFreeShipping">
              <Translate contentKey="madaskillApp.product.isFreeShipping">Is Free Shipping</Translate>
            </span>
          </dt>
          <dd>{productEntity.isFreeShipping ? 'true' : 'false'}</dd>
          <dt>
            <span id="price">
              <Translate contentKey="madaskillApp.product.price">Price</Translate>
            </span>
          </dt>
          <dd>{productEntity.price}</dd>
          <dt>
            <span id="style">
              <Translate contentKey="madaskillApp.product.style">Style</Translate>
            </span>
          </dt>
          <dd>{productEntity.style}</dd>
          <dt>
            <span id="installments">
              <Translate contentKey="madaskillApp.product.installments">Installments</Translate>
            </span>
          </dt>
          <dd>{productEntity.installments}</dd>
          <dt>
            <Translate contentKey="madaskillApp.product.user">User</Translate>
          </dt>
          <dd>{productEntity.user ? productEntity.user.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/product" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/product/${productEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default ProductDetail;
