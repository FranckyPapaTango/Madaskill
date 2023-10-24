import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './preferred-locales.reducer';

export const PreferredLocalesDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const preferredLocalesEntity = useAppSelector(state => state.preferredLocales.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="preferredLocalesDetailsHeading">
          <Translate contentKey="madaskillApp.preferredLocales.detail.title">PreferredLocales</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="madaskillApp.preferredLocales.id">Id</Translate>
            </span>
          </dt>
          <dd>{preferredLocalesEntity.id}</dd>
          <dt>
            <span id="preferredLocales">
              <Translate contentKey="madaskillApp.preferredLocales.preferredLocales">Preferred Locales</Translate>
            </span>
          </dt>
          <dd>{preferredLocalesEntity.preferredLocales}</dd>
        </dl>
        <Button tag={Link} to="/preferred-locales" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/preferred-locales/${preferredLocalesEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default PreferredLocalesDetail;
