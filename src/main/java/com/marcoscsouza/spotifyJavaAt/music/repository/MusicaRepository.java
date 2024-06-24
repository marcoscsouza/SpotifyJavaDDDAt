package com.marcoscsouza.spotifyJavaAt.music.repository;

import com.marcoscsouza.spotifyJavaAt.music.domain.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MusicaRepository extends JpaRepository<Musica, UUID> {
}
