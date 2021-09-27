package br.com.alura.carteira.service;

import br.com.alura.carteira.dto.UsuarioDto;
import br.com.alura.carteira.dto.UsuarioFormDto;
import br.com.alura.carteira.modelo.Usuario;
import br.com.alura.carteira.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private ModelMapper modelMapper = new ModelMapper();

    public List<UsuarioDto> listar() {

        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios
                .stream()
                .map(t -> modelMapper
                        .map(t, UsuarioDto.class))
                .collect(Collectors.toList());
    }


    public void cadastrar(UsuarioFormDto dto) {
        Usuario usuario = modelMapper.map(dto, Usuario.class);

        String senha = new Random().nextInt(999999) + "";
        usuario.setSenha(senha);

        // Só pra testar se a senha tá sendo gerada corretamente
        System.out.println(usuario.getSenha());

        usuarioRepository.save(usuario);
    }
}
