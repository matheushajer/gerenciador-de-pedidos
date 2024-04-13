package br.com.fiap.gerenciadorDepedidos.clientes.adapters.cliente;

import br.com.fiap.gerenciadorDepedidos.clientes.adapters.endereco.EnderecoAdapter;
import br.com.fiap.gerenciadorDepedidos.clientes.adapters.telefone.TelefoneAdapter;
import br.com.fiap.gerenciadorDepedidos.clientes.entities.ClienteEntity;
import br.com.fiap.gerenciadorDepedidos.clientes.records.cliente.DadosCriacaoClienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Classe para efetuar tratamento dos dados vindo das APIs
 * e dos dados retornados nas operações do cliente.
 */
@Service
public class ClienteAdapter {

    @Autowired
    TelefoneAdapter telefoneAdapter;
    @Autowired
    EnderecoAdapter enderecoAdapter;

    /**
     * Método para converter os dados vindo da API para um ClienteEntity.
     *
     * @param dadosCriacaoClienteDTO Objeto DadosCriacaoClienteDTO a ser convertido.
     * @return Objeto ClienteEntity resultante da conversão.
     */
    public ClienteEntity converterParaEntity(DadosCriacaoClienteDTO dadosCriacaoClienteDTO) {

        ClienteEntity clienteEntity = new ClienteEntity(
                dadosCriacaoClienteDTO.nome(),
                dadosCriacaoClienteDTO.cpf(),
                dadosCriacaoClienteDTO.email()
        );

        clienteEntity.setTelefoneEntity(telefoneAdapter.converterParaListaDeTelefones(
                dadosCriacaoClienteDTO.dadosCriacaoTelefoneDTO(), clienteEntity));

        clienteEntity.setEnderecoEntity(enderecoAdapter.converterParaListaDeEnderecos(
                dadosCriacaoClienteDTO.dadosCriacaoEnderecoDTO(), clienteEntity
        ));

        return clienteEntity;

    }

    /**
     * Método para converter os dados vindo da API para um ClienteEntity.
     *
     * @param clienteEntity Objeto ClienteEntity a ser convertido.
     * @return Objeto DadosCriacaoClienteDTO resultante da conversão.
     */
    public DadosCriacaoClienteDTO converterParaDTO(ClienteEntity clienteEntity) {

        return new DadosCriacaoClienteDTO(
                clienteEntity.getNome(),
                censurarCPF(clienteEntity.getCpf()),
                clienteEntity.getEmail(),
                telefoneAdapter.converterParaListaDeDTO(clienteEntity.getTelefoneEntity()),
                enderecoAdapter.converterParaListaDeDTO(clienteEntity.getEnderecoEntity())
        );

    }

    /**
     * Método auxiliar para censurar os dados do CPF.
     *
     * @param cpf CPF que será censurado.
     * @return CPF censurado, na seguinte formatação | ***-***-**6-76
     */
    private String censurarCPF(String cpf) {

        // Aplica a censura no CPF
        return "***-***-**" + cpf.substring(8);

    }

}
