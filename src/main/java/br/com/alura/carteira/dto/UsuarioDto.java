package br.com.alura.carteira.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UsuarioDto {

    private Long id;
    private String nome;
    private String login;

}
