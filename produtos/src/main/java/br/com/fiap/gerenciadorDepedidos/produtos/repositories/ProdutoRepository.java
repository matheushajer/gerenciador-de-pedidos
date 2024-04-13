package br.com.fiap.gerenciadorDepedidos.produtos.repositories;

import br.com.fiap.gerenciadorDepedidos.produtos.entities.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {
}
