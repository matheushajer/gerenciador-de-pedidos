package br.com.fiap.gerenciadorDepedidos.pedidos.adapters;

import br.com.fiap.gerenciadorDepedidos.pedidos.entities.ItemPedidoEntity;
import br.com.fiap.gerenciadorDepedidos.pedidos.entities.PedidoEntity;
import br.com.fiap.gerenciadorDepedidos.pedidos.records.ItemPedidoDTO;
import br.com.fiap.gerenciadorDepedidos.produtos.adapters.ProdutoAdapter;
import br.com.fiap.gerenciadorDepedidos.produtos.entities.ProdutoEntity;
import br.com.fiap.gerenciadorDepedidos.produtos.records.DadosProdutoParaPedidoDTO;
import br.com.fiap.gerenciadorDepedidos.produtos.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemPedidoAdapter {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoAdapter produtoAdapter;

    // Converte um ItemPedidoDTO para uma entidade de ItemPedido, utilizando um PedidoEntity associado.
    public ItemPedidoEntity converterParaEntity(ItemPedidoDTO itemPedidoDTO, PedidoEntity pedidoEntity) {
        // Busca o produto pelo ID, lançando exceção se não encontrado.
        ProdutoEntity produto = produtoRepository.findById(itemPedidoDTO.produtoId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado para o ID: " + itemPedidoDTO.produtoId()));

        // Converte os dados do produto para um DTO específico para pedido.
        DadosProdutoParaPedidoDTO produtoParaPedidoDTO = produtoAdapter.converterParaDadosPedidoDTO(produto);

        // Retorna uma nova entidade de ItemPedido configurada com os dados do produto e pedido.
        return new ItemPedidoEntity(
                itemPedidoDTO.produtoId(),
                produtoParaPedidoDTO.nome(),
                produtoParaPedidoDTO.preco(),
                itemPedidoDTO.quantidade(),
                pedidoEntity
        );

    }

    // Converte uma lista de entidades de ItemPedido para uma lista de DTOs de ItemPedido.

    public List<ItemPedidoDTO> converterParaListaDeDTO(List<ItemPedidoEntity> itemPedidoEntity) {

        List<ItemPedidoDTO> itemPedidoDTO = new ArrayList<>();
        itemPedidoEntity.forEach(itemPedido ->
                itemPedidoDTO.add(new ItemPedidoDTO(
                        itemPedido.getProdutoId(),
                        itemPedido.getNome(),
                        itemPedido.getPreco(),
                        itemPedido.getQuantidade()
                ))
        );

        return itemPedidoDTO;
    }

    // Converte uma lista de ItemPedidoDTO para uma lista de entidades de ItemPedido, utilizando um PedidoEntity associado.

    public List<ItemPedidoEntity> converterParaListaDeItensPedido(List<ItemPedidoDTO> itemPedidoDTOList, PedidoEntity pedidoEntity) {
        List<ItemPedidoEntity> itemPedidoEntityList = new ArrayList<>();

        itemPedidoDTOList.forEach(itemPedidoDTO -> {
            ProdutoEntity produto = produtoRepository.findById(itemPedidoDTO.produtoId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado para o ID: " + itemPedidoDTO.produtoId()));


            itemPedidoEntityList.forEach(itemPedido ->
                    itemPedidoEntityList.add(new ItemPedidoEntity(
                            itemPedido.getProdutoId(),
                            produto.getNome(),
                            produto.getPreco(),
                            itemPedido.getQuantidade(),
                            pedidoEntity
                    )));
        });

        return itemPedidoEntityList;
    }

    // Converte uma lista de entidades de ItemPedido para uma lista de DTOs de ItemPedido.

    public List<ItemPedidoDTO> converterListaItemPedidoEntityParaListaItemPedidoDTO(List<ItemPedidoEntity> itemPedidoEntities) {
        List<ItemPedidoDTO> itenspedidos = new ArrayList<>();

        itemPedidoEntities.forEach(itemPedido -> {
            ItemPedidoDTO itemPedidoDTO = new ItemPedidoDTO(itemPedido.getProdutoId(), itemPedido.getNome(), itemPedido.getPreco(), itemPedido.getQuantidade());
            itenspedidos.add(itemPedidoDTO);

        });
        return itenspedidos;
    }
}
