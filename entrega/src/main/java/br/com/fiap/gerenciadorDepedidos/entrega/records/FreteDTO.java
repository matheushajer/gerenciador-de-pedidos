package br.com.fiap.gerenciadorDepedidos.entrega.records;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class FreteDTO {

    Double distancia;
    Double ValorDeFrete;
    Double tempo;
}