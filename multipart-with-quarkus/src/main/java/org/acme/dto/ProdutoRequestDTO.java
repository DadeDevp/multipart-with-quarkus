package org.acme.dto;

import jakarta.ws.rs.FormParam;
import lombok.Data;
import org.acme.entity.Produto;
import org.jboss.resteasy.reactive.multipart.FileUpload;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProdutoRequestDTO {

    @FormParam("nome")
    private String nome;

    @FormParam("preco")
    private BigDecimal preco;

    @FormParam("files")
    private List<FileUpload> files;

    public static Produto parseToProduto(ProdutoRequestDTO obj){
        Produto produto = new Produto();

        produto.setNome(obj.getNome());
        produto.setPreco(obj.getPreco());

        return produto;
    }
}
