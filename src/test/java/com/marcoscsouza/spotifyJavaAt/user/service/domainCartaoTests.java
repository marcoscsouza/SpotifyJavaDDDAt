package com.marcoscsouza.spotifyJavaAt.user.service;

import com.marcoscsouza.spotifyJavaAt.user.domain.Cartao;
import com.marcoscsouza.spotifyJavaAt.user.domain.Transacao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
public class domainCartaoTests {


    @Test
    public void deve_criar_transacao_com_sucesso() throws Exception {
        Cartao cartao = new Cartao();
        cartao.setAtivo(true);
        cartao.setLimite(1000);
        cartao.setNumero("5110047446070937");
        cartao.setValidade(LocalDate.now().plusYears(1));

        cartao.criarTransacao("xpto", 100, "teste");
        Assertions.assertTrue(cartao.getTransacoes().size() > 0);
        Assertions.assertTrue(cartao.getLimite() == 900);
        //Assertions.assertTrue();

    }

    @Test
    public void nao_deve_criar_transacao_caso_cartao_esteja_inativo() throws Exception {
        Cartao cartao = new Cartao();
        cartao.setAtivo(false);
        cartao.setLimite(1000);
        cartao.setNumero("5110047446070937");
        cartao.setValidade(LocalDate.now().plusYears(1));

        Assertions.assertThrows(Exception.class, () -> {
            cartao.criarTransacao("xpto", 100, "teste");

        } );
    }

    @Test
    public void nao_deve_criar_transacao_caso_cartao_esteja_sem_limite() throws Exception {
        Cartao cartao = new Cartao();
        cartao.setAtivo(true);
        cartao.setLimite(50);
        cartao.setNumero("5110047446070937");
        cartao.setValidade(LocalDate.now().plusYears(1));

        Assertions.assertThrows(Exception.class, () -> {
            cartao.criarTransacao("xpto", 100, "teste");

        } );


    }

    @Test
    public void nao_deve_criar_transacao_caso_seja_alta_frequencia() throws Exception {
        Cartao cartao = new Cartao();
        cartao.setAtivo(true);
        cartao.setLimite(1000);
        cartao.setNumero("5110047446070937");
        cartao.setValidade(LocalDate.now().plusYears(1));

        for (int i = 1; i <= 10; i++){
            Transacao transacao = new Transacao();
            transacao.setDtTransacao(LocalDateTime.now().minusSeconds(30+i));
            transacao.setValor(50);
            transacao.setMerchant("dummy"+i);
            cartao.getTransacoes().add(transacao);
        }

        Assertions.assertThrows(Exception.class, () -> {
            cartao.criarTransacao("xpto", 100, "teste");

        } );

    }

    @Test
    public void nao_deve_criar_transacao_caso_seja_repetida() throws Exception {
        Cartao cartao = new Cartao();
        cartao.setAtivo(true);
        cartao.setLimite(1000);
        cartao.setNumero("5110047446070937");
        cartao.setValidade(LocalDate.now().plusYears(1));

        for (int i = 1; i <= 2; i++){
            Transacao transacao = new Transacao();
            transacao.setDtTransacao(LocalDateTime.now().minusSeconds(30+i));
            transacao.setValor(50);
            transacao.setMerchant("dummy");
            cartao.getTransacoes().add(transacao);
        }

        Assertions.assertThrows(Exception.class, () -> {
            cartao.criarTransacao("dummy", 50, "teste");


        } );

    }
}
