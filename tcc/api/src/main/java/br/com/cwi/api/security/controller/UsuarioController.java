package br.com.cwi.api.security.controller;

import br.com.cwi.api.security.controller.request.UsuarioRequest;
import br.com.cwi.api.security.controller.response.UsuarioCompletoResponse;
import br.com.cwi.api.security.controller.response.UsuarioResponse;
import br.com.cwi.api.security.controller.response.UsuarioResumidoResponse;
import br.com.cwi.api.security.service.AmizadeService;
import br.com.cwi.api.security.service.BuscarUsuarioService;
import br.com.cwi.api.security.service.IncluirUsuarioService;
import br.com.cwi.api.security.service.PesquisarUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static br.com.cwi.api.security.domain.Funcao.Nomes.USUARIO;

@RestController
@RequestMapping("/usuarios")
    public class UsuarioController {

    @Autowired
    private IncluirUsuarioService incluirUsuarioService;

    @Autowired
    private BuscarUsuarioService buscarUsuarioService;

    @Autowired
    private PesquisarUsuarioService pesquisarUsuarioService;

    @Autowired
    private AmizadeService amizadeService;

    @PostMapping
    public UsuarioResponse incluir(@Valid @RequestBody UsuarioRequest request) {
        return incluirUsuarioService.incluir(request);
    }

    @Secured(USUARIO)
    @GetMapping("/me")
    public UsuarioResponse buscar() {
        return buscarUsuarioService.buscar();
    }

    @Secured(USUARIO)
    @GetMapping("/me/detalhe")
    public UsuarioCompletoResponse buscarDetalhe() {
        return buscarUsuarioService.buscarDetalhe();
    }

    @Secured(USUARIO)
    @GetMapping("/pesquisar")
    public Page<UsuarioResumidoResponse> pesquisar(@RequestParam String text, Pageable pageable) {
        return pesquisarUsuarioService.pesquisar(text, pageable);
    }

    @Secured(USUARIO)
    @PutMapping("/amigos/{amigoId}/solicitar")
    public void solicitarAmizade(@PathVariable Long amigoId) {
        amizadeService.solicitar(amigoId);
    }

    @Secured(USUARIO)
    @PutMapping("/amigos/{amigoId}/adicionar")
    public void adicionarAmigo(@PathVariable Long amigoId) {
        amizadeService.adicionar(amigoId);
    }

    @Secured(USUARIO)
    @PutMapping("/amigos/{amigoId}/rejeitar")
    public void rejeitarAmigo(@PathVariable Long amigoId) {
        amizadeService.rejeitar(amigoId);
    }

    @Secured(USUARIO)
    @PutMapping("/amigos/{amigoId}/remover")
    public void removerAmizade(@PathVariable Long amigoId) {
        amizadeService.remover(amigoId);
    }
}
