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
 * A Post.
 */
@Entity
@Table(name = "post")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "body")
    private String body;

    @Column(name = "categorie")
    private String categorie;

    @Column(name = "resumee")
    private String resumee;

    @Column(name = "auteur")
    private String auteur;

    @NotNull
    @Column(name = "date_persistence", nullable = false)
    private Instant datePersistence;

    @OneToMany(mappedBy = "post")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "post", "product" }, allowSetters = true)
    private Set<Photo> photos = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = { "internalUser", "posts", "products" }, allowSetters = true)
    private ApplicationUser user;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Post id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public Post title(String title) {
        this.setTitle(title);
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return this.body;
    }

    public Post body(String body) {
        this.setBody(body);
        return this;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCategorie() {
        return this.categorie;
    }

    public Post categorie(String categorie) {
        this.setCategorie(categorie);
        return this;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getResumee() {
        return this.resumee;
    }

    public Post resumee(String resumee) {
        this.setResumee(resumee);
        return this;
    }

    public void setResumee(String resumee) {
        this.resumee = resumee;
    }

    public String getAuteur() {
        return this.auteur;
    }

    public Post auteur(String auteur) {
        this.setAuteur(auteur);
        return this;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public Instant getDatePersistence() {
        return this.datePersistence;
    }

    public Post datePersistence(Instant datePersistence) {
        this.setDatePersistence(datePersistence);
        return this;
    }

    public void setDatePersistence(Instant datePersistence) {
        this.datePersistence = datePersistence;
    }

    public Set<Photo> getPhotos() {
        return this.photos;
    }

    public void setPhotos(Set<Photo> photos) {
        if (this.photos != null) {
            this.photos.forEach(i -> i.setPost(null));
        }
        if (photos != null) {
            photos.forEach(i -> i.setPost(this));
        }
        this.photos = photos;
    }

    public Post photos(Set<Photo> photos) {
        this.setPhotos(photos);
        return this;
    }

    public Post addPhotos(Photo photo) {
        this.photos.add(photo);
        photo.setPost(this);
        return this;
    }

    public Post removePhotos(Photo photo) {
        this.photos.remove(photo);
        photo.setPost(null);
        return this;
    }

    public ApplicationUser getUser() {
        return this.user;
    }

    public void setUser(ApplicationUser applicationUser) {
        this.user = applicationUser;
    }

    public Post user(ApplicationUser applicationUser) {
        this.setUser(applicationUser);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Post)) {
            return false;
        }
        return id != null && id.equals(((Post) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Post{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", body='" + getBody() + "'" +
            ", categorie='" + getCategorie() + "'" +
            ", resumee='" + getResumee() + "'" +
            ", auteur='" + getAuteur() + "'" +
            ", datePersistence='" + getDatePersistence() + "'" +
            "}";
    }
}
