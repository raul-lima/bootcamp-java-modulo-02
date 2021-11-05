package br.com.alura.carteira.repository;

import br.com.alura.carteira.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByLogin(String login);


    @Query("select u from Usuario u join fetch u.perfis where u.id = :id")
    Optional<Usuario> carregarPorIdComPerfis(Long id);
}

