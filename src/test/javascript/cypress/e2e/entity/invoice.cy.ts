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

describe('Invoice e2e test', () => {
  const invoicePageUrl = '/invoice';
  const invoicePageUrlPattern = new RegExp('/invoice(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const invoiceSample = {};

  let invoice;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/invoices+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/invoices').as('postEntityRequest');
    cy.intercept('DELETE', '/api/invoices/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (invoice) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/invoices/${invoice.id}`,
      }).then(() => {
        invoice = undefined;
      });
    }
  });

  it('Invoices menu should load Invoices page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('invoice');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('Invoice').should('exist');
    cy.url().should('match', invoicePageUrlPattern);
  });

  describe('Invoice page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(invoicePageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create Invoice page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/invoice/new$'));
        cy.getEntityCreateUpdateHeading('Invoice');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', invoicePageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/invoices',
          body: invoiceSample,
        }).then(({ body }) => {
          invoice = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/invoices+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              headers: {
                link: '<http://localhost/api/invoices?page=0&size=20>; rel="last",<http://localhost/api/invoices?page=0&size=20>; rel="first"',
              },
              body: [invoice],
            }
          ).as('entitiesRequestInternal');
        });

        cy.visit(invoicePageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details Invoice page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('invoice');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', invoicePageUrlPattern);
      });

      it('edit button click should load edit Invoice page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('Invoice');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', invoicePageUrlPattern);
      });

      it('edit button click should load edit Invoice page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('Invoice');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', invoicePageUrlPattern);
      });

      it('last delete button click should delete instance of Invoice', () => {
        cy.intercept('GET', '/api/invoices/*').as('dialogDeleteRequest');
        cy.get(entityDeleteButtonSelector).last().click();
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('invoice').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', invoicePageUrlPattern);

        invoice = undefined;
      });
    });
  });

  describe('new Invoice page', () => {
    beforeEach(() => {
      cy.visit(`${invoicePageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('Invoice');
    });

    it('should create an instance of Invoice', () => {
      cy.get(`[data-cy="id"]`).type('b2243987-c33e-440c-a063-dbe584a7583c').should('have.value', 'b2243987-c33e-440c-a063-dbe584a7583c');

      cy.get(`[data-cy="object"]`).type('Open-architected Fresh SAS').should('have.value', 'Open-architected Fresh SAS');

      cy.get(`[data-cy="accountCountry"]`).type('Silver encryption Ouguiya').should('have.value', 'Silver encryption Ouguiya');

      cy.get(`[data-cy="accountName"]`).type('Savings Account').should('have.value', 'Savings Account');

      cy.get(`[data-cy="accountTaxIds"]`).type('Vision-oriented').should('have.value', 'Vision-oriented');

      cy.get(`[data-cy="amountDue"]`).type('5313').should('have.value', '5313');

      cy.get(`[data-cy="amountPaid"]`).type('5541').should('have.value', '5541');

      cy.get(`[data-cy="amountRemaining"]`).type('34826').should('have.value', '34826');

      cy.get(`[data-cy="amountShipping"]`).type('45194').should('have.value', '45194');

      cy.get(`[data-cy="application"]`).type('Peso circuit').should('have.value', 'Peso circuit');

      cy.get(`[data-cy="applicationFeeAmount"]`).type('31814').should('have.value', '31814');

      cy.get(`[data-cy="attemptCount"]`).type('18118').should('have.value', '18118');

      cy.get(`[data-cy="attempted"]`).should('not.be.checked');
      cy.get(`[data-cy="attempted"]`).click().should('be.checked');

      cy.get(`[data-cy="autoAdvance"]`).should('not.be.checked');
      cy.get(`[data-cy="autoAdvance"]`).click().should('be.checked');

      cy.get(`[data-cy="billingReason"]`).type('Keyboard Metal Handcrafted').should('have.value', 'Keyboard Metal Handcrafted');

      cy.get(`[data-cy="charge"]`).type('Implemented Chips Innovative').should('have.value', 'Implemented Chips Innovative');

      cy.get(`[data-cy="collectionMethod"]`).type('Dollar').should('have.value', 'Dollar');

      cy.get(`[data-cy="created"]`).type('39570').should('have.value', '39570');

      cy.get(`[data-cy="currency"]`).type('primary b').should('have.value', 'primary b');

      cy.get(`[data-cy="customFields"]`).type('a Diverse').should('have.value', 'a Diverse');

      cy.get(`[data-cy="customerStringValue"]`).type('Kids sensor').should('have.value', 'Kids sensor');

      cy.get(`[data-cy="customerAddress"]`).type('stable République Fantastic').should('have.value', 'stable République Fantastic');

      cy.get(`[data-cy="customerEmail"]`).type('a Keyboard').should('have.value', 'a Keyboard');

      cy.get(`[data-cy="customerName"]`).type('Borders').should('have.value', 'Borders');

      cy.get(`[data-cy="customerPhone"]`).type('a Pants Royale').should('have.value', 'a Pants Royale');

      cy.get(`[data-cy="customerShipping"]`).type('input Account').should('have.value', 'input Account');

      cy.get(`[data-cy="customerTaxExempt"]`).type('b').should('have.value', 'b');

      cy.get(`[data-cy="customerTaxIds"]`).type('withdrawal Enterprise-wide').should('have.value', 'withdrawal Enterprise-wide');

      cy.get(`[data-cy="defaultPaymentMethod"]`).type('matrices Money c').should('have.value', 'matrices Money c');

      cy.get(`[data-cy="defaultSource"]`)
        .type('indexing Designer mission-critical')
        .should('have.value', 'indexing Designer mission-critical');

      cy.get(`[data-cy="defaultTaxRates"]`).type('b Lesotho revolutionize').should('have.value', 'b Lesotho revolutionize');

      cy.get(`[data-cy="description"]`).type('implement').should('have.value', 'implement');

      cy.get(`[data-cy="discount"]`).type('Monitored Inverse').should('have.value', 'Monitored Inverse');

      cy.get(`[data-cy="discounts"]`).type('Focused c North').should('have.value', 'Focused c North');

      cy.get(`[data-cy="dueDate"]`).type('19984').should('have.value', '19984');

      cy.get(`[data-cy="effectiveAt"]`).type('4709').should('have.value', '4709');

      cy.get(`[data-cy="endingBalance"]`).type('31435').should('have.value', '31435');

      cy.get(`[data-cy="footer"]`).type('Interface architect Plastic').should('have.value', 'Interface architect Plastic');

      cy.get(`[data-cy="fromInvoice"]`)
        .type('human-resource application Ergonomic')
        .should('have.value', 'human-resource application Ergonomic');

      cy.get(`[data-cy="hostedInvoiceUrl"]`).type('metrics').should('have.value', 'metrics');

      cy.get(`[data-cy="invoicePdf"]`).type('benchmark 24/7 navigate').should('have.value', 'benchmark 24/7 navigate');

      cy.get(`[data-cy="lastFinalizationError"]`).type('compelling withdrawal').should('have.value', 'compelling withdrawal');

      cy.get(`[data-cy="latestRevision"]`).type('array').should('have.value', 'array');

      cy.get(`[data-cy="livemode"]`).should('not.be.checked');
      cy.get(`[data-cy="livemode"]`).click().should('be.checked');

      cy.get(`[data-cy="metadata"]`).type('b Mandatory').should('have.value', 'b Mandatory');

      cy.get(`[data-cy="nextPaymentAttempt"]`).type('12652').should('have.value', '12652');

      cy.get(`[data-cy="number"]`).type('CSS online innovative').should('have.value', 'CSS online innovative');

      cy.get(`[data-cy="onBehalfOf"]`).type('dynamic Picardie').should('have.value', 'dynamic Picardie');

      cy.get(`[data-cy="paid"]`).should('not.be.checked');
      cy.get(`[data-cy="paid"]`).click().should('be.checked');

      cy.get(`[data-cy="paidOutOfBand"]`).should('not.be.checked');
      cy.get(`[data-cy="paidOutOfBand"]`).click().should('be.checked');

      cy.get(`[data-cy="paymentIntent"]`).type('Music').should('have.value', 'Music');

      cy.get(`[data-cy="paymentSettings"]`).type('deposit Multi-tiered').should('have.value', 'deposit Multi-tiered');

      cy.get(`[data-cy="periodEnd"]`).type('80536').should('have.value', '80536');

      cy.get(`[data-cy="periodStart"]`).type('95345').should('have.value', '95345');

      cy.get(`[data-cy="postPaymentCreditNotesAmount"]`).type('38812').should('have.value', '38812');

      cy.get(`[data-cy="prePaymentCreditNotesAmount"]`).type('62314').should('have.value', '62314');

      cy.get(`[data-cy="quote"]`).type('Plastic capacity Front-line').should('have.value', 'Plastic capacity Front-line');

      cy.get(`[data-cy="receiptNumber"]`).type('Superviseur').should('have.value', 'Superviseur');

      cy.get(`[data-cy="rendering"]`).type('Bretagne').should('have.value', 'Bretagne');

      cy.get(`[data-cy="renderingOptions"]`)
        .type('invoice Cambridgeshire Languedoc-Roussillon')
        .should('have.value', 'invoice Cambridgeshire Languedoc-Roussillon');

      cy.get(`[data-cy="shippingCost"]`).type('51920').should('have.value', '51920');

      cy.get(`[data-cy="shippingDetails"]`).type('Exclusive').should('have.value', 'Exclusive');

      cy.get(`[data-cy="startingBalance"]`).type('68281').should('have.value', '68281');

      cy.get(`[data-cy="statementDescriptor"]`).type('high-level').should('have.value', 'high-level');

      cy.get(`[data-cy="status"]`).type('b object-oriented').should('have.value', 'b object-oriented');

      cy.get(`[data-cy="statusTransitions"]`).type('Cotton SAS scalable').should('have.value', 'Cotton SAS scalable');

      cy.get(`[data-cy="subscription"]`).type('HTTP navigate d&#39;Azur').should('have.value', 'HTTP navigate d&#39;Azur');

      cy.get(`[data-cy="subscriptionDetails"]`).type('COM').should('have.value', 'COM');

      cy.get(`[data-cy="subtotal"]`).type('35635').should('have.value', '35635');

      cy.get(`[data-cy="subtotalExcludingTax"]`).type('74421').should('have.value', '74421');

      cy.get(`[data-cy="tax"]`).type('logistical generation').should('have.value', 'logistical generation');

      cy.get(`[data-cy="testClock"]`).type('Account a').should('have.value', 'Account a');

      cy.get(`[data-cy="total"]`).type('11044').should('have.value', '11044');

      cy.get(`[data-cy="totalDiscountAmounts"]`)
        .type('methodical Bosnie-Herzégovine')
        .should('have.value', 'methodical Bosnie-Herzégovine');

      cy.get(`[data-cy="totalExcludingTax"]`).type('6226').should('have.value', '6226');

      cy.get(`[data-cy="totalTaxAmounts"]`).type('Reduced a Money').should('have.value', 'Reduced a Money');

      cy.get(`[data-cy="transferData"]`).type('du').should('have.value', 'du');

      cy.get(`[data-cy="webhooksDeliveredAt"]`).type('30663').should('have.value', '30663');

      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(201);
        invoice = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(200);
      });
      cy.url().should('match', invoicePageUrlPattern);
    });
  });
});
