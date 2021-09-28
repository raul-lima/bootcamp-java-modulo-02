package br.com.alura.carteira.controller;

import br.com.alura.carteira.dto.UsuarioDto;
import br.com.alura.carteira.dto.UsuarioFormDto;
import br.com.alura.carteira.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {


    // Autowired faz com que o Spring já dê new no objeto. Precisa lembrar de colocar
    // a anotação @Service lá no service
    @Autowired
    private UsuarioService service;

    @GetMapping
    public Page<UsuarioDto> listar(@PageableDefault(size = 10)Pageable paginacao) {

        return service.listar(paginacao);

    }

    @PostMapping
    public void cadastrar(@RequestBody @Valid UsuarioFormDto dto) {

        service.cadastrar(dto);

    }

}
