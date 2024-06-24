package com.marcoscsouza.spotifyJavaAt.music.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Banda {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column
    private String nome;
    @Column
    private String descricao;

    @OneToMany(mappedBy = "banda", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Musica> musicas;

    @Override
    public String toString() {
        return "Banda{" +
                "nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
