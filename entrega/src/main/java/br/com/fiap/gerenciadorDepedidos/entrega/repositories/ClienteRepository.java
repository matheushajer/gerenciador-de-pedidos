package br.com.fiap.gerenciadorDepedidos.entrega.repositories;

import br.com.fiap.gerenciadorDepedidos.entrega.entities.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> , JpaSpecificationExecutor<ClienteEntity> {
    ClienteEntity findByCpf(String cpf);

    boolean existsByCpf(String cpf);
}
