package br.com.fiap.gerenciadorDepedidos.pedidos.entities.enuns;

/**
 * Enumerador que define os possíveis estados de um pedido.
 */
public enum StatusPedido {
    CRIADO,              // O pedido foi criado e está aguardando processamento.
    PROCESSANDO,         // O pedido está sendo processado.
    AGUARDANDO_PAGAMENTO,// O pedido está aguardando o pagamento ser confirmado.
    PAGO,                // O pagamento foi confirmado.
    PEDIDO_ENVIADO,      // O pedido foi enviado para entrega.
    ENTREGUE,            // O pedido foi entregue ao cliente.
    CANCELADO;           // O pedido foi cancelado.

    /**
     * Converte uma string para um status de pedido correspondente.
     *
     * @param categoria A string que representa o status do pedido.
     * @return StatusPedido correspondente à string fornecida.
     */
    public static StatusPedido fromString(String categoria) {
        return StatusPedido.valueOf(categoria.toUpperCase());
    }

    /**
     * Verifica se é possível alterar o status atual do pedido para um novo status.
     *
     * @param novoStatus O novo status para o qual se deseja mudar.
     * @return true se a mudança for permitida, false caso contrário.
     */
    public boolean statusValido(StatusPedido novoStatus) {
        switch (this) {
            case PROCESSANDO:
                // Permite mudança de PROCESSANDO para PAGO.
                return novoStatus == StatusPedido.PAGO;
            case PAGO:
                // Permite mudança de PAGO para PEDIDO_ENVIADO.
                return novoStatus == StatusPedido.PEDIDO_ENVIADO;
            case PEDIDO_ENVIADO:
                // Permite mudança de PEDIDO_ENVIADO para ENTREGUE.
                return novoStatus == StatusPedido.ENTREGUE;
            case ENTREGUE:
                // O status ENTREGUE é final e não pode ser alterado.
                return false;
            case CANCELADO:
                // O status CANCELADO é final e não pode ser alterado.
                return false;
            default:
                // Qualquer outro status inicial não especifica transições permitidas.
                return false;
        }
    }
}
