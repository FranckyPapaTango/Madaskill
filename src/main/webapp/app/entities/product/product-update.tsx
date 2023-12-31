import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { ILineItem } from 'app/shared/model/line-item.model';
import { getEntities as getLineItems } from 'app/entities/line-item/line-item.reducer';
import { IApplicationUser } from 'app/shared/model/application-user.model';
import { getEntities as getApplicationUsers } from 'app/entities/application-user/application-user.reducer';
import { IProduct } from 'app/shared/model/product.model';
import { getEntity, updateEntity, createEntity, reset } from './product.reducer';
import Dropzone from 'react-dropzone';
import './product-update.scss';
import axios from 'axios';

export const ProductUpdate = () => {
  const [selectedImageName, setSelectedImageName] = useState(''); // Déclarez setSelectedImageName
  const dispatch = useAppDispatch();
  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const lineItems = useAppSelector(state => state.lineItem.entities);
  const applicationUsers = useAppSelector(state => state.applicationUser.entities);
  const productEntity = useAppSelector(state => state.product.entity);
  const loading = useAppSelector(state => state.product.loading);
  const updating = useAppSelector(state => state.product.updating);
  const updateSuccess = useAppSelector(state => state.product.updateSuccess);

  const handleClose = () => {
    navigate('/product' + location.search);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getLineItems({}));
    dispatch(getApplicationUsers({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = async values => {
    try {
      const entity = {
        ...productEntity,
        ...values,
        user: applicationUsers.find(it => it.id.toString() === values.user.toString()),
      };

      if (isNew) {
        if (fileInput) {
          const formData = new FormData();
          formData.append('file', fileInput);

          try {
            const response = await axios.post('/happy/upload-image', formData, {
              headers: {
                'Content-Type': 'multipart/form-data',
              },
            });

            // Vous pouvez gérer la réponse ici, par exemple, enregistrez l'URL de l'image dans votre base de données.
            // console.log('Image uploaded:', response.data);
            // Réinitialisez le champ d'entrée de fichier après l'envoi
            setFileInput(null);
          } catch (error) {
            console.error('Error uploading image:', error);
            // Gérez les erreurs de téléchargement ici.
          }
        }
        dispatch(createEntity(entity));
      } else {
        if (fileInput) {
          const formData = new FormData();
          formData.append('file', fileInput);
          //  const imageName = productEntity.linkToGenericPhotoFile;//.replace('../../../content/productsImages/', '');
          try {
            const response = await axios.put(
              `/happy/update-image/${productEntity.linkToGenericPhotoFile.replace('../../../content/productsImages/', '')}`,
              formData,
              {
                headers: {
                  'Content-Type': 'multipart/form-data',
                },
              }
            );

            // Vous pouvez gérer la réponse ici, par exemple, enregistrez l'URL de l'image dans votre base de données.
            // console.log('Image updated:', response.data);
            // Réinitialisez le champ d'entrée de fichier après l'envoi
            setFileInput(null);
          } catch (error) {
            console.error('Error updating image:', error);
            // Gérez les erreurs de téléchargement ici.
          }
        }
        dispatch(updateEntity(entity));
      }
    } catch (error) {
      console.error('Error saving entity:', error);
      // Gérez les erreurs de téléchargement ici.
    }
  };

  const [fileInput, setFileInput] = useState(null);
  const handleFileChange = acceptedFiles => {
    // Vous pouvez gérer les fichiers acceptés ici. Par exemple, vous pouvez afficher l'image ou effectuer d'autres opérations.
    const selectedFile = acceptedFiles[0];
    setFileInput(selectedFile);

    if (selectedFile) {
      const fileName = selectedFile.name;
      setSelectedImageName(fileName);
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          ...productEntity,
          user: productEntity?.user?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="madaskillApp.product.home.createOrEditLabel" data-cy="ProductCreateUpdateHeading">
            <Translate contentKey="madaskillApp.product.home.createOrEditLabel">Create or edit a Product</Translate>
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
                  id="product-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('madaskillApp.product.object')}
                id="product-object"
                name="object"
                data-cy="object"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.product.active')}
                id="product-active"
                name="active"
                data-cy="active"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('madaskillApp.product.created')}
                id="product-created"
                name="created"
                data-cy="created"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.product.defaultPrice')}
                id="product-defaultPrice"
                name="defaultPrice"
                data-cy="defaultPrice"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.product.description')}
                id="product-description"
                name="description"
                data-cy="description"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.product.livemode')}
                id="product-livemode"
                name="livemode"
                data-cy="livemode"
                check
                type="checkbox"
              />
              <ValidatedField label={translate('madaskillApp.product.name')} id="product-name" name="name" data-cy="name" type="text" />
              <ValidatedField
                label={translate('madaskillApp.product.shippable')}
                id="product-shippable"
                name="shippable"
                data-cy="shippable"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('madaskillApp.product.statementDescriptor')}
                id="product-statementDescriptor"
                name="statementDescriptor"
                data-cy="statementDescriptor"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.product.taxCode')}
                id="product-taxCode"
                name="taxCode"
                data-cy="taxCode"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.product.unitLabel')}
                id="product-unitLabel"
                name="unitLabel"
                data-cy="unitLabel"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.product.updated')}
                id="product-updated"
                name="updated"
                data-cy="updated"
                type="text"
              />
              <ValidatedField label={translate('madaskillApp.product.url')} id="product-url" name="url" data-cy="url" type="text" />
              <ValidatedField label={translate('madaskillApp.product.sku')} id="product-sku" name="sku" data-cy="sku" type="text" />
              <ValidatedField
                label={translate('madaskillApp.product.title')}
                id="product-title"
                name="title"
                data-cy="title"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <Row>
                <Col md="12">
                  <Dropzone onDrop={handleFileChange} accept="image/*" multiple={false}>
                    {({ getRootProps, getInputProps }) => (
                      <div {...getRootProps()} className="dropzone">
                        <input {...getInputProps()} />
                        <p>Glissez et déposez un fichier image ou cliquez pour sélectionner.</p>
                      </div>
                    )}
                  </Dropzone>
                </Col>
              </Row>
              <Row>
                <Col md="12" className="dropzone">
                  {fileInput && fileInput instanceof Blob && (
                    <img src={URL.createObjectURL(fileInput)} alt="Selected Image" className="selected-image" />
                  )}
                </Col>
                <br />
                <Col md="12" className="imgToReplace">
                  {!isNew && <img src={productEntity.linkToGenericPhotoFile} alt="Selected Image" className="selected-image" />}
                  {!isNew && (
                    <div>(Valeur à copier coller dans le champ suivant) = {'../../../content/productsImages/' + selectedImageName}</div>
                  )}
                </Col>
              </Row>
              {isNew && (
                <ValidatedField
                  label={translate('madaskillApp.product.linkToGenericPhotoFile')}
                  id="product-linkToGenericPhotoFile"
                  name="linkToGenericPhotoFile"
                  data-cy="linkToGenericPhotoFile"
                  type="text"
                  value={'../../../content/productsImages/' + selectedImageName} // Utilisez le nom de fichier sélectionné ici
                />
              )}
              {!isNew && (
                <ValidatedField
                  label={translate('madaskillApp.product.linkToGenericPhotoFile')}
                  id="product-linkToGenericPhotoFile"
                  name="linkToGenericPhotoFile"
                  data-cy="linkToGenericPhotoFile"
                  type="text"
                  value={'../../../content/productsImages/' + selectedImageName} // Utilisez le nom de fichier sélectionné ici
                />
              )}
              <ValidatedField
                label={translate('madaskillApp.product.availableSizes')}
                id="product-availableSizes"
                name="availableSizes"
                data-cy="availableSizes"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.product.currencyFormat')}
                id="product-currencyFormat"
                name="currencyFormat"
                data-cy="currencyFormat"
                type="text"
              />
              <ValidatedField
                label={translate('madaskillApp.product.isFreeShipping')}
                id="product-isFreeShipping"
                name="isFreeShipping"
                data-cy="isFreeShipping"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('madaskillApp.product.price')}
                id="product-price"
                name="price"
                data-cy="price"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  validate: v => isNumber(v) || translate('entity.validation.number'),
                }}
              />
              <ValidatedField label={translate('madaskillApp.product.style')} id="product-style" name="style" data-cy="style" type="text" />
              <ValidatedField
                label={translate('madaskillApp.product.installments')}
                id="product-installments"
                name="installments"
                data-cy="installments"
                type="text"
              />
              <ValidatedField id="product-user" name="user" data-cy="user" label={translate('madaskillApp.product.user')} type="select">
                <option value="" key="0" />
                {applicationUsers
                  ? applicationUsers.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/product" replace color="info">
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

export default ProductUpdate;
