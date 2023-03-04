package br.com.cwi.api.factories;

import br.com.cwi.api.domain.Post;
import br.com.cwi.api.security.domain.Usuario;

import java.util.ArrayList;

import static br.com.cwi.api.enuns.Visualizacao.PRIVADO;
import static br.com.cwi.api.enuns.Visualizacao.PUBLICO;
import static br.com.cwi.api.factories.SimpleFactory.getRandomLong;
import static java.time.LocalDateTime.now;

public class PostFactory {

    private static Post.PostBuilder get(Usuario usuario) {
        return Post.builder()
                .id(getRandomLong())
                .usuario(usuario)
                .imagem("teste")
                .legenda("teste")
                .totalCurtidas(0L)
                .dataPostagem(now())
                .comentarios(new ArrayList<>());
    }

    public static Post getPublico(Usuario usuario) {
        return get(usuario)
                .visualizacao(PUBLICO)
                .build();
    }

    public static Post getPrivado(Usuario usuario) {
        return get(usuario)
                .visualizacao(PRIVADO)
                .build();
    }
}
