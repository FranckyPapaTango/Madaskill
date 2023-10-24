package com.rafaros.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.rafaros.IntegrationTest;
import com.rafaros.domain.Metadata;
import com.rafaros.repository.MetadataRepository;
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
 * Integration tests for the {@link MetadataResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class MetadataResourceIT {

    private static final String DEFAULT_ORDER_ID = "AAAAAAAAAA";
    private static final String UPDATED_ORDER_ID = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/metadata";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private MetadataRepository metadataRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMetadataMockMvc;

    private Metadata metadata;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Metadata createEntity(EntityManager em) {
        Metadata metadata = new Metadata().orderId(DEFAULT_ORDER_ID);
        return metadata;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Metadata createUpdatedEntity(EntityManager em) {
        Metadata metadata = new Metadata().orderId(UPDATED_ORDER_ID);
        return metadata;
    }

    @BeforeEach
    public void initTest() {
        metadata = createEntity(em);
    }

    @Test
    @Transactional
    void createMetadata() throws Exception {
        int databaseSizeBeforeCreate = metadataRepository.findAll().size();
        // Create the Metadata
        restMetadataMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(metadata)))
            .andExpect(status().isCreated());

        // Validate the Metadata in the database
        List<Metadata> metadataList = metadataRepository.findAll();
        assertThat(metadataList).hasSize(databaseSizeBeforeCreate + 1);
        Metadata testMetadata = metadataList.get(metadataList.size() - 1);
        assertThat(testMetadata.getOrderId()).isEqualTo(DEFAULT_ORDER_ID);
    }

    @Test
    @Transactional
    void createMetadataWithExistingId() throws Exception {
        // Create the Metadata with an existing ID
        metadata.setId(1L);

        int databaseSizeBeforeCreate = metadataRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restMetadataMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(metadata)))
            .andExpect(status().isBadRequest());

        // Validate the Metadata in the database
        List<Metadata> metadataList = metadataRepository.findAll();
        assertThat(metadataList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllMetadata() throws Exception {
        // Initialize the database
        metadataRepository.saveAndFlush(metadata);

        // Get all the metadataList
        restMetadataMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(metadata.getId().intValue())))
            .andExpect(jsonPath("$.[*].orderId").value(hasItem(DEFAULT_ORDER_ID)));
    }

    @Test
    @Transactional
    void getMetadata() throws Exception {
        // Initialize the database
        metadataRepository.saveAndFlush(metadata);

        // Get the metadata
        restMetadataMockMvc
            .perform(get(ENTITY_API_URL_ID, metadata.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(metadata.getId().intValue()))
            .andExpect(jsonPath("$.orderId").value(DEFAULT_ORDER_ID));
    }

    @Test
    @Transactional
    void getNonExistingMetadata() throws Exception {
        // Get the metadata
        restMetadataMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingMetadata() throws Exception {
        // Initialize the database
        metadataRepository.saveAndFlush(metadata);

        int databaseSizeBeforeUpdate = metadataRepository.findAll().size();

        // Update the metadata
        Metadata updatedMetadata = metadataRepository.findById(metadata.getId()).get();
        // Disconnect from session so that the updates on updatedMetadata are not directly saved in db
        em.detach(updatedMetadata);
        updatedMetadata.orderId(UPDATED_ORDER_ID);

        restMetadataMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedMetadata.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedMetadata))
            )
            .andExpect(status().isOk());

        // Validate the Metadata in the database
        List<Metadata> metadataList = metadataRepository.findAll();
        assertThat(metadataList).hasSize(databaseSizeBeforeUpdate);
        Metadata testMetadata = metadataList.get(metadataList.size() - 1);
        assertThat(testMetadata.getOrderId()).isEqualTo(UPDATED_ORDER_ID);
    }

    @Test
    @Transactional
    void putNonExistingMetadata() throws Exception {
        int databaseSizeBeforeUpdate = metadataRepository.findAll().size();
        metadata.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMetadataMockMvc
            .perform(
                put(ENTITY_API_URL_ID, metadata.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(metadata))
            )
            .andExpect(status().isBadRequest());

        // Validate the Metadata in the database
        List<Metadata> metadataList = metadataRepository.findAll();
        assertThat(metadataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchMetadata() throws Exception {
        int databaseSizeBeforeUpdate = metadataRepository.findAll().size();
        metadata.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMetadataMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(metadata))
            )
            .andExpect(status().isBadRequest());

        // Validate the Metadata in the database
        List<Metadata> metadataList = metadataRepository.findAll();
        assertThat(metadataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamMetadata() throws Exception {
        int databaseSizeBeforeUpdate = metadataRepository.findAll().size();
        metadata.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMetadataMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(metadata)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Metadata in the database
        List<Metadata> metadataList = metadataRepository.findAll();
        assertThat(metadataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateMetadataWithPatch() throws Exception {
        // Initialize the database
        metadataRepository.saveAndFlush(metadata);

        int databaseSizeBeforeUpdate = metadataRepository.findAll().size();

        // Update the metadata using partial update
        Metadata partialUpdatedMetadata = new Metadata();
        partialUpdatedMetadata.setId(metadata.getId());

        restMetadataMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMetadata.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedMetadata))
            )
            .andExpect(status().isOk());

        // Validate the Metadata in the database
        List<Metadata> metadataList = metadataRepository.findAll();
        assertThat(metadataList).hasSize(databaseSizeBeforeUpdate);
        Metadata testMetadata = metadataList.get(metadataList.size() - 1);
        assertThat(testMetadata.getOrderId()).isEqualTo(DEFAULT_ORDER_ID);
    }

    @Test
    @Transactional
    void fullUpdateMetadataWithPatch() throws Exception {
        // Initialize the database
        metadataRepository.saveAndFlush(metadata);

        int databaseSizeBeforeUpdate = metadataRepository.findAll().size();

        // Update the metadata using partial update
        Metadata partialUpdatedMetadata = new Metadata();
        partialUpdatedMetadata.setId(metadata.getId());

        partialUpdatedMetadata.orderId(UPDATED_ORDER_ID);

        restMetadataMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMetadata.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedMetadata))
            )
            .andExpect(status().isOk());

        // Validate the Metadata in the database
        List<Metadata> metadataList = metadataRepository.findAll();
        assertThat(metadataList).hasSize(databaseSizeBeforeUpdate);
        Metadata testMetadata = metadataList.get(metadataList.size() - 1);
        assertThat(testMetadata.getOrderId()).isEqualTo(UPDATED_ORDER_ID);
    }

    @Test
    @Transactional
    void patchNonExistingMetadata() throws Exception {
        int databaseSizeBeforeUpdate = metadataRepository.findAll().size();
        metadata.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMetadataMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, metadata.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(metadata))
            )
            .andExpect(status().isBadRequest());

        // Validate the Metadata in the database
        List<Metadata> metadataList = metadataRepository.findAll();
        assertThat(metadataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchMetadata() throws Exception {
        int databaseSizeBeforeUpdate = metadataRepository.findAll().size();
        metadata.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMetadataMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(metadata))
            )
            .andExpect(status().isBadRequest());

        // Validate the Metadata in the database
        List<Metadata> metadataList = metadataRepository.findAll();
        assertThat(metadataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamMetadata() throws Exception {
        int databaseSizeBeforeUpdate = metadataRepository.findAll().size();
        metadata.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMetadataMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(metadata)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Metadata in the database
        List<Metadata> metadataList = metadataRepository.findAll();
        assertThat(metadataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteMetadata() throws Exception {
        // Initialize the database
        metadataRepository.saveAndFlush(metadata);

        int databaseSizeBeforeDelete = metadataRepository.findAll().size();

        // Delete the metadata
        restMetadataMockMvc
            .perform(delete(ENTITY_API_URL_ID, metadata.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Metadata> metadataList = metadataRepository.findAll();
        assertThat(metadataList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
