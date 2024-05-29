package org.acme.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.dto.ProdutoRequestDTO;
import org.acme.service.ProdutoService;
import org.eclipse.microprofile.openapi.annotations.Operation;

@Path("/produtos")
public class ProdutoResource {

    @Inject
    ProdutoService produtoService;

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN)
    @Operation(summary = "Upload de arquivos", description = "Upload de múltiplos arquivos")
    public Response inserir(ProdutoRequestDTO data) {

        return Response.ok(produtoService.inserir(data)).build();

    }
}
