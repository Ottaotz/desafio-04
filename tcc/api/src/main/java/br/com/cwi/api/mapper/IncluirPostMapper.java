package br.com.cwi.api.mapper;

import br.com.cwi.api.controller.request.IncluirPostRequest;
import br.com.cwi.api.domain.Post;

public class IncluirPostMapper {

    public static Post toEntity(IncluirPostRequest request) {
        return Post.builder()
                .imagem(request.getImagem())
                .legenda(request.getLegenda())
                .visualizacao(request.getVisualizacao())
                .build();
    }
}
