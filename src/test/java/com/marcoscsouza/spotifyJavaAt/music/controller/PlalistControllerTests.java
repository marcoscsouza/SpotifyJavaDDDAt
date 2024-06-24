package com.marcoscsouza.spotifyJavaAt.music.controller;

import com.marcoscsouza.spotifyJavaAt.music.domain.Banda;
import com.marcoscsouza.spotifyJavaAt.music.domain.Musica;
import com.marcoscsouza.spotifyJavaAt.music.domain.Playlist;
import com.marcoscsouza.spotifyJavaAt.music.repository.PlaylistRepository;
import com.marcoscsouza.spotifyJavaAt.music.service.PlaylistService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Objects;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PlalistControllerTests {

    @Mock
    private PlaylistService playlistService;

    @InjectMocks
    private PlaylistController playlistController;

    private Musica musica;
    private Banda banda;

    @BeforeEach
    void setUp() {
        musica = new Musica();
        musica.setNome("One");
        musica.setDuracao(465);
        musica.setId(UUID.randomUUID());

        banda = new Banda();
        banda.setNome("Metallica");
        banda.setDescricao("Heavy Metal");
        musica.setBanda(banda);

    }

    @Test
    public void deve_criar_playlist_com_sucesso() {
        Playlist playlist = new Playlist();
        playlist.criar("Workout");

        ResponseEntity<Playlist> response = playlistController.criarPlaylist(playlist);
        assertEquals(201, response.getStatusCodeValue());

    }

    @Test
    public void deve_adicionar_musica_na_playlist_com_sucesso() throws Exception {
        Playlist playlist = new Playlist();
        playlist.criar("Workout");

        ResponseEntity<Void> response = playlistController
                                .adicionarMusica(playlist.getId(),musica.getId());

        assertEquals(200, response.getStatusCodeValue());
    }


    @Test
    public void nao_deve_adicionar_musica_na_playlist_com_sucesso() throws Exception {
        Playlist playlist = new Playlist();
        playlist.criar("Workout");

        ResponseEntity<Void> response = playlistController
                .adicionarMusica(playlist.getId(),UUID.randomUUID());

        assertEquals(400, response.getStatusCodeValue());
    }

    @Test
    public void deve_remover_musica_da_playlist_com_sucesso() throws Exception {
        Playlist playlist = new Playlist();
        playlist.criar("Workout");
        playlist.adicionarMusica(musica);


        ResponseEntity<Void> response = playlistController
                .removerMusica(playlist.getId(),musica.getId());

        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void nao_deve_remover_musica_da_playlist_com_sucesso() throws Exception {
        Playlist playlist = new Playlist();
        playlist.criar("Workout");


        ResponseEntity<Void> response = playlistController
                .removerMusica(playlist.getId(),musica.getId());

        assertEquals(400, response.getStatusCodeValue());
    }
}
