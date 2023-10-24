import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './photo.reducer';

export const PhotoDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const photoEntity = useAppSelector(state => state.photo.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="photoDetailsHeading">
          <Translate contentKey="madaskillApp.photo.detail.title">Photo</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{photoEntity.id}</dd>
          <dt>
            <span id="title">
              <Translate contentKey="madaskillApp.photo.title">Title</Translate>
            </span>
          </dt>
          <dd>{photoEntity.title}</dd>
          <dt>
            <span id="linkToPhotoFile">
              <Translate contentKey="madaskillApp.photo.linkToPhotoFile">Link To Photo File</Translate>
            </span>
          </dt>
          <dd>{photoEntity.linkToPhotoFile}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="madaskillApp.photo.description">Description</Translate>
            </span>
          </dt>
          <dd>{photoEntity.description}</dd>
          <dt>
            <span id="author">
              <Translate contentKey="madaskillApp.photo.author">Author</Translate>
            </span>
          </dt>
          <dd>{photoEntity.author}</dd>
          <dt>
            <span id="owner">
              <Translate contentKey="madaskillApp.photo.owner">Owner</Translate>
            </span>
          </dt>
          <dd>{photoEntity.owner}</dd>
          <dt>
            <span id="height">
              <Translate contentKey="madaskillApp.photo.height">Height</Translate>
            </span>
          </dt>
          <dd>{photoEntity.height}</dd>
          <dt>
            <span id="width">
              <Translate contentKey="madaskillApp.photo.width">Width</Translate>
            </span>
          </dt>
          <dd>{photoEntity.width}</dd>
          <dt>
            <span id="taken">
              <Translate contentKey="madaskillApp.photo.taken">Taken</Translate>
            </span>
          </dt>
          <dd>{photoEntity.taken ? <TextFormat value={photoEntity.taken} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="uploaded">
              <Translate contentKey="madaskillApp.photo.uploaded">Uploaded</Translate>
            </span>
          </dt>
          <dd>{photoEntity.uploaded ? <TextFormat value={photoEntity.uploaded} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <Translate contentKey="madaskillApp.photo.post">Post</Translate>
          </dt>
          <dd>{photoEntity.post ? photoEntity.post.id : ''}</dd>
          <dt>
            <Translate contentKey="madaskillApp.photo.product">Product</Translate>
          </dt>
          <dd>{photoEntity.product ? photoEntity.product.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/photo" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/photo/${photoEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default PhotoDetail;
