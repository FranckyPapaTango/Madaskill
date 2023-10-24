package com.rafaros.web.rest;

import com.rafaros.domain.Metadata;
import com.rafaros.repository.MetadataRepository;
import com.rafaros.service.MetadataService;
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
 * REST controller for managing {@link com.rafaros.domain.Metadata}.
 */
@RestController
@RequestMapping("/api")
public class MetadataResource {

    private final Logger log = LoggerFactory.getLogger(MetadataResource.class);

    private static final String ENTITY_NAME = "metadata";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MetadataService metadataService;

    private final MetadataRepository metadataRepository;

    public MetadataResource(MetadataService metadataService, MetadataRepository metadataRepository) {
        this.metadataService = metadataService;
        this.metadataRepository = metadataRepository;
    }

    /**
     * {@code POST  /metadata} : Create a new metadata.
     *
     * @param metadata the metadata to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new metadata, or with status {@code 400 (Bad Request)} if the metadata has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/metadata")
    public ResponseEntity<Metadata> createMetadata(@Valid @RequestBody Metadata metadata) throws URISyntaxException {
        log.debug("REST request to save Metadata : {}", metadata);
        if (metadata.getId() != null) {
            throw new BadRequestAlertException("A new metadata cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Metadata result = metadataService.save(metadata);
        return ResponseEntity
            .created(new URI("/api/metadata/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /metadata/:id} : Updates an existing metadata.
     *
     * @param id the id of the metadata to save.
     * @param metadata the metadata to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated metadata,
     * or with status {@code 400 (Bad Request)} if the metadata is not valid,
     * or with status {@code 500 (Internal Server Error)} if the metadata couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/metadata/{id}")
    public ResponseEntity<Metadata> updateMetadata(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Metadata metadata
    ) throws URISyntaxException {
        log.debug("REST request to update Metadata : {}, {}", id, metadata);
        if (metadata.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, metadata.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!metadataRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Metadata result = metadataService.update(metadata);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, metadata.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /metadata/:id} : Partial updates given fields of an existing metadata, field will ignore if it is null
     *
     * @param id the id of the metadata to save.
     * @param metadata the metadata to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated metadata,
     * or with status {@code 400 (Bad Request)} if the metadata is not valid,
     * or with status {@code 404 (Not Found)} if the metadata is not found,
     * or with status {@code 500 (Internal Server Error)} if the metadata couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/metadata/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Metadata> partialUpdateMetadata(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Metadata metadata
    ) throws URISyntaxException {
        log.debug("REST request to partial update Metadata partially : {}, {}", id, metadata);
        if (metadata.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, metadata.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!metadataRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Metadata> result = metadataService.partialUpdate(metadata);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, metadata.getId().toString())
        );
    }

    /**
     * {@code GET  /metadata} : get all the metadata.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of metadata in body.
     */
    @GetMapping("/metadata")
    public ResponseEntity<List<Metadata>> getAllMetadata(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Metadata");
        Page<Metadata> page = metadataService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /metadata/:id} : get the "id" metadata.
     *
     * @param id the id of the metadata to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the metadata, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/metadata/{id}")
    public ResponseEntity<Metadata> getMetadata(@PathVariable Long id) {
        log.debug("REST request to get Metadata : {}", id);
        Optional<Metadata> metadata = metadataService.findOne(id);
        return ResponseUtil.wrapOrNotFound(metadata);
    }

    /**
     * {@code DELETE  /metadata/:id} : delete the "id" metadata.
     *
     * @param id the id of the metadata to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/metadata/{id}")
    public ResponseEntity<Void> deleteMetadata(@PathVariable Long id) {
        log.debug("REST request to delete Metadata : {}", id);
        metadataService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
