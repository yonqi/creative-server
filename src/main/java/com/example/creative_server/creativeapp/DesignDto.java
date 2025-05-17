package com.example.creative_server.creativeapp;

import java.time.LocalDate;


import java.time.LocalDate;

public class DesignDto {
    private String imageUrl;
    private String fact;
    private LocalDate date;
    private String author;

    public DesignDto(String imageUrl, String fact, LocalDate date, String author) {
        this.imageUrl = imageUrl;
        this.fact = fact;
        this.date = date;
        this.author = author;
    }

    // геттеры и сеттеры (если нужно)
    public String getImageUrl() { return imageUrl; }
    public String getFact() { return fact; }
    public LocalDate getDate() { return date; }
    public String getAuthor() { return author; }
}
