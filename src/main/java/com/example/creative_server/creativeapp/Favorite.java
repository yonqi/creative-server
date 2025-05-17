package com.example.creative_server.creativeapp;

import jakarta.persistence.*;

@Entity
@Table(name = "favorites")
public class Favorite {

    public Favorite() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firebase_uid")
    private String firebaseUid;

    @Column(name = "image_url")
    private String imageUrl;

    // Конструктор без id, так как оно генерируется автоматически
    public Favorite(String firebaseUid, String imageUrl) {
        this.firebaseUid = firebaseUid;
        this.imageUrl = imageUrl;
    }



    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirebaseUid() {
        return firebaseUid;
    }

    public void setFirebaseUid(String firebaseUid) {
        this.firebaseUid = firebaseUid;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
