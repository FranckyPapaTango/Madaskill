package com.rafaros.web.rest;

import static com.rafaros.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.rafaros.IntegrationTest;
import com.rafaros.domain.Customer;
import com.rafaros.repository.CustomerRepository;
import com.rafaros.service.CustomerService;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link CustomerResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class CustomerResourceIT {

    private static final String DEFAULT_OBJECT = "AAAAAAAAAA";
    private static final String UPDATED_OBJECT = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final Integer DEFAULT_BALANCE = 1;
    private static final Integer UPDATED_BALANCE = 2;

    private static final Long DEFAULT_CREATED = 1L;
    private static final Long UPDATED_CREATED = 2L;

    private static final String DEFAULT_CURRENCY = "AAAAAAAAAA";
    private static final String UPDATED_CURRENCY = "BBBBBBBBBB";

    private static final String DEFAULT_DEFAULT_SOURCE = "AAAAAAAAAA";
    private static final String UPDATED_DEFAULT_SOURCE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_DELINQUENT = false;
    private static final Boolean UPDATED_DELINQUENT = true;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_DISCOUNT = 1;
    private static final Integer UPDATED_DISCOUNT = 2;

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_INVOICE_PREFIX = "AAAAAAAAAA";
    private static final String UPDATED_INVOICE_PREFIX = "BBBBBBBBBB";

    private static final Boolean DEFAULT_LIVEMODE = false;
    private static final Boolean UPDATED_LIVEMODE = true;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_NEXT_INVOICE_SEQUENCE = 1;
    private static final Integer UPDATED_NEXT_INVOICE_SEQUENCE = 2;

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_SHIPPING = "AAAAAAAAAA";
    private static final String UPDATED_SHIPPING = "BBBBBBBBBB";

    private static final String DEFAULT_TAX_EXEMPT = "AAAAAAAAAA";
    private static final String UPDATED_TAX_EXEMPT = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_TEST_CLOCK = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_TEST_CLOCK = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String ENTITY_API_URL = "/api/customers";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private CustomerRepository customerRepository;

    @Mock
    private CustomerRepository customerRepositoryMock;

    @Mock
    private CustomerService customerServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCustomerMockMvc;

    private Customer customer;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Customer createEntity(EntityManager em) {
        Customer customer = new Customer()
            .object(DEFAULT_OBJECT)
            .address(DEFAULT_ADDRESS)
            .balance(DEFAULT_BALANCE)
            .created(DEFAULT_CREATED)
            .currency(DEFAULT_CURRENCY)
            .defaultSource(DEFAULT_DEFAULT_SOURCE)
            .delinquent(DEFAULT_DELINQUENT)
            .description(DEFAULT_DESCRIPTION)
            .discount(DEFAULT_DISCOUNT)
            .email(DEFAULT_EMAIL)
            .invoicePrefix(DEFAULT_INVOICE_PREFIX)
            .livemode(DEFAULT_LIVEMODE)
            .name(DEFAULT_NAME)
            .nextInvoiceSequence(DEFAULT_NEXT_INVOICE_SEQUENCE)
            .phone(DEFAULT_PHONE)
            .shipping(DEFAULT_SHIPPING)
            .taxExempt(DEFAULT_TAX_EXEMPT)
            .testClock(DEFAULT_TEST_CLOCK);
        return customer;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Customer createUpdatedEntity(EntityManager em) {
        Customer customer = new Customer()
            .object(UPDATED_OBJECT)
            .address(UPDATED_ADDRESS)
            .balance(UPDATED_BALANCE)
            .created(UPDATED_CREATED)
            .currency(UPDATED_CURRENCY)
            .defaultSource(UPDATED_DEFAULT_SOURCE)
            .delinquent(UPDATED_DELINQUENT)
            .description(UPDATED_DESCRIPTION)
            .discount(UPDATED_DISCOUNT)
            .email(UPDATED_EMAIL)
            .invoicePrefix(UPDATED_INVOICE_PREFIX)
            .livemode(UPDATED_LIVEMODE)
            .name(UPDATED_NAME)
            .nextInvoiceSequence(UPDATED_NEXT_INVOICE_SEQUENCE)
            .phone(UPDATED_PHONE)
            .shipping(UPDATED_SHIPPING)
            .taxExempt(UPDATED_TAX_EXEMPT)
            .testClock(UPDATED_TEST_CLOCK);
        return customer;
    }

    @BeforeEach
    public void initTest() {
        customer = createEntity(em);
    }

    @Test
    @Transactional
    void createCustomer() throws Exception {
        int databaseSizeBeforeCreate = customerRepository.findAll().size();
        // Create the Customer
        restCustomerMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(customer)))
            .andExpect(status().isCreated());

        // Validate the Customer in the database
        List<Customer> customerList = customerRepository.findAll();
        assertThat(customerList).hasSize(databaseSizeBeforeCreate + 1);
        Customer testCustomer = customerList.get(customerList.size() - 1);
        assertThat(testCustomer.getObject()).isEqualTo(DEFAULT_OBJECT);
        assertThat(testCustomer.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testCustomer.getBalance()).isEqualTo(DEFAULT_BALANCE);
        assertThat(testCustomer.getCreated()).isEqualTo(DEFAULT_CREATED);
        assertThat(testCustomer.getCurrency()).isEqualTo(DEFAULT_CURRENCY);
        assertThat(testCustomer.getDefaultSource()).isEqualTo(DEFAULT_DEFAULT_SOURCE);
        assertThat(testCustomer.getDelinquent()).isEqualTo(DEFAULT_DELINQUENT);
        assertThat(testCustomer.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testCustomer.getDiscount()).isEqualTo(DEFAULT_DISCOUNT);
        assertThat(testCustomer.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testCustomer.getInvoicePrefix()).isEqualTo(DEFAULT_INVOICE_PREFIX);
        assertThat(testCustomer.getLivemode()).isEqualTo(DEFAULT_LIVEMODE);
        assertThat(testCustomer.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testCustomer.getNextInvoiceSequence()).isEqualTo(DEFAULT_NEXT_INVOICE_SEQUENCE);
        assertThat(testCustomer.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testCustomer.getShipping()).isEqualTo(DEFAULT_SHIPPING);
        assertThat(testCustomer.getTaxExempt()).isEqualTo(DEFAULT_TAX_EXEMPT);
        assertThat(testCustomer.getTestClock()).isEqualTo(DEFAULT_TEST_CLOCK);
    }

    @Test
    @Transactional
    void createCustomerWithExistingId() throws Exception {
        // Create the Customer with an existing ID
        customer.setId("existing_id");

        int databaseSizeBeforeCreate = customerRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCustomerMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(customer)))
            .andExpect(status().isBadRequest());

        // Validate the Customer in the database
        List<Customer> customerList = customerRepository.findAll();
        assertThat(customerList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCustomers() throws Exception {
        // Initialize the database
        customer.setId(UUID.randomUUID().toString());
        customerRepository.saveAndFlush(customer);

        // Get all the customerList
        restCustomerMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(customer.getId())))
            .andExpect(jsonPath("$.[*].object").value(hasItem(DEFAULT_OBJECT)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].balance").value(hasItem(DEFAULT_BALANCE)))
            .andExpect(jsonPath("$.[*].created").value(hasItem(DEFAULT_CREATED.intValue())))
            .andExpect(jsonPath("$.[*].currency").value(hasItem(DEFAULT_CURRENCY)))
            .andExpect(jsonPath("$.[*].defaultSource").value(hasItem(DEFAULT_DEFAULT_SOURCE)))
            .andExpect(jsonPath("$.[*].delinquent").value(hasItem(DEFAULT_DELINQUENT.booleanValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].discount").value(hasItem(DEFAULT_DISCOUNT)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].invoicePrefix").value(hasItem(DEFAULT_INVOICE_PREFIX)))
            .andExpect(jsonPath("$.[*].livemode").value(hasItem(DEFAULT_LIVEMODE.booleanValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].nextInvoiceSequence").value(hasItem(DEFAULT_NEXT_INVOICE_SEQUENCE)))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE)))
            .andExpect(jsonPath("$.[*].shipping").value(hasItem(DEFAULT_SHIPPING)))
            .andExpect(jsonPath("$.[*].taxExempt").value(hasItem(DEFAULT_TAX_EXEMPT)))
            .andExpect(jsonPath("$.[*].testClock").value(hasItem(sameInstant(DEFAULT_TEST_CLOCK))));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllCustomersWithEagerRelationshipsIsEnabled() throws Exception {
        when(customerServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restCustomerMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(customerServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllCustomersWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(customerServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restCustomerMockMvc.perform(get(ENTITY_API_URL + "?eagerload=false")).andExpect(status().isOk());
        verify(customerRepositoryMock, times(1)).findAll(any(Pageable.class));
    }

    @Test
    @Transactional
    void getCustomer() throws Exception {
        // Initialize the database
        customer.setId(UUID.randomUUID().toString());
        customerRepository.saveAndFlush(customer);

        // Get the customer
        restCustomerMockMvc
            .perform(get(ENTITY_API_URL_ID, customer.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(customer.getId()))
            .andExpect(jsonPath("$.object").value(DEFAULT_OBJECT))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS))
            .andExpect(jsonPath("$.balance").value(DEFAULT_BALANCE))
            .andExpect(jsonPath("$.created").value(DEFAULT_CREATED.intValue()))
            .andExpect(jsonPath("$.currency").value(DEFAULT_CURRENCY))
            .andExpect(jsonPath("$.defaultSource").value(DEFAULT_DEFAULT_SOURCE))
            .andExpect(jsonPath("$.delinquent").value(DEFAULT_DELINQUENT.booleanValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.discount").value(DEFAULT_DISCOUNT))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.invoicePrefix").value(DEFAULT_INVOICE_PREFIX))
            .andExpect(jsonPath("$.livemode").value(DEFAULT_LIVEMODE.booleanValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.nextInvoiceSequence").value(DEFAULT_NEXT_INVOICE_SEQUENCE))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE))
            .andExpect(jsonPath("$.shipping").value(DEFAULT_SHIPPING))
            .andExpect(jsonPath("$.taxExempt").value(DEFAULT_TAX_EXEMPT))
            .andExpect(jsonPath("$.testClock").value(sameInstant(DEFAULT_TEST_CLOCK)));
    }

    @Test
    @Transactional
    void getNonExistingCustomer() throws Exception {
        // Get the customer
        restCustomerMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCustomer() throws Exception {
        // Initialize the database
        customer.setId(UUID.randomUUID().toString());
        customerRepository.saveAndFlush(customer);

        int databaseSizeBeforeUpdate = customerRepository.findAll().size();

        // Update the customer
        Customer updatedCustomer = customerRepository.findById(customer.getId()).get();
        // Disconnect from session so that the updates on updatedCustomer are not directly saved in db
        em.detach(updatedCustomer);
        updatedCustomer
            .object(UPDATED_OBJECT)
            .address(UPDATED_ADDRESS)
            .balance(UPDATED_BALANCE)
            .created(UPDATED_CREATED)
            .currency(UPDATED_CURRENCY)
            .defaultSource(UPDATED_DEFAULT_SOURCE)
            .delinquent(UPDATED_DELINQUENT)
            .description(UPDATED_DESCRIPTION)
            .discount(UPDATED_DISCOUNT)
            .email(UPDATED_EMAIL)
            .invoicePrefix(UPDATED_INVOICE_PREFIX)
            .livemode(UPDATED_LIVEMODE)
            .name(UPDATED_NAME)
            .nextInvoiceSequence(UPDATED_NEXT_INVOICE_SEQUENCE)
            .phone(UPDATED_PHONE)
            .shipping(UPDATED_SHIPPING)
            .taxExempt(UPDATED_TAX_EXEMPT)
            .testClock(UPDATED_TEST_CLOCK);

        restCustomerMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedCustomer.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedCustomer))
            )
            .andExpect(status().isOk());

        // Validate the Customer in the database
        List<Customer> customerList = customerRepository.findAll();
        assertThat(customerList).hasSize(databaseSizeBeforeUpdate);
        Customer testCustomer = customerList.get(customerList.size() - 1);
        assertThat(testCustomer.getObject()).isEqualTo(UPDATED_OBJECT);
        assertThat(testCustomer.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testCustomer.getBalance()).isEqualTo(UPDATED_BALANCE);
        assertThat(testCustomer.getCreated()).isEqualTo(UPDATED_CREATED);
        assertThat(testCustomer.getCurrency()).isEqualTo(UPDATED_CURRENCY);
        assertThat(testCustomer.getDefaultSource()).isEqualTo(UPDATED_DEFAULT_SOURCE);
        assertThat(testCustomer.getDelinquent()).isEqualTo(UPDATED_DELINQUENT);
        assertThat(testCustomer.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testCustomer.getDiscount()).isEqualTo(UPDATED_DISCOUNT);
        assertThat(testCustomer.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testCustomer.getInvoicePrefix()).isEqualTo(UPDATED_INVOICE_PREFIX);
        assertThat(testCustomer.getLivemode()).isEqualTo(UPDATED_LIVEMODE);
        assertThat(testCustomer.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCustomer.getNextInvoiceSequence()).isEqualTo(UPDATED_NEXT_INVOICE_SEQUENCE);
        assertThat(testCustomer.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testCustomer.getShipping()).isEqualTo(UPDATED_SHIPPING);
        assertThat(testCustomer.getTaxExempt()).isEqualTo(UPDATED_TAX_EXEMPT);
        assertThat(testCustomer.getTestClock()).isEqualTo(UPDATED_TEST_CLOCK);
    }

    @Test
    @Transactional
    void putNonExistingCustomer() throws Exception {
        int databaseSizeBeforeUpdate = customerRepository.findAll().size();
        customer.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCustomerMockMvc
            .perform(
                put(ENTITY_API_URL_ID, customer.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(customer))
            )
            .andExpect(status().isBadRequest());

        // Validate the Customer in the database
        List<Customer> customerList = customerRepository.findAll();
        assertThat(customerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCustomer() throws Exception {
        int databaseSizeBeforeUpdate = customerRepository.findAll().size();
        customer.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCustomerMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(customer))
            )
            .andExpect(status().isBadRequest());

        // Validate the Customer in the database
        List<Customer> customerList = customerRepository.findAll();
        assertThat(customerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCustomer() throws Exception {
        int databaseSizeBeforeUpdate = customerRepository.findAll().size();
        customer.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCustomerMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(customer)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Customer in the database
        List<Customer> customerList = customerRepository.findAll();
        assertThat(customerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCustomerWithPatch() throws Exception {
        // Initialize the database
        customer.setId(UUID.randomUUID().toString());
        customerRepository.saveAndFlush(customer);

        int databaseSizeBeforeUpdate = customerRepository.findAll().size();

        // Update the customer using partial update
        Customer partialUpdatedCustomer = new Customer();
        partialUpdatedCustomer.setId(customer.getId());

        partialUpdatedCustomer
            .object(UPDATED_OBJECT)
            .currency(UPDATED_CURRENCY)
            .description(UPDATED_DESCRIPTION)
            .discount(UPDATED_DISCOUNT)
            .name(UPDATED_NAME)
            .shipping(UPDATED_SHIPPING)
            .testClock(UPDATED_TEST_CLOCK);

        restCustomerMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCustomer.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCustomer))
            )
            .andExpect(status().isOk());

        // Validate the Customer in the database
        List<Customer> customerList = customerRepository.findAll();
        assertThat(customerList).hasSize(databaseSizeBeforeUpdate);
        Customer testCustomer = customerList.get(customerList.size() - 1);
        assertThat(testCustomer.getObject()).isEqualTo(UPDATED_OBJECT);
        assertThat(testCustomer.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testCustomer.getBalance()).isEqualTo(DEFAULT_BALANCE);
        assertThat(testCustomer.getCreated()).isEqualTo(DEFAULT_CREATED);
        assertThat(testCustomer.getCurrency()).isEqualTo(UPDATED_CURRENCY);
        assertThat(testCustomer.getDefaultSource()).isEqualTo(DEFAULT_DEFAULT_SOURCE);
        assertThat(testCustomer.getDelinquent()).isEqualTo(DEFAULT_DELINQUENT);
        assertThat(testCustomer.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testCustomer.getDiscount()).isEqualTo(UPDATED_DISCOUNT);
        assertThat(testCustomer.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testCustomer.getInvoicePrefix()).isEqualTo(DEFAULT_INVOICE_PREFIX);
        assertThat(testCustomer.getLivemode()).isEqualTo(DEFAULT_LIVEMODE);
        assertThat(testCustomer.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCustomer.getNextInvoiceSequence()).isEqualTo(DEFAULT_NEXT_INVOICE_SEQUENCE);
        assertThat(testCustomer.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testCustomer.getShipping()).isEqualTo(UPDATED_SHIPPING);
        assertThat(testCustomer.getTaxExempt()).isEqualTo(DEFAULT_TAX_EXEMPT);
        assertThat(testCustomer.getTestClock()).isEqualTo(UPDATED_TEST_CLOCK);
    }

    @Test
    @Transactional
    void fullUpdateCustomerWithPatch() throws Exception {
        // Initialize the database
        customer.setId(UUID.randomUUID().toString());
        customerRepository.saveAndFlush(customer);

        int databaseSizeBeforeUpdate = customerRepository.findAll().size();

        // Update the customer using partial update
        Customer partialUpdatedCustomer = new Customer();
        partialUpdatedCustomer.setId(customer.getId());

        partialUpdatedCustomer
            .object(UPDATED_OBJECT)
            .address(UPDATED_ADDRESS)
            .balance(UPDATED_BALANCE)
            .created(UPDATED_CREATED)
            .currency(UPDATED_CURRENCY)
            .defaultSource(UPDATED_DEFAULT_SOURCE)
            .delinquent(UPDATED_DELINQUENT)
            .description(UPDATED_DESCRIPTION)
            .discount(UPDATED_DISCOUNT)
            .email(UPDATED_EMAIL)
            .invoicePrefix(UPDATED_INVOICE_PREFIX)
            .livemode(UPDATED_LIVEMODE)
            .name(UPDATED_NAME)
            .nextInvoiceSequence(UPDATED_NEXT_INVOICE_SEQUENCE)
            .phone(UPDATED_PHONE)
            .shipping(UPDATED_SHIPPING)
            .taxExempt(UPDATED_TAX_EXEMPT)
            .testClock(UPDATED_TEST_CLOCK);

        restCustomerMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCustomer.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCustomer))
            )
            .andExpect(status().isOk());

        // Validate the Customer in the database
        List<Customer> customerList = customerRepository.findAll();
        assertThat(customerList).hasSize(databaseSizeBeforeUpdate);
        Customer testCustomer = customerList.get(customerList.size() - 1);
        assertThat(testCustomer.getObject()).isEqualTo(UPDATED_OBJECT);
        assertThat(testCustomer.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testCustomer.getBalance()).isEqualTo(UPDATED_BALANCE);
        assertThat(testCustomer.getCreated()).isEqualTo(UPDATED_CREATED);
        assertThat(testCustomer.getCurrency()).isEqualTo(UPDATED_CURRENCY);
        assertThat(testCustomer.getDefaultSource()).isEqualTo(UPDATED_DEFAULT_SOURCE);
        assertThat(testCustomer.getDelinquent()).isEqualTo(UPDATED_DELINQUENT);
        assertThat(testCustomer.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testCustomer.getDiscount()).isEqualTo(UPDATED_DISCOUNT);
        assertThat(testCustomer.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testCustomer.getInvoicePrefix()).isEqualTo(UPDATED_INVOICE_PREFIX);
        assertThat(testCustomer.getLivemode()).isEqualTo(UPDATED_LIVEMODE);
        assertThat(testCustomer.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCustomer.getNextInvoiceSequence()).isEqualTo(UPDATED_NEXT_INVOICE_SEQUENCE);
        assertThat(testCustomer.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testCustomer.getShipping()).isEqualTo(UPDATED_SHIPPING);
        assertThat(testCustomer.getTaxExempt()).isEqualTo(UPDATED_TAX_EXEMPT);
        assertThat(testCustomer.getTestClock()).isEqualTo(UPDATED_TEST_CLOCK);
    }

    @Test
    @Transactional
    void patchNonExistingCustomer() throws Exception {
        int databaseSizeBeforeUpdate = customerRepository.findAll().size();
        customer.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCustomerMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, customer.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(customer))
            )
            .andExpect(status().isBadRequest());

        // Validate the Customer in the database
        List<Customer> customerList = customerRepository.findAll();
        assertThat(customerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCustomer() throws Exception {
        int databaseSizeBeforeUpdate = customerRepository.findAll().size();
        customer.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCustomerMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(customer))
            )
            .andExpect(status().isBadRequest());

        // Validate the Customer in the database
        List<Customer> customerList = customerRepository.findAll();
        assertThat(customerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCustomer() throws Exception {
        int databaseSizeBeforeUpdate = customerRepository.findAll().size();
        customer.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCustomerMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(customer)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Customer in the database
        List<Customer> customerList = customerRepository.findAll();
        assertThat(customerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCustomer() throws Exception {
        // Initialize the database
        customer.setId(UUID.randomUUID().toString());
        customerRepository.saveAndFlush(customer);

        int databaseSizeBeforeDelete = customerRepository.findAll().size();

        // Delete the customer
        restCustomerMockMvc
            .perform(delete(ENTITY_API_URL_ID, customer.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Customer> customerList = customerRepository.findAll();
        assertThat(customerList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
