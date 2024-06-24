package com.marcoscsouza.spotifyJavaAt.music.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistDto {

    private UUID id;

    @NotEmpty(message = "Campo nome é obrigatorio")
    private String nome;
    @NotEmpty(message = "Campo usuarioId é obrigatorio")
    private UUID usuarioId;
}
