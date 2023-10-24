package com.rafaros.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.rafaros.IntegrationTest;
import com.rafaros.domain.Invoice;
import com.rafaros.repository.InvoiceRepository;
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
 * Integration tests for the {@link InvoiceResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class InvoiceResourceIT {

    private static final String DEFAULT_OBJECT = "AAAAAAAAAA";
    private static final String UPDATED_OBJECT = "BBBBBBBBBB";

    private static final String DEFAULT_ACCOUNT_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNT_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_ACCOUNT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ACCOUNT_TAX_IDS = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNT_TAX_IDS = "BBBBBBBBBB";

    private static final Long DEFAULT_AMOUNT_DUE = 1L;
    private static final Long UPDATED_AMOUNT_DUE = 2L;

    private static final Long DEFAULT_AMOUNT_PAID = 1L;
    private static final Long UPDATED_AMOUNT_PAID = 2L;

    private static final Long DEFAULT_AMOUNT_REMAINING = 1L;
    private static final Long UPDATED_AMOUNT_REMAINING = 2L;

    private static final Long DEFAULT_AMOUNT_SHIPPING = 1L;
    private static final Long UPDATED_AMOUNT_SHIPPING = 2L;

    private static final String DEFAULT_APPLICATION = "AAAAAAAAAA";
    private static final String UPDATED_APPLICATION = "BBBBBBBBBB";

    private static final Long DEFAULT_APPLICATION_FEE_AMOUNT = 1L;
    private static final Long UPDATED_APPLICATION_FEE_AMOUNT = 2L;

    private static final Integer DEFAULT_ATTEMPT_COUNT = 1;
    private static final Integer UPDATED_ATTEMPT_COUNT = 2;

    private static final Boolean DEFAULT_ATTEMPTED = false;
    private static final Boolean UPDATED_ATTEMPTED = true;

    private static final Boolean DEFAULT_AUTO_ADVANCE = false;
    private static final Boolean UPDATED_AUTO_ADVANCE = true;

    private static final String DEFAULT_BILLING_REASON = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_REASON = "BBBBBBBBBB";

    private static final String DEFAULT_CHARGE = "AAAAAAAAAA";
    private static final String UPDATED_CHARGE = "BBBBBBBBBB";

    private static final String DEFAULT_COLLECTION_METHOD = "AAAAAAAAAA";
    private static final String UPDATED_COLLECTION_METHOD = "BBBBBBBBBB";

    private static final Long DEFAULT_CREATED = 1L;
    private static final Long UPDATED_CREATED = 2L;

    private static final String DEFAULT_CURRENCY = "AAAAAAAAAA";
    private static final String UPDATED_CURRENCY = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOM_FIELDS = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOM_FIELDS = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMER_STRING_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_STRING_VALUE = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMER_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMER_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMER_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMER_SHIPPING = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_SHIPPING = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMER_TAX_EXEMPT = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_TAX_EXEMPT = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMER_TAX_IDS = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_TAX_IDS = "BBBBBBBBBB";

    private static final String DEFAULT_DEFAULT_PAYMENT_METHOD = "AAAAAAAAAA";
    private static final String UPDATED_DEFAULT_PAYMENT_METHOD = "BBBBBBBBBB";

    private static final String DEFAULT_DEFAULT_SOURCE = "AAAAAAAAAA";
    private static final String UPDATED_DEFAULT_SOURCE = "BBBBBBBBBB";

    private static final String DEFAULT_DEFAULT_TAX_RATES = "AAAAAAAAAA";
    private static final String UPDATED_DEFAULT_TAX_RATES = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_DISCOUNT = "AAAAAAAAAA";
    private static final String UPDATED_DISCOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_DISCOUNTS = "AAAAAAAAAA";
    private static final String UPDATED_DISCOUNTS = "BBBBBBBBBB";

    private static final Long DEFAULT_DUE_DATE = 1L;
    private static final Long UPDATED_DUE_DATE = 2L;

    private static final Long DEFAULT_EFFECTIVE_AT = 1L;
    private static final Long UPDATED_EFFECTIVE_AT = 2L;

    private static final Long DEFAULT_ENDING_BALANCE = 1L;
    private static final Long UPDATED_ENDING_BALANCE = 2L;

    private static final String DEFAULT_FOOTER = "AAAAAAAAAA";
    private static final String UPDATED_FOOTER = "BBBBBBBBBB";

    private static final String DEFAULT_FROM_INVOICE = "AAAAAAAAAA";
    private static final String UPDATED_FROM_INVOICE = "BBBBBBBBBB";

    private static final String DEFAULT_HOSTED_INVOICE_URL = "AAAAAAAAAA";
    private static final String UPDATED_HOSTED_INVOICE_URL = "BBBBBBBBBB";

    private static final String DEFAULT_INVOICE_PDF = "AAAAAAAAAA";
    private static final String UPDATED_INVOICE_PDF = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_FINALIZATION_ERROR = "AAAAAAAAAA";
    private static final String UPDATED_LAST_FINALIZATION_ERROR = "BBBBBBBBBB";

    private static final String DEFAULT_LATEST_REVISION = "AAAAAAAAAA";
    private static final String UPDATED_LATEST_REVISION = "BBBBBBBBBB";

    private static final Boolean DEFAULT_LIVEMODE = false;
    private static final Boolean UPDATED_LIVEMODE = true;

    private static final String DEFAULT_METADATA = "AAAAAAAAAA";
    private static final String UPDATED_METADATA = "BBBBBBBBBB";

    private static final Long DEFAULT_NEXT_PAYMENT_ATTEMPT = 1L;
    private static final Long UPDATED_NEXT_PAYMENT_ATTEMPT = 2L;

    private static final String DEFAULT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_ON_BEHALF_OF = "AAAAAAAAAA";
    private static final String UPDATED_ON_BEHALF_OF = "BBBBBBBBBB";

    private static final Boolean DEFAULT_PAID = false;
    private static final Boolean UPDATED_PAID = true;

    private static final Boolean DEFAULT_PAID_OUT_OF_BAND = false;
    private static final Boolean UPDATED_PAID_OUT_OF_BAND = true;

    private static final String DEFAULT_PAYMENT_INTENT = "AAAAAAAAAA";
    private static final String UPDATED_PAYMENT_INTENT = "BBBBBBBBBB";

    private static final String DEFAULT_PAYMENT_SETTINGS = "AAAAAAAAAA";
    private static final String UPDATED_PAYMENT_SETTINGS = "BBBBBBBBBB";

    private static final Long DEFAULT_PERIOD_END = 1L;
    private static final Long UPDATED_PERIOD_END = 2L;

    private static final Long DEFAULT_PERIOD_START = 1L;
    private static final Long UPDATED_PERIOD_START = 2L;

    private static final Long DEFAULT_POST_PAYMENT_CREDIT_NOTES_AMOUNT = 1L;
    private static final Long UPDATED_POST_PAYMENT_CREDIT_NOTES_AMOUNT = 2L;

    private static final Long DEFAULT_PRE_PAYMENT_CREDIT_NOTES_AMOUNT = 1L;
    private static final Long UPDATED_PRE_PAYMENT_CREDIT_NOTES_AMOUNT = 2L;

    private static final String DEFAULT_QUOTE = "AAAAAAAAAA";
    private static final String UPDATED_QUOTE = "BBBBBBBBBB";

    private static final String DEFAULT_RECEIPT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_RECEIPT_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_RENDERING = "AAAAAAAAAA";
    private static final String UPDATED_RENDERING = "BBBBBBBBBB";

    private static final String DEFAULT_RENDERING_OPTIONS = "AAAAAAAAAA";
    private static final String UPDATED_RENDERING_OPTIONS = "BBBBBBBBBB";

    private static final Long DEFAULT_SHIPPING_COST = 1L;
    private static final Long UPDATED_SHIPPING_COST = 2L;

    private static final String DEFAULT_SHIPPING_DETAILS = "AAAAAAAAAA";
    private static final String UPDATED_SHIPPING_DETAILS = "BBBBBBBBBB";

    private static final Long DEFAULT_STARTING_BALANCE = 1L;
    private static final Long UPDATED_STARTING_BALANCE = 2L;

    private static final String DEFAULT_STATEMENT_DESCRIPTOR = "AAAAAAAAAA";
    private static final String UPDATED_STATEMENT_DESCRIPTOR = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS_TRANSITIONS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS_TRANSITIONS = "BBBBBBBBBB";

    private static final String DEFAULT_SUBSCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_SUBSCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_SUBSCRIPTION_DETAILS = "AAAAAAAAAA";
    private static final String UPDATED_SUBSCRIPTION_DETAILS = "BBBBBBBBBB";

    private static final Long DEFAULT_SUBTOTAL = 1L;
    private static final Long UPDATED_SUBTOTAL = 2L;

    private static final Long DEFAULT_SUBTOTAL_EXCLUDING_TAX = 1L;
    private static final Long UPDATED_SUBTOTAL_EXCLUDING_TAX = 2L;

    private static final String DEFAULT_TAX = "AAAAAAAAAA";
    private static final String UPDATED_TAX = "BBBBBBBBBB";

    private static final String DEFAULT_TEST_CLOCK = "AAAAAAAAAA";
    private static final String UPDATED_TEST_CLOCK = "BBBBBBBBBB";

    private static final Long DEFAULT_TOTAL = 1L;
    private static final Long UPDATED_TOTAL = 2L;

    private static final String DEFAULT_TOTAL_DISCOUNT_AMOUNTS = "AAAAAAAAAA";
    private static final String UPDATED_TOTAL_DISCOUNT_AMOUNTS = "BBBBBBBBBB";

    private static final Long DEFAULT_TOTAL_EXCLUDING_TAX = 1L;
    private static final Long UPDATED_TOTAL_EXCLUDING_TAX = 2L;

    private static final String DEFAULT_TOTAL_TAX_AMOUNTS = "AAAAAAAAAA";
    private static final String UPDATED_TOTAL_TAX_AMOUNTS = "BBBBBBBBBB";

    private static final String DEFAULT_TRANSFER_DATA = "AAAAAAAAAA";
    private static final String UPDATED_TRANSFER_DATA = "BBBBBBBBBB";

    private static final Long DEFAULT_WEBHOOKS_DELIVERED_AT = 1L;
    private static final Long UPDATED_WEBHOOKS_DELIVERED_AT = 2L;

    private static final String ENTITY_API_URL = "/api/invoices";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInvoiceMockMvc;

    private Invoice invoice;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Invoice createEntity(EntityManager em) {
        Invoice invoice = new Invoice()
            .object(DEFAULT_OBJECT)
            .accountCountry(DEFAULT_ACCOUNT_COUNTRY)
            .accountName(DEFAULT_ACCOUNT_NAME)
            .accountTaxIds(DEFAULT_ACCOUNT_TAX_IDS)
            .amountDue(DEFAULT_AMOUNT_DUE)
            .amountPaid(DEFAULT_AMOUNT_PAID)
            .amountRemaining(DEFAULT_AMOUNT_REMAINING)
            .amountShipping(DEFAULT_AMOUNT_SHIPPING)
            .application(DEFAULT_APPLICATION)
            .applicationFeeAmount(DEFAULT_APPLICATION_FEE_AMOUNT)
            .attemptCount(DEFAULT_ATTEMPT_COUNT)
            .attempted(DEFAULT_ATTEMPTED)
            .autoAdvance(DEFAULT_AUTO_ADVANCE)
            .billingReason(DEFAULT_BILLING_REASON)
            .charge(DEFAULT_CHARGE)
            .collectionMethod(DEFAULT_COLLECTION_METHOD)
            .created(DEFAULT_CREATED)
            .currency(DEFAULT_CURRENCY)
            .customFields(DEFAULT_CUSTOM_FIELDS)
            .customerStringValue(DEFAULT_CUSTOMER_STRING_VALUE)
            .customerAddress(DEFAULT_CUSTOMER_ADDRESS)
            .customerEmail(DEFAULT_CUSTOMER_EMAIL)
            .customerName(DEFAULT_CUSTOMER_NAME)
            .customerPhone(DEFAULT_CUSTOMER_PHONE)
            .customerShipping(DEFAULT_CUSTOMER_SHIPPING)
            .customerTaxExempt(DEFAULT_CUSTOMER_TAX_EXEMPT)
            .customerTaxIds(DEFAULT_CUSTOMER_TAX_IDS)
            .defaultPaymentMethod(DEFAULT_DEFAULT_PAYMENT_METHOD)
            .defaultSource(DEFAULT_DEFAULT_SOURCE)
            .defaultTaxRates(DEFAULT_DEFAULT_TAX_RATES)
            .description(DEFAULT_DESCRIPTION)
            .discount(DEFAULT_DISCOUNT)
            .discounts(DEFAULT_DISCOUNTS)
            .dueDate(DEFAULT_DUE_DATE)
            .effectiveAt(DEFAULT_EFFECTIVE_AT)
            .endingBalance(DEFAULT_ENDING_BALANCE)
            .footer(DEFAULT_FOOTER)
            .fromInvoice(DEFAULT_FROM_INVOICE)
            .hostedInvoiceUrl(DEFAULT_HOSTED_INVOICE_URL)
            .invoicePdf(DEFAULT_INVOICE_PDF)
            .lastFinalizationError(DEFAULT_LAST_FINALIZATION_ERROR)
            .latestRevision(DEFAULT_LATEST_REVISION)
            .livemode(DEFAULT_LIVEMODE)
            .metadata(DEFAULT_METADATA)
            .nextPaymentAttempt(DEFAULT_NEXT_PAYMENT_ATTEMPT)
            .number(DEFAULT_NUMBER)
            .onBehalfOf(DEFAULT_ON_BEHALF_OF)
            .paid(DEFAULT_PAID)
            .paidOutOfBand(DEFAULT_PAID_OUT_OF_BAND)
            .paymentIntent(DEFAULT_PAYMENT_INTENT)
            .paymentSettings(DEFAULT_PAYMENT_SETTINGS)
            .periodEnd(DEFAULT_PERIOD_END)
            .periodStart(DEFAULT_PERIOD_START)
            .postPaymentCreditNotesAmount(DEFAULT_POST_PAYMENT_CREDIT_NOTES_AMOUNT)
            .prePaymentCreditNotesAmount(DEFAULT_PRE_PAYMENT_CREDIT_NOTES_AMOUNT)
            .quote(DEFAULT_QUOTE)
            .receiptNumber(DEFAULT_RECEIPT_NUMBER)
            .rendering(DEFAULT_RENDERING)
            .renderingOptions(DEFAULT_RENDERING_OPTIONS)
            .shippingCost(DEFAULT_SHIPPING_COST)
            .shippingDetails(DEFAULT_SHIPPING_DETAILS)
            .startingBalance(DEFAULT_STARTING_BALANCE)
            .statementDescriptor(DEFAULT_STATEMENT_DESCRIPTOR)
            .status(DEFAULT_STATUS)
            .statusTransitions(DEFAULT_STATUS_TRANSITIONS)
            .subscription(DEFAULT_SUBSCRIPTION)
            .subscriptionDetails(DEFAULT_SUBSCRIPTION_DETAILS)
            .subtotal(DEFAULT_SUBTOTAL)
            .subtotalExcludingTax(DEFAULT_SUBTOTAL_EXCLUDING_TAX)
            .tax(DEFAULT_TAX)
            .testClock(DEFAULT_TEST_CLOCK)
            .total(DEFAULT_TOTAL)
            .totalDiscountAmounts(DEFAULT_TOTAL_DISCOUNT_AMOUNTS)
            .totalExcludingTax(DEFAULT_TOTAL_EXCLUDING_TAX)
            .totalTaxAmounts(DEFAULT_TOTAL_TAX_AMOUNTS)
            .transferData(DEFAULT_TRANSFER_DATA)
            .webhooksDeliveredAt(DEFAULT_WEBHOOKS_DELIVERED_AT);
        return invoice;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Invoice createUpdatedEntity(EntityManager em) {
        Invoice invoice = new Invoice()
            .object(UPDATED_OBJECT)
            .accountCountry(UPDATED_ACCOUNT_COUNTRY)
            .accountName(UPDATED_ACCOUNT_NAME)
            .accountTaxIds(UPDATED_ACCOUNT_TAX_IDS)
            .amountDue(UPDATED_AMOUNT_DUE)
            .amountPaid(UPDATED_AMOUNT_PAID)
            .amountRemaining(UPDATED_AMOUNT_REMAINING)
            .amountShipping(UPDATED_AMOUNT_SHIPPING)
            .application(UPDATED_APPLICATION)
            .applicationFeeAmount(UPDATED_APPLICATION_FEE_AMOUNT)
            .attemptCount(UPDATED_ATTEMPT_COUNT)
            .attempted(UPDATED_ATTEMPTED)
            .autoAdvance(UPDATED_AUTO_ADVANCE)
            .billingReason(UPDATED_BILLING_REASON)
            .charge(UPDATED_CHARGE)
            .collectionMethod(UPDATED_COLLECTION_METHOD)
            .created(UPDATED_CREATED)
            .currency(UPDATED_CURRENCY)
            .customFields(UPDATED_CUSTOM_FIELDS)
            .customerStringValue(UPDATED_CUSTOMER_STRING_VALUE)
            .customerAddress(UPDATED_CUSTOMER_ADDRESS)
            .customerEmail(UPDATED_CUSTOMER_EMAIL)
            .customerName(UPDATED_CUSTOMER_NAME)
            .customerPhone(UPDATED_CUSTOMER_PHONE)
            .customerShipping(UPDATED_CUSTOMER_SHIPPING)
            .customerTaxExempt(UPDATED_CUSTOMER_TAX_EXEMPT)
            .customerTaxIds(UPDATED_CUSTOMER_TAX_IDS)
            .defaultPaymentMethod(UPDATED_DEFAULT_PAYMENT_METHOD)
            .defaultSource(UPDATED_DEFAULT_SOURCE)
            .defaultTaxRates(UPDATED_DEFAULT_TAX_RATES)
            .description(UPDATED_DESCRIPTION)
            .discount(UPDATED_DISCOUNT)
            .discounts(UPDATED_DISCOUNTS)
            .dueDate(UPDATED_DUE_DATE)
            .effectiveAt(UPDATED_EFFECTIVE_AT)
            .endingBalance(UPDATED_ENDING_BALANCE)
            .footer(UPDATED_FOOTER)
            .fromInvoice(UPDATED_FROM_INVOICE)
            .hostedInvoiceUrl(UPDATED_HOSTED_INVOICE_URL)
            .invoicePdf(UPDATED_INVOICE_PDF)
            .lastFinalizationError(UPDATED_LAST_FINALIZATION_ERROR)
            .latestRevision(UPDATED_LATEST_REVISION)
            .livemode(UPDATED_LIVEMODE)
            .metadata(UPDATED_METADATA)
            .nextPaymentAttempt(UPDATED_NEXT_PAYMENT_ATTEMPT)
            .number(UPDATED_NUMBER)
            .onBehalfOf(UPDATED_ON_BEHALF_OF)
            .paid(UPDATED_PAID)
            .paidOutOfBand(UPDATED_PAID_OUT_OF_BAND)
            .paymentIntent(UPDATED_PAYMENT_INTENT)
            .paymentSettings(UPDATED_PAYMENT_SETTINGS)
            .periodEnd(UPDATED_PERIOD_END)
            .periodStart(UPDATED_PERIOD_START)
            .postPaymentCreditNotesAmount(UPDATED_POST_PAYMENT_CREDIT_NOTES_AMOUNT)
            .prePaymentCreditNotesAmount(UPDATED_PRE_PAYMENT_CREDIT_NOTES_AMOUNT)
            .quote(UPDATED_QUOTE)
            .receiptNumber(UPDATED_RECEIPT_NUMBER)
            .rendering(UPDATED_RENDERING)
            .renderingOptions(UPDATED_RENDERING_OPTIONS)
            .shippingCost(UPDATED_SHIPPING_COST)
            .shippingDetails(UPDATED_SHIPPING_DETAILS)
            .startingBalance(UPDATED_STARTING_BALANCE)
            .statementDescriptor(UPDATED_STATEMENT_DESCRIPTOR)
            .status(UPDATED_STATUS)
            .statusTransitions(UPDATED_STATUS_TRANSITIONS)
            .subscription(UPDATED_SUBSCRIPTION)
            .subscriptionDetails(UPDATED_SUBSCRIPTION_DETAILS)
            .subtotal(UPDATED_SUBTOTAL)
            .subtotalExcludingTax(UPDATED_SUBTOTAL_EXCLUDING_TAX)
            .tax(UPDATED_TAX)
            .testClock(UPDATED_TEST_CLOCK)
            .total(UPDATED_TOTAL)
            .totalDiscountAmounts(UPDATED_TOTAL_DISCOUNT_AMOUNTS)
            .totalExcludingTax(UPDATED_TOTAL_EXCLUDING_TAX)
            .totalTaxAmounts(UPDATED_TOTAL_TAX_AMOUNTS)
            .transferData(UPDATED_TRANSFER_DATA)
            .webhooksDeliveredAt(UPDATED_WEBHOOKS_DELIVERED_AT);
        return invoice;
    }

    @BeforeEach
    public void initTest() {
        invoice = createEntity(em);
    }

    @Test
    @Transactional
    void createInvoice() throws Exception {
        int databaseSizeBeforeCreate = invoiceRepository.findAll().size();
        // Create the Invoice
        restInvoiceMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(invoice)))
            .andExpect(status().isCreated());

        // Validate the Invoice in the database
        List<Invoice> invoiceList = invoiceRepository.findAll();
        assertThat(invoiceList).hasSize(databaseSizeBeforeCreate + 1);
        Invoice testInvoice = invoiceList.get(invoiceList.size() - 1);
        assertThat(testInvoice.getObject()).isEqualTo(DEFAULT_OBJECT);
        assertThat(testInvoice.getAccountCountry()).isEqualTo(DEFAULT_ACCOUNT_COUNTRY);
        assertThat(testInvoice.getAccountName()).isEqualTo(DEFAULT_ACCOUNT_NAME);
        assertThat(testInvoice.getAccountTaxIds()).isEqualTo(DEFAULT_ACCOUNT_TAX_IDS);
        assertThat(testInvoice.getAmountDue()).isEqualTo(DEFAULT_AMOUNT_DUE);
        assertThat(testInvoice.getAmountPaid()).isEqualTo(DEFAULT_AMOUNT_PAID);
        assertThat(testInvoice.getAmountRemaining()).isEqualTo(DEFAULT_AMOUNT_REMAINING);
        assertThat(testInvoice.getAmountShipping()).isEqualTo(DEFAULT_AMOUNT_SHIPPING);
        assertThat(testInvoice.getApplication()).isEqualTo(DEFAULT_APPLICATION);
        assertThat(testInvoice.getApplicationFeeAmount()).isEqualTo(DEFAULT_APPLICATION_FEE_AMOUNT);
        assertThat(testInvoice.getAttemptCount()).isEqualTo(DEFAULT_ATTEMPT_COUNT);
        assertThat(testInvoice.getAttempted()).isEqualTo(DEFAULT_ATTEMPTED);
        assertThat(testInvoice.getAutoAdvance()).isEqualTo(DEFAULT_AUTO_ADVANCE);
        assertThat(testInvoice.getBillingReason()).isEqualTo(DEFAULT_BILLING_REASON);
        assertThat(testInvoice.getCharge()).isEqualTo(DEFAULT_CHARGE);
        assertThat(testInvoice.getCollectionMethod()).isEqualTo(DEFAULT_COLLECTION_METHOD);
        assertThat(testInvoice.getCreated()).isEqualTo(DEFAULT_CREATED);
        assertThat(testInvoice.getCurrency()).isEqualTo(DEFAULT_CURRENCY);
        assertThat(testInvoice.getCustomFields()).isEqualTo(DEFAULT_CUSTOM_FIELDS);
        assertThat(testInvoice.getCustomerStringValue()).isEqualTo(DEFAULT_CUSTOMER_STRING_VALUE);
        assertThat(testInvoice.getCustomerAddress()).isEqualTo(DEFAULT_CUSTOMER_ADDRESS);
        assertThat(testInvoice.getCustomerEmail()).isEqualTo(DEFAULT_CUSTOMER_EMAIL);
        assertThat(testInvoice.getCustomerName()).isEqualTo(DEFAULT_CUSTOMER_NAME);
        assertThat(testInvoice.getCustomerPhone()).isEqualTo(DEFAULT_CUSTOMER_PHONE);
        assertThat(testInvoice.getCustomerShipping()).isEqualTo(DEFAULT_CUSTOMER_SHIPPING);
        assertThat(testInvoice.getCustomerTaxExempt()).isEqualTo(DEFAULT_CUSTOMER_TAX_EXEMPT);
        assertThat(testInvoice.getCustomerTaxIds()).isEqualTo(DEFAULT_CUSTOMER_TAX_IDS);
        assertThat(testInvoice.getDefaultPaymentMethod()).isEqualTo(DEFAULT_DEFAULT_PAYMENT_METHOD);
        assertThat(testInvoice.getDefaultSource()).isEqualTo(DEFAULT_DEFAULT_SOURCE);
        assertThat(testInvoice.getDefaultTaxRates()).isEqualTo(DEFAULT_DEFAULT_TAX_RATES);
        assertThat(testInvoice.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testInvoice.getDiscount()).isEqualTo(DEFAULT_DISCOUNT);
        assertThat(testInvoice.getDiscounts()).isEqualTo(DEFAULT_DISCOUNTS);
        assertThat(testInvoice.getDueDate()).isEqualTo(DEFAULT_DUE_DATE);
        assertThat(testInvoice.getEffectiveAt()).isEqualTo(DEFAULT_EFFECTIVE_AT);
        assertThat(testInvoice.getEndingBalance()).isEqualTo(DEFAULT_ENDING_BALANCE);
        assertThat(testInvoice.getFooter()).isEqualTo(DEFAULT_FOOTER);
        assertThat(testInvoice.getFromInvoice()).isEqualTo(DEFAULT_FROM_INVOICE);
        assertThat(testInvoice.getHostedInvoiceUrl()).isEqualTo(DEFAULT_HOSTED_INVOICE_URL);
        assertThat(testInvoice.getInvoicePdf()).isEqualTo(DEFAULT_INVOICE_PDF);
        assertThat(testInvoice.getLastFinalizationError()).isEqualTo(DEFAULT_LAST_FINALIZATION_ERROR);
        assertThat(testInvoice.getLatestRevision()).isEqualTo(DEFAULT_LATEST_REVISION);
        assertThat(testInvoice.getLivemode()).isEqualTo(DEFAULT_LIVEMODE);
        assertThat(testInvoice.getMetadata()).isEqualTo(DEFAULT_METADATA);
        assertThat(testInvoice.getNextPaymentAttempt()).isEqualTo(DEFAULT_NEXT_PAYMENT_ATTEMPT);
        assertThat(testInvoice.getNumber()).isEqualTo(DEFAULT_NUMBER);
        assertThat(testInvoice.getOnBehalfOf()).isEqualTo(DEFAULT_ON_BEHALF_OF);
        assertThat(testInvoice.getPaid()).isEqualTo(DEFAULT_PAID);
        assertThat(testInvoice.getPaidOutOfBand()).isEqualTo(DEFAULT_PAID_OUT_OF_BAND);
        assertThat(testInvoice.getPaymentIntent()).isEqualTo(DEFAULT_PAYMENT_INTENT);
        assertThat(testInvoice.getPaymentSettings()).isEqualTo(DEFAULT_PAYMENT_SETTINGS);
        assertThat(testInvoice.getPeriodEnd()).isEqualTo(DEFAULT_PERIOD_END);
        assertThat(testInvoice.getPeriodStart()).isEqualTo(DEFAULT_PERIOD_START);
        assertThat(testInvoice.getPostPaymentCreditNotesAmount()).isEqualTo(DEFAULT_POST_PAYMENT_CREDIT_NOTES_AMOUNT);
        assertThat(testInvoice.getPrePaymentCreditNotesAmount()).isEqualTo(DEFAULT_PRE_PAYMENT_CREDIT_NOTES_AMOUNT);
        assertThat(testInvoice.getQuote()).isEqualTo(DEFAULT_QUOTE);
        assertThat(testInvoice.getReceiptNumber()).isEqualTo(DEFAULT_RECEIPT_NUMBER);
        assertThat(testInvoice.getRendering()).isEqualTo(DEFAULT_RENDERING);
        assertThat(testInvoice.getRenderingOptions()).isEqualTo(DEFAULT_RENDERING_OPTIONS);
        assertThat(testInvoice.getShippingCost()).isEqualTo(DEFAULT_SHIPPING_COST);
        assertThat(testInvoice.getShippingDetails()).isEqualTo(DEFAULT_SHIPPING_DETAILS);
        assertThat(testInvoice.getStartingBalance()).isEqualTo(DEFAULT_STARTING_BALANCE);
        assertThat(testInvoice.getStatementDescriptor()).isEqualTo(DEFAULT_STATEMENT_DESCRIPTOR);
        assertThat(testInvoice.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testInvoice.getStatusTransitions()).isEqualTo(DEFAULT_STATUS_TRANSITIONS);
        assertThat(testInvoice.getSubscription()).isEqualTo(DEFAULT_SUBSCRIPTION);
        assertThat(testInvoice.getSubscriptionDetails()).isEqualTo(DEFAULT_SUBSCRIPTION_DETAILS);
        assertThat(testInvoice.getSubtotal()).isEqualTo(DEFAULT_SUBTOTAL);
        assertThat(testInvoice.getSubtotalExcludingTax()).isEqualTo(DEFAULT_SUBTOTAL_EXCLUDING_TAX);
        assertThat(testInvoice.getTax()).isEqualTo(DEFAULT_TAX);
        assertThat(testInvoice.getTestClock()).isEqualTo(DEFAULT_TEST_CLOCK);
        assertThat(testInvoice.getTotal()).isEqualTo(DEFAULT_TOTAL);
        assertThat(testInvoice.getTotalDiscountAmounts()).isEqualTo(DEFAULT_TOTAL_DISCOUNT_AMOUNTS);
        assertThat(testInvoice.getTotalExcludingTax()).isEqualTo(DEFAULT_TOTAL_EXCLUDING_TAX);
        assertThat(testInvoice.getTotalTaxAmounts()).isEqualTo(DEFAULT_TOTAL_TAX_AMOUNTS);
        assertThat(testInvoice.getTransferData()).isEqualTo(DEFAULT_TRANSFER_DATA);
        assertThat(testInvoice.getWebhooksDeliveredAt()).isEqualTo(DEFAULT_WEBHOOKS_DELIVERED_AT);
    }

    @Test
    @Transactional
    void createInvoiceWithExistingId() throws Exception {
        // Create the Invoice with an existing ID
        invoice.setId("existing_id");

        int databaseSizeBeforeCreate = invoiceRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restInvoiceMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(invoice)))
            .andExpect(status().isBadRequest());

        // Validate the Invoice in the database
        List<Invoice> invoiceList = invoiceRepository.findAll();
        assertThat(invoiceList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllInvoices() throws Exception {
        // Initialize the database
        invoice.setId(UUID.randomUUID().toString());
        invoiceRepository.saveAndFlush(invoice);

        // Get all the invoiceList
        restInvoiceMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(invoice.getId())))
            .andExpect(jsonPath("$.[*].object").value(hasItem(DEFAULT_OBJECT)))
            .andExpect(jsonPath("$.[*].accountCountry").value(hasItem(DEFAULT_ACCOUNT_COUNTRY)))
            .andExpect(jsonPath("$.[*].accountName").value(hasItem(DEFAULT_ACCOUNT_NAME)))
            .andExpect(jsonPath("$.[*].accountTaxIds").value(hasItem(DEFAULT_ACCOUNT_TAX_IDS)))
            .andExpect(jsonPath("$.[*].amountDue").value(hasItem(DEFAULT_AMOUNT_DUE.intValue())))
            .andExpect(jsonPath("$.[*].amountPaid").value(hasItem(DEFAULT_AMOUNT_PAID.intValue())))
            .andExpect(jsonPath("$.[*].amountRemaining").value(hasItem(DEFAULT_AMOUNT_REMAINING.intValue())))
            .andExpect(jsonPath("$.[*].amountShipping").value(hasItem(DEFAULT_AMOUNT_SHIPPING.intValue())))
            .andExpect(jsonPath("$.[*].application").value(hasItem(DEFAULT_APPLICATION)))
            .andExpect(jsonPath("$.[*].applicationFeeAmount").value(hasItem(DEFAULT_APPLICATION_FEE_AMOUNT.intValue())))
            .andExpect(jsonPath("$.[*].attemptCount").value(hasItem(DEFAULT_ATTEMPT_COUNT)))
            .andExpect(jsonPath("$.[*].attempted").value(hasItem(DEFAULT_ATTEMPTED.booleanValue())))
            .andExpect(jsonPath("$.[*].autoAdvance").value(hasItem(DEFAULT_AUTO_ADVANCE.booleanValue())))
            .andExpect(jsonPath("$.[*].billingReason").value(hasItem(DEFAULT_BILLING_REASON)))
            .andExpect(jsonPath("$.[*].charge").value(hasItem(DEFAULT_CHARGE)))
            .andExpect(jsonPath("$.[*].collectionMethod").value(hasItem(DEFAULT_COLLECTION_METHOD)))
            .andExpect(jsonPath("$.[*].created").value(hasItem(DEFAULT_CREATED.intValue())))
            .andExpect(jsonPath("$.[*].currency").value(hasItem(DEFAULT_CURRENCY)))
            .andExpect(jsonPath("$.[*].customFields").value(hasItem(DEFAULT_CUSTOM_FIELDS)))
            .andExpect(jsonPath("$.[*].customerStringValue").value(hasItem(DEFAULT_CUSTOMER_STRING_VALUE)))
            .andExpect(jsonPath("$.[*].customerAddress").value(hasItem(DEFAULT_CUSTOMER_ADDRESS)))
            .andExpect(jsonPath("$.[*].customerEmail").value(hasItem(DEFAULT_CUSTOMER_EMAIL)))
            .andExpect(jsonPath("$.[*].customerName").value(hasItem(DEFAULT_CUSTOMER_NAME)))
            .andExpect(jsonPath("$.[*].customerPhone").value(hasItem(DEFAULT_CUSTOMER_PHONE)))
            .andExpect(jsonPath("$.[*].customerShipping").value(hasItem(DEFAULT_CUSTOMER_SHIPPING)))
            .andExpect(jsonPath("$.[*].customerTaxExempt").value(hasItem(DEFAULT_CUSTOMER_TAX_EXEMPT)))
            .andExpect(jsonPath("$.[*].customerTaxIds").value(hasItem(DEFAULT_CUSTOMER_TAX_IDS)))
            .andExpect(jsonPath("$.[*].defaultPaymentMethod").value(hasItem(DEFAULT_DEFAULT_PAYMENT_METHOD)))
            .andExpect(jsonPath("$.[*].defaultSource").value(hasItem(DEFAULT_DEFAULT_SOURCE)))
            .andExpect(jsonPath("$.[*].defaultTaxRates").value(hasItem(DEFAULT_DEFAULT_TAX_RATES)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].discount").value(hasItem(DEFAULT_DISCOUNT)))
            .andExpect(jsonPath("$.[*].discounts").value(hasItem(DEFAULT_DISCOUNTS)))
            .andExpect(jsonPath("$.[*].dueDate").value(hasItem(DEFAULT_DUE_DATE.intValue())))
            .andExpect(jsonPath("$.[*].effectiveAt").value(hasItem(DEFAULT_EFFECTIVE_AT.intValue())))
            .andExpect(jsonPath("$.[*].endingBalance").value(hasItem(DEFAULT_ENDING_BALANCE.intValue())))
            .andExpect(jsonPath("$.[*].footer").value(hasItem(DEFAULT_FOOTER)))
            .andExpect(jsonPath("$.[*].fromInvoice").value(hasItem(DEFAULT_FROM_INVOICE)))
            .andExpect(jsonPath("$.[*].hostedInvoiceUrl").value(hasItem(DEFAULT_HOSTED_INVOICE_URL)))
            .andExpect(jsonPath("$.[*].invoicePdf").value(hasItem(DEFAULT_INVOICE_PDF)))
            .andExpect(jsonPath("$.[*].lastFinalizationError").value(hasItem(DEFAULT_LAST_FINALIZATION_ERROR)))
            .andExpect(jsonPath("$.[*].latestRevision").value(hasItem(DEFAULT_LATEST_REVISION)))
            .andExpect(jsonPath("$.[*].livemode").value(hasItem(DEFAULT_LIVEMODE.booleanValue())))
            .andExpect(jsonPath("$.[*].metadata").value(hasItem(DEFAULT_METADATA)))
            .andExpect(jsonPath("$.[*].nextPaymentAttempt").value(hasItem(DEFAULT_NEXT_PAYMENT_ATTEMPT.intValue())))
            .andExpect(jsonPath("$.[*].number").value(hasItem(DEFAULT_NUMBER)))
            .andExpect(jsonPath("$.[*].onBehalfOf").value(hasItem(DEFAULT_ON_BEHALF_OF)))
            .andExpect(jsonPath("$.[*].paid").value(hasItem(DEFAULT_PAID.booleanValue())))
            .andExpect(jsonPath("$.[*].paidOutOfBand").value(hasItem(DEFAULT_PAID_OUT_OF_BAND.booleanValue())))
            .andExpect(jsonPath("$.[*].paymentIntent").value(hasItem(DEFAULT_PAYMENT_INTENT)))
            .andExpect(jsonPath("$.[*].paymentSettings").value(hasItem(DEFAULT_PAYMENT_SETTINGS)))
            .andExpect(jsonPath("$.[*].periodEnd").value(hasItem(DEFAULT_PERIOD_END.intValue())))
            .andExpect(jsonPath("$.[*].periodStart").value(hasItem(DEFAULT_PERIOD_START.intValue())))
            .andExpect(jsonPath("$.[*].postPaymentCreditNotesAmount").value(hasItem(DEFAULT_POST_PAYMENT_CREDIT_NOTES_AMOUNT.intValue())))
            .andExpect(jsonPath("$.[*].prePaymentCreditNotesAmount").value(hasItem(DEFAULT_PRE_PAYMENT_CREDIT_NOTES_AMOUNT.intValue())))
            .andExpect(jsonPath("$.[*].quote").value(hasItem(DEFAULT_QUOTE)))
            .andExpect(jsonPath("$.[*].receiptNumber").value(hasItem(DEFAULT_RECEIPT_NUMBER)))
            .andExpect(jsonPath("$.[*].rendering").value(hasItem(DEFAULT_RENDERING)))
            .andExpect(jsonPath("$.[*].renderingOptions").value(hasItem(DEFAULT_RENDERING_OPTIONS)))
            .andExpect(jsonPath("$.[*].shippingCost").value(hasItem(DEFAULT_SHIPPING_COST.intValue())))
            .andExpect(jsonPath("$.[*].shippingDetails").value(hasItem(DEFAULT_SHIPPING_DETAILS)))
            .andExpect(jsonPath("$.[*].startingBalance").value(hasItem(DEFAULT_STARTING_BALANCE.intValue())))
            .andExpect(jsonPath("$.[*].statementDescriptor").value(hasItem(DEFAULT_STATEMENT_DESCRIPTOR)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].statusTransitions").value(hasItem(DEFAULT_STATUS_TRANSITIONS)))
            .andExpect(jsonPath("$.[*].subscription").value(hasItem(DEFAULT_SUBSCRIPTION)))
            .andExpect(jsonPath("$.[*].subscriptionDetails").value(hasItem(DEFAULT_SUBSCRIPTION_DETAILS)))
            .andExpect(jsonPath("$.[*].subtotal").value(hasItem(DEFAULT_SUBTOTAL.intValue())))
            .andExpect(jsonPath("$.[*].subtotalExcludingTax").value(hasItem(DEFAULT_SUBTOTAL_EXCLUDING_TAX.intValue())))
            .andExpect(jsonPath("$.[*].tax").value(hasItem(DEFAULT_TAX)))
            .andExpect(jsonPath("$.[*].testClock").value(hasItem(DEFAULT_TEST_CLOCK)))
            .andExpect(jsonPath("$.[*].total").value(hasItem(DEFAULT_TOTAL.intValue())))
            .andExpect(jsonPath("$.[*].totalDiscountAmounts").value(hasItem(DEFAULT_TOTAL_DISCOUNT_AMOUNTS)))
            .andExpect(jsonPath("$.[*].totalExcludingTax").value(hasItem(DEFAULT_TOTAL_EXCLUDING_TAX.intValue())))
            .andExpect(jsonPath("$.[*].totalTaxAmounts").value(hasItem(DEFAULT_TOTAL_TAX_AMOUNTS)))
            .andExpect(jsonPath("$.[*].transferData").value(hasItem(DEFAULT_TRANSFER_DATA)))
            .andExpect(jsonPath("$.[*].webhooksDeliveredAt").value(hasItem(DEFAULT_WEBHOOKS_DELIVERED_AT.intValue())));
    }

    @Test
    @Transactional
    void getInvoice() throws Exception {
        // Initialize the database
        invoice.setId(UUID.randomUUID().toString());
        invoiceRepository.saveAndFlush(invoice);

        // Get the invoice
        restInvoiceMockMvc
            .perform(get(ENTITY_API_URL_ID, invoice.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(invoice.getId()))
            .andExpect(jsonPath("$.object").value(DEFAULT_OBJECT))
            .andExpect(jsonPath("$.accountCountry").value(DEFAULT_ACCOUNT_COUNTRY))
            .andExpect(jsonPath("$.accountName").value(DEFAULT_ACCOUNT_NAME))
            .andExpect(jsonPath("$.accountTaxIds").value(DEFAULT_ACCOUNT_TAX_IDS))
            .andExpect(jsonPath("$.amountDue").value(DEFAULT_AMOUNT_DUE.intValue()))
            .andExpect(jsonPath("$.amountPaid").value(DEFAULT_AMOUNT_PAID.intValue()))
            .andExpect(jsonPath("$.amountRemaining").value(DEFAULT_AMOUNT_REMAINING.intValue()))
            .andExpect(jsonPath("$.amountShipping").value(DEFAULT_AMOUNT_SHIPPING.intValue()))
            .andExpect(jsonPath("$.application").value(DEFAULT_APPLICATION))
            .andExpect(jsonPath("$.applicationFeeAmount").value(DEFAULT_APPLICATION_FEE_AMOUNT.intValue()))
            .andExpect(jsonPath("$.attemptCount").value(DEFAULT_ATTEMPT_COUNT))
            .andExpect(jsonPath("$.attempted").value(DEFAULT_ATTEMPTED.booleanValue()))
            .andExpect(jsonPath("$.autoAdvance").value(DEFAULT_AUTO_ADVANCE.booleanValue()))
            .andExpect(jsonPath("$.billingReason").value(DEFAULT_BILLING_REASON))
            .andExpect(jsonPath("$.charge").value(DEFAULT_CHARGE))
            .andExpect(jsonPath("$.collectionMethod").value(DEFAULT_COLLECTION_METHOD))
            .andExpect(jsonPath("$.created").value(DEFAULT_CREATED.intValue()))
            .andExpect(jsonPath("$.currency").value(DEFAULT_CURRENCY))
            .andExpect(jsonPath("$.customFields").value(DEFAULT_CUSTOM_FIELDS))
            .andExpect(jsonPath("$.customerStringValue").value(DEFAULT_CUSTOMER_STRING_VALUE))
            .andExpect(jsonPath("$.customerAddress").value(DEFAULT_CUSTOMER_ADDRESS))
            .andExpect(jsonPath("$.customerEmail").value(DEFAULT_CUSTOMER_EMAIL))
            .andExpect(jsonPath("$.customerName").value(DEFAULT_CUSTOMER_NAME))
            .andExpect(jsonPath("$.customerPhone").value(DEFAULT_CUSTOMER_PHONE))
            .andExpect(jsonPath("$.customerShipping").value(DEFAULT_CUSTOMER_SHIPPING))
            .andExpect(jsonPath("$.customerTaxExempt").value(DEFAULT_CUSTOMER_TAX_EXEMPT))
            .andExpect(jsonPath("$.customerTaxIds").value(DEFAULT_CUSTOMER_TAX_IDS))
            .andExpect(jsonPath("$.defaultPaymentMethod").value(DEFAULT_DEFAULT_PAYMENT_METHOD))
            .andExpect(jsonPath("$.defaultSource").value(DEFAULT_DEFAULT_SOURCE))
            .andExpect(jsonPath("$.defaultTaxRates").value(DEFAULT_DEFAULT_TAX_RATES))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.discount").value(DEFAULT_DISCOUNT))
            .andExpect(jsonPath("$.discounts").value(DEFAULT_DISCOUNTS))
            .andExpect(jsonPath("$.dueDate").value(DEFAULT_DUE_DATE.intValue()))
            .andExpect(jsonPath("$.effectiveAt").value(DEFAULT_EFFECTIVE_AT.intValue()))
            .andExpect(jsonPath("$.endingBalance").value(DEFAULT_ENDING_BALANCE.intValue()))
            .andExpect(jsonPath("$.footer").value(DEFAULT_FOOTER))
            .andExpect(jsonPath("$.fromInvoice").value(DEFAULT_FROM_INVOICE))
            .andExpect(jsonPath("$.hostedInvoiceUrl").value(DEFAULT_HOSTED_INVOICE_URL))
            .andExpect(jsonPath("$.invoicePdf").value(DEFAULT_INVOICE_PDF))
            .andExpect(jsonPath("$.lastFinalizationError").value(DEFAULT_LAST_FINALIZATION_ERROR))
            .andExpect(jsonPath("$.latestRevision").value(DEFAULT_LATEST_REVISION))
            .andExpect(jsonPath("$.livemode").value(DEFAULT_LIVEMODE.booleanValue()))
            .andExpect(jsonPath("$.metadata").value(DEFAULT_METADATA))
            .andExpect(jsonPath("$.nextPaymentAttempt").value(DEFAULT_NEXT_PAYMENT_ATTEMPT.intValue()))
            .andExpect(jsonPath("$.number").value(DEFAULT_NUMBER))
            .andExpect(jsonPath("$.onBehalfOf").value(DEFAULT_ON_BEHALF_OF))
            .andExpect(jsonPath("$.paid").value(DEFAULT_PAID.booleanValue()))
            .andExpect(jsonPath("$.paidOutOfBand").value(DEFAULT_PAID_OUT_OF_BAND.booleanValue()))
            .andExpect(jsonPath("$.paymentIntent").value(DEFAULT_PAYMENT_INTENT))
            .andExpect(jsonPath("$.paymentSettings").value(DEFAULT_PAYMENT_SETTINGS))
            .andExpect(jsonPath("$.periodEnd").value(DEFAULT_PERIOD_END.intValue()))
            .andExpect(jsonPath("$.periodStart").value(DEFAULT_PERIOD_START.intValue()))
            .andExpect(jsonPath("$.postPaymentCreditNotesAmount").value(DEFAULT_POST_PAYMENT_CREDIT_NOTES_AMOUNT.intValue()))
            .andExpect(jsonPath("$.prePaymentCreditNotesAmount").value(DEFAULT_PRE_PAYMENT_CREDIT_NOTES_AMOUNT.intValue()))
            .andExpect(jsonPath("$.quote").value(DEFAULT_QUOTE))
            .andExpect(jsonPath("$.receiptNumber").value(DEFAULT_RECEIPT_NUMBER))
            .andExpect(jsonPath("$.rendering").value(DEFAULT_RENDERING))
            .andExpect(jsonPath("$.renderingOptions").value(DEFAULT_RENDERING_OPTIONS))
            .andExpect(jsonPath("$.shippingCost").value(DEFAULT_SHIPPING_COST.intValue()))
            .andExpect(jsonPath("$.shippingDetails").value(DEFAULT_SHIPPING_DETAILS))
            .andExpect(jsonPath("$.startingBalance").value(DEFAULT_STARTING_BALANCE.intValue()))
            .andExpect(jsonPath("$.statementDescriptor").value(DEFAULT_STATEMENT_DESCRIPTOR))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.statusTransitions").value(DEFAULT_STATUS_TRANSITIONS))
            .andExpect(jsonPath("$.subscription").value(DEFAULT_SUBSCRIPTION))
            .andExpect(jsonPath("$.subscriptionDetails").value(DEFAULT_SUBSCRIPTION_DETAILS))
            .andExpect(jsonPath("$.subtotal").value(DEFAULT_SUBTOTAL.intValue()))
            .andExpect(jsonPath("$.subtotalExcludingTax").value(DEFAULT_SUBTOTAL_EXCLUDING_TAX.intValue()))
            .andExpect(jsonPath("$.tax").value(DEFAULT_TAX))
            .andExpect(jsonPath("$.testClock").value(DEFAULT_TEST_CLOCK))
            .andExpect(jsonPath("$.total").value(DEFAULT_TOTAL.intValue()))
            .andExpect(jsonPath("$.totalDiscountAmounts").value(DEFAULT_TOTAL_DISCOUNT_AMOUNTS))
            .andExpect(jsonPath("$.totalExcludingTax").value(DEFAULT_TOTAL_EXCLUDING_TAX.intValue()))
            .andExpect(jsonPath("$.totalTaxAmounts").value(DEFAULT_TOTAL_TAX_AMOUNTS))
            .andExpect(jsonPath("$.transferData").value(DEFAULT_TRANSFER_DATA))
            .andExpect(jsonPath("$.webhooksDeliveredAt").value(DEFAULT_WEBHOOKS_DELIVERED_AT.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingInvoice() throws Exception {
        // Get the invoice
        restInvoiceMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingInvoice() throws Exception {
        // Initialize the database
        invoice.setId(UUID.randomUUID().toString());
        invoiceRepository.saveAndFlush(invoice);

        int databaseSizeBeforeUpdate = invoiceRepository.findAll().size();

        // Update the invoice
        Invoice updatedInvoice = invoiceRepository.findById(invoice.getId()).get();
        // Disconnect from session so that the updates on updatedInvoice are not directly saved in db
        em.detach(updatedInvoice);
        updatedInvoice
            .object(UPDATED_OBJECT)
            .accountCountry(UPDATED_ACCOUNT_COUNTRY)
            .accountName(UPDATED_ACCOUNT_NAME)
            .accountTaxIds(UPDATED_ACCOUNT_TAX_IDS)
            .amountDue(UPDATED_AMOUNT_DUE)
            .amountPaid(UPDATED_AMOUNT_PAID)
            .amountRemaining(UPDATED_AMOUNT_REMAINING)
            .amountShipping(UPDATED_AMOUNT_SHIPPING)
            .application(UPDATED_APPLICATION)
            .applicationFeeAmount(UPDATED_APPLICATION_FEE_AMOUNT)
            .attemptCount(UPDATED_ATTEMPT_COUNT)
            .attempted(UPDATED_ATTEMPTED)
            .autoAdvance(UPDATED_AUTO_ADVANCE)
            .billingReason(UPDATED_BILLING_REASON)
            .charge(UPDATED_CHARGE)
            .collectionMethod(UPDATED_COLLECTION_METHOD)
            .created(UPDATED_CREATED)
            .currency(UPDATED_CURRENCY)
            .customFields(UPDATED_CUSTOM_FIELDS)
            .customerStringValue(UPDATED_CUSTOMER_STRING_VALUE)
            .customerAddress(UPDATED_CUSTOMER_ADDRESS)
            .customerEmail(UPDATED_CUSTOMER_EMAIL)
            .customerName(UPDATED_CUSTOMER_NAME)
            .customerPhone(UPDATED_CUSTOMER_PHONE)
            .customerShipping(UPDATED_CUSTOMER_SHIPPING)
            .customerTaxExempt(UPDATED_CUSTOMER_TAX_EXEMPT)
            .customerTaxIds(UPDATED_CUSTOMER_TAX_IDS)
            .defaultPaymentMethod(UPDATED_DEFAULT_PAYMENT_METHOD)
            .defaultSource(UPDATED_DEFAULT_SOURCE)
            .defaultTaxRates(UPDATED_DEFAULT_TAX_RATES)
            .description(UPDATED_DESCRIPTION)
            .discount(UPDATED_DISCOUNT)
            .discounts(UPDATED_DISCOUNTS)
            .dueDate(UPDATED_DUE_DATE)
            .effectiveAt(UPDATED_EFFECTIVE_AT)
            .endingBalance(UPDATED_ENDING_BALANCE)
            .footer(UPDATED_FOOTER)
            .fromInvoice(UPDATED_FROM_INVOICE)
            .hostedInvoiceUrl(UPDATED_HOSTED_INVOICE_URL)
            .invoicePdf(UPDATED_INVOICE_PDF)
            .lastFinalizationError(UPDATED_LAST_FINALIZATION_ERROR)
            .latestRevision(UPDATED_LATEST_REVISION)
            .livemode(UPDATED_LIVEMODE)
            .metadata(UPDATED_METADATA)
            .nextPaymentAttempt(UPDATED_NEXT_PAYMENT_ATTEMPT)
            .number(UPDATED_NUMBER)
            .onBehalfOf(UPDATED_ON_BEHALF_OF)
            .paid(UPDATED_PAID)
            .paidOutOfBand(UPDATED_PAID_OUT_OF_BAND)
            .paymentIntent(UPDATED_PAYMENT_INTENT)
            .paymentSettings(UPDATED_PAYMENT_SETTINGS)
            .periodEnd(UPDATED_PERIOD_END)
            .periodStart(UPDATED_PERIOD_START)
            .postPaymentCreditNotesAmount(UPDATED_POST_PAYMENT_CREDIT_NOTES_AMOUNT)
            .prePaymentCreditNotesAmount(UPDATED_PRE_PAYMENT_CREDIT_NOTES_AMOUNT)
            .quote(UPDATED_QUOTE)
            .receiptNumber(UPDATED_RECEIPT_NUMBER)
            .rendering(UPDATED_RENDERING)
            .renderingOptions(UPDATED_RENDERING_OPTIONS)
            .shippingCost(UPDATED_SHIPPING_COST)
            .shippingDetails(UPDATED_SHIPPING_DETAILS)
            .startingBalance(UPDATED_STARTING_BALANCE)
            .statementDescriptor(UPDATED_STATEMENT_DESCRIPTOR)
            .status(UPDATED_STATUS)
            .statusTransitions(UPDATED_STATUS_TRANSITIONS)
            .subscription(UPDATED_SUBSCRIPTION)
            .subscriptionDetails(UPDATED_SUBSCRIPTION_DETAILS)
            .subtotal(UPDATED_SUBTOTAL)
            .subtotalExcludingTax(UPDATED_SUBTOTAL_EXCLUDING_TAX)
            .tax(UPDATED_TAX)
            .testClock(UPDATED_TEST_CLOCK)
            .total(UPDATED_TOTAL)
            .totalDiscountAmounts(UPDATED_TOTAL_DISCOUNT_AMOUNTS)
            .totalExcludingTax(UPDATED_TOTAL_EXCLUDING_TAX)
            .totalTaxAmounts(UPDATED_TOTAL_TAX_AMOUNTS)
            .transferData(UPDATED_TRANSFER_DATA)
            .webhooksDeliveredAt(UPDATED_WEBHOOKS_DELIVERED_AT);

        restInvoiceMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedInvoice.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedInvoice))
            )
            .andExpect(status().isOk());

        // Validate the Invoice in the database
        List<Invoice> invoiceList = invoiceRepository.findAll();
        assertThat(invoiceList).hasSize(databaseSizeBeforeUpdate);
        Invoice testInvoice = invoiceList.get(invoiceList.size() - 1);
        assertThat(testInvoice.getObject()).isEqualTo(UPDATED_OBJECT);
        assertThat(testInvoice.getAccountCountry()).isEqualTo(UPDATED_ACCOUNT_COUNTRY);
        assertThat(testInvoice.getAccountName()).isEqualTo(UPDATED_ACCOUNT_NAME);
        assertThat(testInvoice.getAccountTaxIds()).isEqualTo(UPDATED_ACCOUNT_TAX_IDS);
        assertThat(testInvoice.getAmountDue()).isEqualTo(UPDATED_AMOUNT_DUE);
        assertThat(testInvoice.getAmountPaid()).isEqualTo(UPDATED_AMOUNT_PAID);
        assertThat(testInvoice.getAmountRemaining()).isEqualTo(UPDATED_AMOUNT_REMAINING);
        assertThat(testInvoice.getAmountShipping()).isEqualTo(UPDATED_AMOUNT_SHIPPING);
        assertThat(testInvoice.getApplication()).isEqualTo(UPDATED_APPLICATION);
        assertThat(testInvoice.getApplicationFeeAmount()).isEqualTo(UPDATED_APPLICATION_FEE_AMOUNT);
        assertThat(testInvoice.getAttemptCount()).isEqualTo(UPDATED_ATTEMPT_COUNT);
        assertThat(testInvoice.getAttempted()).isEqualTo(UPDATED_ATTEMPTED);
        assertThat(testInvoice.getAutoAdvance()).isEqualTo(UPDATED_AUTO_ADVANCE);
        assertThat(testInvoice.getBillingReason()).isEqualTo(UPDATED_BILLING_REASON);
        assertThat(testInvoice.getCharge()).isEqualTo(UPDATED_CHARGE);
        assertThat(testInvoice.getCollectionMethod()).isEqualTo(UPDATED_COLLECTION_METHOD);
        assertThat(testInvoice.getCreated()).isEqualTo(UPDATED_CREATED);
        assertThat(testInvoice.getCurrency()).isEqualTo(UPDATED_CURRENCY);
        assertThat(testInvoice.getCustomFields()).isEqualTo(UPDATED_CUSTOM_FIELDS);
        assertThat(testInvoice.getCustomerStringValue()).isEqualTo(UPDATED_CUSTOMER_STRING_VALUE);
        assertThat(testInvoice.getCustomerAddress()).isEqualTo(UPDATED_CUSTOMER_ADDRESS);
        assertThat(testInvoice.getCustomerEmail()).isEqualTo(UPDATED_CUSTOMER_EMAIL);
        assertThat(testInvoice.getCustomerName()).isEqualTo(UPDATED_CUSTOMER_NAME);
        assertThat(testInvoice.getCustomerPhone()).isEqualTo(UPDATED_CUSTOMER_PHONE);
        assertThat(testInvoice.getCustomerShipping()).isEqualTo(UPDATED_CUSTOMER_SHIPPING);
        assertThat(testInvoice.getCustomerTaxExempt()).isEqualTo(UPDATED_CUSTOMER_TAX_EXEMPT);
        assertThat(testInvoice.getCustomerTaxIds()).isEqualTo(UPDATED_CUSTOMER_TAX_IDS);
        assertThat(testInvoice.getDefaultPaymentMethod()).isEqualTo(UPDATED_DEFAULT_PAYMENT_METHOD);
        assertThat(testInvoice.getDefaultSource()).isEqualTo(UPDATED_DEFAULT_SOURCE);
        assertThat(testInvoice.getDefaultTaxRates()).isEqualTo(UPDATED_DEFAULT_TAX_RATES);
        assertThat(testInvoice.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testInvoice.getDiscount()).isEqualTo(UPDATED_DISCOUNT);
        assertThat(testInvoice.getDiscounts()).isEqualTo(UPDATED_DISCOUNTS);
        assertThat(testInvoice.getDueDate()).isEqualTo(UPDATED_DUE_DATE);
        assertThat(testInvoice.getEffectiveAt()).isEqualTo(UPDATED_EFFECTIVE_AT);
        assertThat(testInvoice.getEndingBalance()).isEqualTo(UPDATED_ENDING_BALANCE);
        assertThat(testInvoice.getFooter()).isEqualTo(UPDATED_FOOTER);
        assertThat(testInvoice.getFromInvoice()).isEqualTo(UPDATED_FROM_INVOICE);
        assertThat(testInvoice.getHostedInvoiceUrl()).isEqualTo(UPDATED_HOSTED_INVOICE_URL);
        assertThat(testInvoice.getInvoicePdf()).isEqualTo(UPDATED_INVOICE_PDF);
        assertThat(testInvoice.getLastFinalizationError()).isEqualTo(UPDATED_LAST_FINALIZATION_ERROR);
        assertThat(testInvoice.getLatestRevision()).isEqualTo(UPDATED_LATEST_REVISION);
        assertThat(testInvoice.getLivemode()).isEqualTo(UPDATED_LIVEMODE);
        assertThat(testInvoice.getMetadata()).isEqualTo(UPDATED_METADATA);
        assertThat(testInvoice.getNextPaymentAttempt()).isEqualTo(UPDATED_NEXT_PAYMENT_ATTEMPT);
        assertThat(testInvoice.getNumber()).isEqualTo(UPDATED_NUMBER);
        assertThat(testInvoice.getOnBehalfOf()).isEqualTo(UPDATED_ON_BEHALF_OF);
        assertThat(testInvoice.getPaid()).isEqualTo(UPDATED_PAID);
        assertThat(testInvoice.getPaidOutOfBand()).isEqualTo(UPDATED_PAID_OUT_OF_BAND);
        assertThat(testInvoice.getPaymentIntent()).isEqualTo(UPDATED_PAYMENT_INTENT);
        assertThat(testInvoice.getPaymentSettings()).isEqualTo(UPDATED_PAYMENT_SETTINGS);
        assertThat(testInvoice.getPeriodEnd()).isEqualTo(UPDATED_PERIOD_END);
        assertThat(testInvoice.getPeriodStart()).isEqualTo(UPDATED_PERIOD_START);
        assertThat(testInvoice.getPostPaymentCreditNotesAmount()).isEqualTo(UPDATED_POST_PAYMENT_CREDIT_NOTES_AMOUNT);
        assertThat(testInvoice.getPrePaymentCreditNotesAmount()).isEqualTo(UPDATED_PRE_PAYMENT_CREDIT_NOTES_AMOUNT);
        assertThat(testInvoice.getQuote()).isEqualTo(UPDATED_QUOTE);
        assertThat(testInvoice.getReceiptNumber()).isEqualTo(UPDATED_RECEIPT_NUMBER);
        assertThat(testInvoice.getRendering()).isEqualTo(UPDATED_RENDERING);
        assertThat(testInvoice.getRenderingOptions()).isEqualTo(UPDATED_RENDERING_OPTIONS);
        assertThat(testInvoice.getShippingCost()).isEqualTo(UPDATED_SHIPPING_COST);
        assertThat(testInvoice.getShippingDetails()).isEqualTo(UPDATED_SHIPPING_DETAILS);
        assertThat(testInvoice.getStartingBalance()).isEqualTo(UPDATED_STARTING_BALANCE);
        assertThat(testInvoice.getStatementDescriptor()).isEqualTo(UPDATED_STATEMENT_DESCRIPTOR);
        assertThat(testInvoice.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testInvoice.getStatusTransitions()).isEqualTo(UPDATED_STATUS_TRANSITIONS);
        assertThat(testInvoice.getSubscription()).isEqualTo(UPDATED_SUBSCRIPTION);
        assertThat(testInvoice.getSubscriptionDetails()).isEqualTo(UPDATED_SUBSCRIPTION_DETAILS);
        assertThat(testInvoice.getSubtotal()).isEqualTo(UPDATED_SUBTOTAL);
        assertThat(testInvoice.getSubtotalExcludingTax()).isEqualTo(UPDATED_SUBTOTAL_EXCLUDING_TAX);
        assertThat(testInvoice.getTax()).isEqualTo(UPDATED_TAX);
        assertThat(testInvoice.getTestClock()).isEqualTo(UPDATED_TEST_CLOCK);
        assertThat(testInvoice.getTotal()).isEqualTo(UPDATED_TOTAL);
        assertThat(testInvoice.getTotalDiscountAmounts()).isEqualTo(UPDATED_TOTAL_DISCOUNT_AMOUNTS);
        assertThat(testInvoice.getTotalExcludingTax()).isEqualTo(UPDATED_TOTAL_EXCLUDING_TAX);
        assertThat(testInvoice.getTotalTaxAmounts()).isEqualTo(UPDATED_TOTAL_TAX_AMOUNTS);
        assertThat(testInvoice.getTransferData()).isEqualTo(UPDATED_TRANSFER_DATA);
        assertThat(testInvoice.getWebhooksDeliveredAt()).isEqualTo(UPDATED_WEBHOOKS_DELIVERED_AT);
    }

    @Test
    @Transactional
    void putNonExistingInvoice() throws Exception {
        int databaseSizeBeforeUpdate = invoiceRepository.findAll().size();
        invoice.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInvoiceMockMvc
            .perform(
                put(ENTITY_API_URL_ID, invoice.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(invoice))
            )
            .andExpect(status().isBadRequest());

        // Validate the Invoice in the database
        List<Invoice> invoiceList = invoiceRepository.findAll();
        assertThat(invoiceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchInvoice() throws Exception {
        int databaseSizeBeforeUpdate = invoiceRepository.findAll().size();
        invoice.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInvoiceMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(invoice))
            )
            .andExpect(status().isBadRequest());

        // Validate the Invoice in the database
        List<Invoice> invoiceList = invoiceRepository.findAll();
        assertThat(invoiceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamInvoice() throws Exception {
        int databaseSizeBeforeUpdate = invoiceRepository.findAll().size();
        invoice.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInvoiceMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(invoice)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Invoice in the database
        List<Invoice> invoiceList = invoiceRepository.findAll();
        assertThat(invoiceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateInvoiceWithPatch() throws Exception {
        // Initialize the database
        invoice.setId(UUID.randomUUID().toString());
        invoiceRepository.saveAndFlush(invoice);

        int databaseSizeBeforeUpdate = invoiceRepository.findAll().size();

        // Update the invoice using partial update
        Invoice partialUpdatedInvoice = new Invoice();
        partialUpdatedInvoice.setId(invoice.getId());

        partialUpdatedInvoice
            .accountName(UPDATED_ACCOUNT_NAME)
            .accountTaxIds(UPDATED_ACCOUNT_TAX_IDS)
            .amountDue(UPDATED_AMOUNT_DUE)
            .application(UPDATED_APPLICATION)
            .applicationFeeAmount(UPDATED_APPLICATION_FEE_AMOUNT)
            .attemptCount(UPDATED_ATTEMPT_COUNT)
            .billingReason(UPDATED_BILLING_REASON)
            .created(UPDATED_CREATED)
            .customerStringValue(UPDATED_CUSTOMER_STRING_VALUE)
            .customerAddress(UPDATED_CUSTOMER_ADDRESS)
            .customerName(UPDATED_CUSTOMER_NAME)
            .customerShipping(UPDATED_CUSTOMER_SHIPPING)
            .customerTaxExempt(UPDATED_CUSTOMER_TAX_EXEMPT)
            .customerTaxIds(UPDATED_CUSTOMER_TAX_IDS)
            .defaultPaymentMethod(UPDATED_DEFAULT_PAYMENT_METHOD)
            .discounts(UPDATED_DISCOUNTS)
            .dueDate(UPDATED_DUE_DATE)
            .effectiveAt(UPDATED_EFFECTIVE_AT)
            .hostedInvoiceUrl(UPDATED_HOSTED_INVOICE_URL)
            .invoicePdf(UPDATED_INVOICE_PDF)
            .latestRevision(UPDATED_LATEST_REVISION)
            .metadata(UPDATED_METADATA)
            .nextPaymentAttempt(UPDATED_NEXT_PAYMENT_ATTEMPT)
            .paid(UPDATED_PAID)
            .paymentIntent(UPDATED_PAYMENT_INTENT)
            .paymentSettings(UPDATED_PAYMENT_SETTINGS)
            .periodEnd(UPDATED_PERIOD_END)
            .periodStart(UPDATED_PERIOD_START)
            .prePaymentCreditNotesAmount(UPDATED_PRE_PAYMENT_CREDIT_NOTES_AMOUNT)
            .quote(UPDATED_QUOTE)
            .receiptNumber(UPDATED_RECEIPT_NUMBER)
            .rendering(UPDATED_RENDERING)
            .shippingCost(UPDATED_SHIPPING_COST)
            .shippingDetails(UPDATED_SHIPPING_DETAILS)
            .subscription(UPDATED_SUBSCRIPTION)
            .testClock(UPDATED_TEST_CLOCK)
            .total(UPDATED_TOTAL)
            .totalExcludingTax(UPDATED_TOTAL_EXCLUDING_TAX)
            .totalTaxAmounts(UPDATED_TOTAL_TAX_AMOUNTS)
            .transferData(UPDATED_TRANSFER_DATA)
            .webhooksDeliveredAt(UPDATED_WEBHOOKS_DELIVERED_AT);

        restInvoiceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedInvoice.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedInvoice))
            )
            .andExpect(status().isOk());

        // Validate the Invoice in the database
        List<Invoice> invoiceList = invoiceRepository.findAll();
        assertThat(invoiceList).hasSize(databaseSizeBeforeUpdate);
        Invoice testInvoice = invoiceList.get(invoiceList.size() - 1);
        assertThat(testInvoice.getObject()).isEqualTo(DEFAULT_OBJECT);
        assertThat(testInvoice.getAccountCountry()).isEqualTo(DEFAULT_ACCOUNT_COUNTRY);
        assertThat(testInvoice.getAccountName()).isEqualTo(UPDATED_ACCOUNT_NAME);
        assertThat(testInvoice.getAccountTaxIds()).isEqualTo(UPDATED_ACCOUNT_TAX_IDS);
        assertThat(testInvoice.getAmountDue()).isEqualTo(UPDATED_AMOUNT_DUE);
        assertThat(testInvoice.getAmountPaid()).isEqualTo(DEFAULT_AMOUNT_PAID);
        assertThat(testInvoice.getAmountRemaining()).isEqualTo(DEFAULT_AMOUNT_REMAINING);
        assertThat(testInvoice.getAmountShipping()).isEqualTo(DEFAULT_AMOUNT_SHIPPING);
        assertThat(testInvoice.getApplication()).isEqualTo(UPDATED_APPLICATION);
        assertThat(testInvoice.getApplicationFeeAmount()).isEqualTo(UPDATED_APPLICATION_FEE_AMOUNT);
        assertThat(testInvoice.getAttemptCount()).isEqualTo(UPDATED_ATTEMPT_COUNT);
        assertThat(testInvoice.getAttempted()).isEqualTo(DEFAULT_ATTEMPTED);
        assertThat(testInvoice.getAutoAdvance()).isEqualTo(DEFAULT_AUTO_ADVANCE);
        assertThat(testInvoice.getBillingReason()).isEqualTo(UPDATED_BILLING_REASON);
        assertThat(testInvoice.getCharge()).isEqualTo(DEFAULT_CHARGE);
        assertThat(testInvoice.getCollectionMethod()).isEqualTo(DEFAULT_COLLECTION_METHOD);
        assertThat(testInvoice.getCreated()).isEqualTo(UPDATED_CREATED);
        assertThat(testInvoice.getCurrency()).isEqualTo(DEFAULT_CURRENCY);
        assertThat(testInvoice.getCustomFields()).isEqualTo(DEFAULT_CUSTOM_FIELDS);
        assertThat(testInvoice.getCustomerStringValue()).isEqualTo(UPDATED_CUSTOMER_STRING_VALUE);
        assertThat(testInvoice.getCustomerAddress()).isEqualTo(UPDATED_CUSTOMER_ADDRESS);
        assertThat(testInvoice.getCustomerEmail()).isEqualTo(DEFAULT_CUSTOMER_EMAIL);
        assertThat(testInvoice.getCustomerName()).isEqualTo(UPDATED_CUSTOMER_NAME);
        assertThat(testInvoice.getCustomerPhone()).isEqualTo(DEFAULT_CUSTOMER_PHONE);
        assertThat(testInvoice.getCustomerShipping()).isEqualTo(UPDATED_CUSTOMER_SHIPPING);
        assertThat(testInvoice.getCustomerTaxExempt()).isEqualTo(UPDATED_CUSTOMER_TAX_EXEMPT);
        assertThat(testInvoice.getCustomerTaxIds()).isEqualTo(UPDATED_CUSTOMER_TAX_IDS);
        assertThat(testInvoice.getDefaultPaymentMethod()).isEqualTo(UPDATED_DEFAULT_PAYMENT_METHOD);
        assertThat(testInvoice.getDefaultSource()).isEqualTo(DEFAULT_DEFAULT_SOURCE);
        assertThat(testInvoice.getDefaultTaxRates()).isEqualTo(DEFAULT_DEFAULT_TAX_RATES);
        assertThat(testInvoice.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testInvoice.getDiscount()).isEqualTo(DEFAULT_DISCOUNT);
        assertThat(testInvoice.getDiscounts()).isEqualTo(UPDATED_DISCOUNTS);
        assertThat(testInvoice.getDueDate()).isEqualTo(UPDATED_DUE_DATE);
        assertThat(testInvoice.getEffectiveAt()).isEqualTo(UPDATED_EFFECTIVE_AT);
        assertThat(testInvoice.getEndingBalance()).isEqualTo(DEFAULT_ENDING_BALANCE);
        assertThat(testInvoice.getFooter()).isEqualTo(DEFAULT_FOOTER);
        assertThat(testInvoice.getFromInvoice()).isEqualTo(DEFAULT_FROM_INVOICE);
        assertThat(testInvoice.getHostedInvoiceUrl()).isEqualTo(UPDATED_HOSTED_INVOICE_URL);
        assertThat(testInvoice.getInvoicePdf()).isEqualTo(UPDATED_INVOICE_PDF);
        assertThat(testInvoice.getLastFinalizationError()).isEqualTo(DEFAULT_LAST_FINALIZATION_ERROR);
        assertThat(testInvoice.getLatestRevision()).isEqualTo(UPDATED_LATEST_REVISION);
        assertThat(testInvoice.getLivemode()).isEqualTo(DEFAULT_LIVEMODE);
        assertThat(testInvoice.getMetadata()).isEqualTo(UPDATED_METADATA);
        assertThat(testInvoice.getNextPaymentAttempt()).isEqualTo(UPDATED_NEXT_PAYMENT_ATTEMPT);
        assertThat(testInvoice.getNumber()).isEqualTo(DEFAULT_NUMBER);
        assertThat(testInvoice.getOnBehalfOf()).isEqualTo(DEFAULT_ON_BEHALF_OF);
        assertThat(testInvoice.getPaid()).isEqualTo(UPDATED_PAID);
        assertThat(testInvoice.getPaidOutOfBand()).isEqualTo(DEFAULT_PAID_OUT_OF_BAND);
        assertThat(testInvoice.getPaymentIntent()).isEqualTo(UPDATED_PAYMENT_INTENT);
        assertThat(testInvoice.getPaymentSettings()).isEqualTo(UPDATED_PAYMENT_SETTINGS);
        assertThat(testInvoice.getPeriodEnd()).isEqualTo(UPDATED_PERIOD_END);
        assertThat(testInvoice.getPeriodStart()).isEqualTo(UPDATED_PERIOD_START);
        assertThat(testInvoice.getPostPaymentCreditNotesAmount()).isEqualTo(DEFAULT_POST_PAYMENT_CREDIT_NOTES_AMOUNT);
        assertThat(testInvoice.getPrePaymentCreditNotesAmount()).isEqualTo(UPDATED_PRE_PAYMENT_CREDIT_NOTES_AMOUNT);
        assertThat(testInvoice.getQuote()).isEqualTo(UPDATED_QUOTE);
        assertThat(testInvoice.getReceiptNumber()).isEqualTo(UPDATED_RECEIPT_NUMBER);
        assertThat(testInvoice.getRendering()).isEqualTo(UPDATED_RENDERING);
        assertThat(testInvoice.getRenderingOptions()).isEqualTo(DEFAULT_RENDERING_OPTIONS);
        assertThat(testInvoice.getShippingCost()).isEqualTo(UPDATED_SHIPPING_COST);
        assertThat(testInvoice.getShippingDetails()).isEqualTo(UPDATED_SHIPPING_DETAILS);
        assertThat(testInvoice.getStartingBalance()).isEqualTo(DEFAULT_STARTING_BALANCE);
        assertThat(testInvoice.getStatementDescriptor()).isEqualTo(DEFAULT_STATEMENT_DESCRIPTOR);
        assertThat(testInvoice.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testInvoice.getStatusTransitions()).isEqualTo(DEFAULT_STATUS_TRANSITIONS);
        assertThat(testInvoice.getSubscription()).isEqualTo(UPDATED_SUBSCRIPTION);
        assertThat(testInvoice.getSubscriptionDetails()).isEqualTo(DEFAULT_SUBSCRIPTION_DETAILS);
        assertThat(testInvoice.getSubtotal()).isEqualTo(DEFAULT_SUBTOTAL);
        assertThat(testInvoice.getSubtotalExcludingTax()).isEqualTo(DEFAULT_SUBTOTAL_EXCLUDING_TAX);
        assertThat(testInvoice.getTax()).isEqualTo(DEFAULT_TAX);
        assertThat(testInvoice.getTestClock()).isEqualTo(UPDATED_TEST_CLOCK);
        assertThat(testInvoice.getTotal()).isEqualTo(UPDATED_TOTAL);
        assertThat(testInvoice.getTotalDiscountAmounts()).isEqualTo(DEFAULT_TOTAL_DISCOUNT_AMOUNTS);
        assertThat(testInvoice.getTotalExcludingTax()).isEqualTo(UPDATED_TOTAL_EXCLUDING_TAX);
        assertThat(testInvoice.getTotalTaxAmounts()).isEqualTo(UPDATED_TOTAL_TAX_AMOUNTS);
        assertThat(testInvoice.getTransferData()).isEqualTo(UPDATED_TRANSFER_DATA);
        assertThat(testInvoice.getWebhooksDeliveredAt()).isEqualTo(UPDATED_WEBHOOKS_DELIVERED_AT);
    }

    @Test
    @Transactional
    void fullUpdateInvoiceWithPatch() throws Exception {
        // Initialize the database
        invoice.setId(UUID.randomUUID().toString());
        invoiceRepository.saveAndFlush(invoice);

        int databaseSizeBeforeUpdate = invoiceRepository.findAll().size();

        // Update the invoice using partial update
        Invoice partialUpdatedInvoice = new Invoice();
        partialUpdatedInvoice.setId(invoice.getId());

        partialUpdatedInvoice
            .object(UPDATED_OBJECT)
            .accountCountry(UPDATED_ACCOUNT_COUNTRY)
            .accountName(UPDATED_ACCOUNT_NAME)
            .accountTaxIds(UPDATED_ACCOUNT_TAX_IDS)
            .amountDue(UPDATED_AMOUNT_DUE)
            .amountPaid(UPDATED_AMOUNT_PAID)
            .amountRemaining(UPDATED_AMOUNT_REMAINING)
            .amountShipping(UPDATED_AMOUNT_SHIPPING)
            .application(UPDATED_APPLICATION)
            .applicationFeeAmount(UPDATED_APPLICATION_FEE_AMOUNT)
            .attemptCount(UPDATED_ATTEMPT_COUNT)
            .attempted(UPDATED_ATTEMPTED)
            .autoAdvance(UPDATED_AUTO_ADVANCE)
            .billingReason(UPDATED_BILLING_REASON)
            .charge(UPDATED_CHARGE)
            .collectionMethod(UPDATED_COLLECTION_METHOD)
            .created(UPDATED_CREATED)
            .currency(UPDATED_CURRENCY)
            .customFields(UPDATED_CUSTOM_FIELDS)
            .customerStringValue(UPDATED_CUSTOMER_STRING_VALUE)
            .customerAddress(UPDATED_CUSTOMER_ADDRESS)
            .customerEmail(UPDATED_CUSTOMER_EMAIL)
            .customerName(UPDATED_CUSTOMER_NAME)
            .customerPhone(UPDATED_CUSTOMER_PHONE)
            .customerShipping(UPDATED_CUSTOMER_SHIPPING)
            .customerTaxExempt(UPDATED_CUSTOMER_TAX_EXEMPT)
            .customerTaxIds(UPDATED_CUSTOMER_TAX_IDS)
            .defaultPaymentMethod(UPDATED_DEFAULT_PAYMENT_METHOD)
            .defaultSource(UPDATED_DEFAULT_SOURCE)
            .defaultTaxRates(UPDATED_DEFAULT_TAX_RATES)
            .description(UPDATED_DESCRIPTION)
            .discount(UPDATED_DISCOUNT)
            .discounts(UPDATED_DISCOUNTS)
            .dueDate(UPDATED_DUE_DATE)
            .effectiveAt(UPDATED_EFFECTIVE_AT)
            .endingBalance(UPDATED_ENDING_BALANCE)
            .footer(UPDATED_FOOTER)
            .fromInvoice(UPDATED_FROM_INVOICE)
            .hostedInvoiceUrl(UPDATED_HOSTED_INVOICE_URL)
            .invoicePdf(UPDATED_INVOICE_PDF)
            .lastFinalizationError(UPDATED_LAST_FINALIZATION_ERROR)
            .latestRevision(UPDATED_LATEST_REVISION)
            .livemode(UPDATED_LIVEMODE)
            .metadata(UPDATED_METADATA)
            .nextPaymentAttempt(UPDATED_NEXT_PAYMENT_ATTEMPT)
            .number(UPDATED_NUMBER)
            .onBehalfOf(UPDATED_ON_BEHALF_OF)
            .paid(UPDATED_PAID)
            .paidOutOfBand(UPDATED_PAID_OUT_OF_BAND)
            .paymentIntent(UPDATED_PAYMENT_INTENT)
            .paymentSettings(UPDATED_PAYMENT_SETTINGS)
            .periodEnd(UPDATED_PERIOD_END)
            .periodStart(UPDATED_PERIOD_START)
            .postPaymentCreditNotesAmount(UPDATED_POST_PAYMENT_CREDIT_NOTES_AMOUNT)
            .prePaymentCreditNotesAmount(UPDATED_PRE_PAYMENT_CREDIT_NOTES_AMOUNT)
            .quote(UPDATED_QUOTE)
            .receiptNumber(UPDATED_RECEIPT_NUMBER)
            .rendering(UPDATED_RENDERING)
            .renderingOptions(UPDATED_RENDERING_OPTIONS)
            .shippingCost(UPDATED_SHIPPING_COST)
            .shippingDetails(UPDATED_SHIPPING_DETAILS)
            .startingBalance(UPDATED_STARTING_BALANCE)
            .statementDescriptor(UPDATED_STATEMENT_DESCRIPTOR)
            .status(UPDATED_STATUS)
            .statusTransitions(UPDATED_STATUS_TRANSITIONS)
            .subscription(UPDATED_SUBSCRIPTION)
            .subscriptionDetails(UPDATED_SUBSCRIPTION_DETAILS)
            .subtotal(UPDATED_SUBTOTAL)
            .subtotalExcludingTax(UPDATED_SUBTOTAL_EXCLUDING_TAX)
            .tax(UPDATED_TAX)
            .testClock(UPDATED_TEST_CLOCK)
            .total(UPDATED_TOTAL)
            .totalDiscountAmounts(UPDATED_TOTAL_DISCOUNT_AMOUNTS)
            .totalExcludingTax(UPDATED_TOTAL_EXCLUDING_TAX)
            .totalTaxAmounts(UPDATED_TOTAL_TAX_AMOUNTS)
            .transferData(UPDATED_TRANSFER_DATA)
            .webhooksDeliveredAt(UPDATED_WEBHOOKS_DELIVERED_AT);

        restInvoiceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedInvoice.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedInvoice))
            )
            .andExpect(status().isOk());

        // Validate the Invoice in the database
        List<Invoice> invoiceList = invoiceRepository.findAll();
        assertThat(invoiceList).hasSize(databaseSizeBeforeUpdate);
        Invoice testInvoice = invoiceList.get(invoiceList.size() - 1);
        assertThat(testInvoice.getObject()).isEqualTo(UPDATED_OBJECT);
        assertThat(testInvoice.getAccountCountry()).isEqualTo(UPDATED_ACCOUNT_COUNTRY);
        assertThat(testInvoice.getAccountName()).isEqualTo(UPDATED_ACCOUNT_NAME);
        assertThat(testInvoice.getAccountTaxIds()).isEqualTo(UPDATED_ACCOUNT_TAX_IDS);
        assertThat(testInvoice.getAmountDue()).isEqualTo(UPDATED_AMOUNT_DUE);
        assertThat(testInvoice.getAmountPaid()).isEqualTo(UPDATED_AMOUNT_PAID);
        assertThat(testInvoice.getAmountRemaining()).isEqualTo(UPDATED_AMOUNT_REMAINING);
        assertThat(testInvoice.getAmountShipping()).isEqualTo(UPDATED_AMOUNT_SHIPPING);
        assertThat(testInvoice.getApplication()).isEqualTo(UPDATED_APPLICATION);
        assertThat(testInvoice.getApplicationFeeAmount()).isEqualTo(UPDATED_APPLICATION_FEE_AMOUNT);
        assertThat(testInvoice.getAttemptCount()).isEqualTo(UPDATED_ATTEMPT_COUNT);
        assertThat(testInvoice.getAttempted()).isEqualTo(UPDATED_ATTEMPTED);
        assertThat(testInvoice.getAutoAdvance()).isEqualTo(UPDATED_AUTO_ADVANCE);
        assertThat(testInvoice.getBillingReason()).isEqualTo(UPDATED_BILLING_REASON);
        assertThat(testInvoice.getCharge()).isEqualTo(UPDATED_CHARGE);
        assertThat(testInvoice.getCollectionMethod()).isEqualTo(UPDATED_COLLECTION_METHOD);
        assertThat(testInvoice.getCreated()).isEqualTo(UPDATED_CREATED);
        assertThat(testInvoice.getCurrency()).isEqualTo(UPDATED_CURRENCY);
        assertThat(testInvoice.getCustomFields()).isEqualTo(UPDATED_CUSTOM_FIELDS);
        assertThat(testInvoice.getCustomerStringValue()).isEqualTo(UPDATED_CUSTOMER_STRING_VALUE);
        assertThat(testInvoice.getCustomerAddress()).isEqualTo(UPDATED_CUSTOMER_ADDRESS);
        assertThat(testInvoice.getCustomerEmail()).isEqualTo(UPDATED_CUSTOMER_EMAIL);
        assertThat(testInvoice.getCustomerName()).isEqualTo(UPDATED_CUSTOMER_NAME);
        assertThat(testInvoice.getCustomerPhone()).isEqualTo(UPDATED_CUSTOMER_PHONE);
        assertThat(testInvoice.getCustomerShipping()).isEqualTo(UPDATED_CUSTOMER_SHIPPING);
        assertThat(testInvoice.getCustomerTaxExempt()).isEqualTo(UPDATED_CUSTOMER_TAX_EXEMPT);
        assertThat(testInvoice.getCustomerTaxIds()).isEqualTo(UPDATED_CUSTOMER_TAX_IDS);
        assertThat(testInvoice.getDefaultPaymentMethod()).isEqualTo(UPDATED_DEFAULT_PAYMENT_METHOD);
        assertThat(testInvoice.getDefaultSource()).isEqualTo(UPDATED_DEFAULT_SOURCE);
        assertThat(testInvoice.getDefaultTaxRates()).isEqualTo(UPDATED_DEFAULT_TAX_RATES);
        assertThat(testInvoice.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testInvoice.getDiscount()).isEqualTo(UPDATED_DISCOUNT);
        assertThat(testInvoice.getDiscounts()).isEqualTo(UPDATED_DISCOUNTS);
        assertThat(testInvoice.getDueDate()).isEqualTo(UPDATED_DUE_DATE);
        assertThat(testInvoice.getEffectiveAt()).isEqualTo(UPDATED_EFFECTIVE_AT);
        assertThat(testInvoice.getEndingBalance()).isEqualTo(UPDATED_ENDING_BALANCE);
        assertThat(testInvoice.getFooter()).isEqualTo(UPDATED_FOOTER);
        assertThat(testInvoice.getFromInvoice()).isEqualTo(UPDATED_FROM_INVOICE);
        assertThat(testInvoice.getHostedInvoiceUrl()).isEqualTo(UPDATED_HOSTED_INVOICE_URL);
        assertThat(testInvoice.getInvoicePdf()).isEqualTo(UPDATED_INVOICE_PDF);
        assertThat(testInvoice.getLastFinalizationError()).isEqualTo(UPDATED_LAST_FINALIZATION_ERROR);
        assertThat(testInvoice.getLatestRevision()).isEqualTo(UPDATED_LATEST_REVISION);
        assertThat(testInvoice.getLivemode()).isEqualTo(UPDATED_LIVEMODE);
        assertThat(testInvoice.getMetadata()).isEqualTo(UPDATED_METADATA);
        assertThat(testInvoice.getNextPaymentAttempt()).isEqualTo(UPDATED_NEXT_PAYMENT_ATTEMPT);
        assertThat(testInvoice.getNumber()).isEqualTo(UPDATED_NUMBER);
        assertThat(testInvoice.getOnBehalfOf()).isEqualTo(UPDATED_ON_BEHALF_OF);
        assertThat(testInvoice.getPaid()).isEqualTo(UPDATED_PAID);
        assertThat(testInvoice.getPaidOutOfBand()).isEqualTo(UPDATED_PAID_OUT_OF_BAND);
        assertThat(testInvoice.getPaymentIntent()).isEqualTo(UPDATED_PAYMENT_INTENT);
        assertThat(testInvoice.getPaymentSettings()).isEqualTo(UPDATED_PAYMENT_SETTINGS);
        assertThat(testInvoice.getPeriodEnd()).isEqualTo(UPDATED_PERIOD_END);
        assertThat(testInvoice.getPeriodStart()).isEqualTo(UPDATED_PERIOD_START);
        assertThat(testInvoice.getPostPaymentCreditNotesAmount()).isEqualTo(UPDATED_POST_PAYMENT_CREDIT_NOTES_AMOUNT);
        assertThat(testInvoice.getPrePaymentCreditNotesAmount()).isEqualTo(UPDATED_PRE_PAYMENT_CREDIT_NOTES_AMOUNT);
        assertThat(testInvoice.getQuote()).isEqualTo(UPDATED_QUOTE);
        assertThat(testInvoice.getReceiptNumber()).isEqualTo(UPDATED_RECEIPT_NUMBER);
        assertThat(testInvoice.getRendering()).isEqualTo(UPDATED_RENDERING);
        assertThat(testInvoice.getRenderingOptions()).isEqualTo(UPDATED_RENDERING_OPTIONS);
        assertThat(testInvoice.getShippingCost()).isEqualTo(UPDATED_SHIPPING_COST);
        assertThat(testInvoice.getShippingDetails()).isEqualTo(UPDATED_SHIPPING_DETAILS);
        assertThat(testInvoice.getStartingBalance()).isEqualTo(UPDATED_STARTING_BALANCE);
        assertThat(testInvoice.getStatementDescriptor()).isEqualTo(UPDATED_STATEMENT_DESCRIPTOR);
        assertThat(testInvoice.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testInvoice.getStatusTransitions()).isEqualTo(UPDATED_STATUS_TRANSITIONS);
        assertThat(testInvoice.getSubscription()).isEqualTo(UPDATED_SUBSCRIPTION);
        assertThat(testInvoice.getSubscriptionDetails()).isEqualTo(UPDATED_SUBSCRIPTION_DETAILS);
        assertThat(testInvoice.getSubtotal()).isEqualTo(UPDATED_SUBTOTAL);
        assertThat(testInvoice.getSubtotalExcludingTax()).isEqualTo(UPDATED_SUBTOTAL_EXCLUDING_TAX);
        assertThat(testInvoice.getTax()).isEqualTo(UPDATED_TAX);
        assertThat(testInvoice.getTestClock()).isEqualTo(UPDATED_TEST_CLOCK);
        assertThat(testInvoice.getTotal()).isEqualTo(UPDATED_TOTAL);
        assertThat(testInvoice.getTotalDiscountAmounts()).isEqualTo(UPDATED_TOTAL_DISCOUNT_AMOUNTS);
        assertThat(testInvoice.getTotalExcludingTax()).isEqualTo(UPDATED_TOTAL_EXCLUDING_TAX);
        assertThat(testInvoice.getTotalTaxAmounts()).isEqualTo(UPDATED_TOTAL_TAX_AMOUNTS);
        assertThat(testInvoice.getTransferData()).isEqualTo(UPDATED_TRANSFER_DATA);
        assertThat(testInvoice.getWebhooksDeliveredAt()).isEqualTo(UPDATED_WEBHOOKS_DELIVERED_AT);
    }

    @Test
    @Transactional
    void patchNonExistingInvoice() throws Exception {
        int databaseSizeBeforeUpdate = invoiceRepository.findAll().size();
        invoice.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInvoiceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, invoice.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(invoice))
            )
            .andExpect(status().isBadRequest());

        // Validate the Invoice in the database
        List<Invoice> invoiceList = invoiceRepository.findAll();
        assertThat(invoiceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchInvoice() throws Exception {
        int databaseSizeBeforeUpdate = invoiceRepository.findAll().size();
        invoice.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInvoiceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(invoice))
            )
            .andExpect(status().isBadRequest());

        // Validate the Invoice in the database
        List<Invoice> invoiceList = invoiceRepository.findAll();
        assertThat(invoiceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamInvoice() throws Exception {
        int databaseSizeBeforeUpdate = invoiceRepository.findAll().size();
        invoice.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInvoiceMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(invoice)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Invoice in the database
        List<Invoice> invoiceList = invoiceRepository.findAll();
        assertThat(invoiceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteInvoice() throws Exception {
        // Initialize the database
        invoice.setId(UUID.randomUUID().toString());
        invoiceRepository.saveAndFlush(invoice);

        int databaseSizeBeforeDelete = invoiceRepository.findAll().size();

        // Delete the invoice
        restInvoiceMockMvc
            .perform(delete(ENTITY_API_URL_ID, invoice.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Invoice> invoiceList = invoiceRepository.findAll();
        assertThat(invoiceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
