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

describe('Customer e2e test', () => {
  const customerPageUrl = '/customer';
  const customerPageUrlPattern = new RegExp('/customer(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const customerSample = { id: '0de855ef-1cad-41e2-ba85-b572d771861d' };

  let customer;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/customers+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/customers').as('postEntityRequest');
    cy.intercept('DELETE', '/api/customers/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (customer) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/customers/${customer.id}`,
      }).then(() => {
        customer = undefined;
      });
    }
  });

  it('Customers menu should load Customers page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('customer');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('Customer').should('exist');
    cy.url().should('match', customerPageUrlPattern);
  });

  describe('Customer page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(customerPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create Customer page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/customer/new$'));
        cy.getEntityCreateUpdateHeading('Customer');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', customerPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/customers',
          body: customerSample,
        }).then(({ body }) => {
          customer = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/customers+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              headers: {
                link: '<http://localhost/api/customers?page=0&size=20>; rel="last",<http://localhost/api/customers?page=0&size=20>; rel="first"',
              },
              body: [customer],
            }
          ).as('entitiesRequestInternal');
        });

        cy.visit(customerPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details Customer page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('customer');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', customerPageUrlPattern);
      });

      it('edit button click should load edit Customer page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('Customer');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', customerPageUrlPattern);
      });

      it('edit button click should load edit Customer page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('Customer');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', customerPageUrlPattern);
      });

      it('last delete button click should delete instance of Customer', () => {
        cy.intercept('GET', '/api/customers/*').as('dialogDeleteRequest');
        cy.get(entityDeleteButtonSelector).last().click();
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('customer').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', customerPageUrlPattern);

        customer = undefined;
      });
    });
  });

  describe('new Customer page', () => {
    beforeEach(() => {
      cy.visit(`${customerPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('Customer');
    });

    it('should create an instance of Customer', () => {
      cy.get(`[data-cy="id"]`).type('0c53333d-6b22-43ec-9091-5a1acad42532').should('have.value', '0c53333d-6b22-43ec-9091-5a1acad42532');

      cy.get(`[data-cy="object"]`).type('systems Practical').should('have.value', 'systems Practical');

      cy.get(`[data-cy="address"]`).type('value-added').should('have.value', 'value-added');

      cy.get(`[data-cy="balance"]`).type('77961').should('have.value', '77961');

      cy.get(`[data-cy="created"]`).type('2438').should('have.value', '2438');

      cy.get(`[data-cy="currency"]`).type('Berkshire Fresh').should('have.value', 'Berkshire Fresh');

      cy.get(`[data-cy="defaultSource"]`).type('invoice c').should('have.value', 'invoice c');

      cy.get(`[data-cy="delinquent"]`).should('not.be.checked');
      cy.get(`[data-cy="delinquent"]`).click().should('be.checked');

      cy.get(`[data-cy="description"]`).type('azure open-source matrix').should('have.value', 'azure open-source matrix');

      cy.get(`[data-cy="discount"]`).type('5100').should('have.value', '5100');

      cy.get(`[data-cy="email"]`).type('Diane.Garnier@yahoo.fr').should('have.value', 'Diane.Garnier@yahoo.fr');

      cy.get(`[data-cy="invoicePrefix"]`).type('Object-based').should('have.value', 'Object-based');

      cy.get(`[data-cy="livemode"]`).should('not.be.checked');
      cy.get(`[data-cy="livemode"]`).click().should('be.checked');

      cy.get(`[data-cy="name"]`).type('Computers').should('have.value', 'Computers');

      cy.get(`[data-cy="nextInvoiceSequence"]`).type('71053').should('have.value', '71053');

      cy.get(`[data-cy="phone"]`).type('0489938218').should('have.value', '0489938218');

      cy.get(`[data-cy="shipping"]`).type('primary b invoice').should('have.value', 'primary b invoice');

      cy.get(`[data-cy="taxExempt"]`).type('deliverables Albanie').should('have.value', 'deliverables Albanie');

      cy.get(`[data-cy="testClock"]`).type('2023-10-23T22:30').blur().should('have.value', '2023-10-23T22:30');

      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(201);
        customer = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(200);
      });
      cy.url().should('match', customerPageUrlPattern);
    });
  });
});
