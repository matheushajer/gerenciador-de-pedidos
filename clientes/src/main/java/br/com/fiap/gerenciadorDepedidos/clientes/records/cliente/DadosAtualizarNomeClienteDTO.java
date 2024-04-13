package br.com.fiap.gerenciadorDepedidos.clientes.records.cliente;

import jakarta.validation.constraints.NotBlank;

public record DadosAtualizarNomeClienteDTO(
        @NotBlank(message = "O novo nome do cliente é obrigatório!")
        String nomeAtualizado
) {
}
