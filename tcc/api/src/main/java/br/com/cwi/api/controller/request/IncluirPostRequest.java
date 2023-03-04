package br.com.cwi.api.controller.request;

import br.com.cwi.api.enuns.Visualizacao;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class IncluirPostRequest {

    @NotBlank(message = "Imagem obrigatória")
    @Size(min = 1, max = 512, message = "Imagem deve conter de 1 a 512 caracteres")
    private String imagem;

    @NotBlank(message = "Legenda obrigatória")
    @Size(min = 1, max = 300, message = "Legenda deve conter de 1 a 300 caracteres")
    private String legenda;

    @NotNull(message = "Visualização obrigatória")
    private Visualizacao visualizacao;
}
