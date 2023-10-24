package com.rafaros.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.domain.Persistable;

/**
 * A Invoice.
 */
@JsonIgnoreProperties(value = { "new" })
@Entity
@Table(name = "invoice")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Invoice implements Serializable, Persistable<String> {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "object")
    private String object;

    @Column(name = "account_country")
    private String accountCountry;

    @Column(name = "account_name")
    private String accountName;

    @Column(name = "account_tax_ids")
    private String accountTaxIds;

    @Column(name = "amount_due")
    private Long amountDue;

    @Column(name = "amount_paid")
    private Long amountPaid;

    @Column(name = "amount_remaining")
    private Long amountRemaining;

    @Column(name = "amount_shipping")
    private Long amountShipping;

    @Column(name = "application")
    private String application;

    @Column(name = "application_fee_amount")
    private Long applicationFeeAmount;

    @Column(name = "attempt_count")
    private Integer attemptCount;

    @Column(name = "attempted")
    private Boolean attempted;

    @Column(name = "auto_advance")
    private Boolean autoAdvance;

    @Column(name = "billing_reason")
    private String billingReason;

    @Column(name = "charge")
    private String charge;

    @Column(name = "collection_method")
    private String collectionMethod;

    @Column(name = "created")
    private Long created;

    @Column(name = "currency")
    private String currency;

    @Column(name = "custom_fields")
    private String customFields;

    @Column(name = "customer_string_value")
    private String customerStringValue;

    @Column(name = "customer_address")
    private String customerAddress;

    @Column(name = "customer_email")
    private String customerEmail;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_phone")
    private String customerPhone;

    @Column(name = "customer_shipping")
    private String customerShipping;

    @Column(name = "customer_tax_exempt")
    private String customerTaxExempt;

    @Column(name = "customer_tax_ids")
    private String customerTaxIds;

    @Column(name = "default_payment_method")
    private String defaultPaymentMethod;

    @Column(name = "default_source")
    private String defaultSource;

    @Column(name = "default_tax_rates")
    private String defaultTaxRates;

    @Column(name = "description")
    private String description;

    @Column(name = "discount")
    private String discount;

    @Column(name = "discounts")
    private String discounts;

    @Column(name = "due_date")
    private Long dueDate;

    @Column(name = "effective_at")
    private Long effectiveAt;

    @Column(name = "ending_balance")
    private Long endingBalance;

    @Column(name = "footer")
    private String footer;

    @Column(name = "from_invoice")
    private String fromInvoice;

    @Column(name = "hosted_invoice_url")
    private String hostedInvoiceUrl;

    @Column(name = "invoice_pdf")
    private String invoicePdf;

    @Column(name = "last_finalization_error")
    private String lastFinalizationError;

    @Column(name = "latest_revision")
    private String latestRevision;

    @Column(name = "livemode")
    private Boolean livemode;

    @Column(name = "metadata")
    private String metadata;

    @Column(name = "next_payment_attempt")
    private Long nextPaymentAttempt;

    @Column(name = "number")
    private String number;

    @Column(name = "on_behalf_of")
    private String onBehalfOf;

    @Column(name = "paid")
    private Boolean paid;

    @Column(name = "paid_out_of_band")
    private Boolean paidOutOfBand;

    @Column(name = "payment_intent")
    private String paymentIntent;

    @Column(name = "payment_settings")
    private String paymentSettings;

    @Column(name = "period_end")
    private Long periodEnd;

    @Column(name = "period_start")
    private Long periodStart;

    @Column(name = "post_payment_credit_notes_amount")
    private Long postPaymentCreditNotesAmount;

    @Column(name = "pre_payment_credit_notes_amount")
    private Long prePaymentCreditNotesAmount;

    @Column(name = "quote")
    private String quote;

    @Column(name = "receipt_number")
    private String receiptNumber;

    @Column(name = "rendering")
    private String rendering;

    @Column(name = "rendering_options")
    private String renderingOptions;

    @Column(name = "shipping_cost")
    private Long shippingCost;

    @Column(name = "shipping_details")
    private String shippingDetails;

    @Column(name = "starting_balance")
    private Long startingBalance;

    @Column(name = "statement_descriptor")
    private String statementDescriptor;

    @Column(name = "status")
    private String status;

    @Column(name = "status_transitions")
    private String statusTransitions;

    @Column(name = "subscription")
    private String subscription;

    @Column(name = "subscription_details")
    private String subscriptionDetails;

    @Column(name = "subtotal")
    private Long subtotal;

    @Column(name = "subtotal_excluding_tax")
    private Long subtotalExcludingTax;

    @Column(name = "tax")
    private String tax;

    @Column(name = "test_clock")
    private String testClock;

    @Column(name = "total")
    private Long total;

    @Column(name = "total_discount_amounts")
    private String totalDiscountAmounts;

    @Column(name = "total_excluding_tax")
    private Long totalExcludingTax;

    @Column(name = "total_tax_amounts")
    private String totalTaxAmounts;

    @Column(name = "transfer_data")
    private String transferData;

    @Column(name = "webhooks_delivered_at")
    private Long webhooksDeliveredAt;

    @Transient
    private boolean isPersisted;

    @JsonIgnoreProperties(value = { "lineItems", "customer" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    private Commande commande;

    @ManyToOne
    @JsonIgnoreProperties(value = { "invoiceSettings", "metadata", "invoices", "preferredLocales" }, allowSetters = true)
    private Customer customer;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Invoice id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObject() {
        return this.object;
    }

    public Invoice object(String object) {
        this.setObject(object);
        return this;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getAccountCountry() {
        return this.accountCountry;
    }

    public Invoice accountCountry(String accountCountry) {
        this.setAccountCountry(accountCountry);
        return this;
    }

    public void setAccountCountry(String accountCountry) {
        this.accountCountry = accountCountry;
    }

    public String getAccountName() {
        return this.accountName;
    }

    public Invoice accountName(String accountName) {
        this.setAccountName(accountName);
        return this;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountTaxIds() {
        return this.accountTaxIds;
    }

    public Invoice accountTaxIds(String accountTaxIds) {
        this.setAccountTaxIds(accountTaxIds);
        return this;
    }

    public void setAccountTaxIds(String accountTaxIds) {
        this.accountTaxIds = accountTaxIds;
    }

    public Long getAmountDue() {
        return this.amountDue;
    }

    public Invoice amountDue(Long amountDue) {
        this.setAmountDue(amountDue);
        return this;
    }

    public void setAmountDue(Long amountDue) {
        this.amountDue = amountDue;
    }

    public Long getAmountPaid() {
        return this.amountPaid;
    }

    public Invoice amountPaid(Long amountPaid) {
        this.setAmountPaid(amountPaid);
        return this;
    }

    public void setAmountPaid(Long amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Long getAmountRemaining() {
        return this.amountRemaining;
    }

    public Invoice amountRemaining(Long amountRemaining) {
        this.setAmountRemaining(amountRemaining);
        return this;
    }

    public void setAmountRemaining(Long amountRemaining) {
        this.amountRemaining = amountRemaining;
    }

    public Long getAmountShipping() {
        return this.amountShipping;
    }

    public Invoice amountShipping(Long amountShipping) {
        this.setAmountShipping(amountShipping);
        return this;
    }

    public void setAmountShipping(Long amountShipping) {
        this.amountShipping = amountShipping;
    }

    public String getApplication() {
        return this.application;
    }

    public Invoice application(String application) {
        this.setApplication(application);
        return this;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public Long getApplicationFeeAmount() {
        return this.applicationFeeAmount;
    }

    public Invoice applicationFeeAmount(Long applicationFeeAmount) {
        this.setApplicationFeeAmount(applicationFeeAmount);
        return this;
    }

    public void setApplicationFeeAmount(Long applicationFeeAmount) {
        this.applicationFeeAmount = applicationFeeAmount;
    }

    public Integer getAttemptCount() {
        return this.attemptCount;
    }

    public Invoice attemptCount(Integer attemptCount) {
        this.setAttemptCount(attemptCount);
        return this;
    }

    public void setAttemptCount(Integer attemptCount) {
        this.attemptCount = attemptCount;
    }

    public Boolean getAttempted() {
        return this.attempted;
    }

    public Invoice attempted(Boolean attempted) {
        this.setAttempted(attempted);
        return this;
    }

    public void setAttempted(Boolean attempted) {
        this.attempted = attempted;
    }

    public Boolean getAutoAdvance() {
        return this.autoAdvance;
    }

    public Invoice autoAdvance(Boolean autoAdvance) {
        this.setAutoAdvance(autoAdvance);
        return this;
    }

    public void setAutoAdvance(Boolean autoAdvance) {
        this.autoAdvance = autoAdvance;
    }

    public String getBillingReason() {
        return this.billingReason;
    }

    public Invoice billingReason(String billingReason) {
        this.setBillingReason(billingReason);
        return this;
    }

    public void setBillingReason(String billingReason) {
        this.billingReason = billingReason;
    }

    public String getCharge() {
        return this.charge;
    }

    public Invoice charge(String charge) {
        this.setCharge(charge);
        return this;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public String getCollectionMethod() {
        return this.collectionMethod;
    }

    public Invoice collectionMethod(String collectionMethod) {
        this.setCollectionMethod(collectionMethod);
        return this;
    }

    public void setCollectionMethod(String collectionMethod) {
        this.collectionMethod = collectionMethod;
    }

    public Long getCreated() {
        return this.created;
    }

    public Invoice created(Long created) {
        this.setCreated(created);
        return this;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public String getCurrency() {
        return this.currency;
    }

    public Invoice currency(String currency) {
        this.setCurrency(currency);
        return this;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCustomFields() {
        return this.customFields;
    }

    public Invoice customFields(String customFields) {
        this.setCustomFields(customFields);
        return this;
    }

    public void setCustomFields(String customFields) {
        this.customFields = customFields;
    }

    public String getCustomerStringValue() {
        return this.customerStringValue;
    }

    public Invoice customerStringValue(String customerStringValue) {
        this.setCustomerStringValue(customerStringValue);
        return this;
    }

    public void setCustomerStringValue(String customerStringValue) {
        this.customerStringValue = customerStringValue;
    }

    public String getCustomerAddress() {
        return this.customerAddress;
    }

    public Invoice customerAddress(String customerAddress) {
        this.setCustomerAddress(customerAddress);
        return this;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerEmail() {
        return this.customerEmail;
    }

    public Invoice customerEmail(String customerEmail) {
        this.setCustomerEmail(customerEmail);
        return this;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public Invoice customerName(String customerName) {
        this.setCustomerName(customerName);
        return this;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return this.customerPhone;
    }

    public Invoice customerPhone(String customerPhone) {
        this.setCustomerPhone(customerPhone);
        return this;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerShipping() {
        return this.customerShipping;
    }

    public Invoice customerShipping(String customerShipping) {
        this.setCustomerShipping(customerShipping);
        return this;
    }

    public void setCustomerShipping(String customerShipping) {
        this.customerShipping = customerShipping;
    }

    public String getCustomerTaxExempt() {
        return this.customerTaxExempt;
    }

    public Invoice customerTaxExempt(String customerTaxExempt) {
        this.setCustomerTaxExempt(customerTaxExempt);
        return this;
    }

    public void setCustomerTaxExempt(String customerTaxExempt) {
        this.customerTaxExempt = customerTaxExempt;
    }

    public String getCustomerTaxIds() {
        return this.customerTaxIds;
    }

    public Invoice customerTaxIds(String customerTaxIds) {
        this.setCustomerTaxIds(customerTaxIds);
        return this;
    }

    public void setCustomerTaxIds(String customerTaxIds) {
        this.customerTaxIds = customerTaxIds;
    }

    public String getDefaultPaymentMethod() {
        return this.defaultPaymentMethod;
    }

    public Invoice defaultPaymentMethod(String defaultPaymentMethod) {
        this.setDefaultPaymentMethod(defaultPaymentMethod);
        return this;
    }

    public void setDefaultPaymentMethod(String defaultPaymentMethod) {
        this.defaultPaymentMethod = defaultPaymentMethod;
    }

    public String getDefaultSource() {
        return this.defaultSource;
    }

    public Invoice defaultSource(String defaultSource) {
        this.setDefaultSource(defaultSource);
        return this;
    }

    public void setDefaultSource(String defaultSource) {
        this.defaultSource = defaultSource;
    }

    public String getDefaultTaxRates() {
        return this.defaultTaxRates;
    }

    public Invoice defaultTaxRates(String defaultTaxRates) {
        this.setDefaultTaxRates(defaultTaxRates);
        return this;
    }

    public void setDefaultTaxRates(String defaultTaxRates) {
        this.defaultTaxRates = defaultTaxRates;
    }

    public String getDescription() {
        return this.description;
    }

    public Invoice description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDiscount() {
        return this.discount;
    }

    public Invoice discount(String discount) {
        this.setDiscount(discount);
        return this;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getDiscounts() {
        return this.discounts;
    }

    public Invoice discounts(String discounts) {
        this.setDiscounts(discounts);
        return this;
    }

    public void setDiscounts(String discounts) {
        this.discounts = discounts;
    }

    public Long getDueDate() {
        return this.dueDate;
    }

    public Invoice dueDate(Long dueDate) {
        this.setDueDate(dueDate);
        return this;
    }

    public void setDueDate(Long dueDate) {
        this.dueDate = dueDate;
    }

    public Long getEffectiveAt() {
        return this.effectiveAt;
    }

    public Invoice effectiveAt(Long effectiveAt) {
        this.setEffectiveAt(effectiveAt);
        return this;
    }

    public void setEffectiveAt(Long effectiveAt) {
        this.effectiveAt = effectiveAt;
    }

    public Long getEndingBalance() {
        return this.endingBalance;
    }

    public Invoice endingBalance(Long endingBalance) {
        this.setEndingBalance(endingBalance);
        return this;
    }

    public void setEndingBalance(Long endingBalance) {
        this.endingBalance = endingBalance;
    }

    public String getFooter() {
        return this.footer;
    }

    public Invoice footer(String footer) {
        this.setFooter(footer);
        return this;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

    public String getFromInvoice() {
        return this.fromInvoice;
    }

    public Invoice fromInvoice(String fromInvoice) {
        this.setFromInvoice(fromInvoice);
        return this;
    }

    public void setFromInvoice(String fromInvoice) {
        this.fromInvoice = fromInvoice;
    }

    public String getHostedInvoiceUrl() {
        return this.hostedInvoiceUrl;
    }

    public Invoice hostedInvoiceUrl(String hostedInvoiceUrl) {
        this.setHostedInvoiceUrl(hostedInvoiceUrl);
        return this;
    }

    public void setHostedInvoiceUrl(String hostedInvoiceUrl) {
        this.hostedInvoiceUrl = hostedInvoiceUrl;
    }

    public String getInvoicePdf() {
        return this.invoicePdf;
    }

    public Invoice invoicePdf(String invoicePdf) {
        this.setInvoicePdf(invoicePdf);
        return this;
    }

    public void setInvoicePdf(String invoicePdf) {
        this.invoicePdf = invoicePdf;
    }

    public String getLastFinalizationError() {
        return this.lastFinalizationError;
    }

    public Invoice lastFinalizationError(String lastFinalizationError) {
        this.setLastFinalizationError(lastFinalizationError);
        return this;
    }

    public void setLastFinalizationError(String lastFinalizationError) {
        this.lastFinalizationError = lastFinalizationError;
    }

    public String getLatestRevision() {
        return this.latestRevision;
    }

    public Invoice latestRevision(String latestRevision) {
        this.setLatestRevision(latestRevision);
        return this;
    }

    public void setLatestRevision(String latestRevision) {
        this.latestRevision = latestRevision;
    }

    public Boolean getLivemode() {
        return this.livemode;
    }

    public Invoice livemode(Boolean livemode) {
        this.setLivemode(livemode);
        return this;
    }

    public void setLivemode(Boolean livemode) {
        this.livemode = livemode;
    }

    public String getMetadata() {
        return this.metadata;
    }

    public Invoice metadata(String metadata) {
        this.setMetadata(metadata);
        return this;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public Long getNextPaymentAttempt() {
        return this.nextPaymentAttempt;
    }

    public Invoice nextPaymentAttempt(Long nextPaymentAttempt) {
        this.setNextPaymentAttempt(nextPaymentAttempt);
        return this;
    }

    public void setNextPaymentAttempt(Long nextPaymentAttempt) {
        this.nextPaymentAttempt = nextPaymentAttempt;
    }

    public String getNumber() {
        return this.number;
    }

    public Invoice number(String number) {
        this.setNumber(number);
        return this;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getOnBehalfOf() {
        return this.onBehalfOf;
    }

    public Invoice onBehalfOf(String onBehalfOf) {
        this.setOnBehalfOf(onBehalfOf);
        return this;
    }

    public void setOnBehalfOf(String onBehalfOf) {
        this.onBehalfOf = onBehalfOf;
    }

    public Boolean getPaid() {
        return this.paid;
    }

    public Invoice paid(Boolean paid) {
        this.setPaid(paid);
        return this;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public Boolean getPaidOutOfBand() {
        return this.paidOutOfBand;
    }

    public Invoice paidOutOfBand(Boolean paidOutOfBand) {
        this.setPaidOutOfBand(paidOutOfBand);
        return this;
    }

    public void setPaidOutOfBand(Boolean paidOutOfBand) {
        this.paidOutOfBand = paidOutOfBand;
    }

    public String getPaymentIntent() {
        return this.paymentIntent;
    }

    public Invoice paymentIntent(String paymentIntent) {
        this.setPaymentIntent(paymentIntent);
        return this;
    }

    public void setPaymentIntent(String paymentIntent) {
        this.paymentIntent = paymentIntent;
    }

    public String getPaymentSettings() {
        return this.paymentSettings;
    }

    public Invoice paymentSettings(String paymentSettings) {
        this.setPaymentSettings(paymentSettings);
        return this;
    }

    public void setPaymentSettings(String paymentSettings) {
        this.paymentSettings = paymentSettings;
    }

    public Long getPeriodEnd() {
        return this.periodEnd;
    }

    public Invoice periodEnd(Long periodEnd) {
        this.setPeriodEnd(periodEnd);
        return this;
    }

    public void setPeriodEnd(Long periodEnd) {
        this.periodEnd = periodEnd;
    }

    public Long getPeriodStart() {
        return this.periodStart;
    }

    public Invoice periodStart(Long periodStart) {
        this.setPeriodStart(periodStart);
        return this;
    }

    public void setPeriodStart(Long periodStart) {
        this.periodStart = periodStart;
    }

    public Long getPostPaymentCreditNotesAmount() {
        return this.postPaymentCreditNotesAmount;
    }

    public Invoice postPaymentCreditNotesAmount(Long postPaymentCreditNotesAmount) {
        this.setPostPaymentCreditNotesAmount(postPaymentCreditNotesAmount);
        return this;
    }

    public void setPostPaymentCreditNotesAmount(Long postPaymentCreditNotesAmount) {
        this.postPaymentCreditNotesAmount = postPaymentCreditNotesAmount;
    }

    public Long getPrePaymentCreditNotesAmount() {
        return this.prePaymentCreditNotesAmount;
    }

    public Invoice prePaymentCreditNotesAmount(Long prePaymentCreditNotesAmount) {
        this.setPrePaymentCreditNotesAmount(prePaymentCreditNotesAmount);
        return this;
    }

    public void setPrePaymentCreditNotesAmount(Long prePaymentCreditNotesAmount) {
        this.prePaymentCreditNotesAmount = prePaymentCreditNotesAmount;
    }

    public String getQuote() {
        return this.quote;
    }

    public Invoice quote(String quote) {
        this.setQuote(quote);
        return this;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getReceiptNumber() {
        return this.receiptNumber;
    }

    public Invoice receiptNumber(String receiptNumber) {
        this.setReceiptNumber(receiptNumber);
        return this;
    }

    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public String getRendering() {
        return this.rendering;
    }

    public Invoice rendering(String rendering) {
        this.setRendering(rendering);
        return this;
    }

    public void setRendering(String rendering) {
        this.rendering = rendering;
    }

    public String getRenderingOptions() {
        return this.renderingOptions;
    }

    public Invoice renderingOptions(String renderingOptions) {
        this.setRenderingOptions(renderingOptions);
        return this;
    }

    public void setRenderingOptions(String renderingOptions) {
        this.renderingOptions = renderingOptions;
    }

    public Long getShippingCost() {
        return this.shippingCost;
    }

    public Invoice shippingCost(Long shippingCost) {
        this.setShippingCost(shippingCost);
        return this;
    }

    public void setShippingCost(Long shippingCost) {
        this.shippingCost = shippingCost;
    }

    public String getShippingDetails() {
        return this.shippingDetails;
    }

    public Invoice shippingDetails(String shippingDetails) {
        this.setShippingDetails(shippingDetails);
        return this;
    }

    public void setShippingDetails(String shippingDetails) {
        this.shippingDetails = shippingDetails;
    }

    public Long getStartingBalance() {
        return this.startingBalance;
    }

    public Invoice startingBalance(Long startingBalance) {
        this.setStartingBalance(startingBalance);
        return this;
    }

    public void setStartingBalance(Long startingBalance) {
        this.startingBalance = startingBalance;
    }

    public String getStatementDescriptor() {
        return this.statementDescriptor;
    }

    public Invoice statementDescriptor(String statementDescriptor) {
        this.setStatementDescriptor(statementDescriptor);
        return this;
    }

    public void setStatementDescriptor(String statementDescriptor) {
        this.statementDescriptor = statementDescriptor;
    }

    public String getStatus() {
        return this.status;
    }

    public Invoice status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusTransitions() {
        return this.statusTransitions;
    }

    public Invoice statusTransitions(String statusTransitions) {
        this.setStatusTransitions(statusTransitions);
        return this;
    }

    public void setStatusTransitions(String statusTransitions) {
        this.statusTransitions = statusTransitions;
    }

    public String getSubscription() {
        return this.subscription;
    }

    public Invoice subscription(String subscription) {
        this.setSubscription(subscription);
        return this;
    }

    public void setSubscription(String subscription) {
        this.subscription = subscription;
    }

    public String getSubscriptionDetails() {
        return this.subscriptionDetails;
    }

    public Invoice subscriptionDetails(String subscriptionDetails) {
        this.setSubscriptionDetails(subscriptionDetails);
        return this;
    }

    public void setSubscriptionDetails(String subscriptionDetails) {
        this.subscriptionDetails = subscriptionDetails;
    }

    public Long getSubtotal() {
        return this.subtotal;
    }

    public Invoice subtotal(Long subtotal) {
        this.setSubtotal(subtotal);
        return this;
    }

    public void setSubtotal(Long subtotal) {
        this.subtotal = subtotal;
    }

    public Long getSubtotalExcludingTax() {
        return this.subtotalExcludingTax;
    }

    public Invoice subtotalExcludingTax(Long subtotalExcludingTax) {
        this.setSubtotalExcludingTax(subtotalExcludingTax);
        return this;
    }

    public void setSubtotalExcludingTax(Long subtotalExcludingTax) {
        this.subtotalExcludingTax = subtotalExcludingTax;
    }

    public String getTax() {
        return this.tax;
    }

    public Invoice tax(String tax) {
        this.setTax(tax);
        return this;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getTestClock() {
        return this.testClock;
    }

    public Invoice testClock(String testClock) {
        this.setTestClock(testClock);
        return this;
    }

    public void setTestClock(String testClock) {
        this.testClock = testClock;
    }

    public Long getTotal() {
        return this.total;
    }

    public Invoice total(Long total) {
        this.setTotal(total);
        return this;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public String getTotalDiscountAmounts() {
        return this.totalDiscountAmounts;
    }

    public Invoice totalDiscountAmounts(String totalDiscountAmounts) {
        this.setTotalDiscountAmounts(totalDiscountAmounts);
        return this;
    }

    public void setTotalDiscountAmounts(String totalDiscountAmounts) {
        this.totalDiscountAmounts = totalDiscountAmounts;
    }

    public Long getTotalExcludingTax() {
        return this.totalExcludingTax;
    }

    public Invoice totalExcludingTax(Long totalExcludingTax) {
        this.setTotalExcludingTax(totalExcludingTax);
        return this;
    }

    public void setTotalExcludingTax(Long totalExcludingTax) {
        this.totalExcludingTax = totalExcludingTax;
    }

    public String getTotalTaxAmounts() {
        return this.totalTaxAmounts;
    }

    public Invoice totalTaxAmounts(String totalTaxAmounts) {
        this.setTotalTaxAmounts(totalTaxAmounts);
        return this;
    }

    public void setTotalTaxAmounts(String totalTaxAmounts) {
        this.totalTaxAmounts = totalTaxAmounts;
    }

    public String getTransferData() {
        return this.transferData;
    }

    public Invoice transferData(String transferData) {
        this.setTransferData(transferData);
        return this;
    }

    public void setTransferData(String transferData) {
        this.transferData = transferData;
    }

    public Long getWebhooksDeliveredAt() {
        return this.webhooksDeliveredAt;
    }

    public Invoice webhooksDeliveredAt(Long webhooksDeliveredAt) {
        this.setWebhooksDeliveredAt(webhooksDeliveredAt);
        return this;
    }

    public void setWebhooksDeliveredAt(Long webhooksDeliveredAt) {
        this.webhooksDeliveredAt = webhooksDeliveredAt;
    }

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public Invoice setIsPersisted() {
        this.isPersisted = true;
        return this;
    }

    @PostLoad
    @PostPersist
    public void updateEntityState() {
        this.setIsPersisted();
    }

    public Commande getCommande() {
        return this.commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Invoice commande(Commande commande) {
        this.setCommande(commande);
        return this;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Invoice customer(Customer customer) {
        this.setCustomer(customer);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Invoice)) {
            return false;
        }
        return id != null && id.equals(((Invoice) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Invoice{" +
            "id=" + getId() +
            ", object='" + getObject() + "'" +
            ", accountCountry='" + getAccountCountry() + "'" +
            ", accountName='" + getAccountName() + "'" +
            ", accountTaxIds='" + getAccountTaxIds() + "'" +
            ", amountDue=" + getAmountDue() +
            ", amountPaid=" + getAmountPaid() +
            ", amountRemaining=" + getAmountRemaining() +
            ", amountShipping=" + getAmountShipping() +
            ", application='" + getApplication() + "'" +
            ", applicationFeeAmount=" + getApplicationFeeAmount() +
            ", attemptCount=" + getAttemptCount() +
            ", attempted='" + getAttempted() + "'" +
            ", autoAdvance='" + getAutoAdvance() + "'" +
            ", billingReason='" + getBillingReason() + "'" +
            ", charge='" + getCharge() + "'" +
            ", collectionMethod='" + getCollectionMethod() + "'" +
            ", created=" + getCreated() +
            ", currency='" + getCurrency() + "'" +
            ", customFields='" + getCustomFields() + "'" +
            ", customerStringValue='" + getCustomerStringValue() + "'" +
            ", customerAddress='" + getCustomerAddress() + "'" +
            ", customerEmail='" + getCustomerEmail() + "'" +
            ", customerName='" + getCustomerName() + "'" +
            ", customerPhone='" + getCustomerPhone() + "'" +
            ", customerShipping='" + getCustomerShipping() + "'" +
            ", customerTaxExempt='" + getCustomerTaxExempt() + "'" +
            ", customerTaxIds='" + getCustomerTaxIds() + "'" +
            ", defaultPaymentMethod='" + getDefaultPaymentMethod() + "'" +
            ", defaultSource='" + getDefaultSource() + "'" +
            ", defaultTaxRates='" + getDefaultTaxRates() + "'" +
            ", description='" + getDescription() + "'" +
            ", discount='" + getDiscount() + "'" +
            ", discounts='" + getDiscounts() + "'" +
            ", dueDate=" + getDueDate() +
            ", effectiveAt=" + getEffectiveAt() +
            ", endingBalance=" + getEndingBalance() +
            ", footer='" + getFooter() + "'" +
            ", fromInvoice='" + getFromInvoice() + "'" +
            ", hostedInvoiceUrl='" + getHostedInvoiceUrl() + "'" +
            ", invoicePdf='" + getInvoicePdf() + "'" +
            ", lastFinalizationError='" + getLastFinalizationError() + "'" +
            ", latestRevision='" + getLatestRevision() + "'" +
            ", livemode='" + getLivemode() + "'" +
            ", metadata='" + getMetadata() + "'" +
            ", nextPaymentAttempt=" + getNextPaymentAttempt() +
            ", number='" + getNumber() + "'" +
            ", onBehalfOf='" + getOnBehalfOf() + "'" +
            ", paid='" + getPaid() + "'" +
            ", paidOutOfBand='" + getPaidOutOfBand() + "'" +
            ", paymentIntent='" + getPaymentIntent() + "'" +
            ", paymentSettings='" + getPaymentSettings() + "'" +
            ", periodEnd=" + getPeriodEnd() +
            ", periodStart=" + getPeriodStart() +
            ", postPaymentCreditNotesAmount=" + getPostPaymentCreditNotesAmount() +
            ", prePaymentCreditNotesAmount=" + getPrePaymentCreditNotesAmount() +
            ", quote='" + getQuote() + "'" +
            ", receiptNumber='" + getReceiptNumber() + "'" +
            ", rendering='" + getRendering() + "'" +
            ", renderingOptions='" + getRenderingOptions() + "'" +
            ", shippingCost=" + getShippingCost() +
            ", shippingDetails='" + getShippingDetails() + "'" +
            ", startingBalance=" + getStartingBalance() +
            ", statementDescriptor='" + getStatementDescriptor() + "'" +
            ", status='" + getStatus() + "'" +
            ", statusTransitions='" + getStatusTransitions() + "'" +
            ", subscription='" + getSubscription() + "'" +
            ", subscriptionDetails='" + getSubscriptionDetails() + "'" +
            ", subtotal=" + getSubtotal() +
            ", subtotalExcludingTax=" + getSubtotalExcludingTax() +
            ", tax='" + getTax() + "'" +
            ", testClock='" + getTestClock() + "'" +
            ", total=" + getTotal() +
            ", totalDiscountAmounts='" + getTotalDiscountAmounts() + "'" +
            ", totalExcludingTax=" + getTotalExcludingTax() +
            ", totalTaxAmounts='" + getTotalTaxAmounts() + "'" +
            ", transferData='" + getTransferData() + "'" +
            ", webhooksDeliveredAt=" + getWebhooksDeliveredAt() +
            "}";
    }
}
