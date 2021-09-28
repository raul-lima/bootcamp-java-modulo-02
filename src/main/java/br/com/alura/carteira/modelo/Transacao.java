package br.com.alura.carteira.modelo;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@ToString
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

}
