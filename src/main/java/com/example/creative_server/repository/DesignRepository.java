package com.example.creative_server.repository;

import com.example.creative_server.creativeapp.Design;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface DesignRepository extends JpaRepository<Design, Long> {
    Optional<Design> findByDate(LocalDate date);

    Optional<Design> findByImageUrl(String imageUrl);

}
