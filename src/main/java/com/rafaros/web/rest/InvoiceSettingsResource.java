package com.rafaros.web.rest;

import com.rafaros.domain.InvoiceSettings;
import com.rafaros.repository.InvoiceSettingsRepository;
import com.rafaros.service.InvoiceSettingsService;
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
 * REST controller for managing {@link com.rafaros.domain.InvoiceSettings}.
 */
@RestController
@RequestMapping("/api")
public class InvoiceSettingsResource {

    private final Logger log = LoggerFactory.getLogger(InvoiceSettingsResource.class);

    private static final String ENTITY_NAME = "invoiceSettings";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InvoiceSettingsService invoiceSettingsService;

    private final InvoiceSettingsRepository invoiceSettingsRepository;

    public InvoiceSettingsResource(InvoiceSettingsService invoiceSettingsService, InvoiceSettingsRepository invoiceSettingsRepository) {
        this.invoiceSettingsService = invoiceSettingsService;
        this.invoiceSettingsRepository = invoiceSettingsRepository;
    }

    /**
     * {@code POST  /invoice-settings} : Create a new invoiceSettings.
     *
     * @param invoiceSettings the invoiceSettings to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new invoiceSettings, or with status {@code 400 (Bad Request)} if the invoiceSettings has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/invoice-settings")
    public ResponseEntity<InvoiceSettings> createInvoiceSettings(@Valid @RequestBody InvoiceSettings invoiceSettings)
        throws URISyntaxException {
        log.debug("REST request to save InvoiceSettings : {}", invoiceSettings);
        if (invoiceSettings.getId() != null) {
            throw new BadRequestAlertException("A new invoiceSettings cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InvoiceSettings result = invoiceSettingsService.save(invoiceSettings);
        return ResponseEntity
            .created(new URI("/api/invoice-settings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /invoice-settings/:id} : Updates an existing invoiceSettings.
     *
     * @param id the id of the invoiceSettings to save.
     * @param invoiceSettings the invoiceSettings to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated invoiceSettings,
     * or with status {@code 400 (Bad Request)} if the invoiceSettings is not valid,
     * or with status {@code 500 (Internal Server Error)} if the invoiceSettings couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/invoice-settings/{id}")
    public ResponseEntity<InvoiceSettings> updateInvoiceSettings(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody InvoiceSettings invoiceSettings
    ) throws URISyntaxException {
        log.debug("REST request to update InvoiceSettings : {}, {}", id, invoiceSettings);
        if (invoiceSettings.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, invoiceSettings.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!invoiceSettingsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        InvoiceSettings result = invoiceSettingsService.update(invoiceSettings);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, invoiceSettings.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /invoice-settings/:id} : Partial updates given fields of an existing invoiceSettings, field will ignore if it is null
     *
     * @param id the id of the invoiceSettings to save.
     * @param invoiceSettings the invoiceSettings to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated invoiceSettings,
     * or with status {@code 400 (Bad Request)} if the invoiceSettings is not valid,
     * or with status {@code 404 (Not Found)} if the invoiceSettings is not found,
     * or with status {@code 500 (Internal Server Error)} if the invoiceSettings couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/invoice-settings/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<InvoiceSettings> partialUpdateInvoiceSettings(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody InvoiceSettings invoiceSettings
    ) throws URISyntaxException {
        log.debug("REST request to partial update InvoiceSettings partially : {}, {}", id, invoiceSettings);
        if (invoiceSettings.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, invoiceSettings.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!invoiceSettingsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<InvoiceSettings> result = invoiceSettingsService.partialUpdate(invoiceSettings);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, invoiceSettings.getId().toString())
        );
    }

    /**
     * {@code GET  /invoice-settings} : get all the invoiceSettings.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of invoiceSettings in body.
     */
    @GetMapping("/invoice-settings")
    public ResponseEntity<List<InvoiceSettings>> getAllInvoiceSettings(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of InvoiceSettings");
        Page<InvoiceSettings> page = invoiceSettingsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /invoice-settings/:id} : get the "id" invoiceSettings.
     *
     * @param id the id of the invoiceSettings to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the invoiceSettings, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/invoice-settings/{id}")
    public ResponseEntity<InvoiceSettings> getInvoiceSettings(@PathVariable Long id) {
        log.debug("REST request to get InvoiceSettings : {}", id);
        Optional<InvoiceSettings> invoiceSettings = invoiceSettingsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(invoiceSettings);
    }

    /**
     * {@code DELETE  /invoice-settings/:id} : delete the "id" invoiceSettings.
     *
     * @param id the id of the invoiceSettings to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/invoice-settings/{id}")
    public ResponseEntity<Void> deleteInvoiceSettings(@PathVariable Long id) {
        log.debug("REST request to delete InvoiceSettings : {}", id);
        invoiceSettingsService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
