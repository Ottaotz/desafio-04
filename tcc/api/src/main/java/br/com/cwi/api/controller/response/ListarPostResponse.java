package br.com.cwi.api.controller.response;

import br.com.cwi.api.enuns.Visualizacao;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.Enumerated;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static javax.persistence.EnumType.STRING;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@JsonInclude(NON_NULL)
public class ListarPostResponse {

    private Long id;

    private Long usuarioId;

    private String nomeUsuario;

    private String imagem;

    private String legenda;

    private Long totalCurtidas;

    private LocalDateTime dataPostagem;

    @Enumerated(STRING)
    private Visualizacao visualizacao;

    private List<ComentarioResponse> comentarios = new ArrayList<>();
}
