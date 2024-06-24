package com.marcoscsouza.spotifyJavaAt.music.service;

import com.marcoscsouza.spotifyJavaAt.music.controller.MusicaController;
import com.marcoscsouza.spotifyJavaAt.music.controller.PlaylistController;
import com.marcoscsouza.spotifyJavaAt.music.domain.Banda;
import com.marcoscsouza.spotifyJavaAt.music.domain.Musica;
import com.marcoscsouza.spotifyJavaAt.music.repository.BandaRepository;
import com.marcoscsouza.spotifyJavaAt.music.repository.MusicaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class musicTests {

    @Mock
    private MusicaRepository musicaRepository;

    @Mock
    private BandaRepository bandaRepository;

    @InjectMocks
    private MusicaController musicaController;

    private Musica musica;
    private Banda banda;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
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

    }

    @Test
    public void getMusica() throws Exception {
        ResponseEntity<Musica> response = musicaController.getById(musica.getId());

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(musica, response.getBody());
    }
}
