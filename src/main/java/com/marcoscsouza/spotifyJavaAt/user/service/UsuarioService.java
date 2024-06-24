package com.marcoscsouza.spotifyJavaAt.user.service;

import com.marcoscsouza.spotifyJavaAt.user.domain.Cartao;
import com.marcoscsouza.spotifyJavaAt.user.domain.Plano;
import com.marcoscsouza.spotifyJavaAt.user.domain.Usuario;
import com.marcoscsouza.spotifyJavaAt.user.repository.CartaoRepository;
import com.marcoscsouza.spotifyJavaAt.user.repository.PlanoRepository;
import com.marcoscsouza.spotifyJavaAt.user.repository.UsuarioRepository;
import com.marcoscsouza.spotifyJavaAt.user.dto.UsuarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PlanoRepository planoRepository;

    @Autowired
    private CartaoRepository cartaoRepository;

    public UsuarioDto criar(UsuarioDto dto) throws Exception {
        //Verificar se temos um plano valido

        Optional<Plano> plano = this.planoRepository.findById(dto.getIdPlano());

        if (plano.isEmpty()){
            throw  new Exception("Plano não encontrado!");
        }

        // criando obj de cartão
        Cartao cartao = new Cartao();
        cartao.setNumero(dto.getNumeroCartao());
        cartao.setAtivo(dto.getCartaoAtivo());
        cartao.setLimite(dto.getLimite());
        cartaoRepository.save(cartao);

        // verificar se o cpf ja esta cadastrado
        if(this.usuarioRepository.findUsuarioByCpf(dto.getCpf()).isEmpty() == false){
            throw new Exception("CPF já cadastrado!");
        }

        Usuario usuario = new Usuario();
        usuario.criar(dto.getNome(), dto.getEmail(), dto.getSenha(), dto.getCpf(), cartao, plano.get());

        // Salvar usuario
        this.usuarioRepository.save(usuario);


        // cria a resposta para o controller
        UsuarioDto response = new UsuarioDto();
        response.setId(usuario.getId());
        response.setNome(usuario.getNome());
        response.setEmail(usuario.getEmail());
        response.setSenha("********");
        response.setNumeroCartao("**** **** **** ****");
        response.setCartaoAtivo(cartao.getAtivo());
        response.setLimite(cartao.getLimite());
        response.setIdPlano(plano.get().getId());
        response.setCpf(usuario.getCpf());
        return response;


    }
}
