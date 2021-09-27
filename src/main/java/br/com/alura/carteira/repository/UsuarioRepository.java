package br.com.alura.carteira.repository;

import br.com.alura.carteira.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}

