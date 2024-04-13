package br.com.fiap.gerenciadorDepedidos.clientes.adapters.endereco;

import br.com.fiap.gerenciadorDepedidos.clientes.entities.ClienteEntity;
import br.com.fiap.gerenciadorDepedidos.clientes.entities.EnderecoEntity;
import br.com.fiap.gerenciadorDepedidos.clientes.records.endereco.DadosCriacaoEnderecoDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe para efetuar tratamento dos dados vindo das APIs
 * e dos dados retornados
 */
@Service
public class EnderecoAdapter {


    /**
     * Método para converter os dados vindo da API para um EnderecoEntity.
     *
     * @param dadosCriacaoEnderecoDTO Objeto DadosCriacaoEnderecoDTO a ser convertido.
     * @return Objeto EnderecoEntity resultante da conversão.
     */
    public EnderecoEntity conveterParaEntity(
            DadosCriacaoEnderecoDTO dadosCriacaoEnderecoDTO, ClienteEntity clienteEntity) {

        return new EnderecoEntity(
                dadosCriacaoEnderecoDTO.cep(),
                dadosCriacaoEnderecoDTO.logradouro(),
                dadosCriacaoEnderecoDTO.numero(),
                dadosCriacaoEnderecoDTO.complemento(),
                dadosCriacaoEnderecoDTO.bairro(),
                dadosCriacaoEnderecoDTO.cidade(),
                dadosCriacaoEnderecoDTO.uf(),
                dadosCriacaoEnderecoDTO.isEnderecoPrincipal(),
                clienteEntity
        );

    }

    /**
     * Método, para efetuar a conversão, de um EnderecoEntity, para um
     * DadosCriacaoEnderecoDTO.
     *
     * @param enderecoEntity Objeto EnderecoEntity a ser convertido.
     * @return Objeto DadosCriacaoEnderecoDTO resultante da conversão.
     */
    public List<DadosCriacaoEnderecoDTO> converterParaListaDeDTO(List<EnderecoEntity> enderecoEntity) {

        List<DadosCriacaoEnderecoDTO> dadosCriacaoEnderecoDTOList = new ArrayList<>();

        enderecoEntity.forEach(endereco ->
                dadosCriacaoEnderecoDTOList.add(new DadosCriacaoEnderecoDTO(
                        endereco.getCep(),
                        endereco.getLogradouro(),
                        endereco.getNumero(),
                        endereco.getComplemento(),
                        endereco.getBairro(),
                        endereco.getCidade(),
                        endereco.getUf(),
                        endereco.isEnderecoPrincipal()
                ))
        );

        return dadosCriacaoEnderecoDTOList;

    }

    public List<EnderecoEntity> converterParaListaDeEnderecos(
            List<DadosCriacaoEnderecoDTO> dadosCriacaoEnderecoDTOList, ClienteEntity clienteEntity) {

        long enderecosPrincipais = dadosCriacaoEnderecoDTOList.stream().filter(DadosCriacaoEnderecoDTO::isEnderecoPrincipal)
                .count();

        if (enderecosPrincipais > 1) {
            throw new IllegalArgumentException("Apenas um endereço deve estar configurado como principal!");
        }

        List<EnderecoEntity> enderecoEntityList = new ArrayList<>();

        dadosCriacaoEnderecoDTOList.forEach(endereco ->
                enderecoEntityList.add(new EnderecoEntity(
                        endereco.cep(),
                        endereco.logradouro(),
                        endereco.numero(),
                        endereco.complemento(),
                        endereco.bairro(),
                        endereco.cidade(),
                        endereco.uf(),
                        endereco.isEnderecoPrincipal(),
                        clienteEntity
                ))
        );

        return enderecoEntityList;

    }

}