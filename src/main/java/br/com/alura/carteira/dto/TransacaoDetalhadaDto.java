package br.com.alura.carteira.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TransacaoDetalhadaDto extends TransacaoDto {

    private LocalDate data;
    private UsuarioDto usuario;


}
