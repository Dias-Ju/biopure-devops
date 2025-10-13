package br.com.fiap.BioPure.service;

import br.com.fiap.BioPure.model.Usuario;
import br.com.fiap.BioPure.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorizationService implements UserDetailsService {


    @Autowired
    private UsuarioRepository repository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> optionalUsuario = repository.findByEmail(username);
        if (optionalUsuario.isPresent()) {
            return optionalUsuario.get();  // Retorna o valor se presente
        } else {
            throw new UsernameNotFoundException("Usuário não encontrado com email: " + username);
        }
    }
}