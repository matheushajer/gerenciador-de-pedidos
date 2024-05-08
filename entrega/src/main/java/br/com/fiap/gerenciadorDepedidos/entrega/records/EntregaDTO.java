package br.com.fiap.gerenciadorDepedidos.entrega.records;

import br.com.fiap.gerenciadorDepedidos.entrega.entites.enuns.StatusPedido;

import java.util.List;

public record EntregaDTO(
        Long id,
        Long pedido_id,
        DadosEntregaDTO DadosDeEntrega,
        StatusPedido status
) {
}