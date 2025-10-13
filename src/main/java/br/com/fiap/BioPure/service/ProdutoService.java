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

import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired //injeta no ProdutoService o repositório para ser instanciado AUTOMATICAMENTE
    private ProdutoRepository produtoRepository;

    public ProdutoExibicaoDto gravar(ProdutoCadastroDto produtoCadastroDto) {
        Produto produto = new Produto();
        //Todas as propriedades de ProdutoCadastroDto vai ser copiado para o objeto produto.
        BeanUtils.copyProperties(produtoCadastroDto,produto);
        return new ProdutoExibicaoDto(produtoRepository.save(produto));
    }

    public ProdutoExibicaoDto buscarPorId(Long id) {
        //O findById retorna um valor "Optional" ou seja, serve para lidar com o fato
        //de se ter um objeto nulo. Por isso, para corrigir, temos que fazer o seguinte:
        Optional<Produto> produtoOptional = produtoRepository.findById(id);

        if(produtoOptional.isPresent()) {
            return new ProdutoExibicaoDto(produtoOptional.get());
        }
        else {
            throw new ProdutoNaoEncontrado("Produto não encontrado!");
        }
    }

    public Page<ProdutoExibicaoDto> listarTodosOsProdutos(Pageable paginacao) {
        return produtoRepository
                .findAll(paginacao)
                .map(ProdutoExibicaoDto::new)
                ; //findAll retorna uma List<> de produto que será retornada em páginas
    }

    public void excluir(Long id) {
        Optional<Produto> produtoOptional = produtoRepository.findById(id);

        if(produtoOptional.isPresent()){
            produtoRepository.delete(produtoOptional.get());
        }
        else {
            throw new ProdutoNaoEncontrado("Produto não encontrado para exclusão");
        }
    }


    public Produto atualizar(Produto produto) {
        Optional<Produto> produtoOptional = produtoRepository.findById(produto.getId());

        if(produtoOptional.isPresent()){
            return produtoRepository.save(produto);
        }
        else {
            throw new ProdutoNaoEncontrado("Produto não encontrado");
        }
    }

    public Produto buscarPeloNome(String nome) {
        Optional<Produto> ProdutoOptional = produtoRepository.buscarPeloNome(nome);

        if(ProdutoOptional.isPresent()){
            return ProdutoOptional.get();
        }
        else {
            throw new ProdutoNaoEncontrado("Produto não encontrado");
        }
    }

//    public List<ProdutoExibicaoDto> listarAniversariantesPorPeriodo(LocalDate dataInicial, LocalDate dataFinal) {
//        return produtoRepository
//                .listarAniversariantesPorPeriodo(dataInicial, dataFinal)
//                .stream()
//                .map(ProdutoExibicaoDto::new)
//                .toList();
//    }


//    public Page<ProdutoExibicaoDto> listarTodosOsProdutos(Pageable paginacao) {
//        return null;
//    }
}
