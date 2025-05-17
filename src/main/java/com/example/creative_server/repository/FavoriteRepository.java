package com.example.creative_server.repository;

import com.example.creative_server.creativeapp.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    // Метод для поиска по firebaseUid и imageUrl (для проверки, не добавлено ли уже в избранное)
    Optional<Favorite> findByFirebaseUidAndImageUrl(String firebaseUid, String imageUrl);

    // Метод для поиска всех избранных изображений по firebaseUid
    List<Favorite> findByFirebaseUid(String firebaseUid);

    boolean existsByFirebaseUidAndImageUrl(String firebaseUid, String imageUrl);

    @Query("SELECT f, d FROM Favorite f LEFT JOIN Design d ON f.imageUrl = d.imageUrl WHERE f.firebaseUid = :firebaseUid")
    List<Object[]> findFavoritesWithDesign(@Param("firebaseUid") String firebaseUid);
}


