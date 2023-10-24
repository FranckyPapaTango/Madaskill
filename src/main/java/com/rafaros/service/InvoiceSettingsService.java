package com.rafaros.service;

import com.rafaros.domain.InvoiceSettings;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link InvoiceSettings}.
 */
public interface InvoiceSettingsService {
    /**
     * Save a invoiceSettings.
     *
     * @param invoiceSettings the entity to save.
     * @return the persisted entity.
     */
    InvoiceSettings save(InvoiceSettings invoiceSettings);

    /**
     * Updates a invoiceSettings.
     *
     * @param invoiceSettings the entity to update.
     * @return the persisted entity.
     */
    InvoiceSettings update(InvoiceSettings invoiceSettings);

    /**
     * Partially updates a invoiceSettings.
     *
     * @param invoiceSettings the entity to update partially.
     * @return the persisted entity.
     */
    Optional<InvoiceSettings> partialUpdate(InvoiceSettings invoiceSettings);

    /**
     * Get all the invoiceSettings.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<InvoiceSettings> findAll(Pageable pageable);

    /**
     * Get the "id" invoiceSettings.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<InvoiceSettings> findOne(Long id);

    /**
     * Delete the "id" invoiceSettings.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
