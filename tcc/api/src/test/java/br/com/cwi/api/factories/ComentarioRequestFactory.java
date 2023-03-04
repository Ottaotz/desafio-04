package br.com.cwi.api.factories;

import br.com.cwi.api.controller.request.IncluirComentarioRequest;

public class ComentarioRequestFactory {

    public static IncluirComentarioRequest get() {
        return IncluirComentarioRequest.builder()
                .comentario("teste comentário")
                .build();
    }
}
