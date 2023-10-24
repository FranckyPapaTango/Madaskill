package com.rafaros.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Commande.
 */
@Entity
@Table(name = "commande")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Commande implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "date_commande", nullable = false)
    private Instant dateCommande;

    @NotNull
    @Column(name = "tva", nullable = false)
    private Double tva;

    @NotNull
    @Column(name = "taxes_totales", nullable = false)
    private Double taxesTotales;

    @NotNull
    @Column(name = "montant_amount_ttc", nullable = false)
    private Double montantAmountTtc;

    @NotNull
    @Column(name = "is_payed_is_facture", nullable = false)
    private Boolean isPayedIsFacture;

    @OneToMany(mappedBy = "commande")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "product", "commande" }, allowSetters = true)
    private Set<LineItem> lineItems = new HashSet<>();

    @JsonIgnoreProperties(value = { "commande", "customer" }, allowSetters = true)
    @OneToOne(mappedBy = "commande")
    private Invoice customer;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Commande id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDateCommande() {
        return this.dateCommande;
    }

    public Commande dateCommande(Instant dateCommande) {
        this.setDateCommande(dateCommande);
        return this;
    }

    public void setDateCommande(Instant dateCommande) {
        this.dateCommande = dateCommande;
    }

    public Double getTva() {
        return this.tva;
    }

    public Commande tva(Double tva) {
        this.setTva(tva);
        return this;
    }

    public void setTva(Double tva) {
        this.tva = tva;
    }

    public Double getTaxesTotales() {
        return this.taxesTotales;
    }

    public Commande taxesTotales(Double taxesTotales) {
        this.setTaxesTotales(taxesTotales);
        return this;
    }

    public void setTaxesTotales(Double taxesTotales) {
        this.taxesTotales = taxesTotales;
    }

    public Double getMontantAmountTtc() {
        return this.montantAmountTtc;
    }

    public Commande montantAmountTtc(Double montantAmountTtc) {
        this.setMontantAmountTtc(montantAmountTtc);
        return this;
    }

    public void setMontantAmountTtc(Double montantAmountTtc) {
        this.montantAmountTtc = montantAmountTtc;
    }

    public Boolean getIsPayedIsFacture() {
        return this.isPayedIsFacture;
    }

    public Commande isPayedIsFacture(Boolean isPayedIsFacture) {
        this.setIsPayedIsFacture(isPayedIsFacture);
        return this;
    }

    public void setIsPayedIsFacture(Boolean isPayedIsFacture) {
        this.isPayedIsFacture = isPayedIsFacture;
    }

    public Set<LineItem> getLineItems() {
        return this.lineItems;
    }

    public void setLineItems(Set<LineItem> lineItems) {
        if (this.lineItems != null) {
            this.lineItems.forEach(i -> i.setCommande(null));
        }
        if (lineItems != null) {
            lineItems.forEach(i -> i.setCommande(this));
        }
        this.lineItems = lineItems;
    }

    public Commande lineItems(Set<LineItem> lineItems) {
        this.setLineItems(lineItems);
        return this;
    }

    public Commande addLineItem(LineItem lineItem) {
        this.lineItems.add(lineItem);
        lineItem.setCommande(this);
        return this;
    }

    public Commande removeLineItem(LineItem lineItem) {
        this.lineItems.remove(lineItem);
        lineItem.setCommande(null);
        return this;
    }

    public Invoice getCustomer() {
        return this.customer;
    }

    public void setCustomer(Invoice invoice) {
        if (this.customer != null) {
            this.customer.setCommande(null);
        }
        if (invoice != null) {
            invoice.setCommande(this);
        }
        this.customer = invoice;
    }

    public Commande customer(Invoice invoice) {
        this.setCustomer(invoice);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Commande)) {
            return false;
        }
        return id != null && id.equals(((Commande) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Commande{" +
            "id=" + getId() +
            ", dateCommande='" + getDateCommande() + "'" +
            ", tva=" + getTva() +
            ", taxesTotales=" + getTaxesTotales() +
            ", montantAmountTtc=" + getMontantAmountTtc() +
            ", isPayedIsFacture='" + getIsPayedIsFacture() + "'" +
            "}";
    }
}
