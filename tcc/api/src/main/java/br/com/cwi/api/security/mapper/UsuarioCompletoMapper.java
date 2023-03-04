package br.com.cwi.api.security.mapper;

import br.com.cwi.api.security.controller.response.UsuarioCompletoResponse;
import br.com.cwi.api.security.controller.response.UsuarioResumidoResponse;
import br.com.cwi.api.security.domain.Usuario;

import java.util.List;
import java.util.stream.Collectors;

public class UsuarioCompletoMapper {

    public static UsuarioCompletoResponse toResponseCompleto(Usuario entity) {
        return UsuarioCompletoResponse.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .amigos(resumido(entity.getAmigos()))
                .solicitacoes(resumido(entity.getSolicitacoes()))
                .dataNascimento(entity.getDataNascimento())
                .imagemPerfil(entity.getImagemPerfil())
                .apelido(entity.getApelido())
                .build();
    }

    private static List<UsuarioResumidoResponse> resumido(List<Usuario> amigos) {
        return amigos.stream()
                .map(UsuarioResumidoMapper::toResponse)
                .collect(Collectors.toList());
    }
}
