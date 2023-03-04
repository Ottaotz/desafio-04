package br.com.cwi.api.service;

import br.com.cwi.api.controller.response.ListarPostResponse;
import br.com.cwi.api.domain.Post;
import br.com.cwi.api.factories.PostFactory;
import br.com.cwi.api.factories.UsuarioFactory;
import br.com.cwi.api.repository.PostRepository;
import br.com.cwi.api.security.domain.Usuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListarPostsServiceTest {

    @InjectMocks
    private ListarPostsService tested;

    @Mock
    private PostRepository repository;

    @Test
    @DisplayName("Deve listar todos os posts")
    void deveListarTodosPosts() {
        Pageable pageable = PageRequest.of(0, 5);

        Usuario usuario = UsuarioFactory.get();
        Usuario usuario2 = UsuarioFactory.get();

        List<Post> posts = List.of(
                PostFactory.getPublico(usuario),
                PostFactory.getPublico(usuario),
                PostFactory.getPrivado(usuario2)
        );

        Page<Post> postsPaginados = new PageImpl<>(posts);

        when(repository.findByAllIgnoringCaseLegendaContains("", pageable)).thenReturn(postsPaginados);

        Page<ListarPostResponse> response = tested.listar("", pageable);

        verify(repository).findByAllIgnoringCaseLegendaContains("", pageable);
        assertEquals(posts.size(), response.getSize());
        assertEquals(posts.get(0).getId(), response.getContent().get(0).getId());
        assertEquals(posts.get(1).getId(), response.getContent().get(1).getId());
        assertEquals(posts.get(2).getId(), response.getContent().get(2).getId());
    }
}
