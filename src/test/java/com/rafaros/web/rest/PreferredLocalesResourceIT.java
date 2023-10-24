package com.rafaros.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.rafaros.IntegrationTest;
import com.rafaros.domain.PreferredLocales;
import com.rafaros.repository.PreferredLocalesRepository;
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
 * Integration tests for the {@link PreferredLocalesResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PreferredLocalesResourceIT {

    private static final String DEFAULT_PREFERRED_LOCALES = "AAAAAAAAAA";
    private static final String UPDATED_PREFERRED_LOCALES = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/preferred-locales";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PreferredLocalesRepository preferredLocalesRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPreferredLocalesMockMvc;

    private PreferredLocales preferredLocales;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PreferredLocales createEntity(EntityManager em) {
        PreferredLocales preferredLocales = new PreferredLocales().preferredLocales(DEFAULT_PREFERRED_LOCALES);
        return preferredLocales;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PreferredLocales createUpdatedEntity(EntityManager em) {
        PreferredLocales preferredLocales = new PreferredLocales().preferredLocales(UPDATED_PREFERRED_LOCALES);
        return preferredLocales;
    }

    @BeforeEach
    public void initTest() {
        preferredLocales = createEntity(em);
    }

    @Test
    @Transactional
    void createPreferredLocales() throws Exception {
        int databaseSizeBeforeCreate = preferredLocalesRepository.findAll().size();
        // Create the PreferredLocales
        restPreferredLocalesMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(preferredLocales))
            )
            .andExpect(status().isCreated());

        // Validate the PreferredLocales in the database
        List<PreferredLocales> preferredLocalesList = preferredLocalesRepository.findAll();
        assertThat(preferredLocalesList).hasSize(databaseSizeBeforeCreate + 1);
        PreferredLocales testPreferredLocales = preferredLocalesList.get(preferredLocalesList.size() - 1);
        assertThat(testPreferredLocales.getPreferredLocales()).isEqualTo(DEFAULT_PREFERRED_LOCALES);
    }

    @Test
    @Transactional
    void createPreferredLocalesWithExistingId() throws Exception {
        // Create the PreferredLocales with an existing ID
        preferredLocales.setId(1L);

        int databaseSizeBeforeCreate = preferredLocalesRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPreferredLocalesMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(preferredLocales))
            )
            .andExpect(status().isBadRequest());

        // Validate the PreferredLocales in the database
        List<PreferredLocales> preferredLocalesList = preferredLocalesRepository.findAll();
        assertThat(preferredLocalesList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllPreferredLocales() throws Exception {
        // Initialize the database
        preferredLocalesRepository.saveAndFlush(preferredLocales);

        // Get all the preferredLocalesList
        restPreferredLocalesMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(preferredLocales.getId().intValue())))
            .andExpect(jsonPath("$.[*].preferredLocales").value(hasItem(DEFAULT_PREFERRED_LOCALES)));
    }

    @Test
    @Transactional
    void getPreferredLocales() throws Exception {
        // Initialize the database
        preferredLocalesRepository.saveAndFlush(preferredLocales);

        // Get the preferredLocales
        restPreferredLocalesMockMvc
            .perform(get(ENTITY_API_URL_ID, preferredLocales.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(preferredLocales.getId().intValue()))
            .andExpect(jsonPath("$.preferredLocales").value(DEFAULT_PREFERRED_LOCALES));
    }

    @Test
    @Transactional
    void getNonExistingPreferredLocales() throws Exception {
        // Get the preferredLocales
        restPreferredLocalesMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingPreferredLocales() throws Exception {
        // Initialize the database
        preferredLocalesRepository.saveAndFlush(preferredLocales);

        int databaseSizeBeforeUpdate = preferredLocalesRepository.findAll().size();

        // Update the preferredLocales
        PreferredLocales updatedPreferredLocales = preferredLocalesRepository.findById(preferredLocales.getId()).get();
        // Disconnect from session so that the updates on updatedPreferredLocales are not directly saved in db
        em.detach(updatedPreferredLocales);
        updatedPreferredLocales.preferredLocales(UPDATED_PREFERRED_LOCALES);

        restPreferredLocalesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedPreferredLocales.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedPreferredLocales))
            )
            .andExpect(status().isOk());

        // Validate the PreferredLocales in the database
        List<PreferredLocales> preferredLocalesList = preferredLocalesRepository.findAll();
        assertThat(preferredLocalesList).hasSize(databaseSizeBeforeUpdate);
        PreferredLocales testPreferredLocales = preferredLocalesList.get(preferredLocalesList.size() - 1);
        assertThat(testPreferredLocales.getPreferredLocales()).isEqualTo(UPDATED_PREFERRED_LOCALES);
    }

    @Test
    @Transactional
    void putNonExistingPreferredLocales() throws Exception {
        int databaseSizeBeforeUpdate = preferredLocalesRepository.findAll().size();
        preferredLocales.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPreferredLocalesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, preferredLocales.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(preferredLocales))
            )
            .andExpect(status().isBadRequest());

        // Validate the PreferredLocales in the database
        List<PreferredLocales> preferredLocalesList = preferredLocalesRepository.findAll();
        assertThat(preferredLocalesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPreferredLocales() throws Exception {
        int databaseSizeBeforeUpdate = preferredLocalesRepository.findAll().size();
        preferredLocales.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPreferredLocalesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(preferredLocales))
            )
            .andExpect(status().isBadRequest());

        // Validate the PreferredLocales in the database
        List<PreferredLocales> preferredLocalesList = preferredLocalesRepository.findAll();
        assertThat(preferredLocalesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPreferredLocales() throws Exception {
        int databaseSizeBeforeUpdate = preferredLocalesRepository.findAll().size();
        preferredLocales.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPreferredLocalesMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(preferredLocales))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PreferredLocales in the database
        List<PreferredLocales> preferredLocalesList = preferredLocalesRepository.findAll();
        assertThat(preferredLocalesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePreferredLocalesWithPatch() throws Exception {
        // Initialize the database
        preferredLocalesRepository.saveAndFlush(preferredLocales);

        int databaseSizeBeforeUpdate = preferredLocalesRepository.findAll().size();

        // Update the preferredLocales using partial update
        PreferredLocales partialUpdatedPreferredLocales = new PreferredLocales();
        partialUpdatedPreferredLocales.setId(preferredLocales.getId());

        partialUpdatedPreferredLocales.preferredLocales(UPDATED_PREFERRED_LOCALES);

        restPreferredLocalesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPreferredLocales.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPreferredLocales))
            )
            .andExpect(status().isOk());

        // Validate the PreferredLocales in the database
        List<PreferredLocales> preferredLocalesList = preferredLocalesRepository.findAll();
        assertThat(preferredLocalesList).hasSize(databaseSizeBeforeUpdate);
        PreferredLocales testPreferredLocales = preferredLocalesList.get(preferredLocalesList.size() - 1);
        assertThat(testPreferredLocales.getPreferredLocales()).isEqualTo(UPDATED_PREFERRED_LOCALES);
    }

    @Test
    @Transactional
    void fullUpdatePreferredLocalesWithPatch() throws Exception {
        // Initialize the database
        preferredLocalesRepository.saveAndFlush(preferredLocales);

        int databaseSizeBeforeUpdate = preferredLocalesRepository.findAll().size();

        // Update the preferredLocales using partial update
        PreferredLocales partialUpdatedPreferredLocales = new PreferredLocales();
        partialUpdatedPreferredLocales.setId(preferredLocales.getId());

        partialUpdatedPreferredLocales.preferredLocales(UPDATED_PREFERRED_LOCALES);

        restPreferredLocalesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPreferredLocales.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPreferredLocales))
            )
            .andExpect(status().isOk());

        // Validate the PreferredLocales in the database
        List<PreferredLocales> preferredLocalesList = preferredLocalesRepository.findAll();
        assertThat(preferredLocalesList).hasSize(databaseSizeBeforeUpdate);
        PreferredLocales testPreferredLocales = preferredLocalesList.get(preferredLocalesList.size() - 1);
        assertThat(testPreferredLocales.getPreferredLocales()).isEqualTo(UPDATED_PREFERRED_LOCALES);
    }

    @Test
    @Transactional
    void patchNonExistingPreferredLocales() throws Exception {
        int databaseSizeBeforeUpdate = preferredLocalesRepository.findAll().size();
        preferredLocales.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPreferredLocalesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, preferredLocales.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(preferredLocales))
            )
            .andExpect(status().isBadRequest());

        // Validate the PreferredLocales in the database
        List<PreferredLocales> preferredLocalesList = preferredLocalesRepository.findAll();
        assertThat(preferredLocalesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPreferredLocales() throws Exception {
        int databaseSizeBeforeUpdate = preferredLocalesRepository.findAll().size();
        preferredLocales.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPreferredLocalesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(preferredLocales))
            )
            .andExpect(status().isBadRequest());

        // Validate the PreferredLocales in the database
        List<PreferredLocales> preferredLocalesList = preferredLocalesRepository.findAll();
        assertThat(preferredLocalesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPreferredLocales() throws Exception {
        int databaseSizeBeforeUpdate = preferredLocalesRepository.findAll().size();
        preferredLocales.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPreferredLocalesMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(preferredLocales))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PreferredLocales in the database
        List<PreferredLocales> preferredLocalesList = preferredLocalesRepository.findAll();
        assertThat(preferredLocalesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePreferredLocales() throws Exception {
        // Initialize the database
        preferredLocalesRepository.saveAndFlush(preferredLocales);

        int databaseSizeBeforeDelete = preferredLocalesRepository.findAll().size();

        // Delete the preferredLocales
        restPreferredLocalesMockMvc
            .perform(delete(ENTITY_API_URL_ID, preferredLocales.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PreferredLocales> preferredLocalesList = preferredLocalesRepository.findAll();
        assertThat(preferredLocalesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
