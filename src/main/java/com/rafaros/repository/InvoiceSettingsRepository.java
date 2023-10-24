package com.rafaros.repository;

import com.rafaros.domain.InvoiceSettings;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the InvoiceSettings entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InvoiceSettingsRepository extends JpaRepository<InvoiceSettings, Long> {}
