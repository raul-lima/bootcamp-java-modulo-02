package br.com.alura.carteira.modelo;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter @Setter @ToString @AllArgsConstructor
@NoArgsConstructor
public class Transacao {


	private String ticker;
	private LocalDate data;
	private BigDecimal preco;
	private int quantidade;
	private TipoTransacao tipo;

}
