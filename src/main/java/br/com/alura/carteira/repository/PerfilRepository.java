package br.com.alura.carteira.repository;

import br.com.alura.carteira.modelo.Perfil;
import br.com.alura.carteira.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface PerfilRepository extends JpaRepository<Perfil, Long> {

}

