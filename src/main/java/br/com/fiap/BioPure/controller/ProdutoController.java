package br.com.fiap.BioPure.controller;

import br.com.fiap.BioPure.dto.ProdutoCadastroDto;
import br.com.fiap.BioPure.dto.ProdutoExibicaoDto;
import br.com.fiap.BioPure.model.Produto;
import br.com.fiap.BioPure.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @PostMapping("/produtos")
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoExibicaoDto gravar(@RequestBody @Valid ProdutoCadastroDto produtoCadastroDto) {
        return service.gravar(produtoCadastroDto);
    }

    @GetMapping("/produtos")
    @ResponseStatus(HttpStatus.OK)
    public Page<ProdutoExibicaoDto> listarTodosOsProdutos(Pageable paginacao) {
        return service.listarTodosOsProdutos(paginacao);
    }

    @GetMapping("/produtos/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProdutoExibicaoDto buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }


    @DeleteMapping("/produtos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }

    @PutMapping("/produtos")
    @ResponseStatus(HttpStatus.OK)
    public Produto atualizar(@RequestBody Produto produtos) {
        return service.atualizar(produtos);
    }

    @GetMapping("/produtos/nome/{nome}")
    @ResponseStatus(HttpStatus.OK)
    public Produto buscarPeloNome(@PathVariable String nome) {
        return service.buscarPeloNome(nome);
    }
}