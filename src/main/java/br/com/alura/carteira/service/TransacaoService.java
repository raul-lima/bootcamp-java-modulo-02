package br.com.alura.carteira.service;

import br.com.alura.carteira.dto.TransacaoDto;
import br.com.alura.carteira.dto.TransacaoFormDto;
import br.com.alura.carteira.modelo.Transacao;
import br.com.alura.carteira.repository.TransacaoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransacaoService {

    @Autowired
    private TransacaoRepository transacaoRepository;

    private ModelMapper modelMapper = new ModelMapper();


    public Page<TransacaoDto> listar(@PageableDefault(size = 10) Pageable paginacao) {

        Page<Transacao> transacoes = transacaoRepository.findAll( paginacao);

        return transacoes
                .map(t -> modelMapper
                        .map(t, TransacaoDto.class));


    }

    @Transactional
    public TransacaoDto cadastrar(TransacaoFormDto dto) {



        Transacao transacao = modelMapper.map(dto, Transacao.class);

        transacao.setId(null);

        transacaoRepository.save(transacao);

        return modelMapper.map(transacao, TransacaoDto.class);

    }
}
