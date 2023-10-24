package com.rafaros.service;

import com.rafaros.domain.Commande;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link Commande}.
 */
public interface CommandeService {
    /**
     * Save a commande.
     *
     * @param commande the entity to save.
     * @return the persisted entity.
     */
    Commande save(Commande commande);

    /**
     * Updates a commande.
     *
     * @param commande the entity to update.
     * @return the persisted entity.
     */
    Commande update(Commande commande);

    /**
     * Partially updates a commande.
     *
     * @param commande the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Commande> partialUpdate(Commande commande);

    /**
     * Get all the commandes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Commande> findAll(Pageable pageable);
    /**
     * Get all the Commande where Customer is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<Commande> findAllWhereCustomerIsNull();

    /**
     * Get the "id" commande.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Commande> findOne(Long id);

    /**
     * Delete the "id" commande.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
