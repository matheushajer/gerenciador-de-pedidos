package br.com.fiap.gerenciadorDepedidos.pedidos.useCases;

import br.com.fiap.gerenciadorDepedidos.pedidos.entities.PedidoEntity;
import br.com.fiap.gerenciadorDepedidos.pedidos.entities.enuns.StatusPedido;
import br.com.fiap.gerenciadorDepedidos.pedidos.records.DadosAtualizacaoStatusPedidoDTO;
import br.com.fiap.gerenciadorDepedidos.pedidos.repositories.PedidoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtualizarStatusPedidoUseCase {

    @Autowired
    private PedidoRepository pedidoRepository;

//    @Autowired
//    private EntregaAdapter entregaAdapter;

    @Transactional
    public DadosAtualizacaoStatusPedidoDTO atualizarStatus(Long pedido_id, DadosAtualizacaoStatusPedidoDTO dadosAtualizacaoStatusPedidoDTO) {
        PedidoEntity pedido = pedidoRepository.findById(pedido_id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado com ID: " + dadosAtualizacaoStatusPedidoDTO.pedidoId()));

        if (pedido.getStatus().statusValido(dadosAtualizacaoStatusPedidoDTO.novoStatus())) {
            pedido.setStatus(dadosAtualizacaoStatusPedidoDTO.novoStatus());
            pedidoRepository.save(pedido);

//           Verifica se o novo status é PAGO e atualiza o status da entrega
//            if (dados.novoStatus() == StatusPedido.PAGO) {
//                entregaAdapter.atualizarStatusEntrega(pedido.getEntregaId(), dados.novoStatus());

        } else {
            throw new IllegalStateException("Transição de status inválida de " + pedido.getStatus() + " para " + dadosAtualizacaoStatusPedidoDTO.novoStatus());
        }

        return dadosAtualizacaoStatusPedidoDTO;

    }
}