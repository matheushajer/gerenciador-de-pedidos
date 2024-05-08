package br.com.fiap.gerenciadorDepedidos.entrega.records;

import jakarta.validation.constraints.NotNull;

public record RequestCEPDTO(

        @NotNull(message = "O Status do pedido é obrigatório.")
        String cepOrigem,

        @NotNull(message = "O Status do pedido é obrigatório.")
        String cepDestino,

        @NotNull(message = "O Status do pedido é obrigatório.")
        String modalidade

) {
}