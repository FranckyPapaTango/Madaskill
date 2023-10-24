package com.rafaros.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.rafaros.IntegrationTest;
import com.rafaros.domain.InvoiceSettings;
import com.rafaros.repository.InvoiceSettingsRepository;
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
 * Integration tests for the {@link InvoiceSettingsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class InvoiceSettingsResourceIT {

    private static final String DEFAULT_CUSTOM_FIELDS = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOM_FIELDS = "BBBBBBBBBB";

    private static final String DEFAULT_DEFAULT_PAYMENT_METHOD = "AAAAAAAAAA";
    private static final String UPDATED_DEFAULT_PAYMENT_METHOD = "BBBBBBBBBB";

    private static final String DEFAULT_FOOTER = "AAAAAAAAAA";
    private static final String UPDATED_FOOTER = "BBBBBBBBBB";

    private static final String DEFAULT_RENDERING_OPTIONS = "AAAAAAAAAA";
    private static final String UPDATED_RENDERING_OPTIONS = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/invoice-settings";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private InvoiceSettingsRepository invoiceSettingsRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInvoiceSettingsMockMvc;

    private InvoiceSettings invoiceSettings;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InvoiceSettings createEntity(EntityManager em) {
        InvoiceSettings invoiceSettings = new InvoiceSettings()
            .customFields(DEFAULT_CUSTOM_FIELDS)
            .defaultPaymentMethod(DEFAULT_DEFAULT_PAYMENT_METHOD)
            .footer(DEFAULT_FOOTER)
            .renderingOptions(DEFAULT_RENDERING_OPTIONS);
        return invoiceSettings;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InvoiceSettings createUpdatedEntity(EntityManager em) {
        InvoiceSettings invoiceSettings = new InvoiceSettings()
            .customFields(UPDATED_CUSTOM_FIELDS)
            .defaultPaymentMethod(UPDATED_DEFAULT_PAYMENT_METHOD)
            .footer(UPDATED_FOOTER)
            .renderingOptions(UPDATED_RENDERING_OPTIONS);
        return invoiceSettings;
    }

    @BeforeEach
    public void initTest() {
        invoiceSettings = createEntity(em);
    }

    @Test
    @Transactional
    void createInvoiceSettings() throws Exception {
        int databaseSizeBeforeCreate = invoiceSettingsRepository.findAll().size();
        // Create the InvoiceSettings
        restInvoiceSettingsMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(invoiceSettings))
            )
            .andExpect(status().isCreated());

        // Validate the InvoiceSettings in the database
        List<InvoiceSettings> invoiceSettingsList = invoiceSettingsRepository.findAll();
        assertThat(invoiceSettingsList).hasSize(databaseSizeBeforeCreate + 1);
        InvoiceSettings testInvoiceSettings = invoiceSettingsList.get(invoiceSettingsList.size() - 1);
        assertThat(testInvoiceSettings.getCustomFields()).isEqualTo(DEFAULT_CUSTOM_FIELDS);
        assertThat(testInvoiceSettings.getDefaultPaymentMethod()).isEqualTo(DEFAULT_DEFAULT_PAYMENT_METHOD);
        assertThat(testInvoiceSettings.getFooter()).isEqualTo(DEFAULT_FOOTER);
        assertThat(testInvoiceSettings.getRenderingOptions()).isEqualTo(DEFAULT_RENDERING_OPTIONS);
    }

    @Test
    @Transactional
    void createInvoiceSettingsWithExistingId() throws Exception {
        // Create the InvoiceSettings with an existing ID
        invoiceSettings.setId(1L);

        int databaseSizeBeforeCreate = invoiceSettingsRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restInvoiceSettingsMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(invoiceSettings))
            )
            .andExpect(status().isBadRequest());

        // Validate the InvoiceSettings in the database
        List<InvoiceSettings> invoiceSettingsList = invoiceSettingsRepository.findAll();
        assertThat(invoiceSettingsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllInvoiceSettings() throws Exception {
        // Initialize the database
        invoiceSettingsRepository.saveAndFlush(invoiceSettings);

        // Get all the invoiceSettingsList
        restInvoiceSettingsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(invoiceSettings.getId().intValue())))
            .andExpect(jsonPath("$.[*].customFields").value(hasItem(DEFAULT_CUSTOM_FIELDS)))
            .andExpect(jsonPath("$.[*].defaultPaymentMethod").value(hasItem(DEFAULT_DEFAULT_PAYMENT_METHOD)))
            .andExpect(jsonPath("$.[*].footer").value(hasItem(DEFAULT_FOOTER)))
            .andExpect(jsonPath("$.[*].renderingOptions").value(hasItem(DEFAULT_RENDERING_OPTIONS)));
    }

    @Test
    @Transactional
    void getInvoiceSettings() throws Exception {
        // Initialize the database
        invoiceSettingsRepository.saveAndFlush(invoiceSettings);

        // Get the invoiceSettings
        restInvoiceSettingsMockMvc
            .perform(get(ENTITY_API_URL_ID, invoiceSettings.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(invoiceSettings.getId().intValue()))
            .andExpect(jsonPath("$.customFields").value(DEFAULT_CUSTOM_FIELDS))
            .andExpect(jsonPath("$.defaultPaymentMethod").value(DEFAULT_DEFAULT_PAYMENT_METHOD))
            .andExpect(jsonPath("$.footer").value(DEFAULT_FOOTER))
            .andExpect(jsonPath("$.renderingOptions").value(DEFAULT_RENDERING_OPTIONS));
    }

    @Test
    @Transactional
    void getNonExistingInvoiceSettings() throws Exception {
        // Get the invoiceSettings
        restInvoiceSettingsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingInvoiceSettings() throws Exception {
        // Initialize the database
        invoiceSettingsRepository.saveAndFlush(invoiceSettings);

        int databaseSizeBeforeUpdate = invoiceSettingsRepository.findAll().size();

        // Update the invoiceSettings
        InvoiceSettings updatedInvoiceSettings = invoiceSettingsRepository.findById(invoiceSettings.getId()).get();
        // Disconnect from session so that the updates on updatedInvoiceSettings are not directly saved in db
        em.detach(updatedInvoiceSettings);
        updatedInvoiceSettings
            .customFields(UPDATED_CUSTOM_FIELDS)
            .defaultPaymentMethod(UPDATED_DEFAULT_PAYMENT_METHOD)
            .footer(UPDATED_FOOTER)
            .renderingOptions(UPDATED_RENDERING_OPTIONS);

        restInvoiceSettingsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedInvoiceSettings.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedInvoiceSettings))
            )
            .andExpect(status().isOk());

        // Validate the InvoiceSettings in the database
        List<InvoiceSettings> invoiceSettingsList = invoiceSettingsRepository.findAll();
        assertThat(invoiceSettingsList).hasSize(databaseSizeBeforeUpdate);
        InvoiceSettings testInvoiceSettings = invoiceSettingsList.get(invoiceSettingsList.size() - 1);
        assertThat(testInvoiceSettings.getCustomFields()).isEqualTo(UPDATED_CUSTOM_FIELDS);
        assertThat(testInvoiceSettings.getDefaultPaymentMethod()).isEqualTo(UPDATED_DEFAULT_PAYMENT_METHOD);
        assertThat(testInvoiceSettings.getFooter()).isEqualTo(UPDATED_FOOTER);
        assertThat(testInvoiceSettings.getRenderingOptions()).isEqualTo(UPDATED_RENDERING_OPTIONS);
    }

    @Test
    @Transactional
    void putNonExistingInvoiceSettings() throws Exception {
        int databaseSizeBeforeUpdate = invoiceSettingsRepository.findAll().size();
        invoiceSettings.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInvoiceSettingsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, invoiceSettings.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(invoiceSettings))
            )
            .andExpect(status().isBadRequest());

        // Validate the InvoiceSettings in the database
        List<InvoiceSettings> invoiceSettingsList = invoiceSettingsRepository.findAll();
        assertThat(invoiceSettingsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchInvoiceSettings() throws Exception {
        int databaseSizeBeforeUpdate = invoiceSettingsRepository.findAll().size();
        invoiceSettings.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInvoiceSettingsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(invoiceSettings))
            )
            .andExpect(status().isBadRequest());

        // Validate the InvoiceSettings in the database
        List<InvoiceSettings> invoiceSettingsList = invoiceSettingsRepository.findAll();
        assertThat(invoiceSettingsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamInvoiceSettings() throws Exception {
        int databaseSizeBeforeUpdate = invoiceSettingsRepository.findAll().size();
        invoiceSettings.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInvoiceSettingsMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(invoiceSettings))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the InvoiceSettings in the database
        List<InvoiceSettings> invoiceSettingsList = invoiceSettingsRepository.findAll();
        assertThat(invoiceSettingsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateInvoiceSettingsWithPatch() throws Exception {
        // Initialize the database
        invoiceSettingsRepository.saveAndFlush(invoiceSettings);

        int databaseSizeBeforeUpdate = invoiceSettingsRepository.findAll().size();

        // Update the invoiceSettings using partial update
        InvoiceSettings partialUpdatedInvoiceSettings = new InvoiceSettings();
        partialUpdatedInvoiceSettings.setId(invoiceSettings.getId());

        partialUpdatedInvoiceSettings
            .defaultPaymentMethod(UPDATED_DEFAULT_PAYMENT_METHOD)
            .footer(UPDATED_FOOTER)
            .renderingOptions(UPDATED_RENDERING_OPTIONS);

        restInvoiceSettingsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedInvoiceSettings.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedInvoiceSettings))
            )
            .andExpect(status().isOk());

        // Validate the InvoiceSettings in the database
        List<InvoiceSettings> invoiceSettingsList = invoiceSettingsRepository.findAll();
        assertThat(invoiceSettingsList).hasSize(databaseSizeBeforeUpdate);
        InvoiceSettings testInvoiceSettings = invoiceSettingsList.get(invoiceSettingsList.size() - 1);
        assertThat(testInvoiceSettings.getCustomFields()).isEqualTo(DEFAULT_CUSTOM_FIELDS);
        assertThat(testInvoiceSettings.getDefaultPaymentMethod()).isEqualTo(UPDATED_DEFAULT_PAYMENT_METHOD);
        assertThat(testInvoiceSettings.getFooter()).isEqualTo(UPDATED_FOOTER);
        assertThat(testInvoiceSettings.getRenderingOptions()).isEqualTo(UPDATED_RENDERING_OPTIONS);
    }

    @Test
    @Transactional
    void fullUpdateInvoiceSettingsWithPatch() throws Exception {
        // Initialize the database
        invoiceSettingsRepository.saveAndFlush(invoiceSettings);

        int databaseSizeBeforeUpdate = invoiceSettingsRepository.findAll().size();

        // Update the invoiceSettings using partial update
        InvoiceSettings partialUpdatedInvoiceSettings = new InvoiceSettings();
        partialUpdatedInvoiceSettings.setId(invoiceSettings.getId());

        partialUpdatedInvoiceSettings
            .customFields(UPDATED_CUSTOM_FIELDS)
            .defaultPaymentMethod(UPDATED_DEFAULT_PAYMENT_METHOD)
            .footer(UPDATED_FOOTER)
            .renderingOptions(UPDATED_RENDERING_OPTIONS);

        restInvoiceSettingsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedInvoiceSettings.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedInvoiceSettings))
            )
            .andExpect(status().isOk());

        // Validate the InvoiceSettings in the database
        List<InvoiceSettings> invoiceSettingsList = invoiceSettingsRepository.findAll();
        assertThat(invoiceSettingsList).hasSize(databaseSizeBeforeUpdate);
        InvoiceSettings testInvoiceSettings = invoiceSettingsList.get(invoiceSettingsList.size() - 1);
        assertThat(testInvoiceSettings.getCustomFields()).isEqualTo(UPDATED_CUSTOM_FIELDS);
        assertThat(testInvoiceSettings.getDefaultPaymentMethod()).isEqualTo(UPDATED_DEFAULT_PAYMENT_METHOD);
        assertThat(testInvoiceSettings.getFooter()).isEqualTo(UPDATED_FOOTER);
        assertThat(testInvoiceSettings.getRenderingOptions()).isEqualTo(UPDATED_RENDERING_OPTIONS);
    }

    @Test
    @Transactional
    void patchNonExistingInvoiceSettings() throws Exception {
        int databaseSizeBeforeUpdate = invoiceSettingsRepository.findAll().size();
        invoiceSettings.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInvoiceSettingsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, invoiceSettings.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(invoiceSettings))
            )
            .andExpect(status().isBadRequest());

        // Validate the InvoiceSettings in the database
        List<InvoiceSettings> invoiceSettingsList = invoiceSettingsRepository.findAll();
        assertThat(invoiceSettingsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchInvoiceSettings() throws Exception {
        int databaseSizeBeforeUpdate = invoiceSettingsRepository.findAll().size();
        invoiceSettings.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInvoiceSettingsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(invoiceSettings))
            )
            .andExpect(status().isBadRequest());

        // Validate the InvoiceSettings in the database
        List<InvoiceSettings> invoiceSettingsList = invoiceSettingsRepository.findAll();
        assertThat(invoiceSettingsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamInvoiceSettings() throws Exception {
        int databaseSizeBeforeUpdate = invoiceSettingsRepository.findAll().size();
        invoiceSettings.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInvoiceSettingsMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(invoiceSettings))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the InvoiceSettings in the database
        List<InvoiceSettings> invoiceSettingsList = invoiceSettingsRepository.findAll();
        assertThat(invoiceSettingsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteInvoiceSettings() throws Exception {
        // Initialize the database
        invoiceSettingsRepository.saveAndFlush(invoiceSettings);

        int databaseSizeBeforeDelete = invoiceSettingsRepository.findAll().size();

        // Delete the invoiceSettings
        restInvoiceSettingsMockMvc
            .perform(delete(ENTITY_API_URL_ID, invoiceSettings.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<InvoiceSettings> invoiceSettingsList = invoiceSettingsRepository.findAll();
        assertThat(invoiceSettingsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
