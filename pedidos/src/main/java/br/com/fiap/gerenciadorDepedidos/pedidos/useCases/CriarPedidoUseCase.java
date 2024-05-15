package br.com.fiap.gerenciadorDepedidos.pedidos.useCases;

import br.com.fiap.gerenciadorDepedidos.pedidos.adapters.PedidoAdapter;
import br.com.fiap.gerenciadorDepedidos.pedidos.entities.PedidoEntity;
import br.com.fiap.gerenciadorDepedidos.pedidos.http.ProdutoClient;
import br.com.fiap.gerenciadorDepedidos.pedidos.records.DadosCriacaoPedidoDTO;
import br.com.fiap.gerenciadorDepedidos.pedidos.records.DadosProdutoParaItemPedidoDTO;
import br.com.fiap.gerenciadorDepedidos.pedidos.records.DadosRetornoCriacaoPedidoDTO;
import br.com.fiap.gerenciadorDepedidos.pedidos.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CriarPedidoUseCase {

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    PedidoAdapter pedidoAdapter;

    @Autowired
    ProdutoClient produtoClient;

    /**
     * Método para efetuar a criação de um novo pedido.
     *
     * @param dadosCriacaoPedidoDTO Objeto com os dados usados para criação do pedido.
     * @return DadosCriacaoPedidoDTO Objeto com os dados criados, após passarem pelas validações.
     */
    public DadosRetornoCriacaoPedidoDTO criarPedido(DadosCriacaoPedidoDTO dadosCriacaoPedidoDTO) {

        List<DadosProdutoParaItemPedidoDTO> dadosProdutoParaItemPedidoDTO = new ArrayList<>();

        dadosCriacaoPedidoDTO.itensPedido().forEach(item ->
                dadosProdutoParaItemPedidoDTO.add(produtoClient.buscarDadosParaItemPedido(item.produtoId()))
        );

        // Chamar API Externa de entrega

        PedidoEntity pedidoEntity = pedidoAdapter.converterParaEntity(dadosCriacaoPedidoDTO,
                dadosProdutoParaItemPedidoDTO);

        pedidoRepository.save(pedidoEntity);

        return pedidoAdapter.converterParaDadosRetornoCriacaoPedidoDTO(pedidoEntity);

    }

}
