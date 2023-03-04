package br.com.cwi.api.mapper;

import br.com.cwi.api.controller.response.ComentarioResponse;
import br.com.cwi.api.domain.Comentario;
import br.com.cwi.api.domain.Post;
import br.com.cwi.api.factories.ComentarioFactory;
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
class DetalheComentarioMapperTest {

    @InjectMocks
    private DetalheComentarioMapper tested;

    @Test
    @DisplayName("Deve retornar comentario detalhado")
    void deveRetornarComentarioDetalhado() {

        Usuario usuario = UsuarioFactory.get();
        Post post = PostFactory.getPublico(usuario);
        Comentario comentario = ComentarioFactory.get(usuario,post);

        ComentarioResponse response = DetalheComentarioMapper.toResponse(comentario);

        assertEquals(comentario.getId(), response.getId());
        assertEquals(comentario.getUsuario().getId(), response.getUsuarioId());
        assertEquals(comentario.getUsuario().getNome(), response.getUsuarioNome());
        assertEquals(comentario.getComentario(), response.getComentario());
    }
}
