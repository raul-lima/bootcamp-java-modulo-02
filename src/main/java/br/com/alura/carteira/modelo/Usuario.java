package br.com.alura.carteira.modelo;

import lombok.*;

@Getter @Setter
@ToString(exclude = "senha")
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    private String nome;
    private String login;
    private String senha;
}
