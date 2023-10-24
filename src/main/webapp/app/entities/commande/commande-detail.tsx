import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './commande.reducer';

export const CommandeDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const commandeEntity = useAppSelector(state => state.commande.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="commandeDetailsHeading">
          <Translate contentKey="madaskillApp.commande.detail.title">Commande</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{commandeEntity.id}</dd>
          <dt>
            <span id="dateCommande">
              <Translate contentKey="madaskillApp.commande.dateCommande">Date Commande</Translate>
            </span>
          </dt>
          <dd>
            {commandeEntity.dateCommande ? <TextFormat value={commandeEntity.dateCommande} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="tva">
              <Translate contentKey="madaskillApp.commande.tva">Tva</Translate>
            </span>
          </dt>
          <dd>{commandeEntity.tva}</dd>
          <dt>
            <span id="taxesTotales">
              <Translate contentKey="madaskillApp.commande.taxesTotales">Taxes Totales</Translate>
            </span>
          </dt>
          <dd>{commandeEntity.taxesTotales}</dd>
          <dt>
            <span id="montantAmountTtc">
              <Translate contentKey="madaskillApp.commande.montantAmountTtc">Montant Amount Ttc</Translate>
            </span>
          </dt>
          <dd>{commandeEntity.montantAmountTtc}</dd>
          <dt>
            <span id="isPayedIsFacture">
              <Translate contentKey="madaskillApp.commande.isPayedIsFacture">Is Payed Is Facture</Translate>
            </span>
          </dt>
          <dd>{commandeEntity.isPayedIsFacture ? 'true' : 'false'}</dd>
        </dl>
        <Button tag={Link} to="/commande" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/commande/${commandeEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default CommandeDetail;
