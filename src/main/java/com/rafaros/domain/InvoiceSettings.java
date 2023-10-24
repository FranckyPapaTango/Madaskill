package com.rafaros.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A InvoiceSettings.
 */
@Entity
@Table(name = "invoice_settings")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class InvoiceSettings implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "custom_fields")
    private String customFields;

    @Column(name = "default_payment_method")
    private String defaultPaymentMethod;

    @Column(name = "footer")
    private String footer;

    @Column(name = "rendering_options")
    private String renderingOptions;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public InvoiceSettings id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomFields() {
        return this.customFields;
    }

    public InvoiceSettings customFields(String customFields) {
        this.setCustomFields(customFields);
        return this;
    }

    public void setCustomFields(String customFields) {
        this.customFields = customFields;
    }

    public String getDefaultPaymentMethod() {
        return this.defaultPaymentMethod;
    }

    public InvoiceSettings defaultPaymentMethod(String defaultPaymentMethod) {
        this.setDefaultPaymentMethod(defaultPaymentMethod);
        return this;
    }

    public void setDefaultPaymentMethod(String defaultPaymentMethod) {
        this.defaultPaymentMethod = defaultPaymentMethod;
    }

    public String getFooter() {
        return this.footer;
    }

    public InvoiceSettings footer(String footer) {
        this.setFooter(footer);
        return this;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

    public String getRenderingOptions() {
        return this.renderingOptions;
    }

    public InvoiceSettings renderingOptions(String renderingOptions) {
        this.setRenderingOptions(renderingOptions);
        return this;
    }

    public void setRenderingOptions(String renderingOptions) {
        this.renderingOptions = renderingOptions;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InvoiceSettings)) {
            return false;
        }
        return id != null && id.equals(((InvoiceSettings) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InvoiceSettings{" +
            "id=" + getId() +
            ", customFields='" + getCustomFields() + "'" +
            ", defaultPaymentMethod='" + getDefaultPaymentMethod() + "'" +
            ", footer='" + getFooter() + "'" +
            ", renderingOptions='" + getRenderingOptions() + "'" +
            "}";
    }
}
