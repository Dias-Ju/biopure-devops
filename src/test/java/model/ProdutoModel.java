package model;

public class ProdutoModel {

    private String nome;
    private String descricao;
    private Double preco;
    private String tempoDegradacao;


    public ProdutoModel() {}

    public ProdutoModel(String nome, String descricao, Double preco, String tempoDegradacao) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.tempoDegradacao = tempoDegradacao;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Double getPreco() { return preco; }
    public void setPreco(Double preco) { this.preco = preco; }

    public String getTempoDegradacao() { return tempoDegradacao; }
    public void setTempoDegradacao(String tempoDegradacao) { this.tempoDegradacao = tempoDegradacao; }
}
