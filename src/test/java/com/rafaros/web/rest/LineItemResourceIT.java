package com.rafaros.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.rafaros.IntegrationTest;
import com.rafaros.domain.LineItem;
import com.rafaros.repository.LineItemRepository;
import java.util.List;
import java.util.UUID;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link LineItemResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class LineItemResourceIT {

    private static final String DEFAULT_OBJECT = "AAAAAAAAAA";
    private static final String UPDATED_OBJECT = "BBBBBBBBBB";

    private static final Long DEFAULT_AMOUNT = 1L;
    private static final Long UPDATED_AMOUNT = 2L;

    private static final Long DEFAULT_AMOUNT_EXCLUDING_TAX = 1L;
    private static final Long UPDATED_AMOUNT_EXCLUDING_TAX = 2L;

    private static final String DEFAULT_CURRENCY = "AAAAAAAAAA";
    private static final String UPDATED_CURRENCY = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_DISCOUNT_AMOUNTS = "AAAAAAAAAA";
    private static final String UPDATED_DISCOUNT_AMOUNTS = "BBBBBBBBBB";

    private static final Boolean DEFAULT_DISCOUNTABLE = false;
    private static final Boolean UPDATED_DISCOUNTABLE = true;

    private static final String DEFAULT_DISCOUNTS = "AAAAAAAAAA";
    private static final String UPDATED_DISCOUNTS = "BBBBBBBBBB";

    private static final String DEFAULT_INVOICE_ITEM = "AAAAAAAAAA";
    private static final String UPDATED_INVOICE_ITEM = "BBBBBBBBBB";

    private static final Boolean DEFAULT_LIVEMODE = false;
    private static final Boolean UPDATED_LIVEMODE = true;

    private static final String DEFAULT_METADATA = "AAAAAAAAAA";
    private static final String UPDATED_METADATA = "BBBBBBBBBB";

    private static final Long DEFAULT_PERIOD_END = 1L;
    private static final Long UPDATED_PERIOD_END = 2L;

    private static final Long DEFAULT_PERIOD_START = 1L;
    private static final Long UPDATED_PERIOD_START = 2L;

    private static final String DEFAULT_PRICE = "AAAAAAAAAA";
    private static final String UPDATED_PRICE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_PRORATION = false;
    private static final Boolean UPDATED_PRORATION = true;

    private static final String DEFAULT_PRORATION_DETAILS = "AAAAAAAAAA";
    private static final String UPDATED_PRORATION_DETAILS = "BBBBBBBBBB";

    private static final Integer DEFAULT_QUANTITY = 1;
    private static final Integer UPDATED_QUANTITY = 2;

    private static final String DEFAULT_SUBSCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_SUBSCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_TAX_AMOUNTS = "AAAAAAAAAA";
    private static final String UPDATED_TAX_AMOUNTS = "BBBBBBBBBB";

    private static final String DEFAULT_TAX_RATES = "AAAAAAAAAA";
    private static final String UPDATED_TAX_RATES = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_UNIT_AMOUNT_EXCLUDING_TAX = "AAAAAAAAAA";
    private static final String UPDATED_UNIT_AMOUNT_EXCLUDING_TAX = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/line-items";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private LineItemRepository lineItemRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLineItemMockMvc;

    private LineItem lineItem;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LineItem createEntity(EntityManager em) {
        LineItem lineItem = new LineItem()
            .object(DEFAULT_OBJECT)
            .amount(DEFAULT_AMOUNT)
            .amountExcludingTax(DEFAULT_AMOUNT_EXCLUDING_TAX)
            .currency(DEFAULT_CURRENCY)
            .description(DEFAULT_DESCRIPTION)
            .discountAmounts(DEFAULT_DISCOUNT_AMOUNTS)
            .discountable(DEFAULT_DISCOUNTABLE)
            .discounts(DEFAULT_DISCOUNTS)
            .invoiceItem(DEFAULT_INVOICE_ITEM)
            .livemode(DEFAULT_LIVEMODE)
            .metadata(DEFAULT_METADATA)
            .periodEnd(DEFAULT_PERIOD_END)
            .periodStart(DEFAULT_PERIOD_START)
            .price(DEFAULT_PRICE)
            .proration(DEFAULT_PRORATION)
            .prorationDetails(DEFAULT_PRORATION_DETAILS)
            .quantity(DEFAULT_QUANTITY)
            .subscription(DEFAULT_SUBSCRIPTION)
            .taxAmounts(DEFAULT_TAX_AMOUNTS)
            .taxRates(DEFAULT_TAX_RATES)
            .type(DEFAULT_TYPE)
            .unitAmountExcludingTax(DEFAULT_UNIT_AMOUNT_EXCLUDING_TAX);
        return lineItem;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LineItem createUpdatedEntity(EntityManager em) {
        LineItem lineItem = new LineItem()
            .object(UPDATED_OBJECT)
            .amount(UPDATED_AMOUNT)
            .amountExcludingTax(UPDATED_AMOUNT_EXCLUDING_TAX)
            .currency(UPDATED_CURRENCY)
            .description(UPDATED_DESCRIPTION)
            .discountAmounts(UPDATED_DISCOUNT_AMOUNTS)
            .discountable(UPDATED_DISCOUNTABLE)
            .discounts(UPDATED_DISCOUNTS)
            .invoiceItem(UPDATED_INVOICE_ITEM)
            .livemode(UPDATED_LIVEMODE)
            .metadata(UPDATED_METADATA)
            .periodEnd(UPDATED_PERIOD_END)
            .periodStart(UPDATED_PERIOD_START)
            .price(UPDATED_PRICE)
            .proration(UPDATED_PRORATION)
            .prorationDetails(UPDATED_PRORATION_DETAILS)
            .quantity(UPDATED_QUANTITY)
            .subscription(UPDATED_SUBSCRIPTION)
            .taxAmounts(UPDATED_TAX_AMOUNTS)
            .taxRates(UPDATED_TAX_RATES)
            .type(UPDATED_TYPE)
            .unitAmountExcludingTax(UPDATED_UNIT_AMOUNT_EXCLUDING_TAX);
        return lineItem;
    }

    @BeforeEach
    public void initTest() {
        lineItem = createEntity(em);
    }

    @Test
    @Transactional
    void createLineItem() throws Exception {
        int databaseSizeBeforeCreate = lineItemRepository.findAll().size();
        // Create the LineItem
        restLineItemMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(lineItem)))
            .andExpect(status().isCreated());

        // Validate the LineItem in the database
        List<LineItem> lineItemList = lineItemRepository.findAll();
        assertThat(lineItemList).hasSize(databaseSizeBeforeCreate + 1);
        LineItem testLineItem = lineItemList.get(lineItemList.size() - 1);
        assertThat(testLineItem.getObject()).isEqualTo(DEFAULT_OBJECT);
        assertThat(testLineItem.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testLineItem.getAmountExcludingTax()).isEqualTo(DEFAULT_AMOUNT_EXCLUDING_TAX);
        assertThat(testLineItem.getCurrency()).isEqualTo(DEFAULT_CURRENCY);
        assertThat(testLineItem.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testLineItem.getDiscountAmounts()).isEqualTo(DEFAULT_DISCOUNT_AMOUNTS);
        assertThat(testLineItem.getDiscountable()).isEqualTo(DEFAULT_DISCOUNTABLE);
        assertThat(testLineItem.getDiscounts()).isEqualTo(DEFAULT_DISCOUNTS);
        assertThat(testLineItem.getInvoiceItem()).isEqualTo(DEFAULT_INVOICE_ITEM);
        assertThat(testLineItem.getLivemode()).isEqualTo(DEFAULT_LIVEMODE);
        assertThat(testLineItem.getMetadata()).isEqualTo(DEFAULT_METADATA);
        assertThat(testLineItem.getPeriodEnd()).isEqualTo(DEFAULT_PERIOD_END);
        assertThat(testLineItem.getPeriodStart()).isEqualTo(DEFAULT_PERIOD_START);
        assertThat(testLineItem.getPrice()).isEqualTo(DEFAULT_PRICE);
        assertThat(testLineItem.getProration()).isEqualTo(DEFAULT_PRORATION);
        assertThat(testLineItem.getProrationDetails()).isEqualTo(DEFAULT_PRORATION_DETAILS);
        assertThat(testLineItem.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
        assertThat(testLineItem.getSubscription()).isEqualTo(DEFAULT_SUBSCRIPTION);
        assertThat(testLineItem.getTaxAmounts()).isEqualTo(DEFAULT_TAX_AMOUNTS);
        assertThat(testLineItem.getTaxRates()).isEqualTo(DEFAULT_TAX_RATES);
        assertThat(testLineItem.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testLineItem.getUnitAmountExcludingTax()).isEqualTo(DEFAULT_UNIT_AMOUNT_EXCLUDING_TAX);
    }

    @Test
    @Transactional
    void createLineItemWithExistingId() throws Exception {
        // Create the LineItem with an existing ID
        lineItem.setId("existing_id");

        int databaseSizeBeforeCreate = lineItemRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restLineItemMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(lineItem)))
            .andExpect(status().isBadRequest());

        // Validate the LineItem in the database
        List<LineItem> lineItemList = lineItemRepository.findAll();
        assertThat(lineItemList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllLineItems() throws Exception {
        // Initialize the database
        lineItem.setId(UUID.randomUUID().toString());
        lineItemRepository.saveAndFlush(lineItem);

        // Get all the lineItemList
        restLineItemMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(lineItem.getId())))
            .andExpect(jsonPath("$.[*].object").value(hasItem(DEFAULT_OBJECT)))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.intValue())))
            .andExpect(jsonPath("$.[*].amountExcludingTax").value(hasItem(DEFAULT_AMOUNT_EXCLUDING_TAX.intValue())))
            .andExpect(jsonPath("$.[*].currency").value(hasItem(DEFAULT_CURRENCY)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].discountAmounts").value(hasItem(DEFAULT_DISCOUNT_AMOUNTS)))
            .andExpect(jsonPath("$.[*].discountable").value(hasItem(DEFAULT_DISCOUNTABLE.booleanValue())))
            .andExpect(jsonPath("$.[*].discounts").value(hasItem(DEFAULT_DISCOUNTS)))
            .andExpect(jsonPath("$.[*].invoiceItem").value(hasItem(DEFAULT_INVOICE_ITEM)))
            .andExpect(jsonPath("$.[*].livemode").value(hasItem(DEFAULT_LIVEMODE.booleanValue())))
            .andExpect(jsonPath("$.[*].metadata").value(hasItem(DEFAULT_METADATA)))
            .andExpect(jsonPath("$.[*].periodEnd").value(hasItem(DEFAULT_PERIOD_END.intValue())))
            .andExpect(jsonPath("$.[*].periodStart").value(hasItem(DEFAULT_PERIOD_START.intValue())))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE)))
            .andExpect(jsonPath("$.[*].proration").value(hasItem(DEFAULT_PRORATION.booleanValue())))
            .andExpect(jsonPath("$.[*].prorationDetails").value(hasItem(DEFAULT_PRORATION_DETAILS)))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY)))
            .andExpect(jsonPath("$.[*].subscription").value(hasItem(DEFAULT_SUBSCRIPTION)))
            .andExpect(jsonPath("$.[*].taxAmounts").value(hasItem(DEFAULT_TAX_AMOUNTS)))
            .andExpect(jsonPath("$.[*].taxRates").value(hasItem(DEFAULT_TAX_RATES)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].unitAmountExcludingTax").value(hasItem(DEFAULT_UNIT_AMOUNT_EXCLUDING_TAX)));
    }

    @Test
    @Transactional
    void getLineItem() throws Exception {
        // Initialize the database
        lineItem.setId(UUID.randomUUID().toString());
        lineItemRepository.saveAndFlush(lineItem);

        // Get the lineItem
        restLineItemMockMvc
            .perform(get(ENTITY_API_URL_ID, lineItem.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(lineItem.getId()))
            .andExpect(jsonPath("$.object").value(DEFAULT_OBJECT))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.intValue()))
            .andExpect(jsonPath("$.amountExcludingTax").value(DEFAULT_AMOUNT_EXCLUDING_TAX.intValue()))
            .andExpect(jsonPath("$.currency").value(DEFAULT_CURRENCY))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.discountAmounts").value(DEFAULT_DISCOUNT_AMOUNTS))
            .andExpect(jsonPath("$.discountable").value(DEFAULT_DISCOUNTABLE.booleanValue()))
            .andExpect(jsonPath("$.discounts").value(DEFAULT_DISCOUNTS))
            .andExpect(jsonPath("$.invoiceItem").value(DEFAULT_INVOICE_ITEM))
            .andExpect(jsonPath("$.livemode").value(DEFAULT_LIVEMODE.booleanValue()))
            .andExpect(jsonPath("$.metadata").value(DEFAULT_METADATA))
            .andExpect(jsonPath("$.periodEnd").value(DEFAULT_PERIOD_END.intValue()))
            .andExpect(jsonPath("$.periodStart").value(DEFAULT_PERIOD_START.intValue()))
            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE))
            .andExpect(jsonPath("$.proration").value(DEFAULT_PRORATION.booleanValue()))
            .andExpect(jsonPath("$.prorationDetails").value(DEFAULT_PRORATION_DETAILS))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY))
            .andExpect(jsonPath("$.subscription").value(DEFAULT_SUBSCRIPTION))
            .andExpect(jsonPath("$.taxAmounts").value(DEFAULT_TAX_AMOUNTS))
            .andExpect(jsonPath("$.taxRates").value(DEFAULT_TAX_RATES))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.unitAmountExcludingTax").value(DEFAULT_UNIT_AMOUNT_EXCLUDING_TAX));
    }

    @Test
    @Transactional
    void getNonExistingLineItem() throws Exception {
        // Get the lineItem
        restLineItemMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingLineItem() throws Exception {
        // Initialize the database
        lineItem.setId(UUID.randomUUID().toString());
        lineItemRepository.saveAndFlush(lineItem);

        int databaseSizeBeforeUpdate = lineItemRepository.findAll().size();

        // Update the lineItem
        LineItem updatedLineItem = lineItemRepository.findById(lineItem.getId()).get();
        // Disconnect from session so that the updates on updatedLineItem are not directly saved in db
        em.detach(updatedLineItem);
        updatedLineItem
            .object(UPDATED_OBJECT)
            .amount(UPDATED_AMOUNT)
            .amountExcludingTax(UPDATED_AMOUNT_EXCLUDING_TAX)
            .currency(UPDATED_CURRENCY)
            .description(UPDATED_DESCRIPTION)
            .discountAmounts(UPDATED_DISCOUNT_AMOUNTS)
            .discountable(UPDATED_DISCOUNTABLE)
            .discounts(UPDATED_DISCOUNTS)
            .invoiceItem(UPDATED_INVOICE_ITEM)
            .livemode(UPDATED_LIVEMODE)
            .metadata(UPDATED_METADATA)
            .periodEnd(UPDATED_PERIOD_END)
            .periodStart(UPDATED_PERIOD_START)
            .price(UPDATED_PRICE)
            .proration(UPDATED_PRORATION)
            .prorationDetails(UPDATED_PRORATION_DETAILS)
            .quantity(UPDATED_QUANTITY)
            .subscription(UPDATED_SUBSCRIPTION)
            .taxAmounts(UPDATED_TAX_AMOUNTS)
            .taxRates(UPDATED_TAX_RATES)
            .type(UPDATED_TYPE)
            .unitAmountExcludingTax(UPDATED_UNIT_AMOUNT_EXCLUDING_TAX);

        restLineItemMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedLineItem.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedLineItem))
            )
            .andExpect(status().isOk());

        // Validate the LineItem in the database
        List<LineItem> lineItemList = lineItemRepository.findAll();
        assertThat(lineItemList).hasSize(databaseSizeBeforeUpdate);
        LineItem testLineItem = lineItemList.get(lineItemList.size() - 1);
        assertThat(testLineItem.getObject()).isEqualTo(UPDATED_OBJECT);
        assertThat(testLineItem.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testLineItem.getAmountExcludingTax()).isEqualTo(UPDATED_AMOUNT_EXCLUDING_TAX);
        assertThat(testLineItem.getCurrency()).isEqualTo(UPDATED_CURRENCY);
        assertThat(testLineItem.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testLineItem.getDiscountAmounts()).isEqualTo(UPDATED_DISCOUNT_AMOUNTS);
        assertThat(testLineItem.getDiscountable()).isEqualTo(UPDATED_DISCOUNTABLE);
        assertThat(testLineItem.getDiscounts()).isEqualTo(UPDATED_DISCOUNTS);
        assertThat(testLineItem.getInvoiceItem()).isEqualTo(UPDATED_INVOICE_ITEM);
        assertThat(testLineItem.getLivemode()).isEqualTo(UPDATED_LIVEMODE);
        assertThat(testLineItem.getMetadata()).isEqualTo(UPDATED_METADATA);
        assertThat(testLineItem.getPeriodEnd()).isEqualTo(UPDATED_PERIOD_END);
        assertThat(testLineItem.getPeriodStart()).isEqualTo(UPDATED_PERIOD_START);
        assertThat(testLineItem.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testLineItem.getProration()).isEqualTo(UPDATED_PRORATION);
        assertThat(testLineItem.getProrationDetails()).isEqualTo(UPDATED_PRORATION_DETAILS);
        assertThat(testLineItem.getQuantity()).isEqualTo(UPDATED_QUANTITY);
        assertThat(testLineItem.getSubscription()).isEqualTo(UPDATED_SUBSCRIPTION);
        assertThat(testLineItem.getTaxAmounts()).isEqualTo(UPDATED_TAX_AMOUNTS);
        assertThat(testLineItem.getTaxRates()).isEqualTo(UPDATED_TAX_RATES);
        assertThat(testLineItem.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testLineItem.getUnitAmountExcludingTax()).isEqualTo(UPDATED_UNIT_AMOUNT_EXCLUDING_TAX);
    }

    @Test
    @Transactional
    void putNonExistingLineItem() throws Exception {
        int databaseSizeBeforeUpdate = lineItemRepository.findAll().size();
        lineItem.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLineItemMockMvc
            .perform(
                put(ENTITY_API_URL_ID, lineItem.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(lineItem))
            )
            .andExpect(status().isBadRequest());

        // Validate the LineItem in the database
        List<LineItem> lineItemList = lineItemRepository.findAll();
        assertThat(lineItemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchLineItem() throws Exception {
        int databaseSizeBeforeUpdate = lineItemRepository.findAll().size();
        lineItem.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLineItemMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(lineItem))
            )
            .andExpect(status().isBadRequest());

        // Validate the LineItem in the database
        List<LineItem> lineItemList = lineItemRepository.findAll();
        assertThat(lineItemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamLineItem() throws Exception {
        int databaseSizeBeforeUpdate = lineItemRepository.findAll().size();
        lineItem.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLineItemMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(lineItem)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the LineItem in the database
        List<LineItem> lineItemList = lineItemRepository.findAll();
        assertThat(lineItemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateLineItemWithPatch() throws Exception {
        // Initialize the database
        lineItem.setId(UUID.randomUUID().toString());
        lineItemRepository.saveAndFlush(lineItem);

        int databaseSizeBeforeUpdate = lineItemRepository.findAll().size();

        // Update the lineItem using partial update
        LineItem partialUpdatedLineItem = new LineItem();
        partialUpdatedLineItem.setId(lineItem.getId());

        partialUpdatedLineItem
            .description(UPDATED_DESCRIPTION)
            .discountAmounts(UPDATED_DISCOUNT_AMOUNTS)
            .discounts(UPDATED_DISCOUNTS)
            .invoiceItem(UPDATED_INVOICE_ITEM)
            .livemode(UPDATED_LIVEMODE)
            .periodEnd(UPDATED_PERIOD_END)
            .periodStart(UPDATED_PERIOD_START)
            .price(UPDATED_PRICE)
            .proration(UPDATED_PRORATION)
            .prorationDetails(UPDATED_PRORATION_DETAILS)
            .quantity(UPDATED_QUANTITY);

        restLineItemMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLineItem.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedLineItem))
            )
            .andExpect(status().isOk());

        // Validate the LineItem in the database
        List<LineItem> lineItemList = lineItemRepository.findAll();
        assertThat(lineItemList).hasSize(databaseSizeBeforeUpdate);
        LineItem testLineItem = lineItemList.get(lineItemList.size() - 1);
        assertThat(testLineItem.getObject()).isEqualTo(DEFAULT_OBJECT);
        assertThat(testLineItem.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testLineItem.getAmountExcludingTax()).isEqualTo(DEFAULT_AMOUNT_EXCLUDING_TAX);
        assertThat(testLineItem.getCurrency()).isEqualTo(DEFAULT_CURRENCY);
        assertThat(testLineItem.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testLineItem.getDiscountAmounts()).isEqualTo(UPDATED_DISCOUNT_AMOUNTS);
        assertThat(testLineItem.getDiscountable()).isEqualTo(DEFAULT_DISCOUNTABLE);
        assertThat(testLineItem.getDiscounts()).isEqualTo(UPDATED_DISCOUNTS);
        assertThat(testLineItem.getInvoiceItem()).isEqualTo(UPDATED_INVOICE_ITEM);
        assertThat(testLineItem.getLivemode()).isEqualTo(UPDATED_LIVEMODE);
        assertThat(testLineItem.getMetadata()).isEqualTo(DEFAULT_METADATA);
        assertThat(testLineItem.getPeriodEnd()).isEqualTo(UPDATED_PERIOD_END);
        assertThat(testLineItem.getPeriodStart()).isEqualTo(UPDATED_PERIOD_START);
        assertThat(testLineItem.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testLineItem.getProration()).isEqualTo(UPDATED_PRORATION);
        assertThat(testLineItem.getProrationDetails()).isEqualTo(UPDATED_PRORATION_DETAILS);
        assertThat(testLineItem.getQuantity()).isEqualTo(UPDATED_QUANTITY);
        assertThat(testLineItem.getSubscription()).isEqualTo(DEFAULT_SUBSCRIPTION);
        assertThat(testLineItem.getTaxAmounts()).isEqualTo(DEFAULT_TAX_AMOUNTS);
        assertThat(testLineItem.getTaxRates()).isEqualTo(DEFAULT_TAX_RATES);
        assertThat(testLineItem.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testLineItem.getUnitAmountExcludingTax()).isEqualTo(DEFAULT_UNIT_AMOUNT_EXCLUDING_TAX);
    }

    @Test
    @Transactional
    void fullUpdateLineItemWithPatch() throws Exception {
        // Initialize the database
        lineItem.setId(UUID.randomUUID().toString());
        lineItemRepository.saveAndFlush(lineItem);

        int databaseSizeBeforeUpdate = lineItemRepository.findAll().size();

        // Update the lineItem using partial update
        LineItem partialUpdatedLineItem = new LineItem();
        partialUpdatedLineItem.setId(lineItem.getId());

        partialUpdatedLineItem
            .object(UPDATED_OBJECT)
            .amount(UPDATED_AMOUNT)
            .amountExcludingTax(UPDATED_AMOUNT_EXCLUDING_TAX)
            .currency(UPDATED_CURRENCY)
            .description(UPDATED_DESCRIPTION)
            .discountAmounts(UPDATED_DISCOUNT_AMOUNTS)
            .discountable(UPDATED_DISCOUNTABLE)
            .discounts(UPDATED_DISCOUNTS)
            .invoiceItem(UPDATED_INVOICE_ITEM)
            .livemode(UPDATED_LIVEMODE)
            .metadata(UPDATED_METADATA)
            .periodEnd(UPDATED_PERIOD_END)
            .periodStart(UPDATED_PERIOD_START)
            .price(UPDATED_PRICE)
            .proration(UPDATED_PRORATION)
            .prorationDetails(UPDATED_PRORATION_DETAILS)
            .quantity(UPDATED_QUANTITY)
            .subscription(UPDATED_SUBSCRIPTION)
            .taxAmounts(UPDATED_TAX_AMOUNTS)
            .taxRates(UPDATED_TAX_RATES)
            .type(UPDATED_TYPE)
            .unitAmountExcludingTax(UPDATED_UNIT_AMOUNT_EXCLUDING_TAX);

        restLineItemMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLineItem.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedLineItem))
            )
            .andExpect(status().isOk());

        // Validate the LineItem in the database
        List<LineItem> lineItemList = lineItemRepository.findAll();
        assertThat(lineItemList).hasSize(databaseSizeBeforeUpdate);
        LineItem testLineItem = lineItemList.get(lineItemList.size() - 1);
        assertThat(testLineItem.getObject()).isEqualTo(UPDATED_OBJECT);
        assertThat(testLineItem.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testLineItem.getAmountExcludingTax()).isEqualTo(UPDATED_AMOUNT_EXCLUDING_TAX);
        assertThat(testLineItem.getCurrency()).isEqualTo(UPDATED_CURRENCY);
        assertThat(testLineItem.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testLineItem.getDiscountAmounts()).isEqualTo(UPDATED_DISCOUNT_AMOUNTS);
        assertThat(testLineItem.getDiscountable()).isEqualTo(UPDATED_DISCOUNTABLE);
        assertThat(testLineItem.getDiscounts()).isEqualTo(UPDATED_DISCOUNTS);
        assertThat(testLineItem.getInvoiceItem()).isEqualTo(UPDATED_INVOICE_ITEM);
        assertThat(testLineItem.getLivemode()).isEqualTo(UPDATED_LIVEMODE);
        assertThat(testLineItem.getMetadata()).isEqualTo(UPDATED_METADATA);
        assertThat(testLineItem.getPeriodEnd()).isEqualTo(UPDATED_PERIOD_END);
        assertThat(testLineItem.getPeriodStart()).isEqualTo(UPDATED_PERIOD_START);
        assertThat(testLineItem.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testLineItem.getProration()).isEqualTo(UPDATED_PRORATION);
        assertThat(testLineItem.getProrationDetails()).isEqualTo(UPDATED_PRORATION_DETAILS);
        assertThat(testLineItem.getQuantity()).isEqualTo(UPDATED_QUANTITY);
        assertThat(testLineItem.getSubscription()).isEqualTo(UPDATED_SUBSCRIPTION);
        assertThat(testLineItem.getTaxAmounts()).isEqualTo(UPDATED_TAX_AMOUNTS);
        assertThat(testLineItem.getTaxRates()).isEqualTo(UPDATED_TAX_RATES);
        assertThat(testLineItem.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testLineItem.getUnitAmountExcludingTax()).isEqualTo(UPDATED_UNIT_AMOUNT_EXCLUDING_TAX);
    }

    @Test
    @Transactional
    void patchNonExistingLineItem() throws Exception {
        int databaseSizeBeforeUpdate = lineItemRepository.findAll().size();
        lineItem.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLineItemMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, lineItem.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(lineItem))
            )
            .andExpect(status().isBadRequest());

        // Validate the LineItem in the database
        List<LineItem> lineItemList = lineItemRepository.findAll();
        assertThat(lineItemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchLineItem() throws Exception {
        int databaseSizeBeforeUpdate = lineItemRepository.findAll().size();
        lineItem.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLineItemMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(lineItem))
            )
            .andExpect(status().isBadRequest());

        // Validate the LineItem in the database
        List<LineItem> lineItemList = lineItemRepository.findAll();
        assertThat(lineItemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamLineItem() throws Exception {
        int databaseSizeBeforeUpdate = lineItemRepository.findAll().size();
        lineItem.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLineItemMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(lineItem)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the LineItem in the database
        List<LineItem> lineItemList = lineItemRepository.findAll();
        assertThat(lineItemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteLineItem() throws Exception {
        // Initialize the database
        lineItem.setId(UUID.randomUUID().toString());
        lineItemRepository.saveAndFlush(lineItem);

        int databaseSizeBeforeDelete = lineItemRepository.findAll().size();

        // Delete the lineItem
        restLineItemMockMvc
            .perform(delete(ENTITY_API_URL_ID, lineItem.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<LineItem> lineItemList = lineItemRepository.findAll();
        assertThat(lineItemList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
