package com.marcoscsouza.spotifyJavaAt.music.service;

import com.marcoscsouza.spotifyJavaAt.music.domain.Banda;
import com.marcoscsouza.spotifyJavaAt.music.domain.Musica;
import com.marcoscsouza.spotifyJavaAt.music.domain.Playlist;
import com.marcoscsouza.spotifyJavaAt.music.repository.PlaylistRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PlaylistTests {

    @Autowired
    private PlaylistService playlistService;

    private Musica musica;
    private Banda banda;

    @Autowired
    private PlaylistRepository playlistRepository;

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

        assertEquals("Workout", playlist.getNome());

    }

    @Test
    public void deve_adicionar_musica_na_playlist_com_sucesso() throws Exception {
        Playlist playlist = new Playlist();
        playlist.criar("Workout");
        playlist.adicionarMusica(musica);

        assertSame("Workout", playlist.getNome());
        assertFalse(playlist.getMusicas().isEmpty());
    }


    @Test
    public void nao_deve_adicionar_musica_na_playlist_com_sucesso() throws Exception {
        Playlist playlist = new Playlist();
        playlist.criar("Workout");

        assertSame("Workout", playlist.getNome());
        assertTrue(playlist.getMusicas().isEmpty());
    }

}
