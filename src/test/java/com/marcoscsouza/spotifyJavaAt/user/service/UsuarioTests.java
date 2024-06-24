package com.marcoscsouza.spotifyJavaAt.user.service;


import com.marcoscsouza.spotifyJavaAt.user.domain.Cartao;
import com.marcoscsouza.spotifyJavaAt.user.domain.Plano;
import com.marcoscsouza.spotifyJavaAt.user.domain.Usuario;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class UsuarioTests {

    private Cartao cartao;
    private Plano plano;

    @BeforeEach
    void setUp() {
        cartao = new Cartao();
        cartao.setNumero("123456781234");
        cartao.setLimite(1000);
        cartao.setAtivo(true);
        cartao.setValidade(LocalDate.now().plusYears(1));


        plano = new Plano();
        plano.setNome("Basico");
        plano.setDescricao("Plano Basico");
        plano.setValor(29);
        plano.setAtivo(true);

    }

    @Test
    public void deve_criar_um_usuario_corretamente() throws Exception {
        Usuario usuario = new Usuario();

        usuario.criar("Marcos","marcossouza@gmail.com", "123456", "42682445063", cartao, plano);

        Assertions.assertTrue(usuario.getCpf().equals("42682445063"));
        Assertions.assertTrue(usuario.getEmail().equals("marcossouza@gmail.com"));
        Assertions.assertTrue(usuario.getSenha().equals("MTIzNDU2"));
        Assertions.assertTrue(usuario.getNome().equals("Marcos"));
        Assertions.assertTrue(usuario.getCartoes().size() == 1);
    }

    @Test
    public void nao_deve_criar_usuario_com_cpf_invalido() throws Exception {
        Usuario usuario = new Usuario();

        Assertions.assertThrows(Exception.class, () -> {
            usuario.criar("Marcos","marcossouza@gmail.com", "123456", "12345678900", cartao, plano);

        });

    }
}
