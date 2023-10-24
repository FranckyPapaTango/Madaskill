package com.rafaros.repository;

import com.rafaros.domain.PreferredLocales;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the PreferredLocales entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PreferredLocalesRepository extends JpaRepository<PreferredLocales, Long> {}
