package br.com.fiap.gerenciadorDepedidos.pedidos.useCases;


import br.com.fiap.gerenciadorDepedidos.pedidos.entities.ItemPedidoEntity;
import br.com.fiap.gerenciadorDepedidos.pedidos.entities.PedidoEntity;
import br.com.fiap.gerenciadorDepedidos.pedidos.entities.enuns.StatusPedido;
import br.com.fiap.gerenciadorDepedidos.pedidos.repositories.PedidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;

@Service
public class RemoverItemPedidoUseCase {

    @Autowired
    private PedidoRepository pedidoRepository;

//    @Autowired
//    private EntregaAdapter entregaAdapter;

    @Transactional
    public void removerItem(Long pedidoId, Long produtoId, Integer quantidade) {
        PedidoEntity pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado com ID: " + pedidoId));

        if (pedido.getStatus() != StatusPedido.PROCESSANDO) {
            throw new IllegalStateException("Itens só podem ser removidos quando o pedido está em PROCESSANDO.");
        }

        boolean itemFound = false;
        Iterator<ItemPedidoEntity> iterator = pedido.getItensPedido().iterator();
        while (iterator.hasNext()) {
            ItemPedidoEntity item = iterator.next();
            if (item.getProdutoId().equals(produtoId)) {
                if (item.getQuantidade() > quantidade) {
                    item.setQuantidade(item.getQuantidade() - quantidade);
                } else {
                    iterator.remove(); // Remove o item completamente se a quantidade a remover é maior ou igual.
                }
                itemFound = true;
                break;
            }
        }

        if (!itemFound) {
            throw new RuntimeException("Produto com ID " + produtoId + " não encontrado no pedido.");
        }

      //  atualizarValoresPedido(pedido);
        pedidoRepository.save(pedido);
    }

//    private void atualizarValoresPedido(PedidoEntity pedido) {
//        BigDecimal valorTotal = pedido.getItensPedido().stream()
//                .map(item -> item.getPreco().multiply(BigDecimal.valueOf(item.getQuantidade())))
//                .reduce(BigDecimal.ZERO, BigDecimal::add);
//
//        pedido.setValorPedido(valorTotal);
//        // Chamar o EntregaAdapter para calcular o frete
//        BigDecimal frete = entregaAdapter.calcularFrete(pedido);
//        pedido.setFrete(frete);
//        pedido.setValorComFrete(valorTotal.add(frete));
//    }
}