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

describe('Commande e2e test', () => {
  const commandePageUrl = '/commande';
  const commandePageUrlPattern = new RegExp('/commande(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const commandeSample = {
    dateCommande: '2023-10-24T05:04:46.453Z',
    tva: 48746,
    taxesTotales: 58224,
    montantAmountTtc: 89476,
    isPayedIsFacture: true,
  };

  let commande;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/commandes+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/commandes').as('postEntityRequest');
    cy.intercept('DELETE', '/api/commandes/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (commande) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/commandes/${commande.id}`,
      }).then(() => {
        commande = undefined;
      });
    }
  });

  it('Commandes menu should load Commandes page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('commande');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('Commande').should('exist');
    cy.url().should('match', commandePageUrlPattern);
  });

  describe('Commande page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(commandePageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create Commande page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/commande/new$'));
        cy.getEntityCreateUpdateHeading('Commande');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', commandePageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/commandes',
          body: commandeSample,
        }).then(({ body }) => {
          commande = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/commandes+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              headers: {
                link: '<http://localhost/api/commandes?page=0&size=20>; rel="last",<http://localhost/api/commandes?page=0&size=20>; rel="first"',
              },
              body: [commande],
            }
          ).as('entitiesRequestInternal');
        });

        cy.visit(commandePageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details Commande page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('commande');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', commandePageUrlPattern);
      });

      it('edit button click should load edit Commande page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('Commande');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', commandePageUrlPattern);
      });

      it('edit button click should load edit Commande page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('Commande');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', commandePageUrlPattern);
      });

      it('last delete button click should delete instance of Commande', () => {
        cy.intercept('GET', '/api/commandes/*').as('dialogDeleteRequest');
        cy.get(entityDeleteButtonSelector).last().click();
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('commande').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', commandePageUrlPattern);

        commande = undefined;
      });
    });
  });

  describe('new Commande page', () => {
    beforeEach(() => {
      cy.visit(`${commandePageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('Commande');
    });

    it('should create an instance of Commande', () => {
      cy.get(`[data-cy="dateCommande"]`).type('2023-10-23T21:30').blur().should('have.value', '2023-10-23T21:30');

      cy.get(`[data-cy="tva"]`).type('76535').should('have.value', '76535');

      cy.get(`[data-cy="taxesTotales"]`).type('39252').should('have.value', '39252');

      cy.get(`[data-cy="montantAmountTtc"]`).type('1536').should('have.value', '1536');

      cy.get(`[data-cy="isPayedIsFacture"]`).should('not.be.checked');
      cy.get(`[data-cy="isPayedIsFacture"]`).click().should('be.checked');

      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(201);
        commande = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(200);
      });
      cy.url().should('match', commandePageUrlPattern);
    });
  });
});
