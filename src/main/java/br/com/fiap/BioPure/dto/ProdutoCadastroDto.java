package br.com.fiap.BioPure.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record ProdutoCadastroDto(
        Long id,

        @NotBlank(message = "Nome do produto é obrigatório!")
        String nome,

        @NotBlank(message = "A Descrição é obrigatório!")
        String descricao,

        @NotNull(message = "O preço é obrigatório!")
        @DecimalMin(value = "0.0", inclusive = false, message = "O preço deve ser maior que zero!")
        double preco,

        @NotNull(message = "O tempo de degradação é obrigatório!")
        String tempoDegradacao
) {

}
