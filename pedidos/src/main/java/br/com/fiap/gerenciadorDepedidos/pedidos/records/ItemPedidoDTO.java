package br.com.fiap.gerenciadorDepedidos.pedidos.records;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ItemPedidoDTO(
        @NotNull(message = "O ID do produto é obrigatório.")
        @Valid
        Long produtoId,

        @NotNull String nome,
        @NotNull @Min(0) BigDecimal preco,

        @NotNull(message = "A quantidade do produto é obrigatória.")
        @Min(value = 1, message = "A quantidade do produto deve ser pelo menos 1.")
        Integer quantidade
) {
}
