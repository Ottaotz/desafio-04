package br.com.cwi.api.service;

import br.com.cwi.api.controller.response.ListarPostResponse;
import br.com.cwi.api.mapper.ListarPostsMapper;
import br.com.cwi.api.repository.PostRepository;
import br.com.cwi.api.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ListarPostsService {

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private PostRepository postRepository;

    public Page<ListarPostResponse> listar(String text, Pageable pageable) {
        return postRepository.findByAllIgnoringCaseLegendaContains(text, pageable)
                .map(ListarPostsMapper::toResponse);
    }
}
