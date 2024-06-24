package com.marcoscsouza.spotifyJavaAt.music.repository;

import com.marcoscsouza.spotifyJavaAt.music.domain.Banda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BandaRepository extends JpaRepository<Banda, UUID> {
}
