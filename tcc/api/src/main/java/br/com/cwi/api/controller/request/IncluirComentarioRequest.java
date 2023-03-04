package br.com.cwi.api.controller.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class IncluirComentarioRequest {

    @NotBlank(message = "Comentário Obrigatório")
    @Size(min = 1, max = 100, message = "Comentário deve conter de 1 a 100 caracteres")
    private String comentario;
}
