package br.com.alura.carteira.service;

import br.com.alura.carteira.dto.UsuarioDto;
import br.com.alura.carteira.dto.UsuarioFormDto;
import br.com.alura.carteira.infra.EnviadorDeEmail;
import br.com.alura.carteira.infra.EnviadorDeEmailReal;
import br.com.alura.carteira.modelo.Perfil;
import br.com.alura.carteira.modelo.Usuario;
import br.com.alura.carteira.repository.PerfilRepository;
import br.com.alura.carteira.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Random;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private EnviadorDeEmail enviadorDeEmail;

    public Page<UsuarioDto> listar(Pageable paginacao) {

//        Page<Usuario> usuarios = usuarioRepository.findAll(paginacao);
        return usuarioRepository
                .findAll(paginacao)
                .map(t -> modelMapper
                        .map(t, UsuarioDto.class));
    }


    @Transactional
    public UsuarioDto cadastrar(UsuarioFormDto dto) {
        Usuario usuario = modelMapper.map(dto, Usuario.class);
        usuario.setId(null);

        Perfil perfil = perfilRepository.getById(dto.getPerfilId());

        usuario.adicionarPerfil(perfil);

        String senha = new Random().nextInt(999999) + "";
        usuario.setSenha(bCryptPasswordEncoder.encode(senha));

        // Só pra testar se a senha tá sendo gerada corretamente
        System.out.println(usuario.getSenha());

        usuarioRepository.save(usuario);

        // Enviar e-mail com senha

        String destinatario = usuario.getEmail();
        String assunto = "Carteira - Boas vindas";
        String mensagem = String.format("Olá %s!\n\n Aqui estão seus dados de acesso ao sistema Carteira:" +
                "\nLogin:%s\nSenha:%s",
                usuario.getNome(), usuario.getLogin(), senha);

        enviadorDeEmail.enviarEmail(destinatario, assunto, mensagem);

        return modelMapper.map(usuario, UsuarioDto.class);
    }
}
