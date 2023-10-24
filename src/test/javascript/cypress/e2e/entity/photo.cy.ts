import {
  entityTableSelector,
  entityDetailsButtonSelector,
  entityDetailsBackButtonSelector,
  entityCreateButtonSelector,
  entityCreateSaveButtonSelector,
  entityCreateCancelButtonSelector,
  entityEditButtonSelector,
  entityDeleteButtonSelector,
  entityConfirmDeleteButtonSelector,
} from '../../support/entity';

describe('Photo e2e test', () => {
  const photoPageUrl = '/photo';
  const photoPageUrlPattern = new RegExp('/photo(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const photoSample = { linkToPhotoFile: 'Guinée Industrial' };

  let photo;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/photos+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/photos').as('postEntityRequest');
    cy.intercept('DELETE', '/api/photos/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (photo) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/photos/${photo.id}`,
      }).then(() => {
        photo = undefined;
      });
    }
  });

  it('Photos menu should load Photos page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('photo');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('Photo').should('exist');
    cy.url().should('match', photoPageUrlPattern);
  });

  describe('Photo page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(photoPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create Photo page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/photo/new$'));
        cy.getEntityCreateUpdateHeading('Photo');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', photoPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/photos',
          body: photoSample,
        }).then(({ body }) => {
          photo = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/photos+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              headers: {
                link: '<http://localhost/api/photos?page=0&size=20>; rel="last",<http://localhost/api/photos?page=0&size=20>; rel="first"',
              },
              body: [photo],
            }
          ).as('entitiesRequestInternal');
        });

        cy.visit(photoPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details Photo page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('photo');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', photoPageUrlPattern);
      });

      it('edit button click should load edit Photo page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('Photo');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', photoPageUrlPattern);
      });

      it('edit button click should load edit Photo page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('Photo');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', photoPageUrlPattern);
      });

      it('last delete button click should delete instance of Photo', () => {
        cy.intercept('GET', '/api/photos/*').as('dialogDeleteRequest');
        cy.get(entityDeleteButtonSelector).last().click();
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('photo').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', photoPageUrlPattern);

        photo = undefined;
      });
    });
  });

  describe('new Photo page', () => {
    beforeEach(() => {
      cy.visit(`${photoPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('Photo');
    });

    it('should create an instance of Photo', () => {
      cy.get(`[data-cy="title"]`).type('withdrawal Trinité-et-Tobago').should('have.value', 'withdrawal Trinité-et-Tobago');

      cy.get(`[data-cy="linkToPhotoFile"]`).type('Dollar expedite').should('have.value', 'Dollar expedite');

      cy.get(`[data-cy="description"]`).type('a enable').should('have.value', 'a enable');

      cy.get(`[data-cy="author"]`).type('Swiss').should('have.value', 'Swiss');

      cy.get(`[data-cy="owner"]`).type('Tilsitt Basse-Normandie Gorgeous').should('have.value', 'Tilsitt Basse-Normandie Gorgeous');

      cy.get(`[data-cy="height"]`).type('86088').should('have.value', '86088');

      cy.get(`[data-cy="width"]`).type('21681').should('have.value', '21681');

      cy.get(`[data-cy="taken"]`).type('2023-10-23T16:48').blur().should('have.value', '2023-10-23T16:48');

      cy.get(`[data-cy="uploaded"]`).type('2023-10-24T04:45').blur().should('have.value', '2023-10-24T04:45');

      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(201);
        photo = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(200);
      });
      cy.url().should('match', photoPageUrlPattern);
    });
  });
});
