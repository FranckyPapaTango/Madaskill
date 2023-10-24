package com.rafaros.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A PreferredLocales.
 */
@Entity
@Table(name = "preferred_locales")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PreferredLocales implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "preferred_locales")
    private String preferredLocales;

    @ManyToMany(mappedBy = "preferredLocales")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "invoiceSettings", "metadata", "invoices", "preferredLocales" }, allowSetters = true)
    private Set<Customer> customers = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public PreferredLocales id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPreferredLocales() {
        return this.preferredLocales;
    }

    public PreferredLocales preferredLocales(String preferredLocales) {
        this.setPreferredLocales(preferredLocales);
        return this;
    }

    public void setPreferredLocales(String preferredLocales) {
        this.preferredLocales = preferredLocales;
    }

    public Set<Customer> getCustomers() {
        return this.customers;
    }

    public void setCustomers(Set<Customer> customers) {
        if (this.customers != null) {
            this.customers.forEach(i -> i.removePreferredLocales(this));
        }
        if (customers != null) {
            customers.forEach(i -> i.addPreferredLocales(this));
        }
        this.customers = customers;
    }

    public PreferredLocales customers(Set<Customer> customers) {
        this.setCustomers(customers);
        return this;
    }

    public PreferredLocales addCustomer(Customer customer) {
        this.customers.add(customer);
        customer.getPreferredLocales().add(this);
        return this;
    }

    public PreferredLocales removeCustomer(Customer customer) {
        this.customers.remove(customer);
        customer.getPreferredLocales().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PreferredLocales)) {
            return false;
        }
        return id != null && id.equals(((PreferredLocales) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PreferredLocales{" +
            "id=" + getId() +
            ", preferredLocales='" + getPreferredLocales() + "'" +
            "}";
    }
}
