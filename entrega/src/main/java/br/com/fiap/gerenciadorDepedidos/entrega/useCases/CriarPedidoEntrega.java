package br.com.fiap.gerenciadorDepedidos.entrega.useCases;

import br.com.fiap.gerenciadorDepedidos.entrega.entites.CepModel;
import br.com.fiap.gerenciadorDepedidos.entrega.entites.enuns.ModalidadeFrete;
import br.com.fiap.gerenciadorDepedidos.entrega.records.DadosEntregaDTO;
import br.com.fiap.gerenciadorDepedidos.entrega.records.FreteDTO;
import br.com.fiap.gerenciadorDepedidos.entrega.records.RequestCEPDTO;
import br.com.fiap.gerenciadorDepedidos.entrega.repositories.ClienteRepository;
import br.com.fiap.gerenciadorDepedidos.pedidos.repositories.PedidoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;

import static br.com.fiap.gerenciadorDepedidos.entrega.entites.enuns.ModalidadeFrete.*;

/**
 * Classe para representar o caso de uso de atualizar
 * o nome de um cliente.
 */
@Service
@Transactional
public class CriarPedidoEntrega {
    static class Coordenadas {
        double latitude;
        double longitude;

        Coordenadas(double latitude, double longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }
    }
    public DadosEntregaDTO criarEntrega(RequestCEPDTO requestCEPDTO) throws Exception {
        var enderecoRestaurante = BuscaCep(requestCEPDTO.cepOrigem());
        var enderecoEntrega = BuscaCep(requestCEPDTO.cepDestino());
        var prazos = calcularDistanciaTempoValor(enderecoEntrega.getCep(), enderecoEntrega.getCep(), requestCEPDTO.modalidade());
        DadosEntregaDTO dadosEntregaDTO = new DadosEntregaDTO();
        dadosEntregaDTO.setModalidade(requestCEPDTO.modalidade());
        dadosEntregaDTO.setPrazo(prazos.getValorDeFrete());
        dadosEntregaDTO.setPrazo(prazos.getTempo());
        return dadosEntregaDTO;
    }

    public CepModel BuscaCep(String cep) throws Exception {

        RestTemplate restTemplate = new RestTemplate();


        final String baseurl = "https://viacep.com.br/ws/"+ cep + "/json/";

        HttpHeaders headers = new HttpHeaders();

        HttpEntity<String> entity = new HttpEntity<>(headers);
        try {
            var response = restTemplate.exchange( baseurl, HttpMethod.GET, entity , CepModel.class );
            return response.getBody();
        } catch (Exception e) {
            throw new Exception(String.format("Erro ao obter token da url %s para o usuario %s", e));
        }
    }

    private static final Map<String, Coordenadas> localizacoesCEP = new HashMap<>();
    static {
        localizacoesCEP.put("01000-000-01999-999", new Coordenadas(-23.55052, -46.6333)); // Exemplo de faixa de CEP e coordenadas centrais
        localizacoesCEP.put("02000-000-02999-999", new Coordenadas(-23.5235, -46.6507)); // Outro exemplo de faixa de CEP e coordenadas centrais
        localizacoesCEP.put("03000-000-03999-999", new Coordenadas(-23.5307, -46.6396));
    }

    public FreteDTO calcularDistanciaTempoValor(String faixaCEPOrigem, String faixaCEPDestino, String modalidade) throws Exception {
        Coordenadas coordenadasOrigem = localizacoesCEP.get(faixaCEPOrigem);
        Coordenadas coordenadasDestino = localizacoesCEP.get(faixaCEPDestino);
        FreteDTO freteDTO = null;
        double distancia = calcularDistancia(coordenadasOrigem, coordenadasDestino);

        if (distancia > 50.0) {
            throw new Exception("A distância entre as faixas de CEP é maior do que 50 km.");
        }

        double tempo = distancia * 2;
        double valorFrete = 5.0 + (distancia * 0.5);

        switch (ModalidadeFrete.valueOf(modalidade)) {
            case MOTOBOY:
            case BIBICLETA:
                    valorFrete = valorFrete+0.5;
                    tempo = tempo + 1;
                break;
            case CAMINHAO:
            case VAN:
                 valorFrete = valorFrete + 1.0;
                tempo = tempo + 4;
        }


        freteDTO.setDistancia(distancia);
        freteDTO.setTempo(tempo);
        freteDTO.setValorDeFrete(valorFrete);
        return freteDTO;
    }

    private static double calcularDistancia(Coordenadas coordenadasOrigem, Coordenadas coordenadasDestino) {
        final double RAIO_TERRA = 6371.0;

        double latitudeOrigemRad = Math.toRadians(coordenadasOrigem.latitude);
        double longitudeOrigemRad = Math.toRadians(coordenadasOrigem.longitude);
        double latitudeDestinoRad = Math.toRadians(coordenadasDestino.latitude);
        double longitudeDestinoRad = Math.toRadians(coordenadasDestino.longitude);

        double difLat = latitudeDestinoRad - latitudeOrigemRad;
        double difLon = longitudeDestinoRad - longitudeOrigemRad;

        double a = Math.pow(Math.sin(difLat / 2), 2) + Math.cos(latitudeOrigemRad) * Math.cos(latitudeDestinoRad) * Math.pow(Math.sin(difLon / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distancia = RAIO_TERRA * c;

        return distancia;
    }
}