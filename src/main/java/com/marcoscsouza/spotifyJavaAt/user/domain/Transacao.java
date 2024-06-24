package com.marcoscsouza.spotifyJavaAt.user.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID condigoAutorizacao;

    @Column
    private double valor;
    @Column
    private LocalDateTime dtTransacao;
    @Column
    private String merchant;
    @Column
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "cartao_id")
    @JsonBackReference
    private Cartao cartao;



}
