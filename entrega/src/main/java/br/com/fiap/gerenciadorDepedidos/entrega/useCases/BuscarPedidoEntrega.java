package br.com.fiap.gerenciadorDepedidos.entrega.useCases;

import br.com.fiap.gerenciadorDepedidos.entrega.entities.ClienteEntity;
import br.com.fiap.gerenciadorDepedidos.entrega.records.DadosEntregaDTO;
import br.com.fiap.gerenciadorDepedidos.entrega.records.EntregaDTO;
import br.com.fiap.gerenciadorDepedidos.entrega.repositories.ClienteRepository;
import br.com.fiap.gerenciadorDepedidos.pedidos.entities.enuns.StatusPedido;
import br.com.fiap.gerenciadorDepedidos.pedidos.repositories.PedidoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Classe para representar o caso de uso de atualizar
 * o nome de um cliente.
 */
@Service
@Transactional
public class BuscarPedidoEntrega {

    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    PedidoRepository  pedidoRepository;

    public EntregaDTO buscarPedido(Long pedido) throws Exception {
        var ped = pedidoRepository.findById(pedido);
        if (ped.isPresent()) {
            DadosEntregaDTO dadosEntregaDTO = null;
            dadosEntregaDTO.setPrazo(ped.get().getPrazoDeEntrega().doubleValue());
            dadosEntregaDTO.setValorDeFrete(ped.get().getFrete().doubleValue());
            dadosEntregaDTO.setModalidade(ped.get().getModalidade);
            return new EntregaDTO(ped.get().getClienteId(), ped.get().getId(), dadosEntregaDTO, ped.get().getModalidade);
        }else {
            throw new Exception(String.format("Pedido nao encontrado. Erro ao buscar pedido."));
        }
    }
}