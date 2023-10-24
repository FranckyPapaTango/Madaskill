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
 * A Product.
 */
@Entity
@Table(name = "product")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "object")
    private String object;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "created")
    private Long created;

    @Column(name = "default_price")
    private Double defaultPrice;

    @Column(name = "description")
    private String description;

    @Column(name = "livemode")
    private Boolean livemode;

    @Column(name = "name")
    private String name;

    @Column(name = "shippable")
    private Boolean shippable;

    @Column(name = "statement_descriptor")
    private String statementDescriptor;

    @Column(name = "tax_code")
    private String taxCode;

    @Column(name = "unit_label")
    private String unitLabel;

    @Column(name = "updated")
    private Long updated;

    @Column(name = "url")
    private String url;

    @Column(name = "sku")
    private String sku;

    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "link_to_generic_photo_file")
    private String linkToGenericPhotoFile;

    @Column(name = "available_sizes")
    private String availableSizes;

    @Column(name = "currency_format")
    private String currencyFormat;

    @Column(name = "is_free_shipping")
    private Boolean isFreeShipping;

    @NotNull
    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "style")
    private String style;

    @Column(name = "installments")
    private Integer installments;

    @OneToMany(mappedBy = "product")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "post", "product" }, allowSetters = true)
    private Set<Photo> photos = new HashSet<>();

    @JsonIgnoreProperties(value = { "product", "commande" }, allowSetters = true)
    @OneToOne(mappedBy = "product")
    private LineItem lineItem;

    @ManyToOne
    @JsonIgnoreProperties(value = { "internalUser", "posts", "products" }, allowSetters = true)
    private ApplicationUser user;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Product id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getObject() {
        return this.object;
    }

    public Product object(String object) {
        this.setObject(object);
        return this;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public Boolean getActive() {
        return this.active;
    }

    public Product active(Boolean active) {
        this.setActive(active);
        return this;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Long getCreated() {
        return this.created;
    }

    public Product created(Long created) {
        this.setCreated(created);
        return this;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public Double getDefaultPrice() {
        return this.defaultPrice;
    }

    public Product defaultPrice(Double defaultPrice) {
        this.setDefaultPrice(defaultPrice);
        return this;
    }

    public void setDefaultPrice(Double defaultPrice) {
        this.defaultPrice = defaultPrice;
    }

    public String getDescription() {
        return this.description;
    }

    public Product description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getLivemode() {
        return this.livemode;
    }

    public Product livemode(Boolean livemode) {
        this.setLivemode(livemode);
        return this;
    }

    public void setLivemode(Boolean livemode) {
        this.livemode = livemode;
    }

    public String getName() {
        return this.name;
    }

    public Product name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getShippable() {
        return this.shippable;
    }

    public Product shippable(Boolean shippable) {
        this.setShippable(shippable);
        return this;
    }

    public void setShippable(Boolean shippable) {
        this.shippable = shippable;
    }

    public String getStatementDescriptor() {
        return this.statementDescriptor;
    }

    public Product statementDescriptor(String statementDescriptor) {
        this.setStatementDescriptor(statementDescriptor);
        return this;
    }

    public void setStatementDescriptor(String statementDescriptor) {
        this.statementDescriptor = statementDescriptor;
    }

    public String getTaxCode() {
        return this.taxCode;
    }

    public Product taxCode(String taxCode) {
        this.setTaxCode(taxCode);
        return this;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public String getUnitLabel() {
        return this.unitLabel;
    }

    public Product unitLabel(String unitLabel) {
        this.setUnitLabel(unitLabel);
        return this;
    }

    public void setUnitLabel(String unitLabel) {
        this.unitLabel = unitLabel;
    }

    public Long getUpdated() {
        return this.updated;
    }

    public Product updated(Long updated) {
        this.setUpdated(updated);
        return this;
    }

    public void setUpdated(Long updated) {
        this.updated = updated;
    }

    public String getUrl() {
        return this.url;
    }

    public Product url(String url) {
        this.setUrl(url);
        return this;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSku() {
        return this.sku;
    }

    public Product sku(String sku) {
        this.setSku(sku);
        return this;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getTitle() {
        return this.title;
    }

    public Product title(String title) {
        this.setTitle(title);
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLinkToGenericPhotoFile() {
        return this.linkToGenericPhotoFile;
    }

    public Product linkToGenericPhotoFile(String linkToGenericPhotoFile) {
        this.setLinkToGenericPhotoFile(linkToGenericPhotoFile);
        return this;
    }

    public void setLinkToGenericPhotoFile(String linkToGenericPhotoFile) {
        this.linkToGenericPhotoFile = linkToGenericPhotoFile;
    }

    public String getAvailableSizes() {
        return this.availableSizes;
    }

    public Product availableSizes(String availableSizes) {
        this.setAvailableSizes(availableSizes);
        return this;
    }

    public void setAvailableSizes(String availableSizes) {
        this.availableSizes = availableSizes;
    }

    public String getCurrencyFormat() {
        return this.currencyFormat;
    }

    public Product currencyFormat(String currencyFormat) {
        this.setCurrencyFormat(currencyFormat);
        return this;
    }

    public void setCurrencyFormat(String currencyFormat) {
        this.currencyFormat = currencyFormat;
    }

    public Boolean getIsFreeShipping() {
        return this.isFreeShipping;
    }

    public Product isFreeShipping(Boolean isFreeShipping) {
        this.setIsFreeShipping(isFreeShipping);
        return this;
    }

    public void setIsFreeShipping(Boolean isFreeShipping) {
        this.isFreeShipping = isFreeShipping;
    }

    public Double getPrice() {
        return this.price;
    }

    public Product price(Double price) {
        this.setPrice(price);
        return this;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getStyle() {
        return this.style;
    }

    public Product style(String style) {
        this.setStyle(style);
        return this;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public Integer getInstallments() {
        return this.installments;
    }

    public Product installments(Integer installments) {
        this.setInstallments(installments);
        return this;
    }

    public void setInstallments(Integer installments) {
        this.installments = installments;
    }

    public Set<Photo> getPhotos() {
        return this.photos;
    }

    public void setPhotos(Set<Photo> photos) {
        if (this.photos != null) {
            this.photos.forEach(i -> i.setProduct(null));
        }
        if (photos != null) {
            photos.forEach(i -> i.setProduct(this));
        }
        this.photos = photos;
    }

    public Product photos(Set<Photo> photos) {
        this.setPhotos(photos);
        return this;
    }

    public Product addPhotos(Photo photo) {
        this.photos.add(photo);
        photo.setProduct(this);
        return this;
    }

    public Product removePhotos(Photo photo) {
        this.photos.remove(photo);
        photo.setProduct(null);
        return this;
    }

    public LineItem getLineItem() {
        return this.lineItem;
    }

    public void setLineItem(LineItem lineItem) {
        if (this.lineItem != null) {
            this.lineItem.setProduct(null);
        }
        if (lineItem != null) {
            lineItem.setProduct(this);
        }
        this.lineItem = lineItem;
    }

    public Product lineItem(LineItem lineItem) {
        this.setLineItem(lineItem);
        return this;
    }

    public ApplicationUser getUser() {
        return this.user;
    }

    public void setUser(ApplicationUser applicationUser) {
        this.user = applicationUser;
    }

    public Product user(ApplicationUser applicationUser) {
        this.setUser(applicationUser);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Product)) {
            return false;
        }
        return id != null && id.equals(((Product) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Product{" +
            "id=" + getId() +
            ", object='" + getObject() + "'" +
            ", active='" + getActive() + "'" +
            ", created=" + getCreated() +
            ", defaultPrice=" + getDefaultPrice() +
            ", description='" + getDescription() + "'" +
            ", livemode='" + getLivemode() + "'" +
            ", name='" + getName() + "'" +
            ", shippable='" + getShippable() + "'" +
            ", statementDescriptor='" + getStatementDescriptor() + "'" +
            ", taxCode='" + getTaxCode() + "'" +
            ", unitLabel='" + getUnitLabel() + "'" +
            ", updated=" + getUpdated() +
            ", url='" + getUrl() + "'" +
            ", sku='" + getSku() + "'" +
            ", title='" + getTitle() + "'" +
            ", linkToGenericPhotoFile='" + getLinkToGenericPhotoFile() + "'" +
            ", availableSizes='" + getAvailableSizes() + "'" +
            ", currencyFormat='" + getCurrencyFormat() + "'" +
            ", isFreeShipping='" + getIsFreeShipping() + "'" +
            ", price=" + getPrice() +
            ", style='" + getStyle() + "'" +
            ", installments=" + getInstallments() +
            "}";
    }
}
