package br.com.fiap.gerenciadorDepedidos.entrega.records;

public record DadosEnvioProdutoDTO(
        Long produto_id,
        Integer largura,
        Integer altura,
        Integer comprimento,
        Integer peso,
        Integer quantidade
) {
}