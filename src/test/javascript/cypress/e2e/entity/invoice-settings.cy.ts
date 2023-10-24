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

describe('InvoiceSettings e2e test', () => {
  const invoiceSettingsPageUrl = '/invoice-settings';
  const invoiceSettingsPageUrlPattern = new RegExp('/invoice-settings(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const invoiceSettingsSample = {};

  let invoiceSettings;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/invoice-settings+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/invoice-settings').as('postEntityRequest');
    cy.intercept('DELETE', '/api/invoice-settings/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (invoiceSettings) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/invoice-settings/${invoiceSettings.id}`,
      }).then(() => {
        invoiceSettings = undefined;
      });
    }
  });

  it('InvoiceSettings menu should load InvoiceSettings page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('invoice-settings');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('InvoiceSettings').should('exist');
    cy.url().should('match', invoiceSettingsPageUrlPattern);
  });

  describe('InvoiceSettings page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(invoiceSettingsPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create InvoiceSettings page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/invoice-settings/new$'));
        cy.getEntityCreateUpdateHeading('InvoiceSettings');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', invoiceSettingsPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/invoice-settings',
          body: invoiceSettingsSample,
        }).then(({ body }) => {
          invoiceSettings = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/invoice-settings+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              headers: {
                link: '<http://localhost/api/invoice-settings?page=0&size=20>; rel="last",<http://localhost/api/invoice-settings?page=0&size=20>; rel="first"',
              },
              body: [invoiceSettings],
            }
          ).as('entitiesRequestInternal');
        });

        cy.visit(invoiceSettingsPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details InvoiceSettings page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('invoiceSettings');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', invoiceSettingsPageUrlPattern);
      });

      it('edit button click should load edit InvoiceSettings page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('InvoiceSettings');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', invoiceSettingsPageUrlPattern);
      });

      it('edit button click should load edit InvoiceSettings page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('InvoiceSettings');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', invoiceSettingsPageUrlPattern);
      });

      it('last delete button click should delete instance of InvoiceSettings', () => {
        cy.intercept('GET', '/api/invoice-settings/*').as('dialogDeleteRequest');
        cy.get(entityDeleteButtonSelector).last().click();
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('invoiceSettings').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', invoiceSettingsPageUrlPattern);

        invoiceSettings = undefined;
      });
    });
  });

  describe('new InvoiceSettings page', () => {
    beforeEach(() => {
      cy.visit(`${invoiceSettingsPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('InvoiceSettings');
    });

    it('should create an instance of InvoiceSettings', () => {
      cy.get(`[data-cy="customFields"]`).type('Aquitaine').should('have.value', 'Aquitaine');

      cy.get(`[data-cy="defaultPaymentMethod"]`).type('niches').should('have.value', 'niches');

      cy.get(`[data-cy="footer"]`).type('Sleek Triple-buffered').should('have.value', 'Sleek Triple-buffered');

      cy.get(`[data-cy="renderingOptions"]`).type('Kenya').should('have.value', 'Kenya');

      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(201);
        invoiceSettings = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(200);
      });
      cy.url().should('match', invoiceSettingsPageUrlPattern);
    });
  });
});
