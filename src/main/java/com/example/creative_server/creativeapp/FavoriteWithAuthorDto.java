package com.example.creative_server.creativeapp;

public class FavoriteWithAuthorDto {
    private Long id;
    private String imageUrl;
    private String author;

    public FavoriteWithAuthorDto(Long id, String imageUrl, String author) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.author = author;
    }

    // Геттеры
    public Long getId() { return id; }
    public String getImageUrl() { return imageUrl; }
    public String getAuthor() { return author; }
}