package br.com.fiap.gerenciadorDepedidos.produtos.adapters;

import br.com.fiap.gerenciadorDepedidos.produtos.entities.ProdutoEntity;
import br.com.fiap.gerenciadorDepedidos.produtos.records.DadosCriacaoProdutoDTO;
import org.springframework.stereotype.Service;

/**
 * Classe para efetuar tratamento dos dados vindo das APIs
 * e dos dados retornados.
 */
@Service
public class ProdutoAdapter {

    /**
     * Método para efetuara conversão de um objeto DadosCriacaoProdutoDTO para
     * um objeto ProdutoEntity.
     *
     * @param dadosCriacaoProdutoDTO Objeto com os dados a serem convertidos.
     * @return ProdutoEntity Objeto com os dados convertidos.
     */
    public ProdutoEntity converterParaEntity(DadosCriacaoProdutoDTO dadosCriacaoProdutoDTO) {

        return new ProdutoEntity(
                dadosCriacaoProdutoDTO.nome(),
                dadosCriacaoProdutoDTO.descricao(),
                dadosCriacaoProdutoDTO.preco(),
                dadosCriacaoProdutoDTO.quantidade(),
                dadosCriacaoProdutoDTO.categoria(),
                dadosCriacaoProdutoDTO.altura(),
                dadosCriacaoProdutoDTO.largura(),
                dadosCriacaoProdutoDTO.comprimento(),
                dadosCriacaoProdutoDTO.peso()
        );

    }

    /**
     * Método para converter um objeto do tipo ProdutoEntity para
     * um objeto do tipo DadosCriacaoProdutoDTO.
     *
     * @param produtoEntity Objeto com os dados a serem convertidos.
     * @return DadosCriacaoProdutoDTO Objeto com os dados convertidos.
     */
    public DadosCriacaoProdutoDTO converterParaDTO(ProdutoEntity produtoEntity) {

        return new DadosCriacaoProdutoDTO(
                produtoEntity.getNome(),
                produtoEntity.getDescricao(),
                produtoEntity.getPreco(),
                produtoEntity.getQuantidade(),
                produtoEntity.getCategoria(),
                produtoEntity.getAltura(),
                produtoEntity.getLargura(),
                produtoEntity.getComprimento(),
                produtoEntity.getPeso()
        );

    }


}
