package br.com.cwi.api.security.controller.response;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuarioCompletoResponse {

    private Long id;

    private String nome;

    private String apelido;

    private LocalDate dataNascimento;

    private String imagemPerfil;

    private List<UsuarioResumidoResponse> amigos;

    private List<UsuarioResumidoResponse> solicitacoes;
}
