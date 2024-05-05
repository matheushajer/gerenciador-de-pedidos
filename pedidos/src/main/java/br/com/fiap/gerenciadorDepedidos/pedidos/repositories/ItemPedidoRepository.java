package br.com.fiap.gerenciadorDepedidos.pedidos.repositories;

import br.com.fiap.gerenciadorDepedidos.pedidos.entities.ItemPedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

    @Repository
    public interface ItemPedidoRepository extends JpaRepository<ItemPedidoEntity, Long> {

        List<ItemPedidoEntity> findByPedidoId(Long pedidoId);

        ItemPedidoEntity findByPedidoIdAndId(Long pedidoId, Long itemId);
    }

