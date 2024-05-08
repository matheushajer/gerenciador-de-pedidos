package br.com.fiap.gerenciadorDepedidos.pedidos.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "tb_item_pedido")
public class ItemPedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PedidoEntity pedidoEntity;

    private Long produtoId;
    private String nome;
    private BigDecimal preco;
    private Integer quantidade;

    public ItemPedidoEntity() {
    }

    public ItemPedidoEntity(Long produtoId, String nome, BigDecimal preco, Integer quantidade, PedidoEntity pedidoEntity) {
        this.produtoId = produtoId;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.pedidoEntity = pedidoEntity;
    }

    public ItemPedidoEntity(Long produtoId, String nome, BigDecimal preco, Integer quantidade) {
        this.produtoId = produtoId;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }
}
