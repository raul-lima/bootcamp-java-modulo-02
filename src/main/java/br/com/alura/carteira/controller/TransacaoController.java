package br.com.alura.carteira.controller;

import br.com.alura.carteira.dto.TransacaoDto;
import br.com.alura.carteira.dto.TransacaoFormDto;
import br.com.alura.carteira.modelo.Transacao;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {


    private List<Transacao> transacoes = new ArrayList<>();

    private ModelMapper modelMapper = new ModelMapper();

    @GetMapping
    public List<TransacaoDto> listar() {

        List<TransacaoDto> transacoesDto = new ArrayList<>();

//        for (Transacao transacao: transacoes
//             ) { TransacaoDto dto = new TransacaoDto();
//            dto.setTicker(transacao.getTicker());
//            dto.setPreco(transacao.getPreco());
//            dto.setQuantidade(transacao.getQuantidade());
//            dto.setTipo(transacao.getTipo());
//
//            transacoesDto.add(dto);
//
//        }
//        return transacoesDto;

        // Equivalente ao foreach anterior, só que com recursos do java 8

        return transacoes
                .stream()
                .map(t -> modelMapper
                        .map(t, TransacaoDto.class))
                .collect(Collectors.toList());


    }

    @PostMapping
    public void cadastrar(@RequestBody TransacaoFormDto dto) {


        Transacao transacao = modelMapper.map(dto, Transacao.class);


        transacoes.add(transacao);

    }

}
