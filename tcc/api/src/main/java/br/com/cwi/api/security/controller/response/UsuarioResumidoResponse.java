package br.com.cwi.api.security.controller.response;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuarioResumidoResponse {

    private Long id;

    private String nome;

    private String apelido;

    private String imagemPerfil;
}
