package com.marcoscsouza.spotifyJavaAt.user.controller;

import com.marcoscsouza.spotifyJavaAt.user.service.UsuarioService;
import com.marcoscsouza.spotifyJavaAt.user.dto.UsuarioDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioDto> criar(@RequestBody @Valid UsuarioDto usuarioDto) throws Exception {
        UsuarioDto response = this.usuarioService.criar(usuarioDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
