package br.com.alura.carteira.controller;

import br.com.alura.carteira.dto.AtualizacaoTransacaoFormDto;
import br.com.alura.carteira.dto.TransacaoDetalhadaDto;
import br.com.alura.carteira.dto.TransacaoDto;
import br.com.alura.carteira.dto.TransacaoFormDto;
import br.com.alura.carteira.modelo.Usuario;
import br.com.alura.carteira.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    @Autowired
    private TransacaoService service;

    @GetMapping
    public Page<TransacaoDto> listar(@PageableDefault(size = 10) Pageable paginacao, @ApiIgnore @AuthenticationPrincipal Usuario logado) {

        return service.listar(paginacao, logado);

    }

    @PostMapping
    public ResponseEntity<TransacaoDto> cadastrar(@RequestBody @Valid TransacaoFormDto dto, UriComponentsBuilder uriBuilder, @ApiIgnore @AuthenticationPrincipal Usuario logado) {

       TransacaoDto transacaoDto = service.cadastrar(dto, logado);

        URI uri= uriBuilder
                .path("/transacoes/{id}")
                .buildAndExpand(transacaoDto.getId())
                .toUri();
        return ResponseEntity.created(uri).body(transacaoDto);
    }

    @PutMapping
    public ResponseEntity<TransacaoDto> atualizar(@RequestBody @Valid AtualizacaoTransacaoFormDto dto, @ApiIgnore @AuthenticationPrincipal Usuario logado) {

        TransacaoDto atualizada = service.atualizar(dto, logado);

        return ResponseEntity.ok(atualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TransacaoDto> remover(@PathVariable @NotNull Long id, @ApiIgnore @AuthenticationPrincipal Usuario logado) {

        service.remover(id, logado);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransacaoDetalhadaDto> detalhar(@PathVariable @NotNull Long id,@ApiIgnore @AuthenticationPrincipal Usuario logado) {

        TransacaoDetalhadaDto dto = service.detalhar(id, logado);

        return ResponseEntity.ok(dto);
    }

}
