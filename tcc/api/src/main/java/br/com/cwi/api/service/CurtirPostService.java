package br.com.cwi.api.service;

import br.com.cwi.api.domain.Post;
import br.com.cwi.api.security.domain.Usuario;
import br.com.cwi.api.security.repository.UsuarioRepository;
import br.com.cwi.api.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CurtirPostService {

    @Autowired
    private BuscarPostService buscarPostService;

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public void curtir(Long postId) {

        Post post = buscarPostService.porId(postId);
        Usuario usuario = usuarioAutenticadoService.get();

        usuario.adicionarCurtida(post);

        usuarioRepository.save(usuario);
    }

    @Transactional
    public void descurtir(Long postId) {
        Post post = buscarPostService.porId(postId);
        Usuario usuario = usuarioAutenticadoService.get();

        usuario.removerCurtida(post);

        usuarioRepository.save(usuario);
    }
}
