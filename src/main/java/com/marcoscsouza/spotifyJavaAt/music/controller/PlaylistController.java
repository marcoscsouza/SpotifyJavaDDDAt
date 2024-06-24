package com.marcoscsouza.spotifyJavaAt.music.controller;

import com.marcoscsouza.spotifyJavaAt.music.domain.Playlist;
import com.marcoscsouza.spotifyJavaAt.music.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/usuario/playlists")
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;

    @PostMapping
    public ResponseEntity<Playlist> criarPlaylist(@RequestBody Playlist playlist) {
        Playlist response = this.playlistService.criarPlaylist(playlist);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/{playlistId}/musica/{musicaId}")
    public ResponseEntity<Void> adicionarMusica(@PathVariable UUID playlistId,
                                                @PathVariable UUID musicaId) {
        try {
            playlistService.adicionarMusica(playlistId,musicaId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{playlistId}/musica/{musicaId}")
    public ResponseEntity<Void> removerMusica(@PathVariable UUID playlistId,
                                                @PathVariable UUID musicaId) {
        try {
            playlistService.removerMusica(playlistId,musicaId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
