package br.com.fiap.BioPure.dto;

import jakarta.validation.constraints.*;

public record ProdutoCadastroDto(

        Long id,

        @NotBlank(message = "O nome do produto é obrigatório!")
        String nome,

        @NotBlank(message = "A descrição é obrigatória!")
        String descricao,

        @NotNull(message = "O preço é obrigatório!")
        @DecimalMin(value = "0.0", inclusive = false, message = "O preço deve ser maior que zero!")
        Double preco,  // 🔹 Corrigido: de 'double' (primitivo) para 'Double' (objeto)

        @NotBlank(message = "O tempo de degradação é obrigatório!") // 🔹 Corrigido: era @NotNull
        String tempoDegradacao
) {
}

