package com.marcoscsouza.spotifyJavaAt.music.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class Musica {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column
    private String nome;
    @Column
    private int duracao;

    @ManyToOne
    @JoinColumn(name = "bannda_id")
    @JsonBackReference
    private Banda banda;

    public String formatarDuracao(int tempoMusica){
        int minuto = tempoMusica / 60;
        int segundo = tempoMusica % 60;

        return minuto + ":" + segundo ;
    }

    @Override
    public String toString() {
        return "Musica{" +
                "nome='" + nome + '\'' +
                ", duracao=" + formatarDuracao(duracao) +
                ", banda=" + banda.getNome() +
                '}';
    }
}
