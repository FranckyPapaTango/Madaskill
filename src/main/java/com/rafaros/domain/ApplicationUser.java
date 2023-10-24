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
 * A ApplicationUser.
 */
@Entity
@Table(name = "application_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ApplicationUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "libelle_adresse")
    private String libelleAdresse;

    @Column(name = "ville_town")
    private String villeTown;

    @NotNull
    @Column(name = "pays_country", nullable = false)
    private String paysCountry;

    @OneToOne
    @JoinColumn(unique = true)
    private User internalUser;

    @OneToMany(mappedBy = "user")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "photos", "user" }, allowSetters = true)
    private Set<Post> posts = new HashSet<>();

    @OneToMany(mappedBy = "user")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "photos", "lineItem", "user" }, allowSetters = true)
    private Set<Product> products = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ApplicationUser id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public ApplicationUser phoneNumber(String phoneNumber) {
        this.setPhoneNumber(phoneNumber);
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return this.email;
    }

    public ApplicationUser email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLibelleAdresse() {
        return this.libelleAdresse;
    }

    public ApplicationUser libelleAdresse(String libelleAdresse) {
        this.setLibelleAdresse(libelleAdresse);
        return this;
    }

    public void setLibelleAdresse(String libelleAdresse) {
        this.libelleAdresse = libelleAdresse;
    }

    public String getVilleTown() {
        return this.villeTown;
    }

    public ApplicationUser villeTown(String villeTown) {
        this.setVilleTown(villeTown);
        return this;
    }

    public void setVilleTown(String villeTown) {
        this.villeTown = villeTown;
    }

    public String getPaysCountry() {
        return this.paysCountry;
    }

    public ApplicationUser paysCountry(String paysCountry) {
        this.setPaysCountry(paysCountry);
        return this;
    }

    public void setPaysCountry(String paysCountry) {
        this.paysCountry = paysCountry;
    }

    public User getInternalUser() {
        return this.internalUser;
    }

    public void setInternalUser(User user) {
        this.internalUser = user;
    }

    public ApplicationUser internalUser(User user) {
        this.setInternalUser(user);
        return this;
    }

    public Set<Post> getPosts() {
        return this.posts;
    }

    public void setPosts(Set<Post> posts) {
        if (this.posts != null) {
            this.posts.forEach(i -> i.setUser(null));
        }
        if (posts != null) {
            posts.forEach(i -> i.setUser(this));
        }
        this.posts = posts;
    }

    public ApplicationUser posts(Set<Post> posts) {
        this.setPosts(posts);
        return this;
    }

    public ApplicationUser addPost(Post post) {
        this.posts.add(post);
        post.setUser(this);
        return this;
    }

    public ApplicationUser removePost(Post post) {
        this.posts.remove(post);
        post.setUser(null);
        return this;
    }

    public Set<Product> getProducts() {
        return this.products;
    }

    public void setProducts(Set<Product> products) {
        if (this.products != null) {
            this.products.forEach(i -> i.setUser(null));
        }
        if (products != null) {
            products.forEach(i -> i.setUser(this));
        }
        this.products = products;
    }

    public ApplicationUser products(Set<Product> products) {
        this.setProducts(products);
        return this;
    }

    public ApplicationUser addProduct(Product product) {
        this.products.add(product);
        product.setUser(this);
        return this;
    }

    public ApplicationUser removeProduct(Product product) {
        this.products.remove(product);
        product.setUser(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ApplicationUser)) {
            return false;
        }
        return id != null && id.equals(((ApplicationUser) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ApplicationUser{" +
            "id=" + getId() +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", email='" + getEmail() + "'" +
            ", libelleAdresse='" + getLibelleAdresse() + "'" +
            ", villeTown='" + getVilleTown() + "'" +
            ", paysCountry='" + getPaysCountry() + "'" +
            "}";
    }
}
