package br.com.fiap.BioPure.service;

import br.com.fiap.BioPure.dto.UsuarioCadastroDto;
import br.com.fiap.BioPure.dto.UsuarioExibicaoDto;
import br.com.fiap.BioPure.exception.ProdutoNaoEncontrado;
import br.com.fiap.BioPure.exception.UsuarioNaoEncontrado;
import br.com.fiap.BioPure.model.Usuario;
import br.com.fiap.BioPure.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //  @Autowired
    //   private BCryptPasswordEncoder passwordEncoder;

    public UsuarioExibicaoDto salvarUsuario(UsuarioCadastroDto usuarioDto) {

        String senhaCriptografada = passwordEncoder.encode(usuarioDto.senha());

        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuarioDto, usuario);
        usuario.setSenha(senhaCriptografada);

        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return new UsuarioExibicaoDto(usuarioSalvo);

    }

    // Listar todos os usuários
    public List<UsuarioExibicaoDto> listarTodos() {
        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioExibicaoDto::new)
                .collect(Collectors.toList());
    }

    // Buscar usuário por ID
    public UsuarioExibicaoDto buscarPorId(Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        if (usuarioOptional.isPresent()) {
            return new UsuarioExibicaoDto(usuarioOptional.get());
        } else {
            throw new UsuarioNaoEncontrado("Usuário não encontrado!");
        }
    }

    // Excluir usuário
    public void excluir(Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        if (usuarioOptional.isPresent()) {
            usuarioRepository.delete(usuarioOptional.get());
        } else {
            throw new UsuarioNaoEncontrado("Usuário não encontrado para exclusão");
        }
    }

    // Atualizar dados do usuário
    public Usuario atualizar(Usuario usuario) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuario.getUsuarioId());

        if (usuarioOptional.isPresent()) {
            return usuarioRepository.save(usuario);
        } else {
            throw new UsuarioNaoEncontrado("Usuário não encontrado");
        }
    }


    public Usuario buscarPeloNome(String nome) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByNome(nome);

        if (usuarioOptional.isPresent()) {
            return usuarioOptional.get();
        } else {
            throw new UsuarioNaoEncontrado("Usuário não encontrado");
        }
    }

    // Buscar por e-mail
    public UsuarioExibicaoDto buscarUsuarioPorEmail(String email) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);

        if (usuarioOptional.isPresent()) {
            return new UsuarioExibicaoDto(usuarioOptional.get());
        } else {
            throw new UsuarioNaoEncontrado("Este e-mail não existe.");
        }
    }
}
