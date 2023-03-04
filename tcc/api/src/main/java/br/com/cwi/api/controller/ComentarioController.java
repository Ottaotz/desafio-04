package br.com.cwi.api.controller;

import br.com.cwi.api.controller.request.IncluirComentarioRequest;
import br.com.cwi.api.service.IncluirComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static br.com.cwi.api.security.domain.Funcao.Nomes.USUARIO;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {

    @Autowired
    private IncluirComentarioService incluirComentarioService;

    @Secured(USUARIO)
    @PostMapping("/{postId}/comentar")
    @ResponseStatus(CREATED)
    public void incluirComentario(@Valid @RequestBody IncluirComentarioRequest request, @PathVariable Long postId) {
        incluirComentarioService.incluirNoPost(postId, request);
    }
}
