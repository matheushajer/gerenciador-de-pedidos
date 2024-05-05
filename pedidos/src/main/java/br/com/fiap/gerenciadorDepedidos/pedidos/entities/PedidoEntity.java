package br.com.fiap.gerenciadorDepedidos.pedidos.entities;

import br.com.fiap.gerenciadorDepedidos.pedidos.entities.enuns.StatusPedido;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Cascade;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "tb_pedido")
public class PedidoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long clienteId;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, orphanRemoval = true)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    private List<ItemPedidoEntity> itensPedido;

    private BigDecimal valorPedido;
    private Integer prazoDeEntrega;
    private BigDecimal frete;
    private BigDecimal valorComFrete;

    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    private Integer codigoDeRastreio;
    private LocalDateTime dataCriacaoPedido;

    // Construtores
    public PedidoEntity() {
    }

    public PedidoEntity(Long clienteId, List<ItemPedidoEntity> itensPedido, BigDecimal valorPedido, Integer prazoDeEntrega,
                        BigDecimal frete, BigDecimal valorComFrete, StatusPedido status, Integer codigoDeRastreio,
                        LocalDateTime dataCriacaoPedido) {
        this.clienteId = clienteId;
        this.itensPedido = itensPedido;
        this.valorPedido = valorPedido;
        this.prazoDeEntrega = prazoDeEntrega;
        this.frete = frete;
        this.valorComFrete = valorComFrete;
        this.status = status;
        this.codigoDeRastreio = codigoDeRastreio;
        this.dataCriacaoPedido = dataCriacaoPedido;
    }

}
