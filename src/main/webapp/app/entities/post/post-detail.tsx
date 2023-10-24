import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './post.reducer';

export const PostDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const postEntity = useAppSelector(state => state.post.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="postDetailsHeading">
          <Translate contentKey="madaskillApp.post.detail.title">Post</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{postEntity.id}</dd>
          <dt>
            <span id="title">
              <Translate contentKey="madaskillApp.post.title">Title</Translate>
            </span>
          </dt>
          <dd>{postEntity.title}</dd>
          <dt>
            <span id="body">
              <Translate contentKey="madaskillApp.post.body">Body</Translate>
            </span>
          </dt>
          <dd>{postEntity.body}</dd>
          <dt>
            <span id="categorie">
              <Translate contentKey="madaskillApp.post.categorie">Categorie</Translate>
            </span>
          </dt>
          <dd>{postEntity.categorie}</dd>
          <dt>
            <span id="resumee">
              <Translate contentKey="madaskillApp.post.resumee">Resumee</Translate>
            </span>
          </dt>
          <dd>{postEntity.resumee}</dd>
          <dt>
            <span id="auteur">
              <Translate contentKey="madaskillApp.post.auteur">Auteur</Translate>
            </span>
          </dt>
          <dd>{postEntity.auteur}</dd>
          <dt>
            <span id="datePersistence">
              <Translate contentKey="madaskillApp.post.datePersistence">Date Persistence</Translate>
            </span>
          </dt>
          <dd>
            {postEntity.datePersistence ? <TextFormat value={postEntity.datePersistence} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <Translate contentKey="madaskillApp.post.user">User</Translate>
          </dt>
          <dd>{postEntity.user ? postEntity.user.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/post" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/post/${postEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default PostDetail;
