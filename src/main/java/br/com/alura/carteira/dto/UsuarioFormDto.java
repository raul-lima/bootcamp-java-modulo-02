package br.com.alura.carteira.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UsuarioFormDto {

    @NotBlank(message = "Nome do usuário deve ser informado!")
    private String nome;
    @NotBlank
    private String login;

}
