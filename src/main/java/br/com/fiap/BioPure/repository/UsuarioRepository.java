package br.com.fiap.BioPure.repository;

import br.com.fiap.BioPure.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {


  // Optional<UserDetails> findByEmail(String email);

    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByNome(String nome);
}
