package com.marcoscsouza.spotifyJavaAt.user.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column
    private String numero;
    @Column
    private Boolean ativo;
    @Column
    private LocalDate validade;
    @Column
    private double limite;

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Transacao> transacoes = new ArrayList<>();

    @Transient
    private int TRANSACAO_INTERVALO_TEMPO = 2;
    @Transient
    private int TRANSACAO_QUANTIDADE_ALTA_FREQUENCIA = 3;
    @Transient
    private int TRANSACAO_MERCHANT_DUPLICADAS = 2;

    @Transient
    private String eMessageValidarTransacao = "";

    public void criarTransacao(String merchant, double valor, String descricao) throws Exception {
        // validar Cartao

        if (this.ativo == false) {
            throw new Exception("Cartão não está ativo");
        }
        Transacao transacao = new Transacao();
        transacao.setMerchant(merchant);
        transacao.setValor(valor);
        transacao.setDescricao(descricao);
        transacao.setDtTransacao(LocalDateTime.now());
        transacao.setCartao(this);


        //Verificar limite
        if (this.validarLimite(transacao) == false){
            throw new Exception("Cartão não possui limite suficiente!");
        }

        //Validar Transacao
        if (this.validarTransacao(transacao) == false) {
            throw new Exception("Transação inválida!" + eMessageValidarTransacao);
        }

        // diminui o limite do cartao
        this.setLimite(this.getLimite() - transacao.getValor());

        // adiciona uma nova transacao e cria o codigo de autorizacao
//        transacao.setCondigoAutorizacao(UUID.randomUUID());

        this.transacoes.add(transacao);
    }

    private boolean validarLimite(Transacao transacao) {
        return this.limite > transacao.getValor();
    }

    private boolean validarTransacao(Transacao transacao) {

        List<Transacao> ultimasTransacoes = this.getTransacoes()
                .stream()
                .filter((x) -> x.getDtTransacao()
                        .isAfter(LocalDateTime.now()
                                .minusMinutes(this.TRANSACAO_INTERVALO_TEMPO)))
                .toList();

        if (ultimasTransacoes.size() >= this.TRANSACAO_QUANTIDADE_ALTA_FREQUENCIA){
            eMessageValidarTransacao = " TRANSACAO_QUANTIDADE_ALTA_FREQUENCIA";
            return false;
        }
        List<Transacao> transacoesMerchantRepetidas = ultimasTransacoes.stream()
                .filter((x) -> x.getMerchant().equals(transacao.getMerchant())
                                && x.getValor() == transacao.getValor())
                .toList();

        if (transacoesMerchantRepetidas.size() >= this.TRANSACAO_MERCHANT_DUPLICADAS){
            eMessageValidarTransacao = " TRANSACAO_MERCHANT_DUPLICADAS";
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Cartao{" +
                "numero='" + numero + '\'' +
                ", ativo=" + ativo +
                ", validade=" + validade +
                ", limite=" + limite +
                '}';
    }

    //TODO: Validar validade do cartao


}
