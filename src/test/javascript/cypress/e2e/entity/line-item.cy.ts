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

describe('LineItem e2e test', () => {
  const lineItemPageUrl = '/line-item';
  const lineItemPageUrlPattern = new RegExp('/line-item(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const lineItemSample = {};

  let lineItem;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/line-items+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/line-items').as('postEntityRequest');
    cy.intercept('DELETE', '/api/line-items/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (lineItem) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/line-items/${lineItem.id}`,
      }).then(() => {
        lineItem = undefined;
      });
    }
  });

  it('LineItems menu should load LineItems page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('line-item');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('LineItem').should('exist');
    cy.url().should('match', lineItemPageUrlPattern);
  });

  describe('LineItem page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(lineItemPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create LineItem page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/line-item/new$'));
        cy.getEntityCreateUpdateHeading('LineItem');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', lineItemPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/line-items',
          body: lineItemSample,
        }).then(({ body }) => {
          lineItem = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/line-items+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              headers: {
                link: '<http://localhost/api/line-items?page=0&size=20>; rel="last",<http://localhost/api/line-items?page=0&size=20>; rel="first"',
              },
              body: [lineItem],
            }
          ).as('entitiesRequestInternal');
        });

        cy.visit(lineItemPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details LineItem page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('lineItem');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', lineItemPageUrlPattern);
      });

      it('edit button click should load edit LineItem page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('LineItem');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', lineItemPageUrlPattern);
      });

      it('edit button click should load edit LineItem page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('LineItem');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', lineItemPageUrlPattern);
      });

      it('last delete button click should delete instance of LineItem', () => {
        cy.intercept('GET', '/api/line-items/*').as('dialogDeleteRequest');
        cy.get(entityDeleteButtonSelector).last().click();
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('lineItem').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', lineItemPageUrlPattern);

        lineItem = undefined;
      });
    });
  });

  describe('new LineItem page', () => {
    beforeEach(() => {
      cy.visit(`${lineItemPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('LineItem');
    });

    it('should create an instance of LineItem', () => {
      cy.get(`[data-cy="id"]`).type('2be4bd62-149d-4bd0-811e-66c93bb76406').should('have.value', '2be4bd62-149d-4bd0-811e-66c93bb76406');

      cy.get(`[data-cy="object"]`).type('olive Tugrik back-end').should('have.value', 'olive Tugrik back-end');

      cy.get(`[data-cy="amount"]`).type('79413').should('have.value', '79413');

      cy.get(`[data-cy="amountExcludingTax"]`).type('47241').should('have.value', '47241');

      cy.get(`[data-cy="currency"]`).type('innovate gold').should('have.value', 'innovate gold');

      cy.get(`[data-cy="description"]`).type('Industrial Belgique').should('have.value', 'Industrial Belgique');

      cy.get(`[data-cy="discountAmounts"]`).type('invoice microchip').should('have.value', 'invoice microchip');

      cy.get(`[data-cy="discountable"]`).should('not.be.checked');
      cy.get(`[data-cy="discountable"]`).click().should('be.checked');

      cy.get(`[data-cy="discounts"]`).type('de Grocery Tasty').should('have.value', 'de Grocery Tasty');

      cy.get(`[data-cy="invoiceItem"]`).type('Loan').should('have.value', 'Loan');

      cy.get(`[data-cy="livemode"]`).should('not.be.checked');
      cy.get(`[data-cy="livemode"]`).click().should('be.checked');

      cy.get(`[data-cy="metadata"]`).type('payment EXE Bourgogne').should('have.value', 'payment EXE Bourgogne');

      cy.get(`[data-cy="periodEnd"]`).type('38516').should('have.value', '38516');

      cy.get(`[data-cy="periodStart"]`).type('40366').should('have.value', '40366');

      cy.get(`[data-cy="price"]`).type('Gorgeous matrix').should('have.value', 'Gorgeous matrix');

      cy.get(`[data-cy="proration"]`).should('not.be.checked');
      cy.get(`[data-cy="proration"]`).click().should('be.checked');

      cy.get(`[data-cy="prorationDetails"]`).type('open-source').should('have.value', 'open-source');

      cy.get(`[data-cy="quantity"]`).type('93182').should('have.value', '93182');

      cy.get(`[data-cy="subscription"]`).type('ability').should('have.value', 'ability');

      cy.get(`[data-cy="taxAmounts"]`).type('user-facing').should('have.value', 'user-facing');

      cy.get(`[data-cy="taxRates"]`)
        .type('programming Producteur Bedfordshire')
        .should('have.value', 'programming Producteur Bedfordshire');

      cy.get(`[data-cy="type"]`).type('Practical').should('have.value', 'Practical');

      cy.get(`[data-cy="unitAmountExcludingTax"]`).type('Gambie').should('have.value', 'Gambie');

      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(201);
        lineItem = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(200);
      });
      cy.url().should('match', lineItemPageUrlPattern);
    });
  });
});
