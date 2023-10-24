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

describe('PreferredLocales e2e test', () => {
  const preferredLocalesPageUrl = '/preferred-locales';
  const preferredLocalesPageUrlPattern = new RegExp('/preferred-locales(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const preferredLocalesSample = {};

  let preferredLocales;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/preferred-locales+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/preferred-locales').as('postEntityRequest');
    cy.intercept('DELETE', '/api/preferred-locales/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (preferredLocales) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/preferred-locales/${preferredLocales.id}`,
      }).then(() => {
        preferredLocales = undefined;
      });
    }
  });

  it('PreferredLocales menu should load PreferredLocales page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('preferred-locales');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('PreferredLocales').should('exist');
    cy.url().should('match', preferredLocalesPageUrlPattern);
  });

  describe('PreferredLocales page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(preferredLocalesPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create PreferredLocales page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/preferred-locales/new$'));
        cy.getEntityCreateUpdateHeading('PreferredLocales');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', preferredLocalesPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/preferred-locales',
          body: preferredLocalesSample,
        }).then(({ body }) => {
          preferredLocales = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/preferred-locales+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              headers: {
                link: '<http://localhost/api/preferred-locales?page=0&size=20>; rel="last",<http://localhost/api/preferred-locales?page=0&size=20>; rel="first"',
              },
              body: [preferredLocales],
            }
          ).as('entitiesRequestInternal');
        });

        cy.visit(preferredLocalesPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details PreferredLocales page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('preferredLocales');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', preferredLocalesPageUrlPattern);
      });

      it('edit button click should load edit PreferredLocales page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('PreferredLocales');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', preferredLocalesPageUrlPattern);
      });

      it('edit button click should load edit PreferredLocales page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('PreferredLocales');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', preferredLocalesPageUrlPattern);
      });

      it('last delete button click should delete instance of PreferredLocales', () => {
        cy.intercept('GET', '/api/preferred-locales/*').as('dialogDeleteRequest');
        cy.get(entityDeleteButtonSelector).last().click();
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('preferredLocales').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', preferredLocalesPageUrlPattern);

        preferredLocales = undefined;
      });
    });
  });

  describe('new PreferredLocales page', () => {
    beforeEach(() => {
      cy.visit(`${preferredLocalesPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('PreferredLocales');
    });

    it('should create an instance of PreferredLocales', () => {
      cy.get(`[data-cy="preferredLocales"]`).type('Israël').should('have.value', 'Israël');

      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(201);
        preferredLocales = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(200);
      });
      cy.url().should('match', preferredLocalesPageUrlPattern);
    });
  });
});
