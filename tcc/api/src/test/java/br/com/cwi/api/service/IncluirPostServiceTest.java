package br.com.cwi.api.service;

import br.com.cwi.api.controller.request.IncluirPostRequest;
import br.com.cwi.api.domain.Post;
import br.com.cwi.api.factories.UsuarioFactory;
import br.com.cwi.api.repository.PostRepository;
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

import static br.com.cwi.api.factories.PostRequestFactory.getRequestPublico;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IncluirPostServiceTest {

    @InjectMocks
    private IncluirPostService tested;

    @Mock
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Mock
    private PostRepository repository;

    @Mock
    private NowService nowService;

    @Captor
    private ArgumentCaptor<Post> postCaptor;

    @Test
    @DisplayName("Deve incluir novo post com a data de publicação")
    void deveIncluirPost() {

        IncluirPostRequest postRequest = getRequestPublico();
        Usuario usuario = UsuarioFactory.get();

        LocalDateTime hoje = LocalDateTime.now();

        when(usuarioAutenticadoService.get()).thenReturn(usuario);
        when(nowService.getDate()).thenReturn(hoje);

        tested.incluir(postRequest);

        verify(repository).save(postCaptor.capture());

        Post post = postCaptor.getValue();
        assertEquals(hoje, post.getDataPostagem());
        assertEquals(postRequest.getVisualizacao(), post.getVisualizacao());
        assertEquals(postRequest.getLegenda(), post.getLegenda());
        assertEquals(postRequest.getImagem(), post.getImagem());
    }
}
