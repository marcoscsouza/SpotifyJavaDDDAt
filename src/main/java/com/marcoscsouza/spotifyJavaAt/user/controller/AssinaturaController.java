package com.marcoscsouza.spotifyJavaAt.user.controller;

import com.marcoscsouza.spotifyJavaAt.user.domain.Assinatura;
import com.marcoscsouza.spotifyJavaAt.user.service.AssinaturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario/assinaturas")
public class AssinaturaController {

    @Autowired
    private AssinaturaService assinaturaService;

    @PostMapping
    public ResponseEntity<Assinatura> cadastrarAssinatura(@RequestBody Assinatura assinaturaDto){
        try {
            Assinatura assinatura = assinaturaService.cadastrarAssinatura(assinaturaDto);
            return ResponseEntity.ok(assinatura);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(assinaturaDto);
        }
    }
}
