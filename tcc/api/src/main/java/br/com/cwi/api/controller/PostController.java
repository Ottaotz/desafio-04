package br.com.cwi.api.controller;

import br.com.cwi.api.controller.request.IncluirPostRequest;
import br.com.cwi.api.controller.response.ListarPostResponse;
import br.com.cwi.api.service.CurtirPostService;
import br.com.cwi.api.service.IncluirPostService;
import br.com.cwi.api.service.ListarPostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static br.com.cwi.api.security.domain.Funcao.Nomes.USUARIO;


@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private ListarPostsService listarPostsService;

    @Autowired
    private IncluirPostService incluirPostService;

    @Autowired
    private CurtirPostService curtirPostService;

    @Secured(USUARIO)
    @GetMapping
    public Page<ListarPostResponse> listar(@RequestParam String text, Pageable pageable) {
        return listarPostsService.listar(text, pageable);
    }

    @Secured(USUARIO)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void incluirPost(@Valid @RequestBody IncluirPostRequest request) {
        incluirPostService.incluir(request);
    }

    @Secured(USUARIO)
    @PutMapping("/{postId}/curtir")
    public void curtir(@PathVariable Long postId) {
        curtirPostService.curtir(postId);
    }

    @Secured(USUARIO)
    @PutMapping("/{postId}/descurtir")
    public void descurtir(@PathVariable Long postId) {
        curtirPostService.descurtir(postId);
    }
}
