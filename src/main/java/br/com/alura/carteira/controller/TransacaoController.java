package br.com.alura.carteira.controller;

import br.com.alura.carteira.dto.TransacaoDto;
import br.com.alura.carteira.dto.TransacaoFormDto;
import br.com.alura.carteira.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    @Autowired
    private TransacaoService service;

    @GetMapping
    public List<TransacaoDto> listar() {

        return service.listar();

    }

    @PostMapping
    public void cadastrar(@RequestBody @Valid TransacaoFormDto dto) {

        service.cadastrar(dto);

    }


}
