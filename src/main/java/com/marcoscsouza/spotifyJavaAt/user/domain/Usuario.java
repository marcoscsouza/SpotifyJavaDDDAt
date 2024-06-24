package com.marcoscsouza.spotifyJavaAt.user.domain;

import com.marcoscsouza.spotifyJavaAt.music.domain.Musica;
import com.marcoscsouza.spotifyJavaAt.music.domain.Playlist;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column
    private String nome;
    @Column
    private String email;
    @Column
    private String senha;
    @Column
    private String cpf;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cartao> cartoes = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_playlist", referencedColumnName = "id")
    private List<Playlist> playlists = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_assinatura", referencedColumnName = "id")
    private List<Assinatura> assinaturas  = new ArrayList<>();

    private static final String DEFAUL_PLAYLIST_NAME = "Musicas favoritas";

    public void criar(String nome, String email, String senha, String cpf, Cartao cartao, Plano plano) throws Exception {

        if (isValid(cpf) == false){
            throw new Exception("CPF invalido");
        }

        this.setNome(nome);
        this.setEmail(email);
        this.setCpf(cpf);
        this.setSenha(this.encodeSenha(senha));

        //Transacionar no cartão
        cartao.criarTransacao(plano.getNome(), plano.getValor(), plano.getDescricao());

        // Adicionar o cartão na lista do usuario

        this.cartoes.add(cartao);

        // Criando uma assinatura
        Assinatura assinatura = new Assinatura();
        assinatura.setPlano(plano);
        assinatura.setDtAssinatura(LocalDateTime.now());
        assinatura.setAtivo(true);
        this.assinaturas.add(assinatura);

        //Criar uma playlist default
        Playlist playlist = new Playlist();
        playlist.setNome(DEFAUL_PLAYLIST_NAME);
        this.playlists.add(playlist);

    }


    private String encodeSenha(String senha) {
        return Base64.getEncoder().encodeToString(senha.getBytes());
    }

    private boolean isValid(String cpf){


        if (cpf == null || cpf.length() != 11 || !cpf.matches("\\d{11}")) {
            return false;
        }

        // Verifica se todos os dígitos são iguais, o que não é um CPF válido
        if (cpf.chars().distinct().count() == 1) {
            return false;
        }

        try {
            // Cálculo do primeiro dígito verificador
            int sum = 0;
            for (int i = 0; i < 9; i++) {
                sum += (cpf.charAt(i) - '0') * (10 - i);
            }
            int firstCheckDigit = 11 - (sum % 11);
            if (firstCheckDigit == 10 || firstCheckDigit == 11) {
                firstCheckDigit = 0;
            }
            if (firstCheckDigit != (cpf.charAt(9) - '0')) {
                return false;
            }

            // Cálculo do segundo dígito verificador
            sum = 0;
            for (int i = 0; i < 10; i++) {
                sum += (cpf.charAt(i) - '0') * (11 - i);
            }
            int secondCheckDigit = 11 - (sum % 11);
            if (secondCheckDigit == 10 || secondCheckDigit == 11) {
                secondCheckDigit = 0;
            }
            if (secondCheckDigit != (cpf.charAt(10) - '0')) {
                return false;
            }

            return true;

        } catch (NumberFormatException e) {
            return false;
        }
    }



    @Override
    public String toString() {
        return "Usuario{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", cpf='" + cpf + '\'' +
                '}';
    }
}
