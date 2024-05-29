package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.acme.dto.ProdutoRequestDTO;
import org.acme.dto.ProdutoResponseDTO;
import org.acme.entity.Produto;
import org.jboss.resteasy.reactive.multipart.FileUpload;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class ProdutoService {

    @Transactional
    public ProdutoResponseDTO inserir(ProdutoRequestDTO produtoRequestDTO) {

        List<String> paths = armazenarArquivo(produtoRequestDTO.getFiles());
        Produto produto = ProdutoRequestDTO.parseToProduto(produtoRequestDTO);

        produto.setPaths(paths);
        Produto.persist(produto);

        return ProdutoResponseDTO.parseToProdutoResponseDTO(produto);

    }

    public List<String> armazenarArquivo(List<FileUpload> data) {

        String projectRoot = System.getProperty("user.dir") + File.separator + "filesUploaded";

        // Verifica se o diretório existe, senão cria
        File directory = new File(projectRoot);
        if (!directory.exists()) {
            if (!directory.mkdirs()) {
                throw new RuntimeException("Erro ao criar o diretório de destino");
            }
        }

        //Cria a lista de paths dos arquivos armazenados
        List<String> paths = new ArrayList<>();

        data.forEach(
                fileUpload -> {
                    try {
//                        paths.add(Files.copy(fileUpload.uploadedFile(), Paths.get(projectRoot + File.separator + fileUpload.fileName())).toString());
                        String uniqueFileName = UUID.randomUUID() + "_" + fileUpload.fileName();
                        Path destinationPath = Paths.get(projectRoot, uniqueFileName);

                        Files.copy(fileUpload.uploadedFile(), destinationPath);
                        paths.add(destinationPath.toString());

                    } catch (IOException e) {
                        throw new RuntimeException("Erro ao salvar arquivo: " + e.getMessage(), e);
                    }
                }
        );
        return paths;
    }
}
