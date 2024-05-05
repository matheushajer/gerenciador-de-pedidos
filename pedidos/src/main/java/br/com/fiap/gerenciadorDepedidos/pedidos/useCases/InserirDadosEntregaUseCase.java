package br.com.fiap.gerenciadorDepedidos.pedidos.useCases;


import br.com.fiap.gerenciadorDepedidos.pedidos.entities.PedidoEntity;
import br.com.fiap.gerenciadorDepedidos.pedidos.entities.enuns.StatusPedido;
import br.com.fiap.gerenciadorDepedidos.pedidos.repositories.PedidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Use case para inserir dados de entrega em um pedido e atualizar seu status para AGUARDANDO_PAGAMENTO.
 */
@Service
public class InserirDadosEntregaUseCase {

    @Autowired
    private PedidoRepository pedidoRepository;

//    @Autowired
//    private EntregaAdapter entregaAdapter;

//    @Transactional
//    public void inserirDadosEntrega(Long pedidoId, DadosEntregaDTO dadosEntrega) {
//        PedidoEntity pedidoEntity = pedidoRepository.findById(pedidoId)
//                .orElseThrow(() -> new RuntimeException("Pedido não encontrado com ID: " + pedidoId));
//
//        // Gerar código de rastreio de 8 dígitos
//        int codigoDeRastreio = gerarCodigoDeRastreio();
//        pedidoEntity.setCodigoDeRastreio(codigoDeRastreio);
//
//        pedidoEntity.setPrazoDeEntrega(dadosEntrega.prazoDeEntrega());
//        pedidoEntity.setFrete(dadosEntrega.frete());
//
//        pedidoEntity.setStatus(StatusPedido.AGUARDANDO_PAGAMENTO);
//
//        pedidoRepository.save(pedidoEntity);
//    }
//
//    private int gerarCodigoDeRastreio() {
//        Random random = new Random();
//        return 10000000 + random.nextInt(90000000);  // Gera um número entre 10000000 e 99999999
//
//    }
}