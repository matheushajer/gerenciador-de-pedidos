package br.com.fiap.gerenciadorDepedidos.produtos.useCases.produto;

import br.com.fiap.gerenciadorDepedidos.produtos.adapters.ProdutoAdapter;
import br.com.fiap.gerenciadorDepedidos.produtos.entities.ProdutoEntity;
import br.com.fiap.gerenciadorDepedidos.produtos.records.DadosCriacaoProdutoDTO;
import br.com.fiap.gerenciadorDepedidos.produtos.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Classe para executar o caso de uso da criação
 * de um produto.
 */
@Service
@Transactional
public class CriarProdutoUseCase {

    @Autowired
    ProdutoAdapter produtoAdapter;
    @Autowired
    ProdutoRepository produtoRepository;

    public DadosCriacaoProdutoDTO criarProduto(DadosCriacaoProdutoDTO dadosCriacaoProdutoDTO) {

        ProdutoEntity produtoEntity = produtoAdapter.converterParaEntity(dadosCriacaoProdutoDTO);

        produtoRepository.save(produtoEntity);

        return produtoAdapter.converterParaDTO(produtoEntity);

    }


}
