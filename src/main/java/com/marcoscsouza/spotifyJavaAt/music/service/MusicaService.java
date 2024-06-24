package com.marcoscsouza.spotifyJavaAt.music.service;

import com.marcoscsouza.spotifyJavaAt.music.domain.Musica;
import com.marcoscsouza.spotifyJavaAt.music.repository.MusicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MusicaService {


    @Autowired
    private MusicaRepository musicaRepository;

    public Musica findById(UUID musicId) throws Exception {
        return musicaRepository.findById(musicId)
                .orElseThrow(()-> new Exception("musica não encontrada!"));
    }

}
