import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './metadata.reducer';

export const MetadataDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const metadataEntity = useAppSelector(state => state.metadata.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="metadataDetailsHeading">
          <Translate contentKey="madaskillApp.metadata.detail.title">Metadata</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="madaskillApp.metadata.id">Id</Translate>
            </span>
          </dt>
          <dd>{metadataEntity.id}</dd>
          <dt>
            <span id="orderId">
              <Translate contentKey="madaskillApp.metadata.orderId">Order Id</Translate>
            </span>
          </dt>
          <dd>{metadataEntity.orderId}</dd>
        </dl>
        <Button tag={Link} to="/metadata" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/metadata/${metadataEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default MetadataDetail;
