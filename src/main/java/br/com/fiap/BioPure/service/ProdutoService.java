package br.com.fiap.BioPure.service;

import br.com.fiap.BioPure.dto.ProdutoCadastroDto;
import br.com.fiap.BioPure.dto.ProdutoExibicaoDto;
import br.com.fiap.BioPure.exception.ProdutoNaoEncontrado;
import br.com.fiap.BioPure.model.Produto;
import br.com.fiap.BioPure.repository.ProdutoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    // Gravar novo produto
    public ProdutoExibicaoDto gravar(ProdutoCadastroDto produtoCadastroDto) {
        Produto produto = new Produto();
        BeanUtils.copyProperties(produtoCadastroDto, produto);
        return new ProdutoExibicaoDto(produtoRepository.save(produto));
    }

    // Buscar produto por ID
    public ProdutoExibicaoDto buscarPorId(Long id) {
        Optional<Produto> produtoOptional = produtoRepository.findById(id);

        if (produtoOptional.isPresent()) {
            return new ProdutoExibicaoDto(produtoOptional.get());
        } else {
            throw new ProdutoNaoEncontrado("Produto não encontrado!");
        }
    }

    // Listar todos os produtos (paginado)
    public Page<ProdutoExibicaoDto> listarTodosOsProdutos(Pageable paginacao) {
        return produtoRepository
                .findAll(paginacao)
                .map(ProdutoExibicaoDto::new);
    }

    // Excluir produto
    public void excluir(Long id) {
        Optional<Produto> produtoOptional = produtoRepository.findById(id);

        if (produtoOptional.isPresent()) {
            produtoRepository.delete(produtoOptional.get());
        } else {
            throw new ProdutoNaoEncontrado("Produto não encontrado para exclusão");
        }
    }

    // Atualizar produto
    public Produto atualizar(Produto produto) {
        Optional<Produto> produtoOptional = produtoRepository.findById(produto.getId());

        if (produtoOptional.isPresent()) {
            return produtoRepository.save(produto);
        } else {
            throw new ProdutoNaoEncontrado("Produto não encontrado para atualização");
        }
    }

    // Buscar produto pelo nome (agora retorna uma lista)
    public List<Produto> buscarPeloNome(String nome) {
        List<Produto> produtos = produtoRepository.buscarPeloNome(nome);

        if (produtos.isEmpty()) {
            throw new ProdutoNaoEncontrado("Nenhum produto encontrado com o nome informado");
        }

        return produtos;
    }
}
