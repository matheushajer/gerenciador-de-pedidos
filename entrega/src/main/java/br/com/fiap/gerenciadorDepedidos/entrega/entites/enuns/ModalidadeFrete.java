package br.com.fiap.gerenciadorDepedidos.entrega.entites.enuns;

public enum ModalidadeFrete {
    CAMINHAO,
    MOTOBOY,
    BIBICLETA,
    RETIRADA,
    VAN,
    TRANSPORTADORA;

    public static ModalidadeFrete fromString(String modalidade) {
        return ModalidadeFrete.valueOf(modalidade.toUpperCase());
    }
}
