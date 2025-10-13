package br.com.fiap.BioPure.controller;

import br.com.fiap.BioPure.dto.ProdutoCadastroDto;
import br.com.fiap.BioPure.dto.ProdutoExibicaoDto;
import br.com.fiap.BioPure.dto.UsuarioCadastroDto;
import br.com.fiap.BioPure.dto.UsuarioExibicaoDto;
import br.com.fiap.BioPure.model.Produto;
import br.com.fiap.BioPure.model.Usuario;
import br.com.fiap.BioPure.service.ProdutoService;
import br.com.fiap.BioPure.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/usuarios")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioExibicaoDto salvar(@RequestBody @Valid UsuarioCadastroDto usuario) {
        return usuarioService.salvarUsuario(usuario);
    }

    @GetMapping("/usuarios")
    @ResponseStatus(HttpStatus.OK)
    public List<UsuarioExibicaoDto> listarTodos() {
        return usuarioService.listarTodos();
    }

    @GetMapping("/usuarios/{usuarioId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UsuarioExibicaoDto> buscarPorId(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(usuarioService.buscarPorId(usuarioId));
    }


    @DeleteMapping("/usuarios/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long usuarioId) {
        usuarioService.excluir(usuarioId);
    }

    @PutMapping("/usuarios")
    @ResponseStatus(HttpStatus.OK)
    public Usuario atualizar(@RequestBody Usuario usuario) {
        return usuarioService.atualizar(usuario);
    }

}


