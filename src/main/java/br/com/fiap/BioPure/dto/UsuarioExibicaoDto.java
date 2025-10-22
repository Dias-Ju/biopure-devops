package br.com.fiap.BioPure.dto;

import br.com.fiap.BioPure.model.Usuario;
import br.com.fiap.BioPure.model.UsuarioRole;

public record UsuarioExibicaoDto(
        Long id,
        String nome,
        String email,
        UsuarioRole role
) {
    public UsuarioExibicaoDto(Usuario usuarioSalvo) {
        this(
                usuarioSalvo.getUsuarioId(),
                usuarioSalvo.getNome(),
                usuarioSalvo.getEmail(),
                usuarioSalvo.getRole()
        );
    }
}

