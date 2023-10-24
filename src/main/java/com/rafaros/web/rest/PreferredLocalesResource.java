package com.rafaros.web.rest;

import com.rafaros.domain.PreferredLocales;
import com.rafaros.repository.PreferredLocalesRepository;
import com.rafaros.service.PreferredLocalesService;
import com.rafaros.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.rafaros.domain.PreferredLocales}.
 */
@RestController
@RequestMapping("/api")
public class PreferredLocalesResource {

    private final Logger log = LoggerFactory.getLogger(PreferredLocalesResource.class);

    private static final String ENTITY_NAME = "preferredLocales";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PreferredLocalesService preferredLocalesService;

    private final PreferredLocalesRepository preferredLocalesRepository;

    public PreferredLocalesResource(
        PreferredLocalesService preferredLocalesService,
        PreferredLocalesRepository preferredLocalesRepository
    ) {
        this.preferredLocalesService = preferredLocalesService;
        this.preferredLocalesRepository = preferredLocalesRepository;
    }

    /**
     * {@code POST  /preferred-locales} : Create a new preferredLocales.
     *
     * @param preferredLocales the preferredLocales to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new preferredLocales, or with status {@code 400 (Bad Request)} if the preferredLocales has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/preferred-locales")
    public ResponseEntity<PreferredLocales> createPreferredLocales(@Valid @RequestBody PreferredLocales preferredLocales)
        throws URISyntaxException {
        log.debug("REST request to save PreferredLocales : {}", preferredLocales);
        if (preferredLocales.getId() != null) {
            throw new BadRequestAlertException("A new preferredLocales cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PreferredLocales result = preferredLocalesService.save(preferredLocales);
        return ResponseEntity
            .created(new URI("/api/preferred-locales/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /preferred-locales/:id} : Updates an existing preferredLocales.
     *
     * @param id the id of the preferredLocales to save.
     * @param preferredLocales the preferredLocales to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated preferredLocales,
     * or with status {@code 400 (Bad Request)} if the preferredLocales is not valid,
     * or with status {@code 500 (Internal Server Error)} if the preferredLocales couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/preferred-locales/{id}")
    public ResponseEntity<PreferredLocales> updatePreferredLocales(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody PreferredLocales preferredLocales
    ) throws URISyntaxException {
        log.debug("REST request to update PreferredLocales : {}, {}", id, preferredLocales);
        if (preferredLocales.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, preferredLocales.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!preferredLocalesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        PreferredLocales result = preferredLocalesService.update(preferredLocales);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, preferredLocales.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /preferred-locales/:id} : Partial updates given fields of an existing preferredLocales, field will ignore if it is null
     *
     * @param id the id of the preferredLocales to save.
     * @param preferredLocales the preferredLocales to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated preferredLocales,
     * or with status {@code 400 (Bad Request)} if the preferredLocales is not valid,
     * or with status {@code 404 (Not Found)} if the preferredLocales is not found,
     * or with status {@code 500 (Internal Server Error)} if the preferredLocales couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/preferred-locales/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<PreferredLocales> partialUpdatePreferredLocales(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody PreferredLocales preferredLocales
    ) throws URISyntaxException {
        log.debug("REST request to partial update PreferredLocales partially : {}, {}", id, preferredLocales);
        if (preferredLocales.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, preferredLocales.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!preferredLocalesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PreferredLocales> result = preferredLocalesService.partialUpdate(preferredLocales);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, preferredLocales.getId().toString())
        );
    }

    /**
     * {@code GET  /preferred-locales} : get all the preferredLocales.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of preferredLocales in body.
     */
    @GetMapping("/preferred-locales")
    public ResponseEntity<List<PreferredLocales>> getAllPreferredLocales(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of PreferredLocales");
        Page<PreferredLocales> page = preferredLocalesService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /preferred-locales/:id} : get the "id" preferredLocales.
     *
     * @param id the id of the preferredLocales to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the preferredLocales, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/preferred-locales/{id}")
    public ResponseEntity<PreferredLocales> getPreferredLocales(@PathVariable Long id) {
        log.debug("REST request to get PreferredLocales : {}", id);
        Optional<PreferredLocales> preferredLocales = preferredLocalesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(preferredLocales);
    }

    /**
     * {@code DELETE  /preferred-locales/:id} : delete the "id" preferredLocales.
     *
     * @param id the id of the preferredLocales to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/preferred-locales/{id}")
    public ResponseEntity<Void> deletePreferredLocales(@PathVariable Long id) {
        log.debug("REST request to delete PreferredLocales : {}", id);
        preferredLocalesService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
