package com.rafaros.service.impl;

import com.rafaros.domain.PreferredLocales;
import com.rafaros.repository.PreferredLocalesRepository;
import com.rafaros.service.PreferredLocalesService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link PreferredLocales}.
 */
@Service
@Transactional
public class PreferredLocalesServiceImpl implements PreferredLocalesService {

    private final Logger log = LoggerFactory.getLogger(PreferredLocalesServiceImpl.class);

    private final PreferredLocalesRepository preferredLocalesRepository;

    public PreferredLocalesServiceImpl(PreferredLocalesRepository preferredLocalesRepository) {
        this.preferredLocalesRepository = preferredLocalesRepository;
    }

    @Override
    public PreferredLocales save(PreferredLocales preferredLocales) {
        log.debug("Request to save PreferredLocales : {}", preferredLocales);
        return preferredLocalesRepository.save(preferredLocales);
    }

    @Override
    public PreferredLocales update(PreferredLocales preferredLocales) {
        log.debug("Request to update PreferredLocales : {}", preferredLocales);
        return preferredLocalesRepository.save(preferredLocales);
    }

    @Override
    public Optional<PreferredLocales> partialUpdate(PreferredLocales preferredLocales) {
        log.debug("Request to partially update PreferredLocales : {}", preferredLocales);

        return preferredLocalesRepository
            .findById(preferredLocales.getId())
            .map(existingPreferredLocales -> {
                if (preferredLocales.getPreferredLocales() != null) {
                    existingPreferredLocales.setPreferredLocales(preferredLocales.getPreferredLocales());
                }

                return existingPreferredLocales;
            })
            .map(preferredLocalesRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PreferredLocales> findAll(Pageable pageable) {
        log.debug("Request to get all PreferredLocales");
        return preferredLocalesRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PreferredLocales> findOne(Long id) {
        log.debug("Request to get PreferredLocales : {}", id);
        return preferredLocalesRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete PreferredLocales : {}", id);
        preferredLocalesRepository.deleteById(id);
    }
}
