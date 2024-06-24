package com.marcoscsouza.spotifyJavaAt.music.controller;

import com.marcoscsouza.spotifyJavaAt.music.domain.Banda;
import com.marcoscsouza.spotifyJavaAt.music.domain.Musica;
import com.marcoscsouza.spotifyJavaAt.music.repository.BandaRepository;
import com.marcoscsouza.spotifyJavaAt.music.repository.MusicaRepository;
import com.marcoscsouza.spotifyJavaAt.music.service.MusicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/usuario/musicas")
public class MusicaController {
    private Musica musica;
    private Banda banda;

    @Autowired
    private MusicaService musicaService;

    @Autowired
    private MusicaRepository musicaRepository;

    @Autowired
    private BandaRepository bandaRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Musica> getById(@PathVariable UUID id) throws Exception {
        musica = new Musica();
        musica.setNome("One");
        musica.setDuracao(465);
        musica.setId(UUID.fromString("9499f773-ed91-4a7a-8d02-95212dd1723c"));

        banda = new Banda();
        banda.setNome("Metallica");
        banda.setDescricao("Heavy Metal");
        musica.setBanda(banda);
        bandaRepository.save(banda);
        musicaRepository.save(musica);
        System.out.println(musica.toString());

        if (musica.getId() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(musica);
        }
        return ResponseEntity.ok(musica);
    }
}
