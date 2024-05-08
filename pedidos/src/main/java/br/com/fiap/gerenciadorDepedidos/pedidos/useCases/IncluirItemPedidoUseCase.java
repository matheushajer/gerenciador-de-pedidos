package br.com.fiap.gerenciadorDepedidos.pedidos.useCases;


import br.com.fiap.gerenciadorDepedidos.pedidos.entities.ItemPedidoEntity;
import br.com.fiap.gerenciadorDepedidos.pedidos.entities.PedidoEntity;
import br.com.fiap.gerenciadorDepedidos.pedidos.entities.enuns.StatusPedido;
import br.com.fiap.gerenciadorDepedidos.pedidos.records.DadosInclusaoItemPedidoDTO;
import br.com.fiap.gerenciadorDepedidos.pedidos.records.ItemPedidoDTO;
import br.com.fiap.gerenciadorDepedidos.pedidos.repositories.PedidoRepository;
import br.com.fiap.gerenciadorDepedidos.produtos.adapters.ProdutoAdapter;
import br.com.fiap.gerenciadorDepedidos.produtos.entities.ProdutoEntity;
import br.com.fiap.gerenciadorDepedidos.produtos.repositories.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class IncluirItemPedidoUseCase {

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired(required = false)
    ProdutoAdapter produtoAdapter;

    @Autowired
    ProdutoRepository produtoRepository;

//    @Autowired
//    private EntregaAdapter entregaAdapter;

    @Transactional
    public void incluirItem(DadosInclusaoItemPedidoDTO dadosInclusaoItemDTO) {
        PedidoEntity pedidoEntity = pedidoRepository.findById(dadosInclusaoItemDTO.pedidoId())
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado"));

        if (pedidoEntity.getStatus() != StatusPedido.PROCESSANDO) {
            throw new EntityNotFoundException("Apenas pedidos em PROCESSANDO podem ser modificados.");
        }

        for (ItemPedidoDTO item : dadosInclusaoItemDTO.novosItens()) {
            ProdutoEntity produto = produtoRepository.findById(item.produtoId())
                    .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com ID: " + item.produtoId()));

            ItemPedidoEntity itemExistente = pedidoEntity.getItensPedido().stream()
                    .filter(existingItem -> existingItem.getProdutoId().equals(item.produtoId()))
                    .findFirst()
                    .orElse(null);

            if (itemExistente != null) {
                itemExistente.setQuantidade(itemExistente.getQuantidade() + item.quantidade());
            } else {
                // Assume ProdutoAdapter has a method to convert ProdutoEntity to some DTO if needed
                ItemPedidoEntity novoItem = new ItemPedidoEntity(
                        produto.getId(), produto.getNome(), produto.getPreco(), item.quantidade()
                );
                pedidoEntity.getItensPedido().add(novoItem); // Adicionar o novo item à lista de itens do pedido
            }

//        // Atualizar o valor do pedido e o valor do frete
//        atualizarValoresPedido(pedidoEntity);
//
            pedidoRepository.save(pedidoEntity);
        }

//    private void atualizarValoresPedido(PedidoEntity pedido) {
//        BigDecimal valorTotal = pedido.getItensPedido().stream()
//                .map(item -> item.getPreco().multiply(new BigDecimal(item.getQuantidade())))
//                .reduce(BigDecimal.ZERO, BigDecimal::add);
//
//        pedido.setValorPedido(valorTotal);
//        BigDecimal novoFrete = entregaAdapter.calcularFrete(pedido);
//        pedido.setFrete(novoFrete);
//        pedido.setValorComFrete(valorTotal.add(novoFrete));
//    }
    }
}