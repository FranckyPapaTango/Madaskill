package com.rafaros.repository;

import com.rafaros.domain.LineItem;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the LineItem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LineItemRepository extends JpaRepository<LineItem, String> {}
