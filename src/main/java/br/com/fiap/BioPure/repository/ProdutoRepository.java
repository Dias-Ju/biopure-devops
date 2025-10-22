package br.com.fiap.BioPure.repository;

import br.com.fiap.BioPure.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    // Busca todos os produtos que possuam o nome informado (pode retornar mais de um)
    @Query("SELECT p FROM Produto p WHERE LOWER(p.nome) = LOWER(:nome)")
    List<Produto> buscarPeloNome(@Param("nome") String nome);
}
