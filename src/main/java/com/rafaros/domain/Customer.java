package com.rafaros.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.domain.Persistable;

/**
 * A Customer.
 */
@JsonIgnoreProperties(value = { "new" })
@Entity
@Table(name = "customer")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Customer implements Serializable, Persistable<String> {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "object")
    private String object;

    @Column(name = "address")
    private String address;

    @Column(name = "balance")
    private Integer balance;

    @Column(name = "created")
    private Long created;

    @Column(name = "currency")
    private String currency;

    @Column(name = "default_source")
    private String defaultSource;

    @Column(name = "delinquent")
    private Boolean delinquent;

    @Column(name = "description")
    private String description;

    @Column(name = "discount")
    private Integer discount;

    @Column(name = "email")
    private String email;

    @Column(name = "invoice_prefix")
    private String invoicePrefix;

    @Column(name = "livemode")
    private Boolean livemode;

    @Column(name = "name")
    private String name;

    @Column(name = "next_invoice_sequence")
    private Integer nextInvoiceSequence;

    @Column(name = "phone")
    private String phone;

    @Column(name = "shipping")
    private String shipping;

    @Column(name = "tax_exempt")
    private String taxExempt;

    @Column(name = "test_clock")
    private ZonedDateTime testClock;

    @Transient
    private boolean isPersisted;

    @OneToOne
    @JoinColumn(unique = true)
    private InvoiceSettings invoiceSettings;

    @OneToOne
    @JoinColumn(unique = true)
    private Metadata metadata;

    @OneToMany(mappedBy = "customer")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "commande", "customer" }, allowSetters = true)
    private Set<Invoice> invoices = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "rel_customer__preferred_locales",
        joinColumns = @JoinColumn(name = "customer_id"),
        inverseJoinColumns = @JoinColumn(name = "preferred_locales_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "customers" }, allowSetters = true)
    private Set<PreferredLocales> preferredLocales = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Customer id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObject() {
        return this.object;
    }

    public Customer object(String object) {
        this.setObject(object);
        return this;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getAddress() {
        return this.address;
    }

    public Customer address(String address) {
        this.setAddress(address);
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getBalance() {
        return this.balance;
    }

    public Customer balance(Integer balance) {
        this.setBalance(balance);
        return this;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Long getCreated() {
        return this.created;
    }

    public Customer created(Long created) {
        this.setCreated(created);
        return this;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public String getCurrency() {
        return this.currency;
    }

    public Customer currency(String currency) {
        this.setCurrency(currency);
        return this;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDefaultSource() {
        return this.defaultSource;
    }

    public Customer defaultSource(String defaultSource) {
        this.setDefaultSource(defaultSource);
        return this;
    }

    public void setDefaultSource(String defaultSource) {
        this.defaultSource = defaultSource;
    }

    public Boolean getDelinquent() {
        return this.delinquent;
    }

    public Customer delinquent(Boolean delinquent) {
        this.setDelinquent(delinquent);
        return this;
    }

    public void setDelinquent(Boolean delinquent) {
        this.delinquent = delinquent;
    }

    public String getDescription() {
        return this.description;
    }

    public Customer description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDiscount() {
        return this.discount;
    }

    public Customer discount(Integer discount) {
        this.setDiscount(discount);
        return this;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public String getEmail() {
        return this.email;
    }

    public Customer email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInvoicePrefix() {
        return this.invoicePrefix;
    }

    public Customer invoicePrefix(String invoicePrefix) {
        this.setInvoicePrefix(invoicePrefix);
        return this;
    }

    public void setInvoicePrefix(String invoicePrefix) {
        this.invoicePrefix = invoicePrefix;
    }

    public Boolean getLivemode() {
        return this.livemode;
    }

    public Customer livemode(Boolean livemode) {
        this.setLivemode(livemode);
        return this;
    }

    public void setLivemode(Boolean livemode) {
        this.livemode = livemode;
    }

    public String getName() {
        return this.name;
    }

    public Customer name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNextInvoiceSequence() {
        return this.nextInvoiceSequence;
    }

    public Customer nextInvoiceSequence(Integer nextInvoiceSequence) {
        this.setNextInvoiceSequence(nextInvoiceSequence);
        return this;
    }

    public void setNextInvoiceSequence(Integer nextInvoiceSequence) {
        this.nextInvoiceSequence = nextInvoiceSequence;
    }

    public String getPhone() {
        return this.phone;
    }

    public Customer phone(String phone) {
        this.setPhone(phone);
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getShipping() {
        return this.shipping;
    }

    public Customer shipping(String shipping) {
        this.setShipping(shipping);
        return this;
    }

    public void setShipping(String shipping) {
        this.shipping = shipping;
    }

    public String getTaxExempt() {
        return this.taxExempt;
    }

    public Customer taxExempt(String taxExempt) {
        this.setTaxExempt(taxExempt);
        return this;
    }

    public void setTaxExempt(String taxExempt) {
        this.taxExempt = taxExempt;
    }

    public ZonedDateTime getTestClock() {
        return this.testClock;
    }

    public Customer testClock(ZonedDateTime testClock) {
        this.setTestClock(testClock);
        return this;
    }

    public void setTestClock(ZonedDateTime testClock) {
        this.testClock = testClock;
    }

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public Customer setIsPersisted() {
        this.isPersisted = true;
        return this;
    }

    @PostLoad
    @PostPersist
    public void updateEntityState() {
        this.setIsPersisted();
    }

    public InvoiceSettings getInvoiceSettings() {
        return this.invoiceSettings;
    }

    public void setInvoiceSettings(InvoiceSettings invoiceSettings) {
        this.invoiceSettings = invoiceSettings;
    }

    public Customer invoiceSettings(InvoiceSettings invoiceSettings) {
        this.setInvoiceSettings(invoiceSettings);
        return this;
    }

    public Metadata getMetadata() {
        return this.metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public Customer metadata(Metadata metadata) {
        this.setMetadata(metadata);
        return this;
    }

    public Set<Invoice> getInvoices() {
        return this.invoices;
    }

    public void setInvoices(Set<Invoice> invoices) {
        if (this.invoices != null) {
            this.invoices.forEach(i -> i.setCustomer(null));
        }
        if (invoices != null) {
            invoices.forEach(i -> i.setCustomer(this));
        }
        this.invoices = invoices;
    }

    public Customer invoices(Set<Invoice> invoices) {
        this.setInvoices(invoices);
        return this;
    }

    public Customer addInvoice(Invoice invoice) {
        this.invoices.add(invoice);
        invoice.setCustomer(this);
        return this;
    }

    public Customer removeInvoice(Invoice invoice) {
        this.invoices.remove(invoice);
        invoice.setCustomer(null);
        return this;
    }

    public Set<PreferredLocales> getPreferredLocales() {
        return this.preferredLocales;
    }

    public void setPreferredLocales(Set<PreferredLocales> preferredLocales) {
        this.preferredLocales = preferredLocales;
    }

    public Customer preferredLocales(Set<PreferredLocales> preferredLocales) {
        this.setPreferredLocales(preferredLocales);
        return this;
    }

    public Customer addPreferredLocales(PreferredLocales preferredLocales) {
        this.preferredLocales.add(preferredLocales);
        preferredLocales.getCustomers().add(this);
        return this;
    }

    public Customer removePreferredLocales(PreferredLocales preferredLocales) {
        this.preferredLocales.remove(preferredLocales);
        preferredLocales.getCustomers().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Customer)) {
            return false;
        }
        return id != null && id.equals(((Customer) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Customer{" +
            "id=" + getId() +
            ", object='" + getObject() + "'" +
            ", address='" + getAddress() + "'" +
            ", balance=" + getBalance() +
            ", created=" + getCreated() +
            ", currency='" + getCurrency() + "'" +
            ", defaultSource='" + getDefaultSource() + "'" +
            ", delinquent='" + getDelinquent() + "'" +
            ", description='" + getDescription() + "'" +
            ", discount=" + getDiscount() +
            ", email='" + getEmail() + "'" +
            ", invoicePrefix='" + getInvoicePrefix() + "'" +
            ", livemode='" + getLivemode() + "'" +
            ", name='" + getName() + "'" +
            ", nextInvoiceSequence=" + getNextInvoiceSequence() +
            ", phone='" + getPhone() + "'" +
            ", shipping='" + getShipping() + "'" +
            ", taxExempt='" + getTaxExempt() + "'" +
            ", testClock='" + getTestClock() + "'" +
            "}";
    }
}
