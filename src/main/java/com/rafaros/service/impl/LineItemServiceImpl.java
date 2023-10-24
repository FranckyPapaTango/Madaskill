package com.rafaros.service.impl;

import com.rafaros.domain.LineItem;
import com.rafaros.repository.LineItemRepository;
import com.rafaros.service.LineItemService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link LineItem}.
 */
@Service
@Transactional
public class LineItemServiceImpl implements LineItemService {

    private final Logger log = LoggerFactory.getLogger(LineItemServiceImpl.class);

    private final LineItemRepository lineItemRepository;

    public LineItemServiceImpl(LineItemRepository lineItemRepository) {
        this.lineItemRepository = lineItemRepository;
    }

    @Override
    public LineItem save(LineItem lineItem) {
        log.debug("Request to save LineItem : {}", lineItem);
        return lineItemRepository.save(lineItem);
    }

    @Override
    public LineItem update(LineItem lineItem) {
        log.debug("Request to update LineItem : {}", lineItem);
        lineItem.setIsPersisted();
        return lineItemRepository.save(lineItem);
    }

    @Override
    public Optional<LineItem> partialUpdate(LineItem lineItem) {
        log.debug("Request to partially update LineItem : {}", lineItem);

        return lineItemRepository
            .findById(lineItem.getId())
            .map(existingLineItem -> {
                if (lineItem.getObject() != null) {
                    existingLineItem.setObject(lineItem.getObject());
                }
                if (lineItem.getAmount() != null) {
                    existingLineItem.setAmount(lineItem.getAmount());
                }
                if (lineItem.getAmountExcludingTax() != null) {
                    existingLineItem.setAmountExcludingTax(lineItem.getAmountExcludingTax());
                }
                if (lineItem.getCurrency() != null) {
                    existingLineItem.setCurrency(lineItem.getCurrency());
                }
                if (lineItem.getDescription() != null) {
                    existingLineItem.setDescription(lineItem.getDescription());
                }
                if (lineItem.getDiscountAmounts() != null) {
                    existingLineItem.setDiscountAmounts(lineItem.getDiscountAmounts());
                }
                if (lineItem.getDiscountable() != null) {
                    existingLineItem.setDiscountable(lineItem.getDiscountable());
                }
                if (lineItem.getDiscounts() != null) {
                    existingLineItem.setDiscounts(lineItem.getDiscounts());
                }
                if (lineItem.getInvoiceItem() != null) {
                    existingLineItem.setInvoiceItem(lineItem.getInvoiceItem());
                }
                if (lineItem.getLivemode() != null) {
                    existingLineItem.setLivemode(lineItem.getLivemode());
                }
                if (lineItem.getMetadata() != null) {
                    existingLineItem.setMetadata(lineItem.getMetadata());
                }
                if (lineItem.getPeriodEnd() != null) {
                    existingLineItem.setPeriodEnd(lineItem.getPeriodEnd());
                }
                if (lineItem.getPeriodStart() != null) {
                    existingLineItem.setPeriodStart(lineItem.getPeriodStart());
                }
                if (lineItem.getPrice() != null) {
                    existingLineItem.setPrice(lineItem.getPrice());
                }
                if (lineItem.getProration() != null) {
                    existingLineItem.setProration(lineItem.getProration());
                }
                if (lineItem.getProrationDetails() != null) {
                    existingLineItem.setProrationDetails(lineItem.getProrationDetails());
                }
                if (lineItem.getQuantity() != null) {
                    existingLineItem.setQuantity(lineItem.getQuantity());
                }
                if (lineItem.getSubscription() != null) {
                    existingLineItem.setSubscription(lineItem.getSubscription());
                }
                if (lineItem.getTaxAmounts() != null) {
                    existingLineItem.setTaxAmounts(lineItem.getTaxAmounts());
                }
                if (lineItem.getTaxRates() != null) {
                    existingLineItem.setTaxRates(lineItem.getTaxRates());
                }
                if (lineItem.getType() != null) {
                    existingLineItem.setType(lineItem.getType());
                }
                if (lineItem.getUnitAmountExcludingTax() != null) {
                    existingLineItem.setUnitAmountExcludingTax(lineItem.getUnitAmountExcludingTax());
                }

                return existingLineItem;
            })
            .map(lineItemRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<LineItem> findAll(Pageable pageable) {
        log.debug("Request to get all LineItems");
        return lineItemRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<LineItem> findOne(String id) {
        log.debug("Request to get LineItem : {}", id);
        return lineItemRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete LineItem : {}", id);
        lineItemRepository.deleteById(id);
    }
}
