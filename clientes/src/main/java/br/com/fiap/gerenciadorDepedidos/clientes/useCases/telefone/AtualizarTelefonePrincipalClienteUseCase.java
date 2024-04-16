package br.com.fiap.gerenciadorDepedidos.clientes.useCases.telefone;

import br.com.fiap.gerenciadorDepedidos.clientes.entities.TelefoneEntity;
import br.com.fiap.gerenciadorDepedidos.clientes.records.telefone.DadosAtualizarTelefoneDTO;
import br.com.fiap.gerenciadorDepedidos.clientes.repositories.TelefoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtualizarTelefonePrincipalClienteUseCase {

    @Autowired
    private TelefoneRepository telefoneRepository;


    /**
     * Metodo para atualizar o Telefone Principal do cliente
     *
     * @param clientId          identificador do cliente
     * @param atualizarTelefone objeto para salvar as informações de telefone
     * @return mensagem de sucesso caso a operação seja concluída
     */
    public String atualizarTelefonePrincipal(Long clientId, DadosAtualizarTelefoneDTO atualizarTelefone) {
        TelefoneEntity telefoneDataBase =
                this.telefoneRepository.findById(this.findByclienteEntityIdAndIsTelefonePrincialIsTrue(clientId).getId())
                        .orElseThrow(() -> new IllegalArgumentException("O Telefone Pricipal Não Foi Encontrado"));

        telefoneDataBase.setDdi(atualizarTelefone.ddi());
        telefoneDataBase.setDdd(atualizarTelefone.ddd());
        telefoneDataBase.setNumero(atualizarTelefone.numero());

        telefoneRepository.save(telefoneDataBase);

        return "Telefone Pricipal Atualizado com Sucesso";
    }

    /**
     * Pegar o telefone principal do cliente
     *
     * @param clienteId identificador do cliente
     * @return O telefone principal do cliente
     */
    private TelefoneEntity findByclienteEntityIdAndIsTelefonePrincialIsTrue(Long clienteId) {
        return this.telefoneRepository.findByclienteEntityIdAndIsTelefonePrincialIsTrue(clienteId);
    }

}