package br.com.cwi.api.mapper;

import br.com.cwi.api.controller.response.ComentarioResponse;
import br.com.cwi.api.domain.Comentario;

public class DetalheComentarioMapper {

    public static ComentarioResponse toResponse(Comentario entity) {
        return ComentarioResponse.builder()
                .id(entity.getId())
                .usuarioId(entity.getUsuario().getId())
                .usuarioNome(entity.getUsuario().getNome())
                .comentario(entity.getComentario())
                .build();
    }
}
