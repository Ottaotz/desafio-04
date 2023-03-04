package br.com.cwi.api.service;

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
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static br.com.cwi.api.factories.SimpleFactory.getRandomLong;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BuscarPostServiceTest {

    @InjectMocks
    private BuscarPostService tested;

    @Mock
    private PostRepository repository;

    @Test
    @DisplayName("Deve retornar um post")
    void deveRetornarPost() {

        Usuario usuario = UsuarioFactory.get();
        Post post = PostFactory.getPublico(usuario);
        Optional<Post> optionalPost = Optional.ofNullable(post);
        Long postId = post.getId();

        when(repository.findById(postId)).thenReturn(optionalPost);

        Post response = tested.porId(postId);

        verify(repository).findById(postId);
        assertEquals(postId, response.getId());
        assertEquals(usuario, response.getUsuario());
    }

    @Test
    @DisplayName("Não deve retornar um post buscando por um id que não existe")
    void deveRetornarUmErro() {

        Long falsoId = getRandomLong();
        doThrow(ResponseStatusException.class).when(repository).findById(falsoId);

        assertThrows(ResponseStatusException.class, () -> tested.porId(falsoId));

        verify(repository).findById(falsoId);
    }
}
