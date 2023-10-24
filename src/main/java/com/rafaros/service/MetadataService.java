package com.rafaros.service;

import com.rafaros.domain.Metadata;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link Metadata}.
 */
public interface MetadataService {
    /**
     * Save a metadata.
     *
     * @param metadata the entity to save.
     * @return the persisted entity.
     */
    Metadata save(Metadata metadata);

    /**
     * Updates a metadata.
     *
     * @param metadata the entity to update.
     * @return the persisted entity.
     */
    Metadata update(Metadata metadata);

    /**
     * Partially updates a metadata.
     *
     * @param metadata the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Metadata> partialUpdate(Metadata metadata);

    /**
     * Get all the metadata.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Metadata> findAll(Pageable pageable);

    /**
     * Get the "id" metadata.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Metadata> findOne(Long id);

    /**
     * Delete the "id" metadata.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
