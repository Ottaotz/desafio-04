package br.com.cwi.api.factories;

import br.com.cwi.api.domain.Comentario;
import br.com.cwi.api.domain.Post;
import br.com.cwi.api.security.domain.Usuario;

import static java.time.LocalDateTime.now;

public class ComentarioFactory {

    public static Comentario get(Usuario usuario, Post post) {
        return Comentario.builder()
                .id(SimpleFactory.getRandomLong())
                .comentario("teste comentário")
                .dataPostagem(now())
                .usuario(usuario)
                .post(post)
                .build();
    }
}
