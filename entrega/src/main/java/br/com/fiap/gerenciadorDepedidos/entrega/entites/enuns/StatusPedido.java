package br.com.fiap.gerenciadorDepedidos.entrega.entites.enuns;

public enum StatusPedido {
    CRIADO,
    PREPARANDO_PACOTE,
    ENVIANDO_PARA_TRANSPORTADORA,
    MERCADORIA_RECEBIDA,
    MERCADORIA_EM_TRANSITO,
    ENTREGUE;

    public static StatusPedido fromString(String statuspedido) {
        return StatusPedido.valueOf(statuspedido.toUpperCase());
    }
}
