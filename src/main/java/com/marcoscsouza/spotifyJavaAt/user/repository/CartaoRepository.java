package com.marcoscsouza.spotifyJavaAt.user.repository;

import com.marcoscsouza.spotifyJavaAt.user.domain.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, UUID> {
}
