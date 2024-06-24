package com.marcoscsouza.spotifyJavaAt.user.service;

import com.marcoscsouza.spotifyJavaAt.user.domain.Assinatura;
import com.marcoscsouza.spotifyJavaAt.user.domain.Plano;
import com.marcoscsouza.spotifyJavaAt.user.domain.Usuario;
import com.marcoscsouza.spotifyJavaAt.user.repository.PlanoRepository;
import com.marcoscsouza.spotifyJavaAt.user.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AssinaturaService {


    @Autowired
    private PlanoRepository planoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    public Assinatura cadastrarAssinatura(Assinatura assinaturaDto) throws Exception {
        Usuario usuario = usuarioRepository.findById(assinaturaDto.getId())
                .orElseThrow(() -> new Exception("Usuário não encontrado"));

        if ((usuario.getAssinaturas() != null)) {
            throw new Exception("Usuário já possui uma assinatura ativa.");
        }

        Plano plano = planoRepository.findById(assinaturaDto.getPlano().getId())
                .orElseThrow(() -> new Exception("Plano não encontrado"));

        Assinatura assinatura = new Assinatura();
        assinatura.setPlano(plano);
        assinatura.setDtAssinatura(LocalDateTime.now());
        assinatura.setAtivo(true);
        usuario.getAssinaturas().add(assinatura);
        usuarioRepository.save(usuario);

        return assinatura;
    }


}
