package br.com.fiap.BioPure.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "tbl_produtos")
public class Produto {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "PRODUTOS_SEQ"
    )
    @SequenceGenerator(
            name = "PRODUTOS_SEQ",
            sequenceName = "PRODUTOS_SEQ",
            allocationSize = 1
    )
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    private String nome;
    private String descricao;
    private String tempoDegradacao;

    private double preco;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTempoDegradacao() {
        return tempoDegradacao;
    }

    public void setTempoDegradacao(String tempoDegradacao) {
        this.tempoDegradacao = tempoDegradacao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(id, produto.id) && Objects.equals(nome, produto.nome) && Objects.equals(descricao, produto.descricao) && Objects.equals(tempoDegradacao, produto.tempoDegradacao) && Objects.equals(preco, produto.preco);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, descricao, tempoDegradacao, preco);
    }
}
