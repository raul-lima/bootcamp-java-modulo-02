package br.com.alura.carteira.infra.security;

import br.com.alura.carteira.modelo.Usuario;
import br.com.alura.carteira.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class VerificacaoTokenFilter extends OncePerRequestFilter {

    private TokenService tokenService;

    private UsuarioRepository usuarioRepository;

    public VerificacaoTokenFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        // recuperar o token e validar
        // se o token estiver correto, liberar a requisição

        // O token não é enviado como parâmetro, e sim como cabeçalho
        // O padrão de nome para pegar o cabeçalho é Authorization
        String token = httpServletRequest.getHeader("Authorization");
        if (token == null || token.isBlank()) {
            // Segue o fluxo sem logar do usuário
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            // Sai do filtro sem fazer mais nada
            return;
        }

        //O token não vem puro, ele vem com a palavra Bearer, espaço, e depois vem o token
        // Tem que tirar a palavra bearer
        token = token.replace("Bearer", "");
        boolean tokenValido = tokenService.isValido(token);
        if (tokenValido) {
            Long idUsuario = tokenService.extrairIdUsuario(token);
            Usuario logado = usuarioRepository.getById(idUsuario);
            Authentication authentication = new UsernamePasswordAuthenticationToken(logado, null, null);
            // Faz o authentication representar o usuário logado
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }
}
