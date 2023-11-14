package com.rafaros.service.dto;

public class CartItem {

    private Long id; // L'identifiant du produit
    private String linkToGenericPhotoFile; // Le lien vers le fichier photo générique
    private String title; // Le titre du produit
    private double price; // Le prix du produit
    private int quantity; // La quantité du produit

    // Constructeurs, getters et setters

    public CartItem() {
        // Constructeur par défaut
    }

    public CartItem(Long id, String linkToGenericPhotoFile, String title, double price, int quantity) {
        this.id = id;
        this.linkToGenericPhotoFile = linkToGenericPhotoFile;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters et setters pour chaque propriété

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLinkToGenericPhotoFile() {
        return linkToGenericPhotoFile;
    }

    public void setLinkToGenericPhotoFile(String linkToGenericPhotoFile) {
        this.linkToGenericPhotoFile = linkToGenericPhotoFile;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
