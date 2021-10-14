package br.com.alura.carteira.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Erro500Dto {

    private LocalDateTime timestamp;
    private String erro;
    private String mensagem;
    private String path;

}
