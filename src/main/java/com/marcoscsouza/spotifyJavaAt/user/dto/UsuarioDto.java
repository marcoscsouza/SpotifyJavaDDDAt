package com.marcoscsouza.spotifyJavaAt.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class UsuarioDto {

    private UUID id;

    @NotEmpty(message = "Campo nome é obrigatorio")
    private String nome;

    @Email(message = "email invalido")
    @NotEmpty(message = "Campo email é obrigatorio")
    private String email;

    @NotEmpty(message = "Campo senha é obrigatorio")
    private String senha;

    @NotEmpty(message = "Campo cpf é obrigatorio")
    private String cpf;

    @NotEmpty(message = "Campo numero cartão é obrigatorio")
    private String numeroCartao;

    @NotNull(message = "Campo limite é obrigatorio")
    private Double limite;

    @NotNull(message = "Campo cartão ativo é obrigatorio")
    private Boolean cartaoAtivo;

    @NotNull(message = "Campo plano  é obrigatorio")
    private UUID idPlano;
}
