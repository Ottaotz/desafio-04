package br.com.cwi.api.factories;

import br.com.cwi.api.controller.request.IncluirPostRequest;

import static br.com.cwi.api.enuns.Visualizacao.PRIVADO;
import static br.com.cwi.api.enuns.Visualizacao.PUBLICO;

public class PostRequestFactory {

    private static IncluirPostRequest.IncluirPostRequestBuilder getBuilder() {
        return IncluirPostRequest.builder()
                .imagem("teste")
                .legenda("teste");
    }

    public static IncluirPostRequest getRequestPublico() {
        return getBuilder()
                .visualizacao(PUBLICO)
                .build();
    }

    public static IncluirPostRequest getRequestPrivado() {
        return getBuilder()
                .visualizacao(PRIVADO)
                .build();
    }
}