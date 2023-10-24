package com.rafaros.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.domain.Persistable;

/**
 * A LineItem.
 */
@JsonIgnoreProperties(value = { "new" })
@Entity
@Table(name = "line_item")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class LineItem implements Serializable, Persistable<String> {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "object")
    private String object;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "amount_excluding_tax")
    private Long amountExcludingTax;

    @Column(name = "currency")
    private String currency;

    @Column(name = "description")
    private String description;

    @Column(name = "discount_amounts")
    private String discountAmounts;

    @Column(name = "discountable")
    private Boolean discountable;

    @Column(name = "discounts")
    private String discounts;

    @Column(name = "invoice_item")
    private String invoiceItem;

    @Column(name = "livemode")
    private Boolean livemode;

    @Column(name = "metadata")
    private String metadata;

    @Column(name = "period_end")
    private Long periodEnd;

    @Column(name = "period_start")
    private Long periodStart;

    @Column(name = "price")
    private String price;

    @Column(name = "proration")
    private Boolean proration;

    @Column(name = "proration_details")
    private String prorationDetails;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "subscription")
    private String subscription;

    @Column(name = "tax_amounts")
    private String taxAmounts;

    @Column(name = "tax_rates")
    private String taxRates;

    @Column(name = "type")
    private String type;

    @Column(name = "unit_amount_excluding_tax")
    private String unitAmountExcludingTax;

    @Transient
    private boolean isPersisted;

    @JsonIgnoreProperties(value = { "photos", "lineItem", "user" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    private Product product;

    @ManyToOne
    @JsonIgnoreProperties(value = { "lineItems", "customer" }, allowSetters = true)
    private Commande commande;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public LineItem id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObject() {
        return this.object;
    }

    public LineItem object(String object) {
        this.setObject(object);
        return this;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public Long getAmount() {
        return this.amount;
    }

    public LineItem amount(Long amount) {
        this.setAmount(amount);
        return this;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getAmountExcludingTax() {
        return this.amountExcludingTax;
    }

    public LineItem amountExcludingTax(Long amountExcludingTax) {
        this.setAmountExcludingTax(amountExcludingTax);
        return this;
    }

    public void setAmountExcludingTax(Long amountExcludingTax) {
        this.amountExcludingTax = amountExcludingTax;
    }

    public String getCurrency() {
        return this.currency;
    }

    public LineItem currency(String currency) {
        this.setCurrency(currency);
        return this;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDescription() {
        return this.description;
    }

    public LineItem description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDiscountAmounts() {
        return this.discountAmounts;
    }

    public LineItem discountAmounts(String discountAmounts) {
        this.setDiscountAmounts(discountAmounts);
        return this;
    }

    public void setDiscountAmounts(String discountAmounts) {
        this.discountAmounts = discountAmounts;
    }

    public Boolean getDiscountable() {
        return this.discountable;
    }

    public LineItem discountable(Boolean discountable) {
        this.setDiscountable(discountable);
        return this;
    }

    public void setDiscountable(Boolean discountable) {
        this.discountable = discountable;
    }

    public String getDiscounts() {
        return this.discounts;
    }

    public LineItem discounts(String discounts) {
        this.setDiscounts(discounts);
        return this;
    }

    public void setDiscounts(String discounts) {
        this.discounts = discounts;
    }

    public String getInvoiceItem() {
        return this.invoiceItem;
    }

    public LineItem invoiceItem(String invoiceItem) {
        this.setInvoiceItem(invoiceItem);
        return this;
    }

    public void setInvoiceItem(String invoiceItem) {
        this.invoiceItem = invoiceItem;
    }

    public Boolean getLivemode() {
        return this.livemode;
    }

    public LineItem livemode(Boolean livemode) {
        this.setLivemode(livemode);
        return this;
    }

    public void setLivemode(Boolean livemode) {
        this.livemode = livemode;
    }

    public String getMetadata() {
        return this.metadata;
    }

    public LineItem metadata(String metadata) {
        this.setMetadata(metadata);
        return this;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public Long getPeriodEnd() {
        return this.periodEnd;
    }

    public LineItem periodEnd(Long periodEnd) {
        this.setPeriodEnd(periodEnd);
        return this;
    }

    public void setPeriodEnd(Long periodEnd) {
        this.periodEnd = periodEnd;
    }

    public Long getPeriodStart() {
        return this.periodStart;
    }

    public LineItem periodStart(Long periodStart) {
        this.setPeriodStart(periodStart);
        return this;
    }

    public void setPeriodStart(Long periodStart) {
        this.periodStart = periodStart;
    }

    public String getPrice() {
        return this.price;
    }

    public LineItem price(String price) {
        this.setPrice(price);
        return this;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Boolean getProration() {
        return this.proration;
    }

    public LineItem proration(Boolean proration) {
        this.setProration(proration);
        return this;
    }

    public void setProration(Boolean proration) {
        this.proration = proration;
    }

    public String getProrationDetails() {
        return this.prorationDetails;
    }

    public LineItem prorationDetails(String prorationDetails) {
        this.setProrationDetails(prorationDetails);
        return this;
    }

    public void setProrationDetails(String prorationDetails) {
        this.prorationDetails = prorationDetails;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public LineItem quantity(Integer quantity) {
        this.setQuantity(quantity);
        return this;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getSubscription() {
        return this.subscription;
    }

    public LineItem subscription(String subscription) {
        this.setSubscription(subscription);
        return this;
    }

    public void setSubscription(String subscription) {
        this.subscription = subscription;
    }

    public String getTaxAmounts() {
        return this.taxAmounts;
    }

    public LineItem taxAmounts(String taxAmounts) {
        this.setTaxAmounts(taxAmounts);
        return this;
    }

    public void setTaxAmounts(String taxAmounts) {
        this.taxAmounts = taxAmounts;
    }

    public String getTaxRates() {
        return this.taxRates;
    }

    public LineItem taxRates(String taxRates) {
        this.setTaxRates(taxRates);
        return this;
    }

    public void setTaxRates(String taxRates) {
        this.taxRates = taxRates;
    }

    public String getType() {
        return this.type;
    }

    public LineItem type(String type) {
        this.setType(type);
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUnitAmountExcludingTax() {
        return this.unitAmountExcludingTax;
    }

    public LineItem unitAmountExcludingTax(String unitAmountExcludingTax) {
        this.setUnitAmountExcludingTax(unitAmountExcludingTax);
        return this;
    }

    public void setUnitAmountExcludingTax(String unitAmountExcludingTax) {
        this.unitAmountExcludingTax = unitAmountExcludingTax;
    }

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public LineItem setIsPersisted() {
        this.isPersisted = true;
        return this;
    }

    @PostLoad
    @PostPersist
    public void updateEntityState() {
        this.setIsPersisted();
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public LineItem product(Product product) {
        this.setProduct(product);
        return this;
    }

    public Commande getCommande() {
        return this.commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public LineItem commande(Commande commande) {
        this.setCommande(commande);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LineItem)) {
            return false;
        }
        return id != null && id.equals(((LineItem) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LineItem{" +
            "id=" + getId() +
            ", object='" + getObject() + "'" +
            ", amount=" + getAmount() +
            ", amountExcludingTax=" + getAmountExcludingTax() +
            ", currency='" + getCurrency() + "'" +
            ", description='" + getDescription() + "'" +
            ", discountAmounts='" + getDiscountAmounts() + "'" +
            ", discountable='" + getDiscountable() + "'" +
            ", discounts='" + getDiscounts() + "'" +
            ", invoiceItem='" + getInvoiceItem() + "'" +
            ", livemode='" + getLivemode() + "'" +
            ", metadata='" + getMetadata() + "'" +
            ", periodEnd=" + getPeriodEnd() +
            ", periodStart=" + getPeriodStart() +
            ", price='" + getPrice() + "'" +
            ", proration='" + getProration() + "'" +
            ", prorationDetails='" + getProrationDetails() + "'" +
            ", quantity=" + getQuantity() +
            ", subscription='" + getSubscription() + "'" +
            ", taxAmounts='" + getTaxAmounts() + "'" +
            ", taxRates='" + getTaxRates() + "'" +
            ", type='" + getType() + "'" +
            ", unitAmountExcludingTax='" + getUnitAmountExcludingTax() + "'" +
            "}";
    }
}
