package br.com.cwi.api.mapper;

import br.com.cwi.api.controller.request.IncluirPostRequest;
import br.com.cwi.api.domain.Post;
import br.com.cwi.api.enuns.Visualizacao;
import br.com.cwi.api.factories.IncluirPostRequestFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class IncluirPostMapperTest {

    @InjectMocks
    private IncluirPostMapper tested;

    @Test
    @DisplayName("Deve retornar um post")
    void deveRetornarPost() {
        String imagem = "teste de imagem";
        String legenda = "teste de legenda";
        Visualizacao visualizacao = Visualizacao.PUBLICO;
        IncluirPostRequest request = IncluirPostRequestFactory.get(imagem, legenda, visualizacao);
        Post post = Post.builder()
                .imagem(imagem)
                .legenda(legenda)
                .visualizacao(visualizacao)
                .build();

        Post postMapper = IncluirPostMapper.toEntity(request);

        assertEquals(post.getImagem(), postMapper.getImagem());
        assertEquals(post.getLegenda(), postMapper.getLegenda());
        assertEquals(post.getVisualizacao(), postMapper.getVisualizacao());
    }
}
