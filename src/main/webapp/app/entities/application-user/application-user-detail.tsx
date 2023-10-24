import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './application-user.reducer';

export const ApplicationUserDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const applicationUserEntity = useAppSelector(state => state.applicationUser.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="applicationUserDetailsHeading">
          <Translate contentKey="madaskillApp.applicationUser.detail.title">ApplicationUser</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{applicationUserEntity.id}</dd>
          <dt>
            <span id="phoneNumber">
              <Translate contentKey="madaskillApp.applicationUser.phoneNumber">Phone Number</Translate>
            </span>
          </dt>
          <dd>{applicationUserEntity.phoneNumber}</dd>
          <dt>
            <span id="email">
              <Translate contentKey="madaskillApp.applicationUser.email">Email</Translate>
            </span>
          </dt>
          <dd>{applicationUserEntity.email}</dd>
          <dt>
            <span id="libelleAdresse">
              <Translate contentKey="madaskillApp.applicationUser.libelleAdresse">Libelle Adresse</Translate>
            </span>
          </dt>
          <dd>{applicationUserEntity.libelleAdresse}</dd>
          <dt>
            <span id="villeTown">
              <Translate contentKey="madaskillApp.applicationUser.villeTown">Ville Town</Translate>
            </span>
          </dt>
          <dd>{applicationUserEntity.villeTown}</dd>
          <dt>
            <span id="paysCountry">
              <Translate contentKey="madaskillApp.applicationUser.paysCountry">Pays Country</Translate>
            </span>
          </dt>
          <dd>{applicationUserEntity.paysCountry}</dd>
          <dt>
            <Translate contentKey="madaskillApp.applicationUser.internalUser">Internal User</Translate>
          </dt>
          <dd>{applicationUserEntity.internalUser ? applicationUserEntity.internalUser.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/application-user" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/application-user/${applicationUserEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default ApplicationUserDetail;
