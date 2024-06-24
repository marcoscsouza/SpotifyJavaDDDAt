package com.marcoscsouza.spotifyJavaAt.music.service;

import com.marcoscsouza.spotifyJavaAt.music.domain.Musica;
import com.marcoscsouza.spotifyJavaAt.music.domain.Playlist;
import com.marcoscsouza.spotifyJavaAt.music.repository.MusicaRepository;
import com.marcoscsouza.spotifyJavaAt.music.repository.PlaylistRepository;
import com.marcoscsouza.spotifyJavaAt.user.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PlaylistService {

    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private MusicaRepository musicaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Playlist criarPlaylist(Playlist playlist){

        playlist.criar(playlist.getNome());
        playlistRepository.save(playlist);
        return playlist;
    }

    public void adicionarMusica(UUID playlistId, UUID musicaId) throws Exception {

        Musica musica = musicaRepository.findById(musicaId)
                .orElseThrow(() -> new Exception("Música não encontrada"));

        for (Playlist playlist : playlistRepository.findAll()) {
            if (playlist.getId().equals(playlistId)) {
                playlist.adicionarMusica(musica);
                playlistRepository.save(playlistRepository.findById(playlistId).get());
            }
        }

    }

    public void removerMusica(UUID playlistId, UUID musicaId) throws Exception {
        Musica musica = musicaRepository.findById(musicaId)
                .orElseThrow(() -> new Exception("Música não encontrada"));

        for (Playlist playlist : playlistRepository.findAll()) {
            if (playlist.getId().equals(playlistId)) {
                playlist.removerMusica(musica);
                playlistRepository.save(playlistRepository.findById(playlistId).get());
            }
        }

    }


}
