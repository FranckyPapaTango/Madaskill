package com.rafaros.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.rafaros.IntegrationTest;
import com.rafaros.domain.Product;
import com.rafaros.repository.ProductRepository;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
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
 * Integration tests for the {@link ProductResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ProductResourceIT {

    private static final String DEFAULT_OBJECT = "AAAAAAAAAA";
    private static final String UPDATED_OBJECT = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ACTIVE = false;
    private static final Boolean UPDATED_ACTIVE = true;

    private static final Long DEFAULT_CREATED = 1L;
    private static final Long UPDATED_CREATED = 2L;

    private static final Double DEFAULT_DEFAULT_PRICE = 1D;
    private static final Double UPDATED_DEFAULT_PRICE = 2D;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Boolean DEFAULT_LIVEMODE = false;
    private static final Boolean UPDATED_LIVEMODE = true;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Boolean DEFAULT_SHIPPABLE = false;
    private static final Boolean UPDATED_SHIPPABLE = true;

    private static final String DEFAULT_STATEMENT_DESCRIPTOR = "AAAAAAAAAA";
    private static final String UPDATED_STATEMENT_DESCRIPTOR = "BBBBBBBBBB";

    private static final String DEFAULT_TAX_CODE = "AAAAAAAAAA";
    private static final String UPDATED_TAX_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_UNIT_LABEL = "AAAAAAAAAA";
    private static final String UPDATED_UNIT_LABEL = "BBBBBBBBBB";

    private static final Long DEFAULT_UPDATED = 1L;
    private static final Long UPDATED_UPDATED = 2L;

    private static final String DEFAULT_URL = "AAAAAAAAAA";
    private static final String UPDATED_URL = "BBBBBBBBBB";

    private static final String DEFAULT_SKU = "AAAAAAAAAA";
    private static final String UPDATED_SKU = "BBBBBBBBBB";

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_LINK_TO_GENERIC_PHOTO_FILE = "AAAAAAAAAA";
    private static final String UPDATED_LINK_TO_GENERIC_PHOTO_FILE = "BBBBBBBBBB";

    private static final String DEFAULT_AVAILABLE_SIZES = "AAAAAAAAAA";
    private static final String UPDATED_AVAILABLE_SIZES = "BBBBBBBBBB";

    private static final String DEFAULT_CURRENCY_FORMAT = "AAAAAAAAAA";
    private static final String UPDATED_CURRENCY_FORMAT = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_FREE_SHIPPING = false;
    private static final Boolean UPDATED_IS_FREE_SHIPPING = true;

    private static final Double DEFAULT_PRICE = 1D;
    private static final Double UPDATED_PRICE = 2D;

    private static final String DEFAULT_STYLE = "AAAAAAAAAA";
    private static final String UPDATED_STYLE = "BBBBBBBBBB";

    private static final Integer DEFAULT_INSTALLMENTS = 1;
    private static final Integer UPDATED_INSTALLMENTS = 2;

    private static final String ENTITY_API_URL = "/api/products";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProductMockMvc;

    private Product product;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Product createEntity(EntityManager em) {
        Product product = new Product()
            .object(DEFAULT_OBJECT)
            .active(DEFAULT_ACTIVE)
            .created(DEFAULT_CREATED)
            .defaultPrice(DEFAULT_DEFAULT_PRICE)
            .description(DEFAULT_DESCRIPTION)
            .livemode(DEFAULT_LIVEMODE)
            .name(DEFAULT_NAME)
            .shippable(DEFAULT_SHIPPABLE)
            .statementDescriptor(DEFAULT_STATEMENT_DESCRIPTOR)
            .taxCode(DEFAULT_TAX_CODE)
            .unitLabel(DEFAULT_UNIT_LABEL)
            .updated(DEFAULT_UPDATED)
            .url(DEFAULT_URL)
            .sku(DEFAULT_SKU)
            .title(DEFAULT_TITLE)
            .linkToGenericPhotoFile(DEFAULT_LINK_TO_GENERIC_PHOTO_FILE)
            .availableSizes(DEFAULT_AVAILABLE_SIZES)
            .currencyFormat(DEFAULT_CURRENCY_FORMAT)
            .isFreeShipping(DEFAULT_IS_FREE_SHIPPING)
            .price(DEFAULT_PRICE)
            .style(DEFAULT_STYLE)
            .installments(DEFAULT_INSTALLMENTS);
        return product;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Product createUpdatedEntity(EntityManager em) {
        Product product = new Product()
            .object(UPDATED_OBJECT)
            .active(UPDATED_ACTIVE)
            .created(UPDATED_CREATED)
            .defaultPrice(UPDATED_DEFAULT_PRICE)
            .description(UPDATED_DESCRIPTION)
            .livemode(UPDATED_LIVEMODE)
            .name(UPDATED_NAME)
            .shippable(UPDATED_SHIPPABLE)
            .statementDescriptor(UPDATED_STATEMENT_DESCRIPTOR)
            .taxCode(UPDATED_TAX_CODE)
            .unitLabel(UPDATED_UNIT_LABEL)
            .updated(UPDATED_UPDATED)
            .url(UPDATED_URL)
            .sku(UPDATED_SKU)
            .title(UPDATED_TITLE)
            .linkToGenericPhotoFile(UPDATED_LINK_TO_GENERIC_PHOTO_FILE)
            .availableSizes(UPDATED_AVAILABLE_SIZES)
            .currencyFormat(UPDATED_CURRENCY_FORMAT)
            .isFreeShipping(UPDATED_IS_FREE_SHIPPING)
            .price(UPDATED_PRICE)
            .style(UPDATED_STYLE)
            .installments(UPDATED_INSTALLMENTS);
        return product;
    }

    @BeforeEach
    public void initTest() {
        product = createEntity(em);
    }

    @Test
    @Transactional
    void createProduct() throws Exception {
        int databaseSizeBeforeCreate = productRepository.findAll().size();
        // Create the Product
        restProductMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isCreated());

        // Validate the Product in the database
        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeCreate + 1);
        Product testProduct = productList.get(productList.size() - 1);
        assertThat(testProduct.getObject()).isEqualTo(DEFAULT_OBJECT);
        assertThat(testProduct.getActive()).isEqualTo(DEFAULT_ACTIVE);
        assertThat(testProduct.getCreated()).isEqualTo(DEFAULT_CREATED);
        assertThat(testProduct.getDefaultPrice()).isEqualTo(DEFAULT_DEFAULT_PRICE);
        assertThat(testProduct.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testProduct.getLivemode()).isEqualTo(DEFAULT_LIVEMODE);
        assertThat(testProduct.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testProduct.getShippable()).isEqualTo(DEFAULT_SHIPPABLE);
        assertThat(testProduct.getStatementDescriptor()).isEqualTo(DEFAULT_STATEMENT_DESCRIPTOR);
        assertThat(testProduct.getTaxCode()).isEqualTo(DEFAULT_TAX_CODE);
        assertThat(testProduct.getUnitLabel()).isEqualTo(DEFAULT_UNIT_LABEL);
        assertThat(testProduct.getUpdated()).isEqualTo(DEFAULT_UPDATED);
        assertThat(testProduct.getUrl()).isEqualTo(DEFAULT_URL);
        assertThat(testProduct.getSku()).isEqualTo(DEFAULT_SKU);
        assertThat(testProduct.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testProduct.getLinkToGenericPhotoFile()).isEqualTo(DEFAULT_LINK_TO_GENERIC_PHOTO_FILE);
        assertThat(testProduct.getAvailableSizes()).isEqualTo(DEFAULT_AVAILABLE_SIZES);
        assertThat(testProduct.getCurrencyFormat()).isEqualTo(DEFAULT_CURRENCY_FORMAT);
        assertThat(testProduct.getIsFreeShipping()).isEqualTo(DEFAULT_IS_FREE_SHIPPING);
        assertThat(testProduct.getPrice()).isEqualTo(DEFAULT_PRICE);
        assertThat(testProduct.getStyle()).isEqualTo(DEFAULT_STYLE);
        assertThat(testProduct.getInstallments()).isEqualTo(DEFAULT_INSTALLMENTS);
    }

    @Test
    @Transactional
    void createProductWithExistingId() throws Exception {
        // Create the Product with an existing ID
        product.setId(1L);

        int databaseSizeBeforeCreate = productRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restProductMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isBadRequest());

        // Validate the Product in the database
        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkTitleIsRequired() throws Exception {
        int databaseSizeBeforeTest = productRepository.findAll().size();
        // set the field null
        product.setTitle(null);

        // Create the Product, which fails.

        restProductMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isBadRequest());

        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPriceIsRequired() throws Exception {
        int databaseSizeBeforeTest = productRepository.findAll().size();
        // set the field null
        product.setPrice(null);

        // Create the Product, which fails.

        restProductMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isBadRequest());

        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllProducts() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList
        restProductMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(product.getId().intValue())))
            .andExpect(jsonPath("$.[*].object").value(hasItem(DEFAULT_OBJECT)))
            .andExpect(jsonPath("$.[*].active").value(hasItem(DEFAULT_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].created").value(hasItem(DEFAULT_CREATED.intValue())))
            .andExpect(jsonPath("$.[*].defaultPrice").value(hasItem(DEFAULT_DEFAULT_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].livemode").value(hasItem(DEFAULT_LIVEMODE.booleanValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].shippable").value(hasItem(DEFAULT_SHIPPABLE.booleanValue())))
            .andExpect(jsonPath("$.[*].statementDescriptor").value(hasItem(DEFAULT_STATEMENT_DESCRIPTOR)))
            .andExpect(jsonPath("$.[*].taxCode").value(hasItem(DEFAULT_TAX_CODE)))
            .andExpect(jsonPath("$.[*].unitLabel").value(hasItem(DEFAULT_UNIT_LABEL)))
            .andExpect(jsonPath("$.[*].updated").value(hasItem(DEFAULT_UPDATED.intValue())))
            .andExpect(jsonPath("$.[*].url").value(hasItem(DEFAULT_URL)))
            .andExpect(jsonPath("$.[*].sku").value(hasItem(DEFAULT_SKU)))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].linkToGenericPhotoFile").value(hasItem(DEFAULT_LINK_TO_GENERIC_PHOTO_FILE)))
            .andExpect(jsonPath("$.[*].availableSizes").value(hasItem(DEFAULT_AVAILABLE_SIZES)))
            .andExpect(jsonPath("$.[*].currencyFormat").value(hasItem(DEFAULT_CURRENCY_FORMAT)))
            .andExpect(jsonPath("$.[*].isFreeShipping").value(hasItem(DEFAULT_IS_FREE_SHIPPING.booleanValue())))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].style").value(hasItem(DEFAULT_STYLE)))
            .andExpect(jsonPath("$.[*].installments").value(hasItem(DEFAULT_INSTALLMENTS)));
    }

    @Test
    @Transactional
    void getProduct() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get the product
        restProductMockMvc
            .perform(get(ENTITY_API_URL_ID, product.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(product.getId().intValue()))
            .andExpect(jsonPath("$.object").value(DEFAULT_OBJECT))
            .andExpect(jsonPath("$.active").value(DEFAULT_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.created").value(DEFAULT_CREATED.intValue()))
            .andExpect(jsonPath("$.defaultPrice").value(DEFAULT_DEFAULT_PRICE.doubleValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.livemode").value(DEFAULT_LIVEMODE.booleanValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.shippable").value(DEFAULT_SHIPPABLE.booleanValue()))
            .andExpect(jsonPath("$.statementDescriptor").value(DEFAULT_STATEMENT_DESCRIPTOR))
            .andExpect(jsonPath("$.taxCode").value(DEFAULT_TAX_CODE))
            .andExpect(jsonPath("$.unitLabel").value(DEFAULT_UNIT_LABEL))
            .andExpect(jsonPath("$.updated").value(DEFAULT_UPDATED.intValue()))
            .andExpect(jsonPath("$.url").value(DEFAULT_URL))
            .andExpect(jsonPath("$.sku").value(DEFAULT_SKU))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
            .andExpect(jsonPath("$.linkToGenericPhotoFile").value(DEFAULT_LINK_TO_GENERIC_PHOTO_FILE))
            .andExpect(jsonPath("$.availableSizes").value(DEFAULT_AVAILABLE_SIZES))
            .andExpect(jsonPath("$.currencyFormat").value(DEFAULT_CURRENCY_FORMAT))
            .andExpect(jsonPath("$.isFreeShipping").value(DEFAULT_IS_FREE_SHIPPING.booleanValue()))
            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE.doubleValue()))
            .andExpect(jsonPath("$.style").value(DEFAULT_STYLE))
            .andExpect(jsonPath("$.installments").value(DEFAULT_INSTALLMENTS));
    }

    @Test
    @Transactional
    void getNonExistingProduct() throws Exception {
        // Get the product
        restProductMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingProduct() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        int databaseSizeBeforeUpdate = productRepository.findAll().size();

        // Update the product
        Product updatedProduct = productRepository.findById(product.getId()).get();
        // Disconnect from session so that the updates on updatedProduct are not directly saved in db
        em.detach(updatedProduct);
        updatedProduct
            .object(UPDATED_OBJECT)
            .active(UPDATED_ACTIVE)
            .created(UPDATED_CREATED)
            .defaultPrice(UPDATED_DEFAULT_PRICE)
            .description(UPDATED_DESCRIPTION)
            .livemode(UPDATED_LIVEMODE)
            .name(UPDATED_NAME)
            .shippable(UPDATED_SHIPPABLE)
            .statementDescriptor(UPDATED_STATEMENT_DESCRIPTOR)
            .taxCode(UPDATED_TAX_CODE)
            .unitLabel(UPDATED_UNIT_LABEL)
            .updated(UPDATED_UPDATED)
            .url(UPDATED_URL)
            .sku(UPDATED_SKU)
            .title(UPDATED_TITLE)
            .linkToGenericPhotoFile(UPDATED_LINK_TO_GENERIC_PHOTO_FILE)
            .availableSizes(UPDATED_AVAILABLE_SIZES)
            .currencyFormat(UPDATED_CURRENCY_FORMAT)
            .isFreeShipping(UPDATED_IS_FREE_SHIPPING)
            .price(UPDATED_PRICE)
            .style(UPDATED_STYLE)
            .installments(UPDATED_INSTALLMENTS);

        restProductMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedProduct.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedProduct))
            )
            .andExpect(status().isOk());

        // Validate the Product in the database
        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeUpdate);
        Product testProduct = productList.get(productList.size() - 1);
        assertThat(testProduct.getObject()).isEqualTo(UPDATED_OBJECT);
        assertThat(testProduct.getActive()).isEqualTo(UPDATED_ACTIVE);
        assertThat(testProduct.getCreated()).isEqualTo(UPDATED_CREATED);
        assertThat(testProduct.getDefaultPrice()).isEqualTo(UPDATED_DEFAULT_PRICE);
        assertThat(testProduct.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testProduct.getLivemode()).isEqualTo(UPDATED_LIVEMODE);
        assertThat(testProduct.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testProduct.getShippable()).isEqualTo(UPDATED_SHIPPABLE);
        assertThat(testProduct.getStatementDescriptor()).isEqualTo(UPDATED_STATEMENT_DESCRIPTOR);
        assertThat(testProduct.getTaxCode()).isEqualTo(UPDATED_TAX_CODE);
        assertThat(testProduct.getUnitLabel()).isEqualTo(UPDATED_UNIT_LABEL);
        assertThat(testProduct.getUpdated()).isEqualTo(UPDATED_UPDATED);
        assertThat(testProduct.getUrl()).isEqualTo(UPDATED_URL);
        assertThat(testProduct.getSku()).isEqualTo(UPDATED_SKU);
        assertThat(testProduct.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testProduct.getLinkToGenericPhotoFile()).isEqualTo(UPDATED_LINK_TO_GENERIC_PHOTO_FILE);
        assertThat(testProduct.getAvailableSizes()).isEqualTo(UPDATED_AVAILABLE_SIZES);
        assertThat(testProduct.getCurrencyFormat()).isEqualTo(UPDATED_CURRENCY_FORMAT);
        assertThat(testProduct.getIsFreeShipping()).isEqualTo(UPDATED_IS_FREE_SHIPPING);
        assertThat(testProduct.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testProduct.getStyle()).isEqualTo(UPDATED_STYLE);
        assertThat(testProduct.getInstallments()).isEqualTo(UPDATED_INSTALLMENTS);
    }

    @Test
    @Transactional
    void putNonExistingProduct() throws Exception {
        int databaseSizeBeforeUpdate = productRepository.findAll().size();
        product.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProductMockMvc
            .perform(
                put(ENTITY_API_URL_ID, product.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(product))
            )
            .andExpect(status().isBadRequest());

        // Validate the Product in the database
        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchProduct() throws Exception {
        int databaseSizeBeforeUpdate = productRepository.findAll().size();
        product.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProductMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(product))
            )
            .andExpect(status().isBadRequest());

        // Validate the Product in the database
        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamProduct() throws Exception {
        int databaseSizeBeforeUpdate = productRepository.findAll().size();
        product.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProductMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Product in the database
        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateProductWithPatch() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        int databaseSizeBeforeUpdate = productRepository.findAll().size();

        // Update the product using partial update
        Product partialUpdatedProduct = new Product();
        partialUpdatedProduct.setId(product.getId());

        partialUpdatedProduct
            .description(UPDATED_DESCRIPTION)
            .livemode(UPDATED_LIVEMODE)
            .unitLabel(UPDATED_UNIT_LABEL)
            .sku(UPDATED_SKU)
            .currencyFormat(UPDATED_CURRENCY_FORMAT)
            .isFreeShipping(UPDATED_IS_FREE_SHIPPING)
            .price(UPDATED_PRICE)
            .installments(UPDATED_INSTALLMENTS);

        restProductMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProduct.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedProduct))
            )
            .andExpect(status().isOk());

        // Validate the Product in the database
        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeUpdate);
        Product testProduct = productList.get(productList.size() - 1);
        assertThat(testProduct.getObject()).isEqualTo(DEFAULT_OBJECT);
        assertThat(testProduct.getActive()).isEqualTo(DEFAULT_ACTIVE);
        assertThat(testProduct.getCreated()).isEqualTo(DEFAULT_CREATED);
        assertThat(testProduct.getDefaultPrice()).isEqualTo(DEFAULT_DEFAULT_PRICE);
        assertThat(testProduct.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testProduct.getLivemode()).isEqualTo(UPDATED_LIVEMODE);
        assertThat(testProduct.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testProduct.getShippable()).isEqualTo(DEFAULT_SHIPPABLE);
        assertThat(testProduct.getStatementDescriptor()).isEqualTo(DEFAULT_STATEMENT_DESCRIPTOR);
        assertThat(testProduct.getTaxCode()).isEqualTo(DEFAULT_TAX_CODE);
        assertThat(testProduct.getUnitLabel()).isEqualTo(UPDATED_UNIT_LABEL);
        assertThat(testProduct.getUpdated()).isEqualTo(DEFAULT_UPDATED);
        assertThat(testProduct.getUrl()).isEqualTo(DEFAULT_URL);
        assertThat(testProduct.getSku()).isEqualTo(UPDATED_SKU);
        assertThat(testProduct.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testProduct.getLinkToGenericPhotoFile()).isEqualTo(DEFAULT_LINK_TO_GENERIC_PHOTO_FILE);
        assertThat(testProduct.getAvailableSizes()).isEqualTo(DEFAULT_AVAILABLE_SIZES);
        assertThat(testProduct.getCurrencyFormat()).isEqualTo(UPDATED_CURRENCY_FORMAT);
        assertThat(testProduct.getIsFreeShipping()).isEqualTo(UPDATED_IS_FREE_SHIPPING);
        assertThat(testProduct.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testProduct.getStyle()).isEqualTo(DEFAULT_STYLE);
        assertThat(testProduct.getInstallments()).isEqualTo(UPDATED_INSTALLMENTS);
    }

    @Test
    @Transactional
    void fullUpdateProductWithPatch() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        int databaseSizeBeforeUpdate = productRepository.findAll().size();

        // Update the product using partial update
        Product partialUpdatedProduct = new Product();
        partialUpdatedProduct.setId(product.getId());

        partialUpdatedProduct
            .object(UPDATED_OBJECT)
            .active(UPDATED_ACTIVE)
            .created(UPDATED_CREATED)
            .defaultPrice(UPDATED_DEFAULT_PRICE)
            .description(UPDATED_DESCRIPTION)
            .livemode(UPDATED_LIVEMODE)
            .name(UPDATED_NAME)
            .shippable(UPDATED_SHIPPABLE)
            .statementDescriptor(UPDATED_STATEMENT_DESCRIPTOR)
            .taxCode(UPDATED_TAX_CODE)
            .unitLabel(UPDATED_UNIT_LABEL)
            .updated(UPDATED_UPDATED)
            .url(UPDATED_URL)
            .sku(UPDATED_SKU)
            .title(UPDATED_TITLE)
            .linkToGenericPhotoFile(UPDATED_LINK_TO_GENERIC_PHOTO_FILE)
            .availableSizes(UPDATED_AVAILABLE_SIZES)
            .currencyFormat(UPDATED_CURRENCY_FORMAT)
            .isFreeShipping(UPDATED_IS_FREE_SHIPPING)
            .price(UPDATED_PRICE)
            .style(UPDATED_STYLE)
            .installments(UPDATED_INSTALLMENTS);

        restProductMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProduct.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedProduct))
            )
            .andExpect(status().isOk());

        // Validate the Product in the database
        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeUpdate);
        Product testProduct = productList.get(productList.size() - 1);
        assertThat(testProduct.getObject()).isEqualTo(UPDATED_OBJECT);
        assertThat(testProduct.getActive()).isEqualTo(UPDATED_ACTIVE);
        assertThat(testProduct.getCreated()).isEqualTo(UPDATED_CREATED);
        assertThat(testProduct.getDefaultPrice()).isEqualTo(UPDATED_DEFAULT_PRICE);
        assertThat(testProduct.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testProduct.getLivemode()).isEqualTo(UPDATED_LIVEMODE);
        assertThat(testProduct.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testProduct.getShippable()).isEqualTo(UPDATED_SHIPPABLE);
        assertThat(testProduct.getStatementDescriptor()).isEqualTo(UPDATED_STATEMENT_DESCRIPTOR);
        assertThat(testProduct.getTaxCode()).isEqualTo(UPDATED_TAX_CODE);
        assertThat(testProduct.getUnitLabel()).isEqualTo(UPDATED_UNIT_LABEL);
        assertThat(testProduct.getUpdated()).isEqualTo(UPDATED_UPDATED);
        assertThat(testProduct.getUrl()).isEqualTo(UPDATED_URL);
        assertThat(testProduct.getSku()).isEqualTo(UPDATED_SKU);
        assertThat(testProduct.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testProduct.getLinkToGenericPhotoFile()).isEqualTo(UPDATED_LINK_TO_GENERIC_PHOTO_FILE);
        assertThat(testProduct.getAvailableSizes()).isEqualTo(UPDATED_AVAILABLE_SIZES);
        assertThat(testProduct.getCurrencyFormat()).isEqualTo(UPDATED_CURRENCY_FORMAT);
        assertThat(testProduct.getIsFreeShipping()).isEqualTo(UPDATED_IS_FREE_SHIPPING);
        assertThat(testProduct.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testProduct.getStyle()).isEqualTo(UPDATED_STYLE);
        assertThat(testProduct.getInstallments()).isEqualTo(UPDATED_INSTALLMENTS);
    }

    @Test
    @Transactional
    void patchNonExistingProduct() throws Exception {
        int databaseSizeBeforeUpdate = productRepository.findAll().size();
        product.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProductMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, product.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(product))
            )
            .andExpect(status().isBadRequest());

        // Validate the Product in the database
        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchProduct() throws Exception {
        int databaseSizeBeforeUpdate = productRepository.findAll().size();
        product.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProductMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(product))
            )
            .andExpect(status().isBadRequest());

        // Validate the Product in the database
        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamProduct() throws Exception {
        int databaseSizeBeforeUpdate = productRepository.findAll().size();
        product.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProductMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Product in the database
        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteProduct() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        int databaseSizeBeforeDelete = productRepository.findAll().size();

        // Delete the product
        restProductMockMvc
            .perform(delete(ENTITY_API_URL_ID, product.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
