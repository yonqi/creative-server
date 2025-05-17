package com.example.creative_server.controller;

public class FavoriteRequest {
    private String firebaseUid;
    private String imageUrl;

    // Геттеры и сеттеры
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