package br.com.alura.carteira.modelo;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transacoes")
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ticker;
    private LocalDate data;
    private BigDecimal preco;
    private Integer quantidade;

    @Enumerated(EnumType.STRING)
    private TipoTransacao tipo;

    @ManyToOne
    private Usuario usuario;

    public Transacao(String ticker, LocalDate data, BigDecimal preco, Integer quantidade, TipoTransacao tipo, Usuario usuario) {
        this.ticker = ticker;
        this.data = data;
        this.preco = preco;
        this.quantidade = quantidade;
        this.tipo = tipo;
        this.usuario = usuario;
    }

    public void atualizarInformacoes(String ticker, LocalDate data, BigDecimal preco, int quantidade, TipoTransacao tipo) {

        this.ticker = ticker;
        this.data = data;
        this.preco = preco;
        this.quantidade = quantidade;
        this.tipo = tipo;
    }

    public boolean pertenceAoUsuario(Usuario usuario) {
        return this.usuario.equals(usuario);
    }
}
