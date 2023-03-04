package br.com.cwi.api.service;

import br.com.cwi.api.controller.request.IncluirComentarioRequest;
import br.com.cwi.api.domain.Comentario;
import br.com.cwi.api.domain.Post;
import br.com.cwi.api.mapper.IncluirComentarioMapper;
import br.com.cwi.api.repository.ComentarioRepository;
import br.com.cwi.api.security.domain.Usuario;
import br.com.cwi.api.security.service.UsuarioAutenticadoService;
import br.com.cwi.api.service.core.NowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IncluirComentarioService {

    @Autowired
    private BuscarPostService buscarPostService;

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private NowService nowService;

    @Transactional
    public void incluirNoPost(Long postId, IncluirComentarioRequest request) {

        Post post = buscarPostService.porId(postId);
        Usuario usuario = usuarioAutenticadoService.get();

        Comentario comentario = IncluirComentarioMapper.toEntity(request);
        comentario.setDataPostagem(nowService.getDate());
        comentario.setUsuario(usuario);



        post.adicionarComentario(comentario);
        usuario.adicionarComentario(comentario);

        comentarioRepository.save(comentario);
    }
}
