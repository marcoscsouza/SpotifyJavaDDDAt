package com.marcoscsouza.spotifyJavaAt.music.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column
    private String nome;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Musica> musicas = new ArrayList<>();


    public void criar(String nome){
        this.setNome(nome);
        this.setMusicas(new ArrayList<>());
        this.setId(UUID.randomUUID());


    }

    public void adicionarMusica(Musica musica) throws Exception {
        this.musicas.add(musica);
    }

    public void removerMusica(Musica musica) throws Exception {
       this.musicas.remove(musica);
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "nome='" + nome + '\'' +
                '}';
    }
}
