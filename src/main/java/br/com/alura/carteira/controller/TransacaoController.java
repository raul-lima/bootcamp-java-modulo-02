package br.com.alura.carteira.controller;

import br.com.alura.carteira.modelo.Transacao;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    private List<Transacao> transacoes = new ArrayList<>();

    @GetMapping
    public List<Transacao> listar(){
        return transacoes;
    }
    @PostMapping
    public void cadastrar(@RequestBody Transacao transacao){

        transacoes.add(transacao);

    }

}
