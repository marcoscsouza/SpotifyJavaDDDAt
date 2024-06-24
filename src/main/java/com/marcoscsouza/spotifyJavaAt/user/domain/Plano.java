package com.marcoscsouza.spotifyJavaAt.user.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class Plano {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column
    private String nome;
    @Column
    private boolean ativo;
    @Column
    private String descricao;
    @Column
    private double valor;



}
