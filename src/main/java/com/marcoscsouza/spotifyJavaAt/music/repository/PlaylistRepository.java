package com.marcoscsouza.spotifyJavaAt.music.repository;

import com.marcoscsouza.spotifyJavaAt.music.domain.Playlist;
import com.marcoscsouza.spotifyJavaAt.user.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, UUID> {

}
