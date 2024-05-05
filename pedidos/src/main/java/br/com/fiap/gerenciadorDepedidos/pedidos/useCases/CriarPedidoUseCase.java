package br.com.fiap.gerenciadorDepedidos.pedidos.useCases;


import br.com.fiap.gerenciadorDepedidos.pedidos.adapters.PedidoAdapter;
import br.com.fiap.gerenciadorDepedidos.pedidos.entities.PedidoEntity;
import br.com.fiap.gerenciadorDepedidos.pedidos.entities.enuns.StatusPedido;
import br.com.fiap.gerenciadorDepedidos.pedidos.records.DadosCriacaoPedidoDTO;
import br.com.fiap.gerenciadorDepedidos.pedidos.repositories.PedidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CriarPedidoUseCase {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoAdapter pedidoAdapter;

    /**
     * Método para efetuar a criação de um novo pedido.
     *
     * @param dadosCriacaoPedidoDTO Objeto com os dados usados para criação do pedido.
     * @return DadosCriacaoPedidoDTO Objeto com os dados criados, após passarem pelas validações.
     */

    public DadosCriacaoPedidoDTO criarPedido(DadosCriacaoPedidoDTO dadosCriacaoPedidoDTO) {

        PedidoEntity pedidoEntity = pedidoAdapter.converterParaEntity(dadosCriacaoPedidoDTO);
        pedidoEntity.setStatus(StatusPedido.valueOf("CRIADO")); // Initial status
        pedidoRepository.save(pedidoEntity);

        return pedidoAdapter.converterParaDTO(pedidoEntity);

    }
}