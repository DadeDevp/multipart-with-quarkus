package org.acme.dto;

import lombok.Data;
import org.acme.entity.Produto;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProdutoResponseDTO {
    private Long id;
    private String nome;
    private BigDecimal preco;
    private List<String> paths;

    public static ProdutoResponseDTO parseToProdutoResponseDTO(Produto obj){
        ProdutoResponseDTO produtoResponseDTO = new ProdutoResponseDTO();

        produtoResponseDTO.setNome(obj.getNome());
        produtoResponseDTO.setPreco(obj.getPreco());
        produtoResponseDTO.setPaths(obj.getPaths());

        return produtoResponseDTO;
    }
}
