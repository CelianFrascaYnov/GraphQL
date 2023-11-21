package com.webservices.graphql.repository;

import com.webservices.graphql.model.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, String> {
    Page<Game> findByGenresAndPlatformAndStudios(String genres, String platform, String studio, Pageable pageable);
}
