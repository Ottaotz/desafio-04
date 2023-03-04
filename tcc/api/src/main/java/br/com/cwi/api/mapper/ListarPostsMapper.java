package br.com.cwi.api.mapper;

import br.com.cwi.api.controller.response.ComentarioResponse;
import br.com.cwi.api.controller.response.ListarPostResponse;
import br.com.cwi.api.domain.Comentario;
import br.com.cwi.api.domain.Post;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class ListarPostsMapper {

    public static ListarPostResponse toResponse(Post entity) {
        return ListarPostResponse.builder()
                .id(entity.getId())
                .usuarioId(entity.getUsuario().getId())
                .nomeUsuario(entity.getUsuario().getNome())
                .imagem(entity.getImagem())
                .legenda(entity.getLegenda())
                .totalCurtidas(entity.getTotalCurtidas())
                .dataPostagem(entity.getDataPostagem())
                .visualizacao(entity.getVisualizacao())
                .comentarios(comentarios(entity.getComentarios()))
                .build();
    }

    private static List<ComentarioResponse> comentarios(List<Comentario> comentarios) {
        return comentarios.stream()
                .map(DetalheComentarioMapper::toResponse)
                .collect(toList());
    }
}
