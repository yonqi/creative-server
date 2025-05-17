package com.example.creative_server.controller;

import com.example.creative_server.creativeapp.Design;
import com.example.creative_server.creativeapp.Favorite;
import com.example.creative_server.creativeapp.FavoriteWithAuthorDto;
import com.example.creative_server.repository.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


import com.example.creative_server.creativeapp.Favorite;
import com.example.creative_server.repository.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/favorites")
@CrossOrigin(origins = "*")
public class FavoriteController {

    @Autowired
    private FavoriteRepository favoriteRepository;

    @GetMapping("/list-with-author")
    public ResponseEntity<List<FavoriteWithAuthorDto>> getFavoritesWithAuthor(@RequestParam String firebaseUid) {
        List<Object[]> results = favoriteRepository.findFavoritesWithDesign(firebaseUid);

        List<FavoriteWithAuthorDto> dtos = results.stream()
                .map(result -> {
                    Favorite favorite = (Favorite) result[0];
                    Design design = (Design) result[1];

                    return new FavoriteWithAuthorDto(
                            favorite.getId(),
                            favorite.getImageUrl(),
                            design != null ? design.getAuthor() : "Неизвестный автор"
                    );
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

    // Добавление в избранное (теперь с @RequestBody)
    @PostMapping("/add")
    public ResponseEntity<String> addFavorite(@RequestBody FavoriteRequest request) {
        System.out.println("firebaseUid: " + request.getFirebaseUid() + ", imageUrl: " + request.getImageUrl());

        Optional<Favorite> existingFavorite = favoriteRepository.findByFirebaseUidAndImageUrl(
                request.getFirebaseUid(),
                request.getImageUrl()
        );

        if (existingFavorite.isPresent()) {
            return ResponseEntity.badRequest().body("Изображение уже добавлено в избранное");
        }

        Favorite favorite = new Favorite(request.getFirebaseUid(), request.getImageUrl());
        favoriteRepository.save(favorite);

        return ResponseEntity.ok("Изображение добавлено в избранное");
    }

    @GetMapping("/check")
    public ResponseEntity<Boolean> checkFavorite(
            @RequestParam String firebaseUid,
            @RequestParam String imageUrl) {

        boolean isFavorite = favoriteRepository.existsByFirebaseUidAndImageUrl(firebaseUid, imageUrl);
        return ResponseEntity.ok(isFavorite);
    }



    // Удаление из избранного (теперь с @RequestBody)
    @DeleteMapping("/remove")
    public ResponseEntity<String> removeFavorite(@RequestBody FavoriteRequest request) {
        Optional<Favorite> existingFavorite = favoriteRepository.findByFirebaseUidAndImageUrl(
                request.getFirebaseUid(),
                request.getImageUrl()
        );

        if (existingFavorite.isEmpty()) {
            return ResponseEntity.badRequest().body("Изображение не найдено в избранном");
        }

        favoriteRepository.delete(existingFavorite.get());
        return ResponseEntity.ok("Изображение удалено из избранного");
    }

    // Получение избранного (остаётся с @RequestParam)
    @GetMapping("/list")
    public ResponseEntity<?> getFavorites(@RequestParam String firebaseUid) {
        List<Favorite> favorites = favoriteRepository.findByFirebaseUid(firebaseUid);
        return ResponseEntity.ok(favorites);
    }
}