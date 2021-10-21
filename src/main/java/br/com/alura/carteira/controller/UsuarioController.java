package br.com.alura.carteira.controller;

import br.com.alura.carteira.dto.UsuarioDto;
import br.com.alura.carteira.dto.UsuarioFormDto;
import br.com.alura.carteira.service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/usuarios")
@Api(tags = "Usuario")
public class UsuarioController {


    // Autowired faz com que o Spring já dê new no objeto. Precisa lembrar de colocar
    // a anotação @Service lá no service
    @Autowired
    private UsuarioService service;

    @GetMapping
    @ApiOperation("Listar usuarios")
    public Page<UsuarioDto> listar(@PageableDefault(size = 10)Pageable paginacao) {

        return service.listar(paginacao);

    }

    @PostMapping
    @ApiOperation("Cadastrar novo usuario")
    public ResponseEntity<UsuarioDto> cadastrar(@RequestBody @Valid UsuarioFormDto dto, UriComponentsBuilder uriBuilder) {

         UsuarioDto usuarioDto = service.cadastrar(dto);

        URI uri= uriBuilder
                .path("/usuarios/{id}")
                .buildAndExpand(usuarioDto.getId())
                .toUri();
        return ResponseEntity.created(uri).body(usuarioDto);

    }

}
