package com.rafaros.service.impl;

import com.rafaros.domain.Invoice;
import com.rafaros.repository.InvoiceRepository;
import com.rafaros.service.InvoiceService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Invoice}.
 */
@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService {

    private final Logger log = LoggerFactory.getLogger(InvoiceServiceImpl.class);

    private final InvoiceRepository invoiceRepository;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public Invoice save(Invoice invoice) {
        log.debug("Request to save Invoice : {}", invoice);
        return invoiceRepository.save(invoice);
    }

    @Override
    public Invoice update(Invoice invoice) {
        log.debug("Request to update Invoice : {}", invoice);
        invoice.setIsPersisted();
        return invoiceRepository.save(invoice);
    }

    @Override
    public Optional<Invoice> partialUpdate(Invoice invoice) {
        log.debug("Request to partially update Invoice : {}", invoice);

        return invoiceRepository
            .findById(invoice.getId())
            .map(existingInvoice -> {
                if (invoice.getObject() != null) {
                    existingInvoice.setObject(invoice.getObject());
                }
                if (invoice.getAccountCountry() != null) {
                    existingInvoice.setAccountCountry(invoice.getAccountCountry());
                }
                if (invoice.getAccountName() != null) {
                    existingInvoice.setAccountName(invoice.getAccountName());
                }
                if (invoice.getAccountTaxIds() != null) {
                    existingInvoice.setAccountTaxIds(invoice.getAccountTaxIds());
                }
                if (invoice.getAmountDue() != null) {
                    existingInvoice.setAmountDue(invoice.getAmountDue());
                }
                if (invoice.getAmountPaid() != null) {
                    existingInvoice.setAmountPaid(invoice.getAmountPaid());
                }
                if (invoice.getAmountRemaining() != null) {
                    existingInvoice.setAmountRemaining(invoice.getAmountRemaining());
                }
                if (invoice.getAmountShipping() != null) {
                    existingInvoice.setAmountShipping(invoice.getAmountShipping());
                }
                if (invoice.getApplication() != null) {
                    existingInvoice.setApplication(invoice.getApplication());
                }
                if (invoice.getApplicationFeeAmount() != null) {
                    existingInvoice.setApplicationFeeAmount(invoice.getApplicationFeeAmount());
                }
                if (invoice.getAttemptCount() != null) {
                    existingInvoice.setAttemptCount(invoice.getAttemptCount());
                }
                if (invoice.getAttempted() != null) {
                    existingInvoice.setAttempted(invoice.getAttempted());
                }
                if (invoice.getAutoAdvance() != null) {
                    existingInvoice.setAutoAdvance(invoice.getAutoAdvance());
                }
                if (invoice.getBillingReason() != null) {
                    existingInvoice.setBillingReason(invoice.getBillingReason());
                }
                if (invoice.getCharge() != null) {
                    existingInvoice.setCharge(invoice.getCharge());
                }
                if (invoice.getCollectionMethod() != null) {
                    existingInvoice.setCollectionMethod(invoice.getCollectionMethod());
                }
                if (invoice.getCreated() != null) {
                    existingInvoice.setCreated(invoice.getCreated());
                }
                if (invoice.getCurrency() != null) {
                    existingInvoice.setCurrency(invoice.getCurrency());
                }
                if (invoice.getCustomFields() != null) {
                    existingInvoice.setCustomFields(invoice.getCustomFields());
                }
                if (invoice.getCustomerStringValue() != null) {
                    existingInvoice.setCustomerStringValue(invoice.getCustomerStringValue());
                }
                if (invoice.getCustomerAddress() != null) {
                    existingInvoice.setCustomerAddress(invoice.getCustomerAddress());
                }
                if (invoice.getCustomerEmail() != null) {
                    existingInvoice.setCustomerEmail(invoice.getCustomerEmail());
                }
                if (invoice.getCustomerName() != null) {
                    existingInvoice.setCustomerName(invoice.getCustomerName());
                }
                if (invoice.getCustomerPhone() != null) {
                    existingInvoice.setCustomerPhone(invoice.getCustomerPhone());
                }
                if (invoice.getCustomerShipping() != null) {
                    existingInvoice.setCustomerShipping(invoice.getCustomerShipping());
                }
                if (invoice.getCustomerTaxExempt() != null) {
                    existingInvoice.setCustomerTaxExempt(invoice.getCustomerTaxExempt());
                }
                if (invoice.getCustomerTaxIds() != null) {
                    existingInvoice.setCustomerTaxIds(invoice.getCustomerTaxIds());
                }
                if (invoice.getDefaultPaymentMethod() != null) {
                    existingInvoice.setDefaultPaymentMethod(invoice.getDefaultPaymentMethod());
                }
                if (invoice.getDefaultSource() != null) {
                    existingInvoice.setDefaultSource(invoice.getDefaultSource());
                }
                if (invoice.getDefaultTaxRates() != null) {
                    existingInvoice.setDefaultTaxRates(invoice.getDefaultTaxRates());
                }
                if (invoice.getDescription() != null) {
                    existingInvoice.setDescription(invoice.getDescription());
                }
                if (invoice.getDiscount() != null) {
                    existingInvoice.setDiscount(invoice.getDiscount());
                }
                if (invoice.getDiscounts() != null) {
                    existingInvoice.setDiscounts(invoice.getDiscounts());
                }
                if (invoice.getDueDate() != null) {
                    existingInvoice.setDueDate(invoice.getDueDate());
                }
                if (invoice.getEffectiveAt() != null) {
                    existingInvoice.setEffectiveAt(invoice.getEffectiveAt());
                }
                if (invoice.getEndingBalance() != null) {
                    existingInvoice.setEndingBalance(invoice.getEndingBalance());
                }
                if (invoice.getFooter() != null) {
                    existingInvoice.setFooter(invoice.getFooter());
                }
                if (invoice.getFromInvoice() != null) {
                    existingInvoice.setFromInvoice(invoice.getFromInvoice());
                }
                if (invoice.getHostedInvoiceUrl() != null) {
                    existingInvoice.setHostedInvoiceUrl(invoice.getHostedInvoiceUrl());
                }
                if (invoice.getInvoicePdf() != null) {
                    existingInvoice.setInvoicePdf(invoice.getInvoicePdf());
                }
                if (invoice.getLastFinalizationError() != null) {
                    existingInvoice.setLastFinalizationError(invoice.getLastFinalizationError());
                }
                if (invoice.getLatestRevision() != null) {
                    existingInvoice.setLatestRevision(invoice.getLatestRevision());
                }
                if (invoice.getLivemode() != null) {
                    existingInvoice.setLivemode(invoice.getLivemode());
                }
                if (invoice.getMetadata() != null) {
                    existingInvoice.setMetadata(invoice.getMetadata());
                }
                if (invoice.getNextPaymentAttempt() != null) {
                    existingInvoice.setNextPaymentAttempt(invoice.getNextPaymentAttempt());
                }
                if (invoice.getNumber() != null) {
                    existingInvoice.setNumber(invoice.getNumber());
                }
                if (invoice.getOnBehalfOf() != null) {
                    existingInvoice.setOnBehalfOf(invoice.getOnBehalfOf());
                }
                if (invoice.getPaid() != null) {
                    existingInvoice.setPaid(invoice.getPaid());
                }
                if (invoice.getPaidOutOfBand() != null) {
                    existingInvoice.setPaidOutOfBand(invoice.getPaidOutOfBand());
                }
                if (invoice.getPaymentIntent() != null) {
                    existingInvoice.setPaymentIntent(invoice.getPaymentIntent());
                }
                if (invoice.getPaymentSettings() != null) {
                    existingInvoice.setPaymentSettings(invoice.getPaymentSettings());
                }
                if (invoice.getPeriodEnd() != null) {
                    existingInvoice.setPeriodEnd(invoice.getPeriodEnd());
                }
                if (invoice.getPeriodStart() != null) {
                    existingInvoice.setPeriodStart(invoice.getPeriodStart());
                }
                if (invoice.getPostPaymentCreditNotesAmount() != null) {
                    existingInvoice.setPostPaymentCreditNotesAmount(invoice.getPostPaymentCreditNotesAmount());
                }
                if (invoice.getPrePaymentCreditNotesAmount() != null) {
                    existingInvoice.setPrePaymentCreditNotesAmount(invoice.getPrePaymentCreditNotesAmount());
                }
                if (invoice.getQuote() != null) {
                    existingInvoice.setQuote(invoice.getQuote());
                }
                if (invoice.getReceiptNumber() != null) {
                    existingInvoice.setReceiptNumber(invoice.getReceiptNumber());
                }
                if (invoice.getRendering() != null) {
                    existingInvoice.setRendering(invoice.getRendering());
                }
                if (invoice.getRenderingOptions() != null) {
                    existingInvoice.setRenderingOptions(invoice.getRenderingOptions());
                }
                if (invoice.getShippingCost() != null) {
                    existingInvoice.setShippingCost(invoice.getShippingCost());
                }
                if (invoice.getShippingDetails() != null) {
                    existingInvoice.setShippingDetails(invoice.getShippingDetails());
                }
                if (invoice.getStartingBalance() != null) {
                    existingInvoice.setStartingBalance(invoice.getStartingBalance());
                }
                if (invoice.getStatementDescriptor() != null) {
                    existingInvoice.setStatementDescriptor(invoice.getStatementDescriptor());
                }
                if (invoice.getStatus() != null) {
                    existingInvoice.setStatus(invoice.getStatus());
                }
                if (invoice.getStatusTransitions() != null) {
                    existingInvoice.setStatusTransitions(invoice.getStatusTransitions());
                }
                if (invoice.getSubscription() != null) {
                    existingInvoice.setSubscription(invoice.getSubscription());
                }
                if (invoice.getSubscriptionDetails() != null) {
                    existingInvoice.setSubscriptionDetails(invoice.getSubscriptionDetails());
                }
                if (invoice.getSubtotal() != null) {
                    existingInvoice.setSubtotal(invoice.getSubtotal());
                }
                if (invoice.getSubtotalExcludingTax() != null) {
                    existingInvoice.setSubtotalExcludingTax(invoice.getSubtotalExcludingTax());
                }
                if (invoice.getTax() != null) {
                    existingInvoice.setTax(invoice.getTax());
                }
                if (invoice.getTestClock() != null) {
                    existingInvoice.setTestClock(invoice.getTestClock());
                }
                if (invoice.getTotal() != null) {
                    existingInvoice.setTotal(invoice.getTotal());
                }
                if (invoice.getTotalDiscountAmounts() != null) {
                    existingInvoice.setTotalDiscountAmounts(invoice.getTotalDiscountAmounts());
                }
                if (invoice.getTotalExcludingTax() != null) {
                    existingInvoice.setTotalExcludingTax(invoice.getTotalExcludingTax());
                }
                if (invoice.getTotalTaxAmounts() != null) {
                    existingInvoice.setTotalTaxAmounts(invoice.getTotalTaxAmounts());
                }
                if (invoice.getTransferData() != null) {
                    existingInvoice.setTransferData(invoice.getTransferData());
                }
                if (invoice.getWebhooksDeliveredAt() != null) {
                    existingInvoice.setWebhooksDeliveredAt(invoice.getWebhooksDeliveredAt());
                }

                return existingInvoice;
            })
            .map(invoiceRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Invoice> findAll(Pageable pageable) {
        log.debug("Request to get all Invoices");
        return invoiceRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Invoice> findOne(String id) {
        log.debug("Request to get Invoice : {}", id);
        return invoiceRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Invoice : {}", id);
        invoiceRepository.deleteById(id);
    }
}
