package br.com.cwi.api.service;

import br.com.cwi.api.controller.request.IncluirComentarioRequest;
import br.com.cwi.api.domain.Comentario;
import br.com.cwi.api.domain.Post;
import br.com.cwi.api.factories.ComentarioRequestFactory;
import br.com.cwi.api.factories.PostFactory;
import br.com.cwi.api.factories.UsuarioFactory;
import br.com.cwi.api.repository.ComentarioRepository;
import br.com.cwi.api.security.domain.Usuario;
import br.com.cwi.api.security.service.UsuarioAutenticadoService;
import br.com.cwi.api.service.core.NowService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IncluirComentarioServiceTest {

    @InjectMocks
    private IncluirComentarioService tested;

    @Mock
    private BuscarPostService buscarPostService;

    @Mock
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Mock
    private ComentarioRepository repository;

    @Mock
    private NowService nowService;

    @Captor
    private ArgumentCaptor<Comentario> comentarioCaptor;

    @Test
    @DisplayName("Deve incluir comentário")
    void deveIncluirComentário() {

        Usuario usuarioPost = UsuarioFactory.get();
        Post post = PostFactory.getPublico(usuarioPost);

        Usuario usuario = UsuarioFactory.get();

        IncluirComentarioRequest request = ComentarioRequestFactory.get();

        LocalDateTime hoje = now();

        when(nowService.getDate()).thenReturn(hoje);
        when(buscarPostService.porId(post.getId())).thenReturn(post);
        when(usuarioAutenticadoService.get()).thenReturn(usuario);

        tested.incluirNoPost(post.getId(), request);


        verify(repository).save(comentarioCaptor.capture());

        Comentario comentario = comentarioCaptor.getValue();
        assertEquals(hoje, comentario.getDataPostagem());
        assertEquals(request.getComentario(), comentario.getComentario());
        assertEquals(post.getId(), comentario.getPost().getId());
        assertEquals(usuario.getId(), comentario.getUsuario().getId());
    }
}
