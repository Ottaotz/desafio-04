package br.com.cwi.api.mapper;

import br.com.cwi.api.controller.response.ListarPostResponse;
import br.com.cwi.api.domain.Post;
import br.com.cwi.api.factories.PostFactory;
import br.com.cwi.api.factories.UsuarioFactory;
import br.com.cwi.api.security.domain.Usuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ListarPostMapperTest {

    @InjectMocks
    private ListarPostsMapper tested;

    @Test
    @DisplayName("Deve retornar com informações iguais ao do post passado")
    void deveRetornarrResponseDeAcordo() {

        Usuario usuario = UsuarioFactory.get();
        Post post = PostFactory.getPublico(usuario);

        ListarPostResponse response = ListarPostsMapper.toResponse(post);

        assertEquals(post.getId(), response.getId());
        assertEquals(post.getUsuario().getId(), response.getUsuarioId());
        assertEquals(post.getUsuario().getNome(), response.getNomeUsuario());
        assertEquals(post.getImagem(), response.getImagem());
        assertEquals(post.getLegenda(), response.getLegenda());
        assertEquals(post.getTotalCurtidas(), response.getTotalCurtidas());
        assertEquals(post.getDataPostagem(), response.getDataPostagem());
        assertEquals(post.getComentarios(), response.getComentarios());
    }
}
