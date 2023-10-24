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

describe('Product e2e test', () => {
  const productPageUrl = '/product';
  const productPageUrlPattern = new RegExp('/product(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const productSample = { title: 'salmon', price: 53454 };

  let product;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/products+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/products').as('postEntityRequest');
    cy.intercept('DELETE', '/api/products/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (product) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/products/${product.id}`,
      }).then(() => {
        product = undefined;
      });
    }
  });

  it('Products menu should load Products page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('product');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('Product').should('exist');
    cy.url().should('match', productPageUrlPattern);
  });

  describe('Product page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(productPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create Product page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/product/new$'));
        cy.getEntityCreateUpdateHeading('Product');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', productPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/products',
          body: productSample,
        }).then(({ body }) => {
          product = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/products+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              headers: {
                link: '<http://localhost/api/products?page=0&size=20>; rel="last",<http://localhost/api/products?page=0&size=20>; rel="first"',
              },
              body: [product],
            }
          ).as('entitiesRequestInternal');
        });

        cy.visit(productPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details Product page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('product');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', productPageUrlPattern);
      });

      it('edit button click should load edit Product page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('Product');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', productPageUrlPattern);
      });

      it('edit button click should load edit Product page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('Product');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', productPageUrlPattern);
      });

      it('last delete button click should delete instance of Product', () => {
        cy.intercept('GET', '/api/products/*').as('dialogDeleteRequest');
        cy.get(entityDeleteButtonSelector).last().click();
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('product').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', productPageUrlPattern);

        product = undefined;
      });
    });
  });

  describe('new Product page', () => {
    beforeEach(() => {
      cy.visit(`${productPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('Product');
    });

    it('should create an instance of Product', () => {
      cy.get(`[data-cy="object"]`).type('XSS').should('have.value', 'XSS');

      cy.get(`[data-cy="active"]`).should('not.be.checked');
      cy.get(`[data-cy="active"]`).click().should('be.checked');

      cy.get(`[data-cy="created"]`).type('55601').should('have.value', '55601');

      cy.get(`[data-cy="defaultPrice"]`).type('41176').should('have.value', '41176');

      cy.get(`[data-cy="description"]`).type('Personal b invoice').should('have.value', 'Personal b invoice');

      cy.get(`[data-cy="livemode"]`).should('not.be.checked');
      cy.get(`[data-cy="livemode"]`).click().should('be.checked');

      cy.get(`[data-cy="name"]`).type('bandwidth SCSI Rupee').should('have.value', 'bandwidth SCSI Rupee');

      cy.get(`[data-cy="shippable"]`).should('not.be.checked');
      cy.get(`[data-cy="shippable"]`).click().should('be.checked');

      cy.get(`[data-cy="statementDescriptor"]`).type('synthesizing').should('have.value', 'synthesizing');

      cy.get(`[data-cy="taxCode"]`).type('Tugrik').should('have.value', 'Tugrik');

      cy.get(`[data-cy="unitLabel"]`).type('drive').should('have.value', 'drive');

      cy.get(`[data-cy="updated"]`).type('64171').should('have.value', '64171');

      cy.get(`[data-cy="url"]`).type('https://séraphin.name').should('have.value', 'https://séraphin.name');

      cy.get(`[data-cy="sku"]`).type('digital').should('have.value', 'digital');

      cy.get(`[data-cy="title"]`).type('deposit parse Account').should('have.value', 'deposit parse Account');

      cy.get(`[data-cy="linkToGenericPhotoFile"]`).type('lime bandwidth').should('have.value', 'lime bandwidth');

      cy.get(`[data-cy="availableSizes"]`)
        .type('fresh-thinking Consultant synergies')
        .should('have.value', 'fresh-thinking Consultant synergies');

      cy.get(`[data-cy="currencyFormat"]`).type('TCP payment').should('have.value', 'TCP payment');

      cy.get(`[data-cy="isFreeShipping"]`).should('not.be.checked');
      cy.get(`[data-cy="isFreeShipping"]`).click().should('be.checked');

      cy.get(`[data-cy="price"]`).type('88789').should('have.value', '88789');

      cy.get(`[data-cy="style"]`).type('b Granite').should('have.value', 'b Granite');

      cy.get(`[data-cy="installments"]`).type('69379').should('have.value', '69379');

      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(201);
        product = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(200);
      });
      cy.url().should('match', productPageUrlPattern);
    });
  });
});
