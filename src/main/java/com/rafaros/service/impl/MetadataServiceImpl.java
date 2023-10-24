package com.rafaros.service.impl;

import com.rafaros.domain.Metadata;
import com.rafaros.repository.MetadataRepository;
import com.rafaros.service.MetadataService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Metadata}.
 */
@Service
@Transactional
public class MetadataServiceImpl implements MetadataService {

    private final Logger log = LoggerFactory.getLogger(MetadataServiceImpl.class);

    private final MetadataRepository metadataRepository;

    public MetadataServiceImpl(MetadataRepository metadataRepository) {
        this.metadataRepository = metadataRepository;
    }

    @Override
    public Metadata save(Metadata metadata) {
        log.debug("Request to save Metadata : {}", metadata);
        return metadataRepository.save(metadata);
    }

    @Override
    public Metadata update(Metadata metadata) {
        log.debug("Request to update Metadata : {}", metadata);
        return metadataRepository.save(metadata);
    }

    @Override
    public Optional<Metadata> partialUpdate(Metadata metadata) {
        log.debug("Request to partially update Metadata : {}", metadata);

        return metadataRepository
            .findById(metadata.getId())
            .map(existingMetadata -> {
                if (metadata.getOrderId() != null) {
                    existingMetadata.setOrderId(metadata.getOrderId());
                }

                return existingMetadata;
            })
            .map(metadataRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Metadata> findAll(Pageable pageable) {
        log.debug("Request to get all Metadata");
        return metadataRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Metadata> findOne(Long id) {
        log.debug("Request to get Metadata : {}", id);
        return metadataRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Metadata : {}", id);
        metadataRepository.deleteById(id);
    }
}
