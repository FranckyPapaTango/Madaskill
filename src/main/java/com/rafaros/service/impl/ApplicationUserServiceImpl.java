package com.rafaros.service.impl;

import com.rafaros.domain.ApplicationUser;
import com.rafaros.repository.ApplicationUserRepository;
import com.rafaros.service.ApplicationUserService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ApplicationUser}.
 */
@Service
@Transactional
public class ApplicationUserServiceImpl implements ApplicationUserService {

    private final Logger log = LoggerFactory.getLogger(ApplicationUserServiceImpl.class);

    private final ApplicationUserRepository applicationUserRepository;

    public ApplicationUserServiceImpl(ApplicationUserRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }

    @Override
    public ApplicationUser save(ApplicationUser applicationUser) {
        log.debug("Request to save ApplicationUser : {}", applicationUser);
        return applicationUserRepository.save(applicationUser);
    }

    @Override
    public ApplicationUser update(ApplicationUser applicationUser) {
        log.debug("Request to update ApplicationUser : {}", applicationUser);
        return applicationUserRepository.save(applicationUser);
    }

    @Override
    public Optional<ApplicationUser> partialUpdate(ApplicationUser applicationUser) {
        log.debug("Request to partially update ApplicationUser : {}", applicationUser);

        return applicationUserRepository
            .findById(applicationUser.getId())
            .map(existingApplicationUser -> {
                if (applicationUser.getPhoneNumber() != null) {
                    existingApplicationUser.setPhoneNumber(applicationUser.getPhoneNumber());
                }
                if (applicationUser.getEmail() != null) {
                    existingApplicationUser.setEmail(applicationUser.getEmail());
                }
                if (applicationUser.getLibelleAdresse() != null) {
                    existingApplicationUser.setLibelleAdresse(applicationUser.getLibelleAdresse());
                }
                if (applicationUser.getVilleTown() != null) {
                    existingApplicationUser.setVilleTown(applicationUser.getVilleTown());
                }
                if (applicationUser.getPaysCountry() != null) {
                    existingApplicationUser.setPaysCountry(applicationUser.getPaysCountry());
                }

                return existingApplicationUser;
            })
            .map(applicationUserRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ApplicationUser> findAll(Pageable pageable) {
        log.debug("Request to get all ApplicationUsers");
        return applicationUserRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ApplicationUser> findOne(Long id) {
        log.debug("Request to get ApplicationUser : {}", id);
        return applicationUserRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ApplicationUser : {}", id);
        applicationUserRepository.deleteById(id);
    }
}
