package com.marcoscsouza.spotifyJavaAt.user.repository;

import com.marcoscsouza.spotifyJavaAt.user.domain.Plano;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PlanoRepository extends JpaRepository<Plano, UUID> {
}
