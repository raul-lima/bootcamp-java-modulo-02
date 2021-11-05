package br.com.alura.carteira.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UsuarioFormDto {

    @NotBlank(message = "Nome do usuário deve ser informado!")
    private String nome;
    @NotBlank(message = "Login deve ser informado!")
    private String login;
    @NotNull
    private Long perfilId;

}
