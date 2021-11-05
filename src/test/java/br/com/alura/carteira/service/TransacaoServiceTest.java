package br.com.alura.carteira.service;

import br.com.alura.carteira.dto.TransacaoDto;
import br.com.alura.carteira.dto.TransacaoFormDto;
import br.com.alura.carteira.modelo.TipoTransacao;
import br.com.alura.carteira.repository.TransacaoRepository;
import br.com.alura.carteira.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class TransacaoServiceTest {

    @Mock
    private TransacaoRepository transacaoRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private TransacaoService service;


    @Test
    void deveriaCadastrarUmaTransacao() {


        TransacaoFormDto formDto = criarTransacaoFormDto();
        TransacaoDto dto = service.cadastrar(formDto, null);

        Mockito.verify(transacaoRepository).save(Mockito.any());

        assertEquals(formDto.getTicker(), dto.getTicker());
        assertEquals(formDto.getPreco(), dto.getPreco());
        assertEquals(formDto.getQuantidade(), dto.getQuantidade());
        assertEquals(formDto.getTipo(), dto.getTipo());
    }


    @Test
    void naoDeveriaCadastrarUmaTransacaoComUsuarioInexistente() {


        TransacaoFormDto formDto = criarTransacaoFormDto();

        Mockito
                .when(usuarioRepository.getById(formDto.getUsuarioId()))
                .thenThrow(EntityNotFoundException.class);

        assertThrows(IllegalArgumentException.class, () -> service.cadastrar(formDto, null));

    }

    private TransacaoFormDto criarTransacaoFormDto() {
        return new TransacaoFormDto(
                "ITSA94",
                new BigDecimal("10.40"),
                LocalDate.now(),
                120,
                TipoTransacao.COMPRA,
                1l
        );
    }

}