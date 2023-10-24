import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IPost } from 'app/shared/model/post.model';
import { getEntities as getPosts } from 'app/entities/post/post.reducer';
import { IProduct } from 'app/shared/model/product.model';
import { getEntities as getProducts } from 'app/entities/product/product.reducer';
import { IPhoto } from 'app/shared/model/photo.model';
import { getEntity, updateEntity, createEntity, reset } from './photo.reducer';

export const PhotoUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const posts = useAppSelector(state => state.post.entities);
  const products = useAppSelector(state => state.product.entities);
  const photoEntity = useAppSelector(state => state.photo.entity);
  const loading = useAppSelector(state => state.photo.loading);
  const updating = useAppSelector(state => state.photo.updating);
  const updateSuccess = useAppSelector(state => state.photo.updateSuccess);

  const handleClose = () => {
    navigate('/photo' + location.search);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getPosts({}));
    dispatch(getProducts({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    values.taken = convertDateTimeToServer(values.taken);
    values.uploaded = convertDateTimeToServer(values.uploaded);

    const entity = {
      ...photoEntity,
      ...values,
      post: posts.find(it => it.id.toString() === values.post.toString()),
      product: products.find(it => it.id.toString() === values.product.toString()),
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {
          taken: displayDefaultDateTime(),
          uploaded: displayDefaultDateTime(),
        }
      : {
          ...photoEntity,
          taken: convertDateTimeFromServer(photoEntity.taken),
          uploaded: convertDateTimeFromServer(photoEntity.uploaded),
          post: photoEntity?.post?.id,
          product: photoEntity?.product?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="madaskillApp.photo.home.createOrEditLabel" data-cy="PhotoCreateUpdateHeading">
            <Translate contentKey="madaskillApp.photo.home.createOrEditLabel">Create or edit a Photo</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField
                  name="id"
                  required
                  readOnly
                  id="photo-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField label={translate('madaskillApp.photo.title')} id="photo-title" name="title" data-cy="title" type="text" />
              <ValidatedField
                label={translate('madaskillApp.photo.linkToPhotoFile')}
                id="photo-linkToPhotoFile"
                name="linkToPhotoFile"
                data-cy="linkToPhotoFile"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('madaskillApp.photo.description')}
                id="photo-description"
                name="description"
                data-cy="description"
                type="text"
              />
              <ValidatedField label={translate('madaskillApp.photo.author')} id="photo-author" name="author" data-cy="author" type="text" />
              <ValidatedField label={translate('madaskillApp.photo.owner')} id="photo-owner" name="owner" data-cy="owner" type="text" />
              <ValidatedField label={translate('madaskillApp.photo.height')} id="photo-height" name="height" data-cy="height" type="text" />
              <ValidatedField label={translate('madaskillApp.photo.width')} id="photo-width" name="width" data-cy="width" type="text" />
              <ValidatedField
                label={translate('madaskillApp.photo.taken')}
                id="photo-taken"
                name="taken"
                data-cy="taken"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('madaskillApp.photo.uploaded')}
                id="photo-uploaded"
                name="uploaded"
                data-cy="uploaded"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField id="photo-post" name="post" data-cy="post" label={translate('madaskillApp.photo.post')} type="select">
                <option value="" key="0" />
                {posts
                  ? posts.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="photo-product"
                name="product"
                data-cy="product"
                label={translate('madaskillApp.photo.product')}
                type="select"
              >
                <option value="" key="0" />
                {products
                  ? products.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/photo" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default PhotoUpdate;
