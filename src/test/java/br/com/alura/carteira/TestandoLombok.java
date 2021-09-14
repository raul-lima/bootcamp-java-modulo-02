package br.com.alura.carteira;

import br.com.alura.carteira.modelo.Transacao;

import java.math.BigDecimal;

public class TestandoLombok {

    public static void main(String[] args) {

        Transacao transacao = new Transacao();

        transacao.setPreco(new BigDecimal("100"));

        System.out.println(transacao);


    }
}
