package br.com.cwi.api.service;

import br.com.cwi.api.controller.request.IncluirPostRequest;
import br.com.cwi.api.domain.Post;
import br.com.cwi.api.mapper.IncluirPostMapper;
import br.com.cwi.api.repository.PostRepository;
import br.com.cwi.api.security.domain.Usuario;
import br.com.cwi.api.security.service.UsuarioAutenticadoService;
import br.com.cwi.api.service.core.NowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IncluirPostService {

    private static final Long CURTIDAS_PADRAO = 0L;

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private NowService nowService;

    @Transactional
    public void incluir(IncluirPostRequest request) {

        Usuario usuario = usuarioAutenticadoService.get();

        Post post = IncluirPostMapper.toEntity(request);
        post.setDataPostagem(nowService.getDate());
        post.setTotalCurtidas(CURTIDAS_PADRAO);

        usuario.adicionarPost(post);

        postRepository.save(post);

    }
}
