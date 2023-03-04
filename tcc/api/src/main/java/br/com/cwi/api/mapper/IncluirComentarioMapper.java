package br.com.cwi.api.mapper;

import br.com.cwi.api.controller.request.IncluirComentarioRequest;
import br.com.cwi.api.domain.Comentario;

public class IncluirComentarioMapper {

    public static Comentario toEntity(IncluirComentarioRequest request) {
        return Comentario.builder()
                .comentario(request.getComentario())
                .build();
    }
}
