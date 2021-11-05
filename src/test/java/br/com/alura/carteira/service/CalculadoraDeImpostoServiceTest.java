package br.com.alura.carteira.service;

import br.com.alura.carteira.modelo.TipoTransacao;
import br.com.alura.carteira.modelo.Transacao;
import br.com.alura.carteira.modelo.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

// 15% de imposto para transações do tipo venda com valor superior a R$ 20.000,00
class CalculadoraDeImpostoServiceTest {

    private CalculadoraDeImpostoService calculadora;


    @BeforeEach
    public void inicializar(){

        calculadora = new CalculadoraDeImpostoService();
    }

    @Test
    void transacaoDoTipoCompraNaoDeveriaTerImposto(){

        Transacao transacao = new Transacao(
                120l, "BBSE3",
                LocalDate.now(),
                new BigDecimal("30.00"),
                10,
                TipoTransacao.COMPRA,
                new Usuario(1l, "Raul", "raul@gmail.com", "123456", null),
                BigDecimal.ZERO
        );

        BigDecimal imposto = calculadora.calcular(transacao);

        assertEquals(BigDecimal.ZERO, imposto);
    }

    @Test
    void transacaoDoTipoVendaComValorMenorQueVinteMilNaoDeveriaTerImposto(){

        Transacao transacao = new Transacao(
                120l, "BBSE3",
                LocalDate.now(),
                new BigDecimal("30.00"),
                10,
                TipoTransacao.VENDA,
                new Usuario(1l, "Raul", "raul@gmail.com", "123456", null),
                BigDecimal.ZERO
        );

        BigDecimal imposto = calculadora.calcular(transacao);

        assertEquals(BigDecimal.ZERO, imposto);
    }

    @Test
    void deveriaCalcularImpostoDeTransacaoDoTipoVendaComValorMaiorQueVinteMil(){

        Transacao transacao = new Transacao(
                120l, "BBSE3",
                LocalDate.now(),
                new BigDecimal("1000.00"),
                30,
                TipoTransacao.VENDA,
                new Usuario(1l, "Raul", "raul@gmail.com", "123456", null),
                BigDecimal.ZERO
        );

        BigDecimal imposto = calculadora.calcular(transacao);

        assertEquals(new BigDecimal("4500.00"), imposto);
    }

}