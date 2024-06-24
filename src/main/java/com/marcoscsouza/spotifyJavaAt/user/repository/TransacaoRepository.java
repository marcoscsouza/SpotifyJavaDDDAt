package com.marcoscsouza.spotifyJavaAt.user.repository;

import com.marcoscsouza.spotifyJavaAt.user.domain.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, UUID> {
}
