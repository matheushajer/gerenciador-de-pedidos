package br.com.fiap.gerenciadorDepedidos.pedidos.adapters;


import br.com.fiap.gerenciadorDepedidos.pedidos.entities.PedidoEntity;
import br.com.fiap.gerenciadorDepedidos.pedidos.records.DadosCriacaoPedidoDTO;
import br.com.fiap.gerenciadorDepedidos.pedidos.records.PedidoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoAdapter {

    @Autowired
    private ItemPedidoAdapter itemPedidoAdapter;

    // Converte um DadosCriacaoPedidoDTO para um PedidoEntity, associando os itens de pedido correspondentes.
    public PedidoEntity converterParaEntity(DadosCriacaoPedidoDTO dadosCriacaoPedidoDTO) {
        PedidoEntity pedidoEntity = new PedidoEntity();
        // Configura os dados básicos do pedidoEntity a partir do DTO recebido
        dadosCriacaoPedidoDTO.clienteId();
        dadosCriacaoPedidoDTO.valorPedido();
        dadosCriacaoPedidoDTO.prazoDeEntrega();
        dadosCriacaoPedidoDTO.frete();
        dadosCriacaoPedidoDTO.valorComFrete();
        dadosCriacaoPedidoDTO.status();
        dadosCriacaoPedidoDTO.codigoDeRastreio();
        dadosCriacaoPedidoDTO.dataCriacaoPedido();

        // Converte os itens de pedido DTO para entidades de ItemPedido e associa ao pedidoEntity
        pedidoEntity.setItensPedido(itemPedidoAdapter.converterParaListaDeItensPedido(dadosCriacaoPedidoDTO.itensPedido(), pedidoEntity));

        return pedidoEntity;
    }

    // Converte um PedidoEntity para um DadosCriacaoPedidoDTO.
    public DadosCriacaoPedidoDTO converterParaDTO(PedidoEntity pedidoEntity) {
        return new DadosCriacaoPedidoDTO(
                pedidoEntity.getClienteId(),
                itemPedidoAdapter.converterParaListaDeDTO(pedidoEntity.getItensPedido()),
                pedidoEntity.getValorPedido(),
                pedidoEntity.getPrazoDeEntrega(),
                pedidoEntity.getFrete(),
                pedidoEntity.getValorComFrete(),
                pedidoEntity.getStatus(),
                pedidoEntity.getCodigoDeRastreio(),
                pedidoEntity.getDataCriacaoPedido()
        );
    }

    // Converte um PedidoEntity para um PedidoDTO, incluindo uma lista de ItemPedidoDTO.
    public PedidoDTO converterPedidoEntityParaPedidoDTO(PedidoEntity pedidoEntity) {
        return new PedidoDTO(
                pedidoEntity.getId(),
                pedidoEntity.getClienteId(),
                itemPedidoAdapter.converterParaListaDeDTO(pedidoEntity.getItensPedido()),
                pedidoEntity.getValorPedido(),
                pedidoEntity.getPrazoDeEntrega(),
                pedidoEntity.getFrete(),
                pedidoEntity.getValorComFrete(),
                pedidoEntity.getStatus(),
                pedidoEntity.getCodigoDeRastreio(),
                pedidoEntity.getDataCriacaoPedido()
        );
    }

    // Converte uma lista de PedidoEntity para uma lista de PedidoDTO.
    public List<PedidoDTO> converterPedidoEntityListParaPedidoDTOList(List<PedidoEntity> pedidoEntityList) {
        List<PedidoDTO> pedidoDTOList = new ArrayList<>();
        // Converte cada PedidoEntity para PedidoDTO e adiciona à lista de retorno
        pedidoEntityList.forEach(entity -> pedidoDTOList.add(converterPedidoEntityParaPedidoDTO(entity)));
        return pedidoDTOList;
    }
}
