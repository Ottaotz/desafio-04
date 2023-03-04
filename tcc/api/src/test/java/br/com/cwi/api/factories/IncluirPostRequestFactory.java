package br.com.cwi.api.factories;

import br.com.cwi.api.controller.request.IncluirPostRequest;
import br.com.cwi.api.enuns.Visualizacao;

public class IncluirPostRequestFactory {

    public static IncluirPostRequest get(String imagem, String legenda, Visualizacao visualizacao) {
        return IncluirPostRequest.builder()
                .imagem(imagem)
                .legenda(legenda)
                .visualizacao(visualizacao)
                .build();
    }
}
