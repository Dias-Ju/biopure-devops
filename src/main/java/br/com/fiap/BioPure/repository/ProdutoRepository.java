package br.com.fiap.BioPure.repository;

import aj.org.objectweb.asm.commons.Remapper;
import br.com.fiap.BioPure.dto.ProdutoCadastroDto;
import br.com.fiap.BioPure.model.Produto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query("SELECT c FROM Produto c WHERE c.nome = :nome")
    Optional<Produto> buscarPeloNome(@Param("nome") String nome);
}
