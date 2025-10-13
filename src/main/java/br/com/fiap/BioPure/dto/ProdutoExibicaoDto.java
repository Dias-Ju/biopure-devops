package br.com.fiap.BioPure.dto;

import br.com.fiap.BioPure.model.Produto;

import java.time.LocalDate;

public record ProdutoExibicaoDto(
        //Parâmetros dentro da classe record que criamos
        Long id,
        String nome,
        String descricao,
        String tempoDegradacao,
        double preco


){
    //código dentro da classe
    public ProdutoExibicaoDto(Produto produto) {
        this(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getTempoDegradacao(),
                produto.getPreco());
    }
}
